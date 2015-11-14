package br.com.dextraining.mediaStore.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
	private static EntityManagerFactory emf;
	private static EntityManager em;

	public static void criarConexao() {
		if (emf == null || !emf.isOpen()) {
			emf = Persistence.createEntityManagerFactory("MediaStorePu");
		}
	}

	public static EntityManager criarEntityManager() {
		if (em == null || !em.isOpen()) {
			em = emf.createEntityManager();
		}
		return em;
	}

	public static void fechaConexao() {
		if (em != null && em.isOpen()) {
			em.close();
		}
		emf.close();
	}
}
