package com.example.laba8.service;

import com.example.laba8.dto.UserDto;
import com.example.laba8.entity.User;
import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
    List<UserDto> findAllUsers();
}