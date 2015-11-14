package br.com.dextraining.mediaStore.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value =  "Album")
public class Album extends Produto{
	
	@Column
	private Integer faixas;

	public Integer getFaixas() {
		return faixas;
	}

	public void setFaixas(Integer faixas) {
		this.faixas = faixas;
	}

	@Override
	public String toString() {
		return "Album [getId()=" + getId() + ", getDescricao()=" + getDescricao() + ", faixas=" + faixas + "]";
	}
	
	
	
	

}
