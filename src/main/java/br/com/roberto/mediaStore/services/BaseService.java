package br.com.roberto.mediaStore.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.roberto.mediaStore.utils.EntityManagerUtil;

public class BaseService<T, E> {
	protected Class<E> entityClass;

	public E findById(T id) {
		return getEm().find(entityClass, id);
	}

	public List<E> findAll(Integer offset, Integer max) {
		String jpql = "FROM " + entityClass.getSimpleName() + " ORDER BY ID" ;
		TypedQuery<E> query = getEm().createQuery(jpql, entityClass);
		if (offset != null) query.setFirstResult(offset);
		if (max != null) query.setMaxResults(max);	
		return query.getResultList();
	}
	
	public E find(Integer offset, Integer max) {
		String jpql = "FROM " + entityClass.getSimpleName() + " ORDER BY ID";
		TypedQuery<E> query = getEm().createQuery(jpql, entityClass);
		if (offset != null) query.setFirstResult(offset);
		if (max != null) query.setMaxResults(max);
		E c;
		try{
			c = query.getSingleResult();
		}catch (NoResultException e){
			c = null;
		}		
		return c;
	}
	
	public Long countAll(){
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT COUNT (en) FROM ");
		jpql.append(entityClass.getSimpleName());
		jpql.append(" en ");
		
		TypedQuery<Long> query =  getEm().createQuery(jpql.toString(), Long.class);
		
		try{
			return query.getSingleResult();
		}catch (NoResultException e){
			return 0l;
		}		
	}

	public List<E> findByAtribute(String atributo, String dado) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("FROM ");
		jpql.append(entityClass.getSimpleName());
		jpql.append(" en");
		jpql.append(" where en.");
		jpql.append(atributo);
		jpql.append(" = :atributo order by ");
		jpql.append(atributo);

		TypedQuery<E> query = getEm().createQuery(jpql.toString(), entityClass);
		query.setParameter("atributo", dado);

		return query.getResultList();

	}

	public E persist(E entity) {
		try {
			getTransaction().begin();
			getEm().persist(entity);
			getTransaction().commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (getTransaction() != null && getTransaction().isActive()) {
				getTransaction().rollback();
			}
		}
		return entity;
	}

	public void remove(E entity) {
		try {
			getTransaction().begin();
			getEm().remove(entity);
			getTransaction().commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (getTransaction() != null && getTransaction().isActive()) {
				getTransaction().rollback();
			}
		}
	}

	public void removeById(T id) {
		StringBuffer jpql = new StringBuffer();
		jpql.append("DELETE FROM ");
		jpql.append(entityClass.getSimpleName());
		jpql.append(" en ");
		jpql.append(" where en.id = :id");

		Query query = getEm().createQuery(jpql.toString(), entityClass);
		query.setParameter("id", id);
		
		try {
			getTransaction().begin();
			query.executeUpdate();
			getTransaction().commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (getTransaction() != null && getTransaction().isActive()) {
				getTransaction().rollback();
			}
		}	
		

	}

	public E update(E entity) {
		try {
			getTransaction().begin();
			getEm().merge(entity);
			getTransaction().commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (getTransaction() != null && getTransaction().isActive()) {
				getTransaction().rollback();
			}
		}
		return entity;
	}

	public EntityManager getEm() {
		return EntityManagerUtil.criarEntityManager();
	}

	public EntityTransaction getTransaction() {
		return getEm().getTransaction();
	}

}
