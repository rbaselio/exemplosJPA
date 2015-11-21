package br.com.roberto.mediaStore.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class FilmeAtor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne
	private Ator ator;
	
	@ManyToOne
	private Filme filme;
	
	@Column
	private String Papel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Ator getAtor() {
		return ator;
	}

	public void setAtor(Ator ator) {
		this.ator = ator;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}
	
	

	public String getPapel() {
		return Papel;
	}

	public void setPapel(String papel) {
		Papel = papel;
	}

	@Override
	public String toString() {
		return "FilmeAtor [id=" + id + ", ator=" + ator + ", filme=" + filme + "]";
	}
	
	
	
	

}
