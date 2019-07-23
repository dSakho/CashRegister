package dao.entity;

import java.sql.Date;

public class RegisterSales {
	String orderID;
	String registerID;
	String cashier;
	Date transactionDate;
	
	public RegisterSales(String orderID, String registerID, String cashier, Date transactionDate) {
		this.orderID = orderID;
		this.registerID = registerID;
		this.cashier = cashier;
		this.transactionDate = transactionDate;
	}
}
