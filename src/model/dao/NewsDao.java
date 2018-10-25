package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.fabric.xmlrpc.base.Data;

import library.ConnectDBLibrary;
import model.bean.Comment;
import model.bean.Define;
import model.bean.News;
import model.bean.User;

public class NewsDao {
	private Connection conn;
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;
	
	public int delItem(int id) {
		int kq = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "DELETE FROM news WHERE id_news = ? ";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			kq = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		CommentDao cmd = new CommentDao();
		ArrayList<Comment> listCmt1 = cmd.getItemsType1ForNews(id);
		if(listCmt1.size() > 0)
		{
			for (Comment comment : listCmt1) {
				cmd.delItemLevel1(comment.getId_cmt());
			}
		}
		return kq;
	}
	
	public int increaseView(int id) {
		int kq = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "UPDATE news SET  views = views + 1 WHERE id_news = ? ";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
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
	
	public int increaseComment(int id) {
		int kq = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "UPDATE news SET  comment = comment + 1 WHERE id_news = ? ";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
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
	
	public int decreaseComment(int id) {
		int kq = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "UPDATE news SET  comment = comment - 1 WHERE id_news = ? ";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
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
	
	public ArrayList<News> getItems()
	{
		conn = ConnectDBLibrary.getConnection();
		ArrayList<News> listTT = new ArrayList<>();
		String sql = "SELECT news.*,users.username,users.picture AS avatar,category.name AS name_cat FROM news INNER JOIN category ON news.id_cat = category.id_cat INNER JOIN users ON news.id_author = users.id_user ORDER BY id_news DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				News objTT = new News(rs.getInt("id_news"), rs.getString("name"), rs.getInt("id_cat"), rs.getString("name_cat"), rs.getString("picture"), rs.getString("description"), rs.getString("detail"), rs.getDate("date_create"), rs.getInt("views"), rs.getInt("id_author"), rs.getString("username"), rs.getString("avatar"));
				listTT.add(objTT);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(st, rs, conn);
		}
		
		return listTT;
	}
	
	public ArrayList<News> getItemsByIdCatLiMit5(int id_cat)
	{
		conn = ConnectDBLibrary.getConnection();
		ArrayList<News> listTT = new ArrayList<>();
		String sql = "SELECT news.*,username FROM news INNER JOIN users ON news.id_author = users.id_user WHERE id_cat = ?  ORDER BY id_news DESC LIMIT 5 ";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_cat);
			rs = pst.executeQuery();
			while (rs.next()) {
				News objTT = new News(rs.getInt("id_news"), rs.getString("name"), rs.getInt("id_cat"), rs.getString("picture"), rs.getString("description"), rs.getString("detail"), rs.getDate("date_create"), rs.getInt("views"),rs.getString("username"),rs.getInt("comment"));
				listTT.add(objTT);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		
		return listTT;
	}
	
	
	public ArrayList<News> getItemsByIdCat(int id_cat)
	{
		conn = ConnectDBLibrary.getConnection();
		ArrayList<News> listTT = new ArrayList<>();
		String sql = "SELECT news.*,username FROM news INNER JOIN users ON news.id_author = users.id_user WHERE id_cat = ?  ORDER BY id_news DESC";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_cat);
			rs = pst.executeQuery();
			while (rs.next()) {
				News objTT = new News(rs.getInt("id_news"), rs.getString("name"), rs.getInt("id_cat"), rs.getString("picture"), rs.getString("description"), rs.getString("detail"), rs.getDate("date_create"), rs.getInt("views"),rs.getString("username"),rs.getInt("comment"));
				listTT.add(objTT);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		
		return listTT;
	}
	
	public int addItem(News objNews) {
		int kq = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "INSERT INTO news(name,description,detail,id_cat,picture,date_create,id_author) VALUES(?,?,?,?,?,?,?) ";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objNews.getName());
			pst.setString(2,objNews.getDescription());
			pst.setString(3, objNews.getDetail());
			pst.setInt(4, objNews.getId_cat());
			pst.setString(5, objNews.getPicture());
			pst.setInt(7, objNews.getId_author());
			pst.setTimestamp(6, new Timestamp(new Date().getTime()));
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
	
	public News getItem(int id_news) {
		News news = null;
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT news.*,username FROM news INNER JOIN users ON news.id_author = users.id_user WHERE id_news = ? ";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_news);
			rs = pst.executeQuery();
			if(rs.next())
			{
				news = new News(rs.getInt("id_news"), rs.getString("name"), rs.getInt("id_cat"), rs.getString("picture"), rs.getString("description"), rs.getString("detail"), rs.getDate("date_create"), rs.getInt("views"),rs.getString("username"),rs.getInt("comment"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}

		return news;
	}

	public int editItem(News objNews) {
		int kq = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "UPDATE news SET name = ?,description = ?,detail = ?,id_cat = ?,picture = ? WHERE id_news = ? ";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objNews.getName());
			pst.setString(2,objNews.getDescription());
			pst.setString(3, objNews.getDetail());
			pst.setInt(4, objNews.getId_cat());
			pst.setString(5, objNews.getPicture());
			pst.setInt(6, objNews.getId_news());
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
	public ArrayList<News> getItemsPopular() {
		conn = ConnectDBLibrary.getConnection();
		ArrayList<News> listTT = new ArrayList<>();
		String sql = "SELECT news.*,username FROM news INNER JOIN users ON news.id_author = users.id_user  ORDER BY views DESC LIMIT 4 ";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				News objTT = new News(rs.getInt("id_news"), rs.getString("name"), rs.getInt("id_cat"), rs.getString("picture"), rs.getString("description"), rs.getString("detail"), rs.getDate("date_create"), rs.getInt("views"),rs.getString("username"),rs.getInt("comment"));
				listTT.add(objTT);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		
		return listTT;
	}
	public ArrayList<News> getMostComment() {
		conn = ConnectDBLibrary.getConnection();
		ArrayList<News> listTT = new ArrayList<>();
		String sql = "SELECT news.*,username FROM news INNER JOIN users ON news.id_author = users.id_user  ORDER BY views DESC LIMIT 4 ";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				News objTT = new News(rs.getInt("id_news"), rs.getString("name"), rs.getInt("id_cat"), rs.getString("picture"), rs.getString("description"), rs.getString("detail"), rs.getDate("date_create"), rs.getInt("views"),rs.getString("username"),rs.getInt("comment"));
				listTT.add(objTT);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		
		return listTT;
	}
	public ArrayList<News> getTop3() {
		conn = ConnectDBLibrary.getConnection();
		ArrayList<News> listTT = new ArrayList<>();
		String sql = "SELECT news.*,username FROM news INNER JOIN users ON news.id_author = users.id_user  ORDER BY id_news DESC LIMIT 3 ";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				News objTT = new News(rs.getInt("id_news"), rs.getString("name"), rs.getInt("id_cat"), rs.getString("picture"), rs.getString("description"), rs.getString("detail"), rs.getDate("date_create"), rs.getInt("views"),rs.getString("username"),rs.getInt("comment"));
				listTT.add(objTT);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		
		return listTT;
	}
	
	public int countNewsByIdCat(int cid) {
		int ketqua = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT COUNT(id_news) AS soLuong FROM news WHERE id_cat= ?";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cid);
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
	
	public int countNewNews(User objUser) {
		int ketqua = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT COUNT(id_news) AS soLuong FROM news WHERE date_create < ?";
		
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
	
	public int countNewsByName(String key) {
		int ketqua = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT COUNT(id_news) AS soLuong FROM news WHERE name LIKE '%" + key + "%'";
		
		try {
			pst = conn.prepareStatement(sql);
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
	
	
	public ArrayList<News> getItemsByIdCatPagination(int id_cat,int offset) {
		ArrayList<News> listNews = new ArrayList<>();
		
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT news.*,username FROM news INNER JOIN users ON news.id_author = users.id_user WHERE id_cat = ? ORDER BY id_news DESC LIMIT ?,? ";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_cat);
			pst.setInt(2, offset);
			pst.setInt(3, Define.Row_Count_Public);
			rs = pst.executeQuery();
			while(rs.next())
			{
				News objTT = new News(rs.getInt("id_news"), rs.getString("name"), rs.getInt("id_cat"), rs.getString("picture"), rs.getString("description"), rs.getString("detail"), rs.getDate("date_create"), rs.getInt("views"),rs.getString("username"),rs.getInt("comment"));
				listNews.add(objTT);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}

		return listNews;
	}
	
	public ArrayList<News> getItemsByNamePagination(String key,int offset) {
		ArrayList<News> listNews = new ArrayList<>();
		
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT news.*,username,category.name AS namecat FROM news INNER JOIN users ON news.id_author = users.id_user INNER JOIN category ON news.id_cat = category.id_cat WHERE news.name  LIKE '%" + key +"%' ORDER BY id_news DESC LIMIT ?,? ";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, 2*Define.Row_Count_Public);
			rs = pst.executeQuery();
			while(rs.next())
			{
				News objTT = new News(rs.getInt("id_news"), rs.getString("name"), rs.getInt("id_cat"), rs.getString("picture"), rs.getString("description"), rs.getString("detail"), rs.getDate("date_create"), rs.getInt("views"),rs.getString("username"),rs.getInt("comment"),rs.getString("namecat"));
				listNews.add(objTT);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}

		return listNews;
	}
	
	
	public int getIdItemsByName(String key) {
		int kq = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT id_news FROM news WHERE news.name  = ? ";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, key);
			rs = pst.executeQuery();
			if(rs.next())
			{
				kq = rs.getInt("id_news");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}

		return kq;
	}
	
	
	public ArrayList<News> getItemsLikeIdCat(int id_news, int id_cat) {
		ArrayList<News> listNews = new ArrayList<News>();
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT news.*,username,category.name AS namecat FROM news INNER JOIN users ON news.id_author = users.id_user INNER JOIN category ON news.id_cat = category.id_cat WHERE news.id_cat = ? AND id_news != ? ORDER BY RAND() DESC LIMIT 0,4 ";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_cat);
			pst.setInt(2, id_news);
			rs = pst.executeQuery();
			while(rs.next())
			{
				News objTT = new News(rs.getInt("id_news"), rs.getString("name"), rs.getInt("id_cat"), rs.getString("picture"), rs.getString("description"), rs.getString("detail"), rs.getDate("date_create"), rs.getInt("views"),rs.getString("username"),rs.getInt("comment"),rs.getString("namecat"));
				listNews.add(objTT);
						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return listNews;
	}
	
	
	public ArrayList<News> searchByNameLimit10(String key)
	{
		conn = ConnectDBLibrary.getConnection();
		ArrayList<News> listTT = new ArrayList<>();
		String sql = "SELECT news.* FROM news WHERE name LIKE '%" + key + "%'  ORDER BY RAND() DESC LIMIT 10 ";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				News objTT = new News(rs.getInt("id_news"), rs.getString("name"), rs.getString("picture"));
				listTT.add(objTT);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		
		return listTT;
	}
}
