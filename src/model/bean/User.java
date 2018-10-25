package model.bean;

import java.util.Date;

public class User {
	private int id_user;
	private String username;
	private String password;
	private String email;
	private int id_role;
	private int fault;
	private String picture;
	private String fullname;
	
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	private Date last_login;
	public Date getLast_login() {
		return last_login;
	}
	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}
	public int getFault() {
		return fault;
	}
	public void setFault(int fault) {
		this.fault = fault;
	}
	public int getStatus() {
		return status;
	}
	private int status;
	private Date date_lock;
	
	public User(int id_user, String username, String password, String email, String picture, String fullname) {
		super();
		this.id_user = id_user;
		this.username = username;
		this.password = password;
		this.email = email;
		this.picture = picture;
		this.fullname = fullname;
	}
	public User(int id_user, String username, String password, String email, int id_role, int status,
			Date date_lock,int fault,Date last_login,String picture) {
		super();
		this.id_user = id_user;
		this.username = username;
		this.password = password;
		this.email = email;
		this.id_role = id_role;
		this.status = status;
		this.date_lock = date_lock;
		this.fault = fault;
		this.last_login = last_login;
		this.picture = picture;
	}
	public User(int id_user, String username, String password, String email, int id_role, int status,
			Date date_lock,int fault,Date last_login,String picture,String fullname) {
		super();
		this.id_user = id_user;
		this.username = username;
		this.password = password;
		this.email = email;
		this.id_role = id_role;
		this.status = status;
		this.date_lock = date_lock;
		this.fault = fault;
		this.last_login = last_login;
		this.picture = picture;
		this.fullname = fullname;
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId_role() {
		return id_role;
	}
	public void setId_role(int id_role) {
		this.id_role = id_role;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getDate_lock() {
		return date_lock;
	}
	public void setDate_lock(Date date_lock) {
		this.date_lock = date_lock;
	}
	public User() {
		super();
	}
	
	
	

}
