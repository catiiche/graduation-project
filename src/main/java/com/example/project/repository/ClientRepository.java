package com.example.project.repository;

import com.example.project.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


// Spring создает класс и прописывает реализацию всех методов из репозитория и прописанных вручную,
// потом создает объект

// CrudeRepository не подойдет т.к нет возможности получать записи частями
// JpaRepository расширяет PagingAndSortingRepository // чтобы можно было получать записи частями
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findBySurname(String surname);

    List<Client>findByCity(String city);

}
