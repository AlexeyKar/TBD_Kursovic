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
import karpachevski.model.Document;
import karpachevski.model.Task;
import karpachevski.model.*;
import java.util.*;
import javax.swing.*;
import karpachevski.component.*;
import java.awt.*;

public class EditTaskWindow extends EditWindow {

	private Task task;
	private Collection documentList;
	
	private JTextField titleFld; // поле ввода "название задания" 
	private DateField dateOfStartPlanFld; // поле ввода "планируемое время окончания задачи"
	private DateField dateOfStartFactFld; // поле ввода "фактическое время начала задачи"
	private DateField dateOfFinishPlanFld; // поле ввода "планируемое время окончания задачи"
	private DateField dateOfFinishFactFld; // поле ввода "фактическое время окончания задачи"
	private JTextField durrationFld; // поле ввода "планируемая продолжительность"
	private JTextField complishionFld; // поле ввода "процент выполения задачи"
	private JComboBox documentFld; // поле ввода "документ подтверждающий выполнение"
	private JComboBox tasksToDoFld; // поле ввода "список предварительных задач"
	
	public EditTaskWindow(JFrame parent, Task task, Collection documentList) {
		super(parent);
		this.task = task;
		this.documentList = documentList;
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
		
		String[] properties = {"Название: ", "План. дата начала: ", "Факт. дата начала: ", 
				"План. дата окончания: ", "Факт. дата окончания: ", 
				"Планируемая продолжительность: ", "Процент выполнения: ", "Документ подтверждающий выполнение: ",
				"Список предварительных задач"};
		

		for(int i = 0; i < properties.length; i++) {

			JLabel lbl = new JLabel(properties[i]);
			c.gridx = 0;
			c.gridy = i;
			c.weightx = 0;
			lblAndFldPanel.add(lbl,c);
		}
		
		titleFld = new JTextField(); 
		dateOfStartPlanFld = new DateField();
		dateOfStartFactFld = new DateField();
		dateOfFinishPlanFld = new DateField();
		dateOfFinishFactFld = new DateField();
		durrationFld = new JTextField();
		complishionFld = new JTextField();
		documentFld = new JComboBox(documentList.toArray());
		tasksToDoFld = new JComboBox();
		
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1;
		lblAndFldPanel.add(titleFld,c);
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 1;
		lblAndFldPanel.add(dateOfStartPlanFld,c);
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 1;
		lblAndFldPanel.add(dateOfStartFactFld,c);
		c.gridx = 1;
		c.gridy = 3;
		c.weightx = 1;
		lblAndFldPanel.add(dateOfFinishPlanFld,c);
		c.gridx = 1;
		c.gridy = 4;
		c.weightx = 1;
		lblAndFldPanel.add(dateOfFinishFactFld,c);
		c.gridx = 1;
		c.gridy = 5;
		c.weightx = 1;
		lblAndFldPanel.add(durrationFld,c);
		c.gridx = 1;
		c.gridy = 6;
		c.weightx = 1;
		lblAndFldPanel.add(complishionFld,c);
		c.gridx = 1;
		c.gridy = 7;
		c.weightx = 1;
		lblAndFldPanel.add(documentFld,c);
		c.gridx = 1;
		c.gridy = 8;
		c.weightx = 1;
		lblAndFldPanel.add(tasksToDoFld,c);
		c.gridx = 0;
		c.gridy = 9;
		c.gridwidth = 2;
		c.weightx = 1;
		lblAndFldPanel.add(new JSeparator(SwingConstants.HORIZONTAL),c);
		
		pane.add(lblAndFldPanel, BorderLayout.CENTER);
	}
	
	private void fillFields() {
		titleFld.setText(task.getTitle()); 
		dateOfStartPlanFld.setDate(task.getDateOfStartPlan());
		dateOfStartFactFld.setDate(task.getDateOfStartFact());
		dateOfFinishPlanFld.setDate(task.getDateOfFinishPlan());
		dateOfFinishFactFld.setDate(task.getDateOfFinishFact());
		durrationFld.setText(String.valueOf(task.getDurration()));
		complishionFld.setText(String.valueOf(task.getComplishion()));
		documentFld.setSelectedItem(task.getDocument());
		//tasksToDoFld.setSelectedItem(task.getTasksToDo());
	}
	
	@Override
	public Task getObject() {
		task.setTitle(titleFld.getText()); 
		task.setDateOfStartPlan(dateOfStartPlanFld.getDate());
		task.setDateOfStartFact(dateOfStartFactFld.getDate());
		task.setDateOfFinishPlan(dateOfFinishPlanFld.getDate());
		task.setDateOfFinishFact(dateOfFinishFactFld.getDate());
		task.setDurration(Integer.valueOf(durrationFld.getText()));
		task.setComplishion(Integer.valueOf(complishionFld.getText()));
		task.setDocument((Document)documentFld.getSelectedItem());
		//task.setTasksToDo(tasksToDoFld.getSelectedItem()));
		
		return task;
	}

	@Override
	protected void actionOk() {
		if (titleFld.getText().trim().length() < 1) {
			JOptionPane.showMessageDialog(this, "Введите название задания", "Заполните поля!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			Integer.valueOf(durrationFld.getText());
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Продолжительность - это целочисленное значение", "Неверное значение!", JOptionPane.ERROR_MESSAGE);
			durrationFld.requestFocusInWindow();
			return;
		}
		try {
			Integer.valueOf(complishionFld.getText());
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Процент выполнения - это целочисленное значение", "Неверное значение!", JOptionPane.ERROR_MESSAGE);
			complishionFld.requestFocusInWindow();
			return;
		}
		dispose();
	}
}
