package regexDictionary;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement; 
//import regexDictionary.JDBCMySQLDemo;

public class fetchData {
	private void getData() throws SQLException {
		JDBCMySQLDemo demo = new JDBCMySQLDemo();
		demo.getData();
	}
	public static void main (String[]args) throws SQLException {
		fetchData fetch = new fetchData();
		fetch.getData();
	}
}
