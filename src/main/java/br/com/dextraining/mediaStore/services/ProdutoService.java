package br.com.dextraining.mediaStore.services;

import java.util.List;

import javax.persistence.TypedQuery;

public class ProdutoService<C> extends BaseService<Long, C> {

	public List<C> findByDescricao(String descricao) {
		StringBuilder builder = new StringBuilder("FROM ");
		builder.append(entityClass.getSimpleName());
		builder.append(" c ");
		builder.append(" WHERE UPPER(c.descricao) LIKE UPPER(:descricao)");
		builder.append(" ORDER BY c.descricao");

		TypedQuery<C> query = getEm().createQuery(builder.toString(), entityClass);
		query.setParameter("descricao", "%" + descricao + "%");
		return query.getResultList();
	}

}