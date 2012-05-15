package karpachevski.tablemodel;

import karpachevski.model.Person;

public class PersonTableModel extends EntityTableModel {
	private final static String[] columnName = {"ID", "Имя", "Фамилия", "Отчество"};
	
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
			Person entity = (Person) entityList.get(rowIndex);
			switch (columnIndex) {
					case 0:	return entity.getId();
					case 1:	return entity.getName();
					case 2: return entity.getSurname();
					case 3: return entity.getMiddleName();
				}
		} catch (Exception e) {
			return "";
		}
		return "";
	}

}
