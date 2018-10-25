package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectDBLibrary;
import model.bean.Category;
import model.bean.News;

public class CatDao {
	private Connection conn;
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;
	
	public ArrayList<Category> getItems(){
		conn = ConnectDBLibrary.getConnection();
		ArrayList<Category> listCat = new ArrayList<>();
		String sql = "SELECT * FROM category ORDER BY priority ASC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Category objCat = new Category(rs.getInt("id_cat"), rs.getString("name"));
				listCat.add(objCat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(st, rs, conn);
		}
		
		return listCat;
	}

	
	public int countCat(){
		conn = ConnectDBLibrary.getConnection();
		int kq = 0;
		String sql = "SELECT COUNT(id_cat) AS soLuong FROM category AS soLuong";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				kq = rs.getInt("soLuong");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(st, rs, conn);
		}
		
		return kq;
	}
	
	public Category getItemByName(String name) {
		conn = ConnectDBLibrary.getConnection();
		Category objCat = null;
		String sql = "SELECT * FROM category WHERE BINARY name = ? ";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			rs = pst.executeQuery();
			if (rs.next()) {
				objCat = new Category(rs.getInt("id_cat"), rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(st, rs, conn);
		}
		return objCat;
	}
	
	public int addItem(Category objCat) {
		conn = ConnectDBLibrary.getConnection();
		String sql = "INSERT INTO category(name,id_parent,priority) VALUES(?,?,?) ";
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objCat.getName());
			pst.setInt(2, objCat.getId_parent());
			pst.setInt(3, objCat.getPriority());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return result;
	}
	
	public int increasePriority(int priority) {
		conn = ConnectDBLibrary.getConnection();
		String sql = "UPDATE category SET priority = priority + 1 WHERE priority >= ?";
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1,priority);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return result;
	}
	
	public int decreasePriority(int priority) {
		conn = ConnectDBLibrary.getConnection();
		String sql = "UPDATE category SET priority = priority - 1 WHERE priority > ?";
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1,priority);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return result;
	}
	
	public int increasePriority(int start,int end) {
		conn = ConnectDBLibrary.getConnection();
		String sql = "UPDATE category SET priority = priority + 1 WHERE priority >= ? AND priority < ?";
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1,start);
			pst.setInt(2,end);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return result;
	}
	
	public int decreasePriority(int start,int end) {
		conn = ConnectDBLibrary.getConnection();
		String sql = "UPDATE category SET priority = priority - 1 WHERE priority <= ? AND priority > ?";
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1,start);
			pst.setInt(2,end);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return result;
	}
	
	public Category getItem(int cid) {
		conn = ConnectDBLibrary.getConnection();
		Category objCat = null;
		String sql = "SELECT * FROM category WHERE id_cat = ? ";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cid);
			rs = pst.executeQuery();
			if (rs.next()) {
				objCat = new Category(rs.getInt("id_cat"), rs.getString("name"),rs.getInt("id_parent"),rs.getInt("priority"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(st, rs, conn);
		}
		return objCat;
	}
	
	public int editItem(Category objCat) {
		conn = ConnectDBLibrary.getConnection();
		String sql = "UPDATE category SET name = ?,id_parent = ?,priority = ? WHERE id_cat = ? ";
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objCat.getName());
			pst.setInt(2, objCat.getId_parent());
			pst.setInt(4, objCat.getId_cat());
			pst.setInt(3, objCat.getPriority());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return result;
	}
	
	public Category getItemByNameAndId(String name, int cid) {
		conn = ConnectDBLibrary.getConnection();
		Category objCat = null;
		String sql = "SELECT * FROM category WHERE BINARY name = ? AND id_cat != ? ";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setInt(2, cid);
			rs = pst.executeQuery();
			if (rs.next()) {
				objCat = new Category(rs.getInt("id_cat"), rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(st, rs, conn);
		}
		return objCat;
	}
	
	public int delItem(int id) {
		CatDao cd = new CatDao();
		Category objCat = cd.getItem(id);
		if(objCat.getId_parent() == 0)
		{
		ArrayList<Category> listSubCat = cd.getItemsByIdParent(id);
		if(listSubCat.size() > 0)
		{
			for (Category category : listSubCat) {
				cd.delItem(category.getId_cat());
			}
		}
		}
		conn = ConnectDBLibrary.getConnection();
		String sql = "DELETE FROM category WHERE id_cat = ? ";
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
		
		NewsDao nd = new NewsDao();
		ArrayList<News> listNews = nd.getItemsByIdCat(id);
		if(listNews.size() > 0)
		{
			for (News news : listNews) {
				nd.delItem(news.getId_news());
			}
		}
		return result;
	}

	public ArrayList<Category> getRootCat() {
		conn = ConnectDBLibrary.getConnection();
		ArrayList<Category> listCat = new ArrayList<>();
		String sql = "SELECT * FROM category WHERE id_parent = 0 ORDER BY priority ASC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Category objCat = new Category(rs.getInt("id_cat"), rs.getString("name"),rs.getInt("id_parent"),rs.getInt("priority"));
				listCat.add(objCat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(st, rs, conn);
		}
		
		return listCat;
	}

	public ArrayList<Category> getItemsByIdParent(int id_parent) {
		conn = ConnectDBLibrary.getConnection();
		ArrayList<Category> listCat = new ArrayList<>();
		String sql = "SELECT * FROM category WHERE id_parent = " + id_parent + " ORDER BY priority ASC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Category objCat = new Category(rs.getInt("id_cat"), rs.getString("name"),rs.getInt("id_parent"),rs.getInt("priority"));
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
