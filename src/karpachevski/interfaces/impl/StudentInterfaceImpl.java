package karpachevski.interfaces.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import javax.swing.JOptionPane;

import karpachevski.connection.ConnectionManager;
import karpachevski.interfaces.SuperEntityInterface;
import karpachevski.model.Document;
import karpachevski.model.Person;
import karpachevski.model.Student;
import karpachevski.model.SuperEntity;
import karpachevski.model.Task;
import karpachevski.interfaces.*;
import karpachevski.model.*;
import java.util.*;

public class StudentInterfaceImpl implements SuperEntityInterface {

	@Override
	public void add(SuperEntity student) throws SQLException, ClassNotFoundException {
		
	}

	@Override
	public void update(SuperEntity student) throws SQLException, ClassNotFoundException {
		// В разработке
	}

	@Override
	public void delete(SuperEntity student) throws SQLException, ClassNotFoundException {
		Connection connection = null;
		connection = ConnectionManager.getConnection();

		Statement statement = connection.createStatement();
		
		statement.executeUpdate("DELETE FROM student WHERE student.student_id = " + student.getId());
		
		statement.close();
		statement = null;
	}

	@Override
	public Collection getAll() throws SQLException, ClassNotFoundException {
		Collection students = null;
		students = new ArrayList();

		Connection connection = null;
		connection = ConnectionManager.getConnection();

		Statement statement = connection.createStatement();

		ResultSet results = statement.executeQuery("SELECT student.student_id, student.name, " +
				"student.surname, student.middlename, student.degree, " +
				"status.name, student.dateofadmission, " +
				"student.dateofplandiss, student.dateoffactdiss, " +
				"student.nameofdiss, student.codeofdiss, " +
				"cleverpeople.cleverpeople_id, cleverpeople.name, " +
				"cleverpeople.surname, cleverpeople.middlename, student.organization " +
				"FROM student, status, cleverpeople " +
				"WHERE student.cleverpeople_id = cleverpeople.cleverpeople_id" +
				" AND status.status_id = student.status_id;");
		ResultSetMetaData rsmd = results.getMetaData();
		while(results.next())
		{
			Student tmp = new Student();
			tmp.setId(results.getLong(1));
			tmp.setName(results.getString(2));
			tmp.setSurname(results.getString(3));
			tmp.setMiddleName(results.getString(4));
			//tmp.setDegree(results.getInt(5));
			tmp.setStatus(results.getString(6));
			//private Calendar dateOfAdmission; // дата поступления
			//private Calendar dateOfPlanDiss; // планируемая дата защиты 
			//private Calendar dateOfFactDiss; // дата фактической защиты
			tmp.setNameOfDiss(results.getString(10)); 
			tmp.setCodeOfDiss(results.getString(11)); // шифр диссертационного совета
			Person tmpPerson = new Person();
			tmpPerson.setId(results.getLong(12));
			tmpPerson.setName(results.getString(13)); // научный руководитель
			tmpPerson.setSurname(results.getString(14));
			tmpPerson.setMiddleName(results.getString(15));
			tmp.setSupervisor(tmpPerson);
			tmp.setOrganization(results.getString(16));; // ведущая организация
			
			students.add(tmp);
		}
		results.close();

		statement.close();
		statement = null;

		
		
		return students; 
	}

}
