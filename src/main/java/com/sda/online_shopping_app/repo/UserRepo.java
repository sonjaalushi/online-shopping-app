package com.sda.online_shopping_app.repo;

import com.sda.online_shopping_app.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<UserEntity,Integer> {
    Optional<UserEntity> getByEmail(String email);


    Optional<UserEntity>getByName(String name);

//    Optional<User>getById(Integer id);

}
