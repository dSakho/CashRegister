package dao.entity;

import java.math.BigDecimal;
import java.sql.Date;

public class Transaction {
	
	BigDecimal total;
	String customerID;
	String orderID;
	Date orderDateTime;
	

	public Transaction(BigDecimal total, String customerID, String orderID, Date orderDateTime) {
		this.total = total;
		this.customerID = customerID;
		this.orderID = orderID;
		this.orderDateTime = orderDateTime;
	}
	
	
	

}
