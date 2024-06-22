package com.sda.online_shopping_app.service;

import com.sda.online_shopping_app.entity.User;
import com.sda.online_shopping_app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;


    public User save(User user) {
        return userRepository.save(user);

    }

    public Optional<User> getById(Integer id) {
        return userRepository.findById(id);
    }

    public List<User> getAll() {

        List<User> u = userRepository.findAll();

        return u;
    }

    public void delete(Integer id){
        userRepository.deleteById(id);
    }


    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        if (userRepository.getByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> loginUser(String email, String password) {
        Optional<User> userOptional = userRepository.getByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }


}