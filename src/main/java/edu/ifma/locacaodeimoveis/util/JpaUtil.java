package edu.ifma.locacaodeimoveis.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class JpaUtil {
	
	private static EntityManagerFactory factory;
	private static EntityManagerFactory factoryTest;


	static {
		factory = Persistence.createEntityManagerFactory("locadoraPU");
	}

	static {
		factoryTest = Persistence.createEntityManagerFactory("locadoraPU_test");
	}
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

	public static EntityManager getEntityManagerTest() {
		return factory.createEntityManager();
	}


	public static void close() {
		factory.close();
	}

}
