package dao;

import java.math.BigDecimal;
import java.util.List;

import dao.entity.Products;

public interface ProductDAO {
	
	// Add new product to the database
	void addProduct(String productID, BigDecimal price,
			String name,
			String supplierID,
			boolean sale);

	// Get Price of a product
	BigDecimal getProductPrice(String productID);
	
	// Get all items in the inventory
	List<Products> getProductInventory();
	
	// Put an item on sale
	void putItemOnSale(String productID);
	void takeItemOffSale(String productID);
	
	// Change the price of an item
	void updateProductPrice(String productID, BigDecimal newPrice);
	
	// Delete a product from inventory
	void deleteProduct(String productID);
}
