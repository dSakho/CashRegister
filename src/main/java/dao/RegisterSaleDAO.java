package dao;

import java.util.List;

import dao.entity.Transaction;

public interface RegisterSaleDAO {
	// Create a new entry in the DB
	void saveRegisterEvent();
	
	// See all transaction from a specific register
	List<Transaction> getAlTransactionsOnRegister();

	// Delete an entry based on its orderID
	void deleteEntry(String regID);
}
