package model.bean;

public class Message {
	private int id_message;
	private int id_type;
	private String email;
	private int seen;
	
	
	public int getSeen() {
		return seen;
	}
	public void setSeen(int seen) {
		this.seen = seen;
	}
	public int getId_message() {
		return id_message;
	}
	public void setId_message(int id_message) {
		this.id_message = id_message;
	}
	public int getId_type() {
		return id_type;
	}
	public void setId_type(int id_type) {
		this.id_type = id_type;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Message(int id_message, int id_type, String email, int seen) {
		super();
		this.id_message = id_message;
		this.id_type = id_type;
		this.seen = seen;
		this.email = email;
	}
	public Message(int id_message, int id_type, String email) {
		super();
		this.id_message = id_message;
		this.id_type = id_type;
		this.email = email;
	}
	
	
	
}
