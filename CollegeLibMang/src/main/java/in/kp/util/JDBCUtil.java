package in.kp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;

public class JDBCUtil {
	
	public static Connection connection=null;
	public static PreparedStatement preparedStatement=null;
	public static String url="jdbc:mysql://127.0.0.1:3306/?user=root";
	
	public static String uname="root";
	public static String pwd="root";
	
	
	public static Connection getConnection() throws SQLException,ClassNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		if(connection==null) {
		connection=DriverManager.getConnection(url, uname, pwd);
		}
		return connection;
	}

}
