package br.com.roberto.mediaStore.ui;

import java.io.IOException;
import java.util.List;

import br.com.roberto.mediaStore.entities.Livro;
import br.com.roberto.mediaStore.services.produto.LivroService;


public class MenuLivro extends MenuBase {
	private LivroService livroService = new LivroService();
	Livro livro = new Livro();

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
			alterar();
			break;
		case '4':
			listarTodos();
			break;	
		case '5':
			remover();
			break;
		case '6':
			break;
		default:
			break;
		}
	}

	private void listarTodos() {
		Integer max = 2;
		Integer start = 0;
		Long total = livroService.countAll();
		List<Livro> todosLivros = null;
		
		do{
			todosLivros	= livroService.findAll(start, max);
			for (Livro livro : todosLivros) {
				System.out.println(livro);		
				
			}
			start += max;
			if (start > total) break;
			
			
			
		} while (confirmacao("mostrar mais? (s/n)?: "));
	}

	private void remover() {
		livro = livroService.findById(pedirLong("Digite o codigo do livro: "));
		System.out.println(livro);
		livroService.remove(livro);
		
	}

	private void alterar() {
		Long id = pedirLong("Digite o codigo do livro: ");
		if(id != null){
			livro = livroService.findById(id);
			livro.setDescricao(pedirString("Digite a nova descrição do livro: "));
			livro.setPreco(pedirValor("Digite o novo valor do livro: "));
			livroService.update(livro);
		}
		else{
			System.out.println("Livro não enctrado");
		}
		
		
	}

	private void bucarPorNome() {
		limparTela();
		String nome = pedirString("Digite o nome do livro: ");
		List<Livro> todosLivros = livroService.findByDescricao(nome);
		for (Livro livro : todosLivros) {
			System.out.println(livro);

		}	
		
		
	}

	private void cadastrarLivro() {		
		livro.setDescricao(pedirString("Digite a descrição do livro: "));
		livro.setISBN(pedirString("Digite o ISBN: "));
		livro.setPreco(pedirValor("Digite o valor do livro: "));
		livroService.persist(livro);
		
		
	}
	
}
