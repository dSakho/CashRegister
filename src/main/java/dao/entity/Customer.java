package dao.entity;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Customer {

	private int id;
	private String email;


	public Customer(int id, String email) {
		this.id = id;
		this.email = email;
	}

	@ColumnName("id")
	public int getId() {
		return id;
	}

	@ColumnName("email")	
	public String getName() {
		return email;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String email) {
		this.email = email;
	}
	
	
}
