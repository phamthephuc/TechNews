package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectDBLibrary;
import model.bean.Role;

public class RoleDao {
	private Connection conn;
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;
	
	public ArrayList<Role> getItems(){
		conn = ConnectDBLibrary.getConnection();
		ArrayList<Role> listCat = new ArrayList<>();
		String sql = "SELECT * FROM role ORDER BY id_role DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Role objCat = new Role(rs.getInt("id_role"), rs.getString("name_role"));
				listCat.add(objCat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(st, rs, conn);
		}
		
		return listCat;
	}
}
