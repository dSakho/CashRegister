package dao;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import dao.entity.Product;

@RunWith(BlockJUnit4ClassRunner.class)
public class ProductsDaoTest {
	
	private Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/TestSupermarket?serverTimezone=America/New_York", "root", "Winner23");

	@Before
	public void setupTestEnvironment() {
		jdbi.installPlugin(new SqlObjectPlugin());

		jdbi.useHandle(handle -> {
			handle.execute("CREATE TABLE Products (id VARCHAR(11) NOT NULL, name VARCHAR(20), price DECIMAL(13,2), supplier_ID int, onSale BOOL, date_added DATETIME)");
			handle.execute("INSERT INTO Products VALUES ('20190729001', 'Kevin Dowdy', 322.11, 1, 0, '2019-07-28 20:07:26')");
			handle.execute("INSERT INTO Products VALUES ('20190729201', 'Dameka Dowdy', 9.11, 4, 1, '2018-07-28 20:17:16')");
			handle.execute("INSERT INTO Products VALUES ('20190729221', 'Daouda Sanke', 1100.22, 2, 1, '2019-06-28 21:07:16')");
			handle.execute("INSERT INTO Products VALUES ('20190729031', 'Kameren Essan', 0.90, 3, 0, CURRENT_TIMESTAMP())");
			handle.execute("INSERT INTO Products VALUES ('20190729031', 'Delano Redding', 0.90, 3, 0, CURRENT_TIMESTAMP())");
		});
		
	}
	
	@Test
	public void AddProductTest() throws Exception {
		
	}
	
	@Test
	public void GetAllProductsTest() throws Exception {

	}
	
	@Test
	public void GetAProductByIDTest() throws Exception {
	
	}
	
	@Test
	public void updateProductTest() {
		
	}
	
	@Test
	public void DeleteProductTest() throws Exception {
		
	}
	
	@After
	public void cleanTestEnvironment() {
		
		Handle handle = jdbi.open();
		
		handle.execute("DROP TABLE Products");
		
		handle.close();	
	}
}
