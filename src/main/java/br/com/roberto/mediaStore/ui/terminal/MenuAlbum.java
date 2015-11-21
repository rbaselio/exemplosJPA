package br.com.roberto.mediaStore.ui.terminal;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.roberto.mediaStore.entities.Album;
import br.com.roberto.mediaStore.entities.Musica;
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
			System.out.println("5\t-\tListar por musicas");
			System.out.println();
			System.out.println("0\t-\tVoltar");
			System.out.print("Escolha sua opcao: ");
			op = reader.read();
		} while (op < 48 || op > 53);
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
			listarPorMusica();
			break;
		default:
			break;
		}
	}

	
	private void listarPorMusica() {
		limparTela();
		
		String nome = pedirString("Digite o nome da Musica: ");
		List<Album> todosAlbums = albumService.findByMusica(nome);
		for (Album album : todosAlbums) {
			System.out.println(album);
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
		album.setPreco(pedirValor("Digite o valor do álbum: "));
		
		Set<Musica> musicas = new HashSet<Musica>();
		do{
			String nomeMusica = pedirString("Digite o nome da musica");
			Integer duracao = pedirInteiro("Duracao da Musica");
			Musica musica = new Musica();
			musica.setNome(nomeMusica);
			musica.setDuracao(duracao);
			musicas.add(musica);
		}while (confirmacao("Incluir mais musicas (s/n)?"));
		
		album.setMusicas(musicas);
		albumService.persist(album);
		
		
	}
	
}
