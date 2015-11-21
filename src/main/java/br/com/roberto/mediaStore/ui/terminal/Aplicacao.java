package br.com.roberto.mediaStore.ui.terminal;

import java.io.IOException;

import br.com.roberto.mediaStore.utils.EntityManagerUtil;


public class Aplicacao extends MenuBase{
	public static void main(String[] args) {
		Aplicacao aplicacao = new Aplicacao();
		try {
			aplicacao.executar();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			aplicacao.sair();
		}
	}

	public void executar() {
		try {
			EntityManagerUtil.criarConexao();
			menuPrincipal();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	private void menuPrincipal() throws IOException {
		int op;
		do {
			limparTela();
			System.out.println();
			System.out.println("-- LOJA VIRTUAL --");
			System.out.println("-----------------------------");
			System.out.println("Funcionalidades:");
			System.out.println("1\t-\tCadastros");
			System.out.println("2\t-\tVendas");
			System.out.println();
			System.out.println("0\t-\tSair");
			System.out.print("Escolha sua opcao: ");
			op = reader.read();
		} while (op < 48 || op > 50);
		reader.readLine();
		char opcao = (char) op;
		switch (opcao) {
		case '0':
			sair();
			break;
		case '1':
			new MenuCadastros().executar();
			break;
		case '2':
			new MenuVenda().executar();
			break;
		default:
			break;
		}
		
		menuPrincipal();
	}

	private void sair() {
		System.out.println("Encerrando sistema...");
		EntityManagerUtil.fechaConexao();
		System.out.println("Sistema encerrado");
		System.exit(0);
	}
	
}
