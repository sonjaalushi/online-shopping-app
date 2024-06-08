package com.sda.online_shopping_app.service;


import com.sda.online_shopping_app.entity.User;
import com.sda.online_shopping_app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;


    public User save(User user) {
        return userRepo.save(user);
    }


}