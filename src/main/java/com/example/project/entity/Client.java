package com.example.project.entity;


//import org.springframework.data.annotation.Id;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int passportId;

    @Column(length = 50, nullable = false)
    //@NaturalId //Hibernate будет поддерживать сопоставление натуральных идентификаторов с первичными ключами
    private String name;
    @Column(length = 50, nullable = false)
    private int age;
    @Column(length = 50, nullable = false)
    private String city;

    private int countOfChildren;
    private int salary;
    private boolean creditHistory;
    private boolean status;

//    public Client(int passportId) {
//        this.passportId = passportId;
//    }

    public Client() {
    }

    public Client(int passportId, String name, int age, String city, int countOfChildren, int salary, boolean creditHistory) {
        setPassportId(passportId);
        setName(name);
        setAge(age);
        setCity(city);
        setCountOfChildren(countOfChildren);
        setSalary(salary);
        setCreditHistory(creditHistory);
    }

    public int getPassportId() {
        return passportId;
    }

    public void setPassportId(int passportId) {
        if (passportId < 10)
            throw new IllegalArgumentException("passportId должен быть не меньше 10");
        this.passportId = passportId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.length() < 4)
            throw new IllegalArgumentException("name должен быть не меньше 4");
        this.name = name;
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
