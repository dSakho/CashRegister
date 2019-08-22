package dao;

import java.math.BigDecimal;
import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import dao.entity.Product;
import dao.mapper.ProductMapper;

public interface ProductsDAO {
	
	// Add new product to the database
	@SqlUpdate("INSERT INTO Products (id, name, price, supplier_ID, onSale, date_added) VALUES (?,?,?,?,?,?)")
	int addProduct(String productID, String name, BigDecimal price, int supplierID, boolean sale, String date);
	
	// Get all items in the inventory
	@SqlQuery("SELECT id, name, price, supplier_ID, onSale, date_added FROM Products")
	@RegisterRowMapper(ProductMapper.class)
	List<Product> getAllProducts();
	
	@SqlQuery("SELECT id, name, price, supplier_ID, onSale, date_added FROM Products WHERE id = ?")
	@RegisterRowMapper(ProductMapper.class)
	Product getProductByID(String productID);
	
	// Update product
	@SqlUpdate("UPDATE Products SET name = ?, price = ?, supplier_ID = ?, onSale = ? WHERE id = ?")
	int updateProduct(String name, BigDecimal newPrice, int supplierID, boolean isSale, String productID);
	
	// Delete a product from inventory
	@SqlUpdate("DELETE FROM Products WHERE id = ?")
	int deleteProduct(String productID);
}
