package karpachevski.interfaces.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import karpachevski.connection.ConnectionManager;
import karpachevski.interfaces.SuperEntityInterface;
import karpachevski.model.Document;
import karpachevski.model.SuperEntity;
import karpachevski.model.Task;
import karpachevski.interfaces.*;
import karpachevski.model.*;
import java.util.*;

public class TaskInterfaceImpl implements SuperEntityInterface {

	@Override
	public void add(SuperEntity task) throws SQLException, ClassNotFoundException {
		Task tsk = (Task) task;
		Connection connection = null;
		connection = ConnectionManager.getConnection();

		Statement statement = connection.createStatement();
		
		ResultSet results = statement.executeQuery("INSERT INTO task (title, dateofstartplan, dateofstartfact, " 
				+ "dateoffinishplan, dateoffinishfact, durration, complishion, document_id) values('" 
				+ tsk.getTitle() + "', '" + tsk.getDateOfStartPlan().getTime() + "', '" 
				+ tsk.getDateOfStartFact().getTime() + "', '" + tsk.getDateOfFinishPlan().getTime() + "', '" 
				+ tsk.getDateOfFinishFact().getTime() + "', '" + tsk.getDurration() + "', '" 
				+ tsk.getComplishion() + "', '" + tsk.getDocument().getId() 
				+ "') RETURNING task_id");
		results.next();
		task.setId(results.getLong(1));
		statement.close();
		statement = null;
	}

	@Override
	public void update(SuperEntity task) throws SQLException, ClassNotFoundException {
		Task tsk = (Task) task;
		Connection connection = null;
		connection = ConnectionManager.getConnection();

		Statement statement = connection.createStatement();
		
		statement.executeUpdate("UPDATE task SET title = '" + tsk.getTitle() 
				+ "', dateofstartplan = '" + tsk.getDateOfStartPlan().getTime() 
				+ "', dateofstartfact = '" + tsk.getDateOfStartFact().getTime()
				+ "', dateoffinishplan = '" + tsk.getDateOfFinishPlan().getTime() 
				+ "', dateoffinishfact = '" + tsk.getDateOfFinishFact().getTime()
				+ "', durration = '" + tsk.getDurration()
				+ "', complishion = '" + tsk.getComplishion()
				+ "', document_id = '" + tsk.getDocument().getId()
				+ "' " 
				+ "WHERE task_id =" + tsk.getId());

		statement.close();
		statement = null;
	}

	@Override
	public void delete(SuperEntity task) throws SQLException, ClassNotFoundException {
		Connection connection = null;
		connection = ConnectionManager.getConnection();

		Statement statement = connection.createStatement();
		
		statement.executeUpdate("DELETE FROM task WHERE task.task_id = " + task.getId());
		
		statement.close();
		statement = null;
	}

	@Override
	public Collection getAll() throws SQLException, ClassNotFoundException {
		Collection tasks = null;
		tasks = new ArrayList();

		Connection connection = null;
		connection = ConnectionManager.getConnection();

		Statement statement = connection.createStatement();

		ResultSet results = statement.executeQuery("SELECT task.task_id, task.title," +
				" task.dateofstartplan, task.dateofstartfact, task.dateoffinishplan," +
				"task.dateoffinishfact, task.durration, task.complishion, " +
				"document.document_id, document.name " +
				"FROM public.task, public.document " +
				"WHERE task.document_id = document.document_id order by task.task_id ");
		ResultSetMetaData rsmd = results.getMetaData();
		while(results.next())
		{
			Task tmp = new Task();
			tmp.setId(results.getLong(1));
			tmp.setTitle(results.getString(2));
			
			java.sql.Date date = results.getDate(3);
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(date);
			tmp.setDateOfStartPlan(cal1);
			
			date = results.getDate(4);
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(date);
			tmp.setDateOfStartFact(cal2);
			
			date = results.getDate(5);
			Calendar cal3 = Calendar.getInstance();
			cal3.setTime(date);
			tmp.setDateOfFinishPlan(cal3);
			
			date = results.getDate(6);
			Calendar cal4 = Calendar.getInstance();
			cal4.setTime(date);
			tmp.setDateOfFinishFact(cal4);
			
			tmp.setDurration(results.getInt(7)); // планируемая продолжительность
			tmp.setComplishion(results.getInt(8)); // процент выполения задачи
			Document tmpDoc = new Document();
			tmpDoc.setId(results.getLong(9));
			tmpDoc.setTitle(results.getString(10));
			tmp.setDocument(tmpDoc); // документ подтверждающий выполнение
			
			tasks.add(tmp);

		}
		results.close();

		statement.close();
		statement = null;

		
		return tasks; 
	}

	@Override
	public Collection getListForEntity(SuperEntity obj) throws SQLException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
