package service.transactions;

import java.math.BigDecimal;
import java.util.List;

import dao.entity.Transaction;

public interface TransactionService {

	Transaction getTransactionByOrderId(String order_ID);
	
	List<Transaction> getAllTransactions();
	
	int saveTransaction(String order_ID, String date, BigDecimal total, int customerID);
	
	int updateTransaction(BigDecimal total, String id);
	
	int deleteTransaction(String order_ID);
}
