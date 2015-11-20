package br.com.roberto.mediaStore.gui.TableModels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.roberto.mediaStore.services.BaseService;

public abstract class BaseTableModel<E> extends AbstractTableModel{
	
	protected Class<E> entityClass;
		
	
	private static final long serialVersionUID = -9090311740938083893L;
	
	protected List<E>  entityList;
	protected String[] columnNames;
	protected BaseService<Long, E> service;
	
	
	public void setService(BaseService<Long, E> service) {
		
		this.service = service;		
	}
	
	public E getEntidade(int rowIndex) {
		try {
			return entityList.get(rowIndex);
		}catch (IndexOutOfBoundsException e){
			return null;
		}
			
	}
		
	public void buscarLista(Integer start, Integer max) {
		entityList = service.findAll(start, max); 		
    }	
		
	public int getRowCount() {
		return entityList.size();
	}

	public int getColumnCount() {
		return columnNames.length;
	}
	@Override
	public String getColumnName(int column) {		
		return columnNames[column];
	}
	
	public abstract Object getValueAt(int rowIndex, int columnIndex);
		
		
}
