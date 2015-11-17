package br.com.roberto.mediaStore.gui.TableModels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.roberto.mediaStore.entities.Livro;
import br.com.roberto.mediaStore.services.produto.LivroService;

public class LivroTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 6105842825518764825L;
	private String[] columnNames = new String[] { "Codigo", "Descricao", "ISBN", "Valor"};
	
	LivroService livroService = new LivroService();
	private List<Livro>  livrosList;

	public LivroTableModel() {
		super();
        livrosList = livroService.findAll(null, null);       
    }
	
	public int getRowCount() {
		return livrosList.size();
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Livro livro = livrosList.get(rowIndex);
		Object[] values = new Object[] { livro.getId(), livro.getDescricao(), livro.getISBN(), livro.getPreco() };
		return values[columnIndex];
	}

	@Override
	public String getColumnName(int column) {		
		return columnNames[column];
	}
}
