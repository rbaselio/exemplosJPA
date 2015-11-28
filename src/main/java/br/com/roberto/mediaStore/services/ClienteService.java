package br.com.roberto.mediaStore.services;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.roberto.mediaStore.entities.Cliente;
import br.com.roberto.mediaStore.utils.Estado;

public class ClienteService extends BaseService<Long, Cliente> {

	public ClienteService() {
		this.entityClass = Cliente.class;
	}

	public List<Cliente> findByNome(String nome) {
		StringBuilder builder = new StringBuilder("FROM ");
		builder.append(Cliente.class.getSimpleName());
		builder.append(" c ");
		builder.append(" WHERE UPPER(c.nome) LIKE UPPER(:nome)");
		builder.append(" AND c.ativo = true");
		builder.append(" ORDER BY c.nome");

		TypedQuery<Cliente> query = getEm().createQuery(builder.toString(), Cliente.class);
		query.setParameter("nome", "%" + nome + "%");
		return query.getResultList();
	}
	
	public List<Cliente> findByEstado(Estado estado) {
		StringBuilder builder = new StringBuilder("Select c FROM ");
		builder.append(Cliente.class.getSimpleName());
		builder.append(" c ");
		builder.append(" WHERE c.endereco.estado = :estado");
		builder.append(" ORDER BY c.nome");

		TypedQuery<Cliente> query = getEm().createQuery(builder.toString(), Cliente.class);
		query.setParameter("estado", estado);
		return query.getResultList();
	}

}
