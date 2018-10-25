package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import library.ConnectDBLibrary;
//import model.bean.Define;
import model.bean.User;

public class UserDao {
	private Connection conn;
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;

	public ArrayList<User> getItems() {
		conn = ConnectDBLibrary.getConnection();
		ArrayList<User> listUser = new ArrayList<>();
		String sql = "SELECT * FROM users ORDER BY id_user DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				User objUser = new User(rs.getInt("id_user"), rs.getString("username"), rs.getString("password"), rs.getString("email"),rs.getInt("id_role"), rs.getInt("status"),new Date(rs.getTimestamp("date_lock").getTime()),rs.getInt("fault"),rs.getDate("last_login"),rs.getString("picture"),rs.getString("fullname"));
				listUser.add(objUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(st, rs, conn);
		}
		return listUser;
	}
	
	public int addUser(User user) {
		conn = ConnectDBLibrary.getConnection();
		int result =0;
		String sql = "INSERT INTO users(username,password,fullname,email,picture) VALUES(?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getFullname());
			pst.setString(4, user.getEmail());
			pst.setString(5, user.getPicture());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return result;
	}

	public User checkUser(String username) {
		conn = ConnectDBLibrary.getConnection();
		System.out.println(conn);
		String sql = "SELECT * FROM users WHERE username = ?";
		User objUser = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			rs = pst.executeQuery();
			if(rs.next()) {
				objUser = new User(rs.getInt("id_user"), rs.getString("username"), rs.getString("password"), rs.getString("email"),rs.getInt("id_role"), rs.getInt("status"),new Date(rs.getTimestamp("date_lock").getTime()),rs.getInt("fault"),rs.getDate("last_login"),rs.getString("picture"),rs.getString("fullname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		 return objUser;
	}
	
	public User checkUserByMail(String username) {
		conn = ConnectDBLibrary.getConnection();
		System.out.println(conn);
		String sql = "SELECT * FROM users WHERE email = ?";
		User objUser = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			rs = pst.executeQuery();
			if(rs.next()) {
				objUser = new User(rs.getInt("id_user"), rs.getString("username"), rs.getString("password"), rs.getString("email"),rs.getInt("id_role"), rs.getInt("status"),new Date(rs.getTimestamp("date_lock").getTime()),rs.getInt("fault"),rs.getDate("last_login"),rs.getString("picture"),rs.getString("fullname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		 return objUser;
	}

	
	public int delItem(int idUser) {
		conn = ConnectDBLibrary.getConnection();
		int result =0;
		String sql = "DELETE FROM users WHERE id_user = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idUser);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return result;
	}


	public int addFault(User objUser) {
		
		conn = ConnectDBLibrary.getConnection();
		int result = 0;
		String sql = "UPDATE users SET fault = fault + 1 WHERE id_user = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, objUser.getId_user());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return result;
	}


	public int setActive(User objUser) {
		conn = ConnectDBLibrary.getConnection();
		int result =0;
		String sql = "UPDATE users SET status = 1, fault = 0 WHERE id_user = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, objUser.getId_user());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return result;
		
	}
	
	
	public int setPassword(String password,int id_user) {
		conn = ConnectDBLibrary.getConnection();
		int result =0;
		String sql = "UPDATE users SET password = ?  WHERE id_user = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1,password);
			pst.setInt(2, id_user);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return result;
		
	}
	
	public int setUnActive(User objUser) {
		conn = ConnectDBLibrary.getConnection();
		int result =0;
		String sql = "UPDATE users SET status = 2,date_lock = ? WHERE id_user = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setTimestamp(1, new Timestamp(new Date().getTime()));
			pst.setInt(2, objUser.getId_user());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return result;
		
	}


	public int setLastLogin(User objUser) {
		conn = ConnectDBLibrary.getConnection();
		int result =0;
		String sql = "UPDATE users SET last_login = ? WHERE id_user = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setTimestamp(1, new Timestamp(new Date().getTime()));
			pst.setInt(2, objUser.getId_user());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return result;
		
	}

	
	
	

	public User getItem(int idUser) {
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT users.*,trangthai.name_status FROM users INNER JOIN trangthai WHERE users.status = trangthai.status AND id_user = ?";
		User objUser = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idUser);
			rs = pst.executeQuery();
			if (rs.next()) {
				objUser = new User(rs.getInt("id_user"), rs.getString("username"), rs.getString("password"), rs.getString("email"),rs.getInt("id_role"), rs.getInt("status"),new Date(rs.getTimestamp("date_lock").getTime()),rs.getInt("fault"),rs.getDate("last_login"),rs.getString("picture"),rs.getString("fullname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return objUser;
	}

	
	public User getItemByEmail(String email) {
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT users.*,trangthai.name_status FROM users INNER JOIN trangthai ON users.status = trangthai.status WHERE email = ?";
		User objUser = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, email);
			rs = pst.executeQuery();
			if (rs.next()) {
				objUser = new User(rs.getInt("id_user"), rs.getString("username"), rs.getString("password"), rs.getString("email"),rs.getInt("id_role"), rs.getInt("status"),new Date(rs.getTimestamp("date_lock").getTime()),rs.getInt("fault"),rs.getDate("last_login"),rs.getString("picture"),rs.getString("fullname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return objUser;
	}

	
	public int editStatus(int id_user, int status,int role) {
		int kq = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "UPDATE users SET status = ?,id_role = ? WHERE id_user = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, status);
			pst.setInt(2, role);
			pst.setInt(3, id_user);
			kq = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		
		return kq;
	}

	public int editUser(User user) {
		conn = ConnectDBLibrary.getConnection();
		int result =0;
		String sql = "UPDATE users SET password = ? , fullname = ?,email = ?,picture = ?  WHERE id_user = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getPassword());
			pst.setString(2, user.getFullname());
			pst.setString(3, user.getEmail());
			pst.setString(4, user.getPicture());
			pst.setInt(5, user.getId_user());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return result;
	}

	
	/*public int countUsers() {
		int kq = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT COUNT(id_user) AS soluong FROM users";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				kq = rs.getInt("soluong");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(st, rs, conn);
		}
		return kq;
	}*/

	/*public ArrayList<User> getItemsPagination(int offset) {
		conn = ConnectDBLibrary.getConnection();
		ArrayList<User> listUser = new ArrayList<>();
		String sql = "SELECT * FROM users ORDER BY id_user DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, Define.Row_Count_Admin);
			rs = pst.executeQuery();
			while (rs.next()) {
				User objUser = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				listUser.add(objUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return listUser;
	}*/
	
	
}
