package br.com.dextraining.mediaStore.services.produto;

import br.com.dextraining.mediaStore.entities.Album;
import br.com.dextraining.mediaStore.services.ProdutoService;

public class AlbumService extends ProdutoService<Album> {
	
	public AlbumService() {
		this.entityClass = Album.class;
	}

}
