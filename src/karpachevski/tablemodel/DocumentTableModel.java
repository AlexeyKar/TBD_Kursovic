package karpachevski.tablemodel;

import java.util.Calendar;

import karpachevski.model.Document;

public class DocumentTableModel extends EntityTableModel {

	private final static String[] columnName = {"ID", "Название", "Дата получения"};
	
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
			Document entity = (Document) entityList.get(rowIndex);
			switch (columnIndex) {
					case 0:	return entity.getId();
					case 1:	return entity.getTitle();
					case 2: return entity.getDateOfSupply().get(Calendar.DAY_OF_MONTH) + "."
								+ entity.getDateOfSupply().get(Calendar.MONTH) + "."
								+ entity.getDateOfSupply().get(Calendar.YEAR);
				}
		} catch (Exception e) {
			return "";
		}
		return "";
	}


}
