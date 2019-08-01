package dao;

import java.math.BigDecimal;
import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import dao.entity.Transaction;

public interface TransactionsDAO {
	
	// Create new transaction entry
	@SqlUpdate("INSERT INTO Transactions (Transaction_Total, Customer_Name, Product_Name, Price, Order_ID, Order_Date_Time) VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP())")
	int saveTransaction(int total, String customerName, String productName, BigDecimal price, String orderID);
	
	// See all transactions
	@SqlQuery("SELECT Transaction_Total, Customer_Name, Product_Name, Price, Order_ID, Order_Date_Time FROM Transactions")
	@RegisterBeanMapper(Transaction.class)
	List<Transaction> getAllTransactions();
	
	// Search for a specific transaction based on orderID
	@SqlQuery("SELECT Transaction_Total, Customer_Name, Product_Name, Price, Order_ID, Order_Date_Time FROM Transactions WHERE Order_ID = ?")
	@RegisterBeanMapper(Transaction.class)
	Transaction getTransaction(String orderID);
	
	// Delete a transaction
	@SqlUpdate("DELETE FROM Food WHERE Order_ID = ?")
	int deleteTransaction(String orderID);
	
	}
