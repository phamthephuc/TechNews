package model.bean;

public class TrangThai {
	private int status;
	private String name_status;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getName_status() {
		return name_status;
	}
	public void setName_status(String name_status) {
		this.name_status = name_status;
	}
	public TrangThai(int status, String name_status) {
		super();
		this.status = status;
		this.name_status = name_status;
	}
	public TrangThai() {
		super();
	}
	
}
