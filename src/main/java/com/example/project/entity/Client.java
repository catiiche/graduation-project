package com.example.project.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

// Модель ?
// хранит в себе данные
// Взаимодействует с БД для получения данных
// Отдает данные контроллеру

@Entity // для сопоставления этого класса с таблицей client
public class Client {
    @Id
    protected Long passportId;

    @Column(length = 50, nullable = false)

    // аннотации для валидации форм
    @NotEmpty(message = "name must not be empty")
    @Size(min = 4, max = 30, message = "name must be between 4 and 30 characters")
    private String name;

    @Column(length = 50, nullable = false)
    private String surname;

    @Column(length = 3, nullable = false)
    @Min(value = 18, message = "age must not be less than 18")
    private int age;

    @Column(length = 50, nullable = false)
    private String city;

    @Column
    private int countOfChildren;
    @Column
    private int salary;
    @Column
    private boolean creditHistory;
    private boolean status;

    public Client() {
    }

    public Client(Long passportId, String name, String surname, int age, String city, int countOfChildren, int salary, boolean creditHistory) {
        setPassportId(passportId);
        setName(name);
        setSurname(surname);
        setAge(age);
        setCity(city);
        setCountOfChildren(countOfChildren);
        setSalary(salary);
        setCreditHistory(creditHistory);
    }

    public Long getPassportId() {
        return passportId;
    }

    public void setPassportId(Long passportId) {
        if (passportId < 1000000000)
            throw new IllegalArgumentException("passportId должен быть не меньше 1000 000 000");
        this.passportId = passportId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.length() < 3)
            throw new IllegalArgumentException("name должен быть не меньше 3");
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if (surname == null || surname.length() < 4)
            throw new IllegalArgumentException("surname должен быть не меньше 4");
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 18)
            throw new IllegalArgumentException("age должен быть не меньше 18");
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if (city == null || city.length() < 3)
            throw new IllegalArgumentException("city должен быть не меньше 3");
        this.city = city;
    }

    public int getCountOfChildren() {
        return countOfChildren;
    }

    public void setCountOfChildren(int countOfChildren) {
        if (countOfChildren < 0)
            throw new IllegalArgumentException("countOfChildren должен быть не меньше 0");
        this.countOfChildren = countOfChildren;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        if (salary < 12000)
            throw new IllegalArgumentException("salary должен быть не меньше 12 000");
        this.salary = salary;
    }

    public boolean getCreditHistory() {
        return creditHistory;
    }

    public void setCreditHistory(boolean creditHistory) {
        this.creditHistory = creditHistory;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
