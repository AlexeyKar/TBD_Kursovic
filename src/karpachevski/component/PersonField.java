package karpachevski.component;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import karpachevski.model.Person;
import javax.swing.*;
import karpachevski.model.*;

public class PersonField extends JPanel {
	private JTextField nameFld;
	private JTextField surnameFld;
	private JTextField middleNameFld;
	
	public PersonField() {

		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(3,3,3,3);
		c.gridx = 0;
		c.gridy = 0;
		
		setLayout(layout);
		
		String[] properties = {"Имя: ", "Фамилия: ", "Отчество: "};
		
		JLabel nameLbl = new JLabel(properties[0]);
		JLabel surnameLbl = new JLabel(properties[1]);
		JLabel middleNameLbl = new JLabel(properties[2]);

		nameFld = new  JTextField(10);
		surnameFld  = new  JTextField(10);
		middleNameFld  = new  JTextField(10);

		c.gridx = 0;
		c.gridy = 0;
		add(nameLbl,c);
		c.gridx = 1;
		c.gridy = 0;
		add(nameFld,c);
		c.gridx = 2;
		c.gridy = 0;
		add(surnameLbl,c);
		c.gridx = 3;
		c.gridy = 0;
		add(surnameFld,c);
		c.gridx = 4;
		c.gridy = 0;
		add(middleNameLbl,c);
		c.gridx = 5;
		c.gridy = 0;
		add(middleNameFld,c);



		
//		//c.gridwidth = 1;
//		//c.weightx = 1;
//	//	add(dayFld,c);
//	//	c.gridx = 1;
//		c.gridy = 0;
//		//c.gridwidth = 1;
//		//c.weightx = 1;
//		add(monthFld,c);
//		c.gridx = 2;
//		c.gridy = 0;
//		//c.gridwidth = 1;
//		//c.weightx = 1;
//		add(yearFld,c);
	}
	
	public void setPerson(Person person) {
		if (person != null) {
		nameFld.setText(person.getName());
		surnameFld.setText(person.getSurname());
		middleNameFld.setText(person.getMiddleName());
		}
	}
	
	public Person getPerson() {
		return new Person(nameFld.getText(), surnameFld.getText(), middleNameFld.getText());
	}
}
