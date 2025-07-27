package com.athome.springTest.controller;

import com.athome.springTest.model.Users;
import com.athome.springTest.repository.UserRepository;
import com.athome.springTest.service.JwtService;
import com.athome.springTest.service.MyUserDetailService;
import com.athome.springTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
//@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody Users user){
        try {
            Map<String, String> res = userService.verifyUser(user);
            if (res != null) {
                return ResponseEntity.ok(res);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }
        } catch (Exception e) {
            return ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> getRefreshToken(@RequestBody Map<String, String> request){
        try {
            String refresh_token = request.get("refreshToken");
            String username = jwtService.extractUserName(refresh_token);
            String role = jwtService.extractRole(refresh_token);

            UserDetails userDetails = User.withUsername(username).password("").roles(role).build();

            if(jwtService.validateToken(refresh_token, userDetails)){
                String newAccessToken = jwtService.generateToken(userRepository.findByUsername(username));
                return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        }
    }

    @GetMapping("/")
    public ResponseEntity<Map<String, String>> home() {
        Map<String, String> body = new HashMap<>();
        body.put("message", "User Home page");
        return ResponseEntity.ok(body);
    }

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody Users users){
        try {
            Users res = userService.addUser(users);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }

    }
}
