package br.com.roberto.mediaStore.ui.gui.cadastros.tablemodels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.roberto.mediaStore.entities.Musica;

public class MusicasTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private List<Musica> li;
	private String[] columnNames;

	public MusicasTableModel(List<Musica> list) {
		this.li = list;
		this.columnNames = new String[] { "Nome", "Duração" };
	}
	
	public MusicasTableModel(){
		this.li = new ArrayList<Musica>();
		
	}

	public int getRowCount() {
		return li.size();
	}
	
	public void addMusica(Musica musica){
		li.add(musica);
	}
	
	public List<Musica> getMusicList(){
		return (li) ;
	}
	
	public void limparMusicList(){
		li.clear();
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

	public Musica getValueAt(int selectedRow) {
		return li.get(selectedRow);
	}
	
	public Musica removeValueAt(int selectedRow) {
		return li.get(selectedRow);
	}
	
}