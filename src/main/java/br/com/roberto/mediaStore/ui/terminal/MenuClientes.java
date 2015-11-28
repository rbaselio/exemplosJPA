package br.com.roberto.mediaStore.ui.terminal;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import br.com.roberto.mediaStore.entities.Cliente;
import br.com.roberto.mediaStore.entities.Endereco;
import br.com.roberto.mediaStore.services.ClienteService;
import br.com.roberto.mediaStore.utils.Estado;

public class MenuClientes extends MenuBase {
	Cliente c1 = new Cliente();
	ClienteService clienteService = new ClienteService();

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
			alterarCliente();
			break;
		case '4':
			listarPorEstado();
			break;
		case '5':
			removerCliente();
			break;
		default:
			break;
		}
	}

	private void listarPorEstado() {
		limparTela();
		Estado estado = pedirEstado("Digite o estado: ");
		List<Cliente> todosClientes = clienteService.findByEstado(estado);
		for (Cliente pessoa : todosClientes) {
			System.out.println(pessoa);
		}		
	}

	private void removerCliente() {
		Long id = pedirLong("Digite o codigo do livro: ");
		if (id != null) {

			c1 = clienteService.findById(id);
			System.out.println(c1);
			clienteService.remove(c1);
		} else {
			System.out.println("Cliente não encontrado");
		}

	}

	private void alterarCliente() {
		Long id = pedirLong("Digite o codigo do livro: ");
		if (id != null) {
			c1 = clienteService.findById(id);
			c1.setNome(pedirString("Digite o novo nome do cliente: "));
			c1.setNascimento(pedirData("Digite o novo nascimento:(dd/mm/aaaa) "));
			c1.setAtivo(confirmacao("O cliente é ativo? (s/n): "));
			clienteService.update(c1);
		} else {
			System.out.println("Cliente não encontrado");
		}

	}

	private void insereClinte() {
		limparTela();
		//endereço
		Endereco end = new Endereco();
		
		String logradouro = pedirString("Digite o endereço: ");
		String cidade = pedirString("Digite a cidade: ");
		Estado estado = pedirEstado("Digite o estado: ");
		
		end.setCidade(cidade);
		end.setLogradouro(logradouro);
		end.setEstado(estado);
		
		//cliente
		String nome = pedirString("Digite o nome: ");
		Date nascimento = pedirData("Digite o nascimento:(dd/mm/aaaa) ");
		
		c1.setNome(nome);
		c1.setNascimento(nascimento);
		c1.setAtivo(true);
		c1.setEndereco(end);
		clienteService.persist(c1);

	}

	private void consultarClintePorNome() {
		limparTela();
		String nome = pedirString("Digite o nome: ");
		List<Cliente> todosClientes = clienteService.findByNome(nome);
		for (Cliente pessoa : todosClientes) {
			System.out.println(pessoa);

		}

	}

}
