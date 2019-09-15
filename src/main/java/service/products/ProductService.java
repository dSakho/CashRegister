package service.products;

import java.math.BigDecimal;
import java.util.List;

import dao.entity.Product;

public interface ProductService {

	public Product scanAndGetProduct(final String id);
	
	public List<Product> getProducts();
	
	public int createProduct(BigDecimal price, String name, int supplierID, boolean sale, String productID, String date_added);
	
	public int updateProduct(BigDecimal price, String name, int supplierID, boolean sale, String productID);
	
	public int deleteProduct(String pid);
}
