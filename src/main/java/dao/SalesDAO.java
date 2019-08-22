package dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import dao.entity.Sale;
import dao.mapper.SaleMapper;

public interface SalesDAO {

	// Save a product sale
	@SqlUpdate("INSERT INTO Sales (order_ID, pos_ID, pid) VALUES (?, ?, ?)")
	int saveProductSale(String order_id, String pointOfSale, String product_ID);
	
	// Get the history of every product sold for each transaction
	@SqlQuery("SELECT order_ID, pos_ID, pid FROM Sales")
	@RegisterRowMapper(SaleMapper.class)
	List<Sale> getListOfSales();
	
	// Get the history of every product sold for a specific transaction (the receipt)
	@SqlQuery("SELECT pid FROM Sales WHERE order_ID = ?")
	List<String> getProductsSoldHistoryByOrderID(String order_ID);
	
	@SqlUpdate("UPDATE Sales SET order_ID = \"RETURNED\" WHERE order_ID = ? AND pid = ? LIMIT 1")
	int returnedItem(String order_ID, String pid);
	
	@SqlUpdate("DELETE FROM Sales WHERE order_ID = ?")
	int deleteHistory(String orderID);
	
}
