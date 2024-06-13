package com.sda.online_shopping_app.repo;

import com.sda.online_shopping_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    Optional<User> getByEmail(String email);

//    Optional<User>getById(Integer id);

}
