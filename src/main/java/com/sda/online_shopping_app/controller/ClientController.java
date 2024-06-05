package com.sda.online_shopping_app.controller;

import com.sda.online_shopping_app.entity.Client;
import com.sda.online_shopping_app.exceptions.ClientNotFoundException;
import com.sda.online_shopping_app.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;



    @PostMapping("/addClient")
    public Client save(@RequestBody Client client){
        return clientService.save(client);
    }

    @GetMapping("/findClient/{id}")
    public ResponseEntity<Optional<Client>> findClient(@RequestParam Integer id){
       Optional<Client> getClient=clientService.findById(id);
       return new ResponseEntity<>(getClient,HttpStatus.OK);
    }

}
