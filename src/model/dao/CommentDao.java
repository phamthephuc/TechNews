package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import library.ConnectDBLibrary;
import model.bean.Category;
import model.bean.Comment;
import model.bean.User;

public class CommentDao {
	private Connection conn;
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;
	
	public int countNewComment(User objUser) {
		int ketqua = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT COUNT(id_cmt) AS soLuong FROM comment WHERE date_cmt > ?";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setTimestamp(1, new Timestamp(objUser.getLast_login().getTime()));
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
	
	public ArrayList<Comment> getItemsType1(){
		conn = ConnectDBLibrary.getConnection();
		ArrayList<Comment> listCmt = new ArrayList<>();
		String sql = "SELECT comment.*,news.name As tenBai FROM comment INNER JOIN news ON comment.id_baicmt = news.id_news WHERE type_cmt = 1 ORDER BY id_cmt DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Comment objCmt = new Comment(rs.getInt("id_cmt"), rs.getInt("type_cmt"), rs.getString("content"), rs.getInt("id_baicmt"), rs.getDate("date_cmt"), rs.getString("fullname"), rs.getInt("status_cmt"),rs.getString("tenBai"),rs.getString("avatar"));
				listCmt.add(objCmt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(st, rs, conn);
		}
		
		return listCmt;
	}
	
	public ArrayList<Comment> getItemsLimit10(){
		conn = ConnectDBLibrary.getConnection();
		ArrayList<Comment> listCmt = new ArrayList<>();
		String sql = "SELECT comment.* FROM comment ORDER BY id_cmt DESC LIMIT 10";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Comment objCmt = new Comment(rs.getInt("id_cmt"), rs.getInt("type_cmt"), rs.getString("content"), rs.getInt("id_baicmt"), rs.getDate("date_cmt"), rs.getString("fullname"), rs.getInt("status_cmt"),"",rs.getString("avatar"));
				listCmt.add(objCmt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(st, rs, conn);
		}
		
		return listCmt;
	}
	
	public ArrayList<Comment> getItemsType1ForNews(int id_news){
		conn = ConnectDBLibrary.getConnection();
		ArrayList<Comment> listCmt = new ArrayList<>();
		String sql = "SELECT comment.*,news.name As tenBai FROM comment INNER JOIN news ON comment.id_baicmt = news.id_news WHERE type_cmt = 1 AND id_baicmt = ? AND status_cmt = 1 ORDER BY id_cmt ASC";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_news);
			rs = pst.executeQuery();
			while (rs.next()) {
				Comment objCmt = new Comment(rs.getInt("id_cmt"), rs.getInt("type_cmt"), rs.getString("content"), rs.getInt("id_baicmt"), rs.getDate("date_cmt"), rs.getString("fullname"), rs.getInt("status_cmt"),rs.getString("tenBai"),rs.getString("avatar"));
				listCmt.add(objCmt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		
		return listCmt;
	}
	
	
	public ArrayList<Comment> getItemsType2ForNews(Comment obj){
		conn = ConnectDBLibrary.getConnection();
		ArrayList<Comment> listCmt = new ArrayList<>();
		String sql = "SELECT comment.* FROM comment WHERE type_cmt = 2 AND id_baicmt = ? AND status_cmt = 1  ORDER BY id_cmt ASC";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, obj.getId_cmt());
			rs = pst.executeQuery();
			while (rs.next()) {
				Comment objCmt = new Comment(rs.getInt("id_cmt"), rs.getInt("type_cmt"), rs.getString("content"),obj.getId_bai(), rs.getDate("date_cmt"), rs.getString("fullname"), rs.getInt("status_cmt"),obj.getContent(),rs.getString("avatar"));
				listCmt.add(objCmt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		
		return listCmt;
	}
	
	public ArrayList<Comment> getItemsType2(Comment obj){
		conn = ConnectDBLibrary.getConnection();
		ArrayList<Comment> listCmt = new ArrayList<>();
		String sql = "SELECT comment.* FROM comment WHERE id_baicmt = ? && type_cmt = 2 ORDER BY id_cmt DESC";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, obj.getId_cmt());
			rs = pst.executeQuery();
			while (rs.next()) {
				Comment objCmt = new Comment(rs.getInt("id_cmt"), rs.getInt("type_cmt"), rs.getString("content"),obj.getId_bai(), rs.getDate("date_cmt"), rs.getString("fullname"), rs.getInt("status_cmt"),obj.getContent(),rs.getString("avatar"));
				listCmt.add(objCmt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(st, rs, conn);
		}
		
		return listCmt;
	}
	
	public Comment getItem(int id_cmt){
		conn = ConnectDBLibrary.getConnection();
		Comment objCmt = null;
		try {
			String sql = "SELECT comment.*,news.name As tenBai FROM comment INNER JOIN news ON comment.id_baicmt = news.id_news WHERE id_cmt = ? ORDER BY id_cmt DESC";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_cmt);
			rs = pst.executeQuery();
			if (rs.next()) {
				 objCmt = new Comment(rs.getInt("id_cmt"), rs.getInt("type_cmt"), rs.getString("content"), rs.getInt("id_baicmt"), rs.getDate("date_cmt"), rs.getString("fullname"), rs.getInt("status_cmt"),rs.getString("tenBai"),rs.getString("avatar"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(st, rs, conn);
		}
		
		return objCmt;
	}



	public int delItemLevel1(int id_cmt) {
		int kq = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "DELETE FROM comment WHERE id_cmt = ? OR (type_cmt = 2 AND id_baicmt = ?)";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_cmt);
			pst.setInt(2, id_cmt);
			kq = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return kq;
	}
	
	public int delItemLevel2(int id_cmt) {
		int kq = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "DELETE FROM comment WHERE id_cmt = ? ";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_cmt);
			kq = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return kq;
	}



	public int editStatus(int id_cmt, int status) {
		int kq = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "UPDATE comment SET status_cmt = ? WHERE id_cmt = ? ";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, status);
			pst.setInt(2, id_cmt);
			kq = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return kq;
	}



	public int getCmt2Relative(int id_cmt) {
		conn = ConnectDBLibrary.getConnection();
		int kq = 0;
		String sql = "SELECT COUNT(id_cmt) as SoLuong FROM comment WHERE id_baicmt = ? && type_cmt = 2";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_cmt);
			rs = pst.executeQuery();
			if(rs.next())
			{
				kq = rs.getInt("SoLuong");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(st, rs, conn);
		}
		
		return kq;
	}
	
	public int addItem(Comment objCmt) {
		conn = ConnectDBLibrary.getConnection();
		String sql = "INSERT INTO comment(type_cmt,content,date_cmt,id_baicmt,status_cmt,email,avatar,fullname) VALUES(?,?,?,?,?,?,?,?) ";
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, objCmt.getLevel());
			pst.setString(2, objCmt.getContent());
			pst.setTimestamp(3, new Timestamp(objCmt.getDate_creat().getTime()));
			pst.setInt(4, objCmt.getId_bai());
			pst.setInt(5, objCmt.getStatus());
			pst.setString(6, objCmt.getEmail());
			pst.setString(7, objCmt.getAvatar());
			pst.setString(8, objCmt.getName_user());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return result;
	}

	public String getTenBaiViet(int level,int id) {
		String sql="";
		conn = ConnectDBLibrary.getConnection();
		String ketqua = "";
		if(level == 1) {
		sql = "SELECT name AS tenBai FROM news WHERE id_news = ?";
		}
		else {
			sql = "SELECT content AS tenBai FROM comment WHERE id_cmt = ?";
		}
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next())
			{
				ketqua = rs.getString("tenBai");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(st, rs, conn);
		}
		
		return ketqua;
	}
}
