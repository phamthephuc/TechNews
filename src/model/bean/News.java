package model.bean;

import java.util.Date;

public class News {
	private int id_news;
	private String name;
	private int id_cat;
	private String catName;
	private String picture;
	private String description;
	private String detail;
	private Date date_create;
	private int views;
	private int id_author;
	private String nameAuthor;
	private String avatar;
	private int comment;
	
	public int getComment() {
		return comment;
	}
	public News(int id_news, String name, String picture) {
		super();
		this.id_news = id_news;
		this.name = name;
		this.picture = picture;
	}
	public void setComment(int comment) {
		this.comment = comment;
	}
	public News(int id_news, String name, int id_cat, String picture, String description, String detail,
			Date date_create, int views, String nameAuthor,int comment) {
		super();
		this.id_news = id_news;
		this.name = name;
		this.id_cat = id_cat;
		this.picture = picture;
		this.description = description;
		this.detail = detail;
		this.date_create = date_create;
		this.views = views;
		this.nameAuthor = nameAuthor;
		this.comment = comment;
	}
	
	public News(int id_news, String name, int id_cat, String picture, String description, String detail,
			Date date_create, int views, String nameAuthor,int comment,String namecat) {
		super();
		this.id_news = id_news;
		this.name = name;
		this.id_cat = id_cat;
		this.picture = picture;
		this.description = description;
		this.detail = detail;
		this.date_create = date_create;
		this.views = views;
		this.nameAuthor = nameAuthor;
		this.comment = comment;
		this.catName = namecat;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public int getId_news() {
		return id_news;
	}
	public void setId_news(int id_news) {
		this.id_news = id_news;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId_cat() {
		return id_cat;
	}
	public void setId_cat(int id_cat) {
		this.id_cat = id_cat;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Date getDate_create() {
		return date_create;
	}
	public void setDate_create(Date date_create) {
		this.date_create = date_create;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public int getId_author() {
		return id_author;
	}
	public void setId_author(int id_author) {
		this.id_author = id_author;
	}
	public String getNameAuthor() {
		return nameAuthor;
	}
	public void setNameAuthor(String nameAuthor) {
		this.nameAuthor = nameAuthor;
	}
	public News(int id_news, String name, int id_cat, String catName, String picture, String description, String detail,
			Date date_create, int views, int id_author, String nameAuthor,String avatar) {
		super();
		this.id_news = id_news;
		this.name = name;
		this.id_cat = id_cat;
		this.catName = catName;
		this.picture = picture;
		this.description = description;
		this.detail = detail;
		this.date_create = date_create;
		this.views = views;
		this.id_author = id_author;
		this.nameAuthor = nameAuthor;
		this.avatar = avatar;
	}
	
	public News(int id_news, String name, int id_cat, String picture, String description, String detail,
			Date date_create, int views, int id_author) {
		super();
		this.id_news = id_news;
		this.name = name;
		this.id_cat = id_cat;
		this.picture = picture;
		this.description = description;
		this.detail = detail;
		this.date_create = date_create;
		this.views = views;
		this.id_author = id_author;
	}
	
	public News(int id_news, String name, String preview_text, String detail_text, int id_cat, String picture) {
		super();
		this.id_news = id_news;
		this.name = name;
		this.description = preview_text;
		this.detail = detail_text;
		this.id_cat = id_cat;
		this.picture = picture;
	}
	
	public News(int id_news, String name, String preview_text, String detail_text, int id_cat, String picture,int id_author) {
		super();
		this.id_news = id_news;
		this.name = name;
		this.description = preview_text;
		this.detail = detail_text;
		this.id_cat = id_cat;
		this.picture = picture;
		this.id_author = id_author;
	}
	
	public News() {
		super();
	}
	
	
}
