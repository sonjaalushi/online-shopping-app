package com.sda.online_shopping_app.repo;

import com.sda.online_shopping_app.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepo extends JpaRepository<Client, Integer> {

}
