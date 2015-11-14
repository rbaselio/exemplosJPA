package br.com.dextraining.mediaStore.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


import br.com.dextraining.mediaStore.utils.EntityManagerUtil;

public class ProdutoService<C> extends BaseService<Long, C>{
	
	protected Class<C> entityClass;
	
	
	public List<C> findByDescricao(String descricao) {
		EntityManager em = EntityManagerUtil.criarEntityManager();
		StringBuilder builder = new StringBuilder("FROM ");
		builder.append(entityClass.getSimpleName());
		builder.append(" c ");
		builder.append(" WHERE UPPER(c.descricao) LIKE UPPER(:descricao)");
		builder.append(" ORDER BY c.descricao");
		
		TypedQuery<C> query = em.createQuery(builder.toString(), entityClass);
		query.setParameter("descricao", "%"+descricao+"%");
		return query.getResultList();
	}
	
	
	
	

}
