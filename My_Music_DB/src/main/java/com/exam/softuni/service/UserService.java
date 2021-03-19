package com.exam.softuni.service;

import com.exam.softuni.model.entity.User;
import com.exam.softuni.model.service.UserServiceModel;

public interface UserService {
    boolean findUserByUsername(String username);

    boolean findUserByEmail(String email);

    void registerUser(UserServiceModel userServiceModel);

    boolean findUserByUsernameAndPassword(String username, String password);

    User getUserByUsername(String username);
}
