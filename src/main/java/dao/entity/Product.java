package dao.entity;

import java.math.BigDecimal;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Product {

	public Product() {
	}

	public Product(String string, BigDecimal bigDecimal, String string2, String string3, boolean boolean1) {
		productIdentifier = string;
		price = bigDecimal;
		productName = string2;
		productSupplier = string3;
		onSale = boolean1;
	}

	private String productIdentifier;
	private BigDecimal price;
	private String productName;
	private String productSupplier;
	private boolean onSale;


	@ColumnName("Serial Number")
	public String getProductIdentifier() {
		return productIdentifier;
	}

	public void setProductIdentifier(String productIdentifier) {
		this.productIdentifier = productIdentifier;
	}

	@ColumnName("Price")
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@ColumnName("Product Name")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@ColumnName("Supplier")
	public String getProductSupplier() {
		return productSupplier;
	}

	public void setProductSupplier(String productSupplier) {
		this.productSupplier = productSupplier;
	}

	@ColumnName("Sale")
	public boolean isOnSale() {
		return onSale;
	}

	public void setOnSale(boolean onSale) {
		this.onSale = onSale;
	}

}
