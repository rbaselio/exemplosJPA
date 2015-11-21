package br.com.roberto.mediaStore.ui.terminal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.roberto.mediaStore.entities.Estado;

public abstract class MenuBase {
	private static String DATE_FORMAT = "dd/MM/yyyy";

	protected BufferedReader reader = new BufferedReader(new InputStreamReader(
			System.in));

	protected void limparTela() {
		final String os = System.getProperty("os.name");

		try {
			if (os.contains("Windows")) {
				// Runtime.getRuntime().exec("cls");
			} else {
				 
				Runtime.getRuntime().exec("clear");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	protected String pedirString(String titulo) {
		String entrada = null;
		do {
			System.out.print(titulo);
			try {
				entrada = reader.readLine();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		} while (entrada == null || "".equals(entrada.trim()));

		return entrada;
	}

	protected BigDecimal pedirValor(String titulo) {
		BigDecimal valor = null;
		do {
			String dado = pedirString(titulo);
			try {
				valor = new BigDecimal(dado);
			} catch (NumberFormatException e) {
			}
			;
		} while (valor == null);
		return valor;
	}

	protected boolean confirmacao(String titulo) {
		String dado = null;
		do {
			dado = pedirString(titulo);
		} while (!("S".equals(dado) || "s".equals(dado) || "N".equals(dado) || "n"
				.equals(dado)));
		if ("S".equals(dado) || "s".equals(dado)) {
			return true;
		}
		if ("N".equals(dado) || "n".equals(dado)) {
			return false;
		}
		return false;
	}
	
	protected Long pedirLong(String titulo) {
		Long valor = null;
		do {
			String dado = pedirString(titulo);
			try {
				valor = Long.parseLong(dado);
			} catch (NumberFormatException e) {
			}
		} while (valor == null);
		return valor;
	}
	
	protected Integer pedirInteiro(String titulo) {
		Integer valor = null;
		do {
			String dado = pedirString(titulo);
			try {
				valor = Integer.parseInt(dado);
			} catch (NumberFormatException e) {
			}
		} while (valor == null);
		return valor;
	}
	
	protected Date pedirData(String titulo) {
		String valor = pedirString(titulo);
		
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		Date valorData = null;
		do {
			try {
				valorData = format.parse(valor);
			} catch (ParseException e) {
			}
		} while (valorData == null);
		return valorData;
	}
	
	protected Estado pedirEstado(String titulo){
		
		Estado estado = null;
		do{
			try {
				String valorEstado = pedirString(titulo);
				estado = Estado.valueOf(valorEstado);
			} catch (IllegalArgumentException e) {
				System.out.println("Estado invalido");
			}
		} while (estado == null);
		return estado;
	}
	

	public abstract void executar() throws IOException;

}
