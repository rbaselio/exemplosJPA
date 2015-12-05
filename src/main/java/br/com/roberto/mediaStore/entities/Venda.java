package br.com.roberto.mediaStore.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Venda {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private BigDecimal valortotal = new BigDecimal(0);
	
	@Column
	private Date data;
	
	@ManyToOne
	private Cliente cliente;
	
	@OneToMany (cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "venda_id")
	
	private Set<ItemVenda> itemVendas;
	
	public Venda(){
		itemVendas = new HashSet<ItemVenda>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValortotal() {
		return valortotal;
	}

	public void setValortotal(BigDecimal valortotal) {
		this.valortotal = valortotal;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<ItemVenda> getItemVendas() {
		return itemVendas;
	}

	public void addItemVendas(ItemVenda itens) {
		itemVendas.add(itens);
		for (ItemVenda itemVenda : itemVendas) {
			setValortotal(getValortotal().add(itemVenda.getProduto().getPreco().multiply(itemVenda.getQuantidade())));
		}
	}
	
	public void removeItemVendas(ItemVenda itens) {
		itemVendas.remove(itens);
		for (ItemVenda itemVenda : itemVendas) {
			setValortotal(getValortotal().add(itemVenda.getProduto().getPreco().multiply(itemVenda.getQuantidade())));
		}
	}

	@Override
	public String toString() {
		return "Venda [id=" + id + ", valortotal=" + valortotal + ", data=" + data + ", cliente=" + cliente
				+ ", itemVendas=" + itemVendas + ", getCliente()=" + getCliente() + ", getItemVendas()="
				+ getItemVendas() + "]";
	}
	
	
	
	
	
	
	
	

}
