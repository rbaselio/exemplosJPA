package br.com.roberto.mediaStore.services.produto;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.roberto.mediaStore.entities.Album;
import br.com.roberto.mediaStore.services.BaseService;
import br.com.roberto.mediaStore.utils.EntityManagerUtil;

public class AlbumService extends BaseService<Long, Album>  {
	EntityManager em = EntityManagerUtil.criarEntityManager();
	
	
	public AlbumService() {
		this.entityClass = Album.class;
	}
	
	public List<Album> findByDescricao(String descricao) {
		
		StringBuilder builder = new StringBuilder("FROM ");
		builder.append(entityClass.getSimpleName());
		builder.append(" c ");
		builder.append(" WHERE UPPER(c.descricao) LIKE UPPER(:descricao)");
		builder.append(" ORDER BY c.descricao");
		
		TypedQuery<Album> query = em.createQuery(builder.toString(), entityClass);
		query.setParameter("descricao", "%"+descricao+"%");
		return query.getResultList();
	}
	
	public List<Album> findByMusica(String musica) {
		
		StringBuilder builder = new StringBuilder("Select a FROM ");
		builder.append(entityClass.getSimpleName());
		builder.append(" a JOIN FETCH a.musicas m ");
		builder.append(" WHERE UPPER(m.nome) LIKE UPPER(:musica)");
		builder.append(" ORDER BY a.descricao");
		
		TypedQuery<Album> query = em.createQuery(builder.toString(), entityClass);
		query.setParameter("musica", "%"+musica+"%");
		return query.getResultList();
	}

}
