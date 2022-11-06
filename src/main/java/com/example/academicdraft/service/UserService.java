package com.example.academicdraft.service;

import com.example.academicdraft.dto.UserDto;
import com.example.academicdraft.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();

}
