package karpachevski.tablemodel;

import karpachevski.model.Task;

public class TaskTableModel extends EntityTableModel {

	private final static String[] columnName = {"Id", "Название", "План. дата начала", 
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
				case 2: return entity.getDateOfStartPlan(); // планируемое время окончания задачи
				case 3: return entity.getDateOfStartFact(); // фактическое время начала задачи
				case 4: return entity.getDateOfFinishPlan(); // планируемое время окончания задачи
				case 5: return entity.getDateOfFinishFact(); // фактическое время окончания задачи
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
