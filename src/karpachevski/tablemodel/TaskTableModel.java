package karpachevski.tablemodel;

import karpachevski.model.Task;

public class TaskTableModel extends EntityTableModel {

	private final static String[] columnName = {"Название", "План. дата начала", 
		"Факт дата начала", "План. дата окончания", "Факт. дата окончания", 
		"Планируемая продолжительность", "Процент выполнения", 
		"Документ", "Список предвар. задач"};
	
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
				case 0:	return entity.getTitle();
				case 1: return entity.getComplishion();
			}
		} catch (Exception e) {
			return "";
		}
		return "";
	}

}
