package br.com.roberto.mediaStore.services;

import br.com.roberto.mediaStore.entities.Produto;

public class ProdutoService extends BaseService<Long, Produto> {
	
	public ProdutoService() {
		this.entityClass = Produto.class;
	}


}