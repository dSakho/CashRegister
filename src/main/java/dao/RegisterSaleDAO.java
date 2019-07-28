package dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import dao.entity.RegisterSales;
import dao.entity.Transaction;

public interface RegisterSaleDAO {
	// Create a new entry in the DB
	@SqlUpdate("INSERT INTO Sales VALUES(PurchaseDate, Cashier, Register, OrderID VALUES (CURRENT_TIMESTAMP(), ?, ?, ?)")
	int saveRegisterEvent(String Cashier, String Register, String OrderID);
	

	// See all transaction from a specific register
	@SqlQuery("SELECT PurchaseDate, Cashier, Register, OrderID FROM Sales")
	@RegisterBeanMapper(RegisterSales.class)
	List<Transaction> getAllTransactionsOnRegister();

	
	// See one transaction
	@SqlQuery("SELECT PurchaseDate, Cashier, Register, OrderID FROM ItemsSold WHERE OrderID = ?")
	@RegisterBeanMapper(RegisterSales.class)
	Transaction getTransactionByID(String orderID);
	
	// I Don't think there's ever a reason to update this table
	
	// Delete an entry based on its orderID
	@SqlUpdate("DELETE FROM Food WHERE OrderID = ?")
	int deleteEntry(String regID);
}
