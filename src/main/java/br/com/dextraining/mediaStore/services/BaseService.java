package br.com.dextraining.mediaStore.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import br.com.dextraining.mediaStore.utils.EntityManagerUtil;

public class BaseService<T, C> {
	protected Class<C> entityClass;
	
	public C findById(T id) {
		EntityManager em = EntityManagerUtil.criarEntityManager();
		return em.find(entityClass, id);
	}
	
	public List<C> findAll() {
		EntityManager em = EntityManagerUtil.criarEntityManager();
		String jpql = "FROM " + entityClass.getSimpleName();
		TypedQuery<C> query = em.createQuery(jpql, entityClass);
		return query.getResultList();
	}
	
	public C persist(C entity) {
		EntityManager em = EntityManagerUtil.criarEntityManager();
		EntityTransaction transaction = null;
		
		try {
			transaction = em.getTransaction();
			transaction.begin();
			em.persist(entity);
			transaction.commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
			if(transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
		} finally {
			em.close();
		}
		return entity;
	}
}
