package karpachevski.tablemodel;

import karpachevski.model.Student;

public class StudentTableModel extends EntityTableModel {

	private final static String[] columnName = {"ФИО", "Статус", "Дата поступления", "План. срок защиты",
		"Факт. срок защиты", "Список задач", "Тема диссертации", 
		"Шифр", "Руководитель", "Оппоненты", "Ведущая организация"};
	
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
			Student entity = (Student) entityList.get(rowIndex);
			switch (columnIndex) {
				case 0:	return entity.toString();
				case 1: return entity.getStatus();
				case 2: return entity.getNameOfDiss();
			}
		} catch (Exception e) {
			return "";
		}
		return "";
	}

}
