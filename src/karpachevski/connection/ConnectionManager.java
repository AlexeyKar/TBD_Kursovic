package karpachevski.connection;

import java.sql.*;


public class ConnectionManager {
	private ConnectionManager(){};
	private static boolean driverLoad = false;
	private static final String pgDriver = "org.postgresql.Driver";
	private static final String pgUrl = "jdbc:postgresql://localhost:5432/";
	private static String usr = "postgres";
	private static String psw = "111";
	private static String dataBaseName = "DissertationDB";  // Название базы данных
	
	
	private static final String studentTable = "student";  // Название табилцы аспиратнов и докторантов
	private static final String taskTable = "task";  // Название таблицы  с заданиями 
	private static final String documentTable = "document";  // Название таблицы с документами
	private static final String statusTable = "status";  // Название таблицы со статусами

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if(!driverLoad) {
			Class.forName(pgDriver);
			driverLoad=true;
		}
		return DriverManager.getConnection(pgUrl+dataBaseName, usr, psw);
	}
	
	public static void closeConnection() throws SQLException, ClassNotFoundException {
		Connection connection = getConnection();
		connection.close();
	}
	
	public static void setProperties(String username, String password, String name) {
		usr = username;
		psw = password;
		dataBaseName = name;
	}
	
	public static void createDataBase() throws ClassNotFoundException, SQLException {
//		Connection connection = getConnection();
//		Statement statement = connection.createStatement();
//		try {
			
	          // ResultSet results = statement.executeQuery("select * from status");
	          // ResultSet results = statement.executeQuery("select document.document_id, document.name,(select (cleverpeople.name, cleverpeople.surname, cleverpeople.middlename) as fio from cleverpeople where cleverpeople_id = documents_cleverpeople.cleverpeople_id) from document right join documents_cleverpeople on(document.document_id = documents_cleverpeople.document_id)");
//	           ResultSetMetaData rsmd = results.getMetaData();
//
//	            while(results.next())
//	            {
//	                int id = results.getInt(1);
//	                String name = results.getString(2);
//	                System.out.println(id + " " + name);
//	            }
//	            results.close();

			
	//		statement.close();
	//		statement = null;
		
	//	} catch (SQLException e) {
			System.out.println("Запуск");
	//	}
	}

}
