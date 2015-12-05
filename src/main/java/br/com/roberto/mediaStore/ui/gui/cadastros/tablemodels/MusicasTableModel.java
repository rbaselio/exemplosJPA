package br.com.roberto.mediaStore.ui.gui.cadastros.tablemodels;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.table.AbstractTableModel;

import br.com.roberto.mediaStore.entities.Musica;

public class MusicasTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private List<Musica> li;
	private String[] columnNames;

	public MusicasTableModel(Set<Musica> list) {
		this.li = new ArrayList<Musica>(list);
		this.columnNames = new String[] { "Nome", "Duração" };
	}
	
	public int getRowCount() {
		return li.size();
	}
	
	public Musica removerMusica(int i){
		return li.remove(i);
	}

	public int getColumnCount() {
		return columnNames.length;
	}
	
	public String getColumnName(int column) {		
		return columnNames[column];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Musica musica = li.get(rowIndex);
		Object[] values = new Object[] { musica.getNome(), musica.getDuracao() };
		return values[columnIndex];

	}

	public void limpar() {
		li.clear();
		
	}
	
}