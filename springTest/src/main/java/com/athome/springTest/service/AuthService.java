package com.athome.springTest.service;

import com.athome.springTest.dto.LoginRequest;
import com.athome.springTest.dto.TokenResponse;
import com.athome.springTest.model.Users;
import com.athome.springTest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public Users registerUser(Users users) {
        // Example: check if user already exists
        if (userRepository.existsByUsername(users.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        return userRepository.save(users);
    }

    public TokenResponse verifyUser(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        if(!authentication.isAuthenticated()){
            throw new RuntimeException("Invalid credentials");
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String role = userDetails.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");


        String activeToken = jwtService.generateActiveToken(userDetails.getUsername() , role);
        String refreshToken = jwtService.generateRefreshToken(userDetails.getUsername() , role);

        return new TokenResponse(activeToken, refreshToken);
    }

    public Map<String, String> getRefreshToken(String refreshToken) {
        try {
            String username = jwtService.extractUserName(refreshToken);
            String role = jwtService.extractRole(refreshToken);

            UserDetails userDetails = User.withUsername(username).password("").roles(role).build();

            if(jwtService.validateToken(refreshToken, userDetails)){
                String newAccessToken = jwtService.generateActiveToken(userDetails.getUsername(), role);
                return Map.of("accessToken", newAccessToken);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Error generate refresh token.");
    }
}
