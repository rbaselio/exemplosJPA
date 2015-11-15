package br.com.roberto.mediaStore.services.produto;

import br.com.roberto.mediaStore.entities.Livro;
import br.com.roberto.mediaStore.services.ProdutoService;

public class LivroService extends ProdutoService<Livro> {

	public LivroService() {
		this.entityClass = Livro.class;
	}

}
