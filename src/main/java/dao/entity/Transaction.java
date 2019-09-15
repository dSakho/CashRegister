package dao.entity;

import java.math.BigDecimal;

import org.jdbi.v3.core.mapper.reflect.ColumnName;
import org.joda.time.DateTime;

public class Transaction {

	private String id;
	private BigDecimal total;
	private int customer_ID;
	private String transaction_date;

	public Transaction(String id, BigDecimal total, int customer_ID, String transaction_date) {
		this.id = id;
		this.total = total;
		this.customer_ID = customer_ID;
		this.transaction_date = transaction_date;
	}

	@ColumnName("id")
	public String getId() {
		return id;
	}
	
	@ColumnName("total")
	public BigDecimal getTotal() {
		return total;
	}

	@ColumnName("customer_ID")
	public int getCustomer_ID() {
		return customer_ID;
	}

	@ColumnName("date")
	public String getTransaction_date() {
		return transaction_date;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public void setCustomer_ID(int customer_ID) {
		this.customer_ID = customer_ID;
	}

	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}
	
	
}
