package br.com.aulaJPA.lojaVirtual.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import br.com.aulaJPA.lojaVirtual.utils.EntityManagerUtil;



public class BaseService<T, C> {
	
	protected Class<C> entityClass;
	
	public C findbyId(T id){
		EntityManager em = EntityManagerUtil.criarEntityManager();
		return em.find(entityClass, id);
	}
	
	
	public C persist (C entity){
		EntityManager em = EntityManagerUtil.criarEntityManager();
		EntityTransaction transaction = null;
		
		try {
			transaction = em.getTransaction();
			transaction.begin();
			em.persist(entity);
			transaction.commit();
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			if(transaction !=null && transaction.isActive()){
				transaction.rollback();
			}
		} finally{
			em.close();
		}
		return entity;
		
	}
	
	public List<C> findAll(){
		EntityManager em = EntityManagerUtil.criarEntityManager();
		
		StringBuffer jpql = new StringBuffer();
		jpql.append("FROM ");
		jpql.append(entityClass.getSimpleName());
		jpql.append(" c");
		
		TypedQuery<C> query  = em.createQuery(jpql.toString(), entityClass);
		return query.getResultList();
		
	}
	
	public List<C> findByAtribute(String atributo, String dado){
		EntityManager em = EntityManagerUtil.criarEntityManager();
		
		StringBuffer jpql = new StringBuffer();
		jpql.append("FROM ");
		jpql.append(entityClass.getSimpleName());
		jpql.append(" c");
		jpql.append(" where c." );
		jpql.append(atributo);
		jpql.append(" = :atributo order by ");
		jpql.append(atributo);
							
		TypedQuery<C> query  = em.createQuery(jpql.toString(), entityClass);
		query.setParameter("atributo", dado);
						
		return query.getResultList();		
		
	}
	

}
