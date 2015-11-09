package br.com.aulaJPA.lojaVirtual.Exemplos;



import java.util.Date;
import java.util.Random;

import br.com.aulaJPA.lojaVirtual.entities.Cliente;
import br.com.aulaJPA.lojaVirtual.services.ClienteService;
import br.com.aulaJPA.lojaVirtual.utils.EntityManagerUtil;

public class InserirClientes {

	public static void main(String[] args) {
		Random gerador = new Random();
		EntityManagerUtil.criarConexão();
		
		ClienteService clienteService = new ClienteService();
		
		Cliente cliente = null;
		
		for (int i = 0; i < 10; i++) {
			cliente = new Cliente();
			cliente.setEndereco("Rua " + gerador.nextInt());
			cliente.setNome("Usuario " + gerador.nextInt());
			cliente.setNasc(new Date(Math.abs(System.currentTimeMillis() - gerador.nextInt())));
			
			clienteService.persist(cliente);
		}
		
		
		
		EntityManagerUtil.fechaConexao();
		
		

	}

}
