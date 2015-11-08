package br.com.aulaJPA.lojaVirtual.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.aulaJPA.lojaVirtual.entities.Cliente;
import br.com.aulaJPA.lojaVirtual.utils.EntityManagerUtil;

public class ClienteService extends BaseService<Long, Cliente> {
	public ClienteService() {
		this.entityClass = Cliente.class;
	}
	
	public List<Cliente> findName(String name, Boolean ativo){
		EntityManager em = EntityManagerUtil.criarEntityManager();
		
		StringBuffer jpql = new StringBuffer();
		jpql.append("FROM ");
		jpql.append(Cliente.class.getSimpleName());
		jpql.append(" c");
		jpql.append(" where c.nome like :nome ");
		
		if (ativo){ jpql.append(" and c.ativo = :ativo ");}
		
		jpql.append("order by c.nome");
		
		TypedQuery<Cliente> query  = em.createQuery(jpql.toString(), Cliente.class);
		
		if (ativo){ query.setParameter("ativo", ativo);}
		
		query.setParameter("nome", "%" + name + "%");
		return query.getResultList();		
		
	}

}
