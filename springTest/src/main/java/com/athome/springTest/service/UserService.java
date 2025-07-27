package com.athome.springTest.service;

import com.athome.springTest.model.Users;
import com.athome.springTest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public Users addUser(Users users) {
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        return userRepository.save(users);
    }

    public Map<String, String> verifyUser(Users user) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        if(authentication.isAuthenticated()){
             String accessToken = jwtService.generateToken(userRepository.findByUsername(user.getUsername()));
             String refreshToken = jwtService.generateRefreshToken(userRepository.findByUsername(user.getUsername()));

            return Map.of(
                    "accessToken", accessToken,
                    "refreshToken", refreshToken
            );
        }
        throw new RuntimeException("Invalid credentials");
    }

    public Map<String, String> getRefreshToken(String refresh_token) {
        try {
            String username = jwtService.extractUserName(refresh_token);
            String role = jwtService.extractRole(refresh_token);

            UserDetails userDetails = User.withUsername(username).password("").roles(role).build();

            if(jwtService.validateToken(refresh_token, userDetails)){
                String newAccessToken = jwtService.generateToken(userRepository.findByUsername(username));
                return Map.of("accessToken", newAccessToken);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Error generate refresh token.");
    }

}
