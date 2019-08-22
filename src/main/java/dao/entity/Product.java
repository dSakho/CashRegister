package dao.entity;

import java.math.BigDecimal;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Product {

	private String id;
	private String name;
	private BigDecimal price;
	private int supplier_ID;
	private boolean onSale;
	private String date_added;
	
	public Product() {
	}

	public Product(String id, String name, BigDecimal price, int supplier_ID, boolean onSale, String date_added) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.supplier_ID = supplier_ID;
		this.onSale = onSale;
		this.date_added = date_added;
	}

	@ColumnName("id")
	public String getId() {
		return id;
	}
	
	@ColumnName("name")
	public String getName() {
		return name;
	}

	@ColumnName("price")
	public BigDecimal getPrice() {
		return price;
	}

	@ColumnName("supplier_id")
	public int getSupplier_ID() {
		return supplier_ID;
	}

	@ColumnName("onSale")
	public boolean isOnSale() {
		return onSale;
	}

	@ColumnName("date_added")
	public String getDate_added() {
		return date_added;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setSupplier_ID(int supplier_ID) {
		this.supplier_ID = supplier_ID;
	}

	public void setOnSale(boolean onSale) {
		this.onSale = onSale;
	}

	public void setDate_added(String date_added) {
		this.date_added = date_added;
	}

}
