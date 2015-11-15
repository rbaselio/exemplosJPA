package br.com.roberto.mediaStore.services.produto;


import br.com.roberto.mediaStore.entities.Filme;
import br.com.roberto.mediaStore.services.ProdutoService;

public class FilmeService extends ProdutoService<Filme>  {
	
	public FilmeService() {
		this.entityClass = Filme.class;
	}
	
	
	
	
}