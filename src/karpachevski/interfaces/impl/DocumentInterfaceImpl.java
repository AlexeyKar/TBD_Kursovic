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
		// В разработке
	}

	@Override
	public void update(SuperEntity document) throws SQLException, ClassNotFoundException {
		// В разработке
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

		ResultSet results = statement.executeQuery("select * from document");
		ResultSetMetaData rsmd = results.getMetaData();
		while(results.next())
		{
			Document tmp = new Document();
			tmp.setId(results.getLong(1));
			tmp.setTitle(results.getString(2));
			//tmp.setDateOfSupply(results.getDate(3));
			documents.add(tmp);
		}
		results.close();

		statement.close();
		statement = null;

		return documents; 
	}

}
