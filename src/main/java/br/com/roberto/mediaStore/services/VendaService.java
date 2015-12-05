package br.com.roberto.mediaStore.services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.roberto.mediaStore.entities.Cliente;
import br.com.roberto.mediaStore.entities.Venda;

public class VendaService extends BaseService<Long, Venda> {
	public VendaService() {
		this.entityClass = Venda.class;
	}

	public List<Venda> findAll(Integer offset, Integer max) {
		StringBuilder jpql = new StringBuilder();
		jpql.append("FROM ");
		jpql.append(Venda.class.getSimpleName());
		jpql.append(" e JOIN FETCH e.itemVendas");

		TypedQuery<Venda> query = getEm().createQuery(jpql.toString(), Venda.class);
		if (offset != null)
			query.setFirstResult(offset);
		if (max != null)
			query.setMaxResults(max);
		return query.getResultList();
	}

	/*
	 * public HashMap<Cliente, BigDecimal> totalCliente() { StringBuilder jpql =
	 * new StringBuilder(); jpql.append(
	 * "SELECT v.cliente, SUM(v.valortotal) FROM ");
	 * jpql.append(Venda.class.getSimpleName()); jpql.append(
	 * " GROUP BY v.cliente");
	 * 
	 * TypedQuery<BigDecimal> query = getEm().createQuery(jpql.toString(),
	 * HashMap<Cliente, BigDecimal>.class); query.setParameter("cliente",
	 * cliente);
	 * 
	 * return query.getSingleResult(); }
	 */

	@SuppressWarnings("unchecked")
	public HashMap<Cliente, BigDecimal> totalPorCliente() {
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT c, SUM(v.valortotal) FROM ");
		jpql.append(Venda.class.getSimpleName());
		jpql.append(" v JOIN v.cliente c ");
		jpql.append("GROUP BY c ");
		jpql.append("ORDER BY c.nome ");

		Query query = getEm().createQuery(jpql.toString());

		List<Object[]> list = query.getResultList();
		HashMap<Cliente, BigDecimal> clienteValor = new HashMap<Cliente, BigDecimal>();

		for (Object[] objects : list) {
			clienteValor.put((Cliente) objects[0], (BigDecimal) objects[1]);
		}

		return clienteValor;

	}

	public BigDecimal totalCliente(Cliente cliente) {
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT SUM(v.valortotal) FROM ");
		jpql.append(Venda.class.getSimpleName());
		jpql.append(" v where v.cliente = :cliente");

		TypedQuery<BigDecimal> query = getEm().createQuery(jpql.toString(), BigDecimal.class);
		query.setParameter("cliente", cliente);

		return query.getSingleResult();
	}

}
