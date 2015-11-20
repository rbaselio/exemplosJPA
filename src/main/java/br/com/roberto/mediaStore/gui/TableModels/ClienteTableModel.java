package br.com.roberto.mediaStore.gui.TableModels;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import br.com.roberto.mediaStore.entities.Cliente;
import br.com.roberto.mediaStore.services.BaseService;

public class ClienteTableModel extends BaseTableModel<Cliente>{
	
	private static final long serialVersionUID = 6105842825518764825L;
	
	public ClienteTableModel() {
		super();
		this.entityClass = Cliente.class;
		this.columnNames = new String[] { "Codigo", "Nome", "Nascimento", "Ativo"};			
	}	
	
	
	public Object getValueAt(int rowIndex, int columnIndex) {		
		Cliente c = entityList.get(rowIndex);
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");		
		Object[] values = new Object[] { c.getId(), c.getNome(), df.format(c.getNascimento()), c.isAtivo() };
		return values[columnIndex];
	}



	
	
}
