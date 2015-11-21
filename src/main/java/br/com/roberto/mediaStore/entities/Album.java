package br.com.roberto.mediaStore.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value = "ALBUM")
public class Album extends Produto{
	
	@Column
	private Integer faixas;
	
	@JoinColumn(name = "album_id")
	@OneToMany (cascade = CascadeType.ALL)
	private Set<Musica> musicas;

	public Integer getFaixas() {
		return musicas.size();
	}

	public void setFaixas(Integer faixas) {
		this.faixas = faixas;
	}		

	public Set<Musica> getMusicas() {
		return musicas;
	}

	public void setMusicas(Set<Musica> musicas) {
		this.musicas = musicas;
		setFaixas(musicas.size());
	}

		
	@Override
	public String toString() {
		return "Album [getId()=" + getId() + ", getDescricao()=" + getDescricao() + ", faixas=" + faixas + ", musicas="
				+ musicas + "]";
	}
	
	
	
	

}
