package dao;

import java.util.List;

import dao.entity.Product;
import dao.entity.Transaction;

public interface SoldDAO {

	// Add a row to the database for all items from a purchase
	void saveProductSalet(String productID);
	
	// See all products sold
	List<Product> getListOfProducts();
	
	// See all of a specific purchases for a specific item
	List<Product> getProductSaleHistory(String productID);
	
	// See all orders that include a specific orderID
	List<Transaction> getAllOrdersThatPurchasedItem(String productID);
	
}
