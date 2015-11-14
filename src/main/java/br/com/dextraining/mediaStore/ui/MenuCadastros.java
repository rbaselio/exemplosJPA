package br.com.dextraining.mediaStore.ui;

import java.io.IOException;

public class MenuCadastros extends MenuBase {
	
	public void executar() throws IOException {
		int op;
		do {
			limparTela();
			System.out.println("-----------------------------");
			System.out.println("Cadastros:");
			System.out.println("1\t-\tCliente");
			System.out.println("2\t-\tLivros");
			System.out.println("3\t-\tFilmes");
			System.out.println("4\t-\tAlbuns");
			System.out.println();
			System.out.println("0\t-\tVoltar");
			System.out.print("Escolha sua opcao: ");
			op = reader.read();
		} while (op < 48 || op > 52);
		reader.readLine();
		char opcao = (char) op;
		switch (opcao) {
		case '1':
			new MenuClientes().executar();
			break;
		case '2':
			new MenuLivro().executar();
			break;
		case '3':
			new MenuFilme().executar();
			break;
		case '4':
			new MenuAlbum().executar();
			break;
		default:
			break;
		}
	}

}
