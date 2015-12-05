package br.com.roberto.mediaStore.entities;

import java.util.HashSet;
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
		if(musicas != null) return 0;
		return musicas.size();
	}

	public void setFaixas(Integer faixas) {
		this.faixas = faixas;
	}		

	public Set<Musica> getMusicas() {
		return musicas;
	}
	

	public void addMusicas(Musica musica) {
		if (musicas == null) musicas = new HashSet<Musica>(); 
		System.out.println(musicas.add(musica));
		
		setFaixas(musicas.size());
	}

	public void removeMusicas(Musica musica) {
		musicas.remove(musica);
		setFaixas(musicas.size());
	}
		
	@Override
	public String toString() {
		return "Album [getId()=" + getId() + ", getDescricao()=" + getDescricao() + ", faixas=" + faixas + ", musicas="
				+ musicas + "]";
	}

	public String getduracao() {
		Integer cont = 0;
		for (Musica musica : musicas) {
			cont += musica.getDuracao();			
		}
		return cont.toString();
	}
	
	
	
	

}
