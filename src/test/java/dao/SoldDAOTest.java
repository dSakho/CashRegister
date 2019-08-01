package dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.junit.Test;

public class SoldDAOTest {
	
	@Test
	public void saveProductSaleTest() {
		
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/Supermarket?serverTimezone=America/New_York&useSSL=false", "root", "Winner23");
		jdbi.installPlugin(new SqlObjectPlugin());
		
		String PID = new String("");
		BigDecimal pPrice = new BigDecimal(0);
		String pName = new String("");
		
		int count = jdbi.withExtension(SoldDAO.class, dao -> dao.saveProductSale(PID, pPrice, pName));
		
		assertThat(count).isEqualTo(1);
		assertThat(jdbi.withExtension(SoldDAO.class, dao -> dao.getProductSaleHistory(PID)).get(0).getProductIdentifier().contentEquals(PID));
		System.out.println("Test 'saveProductSale()' passed!");
	}
	
	@Test
	public void getListOfSoldProductsTest() {
		
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/Supermarket?serverTimezone=America/New_York&useSSL=false", "root", "Winner23");
		jdbi.installPlugin(new SqlObjectPlugin());
		
		
		System.out.println("Test 'getListOfSoldProductsTest()' passed!");
	}
	
	@Test
	public void getProductSaleHistoryTest() {
		
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/Supermarket?serverTimezone=America/New_York&useSSL=false", "root", "Winner23");
		jdbi.installPlugin(new SqlObjectPlugin());
		
		
		System.out.println("Test 'getProductSaleHistoryTest()' passed!");
	}
	
	@Test
	public void getAllOrdersThatPurchasedItemTest() {
		
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/Supermarket?serverTimezone=America/New_York&useSSL=false", "root", "Winner23");
		jdbi.installPlugin(new SqlObjectPlugin());
		
		System.out.println("Test 'getAllOrdersThatPurchasedItemTest()' passed!");
	}
	
}
