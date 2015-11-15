package br.com.roberto.mediaStore.exemplos;

import java.util.List;

import br.com.roberto.mediaStore.entities.Cliente;
import br.com.roberto.mediaStore.services.ClienteService;
import br.com.roberto.mediaStore.utils.EntityManagerUtil;

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
