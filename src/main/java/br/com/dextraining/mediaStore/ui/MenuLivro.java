package br.com.dextraining.mediaStore.ui;

import java.io.IOException;
import java.util.List;

import br.com.dextraining.mediaStore.entities.Cliente;
import br.com.dextraining.mediaStore.entities.Livro;
import br.com.dextraining.mediaStore.services.ClienteService;
import br.com.dextraining.mediaStore.services.produto.LivroService;


public class MenuLivro extends MenuBase {
	private LivroService livroService = new LivroService();

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
			System.out.println("6\t-\tListar por Editora");
			System.out.println();
			System.out.println("0\t-\tVoltar");
			System.out.print("Escolha sua opcao: ");
			op = reader.read();
		} while (op < 48 || op > 54);
		reader.readLine();
		char opcao = (char) op;
		switch (opcao) {
		case '1':
			cadastrarLivro();
			break;
		case '2':
			bucarPorNome();
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

	private void bucarPorNome() {
		limparTela();
		String nome = pedirString("Digite o nome do livro: ");
		List<Livro> todosLivros = livroService.findByDescricao(nome);
		for (Livro pessoa : todosLivros) {
			System.out.println(pessoa);

		}	
		
		
	}

	private void cadastrarLivro() {
		Livro livro = new Livro();
		livro.setDescricao(pedirString("Digite a descrição do livro: "));
		livro.setISBN(pedirString("Digite o ISBN: "));
		livro.setPreco(pedirValor("Digite o valor do livro: "));
		livroService.persist(livro);
		
		
	}
	
}
