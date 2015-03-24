package wagner.javaee6.dao;

import java.sql.Connection;

import javax.persistence.EntityManager;

import wagner.javaee6.domain.Contact;

public class ContactDao {

	private EntityManager entityManager;
	
	public void create(Contact contact) {
		entityManager.persist(contact);
	}
}
