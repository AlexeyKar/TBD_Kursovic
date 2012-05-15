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
		Student stud = (Student) student;
		Connection connection = null;
		connection = ConnectionManager.getConnection();

		Statement statement = connection.createStatement();
		
		ResultSet results = statement.executeQuery("INSERT INTO student (name, surname, middlename, " +
				"status_id, dateOfAdmission, dateOfPlanDiss, " +
				"dateOfFactDiss, nameOfDiss, codeOfDiss, cleverpeople_id, " +
				"organization) values('" + stud.getName() + "', '" + stud.getSurname() + "', '" 
				+ stud.getMiddleName() + "', '" + stud.getStatus().getId() + "', '" 
				+ stud.getDateOfAdmission().getTime() + "', '" + stud.getDateOfPlanDiss().getTime() + "', '" 
				+ stud.getDateOfFactDiss().getTime() + "', '" + stud.getNameOfDiss() + "', '"
				+ stud.getCodeOfDiss() + "', '" + stud.getSupervisor().getId() + "', '"
				+ stud.getOrganization() + "') RETURNING student_id");
		results.next();
		stud.setId(results.getLong(1));
		statement.close();
		statement = null;		
	}

	@Override
	public void update(SuperEntity student) throws SQLException, ClassNotFoundException {
		Student stud = (Student) student;
		Connection connection = null;
		connection = ConnectionManager.getConnection();

		Statement statement = connection.createStatement();
		
		statement.executeUpdate("UPDATE student SET name = '" + stud.getName() 
				+ "', surname = '" + stud.getSurname()
				+ "', middleName = '" + stud.getMiddleName()
				+ "', status_id = '" + stud.getStatus().getId()
				+ "', dateOfAdmission = '" + stud.getDateOfAdmission().getTime()
				+ "', dateOfPlanDiss = '" + stud.getDateOfPlanDiss().getTime() 
				+ "', dateOfFactDiss = '" + stud.getDateOfFactDiss().getTime()
				+ "', nameOfDiss = '" + stud.getNameOfDiss()
				+ "', codeOfDiss = '" + stud.getCodeOfDiss()
				+ "', cleverpeople_id = '" + stud.getSupervisor().getId()
				+ "', organization = '" + stud.getOrganization()
				+ "' " 
				+ "WHERE student_id =" + stud.getId());
		
		statement.close();
		statement = null;
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
				"status.name, status.status_id, student.dateofadmission, " +
				"student.dateofplandiss, student.dateoffactdiss, " +
				"student.nameofdiss, student.codeofdiss, " +
				"cleverpeople.cleverpeople_id, cleverpeople.name, " +
				"cleverpeople.surname, cleverpeople.middlename, student.organization " +
				"FROM student, status, cleverpeople " +
				"WHERE student.cleverpeople_id = cleverpeople.cleverpeople_id " +
				"AND status.status_id = student.status_id;");
		ResultSetMetaData rsmd = results.getMetaData();
		while(results.next())
		{
			Student tmp = new Student();
			tmp.setId(results.getLong(1));
			tmp.setName(results.getString(2));
			tmp.setSurname(results.getString(3));
			tmp.setMiddleName(results.getString(4));
			//tmp.setDegree(results.getInt(5));
			
			Status status = new Status(results.getString(6));
			status.setId(results.getLong(7));
			tmp.setStatus(status);
			
			java.sql.Date date = results.getDate(8);
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(date);
			tmp.setDateOfAdmission(cal1);
			
			date = results.getDate(8);
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(date);
			tmp.setDateOfPlanDiss(cal2);
			
			date = results.getDate(10);
			Calendar cal3 = Calendar.getInstance();
			cal3.setTime(date);
			tmp.setDateOfFactDiss(cal3);
			
			tmp.setNameOfDiss(results.getString(11)); 
			tmp.setCodeOfDiss(results.getString(12)); // шифр диссертационного совета
			Person tmpPerson = new Person();
			tmpPerson.setId(results.getLong(13));
			tmpPerson.setName(results.getString(14)); // научный руководитель
			tmpPerson.setSurname(results.getString(15));
			tmpPerson.setMiddleName(results.getString(16));
			tmp.setSupervisor(tmpPerson);
			tmp.setOrganization(results.getString(17));; // ведущая организация
			
			students.add(tmp);
		}
		results.close();

		statement.close();
		statement = null;

		
		
		return students; 
	}

	@Override
	public Collection getListForEntity(SuperEntity obj) throws SQLException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
