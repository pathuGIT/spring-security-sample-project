package com.athome.springTest.controller;

import com.athome.springTest.dto.RoleChangeRequest;
import com.athome.springTest.model.Role;
import com.athome.springTest.model.Users;
import com.athome.springTest.repository.UserRepository;
import com.athome.springTest.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class SuperAdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UsersService usersService;




    @PutMapping("/change_user_role/{id}")
    public ResponseEntity<?> change_user_role(@PathVariable int id, @RequestBody RoleChangeRequest request){
        try {
            Users response = usersService.changeUserRole(id, request.getRole());

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred"); // 500
        }
    }
}
