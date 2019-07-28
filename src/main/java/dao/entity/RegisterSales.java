package dao.entity;

import java.sql.Date;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class RegisterSales {
	private String orderID;
	private String registerID;
	private String cashier;
	private Date transactionDate;
	
	public RegisterSales(String orderID, String registerID, String cashier, Date transactionDate) {
		this.orderID = orderID;
		this.registerID = registerID;
		this.cashier = cashier;
		this.transactionDate = transactionDate;
	}

	@ColumnName("Order_ID")
	public String getOrderID() {
		return orderID;
	}


	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	@ColumnName("Register")
	public String getRegisterID() {
		return registerID;
	}


	public void setRegisterID(String registerID) {
		this.registerID = registerID;
	}

	@ColumnName("Cashier")
	public String getCashier() {
		return cashier;
	}


	public void setCashier(String cashier) {
		this.cashier = cashier;
	}

	@ColumnName("PurchaseDate")
	public Date getTransactionDate() {
		return transactionDate;
	}


	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
}
