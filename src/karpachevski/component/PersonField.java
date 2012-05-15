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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import karpachevski.model.Person;
import karpachevski.model.SuperEntity;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import karpachevski.model.*;
import karpachevski.tablemodel.PersonTableModel;
import karpachevski.tablemodel.TaskTableModel;

public class PersonField extends JPanel {
	
	private JComboBox fioFld;
	private PersonTableModel personTableModel;
	private JTable personList;
	private JButton delBtn;
	private Collection personArray;
	
	public PersonField(Boolean single, Collection personArray) {

		this.personArray = personArray;
		
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

		if (personArray != null) {
			fioFld = new JComboBox(personArray.toArray());
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
//					JLabel lbl = new JLabel(fioFld.getSelectedItem().toString());
//					JButton btn = new JButton("-");
//					c.gridx = 0;
//					c.gridy = 2;
//					c.gridwidth = 3;
//					add(lbl,c);
//					c.gridx = 4;
//					c.gridy = 2;
//					c.gridwidth = 1;
//					add(btn,c);
					personTableModel.addEntity(getPerson());	
//					try {
//							if (fioFld.getSelectedItem().getClass().equals(Class.forName("karpachevski.model.Person"))) {
//								personTableModel.addEntity((Person)fioFld.getSelectedItem());
//							}
//							else  {
//								String[] str = fioFld.getSelectedItem().toString().split(" ");
//							
//								personTableModel.addEntity(new Person(str[0],str[1],str[2]));
//							}
//						} catch (ClassNotFoundException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}

					//personTableModel.addEntity(new Person(fioFld.getSelectedItem().toString(),"", ""));
					//fioFld.setSelectedItem("");
					

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
			//personList.setSize(15, 15);

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
//					JLabel lbl = new JLabel(fioFld.getSelectedItem().toString());
//					JButton btn = new JButton("-");
//					c.gridx = 0;
//					c.gridy = 2;
//					c.gridwidth = 3;
//					add(lbl,c);
//					c.gridx = 4;
//					c.gridy = 2;
//					c.gridwidth = 1;
//					add(btn,c);
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
	
	private void updateButtons() {
		if (personTableModel.getEntity(personList.getSelectedRow()) == null) 
			delBtn.setEnabled(false);
		else
			delBtn.setEnabled(true);
	}
	
	public void setPerson(Person person) {
		if (person != null) {
//		nameFld.setText(person.getName());
//		surnameFld.setText(person.getSurname());
//		middleNameFld.setText(person.getMiddleName());
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

		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return new Person();		
	}
	
	public Collection getPersons() {
		return personTableModel.getEntities();
	}
}
