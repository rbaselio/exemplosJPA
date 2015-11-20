package br.com.roberto.mediaStore.gui.TableModels;

import java.text.DecimalFormat;

import br.com.roberto.mediaStore.entities.Album;



public class AlbumTableModel extends BaseTableModel<Album>{
	private static final long serialVersionUID = 6105842825518764825L;
		

	public AlbumTableModel() {
		super();
		this.entityClass = Album.class;
		this.columnNames = new String[] { "Codigo", "Descricao", "Faixas", "Valor"};
	}	
	
	
	public Object getValueAt(int rowIndex, int columnIndex) {		
		Album l = entityList.get(rowIndex);
		DecimalFormat df = new DecimalFormat("#,##0.00") ;
		
		Object[] values = new Object[] { l.getId(), l.getDescricao(), l.getFaixas(), df.format(l.getPreco()) };
		return values[columnIndex];
	}
		
}
