package dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import dao.entity.Product;

@RunWith(BlockJUnit4ClassRunner.class)
public class ProductDaoTest {
	
	@Test
	public void testGetAllProducts() throws Exception {
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/Supermarket?serverTimezone=America/New_York&useSSL=false", "root", "Winner23");
		jdbi.installPlugin(new SqlObjectPlugin());
		
		List<Product> productList = jdbi.withExtension(ProductDAO.class, dao -> dao.getProducts());
		assertThat(productList).isNotNull();
		assertThat(productList).hasSize(2);
		
		System.out.println("Test 'testGetAllProducts()' passed!");
	}
	
	@Test
	public void testGetAProduct() throws Exception {
		
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/Supermarket?serverTimezone=America/New_York&useSSL=false", "root", "Winner23");
		jdbi.installPlugin(new SqlObjectPlugin());
		
		Product exampleProduct = jdbi.withExtension(ProductDAO.class, dao -> dao.getProductByID("123"));
		
		assertThat(exampleProduct).isNotNull();
		assertThat(exampleProduct.getPrice()).asString().contains("10");
		System.out.println("Test 'testGetAProduct()' passed!");
	}
	
	@Test
	public void testAddProduct() throws Exception {
		
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/Supermarket?serverTimezone=America/New_York&useSSL=false", "root", "Winner23");
		jdbi.installPlugin(new SqlObjectPlugin());
		
		BigDecimal testPrice = new BigDecimal(30);
		String testPID = new String("0001");
		String testPname = new String("Test Product2");
		String testPsupplier = new String("11112");
		boolean testPsale = false;
		
		int count = jdbi.withExtension(ProductDAO.class, dao -> dao.addProduct(testPID, testPrice, testPname, testPsupplier, testPsale));
		
		assertThat(count).isEqualTo(1);
		assertThat(jdbi.withExtension(ProductDAO.class, dao -> dao.getProductByID(testPID)).getPrice().equals(testPrice));
		assertThat(jdbi.withExtension(ProductDAO.class, dao -> dao.getProductByID(testPID)).getProductName().contentEquals(testPname));
		assertThat(jdbi.withExtension(ProductDAO.class, dao -> dao.getProductByID(testPID)).isOnSale()).isEqualTo(testPsale);
		assertThat(jdbi.withExtension(ProductDAO.class, dao -> dao.getProductByID(testPID)).getProductSupplier().contentEquals(testPsupplier));
		
		System.out.println("Test 'testAddProduct()' passed!");
	}
	
	@Test
	public void testDeleteProduct() throws Exception {
		
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/Supermarket?serverTimezone=America/New_York&useSSL=false", "root", "Winner23");
		jdbi.installPlugin(new SqlObjectPlugin());
		
		int count = jdbi.withExtension(ProductDAO.class, dao -> dao.deleteProduct("000"));
		
		assertThat(count).isEqualTo(1);
		assertThat(jdbi.withExtension(ProductDAO.class, dao -> dao.getProductByID("000")).equals(null));
		
		System.out.println("Test 'testDeleteProduct()' passed!");
	}
}
