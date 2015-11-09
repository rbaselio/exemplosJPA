package br.com.aulaJPA.lojaVirtual.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String endereco;
	private Date nasc;
	
	@Column (nullable=false)
	private Boolean ativo = true;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Date getNasc() {
		return nasc;
	}

	public void setNasc(Date nasc) {
		this.nasc = nasc;
	}

	public Boolean isAtivo(){
		return ativo;
	}

	@Override
	public String toString() {
		
		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
				
		return "Cliente [ id=" + id + ", nome=" + nome + ", endereco="
				+ endereco + ", nasc=" + dt.format(nasc) + " ]";
	}

	
}
