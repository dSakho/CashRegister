package dao.entity;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Supplier {
	
	private int id;
	private String name;

	public Supplier() {
	}
	
	public Supplier(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ColumnName("name")
	public String getName() {
		return name;
	}

	@ColumnName("id")
	public int getId() {
		return id;
	}

}
