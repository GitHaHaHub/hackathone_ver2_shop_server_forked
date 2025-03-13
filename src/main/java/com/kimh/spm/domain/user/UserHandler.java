package com.kimh.spm.domain.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kimh.spm.security.jwt.UserInfoDetails;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserHandler implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder encoder;

    public UserHandler() {
        this.encoder = new BCryptPasswordEncoder();
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already in use");
        }
        else
            return userRepository.save(user);
    }

    public Optional<User> searchUser(String id, String password) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        }
        else
            throw new IllegalArgumentException("Fail");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userDetail = userRepository.findByName(username); // Assuming 'email' is used as username

        // Converting user to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    public String addUser(User user) {
        // Encode password before saving the user
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User Added Successfully";
    }

}