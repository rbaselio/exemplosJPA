package br.com.roberto.mediaStore.gui.TableModels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.roberto.mediaStore.entities.Album;
import br.com.roberto.mediaStore.services.produto.AlbumService;

public class AlbumTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 6105842825518764825L;
	private String[] columnNames = new String[] { "Codigo", "Descricao", "Faixas", "Valor"};
	
	AlbumService albumService = new AlbumService();
	private List<Album>  albumsList;

	public AlbumTableModel() {
		super();
        albumsList = albumService.findAll(null, null);       
    }
	
	public int getRowCount() {
		return albumsList.size();
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Album album = albumsList.get(rowIndex);
		Object[] values = new Object[] { album.getId(), album.getDescricao(), album.getFaixas(), album.getPreco() };
		return values[columnIndex];
	}

	@Override
	public String getColumnName(int column) {		
		return columnNames[column];
	}
}
