package karpachevski.gui.editwindow;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Collection;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import karpachevski.component.DateField;
import karpachevski.component.PersonField;
import karpachevski.model.*;
import java.util.*;
import javax.swing.*;
import karpachevski.component.*;
import java.awt.*;

public class EditStudentWindow extends EditWindow {

	private Student student;
	private Collection taskList;
	private Collection statusList;
	private Collection personList;
	
	private JTextField nameFld;
	private JTextField surnameFld;
	private JTextField middleNameFld;
	private JComboBox statusFld;
	private JComboBox degreeFld;
	private DateField dateOfAdmissionFld;
	private DateField dateOfPlanDissFld;
	private DateField dateOfFactDissFld;
	private TaskField taskFld;
	private JTextField nameOfDissFld;
	private JTextField codeOfDissFld;
	private PersonField supervisorFld;
	private PersonField opponentsFld;
	private JTextField organizationFld;

	
	public EditStudentWindow(JFrame parent, Student student, Collection taskList, Collection statusList, Collection personList) {
		super(parent);
		this.student = student;
		this.taskList = taskList;
		this.statusList = statusList;
		this.personList = personList;
		addComponentsToPane(getContentPane());
		fillFields();
		pack();
		setMinimumSize(getSize());
	}

	private void addComponentsToPane(Container pane) {
		JPanel lblAndFldPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(3,3,3,3);
		
		String[] properties = {"Имя: ", "Фамилия: ", "Отчество: ", "Статус: ", "Степень", "Дата поступления: ", 
				"План. срок защиты: ", "Факт. срок защиты: ",
				"Список задач: ", "Тема диссертации: ", "Шифр дисс. совета: ",
				"Научный руководитель: ", "Оппоненты: ", "Ведущая организация: "};
		String[] degreeValue = {"Аспирант", "Докторант"};

		for(int i = 0; i < properties.length; i++) {

			JLabel lbl = new JLabel(properties[i]);
			c.gridx = 0;
			c.gridy = i;
			c.weightx = 0;
			lblAndFldPanel.add(lbl,c);
		}
		
		nameFld = new JTextField(10);
		surnameFld = new JTextField(10);
		middleNameFld = new JTextField(10);
		statusFld = new JComboBox(statusList.toArray());
		statusFld.setEditable(true);
		degreeFld = new JComboBox(degreeValue);
		dateOfAdmissionFld = new DateField();
		dateOfPlanDissFld = new DateField();
		dateOfFactDissFld = new DateField();
		taskFld = new TaskField(taskList);
		nameOfDissFld = new JTextField();
		codeOfDissFld = new JTextField();
		supervisorFld = new PersonField(true, personList);
		opponentsFld = new PersonField(false, personList);
		organizationFld = new JTextField();
		
		
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1;
		lblAndFldPanel.add(nameFld,c);
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 1;
		lblAndFldPanel.add(surnameFld,c);
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 1;
		lblAndFldPanel.add(middleNameFld,c);
		c.gridx = 1;
		c.gridy = 3;
		c.weightx = 1;
		lblAndFldPanel.add(statusFld,c);
		c.gridx = 1;
		c.gridy = 4;
		c.weightx = 1;
		lblAndFldPanel.add(degreeFld,c);
		c.gridx = 1;
		c.gridy = 5;
		c.weightx = 1;
		lblAndFldPanel.add(dateOfAdmissionFld,c);
		c.gridx = 1;
		c.gridy = 6;
		c.weightx = 1;
		lblAndFldPanel.add(dateOfPlanDissFld,c);
		c.gridx = 1;
		c.gridy = 7;
		c.weightx = 1;
		lblAndFldPanel.add(dateOfFactDissFld,c);
		c.gridx = 1;
		c.gridy = 8;
		c.weightx = 1;
		lblAndFldPanel.add(taskFld,c);
		c.gridx = 1;
		c.gridy = 9;
		c.weightx = 1;
		lblAndFldPanel.add(nameOfDissFld,c);
		c.gridx = 1;
		c.gridy = 10;
		c.weightx = 1;
		lblAndFldPanel.add(codeOfDissFld,c);
		c.gridx = 1;
		c.gridy = 11;
		c.weightx = 1;
		lblAndFldPanel.add(supervisorFld,c);
		c.gridx = 1;
		c.gridy = 12;
		c.weightx = 1;
		lblAndFldPanel.add(opponentsFld,c);
		c.gridx = 1;
		c.gridy = 13;
		c.weightx = 1;
		lblAndFldPanel.add(organizationFld,c);
		c.gridx = 0;
		c.gridy = 14;
		c.gridwidth = 2;
		c.weightx = 1;
		lblAndFldPanel.add(new JSeparator(SwingConstants.HORIZONTAL),c);
		
		pane.add(lblAndFldPanel, BorderLayout.CENTER);
	}
	
	private void fillFields() {
		nameFld.setText(student.getName());
		surnameFld.setText(student.getSurname());
		middleNameFld.setText(student.getMiddleName());
		statusFld.setSelectedItem(student.getStatus());
		degreeFld.setSelectedIndex(student.getDegree());
		dateOfAdmissionFld.setDate(student.getDateOfAdmission());
		dateOfPlanDissFld.setDate(student.getDateOfPlanDiss());
		dateOfFactDissFld.setDate(student.getDateOfFactDiss());
		taskFld.setTasks(student.getTasks());              
		nameOfDissFld.setText(student.getNameOfDiss());
		codeOfDissFld.setText(student.getCodeOfDiss());
		supervisorFld.setPerson(student.getSupervisor());;
		opponentsFld.setPersons(student.getOpponents());         
		organizationFld.setText(student.getOrganization());
	}
	
	@Override
	public Student getObject() {
		student.setName(nameFld.getText());
		student.setSurname(surnameFld.getText());
		student.setMiddleName(middleNameFld.getText());
		
		try {
			if (statusFld.getSelectedItem().getClass().equals(Class.forName("karpachevski.model.Status"))) {
				student.setStatus((Status)statusFld.getSelectedItem());
			} 
			else {
				student.setStatus(new Status(statusFld.getSelectedItem().toString()));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		student.setDegree(degreeFld.getSelectedIndex());
		student.setDateOfAdmission(dateOfAdmissionFld.getDate());
		student.setDateOfPlanDiss(dateOfPlanDissFld.getDate());
		student.setDateOfFactDiss(dateOfFactDissFld.getDate());
		student.setTasks(taskFld.getTasks());              
		student.setNameOfDiss(nameOfDissFld.getText());
		student.setCodeOfDiss(codeOfDissFld.getText());
		student.setSupervisor(supervisorFld.getPerson());
		student.setOpponents(opponentsFld.getPersons());          
		student.setOrganization(organizationFld.getText());
		
		return student;
	}

	@Override
	protected void actionOk() {
		if (nameFld.getText().trim().length() < 1 
				|| surnameFld.getText().trim().length() < 1) {
			JOptionPane.showMessageDialog(this, "Введите имя и фамилию аспиранта", "Заполните поля!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		dispose();
	}
}
