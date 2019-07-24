package dao;

import static org.assertj.core.api.Assertions.assertThat;

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
	}
}
