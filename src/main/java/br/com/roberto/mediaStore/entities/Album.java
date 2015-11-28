package br.com.roberto.mediaStore.entities;

import java.util.List;
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
	private List<Musica> musicas;

	public Integer getFaixas() {
		if(musicas != null) return 0;
		return musicas.size();
	}

	public void setFaixas(Integer faixas) {
		this.faixas = faixas;
	}		

	public List<Musica> getMusicas() {
		return musicas;
	}

	public void setMusicas(List<Musica> list) {
		this.musicas = list;
		setFaixas(list.size());
	}

		
	@Override
	public String toString() {
		return "Album [getId()=" + getId() + ", getDescricao()=" + getDescricao() + ", faixas=" + faixas + ", musicas="
				+ musicas + "]";
	}
	
	
	
	

}
