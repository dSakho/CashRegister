package dao;

import java.math.BigDecimal;
import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import dao.entity.Product;

public interface ProductDAO {
	
	// Get all items in the inventory
	@SqlQuery("SELECT `Serial Number`, Price, `Product Name`, Supplier, Sale FROM Food")
	@RegisterBeanMapper(Product.class)
	List<Product> getProducts();
	
	@SqlQuery("SELECT `Serial Number`, Price, `Product Name`, Supplier, Sale FROM Food WHERE `Serial Number` = ?")
	Product getProductByID(String productID);
	
	// Add new product to the database
	@SqlUpdate("INSERT INTO Food (`Serial Number`, Price, `Product Name`, Supplier, Sale) VALUES (?,?,?,?,?)")
	int addProduct(String productID, BigDecimal price,
			String name,
			String supplierID,
			boolean sale);
	
	// Update product
	@SqlUpdate("UPDATE Food SET Price = ?, `Product Name` = ?, Supplier = ?, Sale = ? WHERE `Serial Number` = ?")
	int updateProduct(BigDecimal price,
			String name,
			String supplierID,
			boolean sale, String productID);
	
	// Delete a product from inventory
	@SqlUpdate("DELETE FROM Food WHERE `Serial Number` = ?")
	int deleteProduct(String productID);
}
