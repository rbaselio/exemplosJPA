package br.com.roberto.mediaStore.services;

import br.com.roberto.mediaStore.entities.Venda;


public class VendaService extends BaseService<Long, Venda>{
	public VendaService() {
		this.entityClass = Venda.class;
	}

}
