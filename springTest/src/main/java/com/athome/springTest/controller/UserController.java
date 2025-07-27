package com.athome.springTest.controller;

import com.athome.springTest.model.Users;
import com.athome.springTest.service.JwtService;
import com.athome.springTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
//@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody Users user){
        System.out.println("tet");
        try {
            String res = userService.verifyUser(user);
            if (res != null) {
                return ResponseEntity.ok(res);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }
        } catch (Exception e) {
            return ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR);
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
