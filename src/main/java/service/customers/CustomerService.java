package service.customers;

import java.util.List;

import dao.entity.Customer;

public interface CustomerService {

	int saveNewCustomerInfo(String name);
	
	List<Customer> getAllCustomers();
	
	Customer getCustomerById(int id);
	
	int updateCustomerName(String newName, int id);
	
	int deleteEntry(int id);
}
