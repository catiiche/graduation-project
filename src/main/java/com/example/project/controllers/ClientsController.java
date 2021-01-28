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
/*
http://localhost:8080/clients/save
{
    "passportId": 1000000001,
    "name": "Kate",
    "surname": "Shkuratova",
    "age": 27,
    "city": "Smolensk",
    "countOfChildren": 0,
    "salary": 45000,
    "creditHistory": "true"
}


 */
//класс получает информацию из запроса и отдает ответ
//возьмет клиента и отдаст его в ClientService

// класс, который будет реагировать на конкретый запрос клиента
// наш ClientController будет обрабатывать все запросы, которые связаны с клиентом
//@RestController // обрабатывает запрос и возвращает json строчку в ответ, собирает ее сам
// экземпляр данного класса будет создан и добавлен в контейнер spring

// этот класс отвечает за навигацию и обработку запросов

//т.е. класс обрабатывает запрос от пользователя
// обменивается данными с моделью
// показывает пользователю правильное представление
// переадресовывает пользователя на другие страницы
// В контроллере не должно содержаться никакой логики по работе с сущностями. Вся логика сосредоточена в сервисном слое.
// Поэтому, в контроллере мы просто вызываем методы из сервиса и перебрасываем ответы на клиент


// все запросы идущие на clients будут обрабатываться данным контроллером //http://localhost/clients
@RestController //  Controller  для html страничек
@RequestMapping("/clients") // URL адреса всех методов будут обязательно иметь в себе /clients в начале
@CrossOrigin
public class ClientsController {
    @Autowired
    private final ClientService service;

    public ClientsController(ClientService service) {
        this.service = service;
    }

    // Внутри аннотации указан путь за который отвечают эти методы.
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


    // метод обрабатывает запрос get
    // @GetMapping - связывают метод контроллера с адресом, по которому можно к этому методу обратиться
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
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

