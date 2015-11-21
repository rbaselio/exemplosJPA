package br.com.roberto.mediaStore.services;

import br.com.roberto.mediaStore.entities.Editora;

public class EditoraService extends BaseService<Long, Editora> {

	public EditoraService() {
		this.entityClass = Editora.class;
	}

}
