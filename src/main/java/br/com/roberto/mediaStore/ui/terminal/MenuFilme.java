package br.com.roberto.mediaStore.ui.terminal;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.roberto.mediaStore.entities.Categoria;
import br.com.roberto.mediaStore.entities.Filme;
import br.com.roberto.mediaStore.services.CategoriaService;
import br.com.roberto.mediaStore.services.produto.FilmeService;


public class MenuFilme extends MenuBase {
	private FilmeService filmeService = new FilmeService();
	
	
	@Override
	public void executar() throws IOException {
		int op;
		do {
			limparTela();
			System.out.println("-----------------------------");
			System.out.println("Filmes:");
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
			for (Categoria cat : filme.getCategorias()) {
				System.out.println(cat.toString());
			}
			System.out.println(filme);
		}		
		
	}

	
	private void cadastrarFilme() {
		Filme filme = new Filme();
		CategoriaService categoriaService = new CategoriaService();
		filme.setDescricao(pedirString("Digite a descrição do Filme: "));
		filme.setDuracao(pedirInteiro("Digite a duração: "));
		filme.setPreco(pedirValor("Digite o valor do filme: "));
		Set<Categoria> categorias = new HashSet<Categoria>();
		do{
			Categoria categoria = null;
			do{
				Long id = pedirLong("Inform o Categoria:");
				categoria = categoriaService.findById(id);
				if (categoria == null) System.out.println("Categoria não entrado!!");		
			}while(categoria == null);
			categorias.add(categoria);
			
		}while(confirmacao("Incluir outra categoria (s/n)?"));
		
		filme.setCategorias(categorias);
		
		
		
		filmeService.persist(filme);
		
		
	}
	
}
