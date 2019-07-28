package dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.junit.Test;

import dao.entity.Transaction;
public class TransactionDAOTest {
	
	@Test
	public void testAllTransactionDAOMethods() {
		
		
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/Supermarket?serverTimezone=America/New_York&useSSL=false", "root", "Winner23");
		jdbi.installPlugin(new SqlObjectPlugin());
		
		Transaction trans = new Transaction(200, "Dameka Dowdy", "00000000", "", new BigDecimal(20));
		
		int count = jdbi.withExtension(TransactionsDAO.class, dao -> dao.saveTransaction(trans.getTotal(), trans.getCustomerID(), trans.getOrderID(), trans.getPrice(), "1999072801"));
		
		assertThat(count).isEqualTo(1);
		
		System.out.println("Test 'saveTransactionTest()' passed!");
		
		List<Transaction> history = jdbi.withExtension(TransactionsDAO.class, dao -> dao.getAllTransactions());
		
		assertThat(history).isNotNull();
		assertThat(history).hasSize(1);
		
		System.out.println("Test 'getAllTransactionsTest()' passed!");

		Transaction transaction = jdbi.withExtension(TransactionsDAO.class, dao -> dao.getTransaction("1999072801"));
		
		assertThat(transaction).isNotNull();
		assertThat(transaction.getTotal()).isEqualByComparingTo(0);
		
		System.out.println("Test 'getTransactionByID()' passed!");

		count = 0;
		assertThat(count).isEqualTo(0);
		
		count = jdbi.withExtension(TransactionsDAO.class, dao -> dao.deleteTransaction("20000000"));
		
		assertThat(count).isEqualTo(1);
		assertThat(jdbi.withExtension(TransactionsDAO.class, dao -> dao.getAllTransactions()).size()).isEqualTo(0);

		System.out.println("Test 'deleteTransactionTest()' passed!");
		
	}
}
