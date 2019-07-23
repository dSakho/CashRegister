package dao.entity;

import java.math.BigDecimal;

public class Sold_Products {
	
	String orderID;
	BigDecimal price;
	String productName;

	public Sold_Products(String orderID, BigDecimal price, String productName) {
		this.orderID = orderID;
		this.price = price;
		this.productName = productName;
	}

}
