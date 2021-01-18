package com.example.project.controllers;


import com.example.project.entity.Client;
import com.example.project.exception.ClientException;
import com.example.project.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
/*
{
"passportId": 11,
    "name": "java kjhkg",
    "age": 27,
    "city": "Smolensk",
    "countOfChildren": 2,
    "salary": 45,
    "creditHistory": "true"
 }

 */
//класс получает информацию из запроса и отдает ответ
//возьмет клиента и отдаст его в ClientService

// класс, который будет реагировать на конкретый запрос клиента
// наш ClientController будет обрабатывать все запросы, которые связаны с клиентом
@RestController // обрабатывает запрос и возвращает json строчку в ответ, собирает ее сам
// экземпляр данного класса будет создан и добавлен в контейнер spring
@RequestMapping("/clients") // все запросы идущие на clients будут обрабатываться данным контроллером //http://localhost/clients
public class ClientController {
    @Autowired
    private ClientService service;

    // метод обрабатывает запрос get
    @GetMapping("/{id}") // метод среагирует на GET запрос, у которого в строке запроса еще передается id
    //@PathVariable  чтобы получить значение из строки запроса
    // если в json строчке должен быть 1 объект, то метод должен возвращать Optional
    public Client getPassportId(@PathVariable int id) {
        Optional<Client> optionalClient;
        try {
            optionalClient = service.getById(id);
        } catch (ClientException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return optionalClient.get();
    }


    // клиентская сторона будет получать по 10 записей
    // page = 1 - это первый десяток записей
    // @RequestParam - используется если передача данных идет в строке через знак вопроса
    // @RequestBody  данные передаются в теле сообщения
    @GetMapping // http://localhost/clients?page=5&size=10
    // Page - информация сколько еще осталось записей
    public Page<Client> getAll(@RequestParam int page, @RequestParam int size) {
        Page<Client> clientPage;
        try {
            clientPage = service.getByPage(page, size);
        } catch (ClientException e) {
            // выбрасываем новый эксепшн, чтобы отправить клиенту информацию, что его запрос был неправильно составлен
            // либо информация не найдена и т.д. Удобно передавать статус ответа
            // ошибка/сообщение прилетит клиенту
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return clientPage;
    }

    //    @GetMapping
    // public Iterable<Course> getAll(){ }
    // controller -> service -> repository
    @PostMapping // будет реагировать на POST запрос
    // если информация внутри тела сообщения, оттуда ее достаем через @RequestBody
    // Spring используя библиотеку вытащит информацию из тела сообщения и преобразует ее в объект
    public Client addClient(@RequestBody Client client) {
        Client saved;
        try {
            saved = service.add(client);
        } catch (ClientException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
        return saved;
    }

    @PutMapping
    public Client updateClient(@RequestBody Client client) {
        Client updated;
        try {
            updated = service.update(client);
        } catch (ClientException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        return updated;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        try {
            service.delete(id);
        } catch (ClientException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
