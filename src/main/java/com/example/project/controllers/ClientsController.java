package com.example.project.controllers;

import com.example.project.entity.Client;
import com.example.project.exception.ClientException;
import com.example.project.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * class ClientsController
 * responsible for navigation and processing requests
 * respond to a specific client request
 *
 * @author Kate Shkuratova
 * @version 1.0
 * @see ClientService
 */

@RestController

// URL адреса всех методов будут обязательно иметь в себе /clients в начале
@RequestMapping("/clients")
@CrossOrigin
public class ClientsController {
    @Autowired
    private final ClientService service;

    public ClientsController(ClientService service) {
        this.service = service;
    }


    // Внутри аннотации указан путь, за который отвечают методы.
    @PostMapping("/save")
    public Client saveClient(@RequestBody Client client) throws ClientException {
        Client saved;
        try {
            saved = service.saveClient(client);
        } catch (ClientException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
        return saved;
    }

    @GetMapping("/findAll")
    public List<Client> findAllClients() {
        List<Client> clients;
        try {
            clients = service.findAll();
        } catch (ClientException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return clients;
    }

    @GetMapping("/findById")
    public Optional<Client> findById(@RequestParam Long passportId) {
        Optional<Client> client;
        try {
            client = service.findById(passportId);
        } catch (ClientException e) {
            return null;
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return client;
    }

    @GetMapping("/findBySurname")
    public List<Client> findBySurname(@RequestParam String surname) {
        List<Client> clients;
        try {
            clients = service.findBySurname(surname);
        } catch (ClientException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return clients;
    }

    @GetMapping("/findByCity")
    public List<Client> findByCity(@RequestParam String city) {
        List<Client> clients;
        try {
            clients = service.findByCity(city);

        } catch (ClientException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return clients;
    }

    @DeleteMapping("/delete/{id}")
    //{} - сможем получить параметры. Если не отправить параметр - будет ошибка "Неправильная строка запроса"
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            service.deleteClient(id);
        } catch (ClientException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        return ResponseEntity.ok().build();
    }
}

