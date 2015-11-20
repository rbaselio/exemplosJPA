package br.com.roberto.mediaStore.gui.TableModels;

import java.text.DecimalFormat;
import br.com.roberto.mediaStore.entities.Livro;

public class LivroTableModel extends BaseTableModel<Livro>{
	private static final long serialVersionUID = 6105842825518764825L;
	
	
	
	public LivroTableModel() {
		super();
		this.entityClass = Livro.class;
		this.columnNames = new String[] { "Codigo", "Descricao", "ISBN", "Valor"};			
	}	
	
	
	public Object getValueAt(int rowIndex, int columnIndex) {		
		Livro l = entityList.get(rowIndex);
		DecimalFormat df = new DecimalFormat("#,##0.00") ;
		
		Object[] values = new Object[] { l.getId(), l.getDescricao(), l.getISBN(), df.format(l.getPreco()) };
		return values[columnIndex];
	}
		
	
}
