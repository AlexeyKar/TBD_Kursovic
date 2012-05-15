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
import karpachevski.model.Person;
import karpachevski.model.SuperEntity;

public class PersonInterfaceImpl implements SuperEntityInterface {

	@Override
	public void add(SuperEntity person) throws SQLException, ClassNotFoundException {
		Person pers = (Person) person;
		Connection connection = null;
		connection = ConnectionManager.getConnection();

		Statement statement = connection.createStatement();
		
		ResultSet results = statement.executeQuery("INSERT INTO cleverpeople (name, surname, middlename) values('" 
				+ pers.getName() + "', '" + pers.getSurname() + "', '" + pers.getMiddleName() 
				+ "') RETURNING cleverpeople_id");
		results.next();
		pers.setId(results.getLong(1));
		statement.close();
		statement = null;
	}

	@Override
	public Collection getAll() throws SQLException, ClassNotFoundException {
		Collection persons = null;
		persons = new ArrayList();

		Connection connection = null;
		connection = ConnectionManager.getConnection();

		Statement statement = connection.createStatement();

		ResultSet results = statement.executeQuery("select * from cleverpeople order by cleverpeople_id");
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

	@Override
	public void update(SuperEntity obj) throws SQLException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(SuperEntity obj) throws SQLException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection getListForEntity(SuperEntity obj) throws SQLException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
