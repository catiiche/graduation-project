package com.example.project.repository;

import com.example.project.entity.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository; // чтобы можно было получать записи частями
import org.springframework.data.repository.query.Param;

import java.util.List;

// Spring создает класс и прописывает реализацию всех методов из репозитория и прописанных вручную,
// потом создает объект

// для реализации функциаонала получения / добавления / удаления клиента в базе
// работает с сущностями Объекты <Client, Integer> - Integer - уникальный идентификатор
// CrudeRepository не подойдет т.к нет возможности получать записи частями
public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {
    @Query(value = "SELECT c FROM Client c WHERE UPPER(c.name) LIKE %:clientName%")
        // в jpquery используются название сущностей(не столбцов/полей таблицы)
        // : - обозначает, что дальше будет имя параметра
        // c.name - поле в Client;  :clientName -  имя параметра, вместо него будет подставлено конкретное значение
        // LIKE = contains
        // UPPER - функция postgress приведение к верхнему регистру
        // % - может начинаться с чего угодно и чем угодно заканчиваться
    List<Client> findLikeName(@Param("clientName") String clientName);
    // вместо Iterable можно сделать List
    // в метод передаем строчку и эта строчка должна подставиться вместо clientName
    // @Param("clientName") String clientName - связываем аргумент(конкретное значение в запросе) с параметром clientName

    @Query(value = "SELECT c FROM Client c WHERE UPPER(c.name) = :clientName")
        // если нужно получить единичный объект указываем тип сущности
    Client findByName(@Param("clientName") String clientName); // Optional<Course>
}
