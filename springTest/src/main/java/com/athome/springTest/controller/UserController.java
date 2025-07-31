package com.athome.springTest.controller;

import com.athome.springTest.model.Course;
import com.athome.springTest.model.Users;
import com.athome.springTest.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UsersService usersService;

    @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('SUB_ADMIN')")
    @GetMapping("/list_all_users")
    public ResponseEntity<List<Users>> list_all_users(){
        List<Users> users =  usersService.getAllUsers();

        if(users.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        return ResponseEntity.ok(users);
    }

    // Update users name, password
    // Super admin can update all users data by passing user id
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update_user(@PathVariable int id, @RequestBody Users user){
        try {
            Users res =  usersService.update(id, user);
            return ResponseEntity.ok(res);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
