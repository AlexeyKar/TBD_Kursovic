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
		// В разработке
	}

	@Override
	public void update(SuperEntity task) throws SQLException, ClassNotFoundException {
		// В разработке
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
				"WHERE task.document_id = document.document_id;");
		ResultSetMetaData rsmd = results.getMetaData();
		while(results.next())
		{
			Task tmp = new Task();
			tmp.setId(results.getLong(1));
			tmp.setTitle(results.getString(2));
			//tmp. dateOfStartPlan; // планируемое время окончания задачи
			//private Calendar dateOfStartFact; // фактическое время начала задачи
			//private Calendar dateOfFinishPlan; // планируемое время окончания задачи
			//private Calendar dateOfFinishFact; // фактическое время окончания задачи
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
	
}
