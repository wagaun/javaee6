package com.tsuchiya.wagner.study.entitymanagers.jpa.dao;

import com.tsuchiya.wagner.study.entitymanagers.jpa.entities.Person;
import com.tsuchiya.wagner.study.entitymanagers.jpa.entities.PersonType;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by wagner on 4/27/15.
 */
public abstract class PeopleDAO {

    public void savePerson(Person person) {
        getEntityManager().persist(person);
    }

    public void deletePerson(Person person) {
        getEntityManager().remove(person);
    }

    public List<Person> getPeople()  {
        Query query = getEntityManager().createQuery("SELECT p from Person as p");
        return query.getResultList();
    }

    public List<Person> getByType(PersonType personType) {
        Query query = getEntityManager().createQuery("SELECT p from Person as p where p.type = :type");
        query.setParameter("type", personType);
        return query.getResultList();
    }

    protected abstract EntityManager getEntityManager();
}
