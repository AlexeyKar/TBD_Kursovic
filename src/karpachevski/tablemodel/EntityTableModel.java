package karpachevski.tablemodel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import karpachevski.model.SuperEntity;
import javax.swing.table.*;
import java.util.*;
import karpachevski.model.*;

public abstract class EntityTableModel extends AbstractTableModel {

	protected ArrayList entityList = new ArrayList(); 
	
	public void setEntities(Object[] entities) {
		for (int i = 0; i < entities.length; i++) {
			entityList.add(entities[i]);
		}
		fireTableDataChanged();
	}
	
	public ArrayList getEntities() {
		return entityList;
	}
	
	public void addEntity(SuperEntity item) {
		entityList.add(item);
		fireTableRowsInserted(entityList.size(), entityList.size());
	}
	
	public SuperEntity getEntity(int row) {
		if (row < 0) 
			return null;
		return (SuperEntity)entityList.get(row); 
	}
	
	public void updateEntity(int row) {
		fireTableRowsUpdated(row,row);
	}
	
	public void deleteEntity(int row) {
		entityList.remove(row);
		fireTableRowsDeleted(row,row);
	}
	
	@Override
	public int getRowCount() {
		return entityList.size();
	}

	public abstract int getColumnCount();
	public abstract String getColumnName(int column);
	public abstract Object getValueAt(int rowIndex, int columnIndex);
}
