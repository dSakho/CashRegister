package dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import dao.entity.Sale;
import dao.mapper.SaleMapper;

public interface SalesDAO {

	@SqlUpdate("INSERT INTO Sales (order_ID, pos_ID, pid) VALUES (?, ?, ?)")
	int saveProductSale(String productID, String price, String proName);
	
	@SqlQuery("SELECT order_ID, pos_ID, pid FROM Sales")
	@RegisterRowMapper(SaleMapper.class)
	List<Sale> getListOfSoldProducts();
	
	@SqlQuery("SELECT order_ID, pos_ID, pid FROM Sales WHERE order_ID = ? LIMIT 1")
	@RegisterRowMapper(SaleMapper.class)
	Sale getProductSaleHistoryByOrderID(String order_ID);
	
	@SqlUpdate("UPDATE Sales SET Order_ID = \"RETURNED\" WHERE Order_ID = ? LIMIT 1")
	int returnedItem(String order_ID);
	
	@SqlUpdate("DELETE FROM Sales WHERE order_ID = ?")
	int deleteHistory(String orderID);
	
}
