package com.example.project.services;

import com.example.project.entity.Client;
import com.example.project.exception.ClientException;
import com.example.project.repository.ClientRepository;
import com.example.project.thread.ClientCollections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

/**
 * class ClientService
 * <p>
 * makes a request to ClientRepository, execute method and send response to ClientsController.
 * Also contains 2 methods of knn algorithm that help to assess the solvency of a bank's client
 *
 * @author Kate Shkuratova
 * @version 1.0
 * @see ClientRepository
 */

@Service // spring создаст объект этого сервиса и положит себе в контейнер
public class ClientService implements IClientService {

    private final ClientRepository repository;

    private ClientCollections clientsList = new ClientCollections();
    private static final int K = 6;


    @Autowired // инъекция зависимостей - spring установит в зависимости все, что установленно в данном конструкторе
    public ClientService(ClientRepository repository) {
        this.repository = repository;
        clientsList.createClients();
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
        List<Client> additional = new ArrayList<>();
        for (int i = 0; i < clients.size(); i++) {

            Map<Integer, Double> mapD;
            mapD = euclidianDistance(clients.get(i), clientsList.getApprovedClients(), K);

            clients.get(i).setStatus(voting(mapD, clientsList.getApprovedClients()));
            additional.add(clients.get(i));
        }
        return clients;
    }

    public Optional<Client> findById(Long passportId) {
        Optional<Client> client = repository.findById(passportId);
        if (client == null) throw new ClientException("Запись не существует");
        Client additional;
        additional = client.orElse(new Client());

        Map<Integer, Double> mapD = new HashMap<>();
        mapD = euclidianDistance(additional, clientsList.getApprovedClients(), K);

        additional.setStatus(voting(mapD, clientsList.getApprovedClients()));
        client = Optional.ofNullable(additional);
        return client;
    }


    @Override
    public List<Client> findByCity(String city) {
        List<Client> clients = repository.findByCity(city);
        if (clients == null || clients.isEmpty()) throw new ClientException("Запись не существует");
        List<Client> additional = new ArrayList<>();
        for (int i = 0; i < clients.size(); i++) {

            Map<Integer, Double> mapD = new HashMap<>();
            mapD = euclidianDistance(clients.get(i), clientsList.getApprovedClients(), K);

            clients.get(i).setStatus(voting(mapD, clientsList.getApprovedClients()));
            additional.add(clients.get(i));
        }
        return clients;
    }

    @Override
    public List<Client> findAll() {
        return repository.findAll().stream().collect(Collectors.toList());
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


