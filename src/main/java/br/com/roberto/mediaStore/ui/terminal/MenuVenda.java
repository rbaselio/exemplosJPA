package br.com.roberto.mediaStore.ui.terminal;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import br.com.roberto.mediaStore.entities.Cliente;
import br.com.roberto.mediaStore.entities.ItemVenda;
import br.com.roberto.mediaStore.entities.Produto;
import br.com.roberto.mediaStore.entities.Venda;
import br.com.roberto.mediaStore.services.ClienteService;
import br.com.roberto.mediaStore.services.ProdutoService;
import br.com.roberto.mediaStore.services.VendaService;


public class MenuVenda extends MenuBase {

	@Override
	public void executar() throws IOException {
		int op;
		do {
			limparTela();
			System.out.println("-----------------------------");
			System.out.println("Clientes:");
			System.out.println("1\t-\tVender");
			System.out.println("1\t-\tTotal por cliente");
			System.out.println();
			System.out.println("0\t-\tVoltar");
			System.out.print("Escolha sua opcao: ");
			op = reader.read();
		} while (op < 48 || op > 53);
		reader.readLine();
		char opcao = (char) op;
		switch (opcao) {
		case '1':
			vender();
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

	private void vender() {
		Venda venda = new Venda();
		Cliente cliente = null;
		ClienteService clienteSevice = new ClienteService();
		ProdutoService produtoService = new ProdutoService();
		do{
			Long id = pedirLong("Inform o Cliente:");
			cliente = clienteSevice.findById(id);
			if (cliente == null) System.out.println("Cliente não entrado!!");		
		}while(cliente == null);
		venda.setCliente(cliente);
		
		
		Set<ItemVenda> itemVendas = new HashSet<ItemVenda>();
		do{
			Produto produto = null;
			do{
				Long id = pedirLong("Informe o Produto:");
				produto = produtoService.findById(id);
				if (produto == null) System.out.println("Cliente não entrado!!");
			}while(produto == null);
			ItemVenda itemvenda = new ItemVenda();
			itemvenda.setProdutos(produto);
			itemvenda.setQuantidade(pedirValor("Digite a quantidade:"));
			itemVendas.add(itemvenda);
		}while(confirmacao("Inserir novo produdo (s/n)?"));
		
		venda.setItemVendas(itemVendas);
		venda.setData(pedirData("Ditige a data da venda:"));
		new VendaService().persist(venda);
	}
	
	

}
