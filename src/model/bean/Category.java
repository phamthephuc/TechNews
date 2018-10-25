package model.bean;

public class Category {
	private int id_cat;
	private String name;
	private int id_parent;
	private int priority;
	
	
	public Category(int id_cat, String name, int id_parent, int priority) {
		super();
		this.id_cat = id_cat;
		this.name = name;
		this.id_parent = id_parent;
		this.priority = priority;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public int getId_parent() {
		return id_parent;
	}
	public void setId_parent(int id_parent) {
		this.id_parent = id_parent;
	}
	public int getId_cat() {
		return id_cat;
	}
	public void setId_cat(int id_cat) {
		this.id_cat = id_cat;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Category(int id_cat, String name, int id_parent) {
		super();
		this.id_cat = id_cat;
		this.name = name;
		this.id_parent = id_parent;
	}
	public Category(int id_cat, String name) {
		super();
		this.id_cat = id_cat;
		this.name = name;
	}
	public Category() {
		super();
	}
	
	
}
