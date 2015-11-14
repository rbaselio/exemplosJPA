package br.com.dextraining.mediaStore.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value =  "Filme")
public class Filme extends Produto{
	

	@Column
	private Integer duracao;

	public Integer getDuracao() {
		return duracao;
	}

	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}
	
	@Override
	public String toString() {
		return "Filme [getId()=" + getId() + ", getDescricao()=" + getDescricao() + ", duracao=" + duracao + "]";
	}
	
	

}
