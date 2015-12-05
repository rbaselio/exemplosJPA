package br.com.roberto.mediaStore.entities;

import javax.persistence.Embeddable;

@Embeddable
public class CPF implements Comparable<CPF> {

	private String cpf;

	private String rg;

	/**
	 * 
	 * @param numero
	 * @throws CPFInvalidoException
	 *             Quando o numero do cpf é inválido
	 */
	public CPF(String numero) {
		validar(numero);
		this.cpf = numero;
	}

	public CPF() {

	}

	private void validar(String numero) {
		if (numero == null || numero.equals("") || numero.equals("0")) {

		}
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CPF other = (CPF) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}

	public int compareTo(CPF other) {
		return this.cpf.compareTo(other.cpf);
	}

}
