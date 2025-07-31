package com.athome.springTest.controller;

import com.athome.springTest.dto.RoleChangeRequest;
import com.athome.springTest.dto.UserResponse;
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
    private UsersService usersService;

    @PutMapping("/change_user_role/{id}")
    public ResponseEntity<?> change_user_role(@PathVariable int id, @RequestBody RoleChangeRequest request){
        try {
            UserResponse response = usersService.changeUserRole(id, request.getRole());
            return ResponseEntity.ok(response);
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred"); // 500
        }
    }

    // Super admin can update all users name by passing user id
    @PutMapping("/update_user/{id}")
    public ResponseEntity<?> update_user(@PathVariable int id, @RequestBody Users user){
        try {
            System.out.println("her "+ id + user);
            UserResponse res =  usersService.update(id, user);
            return ResponseEntity.ok(res);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete_user/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        try {
            String res =  usersService.delete(id);
            return ResponseEntity.ok(res);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/update_user_password/{id}")
    public ResponseEntity<?> updatePassword(@PathVariable int id, @RequestBody Users user){
        try {
            String res =  usersService.updatePassword(id, user);
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
