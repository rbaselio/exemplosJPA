package br.com.roberto.mediaStore.ui.gui.cadastros.tablemodels;

import java.text.DecimalFormat;

import br.com.roberto.mediaStore.entities.Editora;
import br.com.roberto.mediaStore.entities.Livro;
import br.com.roberto.mediaStore.services.produto.LivroService;

public class LivroTableModel extends BaseTableModel<Livro>{
	private static final long serialVersionUID = 6105842825518764825L;
	
	
	public LivroTableModel() {
		super();
		this.entityClass = Livro.class;
		this.columnNames = new String[] { "Codigo", "Descricao", "ISBN", "Valor"};			
	}	
	
	public void buscarEditora(Editora editora) {
		entityList = new LivroService().findByEditora(editora); 		
    }	
	
	
	public Object getValueAt(int rowIndex, int columnIndex) {		
		Livro l = entityList.get(rowIndex);
		DecimalFormat df = new DecimalFormat("#,##0.00") ;
		
		Object[] values = new Object[] { l.getId(), l.getDescricao(), l.getISBN(), df.format(l.getPreco()) };
		return values[columnIndex];
	}
		
	
}
