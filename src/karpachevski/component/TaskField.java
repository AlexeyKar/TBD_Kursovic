package karpachevski.component;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import karpachevski.model.Task;
import karpachevski.tablemodel.TaskMiniTableModel;

public class TaskField extends JPanel {
	private JComboBox taskFld;
	private TaskMiniTableModel taskTableModel;
	private JTable taskList;
	private JButton delBtn;
	private Collection taskArr;
	
	public TaskField(Collection taskArray) {

		this.taskArr = taskArray;
		
		GridBagLayout layout = new GridBagLayout();
		final GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(3,3,3,3);
		c.gridx = 0;
		c.gridy = 0;
		
		setLayout(layout);
		
		String[] properties = {"Предварительные задачи"};
		
		JLabel titleLbl = new JLabel(properties[0]);


		if (taskArr != null) {
			taskFld = new JComboBox(taskArr.toArray());
		}
			else
				taskFld = new JComboBox();
		taskFld.setEditable(false);
		

		c.gridx = 1;
		c.gridy = 0;
		add(titleLbl,c);

		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		add(taskFld,c);
		
		JButton addBtn = new JButton("+");
		addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (getTask() != null) {
					if (!taskTableModel.containsEntity(getTask()))
						taskTableModel.addEntity(getTask());
					else 
						return;
				}
			}
			
		});
			
		c.gridx = 4;
		c.gridy = 1;
		c.gridwidth = 1;

		add(addBtn,c);
			
		taskTableModel = new TaskMiniTableModel();
		taskList = new JTable(taskTableModel);
		taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		taskList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				updateButtons();
			}
		
		});
			
		JScrollPane personView = new JScrollPane(taskList);

		taskList.setPreferredScrollableViewportSize(
		         new Dimension(
		        	taskList.getPreferredScrollableViewportSize().width,
			             3 * taskList.getRowHeight()
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
					taskTableModel.deleteEntity(taskList.getSelectedRow());
				}
			});

			c.gridx = 4;
			c.gridy = 2;
			c.gridwidth = 1;
			c.gridheight = 1;
			

			add(delBtn,c);
			updateButtons();

	}
	
	private void updateButtons() {
		if (taskTableModel.getEntity(taskList.getSelectedRow()) == null) 
			delBtn.setEnabled(false);
		else
			delBtn.setEnabled(true);
	}
	
	public void setTasks(Collection tasks) {
		if (tasks != null) {
			taskTableModel.setEntities(tasks.toArray());

		}
	}
	
	public Task getTask() {
		try {
			if (taskFld.getSelectedItem() != null) { 
				if (taskFld.getSelectedItem().getClass().equals(Class.forName("karpachevski.model.Task"))) {
					return (Task)taskFld.getSelectedItem();
				}
			}

		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;		
	}
	
	public Collection getTasks() {
		return taskTableModel.getEntities();
	}
	
}
