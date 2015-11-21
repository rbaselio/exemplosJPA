package br.com.roberto.mediaStore.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value = "LIVRO")
public class Livro extends Produto {

	@Column
	private String ISBN;

	
	@ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Editora editora;

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	@Override
	public String toString() {
		return "Livro [getId()=" + getId() + ", getDescricao()=" + getDescricao() + ", getPreco()=" + getPreco()
				+ ", ISBN=" + ISBN + ", editora=" + editora + "]";
	}

}
