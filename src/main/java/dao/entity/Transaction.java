package dao.entity;

import java.math.BigDecimal;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Transaction {
	
	private int total;
	private String customerID;
	private String orderID;
	private String orderDateTime;
	private BigDecimal price;
	
	public Transaction(int total, String customerID, String orderID, String orderDateTime, BigDecimal price) {
		this.total = total;
		this.customerID = customerID;
		this.orderID = orderID;
		this.orderDateTime = orderDateTime;
		this.price = price;
	}


	@ColumnName("Transaction_Total")
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@ColumnName("Customer_Name")
	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	@ColumnName("Order_ID")
	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	@ColumnName("Order_Date_Time")
	public String getOrderDateTime() {
		return orderDateTime;
	}

	public void setOrderDateTime(String orderDateTime) {
		this.orderDateTime = orderDateTime;
	}

	@ColumnName("Price")
	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
