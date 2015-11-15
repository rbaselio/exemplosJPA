package br.com.roberto.mediaStore.ui;

import java.io.IOException;
import java.util.List;

import br.com.roberto.mediaStore.entities.Album;
import br.com.roberto.mediaStore.services.produto.AlbumService;


public class MenuAlbum extends MenuBase {

	private AlbumService albumService = new AlbumService();
	
	@Override
	public void executar() throws IOException {
		int op;
		do {
			limparTela();
			System.out.println("-----------------------------");
			System.out.println("Albuns:");
			System.out.println("1\t-\tIncluir");
			System.out.println("2\t-\tBuscar por nome");
			System.out.println("3\t-\tAlterar");
			System.out.println("4\t-\tRemover");
			System.out.println();
			System.out.println("0\t-\tVoltar");
			System.out.print("Escolha sua opcao: ");
			op = reader.read();
		} while (op < 48 || op > 52);
		reader.readLine();
		char opcao = (char) op;
		switch (opcao) {
		case '1':
			cadastrarAlbum();
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
		default:
			break;
		}
	}

	
	private void buscarPorNome() {
		limparTela();
		String nome = pedirString("Digite o nome do album: ");
		List<Album> todosAlbums = albumService.findByDescricao(nome);
		for (Album album : todosAlbums) {
			System.out.println(album);

		}	
		
		
	}

	private void cadastrarAlbum() {
		Album album = new Album();
		album.setDescricao(pedirString("Digite a descrição do Album: "));
		album.setFaixas(pedirInteiro("Digite a quantidade de faixas: "));
		album.setPreco(pedirValor("Digite o valor do livro: "));
		albumService.persist(album);
		
		
	}
	
}
