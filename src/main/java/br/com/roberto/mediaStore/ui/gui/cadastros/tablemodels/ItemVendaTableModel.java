package br.com.roberto.mediaStore.ui.gui.cadastros.tablemodels;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.table.AbstractTableModel;

import br.com.roberto.mediaStore.entities.ItemVenda;

public class ItemVendaTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private List<ItemVenda> li;
	private String[] columnNames;

	public ItemVendaTableModel(Set<ItemVenda> list) {
		this.li = new ArrayList<ItemVenda>(list);
		this.columnNames = new String[] { "Id", "Descricao do produto", "Quant", "Valor Unit", "Valor Total" };
	}
	
	public int getRowCount() {
		return li.size();
	}
	
	public ItemVenda removerItemVenda(int i){
		return li.remove(i);
	}

	public int getColumnCount() {
		return columnNames.length;
	}
	
	public String getColumnName(int column) {		
		return columnNames[column];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		ItemVenda itemVenda = li.get(rowIndex);
		Object[] values = new Object[] { itemVenda.getProduto().getId(), itemVenda.getProduto().getDescricao(), itemVenda.getQuantidade(), itemVenda.getProduto().getPreco(), itemVenda.getQuantidade().multiply(itemVenda.getProduto().getPreco()) };
		return values[columnIndex];
	}

	public void limpar() {
		li.clear();
		
	}
	
}