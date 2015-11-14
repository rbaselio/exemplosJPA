package br.com.dextraining.mediaStore.services.produto;


import br.com.dextraining.mediaStore.entities.Filme;
import br.com.dextraining.mediaStore.services.ProdutoService;

public class FilmeService extends ProdutoService<Filme> {
	
	public FilmeService() {
		this.entityClass = Filme.class;
	}
	
	
}