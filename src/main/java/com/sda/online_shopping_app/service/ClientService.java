package com.sda.online_shopping_app.service;

import com.sda.online_shopping_app.entity.Client;
import com.sda.online_shopping_app.exceptions.ClientNotFoundException;
import com.sda.online_shopping_app.repo.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepo clientRepo;



    public Client save(Client client){
        return clientRepo.save(client);
    }

    public Optional<Client> findById(Integer id){
        return Optional.of(clientRepo.findById(id)).orElseThrow(()
                -> new ClientNotFoundException("Client not found with id : "+id));
    }
}