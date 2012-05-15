package karpachevski.component;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Calendar;

import javax.swing.*;
import java.util.*;

public class DateField extends JPanel {

	private JSpinner dayFld;
	private JSpinner monthFld;
	private JSpinner yearFld;
	
	public DateField() {

		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(3,3,3,3);
		c.gridx = 0;
		c.gridy = 0;
		
		setLayout(layout);
		
		String[] properties = {"День: ", "Месяц: ", "Год: "};
		
		JLabel dayLbl = new JLabel(properties[0]);
		JLabel monthLbl = new JLabel(properties[1]);
		JLabel yearLbl = new JLabel(properties[2]);

		SpinnerModel daySpinModel = new SpinnerNumberModel(0,0,31,1);
		SpinnerModel monthSpinModel = new SpinnerNumberModel(0,0,11,1); 
		SpinnerModel yearSpinModel = new SpinnerNumberModel(1950,0,2100,1); 
		dayFld = new JSpinner(daySpinModel);
		monthFld  = new JSpinner(monthSpinModel);
		yearFld  = new JSpinner(yearSpinModel);

		c.gridx = 0;
		c.gridy = 0;
		add(dayLbl,c);
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1;
		add(dayFld,c);
		c.gridx = 2;
		c.gridy = 0;
		c.weightx = 0;
		add(monthLbl,c);
		c.gridx = 3;
		c.gridy = 0;
		c.weightx = 1;
		add(monthFld,c);
		c.gridx = 4;
		c.gridy = 0;
		c.weightx = 0;
		add(yearLbl,c);
		c.gridx = 5;
		c.gridy = 0;
		c.weightx = 1;
		add(yearFld,c);
	}
	
	public void setDate(Calendar calendar) {
		if (calendar != null) {
			dayFld.setValue(calendar.get(Calendar.DAY_OF_MONTH));
			monthFld.setValue(calendar.get(Calendar.MONTH));
			yearFld.setValue(calendar.get(Calendar.YEAR));
		}
	}
	
	public Calendar getDate() {
		Calendar date = Calendar.getInstance();
		date.set((Integer)yearFld.getValue(), (Integer)monthFld.getValue(), (Integer)dayFld.getValue());
		return date;
	}
	
}
