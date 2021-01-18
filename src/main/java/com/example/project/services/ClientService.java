package com.example.project.services;

import com.example.project.entity.Client;
import com.example.project.exception.ClientException;
import com.example.project.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

// служебный класс
// ClientService обратится к ClientRepository, после добавления обратится к ClientController  и ответит ему "добавил"
@Service // spring создаст объект этого сервиса и положит себе в контейнер
public class ClientService {
    private ClientRepository repository;
    public ClientRepository getRepository() {
        return repository;
    }

    @Autowired // инъекция зависимостей - spring установит в зависимости все, что установленно в данном конструкторе
    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    // методами, перечисленными ниже будет пользоваться контроллер
    // ВАЛИДАЦИЯ

    // если запись не найдена  - вернется null
    public Client add(Client client) {
        if (repository.existsById(client.getPassportId()) ||
                repository.findByName(client.getName().toUpperCase()) != null) {
            throw new ClientException("Запись уже существует");
        }
        return repository.save(client);
    }

    // обновление записи
    public Client update(Client client) {
        if (!repository.existsById(client.getPassportId())) {
            throw new ClientException("Запись не существует");
        }
        return repository.save(client);
    }

    public Page<Client> getByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size); // объект выборки, внутри себя хранит страницу и размер
        Page<Client> clientPage = repository.findAll(pageable);
        if (clientPage.getContent().isEmpty()) {
            throw new ClientException("Записи не были найдены");
        }
        return clientPage;
    }

    public Optional<Client> getById(int id) {
        Optional<Client> optionalClient = repository.findById(id);
        if (optionalClient.isEmpty()) throw new ClientException("Запись не существует");
        return optionalClient;
    }

    public void delete(int id) {
        if (!repository.existsById(id)) throw new ClientException("Запись не существует");
        repository.deleteById(id);
    }
}
