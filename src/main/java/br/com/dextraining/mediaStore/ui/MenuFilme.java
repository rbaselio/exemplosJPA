package br.com.dextraining.mediaStore.ui;

import java.io.IOException;
import java.util.List;

import br.com.dextraining.mediaStore.entities.Filme;
import br.com.dextraining.mediaStore.services.produto.FilmeService;


public class MenuFilme extends MenuBase {
	private FilmeService filmeService = new FilmeService();
	
	
	@Override
	public void executar() throws IOException {
		int op;
		do {
			limparTela();
			System.out.println("-----------------------------");
			System.out.println("Livros:");
			System.out.println("1\t-\tIncluir");
			System.out.println("2\t-\tBuscar por nome");
			System.out.println("3\t-\tAlterar");
			System.out.println("4\t-\tListar todos");
			System.out.println("5\t-\tRemover");
			System.out.println("6\t-\tListar por Ator");
			System.out.println();
			System.out.println("0\t-\tVoltar");
			System.out.print("Escolha sua opcao: ");
			op = reader.read();
		} while (op < 48 || op > 54);
		reader.readLine();
		char opcao = (char) op;
		switch (opcao) {
		case '1':
			cadastrarFilme();
			break;
		case '2':
			buscarPorNome();
			break;
		case '3':
			break;
		case '4':
			break;	
		case '5':
			break;
		case '6':
			break;
		default:
			break;
		}
	}
	
	
	private void buscarPorNome() {
		limparTela();
		String nome = pedirString("Digite o nome do filme: ");
		List<Filme> todosFilmes = filmeService.findByDescricao(nome);
		for (Filme filme : todosFilmes) {
			System.out.println(filme);

		}	
		
		
	}

	private void cadastrarFilme() {
		Filme filme = new Filme();
		filme.setDescricao(pedirString("Digite a descrição do Filme: "));
		filme.setDuracao(pedirInteiro("Digite a duração: "));
		filme.setPreco(pedirValor("Digite o valor do livro: "));
		filmeService.persist(filme);
		
		
	}
	
}
