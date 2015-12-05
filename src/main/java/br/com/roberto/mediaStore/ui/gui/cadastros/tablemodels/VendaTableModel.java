package br.com.roberto.mediaStore.ui.gui.cadastros.tablemodels;

import java.text.DecimalFormat;

import br.com.roberto.mediaStore.entities.Venda;

public class VendaTableModel extends BaseTableModel<Venda>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public VendaTableModel() {
		super();
		this.entityClass = Venda.class;
		this.columnNames = new String[] { "Venda", "Cliente", "Valor Unit."};
	}	
	
	
	public Object getValueAt(int rowIndex, int columnIndex) {		
		Venda l = entityList.get(rowIndex);
		DecimalFormat df = new DecimalFormat("#,##0.00") ;
		
		Object[] values = new Object[] { l.getId(), l.getCliente().getNome(), df.format(l.getValortotal())};
		return values[columnIndex];
	}

}
