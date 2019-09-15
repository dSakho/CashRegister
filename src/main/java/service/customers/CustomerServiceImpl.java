package service.customers;

import java.util.List;

import dao.CustomersDAO;
import dao.entity.Customer;

public class CustomerServiceImpl implements CustomerService {

	private CustomersDAO customersDAO;

	public CustomerServiceImpl(CustomersDAO customersDAO) {
		super();
		this.customersDAO = customersDAO;
	}

	@Override
	public int saveNewCustomerInfo(String name) {

		return customersDAO.saveNewCustomerInfo(name);
	}

	@Override
	public List<Customer> getAllCustomers() {
		
		return customersDAO.getAllCustomers();
	}

	@Override
	public Customer getCustomerById(int id) {
		Customer customer = customersDAO.getCustomerById(id);
		if(customer == null)
			{
			return null;
			}
		else {
			return customer;
		}
	}

	@Override
	public int updateCustomerName(String newName, int id) {
		Customer customer = customersDAO.getCustomerById(id);
		if(customer == null)
			{
			return 0;
			}
		else {
			return customersDAO.updateCustomerName(newName, id);
			}
		}

	@Override
	public int deleteEntry(int id) {
		Customer customer = customersDAO.getCustomerById(id);
		if(customer == null)
			{
			return 0;
			}
		else {
			return customersDAO.deleteEntry(id);
			}
	}

}
