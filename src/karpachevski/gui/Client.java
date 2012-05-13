package karpachevski.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import karpachevski.model.Student;
import karpachevski.model.Task;
import karpachevski.model.Document;
import karpachevski.model.SuperEntity;
import karpachevski.tablemodel.DocumentTableModel;
import karpachevski.tablemodel.EntityTableModel;
import karpachevski.tablemodel.StudentTableModel;
import karpachevski.tablemodel.TaskTableModel;
import karpachevski.connection.ConnectionManager;
import karpachevski.factory.Factory;
import karpachevski.gui.editwindow.EditStudentWindow;
import karpachevski.gui.editwindow.EditTaskWindow;
import karpachevski.gui.editwindow.EditDocumentWindow;
import karpachevski.gui.editwindow.EditWindow;
import karpachevski.gui.loading.LoadingDialog;
import karpachevski.gui.login.LoginDialog;
import javax.swing.*;
import karpachevski.tablemodel.*;
import karpachevski.model.*;
import java.awt.*;

public class Client extends JFrame {
	
	private EntityTableModel studentTableModel;
	private EntityTableModel taskTableModel;
	private EntityTableModel documentTableModel;
	
	private JTable studentList;
	private JTable taskList;
	private JTable documentList;
	
	private static final int STUDENTS = 0;
	private static final int TASKS = 1;
	private static final int DOCUMENTS = 2;
	
	private int currentTab = 0;
	private SuperEntity selectedItem;
	
	private JButton addBtn;
	private JButton editBtn;
	private JButton deleteBtn;
	
