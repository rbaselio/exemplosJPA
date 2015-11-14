package br.com.dextraining.mediaStore.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity

public class Livro extends Produto{
	
	@Column
	private String ISBN;

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}

	@Override
	public String toString() {
		return "Livro [getId()=" + getId() + ", getDescricao()=" + getDescricao() + ", ISBN=" + ISBN + "]";
	}
	
		
	
	

}
