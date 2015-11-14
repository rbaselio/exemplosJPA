package br.com.dextraining.mediaStore.exemplos;

import br.com.dextraining.mediaStore.entities.Cliente;
import br.com.dextraining.mediaStore.services.ClienteService;
import br.com.dextraining.mediaStore.utils.EntityManagerUtil;

public class InserirClientes {

	public static void main(String[] args) {
		EntityManagerUtil.criarConexao();
		
		ClienteService clienteService = new ClienteService();
		
		Cliente c1 = new Cliente();
		c1.setNome("Quarto cliente");
		clienteService.persist(c1);
		
		EntityManagerUtil.fechaConexao();
	}

}