	public Client() {
		super();
		setTitle("Клиент");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				actionExit();
			}
		});
		addComponentsToPane(getContentPane());
		updateButtons();
		pack();
		setMinimumSize(getSize());
	}
	
	private void addComponentsToPane(Container pane) {
		JTabbedPane tabbedPane = new JTabbedPane();
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("Файл");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		JMenuItem fileMenuExitItem = new JMenuItem("Выход", KeyEvent.VK_Q);
		fileMenuExitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionExit();
			}
		});
		fileMenu.add(fileMenuExitItem);
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
		
		tabbedPane.addTab("Аспиранты-Докторанты", makeStudentTab());
		tabbedPane.addTab("Задания", makeTaskTab());
		tabbedPane.addTab("Документы", makeDocumentTab());
		tabbedPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
		        JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
		        currentTab = sourceTabbedPane.getSelectedIndex();
		        updateButtons();
			}
		});
		tabbedPane.setSelectedIndex(currentTab);
		
		JPanel buttonPanel = new JPanel(new GridLayout(0,3,10,0));
		
		addBtn = new JButton("Добавить");
		addBtn.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				switch (currentTab) {
					case STUDENTS: actionAddStudent(); break;
					case TASKS: actionAddTask(); break;
					case DOCUMENTS: actionAddDocument(); break;
				}
			}
		});
		editBtn = new JButton("Редактировать");
		editBtn.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				switch (currentTab) {
					case STUDENTS: actionEditStudent(); break;
					case TASKS: actionEditTask(); break;
					case DOCUMENTS: actionEditDocument(); break;
				}
			}
		});
		deleteBtn = new JButton("Удалить");
		deleteBtn.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				switch (currentTab) {
					case STUDENTS: actionDeleteStudent(); break;
					case TASKS: actionDeleteTask(); break;
					case DOCUMENTS: actionDeleteDocument(); break;
				}
			}
		});

		buttonPanel.add(addBtn);
		buttonPanel.add(editBtn);
		buttonPanel.add(deleteBtn);
		
		pane.add(tabbedPane, BorderLayout.CENTER);
		pane.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	private JPanel makeStudentTab() {
		JPanel studentPanel = new JPanel(new BorderLayout());
		
		studentTableModel = new StudentTableModel();
		studentList = new JTable(studentTableModel);
		studentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		studentList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				updateButtons();
			}
			
		});

		JScrollPane studentView = new JScrollPane(studentList);
		studentPanel.add(studentView, BorderLayout.CENTER);
		
		return studentPanel;
	}
	
	private JPanel makeTaskTab() {
		JPanel taskPanel = new JPanel(new BorderLayout());
		
		taskTableModel = new TaskTableModel();
		taskList = new JTable(taskTableModel);
		taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		taskList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				updateButtons();
			}
			
		});
		
		JScrollPane taskView = new JScrollPane(taskList);
		taskPanel.add(taskView, BorderLayout.CENTER);

		return taskPanel;
	}
	
	private JPanel makeDocumentTab() {
		JPanel documentPanel = new JPanel(new BorderLayout());
		
		documentTableModel = new DocumentTableModel();
		documentList = new JTable(documentTableModel);
		documentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		documentList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				updateButtons();
			}
			
		});
		
		JScrollPane documentView = new JScrollPane(documentList);
		documentPanel.add(documentView, BorderLayout.CENTER);
	
		return documentPanel;
	}
	
	public void connection() {
		final LoginDialog login = new LoginDialog(this);
		login.setVisible(true);
		ConnectionManager.setProperties(login.getUsername(), login.getPassword(), login.getDBName());
		
		final LoadingDialog loading = new LoadingDialog(this);
		javax.swing.SwingUtilities.invokeLater(new Runnable() { 
			public void run() {
				loading.setVisible(true);
			}
		});
		
		try {
			getAllLists();	
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(this, e, "Не найден драйвер jdbc для postres", JOptionPane.ERROR_MESSAGE);
			actionExit();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e, "Ошибка создания БД", JOptionPane.ERROR_MESSAGE);
			actionExit();
		}
		
	
		loading.dispose();
	}
	
	private void actionAddStudent() {
		EditWindow editWindow = new EditStudentWindow(this, new Student(), taskTableModel.getEntities());
		editWindow.setVisible(true);
		if (!editWindow.isCanceled()) {
			Student newItem = (Student)editWindow.getObject();
			try {
				Factory.getInstance().getStudentInterface().add(newItem);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, e, "Ошибка доступа к бд, невозможно добавить аспиранта.", JOptionPane.ERROR_MESSAGE);
				return;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			studentTableModel.addEntity(newItem);
		}
		else 
			return;
	}
	
	private void actionEditStudent() {
		Student selectedStudent = (Student)studentTableModel.getEntity(studentList.getSelectedRow());
		if (selectedStudent != null) {
			EditWindow editWindow = new EditStudentWindow(this, selectedStudent, taskTableModel.getEntities());
			editWindow.setVisible(true);
			if (!editWindow.isCanceled()) {
				try {
					Factory.getInstance().getStudentInterface().update((Student)editWindow.getObject());
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(this, e, "Ошибка доступа к бд. Невозможно редактировать аспиранта.", JOptionPane.ERROR_MESSAGE);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				studentTableModel.updateEntity(studentList.getSelectedRow());
			}
			else 
				return;
		}
		else 
			return;
	}
	
	private void actionDeleteStudent() {
		Student selectedStudent = (Student) studentTableModel.getEntity(studentList.getSelectedRow());
		if (selectedStudent != null) {
			try {
				Factory.getInstance().getStudentInterface().delete(selectedStudent);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, e, "Ошибка доступа к бд. Невозможно удалить аспиранта.", JOptionPane.ERROR_MESSAGE);
				return;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			studentTableModel.deleteEntity(studentList.getSelectedRow());
		}
		else 
			return;
	}
	
	private void actionAddTask() {
		EditWindow editWindow = new EditTaskWindow(this, new Task(), documentTableModel.getEntities());
		editWindow.setVisible(true);
		if (!editWindow.isCanceled()) {
			Task newItem = (Task)editWindow.getObject();
			try {
				Factory.getInstance().getTaskInterface().add(newItem);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, e, "Ошибка доступа к бд. Невозможно добавить задание.", JOptionPane.ERROR_MESSAGE);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			taskTableModel.addEntity(newItem);
		}
		else 
			return;
	}
	
	private void actionEditTask() {
		Task selectedTask = (Task) taskTableModel.getEntity(taskList.getSelectedRow());
		if (selectedTask != null) {
			EditWindow editWindow = new EditTaskWindow(this, selectedTask, documentTableModel.getEntities());
			editWindow.setVisible(true);
			if (!editWindow.isCanceled()) {
				try {
					Factory.getInstance().getTaskInterface().update((Task)editWindow.getObject());
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(this, e, "Ошибка доступа к бд. Невозможно редактировать задание.", JOptionPane.ERROR_MESSAGE);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				taskTableModel.updateEntity(taskList.getSelectedRow());
			}
			else 
				return;
		}
		else 
			return;
	}
	
	private void actionDeleteTask() {
		Task selectedTask = (Task) taskTableModel.getEntity(taskList.getSelectedRow());
		if (selectedTask != null) {
			try {
				Factory.getInstance().getTaskInterface().delete(selectedTask);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, e, "Ошибка доступа к бд. Невозможно удалить задание.", JOptionPane.ERROR_MESSAGE);
				return;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			taskTableModel.deleteEntity(taskList.getSelectedRow());
		}
		else 
			return;
	}
	
	private void actionAddDocument() {
		EditWindow editWindow = new EditDocumentWindow(this, new Document());
		editWindow.setVisible(true);
		if (!editWindow.isCanceled()) {
			Document newItem = (Document)editWindow.getObject();
			try {
				Factory.getInstance().getDocumentInterface().add(newItem);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, e, "Ошибка доступа к бд. Невозможно добавить документ.", JOptionPane.ERROR_MESSAGE);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			documentTableModel.addEntity(newItem);
		}
		else 
			return;
	}
	
	private void actionEditDocument() {
		Document selectedDocument = (Document) documentTableModel.getEntity(documentList.getSelectedRow());
		if (selectedDocument != null) {
			EditWindow editWindow = new EditDocumentWindow(this, selectedDocument);
			editWindow.setVisible(true);
			if (!editWindow.isCanceled()) {
				try {
					Factory.getInstance().getDocumentInterface().update((Document)editWindow.getObject());
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(this, e, "Ошибка доступа к бд. Невозможно редактировать документ.", JOptionPane.ERROR_MESSAGE);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				documentTableModel.updateEntity(documentList.getSelectedRow());
			}
			else 
				return;
		}
		else 
			return;
	}
	
	private void actionDeleteDocument() {
		Document selectedDocument = (Document) documentTableModel.getEntity(documentList.getSelectedRow());
		if (selectedDocument != null) {
			try {
				Factory.getInstance().getDocumentInterface().delete(selectedDocument);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, e, "Ошибка доступа к бд. Невозможно удалить документ.", JOptionPane.ERROR_MESSAGE);
				return;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			documentTableModel.deleteEntity(documentList.getSelectedRow());
		}
		else 
			return;
	}
	
	private void getAllLists() throws SQLException, ClassNotFoundException {
		getStudentList();
		getTaskList();
		getDocumentList();
	}
	
	private void getStudentList() throws ClassNotFoundException, SQLException {
		Object[] studentArray = null;
		studentArray = Factory.getInstance().getStudentInterface().getAll().toArray();
		studentTableModel.setEntities(studentArray);
	}
	
	private void getTaskList() throws ClassNotFoundException, SQLException {
		Object[] taskArray = null;
		taskArray =  Factory.getInstance().getTaskInterface().getAll().toArray();
		taskTableModel.setEntities(taskArray);
	}
	
	private void getDocumentList() throws ClassNotFoundException, SQLException {
		Object[] documentArray = null;
		documentArray = Factory.getInstance().getDocumentInterface().getAll().toArray();
		documentTableModel.setEntities(documentArray);
	}
	
	//private void getStatusList() throws ClassNotFoundException, SQLException {
	//	Object[] statusArray = null;
	//	statusArray = Factory.getInstance().getStatusInterface().getAll().toArray();
	//}
	
	private void updateButtons() {
		selectedItem = null;
		switch (currentTab) {
			case STUDENTS: selectedItem = (SuperEntity)studentTableModel.getEntity(studentList.getSelectedRow()); break;
			case TASKS: selectedItem = (SuperEntity)taskTableModel.getEntity(taskList.getSelectedRow()); break;
			case DOCUMENTS: selectedItem = (SuperEntity)documentTableModel.getEntity(documentList.getSelectedRow()); break;
		}

		if (selectedItem != null) {
			if  (selectedItem.getId() != 1) 
			{
				addBtn.setEnabled(true);
				editBtn.setEnabled(true);
				deleteBtn.setEnabled(true);
			}
			else {
				addBtn.setEnabled(true);
				editBtn.setEnabled(true);
				deleteBtn.setEnabled(false);
			}
		}
		else {
			addBtn.setEnabled(true);
			editBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
		}
	}
	
	private void actionExit() {
		try {
			ConnectionManager.closeConnection();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e, "Ошибка при закрытие соединения с БД", JOptionPane.ERROR_MESSAGE);
		}
		System.exit(0);
	}
	
	public static void main(String[] args) {
		Client client = new Client();
		client.setVisible(true);
		client.connection();
	}
}
