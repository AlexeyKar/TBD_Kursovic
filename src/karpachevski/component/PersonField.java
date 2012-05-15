package karpachevski.component;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import karpachevski.model.Person;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import karpachevski.tablemodel.PersonTableModel;

public class PersonField extends JPanel {
	
	private JComboBox fioFld;
	private PersonTableModel personTableModel;
	private JTable personList;
	private JButton delBtn;
	private Collection personArr;
	
	public PersonField(Boolean single, Collection personArray) {

		this.personArr = personArray;
		
		GridBagLayout layout = new GridBagLayout();
		final GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(3,3,3,3);
		c.gridx = 0;
		c.gridy = 0;
		
		setLayout(layout);
		
		String[] properties = {"Имя ", "Фамилия ", "Отчество "};
		
		JLabel nameLbl = new JLabel(properties[0]);
		JLabel surnameLbl = new JLabel(properties[1]);
		JLabel middleNameLbl = new JLabel(properties[2]);

		if (personArr != null) {
			fioFld = new JComboBox(personArr.toArray());
		}
			else
				fioFld = new JComboBox();
		
		fioFld.setEditable(true);

		c.gridx = 0;
		c.gridy = 0;
		add(nameLbl,c);

		c.gridx = 1;
		c.gridy = 0;
		add(surnameLbl,c);

		c.gridx = 2;
		c.gridy = 0;
		add(middleNameLbl,c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		add(fioFld,c);
		
		if (!single) {

			JButton addBtn = new JButton("+");
			addBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (getPerson() != null) { 
						if (!personTableModel.containsEntity(getPerson())) 
							personTableModel.addEntity(getPerson());
						else 
							return;
					}
				}
				
			});
			
			c.gridx = 4;
			c.gridy = 1;
			c.gridwidth = 1;

			add(addBtn,c);
			
			personTableModel = new PersonTableModel();
			personList = new JTable(personTableModel);
			personList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			personList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					updateButtons();
				}
				
			});
			
			JScrollPane personView = new JScrollPane(personList);

			personList.setPreferredScrollableViewportSize(
			         new Dimension(
			        	personList.getPreferredScrollableViewportSize().width,

			             3 * personList.getRowHeight()
			     ));
			
			c.gridx = 0;
			c.gridy = 2;
			c.gridwidth = 3;
			c.gridheight = 3;
			

			add(personView,c);
			
			delBtn = new JButton("-");
			delBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					personTableModel.deleteEntity(personList.getSelectedRow());

				}
				
			});
			c.gridx = 4;
			c.gridy = 2;
			c.gridwidth = 1;
			c.gridheight = 1;
			

			add(delBtn,c);
			updateButtons();
		}



		

	}
	
	private void updateButtons() {
		if (personTableModel.getEntity(personList.getSelectedRow()) == null) 
			delBtn.setEnabled(false);
		else
			delBtn.setEnabled(true);
	}
	
	public void setPerson(Person person) {
		if (person != null) {
			fioFld.setSelectedItem(person);
		}
	}
	
	public void setPersons(Collection persons) {
		if (persons != null) {
			personTableModel.setEntities(persons.toArray());
		}
	}
	
	public Person getPerson() {
		try {
			if (fioFld.getSelectedItem() != null ) {
				if (fioFld.getSelectedItem().getClass().equals(Class.forName("karpachevski.model.Person"))) {
					return (Person)fioFld.getSelectedItem();
				}
				else  {
					String[] str = fioFld.getSelectedItem().toString().split(" ");
					if (str.length > 2) 
						return new Person(str[0],str[1],str[2]);
					else
						if (str.length == 2) 
							return new Person(str[0],str[1], "");
						else 
							if (str.length == 1) 
							return new Person(str[0], "", "");
				}
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;		
	}
	
	public Collection getPersons() {
		return personTableModel.getEntities();
	}
}
