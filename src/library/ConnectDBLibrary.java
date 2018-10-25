package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDBLibrary {
	private static final String URL="jdbc:mysql://localhost:3306/duandautien?useUnicode=yes&characterEncoding=UTF-8";
	private static final String USER = "root";							   
	private static final String PASSWORD ="";
	
	public static Connection getConnection() {
		Connection conn =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//tao chuoi ket noi
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(Statement st) {
		if(st!=null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(ResultSet st) {
		if(st!=null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Connection st) {
		if(st!=null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(PreparedStatement pst) {
		if(pst!=null) {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void close(Statement st,ResultSet rs,Connection conn) {
		close(rs);
		close(st);
		close(conn);
	}
	public static void close(PreparedStatement pst,ResultSet rs,Connection conn) {
		close(rs);
		close(pst);
		close(conn);
	}
}
