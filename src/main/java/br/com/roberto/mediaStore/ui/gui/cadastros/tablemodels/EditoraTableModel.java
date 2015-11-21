package br.com.roberto.mediaStore.ui.gui.cadastros.tablemodels;


import br.com.roberto.mediaStore.entities.Editora;

public class EditoraTableModel extends BaseTableModel<Editora>{
	private static final long serialVersionUID = 6105842825518764825L;
	
	
	
	public EditoraTableModel() {
		super();
		this.entityClass = Editora.class;
		this.columnNames = new String[] { "Codigo", "Nome"};			
	}	
	
	
	public Object getValueAt(int rowIndex, int columnIndex) {		
		Editora l = entityList.get(rowIndex);
		
		Object[] values = new Object[] { l.getId(), l.getNomeEditora() };
		return values[columnIndex];
	}
		
	
}
