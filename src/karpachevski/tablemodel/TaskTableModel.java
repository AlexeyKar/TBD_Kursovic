package karpachevski.tablemodel;

import java.util.Calendar;

import karpachevski.model.Task;

public class TaskTableModel extends EntityTableModel {

	private final static String[] columnName = {"ID", "Название", "План. дата начала", 
		"Факт дата начала", "План. дата окончания", "Факт. дата окончания", 
		"Планируемая продолжительность", "Процент выполнения", 
		"Документ"};
	
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
				case 2: return entity.getDateOfStartPlan().get(Calendar.DAY_OF_MONTH) + "."
							+ entity.getDateOfStartPlan().get(Calendar.MONTH) + "."
							+ entity.getDateOfStartPlan().get(Calendar.YEAR); 
				case 3: return entity.getDateOfStartFact().get(Calendar.DAY_OF_MONTH) + "."
							+ entity.getDateOfStartFact().get(Calendar.MONTH) + "."
							+ entity.getDateOfStartFact().get(Calendar.YEAR);
				case 4: return entity.getDateOfFinishPlan().get(Calendar.DAY_OF_MONTH) + "."
							+ entity.getDateOfFinishPlan().get(Calendar.MONTH) + "."
							+ entity.getDateOfFinishPlan().get(Calendar.YEAR);
				case 5: return entity.getDateOfFinishFact().get(Calendar.DAY_OF_MONTH) + "."
							+ entity.getDateOfFinishFact().get(Calendar.MONTH) + "."
							+ entity.getDateOfFinishFact().get(Calendar.YEAR);
				case 6: return entity.getDurration();
				case 7: return entity.getComplishion();
				case 8: return entity.getDocument();
			}
		} catch (Exception e) {
			return "";
		}
		return "";
	}

}
