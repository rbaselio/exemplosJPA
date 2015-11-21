package br.com.roberto.mediaStore.ui.gui.cadastros.tablemodels;

import java.text.DecimalFormat;

import br.com.roberto.mediaStore.entities.Filme;

public class FilmeTableModel extends BaseTableModel<Filme>{
	private static final long serialVersionUID = 6105842825518764825L;
	
	
	public FilmeTableModel() {
		super();
		this.entityClass = Filme.class;
		this.columnNames = new String[] { "Codigo", "Descricao", "Duração", "Valor"};
	}	
	
	
	public Object getValueAt(int rowIndex, int columnIndex) {		
		Filme l = entityList.get(rowIndex);
		DecimalFormat df = new DecimalFormat("#,##0.00") ;
		
		Object[] values = new Object[] { l.getId(), l.getDescricao(), l.getDuracao(), df.format(l.getPreco()) };
		return values[columnIndex];
	}
}
