package wagner.javaee6.factory;

import javax.inject.Singleton;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Singleton
public class EntityManagerFactoryFactory {
	
	private EntityManagerFactory emf;

	public EntityManagerFactoryFactory() {
		emf = Persistence.createEntityManagerFactory("testPersistenceUnit");
	}
	
	public EntityManagerFactory getEntityManagerFactory() {
	     return emf;
	 }
}
