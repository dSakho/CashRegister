package dao.entity;

import java.math.BigDecimal;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Sold_Products {
	
	private String orderID;
	private BigDecimal price;
	private String productName;

	public Sold_Products(String orderID, BigDecimal price, String productName) {
		this.orderID = orderID;
		this.price = price;
		this.productName = productName;
	}

	@ColumnName("Order_ID")
	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	@ColumnName("Price")
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@ColumnName("Product_Name")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}
