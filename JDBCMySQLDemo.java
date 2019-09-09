package regexDictionary;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.IOException;

public class JDBCMySQLDemo {
	String url;
	String user;
	String password;
	Connection connection;
	public JDBCMySQLDemo(){
		url = "jdbc:mysql://localhost:3306/regexDictionary";
		user = "root";
		password = "root";
		String DRIVER_CLASS = "com.mysql.jdbc.Driver";
		/*String add = "insert into dictionary_results "
				+ " (head_word,pronounciation,definition)"
			 	+ " value ('test','test','test')";
	 	
		try {
			JDBCMySQLDemo demo = new JDBCMySQLDemo();
			Statement statement = connection.createStatement();
			//statement.executeUpdate(add);
			//System.out.println("Insert Completed");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		*/
	}
	public void testConnection() throws SQLException{
		JDBCMySQLDemo demo = new JDBCMySQLDemo();
		Connection connection = DriverManager.getConnection(demo.url,demo.user,demo.password);
		try {
			Statement statement = connection.createStatement();
			//statement.executeUpdate(add);
			System.out.println("Connection Success");
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		/*finally {
			try {
				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	   */
	}
	
	public void getData() throws SQLException {
		JDBCMySQLDemo demo = new JDBCMySQLDemo();
		Connection connection = DriverManager.getConnection(demo.url,demo.user,demo.password);
		try {
			Statement statement = connection.createStatement();
			String query = "select * from dictionary_results";
			//statement.executeUpdate(add);
			 ResultSet rs = statement.executeQuery(query);
			 while (rs.next())
	            {
	                String id = rs.getString(1);
	                String headword = rs.getString(2);
	                String pronounciation = rs.getString(3);
	                String definition = rs.getString(4);
	                System.out.println(id);
	                System.out.println(headword);
	                System.out.println(pronounciation);
	                System.out.println(definition);
	            }
			//System.out.println("Connection Success");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}
	