package com.example.academicdraft.service;

import com.example.academicdraft.entity.User;
import com.example.academicdraft.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private LogService logService;
    private UserRepository userRepository;
    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, LogService logService){
        this.userRepository = userRepository;
        this.logService = logService;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(usernameOrEmail);
        if(user != null){
            return new org.springframework.security.core.userdetails.User(user.getEmail(),
                    user.getPassword(), user.getRoles().stream().
                    map((role) -> new SimpleGrantedAuthority(role.getName()))
                    .collect(Collectors.toList()));
        }else {
            throw new UsernameNotFoundException("Invalid email or password");
        }

    }
}
