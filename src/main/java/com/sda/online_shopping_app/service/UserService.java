package com.sda.online_shopping_app.service;

import com.sda.online_shopping_app.entity.UserEntity;
import com.sda.online_shopping_app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private  UserRepo userRepository;


    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    public Optional<UserEntity> getById(Integer id) {
        return userRepository.findById(id);
    }

    public Optional<UserEntity> getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    public void delete(Integer id){
        userRepository.deleteById(id);
    }

    public UserEntity registerUser(UserEntity user) {
        if (userRepository.getByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<UserEntity> loginUser(String email, String password) {
        Optional<UserEntity> userOptional = userRepository.getByEmail(email);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

}