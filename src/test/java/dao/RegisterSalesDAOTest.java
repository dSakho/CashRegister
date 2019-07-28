package dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import dao.entity.Transaction;
import dao.RegisterSaleDAO;

public class RegisterSalesDAOTest {
	
	public void testsaveRegisterEvent() {

		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/Supermarket?serverTimezone=America/New_York&useSSL=false", "root", "Winner23");
		jdbi.installPlugin(new SqlObjectPlugin());
		
		String testCashierName = new String("Kevin Dowdy");
		String testRegID = new String("testReg");
		String testOrderID = new String("2019078888");

		int count = jdbi.withExtension(RegisterSaleDAO.class, dao -> dao.saveRegisterEvent(testCashierName, testRegID, testOrderID));

		assertThat(count).isEqualTo(1);
		assertThat(jdbi.withExtension(RegisterSaleDAO.class, dao -> dao.getTransactionByID(testRegID)).getOrderID().contains(testOrderID));
		
		System.out.println("Test 'testsaveRegisterEvent()' passed!");
	}
	
	public void testGetAllTransactionsOnRegister() {

		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/Supermarket?serverTimezone=America/New_York&useSSL=false", "root", "Winner23");
		jdbi.installPlugin(new SqlObjectPlugin());
		
		List<Transaction> regSales = jdbi.withExtension(RegisterSaleDAO.class, dao -> dao.getAllTransactionsOnRegister());
		assertThat(regSales).isNotNull();
				
		System.out.println("Test 'getAlTransactionsOnRegister()' passed!");
	}
	
	public void testGetTransactionByID() {

		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/Supermarket?serverTimezone=America/New_York&useSSL=false", "root", "Winner23");
		jdbi.installPlugin(new SqlObjectPlugin());
		
		String testID = new String("");
		Transaction testTransaction = jdbi.withExtension(RegisterSaleDAO.class, dao -> dao.getTransactionByID(testID));
		
		assertThat(testTransaction).isNotNull();
		
		System.out.println("Test 'getTransactionByID()' passed!");
	}
	
	public void testDeleteEntry() {

		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/Supermarket?serverTimezone=America/New_York&useSSL=false", "root", "Winner23");
		jdbi.installPlugin(new SqlObjectPlugin());

		String testID = new String("");

		Transaction testTransaction = jdbi.withExtension(RegisterSaleDAO.class, dao -> dao.getTransactionByID(testID));
		
		assertThat(testTransaction).isNotNull();
		
		int count = jdbi.withExtension(RegisterSaleDAO.class, dao -> dao.deleteEntry(testID));
		
		assertThat(count).isEqualTo(1);
		
		System.out.println("Test 'deleteEntry()' passed!");
	}
}
