package br.com.dextraining.mediaStore.services.produto;

import br.com.dextraining.mediaStore.entities.Livro;
import br.com.dextraining.mediaStore.services.ProdutoService;

public class LivroService extends ProdutoService<Livro> {
	
	public LivroService() {
		this.entityClass = Livro.class;
	}
	
	

}
