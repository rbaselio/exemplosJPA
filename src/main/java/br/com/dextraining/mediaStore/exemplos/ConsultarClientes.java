package br.com.dextraining.mediaStore.exemplos;

import java.util.List;

import br.com.dextraining.mediaStore.entities.Cliente;
import br.com.dextraining.mediaStore.services.ClienteService;
import br.com.dextraining.mediaStore.utils.EntityManagerUtil;

public class ConsultarClientes {

	public static void main(String[] args) {
		EntityManagerUtil.criarConexao();
		
		ClienteService clienteService = new ClienteService();
		
		List<Cliente> clientes = clienteService.findByNome("cliente");
		for (Cliente cliente : clientes) {
			System.out.println(cliente);
		}
		
		EntityManagerUtil.fechaConexao();
	}

}
