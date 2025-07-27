package com.athome.springTest.service;

import com.athome.springTest.model.Users;
import com.athome.springTest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public Users addUser(Users users) {
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        return userRepository.save(users);
    }

    public String verifyUser(Users user) {
        Users foundUser = userRepository.findByUsername(user.getUsername());
        if (foundUser != null && bCryptPasswordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
            return jwtService.generateToken(foundUser);
        }
        return null;
    }

}
