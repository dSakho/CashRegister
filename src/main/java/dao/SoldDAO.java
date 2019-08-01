package dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import dao.entity.Product;
import dao.entity.Sold_Products;
import dao.entity.Transaction;
import java.math.BigDecimal;

public interface SoldDAO {

	// Add a row to the database for all items from a purchase
	@SqlUpdate("INSERT INTO ItemsSold (Order_ID, Price, Product_Name) VALUES (?, ?, ?)")
	int saveProductSale(String productID, BigDecimal price, String proName);
	
	// See all products sold
	@SqlQuery("SELECT Order_ID, Price, Product_Name FROM ItemsSold")
	@RegisterBeanMapper(Sold_Products.class)
	List<Product> getListOfSoldProducts();
	
	// See all of a specific purchases for a specific item
	@SqlQuery("SELECT Order_ID, Price, Product_Name FROM ItemsSold WHERE Order_Name = ?")
	@RegisterBeanMapper(Sold_Products.class)
	List<Product> getProductSaleHistory(String productID);
	
	@SqlUpdate("UPDATE ItemsSold SET Order_ID = \"RETURNED\" WHERE Order_ID = ? AND Product_Name = ?")
	int returnedItem(String order_ID, String productName);
	
	// See all orders that include a specific orderID
	@SqlQuery("SELECT Order_ID, Price, Product_Name FROM ItemsSold WHERE Order_Name = ?")
	@RegisterBeanMapper(Sold_Products.class)
	List<Transaction> getAllOrdersThatPurchasedItem(String productID);
	
	@SqlUpdate("DELETE FROM ItemsSold WHERE Order_ID = ?")
	int deleteHistory(String orderID);
	
}
