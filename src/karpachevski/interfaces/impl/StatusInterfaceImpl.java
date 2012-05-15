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
import karpachevski.model.Status;
import karpachevski.model.SuperEntity;

public class StatusInterfaceImpl implements SuperEntityInterface {

	@Override
	public void add(SuperEntity status) throws SQLException, ClassNotFoundException {
		Status sts = (Status) status;
		Connection connection = null;
		connection = ConnectionManager.getConnection();

		Statement statement = connection.createStatement();
		
		ResultSet results = statement.executeQuery("INSERT INTO status (name) values('" 
				+ sts.getName() 
				+ "') RETURNING status_id");
		results.next();
		sts.setId(results.getLong(1));
		statement.close();
		statement = null;
	}

	@Override
	public Collection getAll() throws SQLException, ClassNotFoundException {
		Collection statuses = null;
		statuses = new ArrayList();

		Connection connection = null;
		connection = ConnectionManager.getConnection();

		Statement statement = connection.createStatement();

		ResultSet results = statement.executeQuery("select * from status order by status_id");
		ResultSetMetaData rsmd = results.getMetaData();
		while(results.next())
		{
			Status tmp = new Status();
			tmp.setId(results.getLong(1));
			tmp.setName(results.getString(2));

			statuses.add(tmp);
		}
		results.close();

		statement.close();
		statement = null;

		return statuses; 
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
