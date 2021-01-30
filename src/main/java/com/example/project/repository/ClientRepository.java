package com.example.project.repository;

import com.example.project.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * interface ClientRepository
 * Spring creates a class and describes all methods from this repository
 *
 * @author Kate Shkuratova
 * @version 1.0
 */


// JpaRepository расширяет PagingAndSortingRepository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findBySurname(String surname);

    List<Client> findByCity(String city);
}
