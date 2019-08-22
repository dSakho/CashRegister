package service.transactions;

import java.math.BigDecimal;
import java.util.List;

import dao.TransactionsDAO;
import dao.entity.Transaction;

public class TransactionServiceImpl implements TransactionService {

	private TransactionsDAO transactionsDAO;
	
	
	public TransactionServiceImpl(TransactionsDAO transactionsDAO) {
		super();
		this.transactionsDAO = transactionsDAO;
	}

	@Override
	public Transaction getTransactionByOrderId(String order_ID) {
		return transactionsDAO.getTransaction(order_ID);
	}

	@Override
	public List<Transaction> getAllTransactions() {
		List<Transaction> transactions = transactionsDAO.getAllTransactions();
		
		return (transactions==null) ? null : transactions;
	}

	@Override
	public int saveTransaction(String order_ID, String date, BigDecimal total, int customerID) {
		if(transactionsDAO.getTransaction(order_ID) != null) {
			return 0;
		}
		else {
			return transactionsDAO.saveTransaction(order_ID, date, total, customerID);
		}
	}

	@Override
	public int updateTransaction(BigDecimal total, String id) {
		if(transactionsDAO.getTransaction(id) == null) {
			return 0;
		}
		else {
			return transactionsDAO.updateTransactionHistory(total, id);
			}
		}

	@Override
	public int deleteTransaction(String order_ID) {
		if(transactionsDAO.getTransaction(order_ID) == null) {
			return 0;
		}
		else {
			return transactionsDAO.deleteTransaction(order_ID);
			}
		}
}
