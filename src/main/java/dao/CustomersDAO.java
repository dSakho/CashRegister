package dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import dao.entity.Customer;
import dao.mapper.CustomerMapper;

public interface CustomersDAO {
	
	// Create a new entry in the DB
	@SqlUpdate("INSERT INTO Customers (name) VALUES (?)")
	int saveNewCustomerInfo(String name);
	
	@SqlQuery("SELECT id, name FROM Customers")
	@RegisterRowMapper(CustomerMapper.class)
	List<Customer> getAllCustomers();
	
	@SqlQuery("SELECT id, name WHERE id = ?")
	@RegisterRowMapper(CustomerMapper.class)
	Customer getCustomerById(int id);
	
	@SqlUpdate("UPDATE Customers SET name = ? WHERE id = ?")
	int updateCustomerName(String newName, int id);
	
	@SqlUpdate("DELETE FROM Customers WHERE id = ?")
	int deleteEntry(int id);
}
