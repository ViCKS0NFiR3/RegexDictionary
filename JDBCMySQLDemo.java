package regexDictionary;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.IOException;

public class JDBCMySQLDemo {
	public static void main(String[]args) {
		String url = "jdbc:mysql://localhost:3306/regexDictionary";
		String user = "root";
		String password = "root";
		String DRIVER_CLASS = "com.mysql.jdbc.Driver";
		String add = "insert into dictionary_results "
				+ " (head_word,pronounciation,definition)"
			 	+ " value ('test','test','test')";
		try {
			JDBCMySQLDemo demo = new JDBCMySQLDemo();
			Connection connection = DriverManager.getConnection(url,user,password);
			Statement statement = connection.createStatement();
			statement.executeUpdate(add);
			System.out.println("Insert Completed");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}