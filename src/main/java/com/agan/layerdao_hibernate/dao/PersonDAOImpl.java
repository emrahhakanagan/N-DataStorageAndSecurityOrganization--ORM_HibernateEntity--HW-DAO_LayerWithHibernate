package com.agan.layerdao_hibernate.dao;

import com.agan.layerdao_hibernate.entity.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PersonDAOImpl implements PersonDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Person> getPersonsByCity(String city) {
//        String query = "SELECT p FROM Person p WHERE p.cityOfLiving = :cityInJPQL";

//        String query = "FROM orm_hibernate_hw.person JOIN orm_hibernate_hw.orders" +
//                       "ON person.age = orders.customer_age" +
//                       "AND person.name = orders.customer_name" +
//                       "AND person.surname = orders.customer_surname" +
//                       "WHERE city_of_living = :city";


        String query = "SELECT o FROM Order o JOIN o.person p " +
                       "WHERE p.cityOfLiving = :cityInJPQL";


        List<Person> persons;

        try {
            persons = entityManager.createQuery(query, Person.class)
                    .setParameter("cityInJPQL", city)
                    .getResultList();
        } catch (RuntimeException e) {
            System.out.println("Error: " + e);
            return null;
        }

        return persons;
    }
}

