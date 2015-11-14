package br.com.dextraining.mediaStore.ui;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import br.com.dextraining.mediaStore.entities.Cliente;
import br.com.dextraining.mediaStore.services.ClienteService;


public class MenuClientes extends MenuBase {

	@Override
	public void executar() throws IOException {
		int op;
		do {
			limparTela();
			System.out.println("-----------------------------");
			System.out.println("Clientes:");
			System.out.println("1\t-\tIncluir");
			System.out.println("2\t-\tBuscar por nome");
			System.out.println("3\t-\tAlterar");
			System.out.println("4\t-\tListar por Estado");
			System.out.println("5\t-\tRemover");
			System.out.println();
			System.out.println("0\t-\tVoltar");
			System.out.print("Escolha sua opcao: ");
			op = reader.read();
		} while (op < 48 || op > 53);
		reader.readLine();
		char opcao = (char) op;
		switch (opcao) {
		case '1':
			insereClinte();
			break;
		case '2':
			consultarClintePorNome();
			break;
		case '3':
			break;
		case '4':
			break;	
		case '5':
			break;
		default:
			break;
		}
	}
	
	private void insereClinte(){
		limparTela();
		String nome = pedirString("Digite o nome: ");
		Date nascimento = pedirData("Digite o nascimento:(dd/mm/aaaa) ");
		ClienteService clienteService = new ClienteService();
		
		Cliente c1 = new Cliente();
		c1.setNome(nome);
		c1.setNascimento(nascimento);
		c1.setAtivo(true);
		clienteService.persist(c1);		
		
	}
	
	private void consultarClintePorNome(){
		limparTela();
		String nome = pedirString("Digite o nome: ");
		ClienteService clienteService = new ClienteService();
		List<Cliente> todosClientes = clienteService.findByNome(nome);
		for (Cliente pessoa : todosClientes) {
			System.out.println(pessoa);

		}		
		
	}	
	

}
