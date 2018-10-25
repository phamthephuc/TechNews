package model.bean;

import java.util.Date;

public class Comment {
	private int id_cmt;
	private int level;
	private String content;
	private int id_bai;
	private String email;
	
	public Comment(int id_cmt, int level, String content, int id_bai, String email, Date date_creat, String name_user,
			int status, String avatar) {
		super();
		this.id_cmt = id_cmt;
		this.level = level;
		this.content = content;
		this.id_bai = id_bai;
		this.email = email;
		this.date_creat = date_creat;
		this.name_user = name_user;
		this.status = status;
		this.avatar = avatar;
	}
	private Date date_creat;
	private String name_user;
	private int status;
	private String tenBaiViet;
	private String avatar;
	
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getTenBaiViet() {
		return tenBaiViet;
	}
	public void setTenBaiViet(String tenBaiViet) {
		this.tenBaiViet = tenBaiViet;
	}
	public int getId_cmt() {
		return id_cmt;
	}
	public void setId_cmt(int id_cmt) {
		this.id_cmt = id_cmt;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getId_bai() {
		return id_bai;
	}
	public void setId_bai(int id_bai) {
		this.id_bai = id_bai;
	}
	
	public Date getDate_creat() {
		return date_creat;
	}
	public void setDate_creat(Date date_creat) {
		this.date_creat = date_creat;
	}
	public String getName_user() {
		return name_user;
	}
	public void setName_user(String name_user) {
		this.name_user = name_user;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public Comment(int id_cmt, int level, String content, int id_bai, Date date_creat, String name_user,
			int status,String tenBaiViet,String avatar) {
		super();
		this.id_cmt = id_cmt;
		this.level = level;
		this.content = content;
		this.id_bai = id_bai;
		this.date_creat = date_creat;
		this.name_user = name_user;
		this.status = status;
		this.tenBaiViet = tenBaiViet;
		this.avatar = avatar;
	}
	
	public Comment(int id_cmt, int level, String content, int id_user, Date date_creat, String name_user,
			int status,String avatar) {
		super();
		this.id_cmt = id_cmt;
		this.level = level;
		this.content = content;
		this.date_creat = date_creat;
		this.name_user = name_user;
		this.status = status;
		this.avatar = avatar;
	}
	public Comment() {
		super();
	}
	
	
}
