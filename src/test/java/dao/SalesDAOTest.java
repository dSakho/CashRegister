package dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.entity.Sale;

public class SalesDAOTest {
	
	private Jdbi jdbi = Jdbi.create(
			"jdbc:mysql://localhost:3306/TestSupermarket?serverTimezone=America/New_York", "root", "Winner23");

	@Before
	public void setupTestEnvironment() {
		
		jdbi.installPlugin(new SqlObjectPlugin());
		
		jdbi.useHandle(handle -> {
			handle.execute("CREATE TABLE Sales (order_ID VARCHAR(11) NOT NULL, pos_ID VARCHAR(5), pid VARCHAR(10))");
			handle.execute("INSERT INTO Sales (order_ID, pos_ID, pid) VALUES ('20190729001', '00001', '123')");
			handle.execute("INSERT INTO Sales (order_ID, pos_ID, pid) VALUES ('20190729002', '00002', '321')");
			handle.execute("INSERT INTO Sales (order_ID, pos_ID, pid) VALUES ('20190729020', '00001', '123')");
			handle.execute("INSERT INTO Sales (order_ID, pos_ID, pid) VALUES ('20190729011', '00003', '123')");
			handle.execute("INSERT INTO Sales (order_ID, pos_ID, pid) VALUES ('20190729001', '00003', '123')");
		});
	}
	
	@Test
	public void saveProductSaleTest() {
		
		List <Sale> sales = jdbi.withExtension(SalesDAO.class, dao -> dao.getListOfSoldProducts());
		assertThat(sales.size()).isEqualTo(5);

		int addedCount = jdbi.withExtension(SalesDAO.class, dao -> dao.saveProductSale("2019072003", "9001", "Spi Chips"));
		assertThat(addedCount).isEqualTo(1);

		sales = jdbi.withExtension(SalesDAO.class, dao -> dao.getListOfSoldProducts());
		assertThat(sales.size()).isEqualTo(6);
		
		assertThat(sales.get(5).getOrder_ID()).isEqualToIgnoringNewLines("2019072003");
		assertThat(sales.get(5).getPos_ID()).isEqualToIgnoringNewLines("9001");
		assertThat(sales.get(5).getProductID()).isEqualToIgnoringNewLines("Spi Chips");

	}
	
	@Test
	public void getListOfSoldProductsTest() {
		
		List <Sale> sales = jdbi.withExtension(SalesDAO.class, dao -> dao.getListOfSoldProducts());
		
		assertThat(sales.size()).isEqualTo(5);
		assertThat(sales.get(0).getOrder_ID()).isEqualToIgnoringNewLines("20190729001");
		assertThat(sales.get(4).getPos_ID()).isEqualToIgnoringNewLines("00003");
		assertThat(sales.get(2).getPos_ID()).isEqualToIgnoringNewLines("00001");
		assertThat(sales.get(3).getProductID()).isEqualToIgnoringNewLines("123");
		
	}
	
	@Test
	public void getProductSaleHistoryByOrderIDTest() {
		Sale selectSale = jdbi.withExtension(SalesDAO.class, dao -> dao.getProductSaleHistoryByOrderID("20190729002"));
		
		assertThat(selectSale).isNotNull();
		
		assertThat(selectSale.getPos_ID()).isEqualToIgnoringNewLines("00002");
		assertThat(selectSale.getProductID()).isEqualToIgnoringNewLines("321");
	}
	
	@Test
	public void returnedItemTest() {
		Sale selectSale = jdbi.withExtension(SalesDAO.class, dao -> dao.getProductSaleHistoryByOrderID("20190729002"));

		assertThat(selectSale).isNotNull();		
		assertThat(selectSale.getPos_ID()).isEqualToIgnoringNewLines("00002");
		assertThat(selectSale.getProductID()).isEqualToIgnoringNewLines("321");
		
		int returnedCount = jdbi.withExtension(SalesDAO.class, dao -> dao.returnedItem("20190729002"));
		selectSale = jdbi.withExtension(SalesDAO.class, dao -> dao.getProductSaleHistoryByOrderID("20190729002"));
		
		assertThat(selectSale).isNull();
		assertThat(returnedCount).isEqualTo(1);
		
		
	}
	
	@Test
	public void deleteHistoryTest() {
		
		List <Sale> sales = jdbi.withExtension(SalesDAO.class, dao -> dao.getListOfSoldProducts());
		assertThat(sales.size()).isEqualTo(5);
		
		int deletedCount = jdbi.withExtension(SalesDAO.class, dao -> dao.deleteHistory("20190729002"));
		assertThat(deletedCount).isEqualTo(1);
		
		sales = jdbi.withExtension(SalesDAO.class, dao -> dao.getListOfSoldProducts());
		assertThat(sales.size()).isEqualTo(4);

	}
	
	@After
	public void cleanTestEnvironment() {
		
		Handle handle = jdbi.open();
		
		handle.execute("DROP TABLE Sales");
		
		handle.close();
		}
		
}
