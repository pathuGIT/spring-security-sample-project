package com.athome.springTest.service;

import com.athome.springTest.model.Role;
import com.athome.springTest.model.Users;
import com.athome.springTest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Users changeUserRole(int id, Role role) {
        Users user = userRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("User not found with ID: " + id));

        if (user.getRole().equals(role)) {
            throw new IllegalArgumentException("User ID: " + id + " already has this role: " + role);
        }

        user.setRole(role);

        return userRepository.save(user);
    }

    public Users update(int id, Users user) {
        Users userExist = userRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("User not found with ID: " + id));
        if(userRepository.existsByUsername(user.getUsername()))
            throw new IllegalArgumentException("This user name already have.");

        userExist.setUsername(user.getUsername());
        return userRepository.save(userExist);
    }

    public String delete(int id) {
        userRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("User not found with ID: " + id));

        userRepository.deleteById(id);
        return "Successfully deleted user: " + id;
    }

    public Users updatePassword(int id, Users user) {
        Users existUser = userRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("User not found with ID: " + id));

        existUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(existUser);
    }
}
