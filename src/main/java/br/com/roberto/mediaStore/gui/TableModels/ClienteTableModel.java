package br.com.roberto.mediaStore.gui.TableModels;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.roberto.mediaStore.entities.Cliente;
import br.com.roberto.mediaStore.services.ClienteService;

public class ClienteTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 6105842825518764825L;
	private String[] columnNames = new String[] { "Codigo", "Nome", "Nascimento", "Ativo"};
	
	ClienteService clienteService = new ClienteService();
	private List<Cliente>  clientesList;

	public ClienteTableModel() {
		super();
        clientesList = clienteService.findAll(null, null);       
    }
	
	public int getRowCount() {
		return clientesList.size();
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Cliente cliente = clientesList.get(rowIndex);
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String data;
		try{
			data = df.format(cliente.getNascimento());
		} catch(Exception e){data = "";}
		
		
		Object[] values = new Object[] { cliente.getId(), cliente.getNome(), data, cliente.isAtivo()};
		return values[columnIndex];
	}

	@Override
	public String getColumnName(int column) {		
		return columnNames[column];
	}
}
