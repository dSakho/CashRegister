package service.products;

import java.math.BigDecimal;
import java.util.List;

import dao.entity.Product;

public interface ProductService {

	public Product scanAndGetProduct(final String id);
	
	public List<Product> getProducts();
	
	public int createProduct(BigDecimal price, String name, String supplierID, boolean sale, String productID);
	
	public int updateProduct(BigDecimal price, String name, String supplierID, boolean sale, String productID);
	
	public int deleteProduct(String pid);
}
