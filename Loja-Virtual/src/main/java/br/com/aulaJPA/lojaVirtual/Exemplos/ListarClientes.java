package br.com.aulaJPA.lojaVirtual.Exemplos;

import java.util.List;

import br.com.aulaJPA.lojaVirtual.entities.Cliente;
import br.com.aulaJPA.lojaVirtual.services.ClienteService;
import br.com.aulaJPA.lojaVirtual.utils.EntityManagerUtil;



public class ListarClientes {

	public static void main(String[] args) {
		EntityManagerUtil.criarConexão();

		ClienteService clienteService = new ClienteService();

		//List<Cliente> todosClientes = clienteService.findAll();
		
		//List<Cliente> todosClientes = clienteService.findByAtribute("nome", "Usuario 1");
		
		List<Cliente> todosClientes = clienteService.findName("Usuario", true);

		for (Cliente pessoa : todosClientes) {
			System.out.println(pessoa);

		}

		EntityManagerUtil.fechaConexao();
	}

}
