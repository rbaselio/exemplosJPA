package br.com.dextraining.mediaStore.ui;

import java.io.IOException;


public class MenuAlbum extends MenuBase {

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
			break;
		case '2':
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

}
