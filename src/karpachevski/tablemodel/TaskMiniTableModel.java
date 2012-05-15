package karpachevski.tablemodel;

import karpachevski.model.Task;

public class TaskMiniTableModel extends EntityTableModel {

	private final static String[] columnName = {"ID", "Название"};
	
	@Override
	public int getColumnCount() {
		return columnName.length;
	}

	@Override
	public String getColumnName(int column) {
		return columnName[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		try {
			Task entity = (Task) entityList.get(rowIndex);
			switch (columnIndex) {
				
				case 0:	return entity.getId();
				case 1:	return entity.getTitle();

			}
		} catch (Exception e) {
			return "";
		}
		return "";
	}

}
