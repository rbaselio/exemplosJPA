package br.com.dextraining.mediaStore.services.produto;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.dextraining.mediaStore.entities.Album;
import br.com.dextraining.mediaStore.services.ProdutoService;
import br.com.dextraining.mediaStore.utils.EntityManagerUtil;

public class AlbumService extends ProdutoService<Album>  {
	
	public AlbumService() {
		this.entityClass = Album.class;
	}
	
	public List<Album> findByDescricao(String descricao) {
		EntityManager em = EntityManagerUtil.criarEntityManager();
		StringBuilder builder = new StringBuilder("FROM ");
		builder.append(entityClass.getSimpleName());
		builder.append(" c ");
		builder.append(" WHERE UPPER(c.descricao) LIKE UPPER(:descricao)");
		builder.append(" ORDER BY c.descricao");
		
		TypedQuery<Album> query = em.createQuery(builder.toString(), entityClass);
		query.setParameter("descricao", "%"+descricao+"%");
		return query.getResultList();
	}

}
