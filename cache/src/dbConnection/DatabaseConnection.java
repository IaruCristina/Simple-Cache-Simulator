package dbConnection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Cache;

public class DatabaseConnection {

	public EntityManagerFactory entityManagerFactory;
	public EntityManager entityManager;
	

	/**
	 * @throws Exception Initializations
	 */
	public void setUp() throws Exception {
		entityManagerFactory = Persistence.createEntityManagerFactory("cache");
		entityManager = entityManagerFactory.createEntityManager();
	}
	/**
	 * Starts the entity
	 */
	public void startTransaction() {
		entityManager.getTransaction().begin();
	}

	/**
	 * Commits the changes
	 */
	public void commitTransaction() {
		entityManager.getTransaction().commit();
	}

	/**
	 * Closes the entity
	 */
	public void closeEntityManager() {
		entityManager.close();
	}
	
	public void saveCache(Cache cache) {
		entityManager.persist(cache);
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
}
