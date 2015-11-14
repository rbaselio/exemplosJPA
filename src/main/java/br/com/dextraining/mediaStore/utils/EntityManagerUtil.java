package br.com.dextraining.mediaStore.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
private static EntityManagerFactory emf;
	
	public static void criarConexao() {
		emf = Persistence.createEntityManagerFactory("MediaStorePu");
	}
	
	public static EntityManager criarEntityManager() {
		return emf.createEntityManager();
	}
	
	public static void fechaConexao() {
		emf.close();
	}
}
