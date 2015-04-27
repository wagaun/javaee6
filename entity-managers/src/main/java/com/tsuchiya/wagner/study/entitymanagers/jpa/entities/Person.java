package com.tsuchiya.wagner.study.entitymanagers.jpa.entities;

import javax.persistence.*;

/**
 * Created by wagner on 4/27/15.
 */
@Entity
public class Person {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    private PersonType type;

    public Person() {

    }

    public Person(String name, PersonType type) {
        this.name = name;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PersonType getType() {
        return type;
    }

    public void setType(PersonType type) {
        this.type = type;
    }
}
