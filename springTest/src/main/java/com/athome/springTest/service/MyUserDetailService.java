package com.athome.springTest.service;

import com.athome.springTest.model.Users;
import com.athome.springTest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("test 4");
        Users response = userRepository.findByUsername(username);

        if(response == null)
            throw new UsernameNotFoundException("User not found: " + username);

        return User.builder()
                .username(response.getUsername())
                .password(response.getPassword())
                .roles(response.getRole())
                .build();

    }
}
