package dao;

import java.math.BigDecimal;
import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import dao.entity.Transaction;
import dao.mapper.TransactionMapper;

public interface TransactionsDAO {
	
	// Create new transaction entry
	@SqlUpdate("INSERT INTO Transactions (id, transaction_date, total, customer_ID) VALUES (?, ?, ?, ?)")
	int saveTransaction(String id, String date, BigDecimal total, int customerID);
	
	// See all transactions
	@SqlQuery("SELECT id, total, customer_ID, transaction_date FROM Transactions")
	@RegisterRowMapper(TransactionMapper.class)
	List<Transaction> getAllTransactions();
	
	// Search for a specific transaction based on orderID
	@SqlQuery("SELECT id, total, customer_ID, transaction_date FROM Transactions WHERE id = ?")
	@RegisterRowMapper(TransactionMapper.class)
	Transaction getTransaction(String id);
	
	@SqlUpdate("UPDATE Transactions SET total = ? WHERE id = ?")
	int updateTransactionHistory(BigDecimal total, String id);
	
	// Delete a transaction
	@SqlUpdate("DELETE FROM Transaction WHERE id = ?")
	int deleteTransaction(String orderID);
	
	}
