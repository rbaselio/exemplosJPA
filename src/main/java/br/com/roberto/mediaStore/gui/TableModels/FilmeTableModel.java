package br.com.roberto.mediaStore.gui.TableModels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.roberto.mediaStore.entities.Filme;
import br.com.roberto.mediaStore.services.produto.FilmeService;

public class FilmeTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 6105842825518764825L;
	private String[] columnNames = new String[] { "Codigo", "Descricao", "Duração", "Valor"};
	
	FilmeService livroService = new FilmeService();
	private List<Filme>  livrosList;

	public FilmeTableModel() {
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
		Filme livro = livrosList.get(rowIndex);
		Object[] values = new Object[] { livro.getId(), livro.getDescricao(), livro.getDuracao(), livro.getPreco() };
		return values[columnIndex];
	}

	@Override
	public String getColumnName(int column) {		
		return columnNames[column];
	}
}
