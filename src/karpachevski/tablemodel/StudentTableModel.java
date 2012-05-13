package karpachevski.tablemodel;

import java.util.ArrayList;
import java.util.Calendar;

import karpachevski.model.Person;
import karpachevski.model.Student;
import karpachevski.model.Task;

public class StudentTableModel extends EntityTableModel {

	private final static String[] columnName = {"Id", "ФИО", "Статус", "Дата поступления", "План. срок защиты",
		"Факт. срок защиты", "Тема диссертации", 
		"Шифр", "Руководитель", "Ведущая организация"};
	
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
				case 0:	return entity.getId();
				case 1:	return entity.toString();
				case 2: return entity.getStatus();
				case 3: return entity.getDateOfAdmission();
				case 4: return entity.getDateOfPlanDiss();
				case 5: return entity.getDateOfFactDiss();
				case 6: return entity.getNameOfDiss();
				case 7: return entity.getCodeOfDiss();
				case 8: return entity.getSupervisor();
				case 9: return entity.getOrganization();
			}
		} catch (Exception e) {
			return "";
		}
		return "";
	}

}
