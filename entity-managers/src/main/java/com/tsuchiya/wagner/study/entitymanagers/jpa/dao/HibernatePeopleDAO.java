package com.tsuchiya.wagner.study.entitymanagers.jpa.dao;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 * Created by wagner on 4/27/15.
 */
@Stateful
public class HibernatePeopleDAO extends PeopleDAO {

    @PersistenceContext(unitName = "hibernate-example-unit", type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
