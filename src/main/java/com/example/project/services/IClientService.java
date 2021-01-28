package com.example.project.services;

import com.example.project.entity.Client;
import com.example.project.exception.ClientException;

import java.util.List;

public interface IClientService {
    Client saveClient(Client client) throws ClientException;

    void deleteClient(Long passportID);

    List<Client> findBySurname(String surname);

    List<Client> findByCity(String city);

    List<Client> findAll();
}
