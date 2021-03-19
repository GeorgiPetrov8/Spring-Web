package com.exam.softuni.service.impl;

import com.exam.softuni.model.entity.User;
import com.exam.softuni.model.service.UserServiceModel;
import com.exam.softuni.repository.UserRepository;
import com.exam.softuni.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean findUserByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public boolean findUserByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        userRepository.save(modelMapper.map(userServiceModel, User.class));
    }

    @Override
    public boolean findUserByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password).isPresent();
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
