package com.example.project.services;

import com.example.project.entity.Client;
import com.example.project.exception.ClientException;
import com.example.project.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;


// служебный класс
// ClientService обратится к ClientRepository, после добавления обратится к ClientController  и ответит ему "добавил"
@Service // spring создаст объект этого сервиса и положит себе в контейнер
public class ClientService implements IClientService {

    private final ClientRepository repository;

    @Autowired // инъекция зависимостей - spring установит в зависимости все, что установленно в данном конструкторе
    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    // методами, перечисленными ниже будет пользоваться контроллер

    private void validateClient(Client client) throws ClientException {
        if (isNull(client)) {
            throw new ClientException("Object client is null");
        }
        if (isNull(client.getSurname()) || client.getSurname().isEmpty()) {
            throw new ClientException("Surname is empty");
        }
    }

    @Override
    public Client saveClient(Client client) throws ClientException {
        validateClient(client);
        Client savedClient = repository.save(client);
        return savedClient;
    }

    @Override
    public void deleteClient(Long passportID) {
        repository.deleteById(passportID);
    }

    @Override
    public List<Client> findBySurname(String surname) {
        List<Client> clients = repository.findBySurname(surname);
        if (clients == null || clients.isEmpty()) throw new ClientException("Запись не существует");
        return clients;
    }

    public Optional<Client> findById(Long passportId) {
        Optional<Client> client = repository.findById(passportId);
        if (client == null) throw new ClientException("Запись не существует");
        return client;
    }


    @Override
    public List<Client> findByCity(String city) {
        List<Client> clients = repository.findByCity(city);
        if (clients == null || clients.isEmpty()) throw new ClientException("Запись не существует");
        return clients;
    }

    @Override
    public List<Client> findAll() {
        return repository.findAll().stream().collect(Collectors.toList());
    }
}


