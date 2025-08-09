package com.athome.springTest.controller;

import com.athome.springTest.dto.LoginRequest;
import com.athome.springTest.dto.TokenResponse;
import com.athome.springTest.model.Role;
import com.athome.springTest.model.Users;
import com.athome.springTest.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Users users){
        try {
            users.setRole(Role.USER); // force default
            Users response = authService.registerUser(users);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            TokenResponse tokenResponse = authService.verifyUser(request);
            return ResponseEntity.ok(tokenResponse); // 200 OK instead of 201
        } catch (IllegalArgumentException ex) {
            // Bad request: invalid username/password or user not found
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            // Internal server error for unexpected cases
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> getRefreshToken(@RequestBody Map<String, String> request){
        try {
            String refresh_token = request.get("refreshToken");
            Map<String, String> res =  authService.getRefreshToken(refresh_token);
            return ResponseEntity.ok(res);

        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        }
    }

}

