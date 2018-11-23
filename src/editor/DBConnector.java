package editor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBConnector {
	
	private static Connection connect = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	
	
	public DBConnector() {
		String host = "jdbc:mysql://localhost:3306/insultary?";
		String userName = "root";
		String password = "Impala1720!";
		try {
			connect = DriverManager.getConnection(host, userName, password);
		} catch (SQLException e) {
			System.out.println("Something went wrong\n" + e.getMessage());
		}	
	}
	
	public void addWordListsToDB(List<String> wordList0, List<String> wordList1, List<String> wordList2) {
		try {
			for (String word : wordList0) {
				preparedStatement = connect.prepareStatement("INSERT INTO insultary.words (word, position) VALUES (?, ?)");
				preparedStatement.setString(1, word); 
				preparedStatement.setInt(2, 1);
				preparedStatement.executeUpdate();
			}
			for (String word : wordList1) {
				preparedStatement = connect.prepareStatement("INSERT INTO insultary.words (word, position) VALUES (?, ?)");
				preparedStatement.setString(1, word); 
				preparedStatement.setInt(2, 2);
				preparedStatement.executeUpdate();
			}
			for (String word : wordList2) {
				preparedStatement = connect.prepareStatement("INSERT INTO insultary.words (word, position) VALUES (?, ?)");
				preparedStatement.setString(1, word); 
				preparedStatement.setInt(2, 3);
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("Something went wrong\n" + e.getMessage());
		}
	}

	public void saveInsult(String word1, String word2, String word3, String insult){
		try {
			preparedStatement = connect.prepareStatement("INSERT INTO insultary.insults (word1, word2, word3, insult_string) VALUES (?, ?, ?, ?)");
			preparedStatement.setString(1, word1);
			preparedStatement.setString(2, word2);
			preparedStatement.setString(3, word3);
			preparedStatement.setString(4, insult);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Something went wrong\n" + e.getMessage());
		}	
	}
	
	public List<String> getWordList(int position){
		try {
			preparedStatement = connect.prepareStatement("SELECT word FROM insultary.words WHERE position = ?");
			preparedStatement.setInt(1, position);
			resultSet = preparedStatement.executeQuery();
		
			List<String> wordList = new ArrayList<String>();
			String word = null;
			while (resultSet.next()) {
				word = resultSet.getString("word");
				wordList.add(word);
			}
			return wordList;
		} catch (SQLException e){
			System.out.println("Something went wrong\n" + e.getMessage());
		}
		
		return null;
	}
}
