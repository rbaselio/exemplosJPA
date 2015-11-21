package br.com.roberto.mediaStore.services.produto;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.roberto.mediaStore.entities.Editora;
import br.com.roberto.mediaStore.entities.Livro;
import br.com.roberto.mediaStore.services.BaseService;

public class LivroService extends BaseService<Long, Livro> {

	public LivroService() {
		this.entityClass = Livro.class;
	}

	public List<Livro> findByEditora(Editora editora) {
		StringBuilder builder = new StringBuilder("Select c FROM ");
		builder.append(Livro.class.getSimpleName());
		builder.append(" c ");
		builder.append(" WHERE c.editora = :editora");
		builder.append(" ORDER BY c.descricao");

		TypedQuery<Livro> query = getEm().createQuery(builder.toString(), Livro.class);
		query.setParameter("editora", editora);
		return query.getResultList();
		
	}

	public List<Livro> findByDescricao(String nome) {	
			StringBuffer jpql = new StringBuffer();
			jpql.append("FROM ");
			jpql.append(entityClass.getSimpleName());
			jpql.append(" en where en.descricao = :descricao");
			

			TypedQuery<Livro> query = getEm().createQuery(jpql.toString(), entityClass);
			query.setParameter("descricao", "%" + nome + "%");

			return query.getResultList();

		
	
	}

}
