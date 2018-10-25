package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import library.ConnectDBLibrary;
import model.bean.Category;
import model.bean.Message;
import model.bean.User;

public class MessageDao {
	private Connection conn;
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;
	
	public int countNewMessageByType(int type) {
		int ketqua = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT COUNT(id_message) AS soLuong FROM message WHERE id_type = ? AND seen = 0";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, type);
			rs = pst.executeQuery();
			if(rs.next())
			{
				ketqua = rs.getInt("soLuong");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return ketqua;
	}
	
	public int addItem(String email,int type)
	{
		conn = ConnectDBLibrary.getConnection();
		String sql = "INSERT INTO message(email,id_type,seen) VALUES(?,?,0) ";
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, email);
			pst.setInt(2, type);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return result;
	}
	
	
	public ArrayList<Message> getUnseenByType(int type) {
		conn = ConnectDBLibrary.getConnection();
		ArrayList<Message> listMessage = new ArrayList<>();
		String sql = "SELECT * FROM message WHERE id_type = ? ORDER BY seen ASC,id_message DESC LIMIT 10";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, type);
			rs = pst.executeQuery();
			while (rs.next()) {
				Message msg = new Message(rs.getInt("id_message"), rs.getInt("id_type"), rs.getString("email"),rs.getInt("seen"));
				listMessage.add(msg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(st, rs, conn);
		}
		return listMessage;
	}
	
	
	public ArrayList<Message> getSubcribe(int type) {
		conn = ConnectDBLibrary.getConnection();
		ArrayList<Message> listMessage = new ArrayList<>();
		String sql = "SELECT * FROM message WHERE id_type = ? ORDER BY seen ASC,id_message DESC";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, type);
			rs = pst.executeQuery();
			while (rs.next()) {
				Message msg = new Message(rs.getInt("id_message"), rs.getInt("id_type"), rs.getString("email"),rs.getInt("seen"));
				listMessage.add(msg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(st, rs, conn);
		}
		return listMessage;
	}
	
	
	public Message getItem(int id) {
		conn = ConnectDBLibrary.getConnection();
		Message msg = null;
		String sql = "SELECT * FROM message WHERE id_message = ? ";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
			msg = new Message(rs.getInt("id_message"), rs.getInt("id_type"), rs.getString("email"),rs.getInt("seen"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(st, rs, conn);
		}
		return msg;
	}
	
	public int setSeenForSubcribe()
	{
		conn = ConnectDBLibrary.getConnection();
		String sql = "UPDATE message SET seen = 1 WHERE id_type = 1 ";
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return result;
	}
	
	public int setSeenById(int id)
	{
		conn = ConnectDBLibrary.getConnection();
		String sql = "UPDATE message SET seen = 1 WHERE id_message = ? ";
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return result;
	}

	public Message getItemSubcribeByEmail(String email) {
		conn = ConnectDBLibrary.getConnection();
		Message msg = null;
		String sql = "SELECT * FROM message WHERE email = ? AND id_type = 1 ";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, email);
			rs = pst.executeQuery();
			if (rs.next()) {
			msg = new Message(rs.getInt("id_message"), rs.getInt("id_type"), rs.getString("email"),rs.getInt("seen"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(st, rs, conn);
		}
		return msg;
	}
	
	public Message getItemForgotByEmail(String email) {
		conn = ConnectDBLibrary.getConnection();
		Message msg = null;
		String sql = "SELECT * FROM message WHERE email = ? AND id_type = 2 AND seen = 0 ";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, email);
			rs = pst.executeQuery();
			if (rs.next()) {
			msg = new Message(rs.getInt("id_message"), rs.getInt("id_type"), rs.getString("email"),rs.getInt("seen"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(st, rs, conn);
		}
		return msg;
	}
	
	
	
}
