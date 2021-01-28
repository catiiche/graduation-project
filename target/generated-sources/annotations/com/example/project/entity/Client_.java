package com.example.project.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Client.class)
public abstract class Client_ {

	public static volatile SingularAttribute<Client, String> city;
	public static volatile SingularAttribute<Client, String> surname;
	public static volatile SingularAttribute<Client, String> name;
	public static volatile SingularAttribute<Client, Integer> countOfChildren;
	public static volatile SingularAttribute<Client, Integer> salary;
	public static volatile SingularAttribute<Client, Long> passportId;
	public static volatile SingularAttribute<Client, Integer> age;
	public static volatile SingularAttribute<Client, Boolean> creditHistory;
	public static volatile SingularAttribute<Client, Boolean> status;

	public static final String CITY = "city";
	public static final String SURNAME = "surname";
	public static final String NAME = "name";
	public static final String COUNT_OF_CHILDREN = "countOfChildren";
	public static final String SALARY = "salary";
	public static final String PASSPORT_ID = "passportId";
	public static final String AGE = "age";
	public static final String CREDIT_HISTORY = "creditHistory";
	public static final String STATUS = "status";

}

