package com.kimh.spm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kimh.spm.model.User;
import com.kimh.spm.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserHandler {
    private final UserRepository userRepository;

    public UserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }
}