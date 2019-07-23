package dao;

import java.util.List;

import dao.entity.Transaction;

public interface TransactionsDAO {
	
	// Create new transaction entry
	void saveTransaction();
	
	// See all transactions
	List<Transaction> getAllTransactions();
	
	// Search for a specific transaction based on orderID
	Transaction getTransaction(String orderID);
	
	// Delete a transaction
	void deleteTransaction(String orderID);
	
	}
