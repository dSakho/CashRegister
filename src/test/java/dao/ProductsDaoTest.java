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
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import dao.entity.Product;

@RunWith(BlockJUnit4ClassRunner.class)
public class ProductsDaoTest {

	private Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/TestSupermarket?serverTimezone=America/New_York",
			"root", "Winner23");

	@Before
	public void setupTestEnvironment() {
		jdbi.installPlugin(new SqlObjectPlugin());

		jdbi.useHandle(handle -> {
			handle.execute(
					"CREATE TABLE Products (id VARCHAR(11) NOT NULL, name VARCHAR(20), price DECIMAL(13,2), supplier_ID int, onSale BOOL, date_added DATETIME)");
			handle.execute("INSERT INTO Products VALUES ('123', 'Quinoa', 322.11, 1, 0, '2019-07-28 20:07:26')");
			handle.execute("INSERT INTO Products VALUES ('345', 'Potatoes', 9.11, 4, 1, '2018-07-28 20:17:16')");
			handle.execute("INSERT INTO Products VALUES ('765', 'Eggs', 1100.22, 2, 1, '2019-06-28 21:07:16')");
			handle.execute("INSERT INTO Products VALUES ('201', 'Yogurt-Coconut', 0.90, 3, 0, CURRENT_TIMESTAMP())");
			handle.execute("INSERT INTO Products VALUES ('444', 'Mozzerella', 0.90, 3, 0, CURRENT_TIMESTAMP())");
		});

	}

	@Test
	public void AddProductTest() throws Exception {
		List<Product> products = jdbi.withExtension(ProductsDAO.class, dao -> dao.getAllProducts());

		assertThat(products.size()).isEqualTo(5);

		int productAdded = jdbi.withExtension(ProductsDAO.class,
				dao -> dao.addProduct("877", "Apple Pie", new BigDecimal(3.99), 22, true, "2018-07-28 20:17:16"));
		assertThat(productAdded).isEqualTo(1);

		List<Product> productsAndNewProductList = jdbi.withExtension(ProductsDAO.class, dao -> dao.getAllProducts());
		assertThat(productsAndNewProductList.size()).isEqualTo(6);
	}

	@Test
	public void GetAllProductsTest() throws Exception {
		
		List<Product> products = jdbi.withExtension(ProductsDAO.class, dao -> dao.getAllProducts());

		assertThat(products.size()).isEqualTo(5);
	}

	@Test
	public void GetAProductByIDTest() throws Exception {
		
		Product product = jdbi.withExtension(ProductsDAO.class, dao -> dao.getProductByID("444"));
		assertThat(product.getId()).isEqualTo("444");
	}

	@Test
	public void updateProductTest() {
		int updatedProducts = jdbi.withExtension(ProductsDAO.class, dao -> dao.updateProduct("Cheddar", new BigDecimal(2.34), 3, true, "444"));
		assertThat(updatedProducts).isEqualTo(1);
		
		Product updatedProduct = jdbi.withExtension(ProductsDAO.class, dao -> dao.getProductByID("444"));
		assertThat(updatedProduct.getName()).isEqualToIgnoringNewLines("Cheddar");
	}

	@Test
	public void DeleteProductTest() throws Exception {
		int productsDeleted2 = jdbi.withExtension(ProductsDAO.class, dao -> dao.deleteProduct("4"));
		assertThat(productsDeleted2).isEqualTo(0);

		int productsDeleted = jdbi.withExtension(ProductsDAO.class, dao -> dao.deleteProduct("444"));
		assertThat(productsDeleted).isEqualTo(1);
	}

	@After
	public void cleanTestEnvironment() {

		Handle handle = jdbi.open();

		handle.execute("DROP TABLE Products");

		handle.close();
	}
}
