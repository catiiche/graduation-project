package com.example.project.controllers;


import com.example.project.entity.Client;
import com.example.project.exception.ClientException;
import com.example.project.services.ClientService;
import com.example.project.thread.ClientCollections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static java.util.Map.Entry.comparingByValue;
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
    private ClientCollections clientsList = new ClientCollections();
    private static final int K = 6;

    public ClientsController(ClientService service) {
        this.service = service;
        clientsList.createClients();
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
        Client additional;
        try {
            client = service.findById(passportId);
            additional = client.orElse(new Client());

            Map<Integer, Double> mapD = new HashMap<>();
            mapD = euclidianDistance(additional, clientsList.getApprovedClients(), K);

            additional.setStatus(voting(mapD, clientsList.getApprovedClients()));
            client = Optional.ofNullable(additional);
        } catch (ClientException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return client;
    }

    @GetMapping("/findBySurname")
    public List<Client> findBySurname(@RequestParam String surname) {
        List<Client> clients;
        List<Client> additional = new ArrayList<>();
        try {
            clients = service.findBySurname(surname);
            for (int i = 0; i < clients.size(); i++) {

                Map<Integer, Double> mapD = new HashMap<>();
                mapD = euclidianDistance(clients.get(i), clientsList.getApprovedClients(), K);

                clients.get(i).setStatus(voting(mapD, clientsList.getApprovedClients()));
                additional.add(clients.get(i));
            }
        } catch (ClientException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return clients;
    }

    @GetMapping("/findByCity")
    public List<Client> findByCity(@RequestParam String city) {
        List<Client> clients;
        List<Client> additional = new ArrayList<>();
        try {
            clients = service.findByCity(city);
            for (int i = 0; i < clients.size(); i++) {

                Map<Integer, Double> mapD = new HashMap<>();
                mapD = euclidianDistance(clients.get(i), clientsList.getApprovedClients(), K);

                clients.get(i).setStatus(voting(mapD, clientsList.getApprovedClients()));
                additional.add(clients.get(i));
            }
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

    // расстояние Чебышева или Евклидово
    public Map<Integer, Double> euclidianDistance(Client client, List<Client> approvedClients, int k) {
        Map<Integer, Double> mapD = new HashMap<>();
        Map<Integer, Double> mapDk = new HashMap<>();

        Double d;
        int creditNonApproved = client.getCreditHistory() ? 20000 : 0;

        int cityNonApproved = client.getCity().equals("Moscow") ? 5000 : 0;

        for (int i = 0; i < approvedClients.size(); i++) {
            int creditApproved = approvedClients.get(i).getCreditHistory() ? 20000 : 0;
            int cityApproved = approvedClients.get(i).getCity().equals("Moscow") ? 5000 : 0;


            d = Math.pow(client.getSalary() - approvedClients.get(i).getSalary(), 2)
                    + Math.pow(2500 * (client.getAge() - approvedClients.get(i).getAge()), 2)
                    + Math.pow(25000 * (client.getCountOfChildren() - approvedClients.get(i).getCountOfChildren()), 2)
                    + Math.pow(creditNonApproved - creditApproved, 2)
                    + Math.pow(cityNonApproved - cityApproved, 2);

            mapD.put(i, Math.sqrt(d));
        }

        Map<Integer, Double> mapDSorted = mapD.entrySet().stream()
                .sorted(Map.Entry.<Integer, Double>comparingByValue())
                .collect(LinkedHashMap::new,
                        (m, c) -> m.put(c.getKey(), c.getValue()),
                        LinkedHashMap::putAll);
        for (Map.Entry<Integer, Double> entry : mapDSorted.entrySet()) {
            if (k == 0) break;
            mapDk.put(entry.getKey(), entry.getValue());
            k--;
        }
        for (Map.Entry<Integer, Double> entry : mapDk.entrySet()) {
            System.out.println(entry.getValue() + " " + entry.getKey());
        }
        return mapDk;
    }

    //
    public boolean voting(Map<Integer, Double> mapDk, List<Client> approvedClients) {
        int cnt1 = 0;
        int cnt2 = 0;

        for (int i = 0; i < approvedClients.size(); i++) {
            if (mapDk.containsKey(i)) {
                if (approvedClients.get(i).getStatus() == true)
                    cnt1++;
                else cnt2++;
            }
        }
        //System.out.println("cnt1 " + cnt1 + " cnt2 " + cnt2);
        return cnt1 > cnt2 ? true : false;
    }
}

