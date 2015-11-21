package br.com.roberto.mediaStore.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value = "FILME")
public class Filme extends Produto{
	

	@Column
	private Integer duracao;
	
	@ManyToMany
	private Set<Categoria> categorias;
	
	@OneToMany (cascade = CascadeType.ALL, mappedBy="filme")
	private Set<FilmeAtor> atores;

	public Integer getDuracao() {
		return duracao;
	}

	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}
	
		
	public Set<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(Set<Categoria> categorias) {
		this.categorias = categorias;
	}

	@Override
	public String toString() {
		return "Filme [getId()=" + getId() + ", getDescricao()=" + getDescricao() + ", duracao=" + duracao
				+ ", categorias=" + categorias + "]";
	}
	
		
	

}
