package wagner.javaee6;

import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import wagner.javaee6.factory.EntityManagerFactoryFactory;

public class Main {
	public static void main(String[] args) throws SQLException {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("testPersistenceUnit");
		
		factory.close();
	}
}
