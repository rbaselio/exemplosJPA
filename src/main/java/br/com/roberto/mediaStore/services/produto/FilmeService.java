package br.com.roberto.mediaStore.services.produto;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.roberto.mediaStore.entities.Filme;
import br.com.roberto.mediaStore.services.BaseService;

public class FilmeService extends BaseService<Long, Filme> {

	public FilmeService() {
		this.entityClass = Filme.class;
	}

	public List<Filme> findByDescricao(String nome) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("FROM ");
		jpql.append(entityClass.getSimpleName());
		jpql.append(" en where en.descricao like :descricao");

		TypedQuery<Filme> query = getEm().createQuery(jpql.toString(), entityClass);
		query.setParameter("descricao", "%" + nome + "%");

		return query.getResultList();

	}

}