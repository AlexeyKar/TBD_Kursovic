package karpachevski.interfaces.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import karpachevski.connection.ConnectionManager;
import karpachevski.interfaces.SuperEntityInterface;
import karpachevski.model.SuperEntity;
import karpachevski.interfaces.*;
import karpachevski.model.*;
import java.util.*;

public class DocumentInterfaceImpl implements SuperEntityInterface {

	@Override
	public void add(SuperEntity document) throws SQLException, ClassNotFoundException {
		Document doc = (Document) document;
		Connection connection = null;
		connection = ConnectionManager.getConnection();

		Statement statement = connection.createStatement();
		
		ResultSet results = statement.executeQuery("INSERT INTO document (name, dateOfSupply) values('" 
				+ doc.getTitle() + "', '" + doc.getDateOfSupply().getTime() 
				+ "') RETURNING document_id");
		results.next();
		document.setId(results.getLong(1));
		
		Collection persons = doc.getListOfSign();
		for (Object item : persons) {
			Person person = (Person) item;
			if (person.getId() == -1) {
				results = statement.executeQuery("INSERT INTO cleverpeople (name, surname, middlename) values('" 
						+ person.getName() + "', '" + person.getSurname() + "', '" + person.getMiddleName()
						+ "') RETURNING cleverpeople_id ");
				results.next();
				person.setId(results.getLong(1));
			}
			
			statement.executeUpdate("INSERT INTO document_cleverpeople values('" 
					+ document.getId() + "', '" + person.getId() 
					+ "')");
			
		}

		results.close();
		
		statement.close();
		statement = null;
	}

	@Override
	public void update(SuperEntity document) throws SQLException, ClassNotFoundException {
		Document doc = (Document) document;
		Connection connection = null;
		connection = ConnectionManager.getConnection();

		Statement statement = connection.createStatement();
		
		statement.executeUpdate("UPDATE document SET name = '" + doc.getTitle() + "', dateOfSupply = '" 
				+ doc.getDateOfSupply().getTime() 
				+ "' " + "WHERE  document_id = " + doc.getId());

		statement.close();
		statement = null;
	}

	@Override
	public void delete(SuperEntity document) throws SQLException, ClassNotFoundException {
		Connection connection = null;
		connection = ConnectionManager.getConnection();

		Statement statement = connection.createStatement();
		
		statement.executeUpdate("DELETE FROM document WHERE document.document_id = " + document.getId());
		
		statement.close();
		statement = null;
	}

	@Override
	public Collection getAll() throws SQLException, ClassNotFoundException {
		Collection documents = null;
		documents = new ArrayList();

		Connection connection = null;
		connection = ConnectionManager.getConnection();

		Statement statement = connection.createStatement();

		ResultSet results = statement.executeQuery("select * from document order by document_id");
		ResultSetMetaData rsmd = results.getMetaData();
		while(results.next())
		{
			Document tmp = new Document();
			tmp.setId(results.getLong(1));
			tmp.setTitle(results.getString(2));
			
			java.sql.Date date = (java.sql.Date) results.getDate(3);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			tmp.setDateOfSupply(calendar);

			documents.add(tmp);
		}
		results.close();

		statement.close();
		statement = null;

		return documents; 
	}

	public Collection getListForEntity(SuperEntity document) throws ClassNotFoundException, SQLException {
		Collection persons = null;
		persons = new ArrayList();
		
		Connection connection = null;
		connection = ConnectionManager.getConnection();

		Statement statement = connection.createStatement();
		
		ResultSet results = statement.executeQuery("select cleverpeople.cleverpeople_id, cleverpeople.name, " +
						"cleverpeople.surname, cleverpeople.middlename " +
						"FROM cleverpeople RIGHT JOIN document_cleverpeople " +
						"ON document_cleverpeople.cleverpeople_id = cleverpeople.cleverpeople_id " +
						"WHERE document_id = " + document.getId());
		ResultSetMetaData rsmd = results.getMetaData();
		while(results.next())
		{
			Person tmp = new Person();
			tmp.setId(results.getLong(1));
			tmp.setName(results.getString(2));
			tmp.setSurname(results.getString(3));
			tmp.setMiddleName(results.getString(4));

			persons.add(tmp);
		}
		results.close();

		statement.close();
		statement = null;
		
		return persons;
	}

}
