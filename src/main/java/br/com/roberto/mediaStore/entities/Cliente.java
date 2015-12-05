package br.com.roberto.mediaStore.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@SuppressWarnings("unused")
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String nome;
	
	@Column
	private Date nascimento;
	
	@Column
	private boolean ativo = true;
	
	@OneToOne (cascade = CascadeType.ALL)
	private Endereco endereco;
	
	@Embedded
	private CPF cpf;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	

	
	private CPF getCpf() {
		return cpf;
	}

	private void setCpf(CPF cpf) {
		this.cpf = cpf;
	}
	
	private void setValorCpf(String cpf){
		if(this.cpf == null){
			this.cpf = new CPF();
		}
		this.cpf.setCpf(cpf);
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + "] - " + endereco.toString();
	}
	
}
