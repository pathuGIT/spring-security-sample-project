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


}
