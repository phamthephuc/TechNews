package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectDBLibrary;
import model.bean.Category;
import model.bean.TrangThai;

public class TrangThaiDao {
	private Connection conn;
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;
	public ArrayList<TrangThai> getItems()
	{
		conn = ConnectDBLibrary.getConnection();
		ArrayList<TrangThai> listTT = new ArrayList<>();
		String sql = "SELECT * FROM trangthai ORDER BY status";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				TrangThai objTT = new TrangThai(rs.getInt("status"), rs.getString("name_status"));
				listTT.add(objTT);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(st, rs, conn);
		}
		
		return listTT;
	}
}
