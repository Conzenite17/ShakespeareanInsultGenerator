package editor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import com.mysql.jdbc.Statement;

public class DBConnector {
	
	private static Connection connect = null;
	private static Statement statement = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	
	
	public DBConnector() {
		String host = "jdbc:mysql://localhost:3306/insultary?";
		String userName = "root";
		String password = "Impala1720!";
		try {
			connect = DriverManager.getConnection(host, userName, password);
			System.out.println(connect.toString());
		} catch (SQLException e) {
			System.out.println("Something went wrong\n" + e.getMessage());
		}	
	}
	
	public void addWordListsToDB(List<String> wordList0, List<String> wordList1, List<String> wordList2) {
		try {
			for (String word : wordList0) {
				preparedStatement = connect.prepareStatement("INSERT INTO insultary.words (word, position) values (?, ?)");
				preparedStatement.setString(1, word); 
				preparedStatement.setInt(2, 1);
				preparedStatement.executeUpdate();
			}
			for (String word : wordList1) {
				preparedStatement = connect.prepareStatement("INSERT INTO insultary.words (word, position) values (?, ?)");
				preparedStatement.setString(1, word); 
				preparedStatement.setInt(2, 2);
				preparedStatement.executeUpdate();
			}
			for (String word : wordList1) {
				preparedStatement = connect.prepareStatement("INSERT INTO insultary.words (word, position) values (?, ?)");
				preparedStatement.setString(1, word); 
				preparedStatement.setInt(2, 3);
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("Something went wrong\n" + e.getMessage());
		}
		
	}
	
	public Connection getCon() {
		return connect;
	}
	
}
