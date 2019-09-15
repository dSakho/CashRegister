package dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.entity.Transaction;


public class TransactionsDAOTest {
	
	static private Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/TestSupermarket?serverTimezone=America/New_York", "root", "Winner23");

	@Before
	public void setupTestEnvironment() {
		jdbi.installPlugin(new SqlObjectPlugin());
		
		jdbi.useHandle(handle -> {
			handle.execute("CREATE TABLE Transactions (id varchar(11) not null, date DATETIME, total DECIMAL(9,2) not null, customer_ID int)");
			handle.execute("INSERT INTO Transactions VALUES ('20190729001', CURRENT_TIMESTAMP(), 99.89, 9)");
			handle.execute("INSERT INTO Transactions VALUES ('20190729002', '2019-07-28 20:07:16', 199.89, 7)");
			handle.execute("INSERT INTO Transactions VALUES ('20190729011', '2018-08-28 12:07:16', 1199.89, 1)");
			handle.execute("INSERT INTO Transactions VALUES ('20190729099', '2017-09-22 13:07:16', 9.0, 22)");
		});
	}
	
	@Test
	public void saveTransaction() {
		int transactionsAdded = jdbi.withExtension(
				
		TransactionsDAO.class, dao -> dao.saveTransaction("20190729100", "CURRENT_TIMESTAMP()", new BigDecimal(659.87), 5));
		assertThat(transactionsAdded).isEqualTo(1);
		
	}
	
	@Test
	public void getAllTransactionsTest() {
		
		List<Transaction> listOfTransactions = jdbi.withExtension(TransactionsDAO.class, dao -> dao.getAllTransactions());
		assertThat(listOfTransactions).isNotNull();
	}
	
	@Test
	public void getTransactionTest() {
		Transaction transaction = jdbi.withExtension(TransactionsDAO.class, dao -> dao.getTransaction("20190729002"));
		assertThat(transaction).isNotNull();
	}
	
	@Test
	public void updateTransactionHistoryTest() {
		int transactionsUpdated = jdbi.withExtension(TransactionsDAO.class, dao -> dao.updateTransactionHistory(new BigDecimal(40.90), "20190729002"));
		assertThat(transactionsUpdated).isEqualTo(1);

	}
	
	@Test
	public void deleteTransaction() {
		int deletedTransaction = jdbi.withExtension(TransactionsDAO.class, dao -> dao.deleteTransaction("20190729002"));
		assertThat(deletedTransaction).isEqualTo(1);

	}
	
	@After
	public void cleanTestEnvironment() {
		
		Handle handle = jdbi.open();
		
		handle.execute("DROP TABLE Transactions");
		
		handle.close();		}
}
