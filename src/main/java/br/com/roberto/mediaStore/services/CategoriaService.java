package br.com.roberto.mediaStore.services;

import br.com.roberto.mediaStore.entities.Categoria;

public class CategoriaService extends BaseService<Long, Categoria>{
	
	public CategoriaService() {
		this.entityClass = Categoria.class;
	}

}
