package dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.entity.Customer;

public class CustomersDAOTest {
	
	private Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/TestSupermarket?serverTimezone=America/New_York", "root", "Winner23");
	
	@Before
	public void setupTestEnvironment() {
		jdbi.installPlugin(new SqlObjectPlugin());
		
		jdbi.useHandle(handle -> {
			handle.execute("CREATE TABLE Customers(id int not null auto_increment, name varchar(20) not null, primary key(id))");
			handle.execute("INSERT INTO Customers (name) VALUES ('Kevin')");
			handle.execute("INSERT INTO Customers (name) VALUES ('Dominick')");
			handle.execute("INSERT INTO Customers (name) VALUES ('Noel')");
			handle.execute("INSERT INTO Customers (name) VALUES ('Nya')");
		});
		
	}
	
	@Test
	public void saveNewCustomerInfoTest() {
		
		int customerAdded = jdbi.withExtension(CustomersDAO.class, dao -> dao.saveNewCustomerInfo("Phillip"));
		assertThat(customerAdded).isEqualTo(1);
		
		Customer customer = jdbi.withExtension(CustomersDAO.class, dao -> dao.getCustomerById(4));
		assertThat(customer.getName()).isEqualToIgnoringWhitespace("Phillip");
	}
	
	@Test
	public void getAllCustomersTest() {

		int numberOfCustomers = 0;
		assertThat(numberOfCustomers).isEqualTo(0);
		
		List<Customer> listOfCustomers = jdbi.withExtension(CustomersDAO.class, dao -> dao.getAllCustomers());
		
		assertThat(listOfCustomers.get(0).getId()).isEqualTo(1);
		assertThat(listOfCustomers.get(2).getId()).isEqualTo(3);
		
		assertThat(listOfCustomers.get(3).getName()).isEqualToIgnoringWhitespace("Nya");
		
	}
	
	@Test
	public void getCustomerByIdTest() {
		
		Customer customer = jdbi.withExtension(CustomersDAO.class, dao -> dao.getCustomerById(2));
		
		assertThat(customer).isNotNull();
		
		assertThat(customer.getId()).isEqualTo(2);
		
		assertThat(customer.getName()).isEqualToIgnoringWhitespace("Dominick");
	}
	
	@Test
	public void updateCustomerNameTest() {
		
	}
	
	@Test
	public void deleteEntryTest() {
		
		int numberOfCustomersDeleted = 0;
		assertThat(numberOfCustomersDeleted).isEqualTo(0);
		
		List<Customer> listOfCustomers = jdbi.withExtension(CustomersDAO.class, dao -> dao.getAllCustomers());
		
		assertThat(listOfCustomers.size()).isEqualTo(4);
		
		numberOfCustomersDeleted = jdbi.withExtension(CustomersDAO.class, dao -> dao.deleteEntry(2));
		assertThat(numberOfCustomersDeleted).isEqualTo(1);
		
		List<Customer> newListOfCustomers = jdbi.withExtension(CustomersDAO.class, dao -> dao.getAllCustomers());
		
		assertThat(newListOfCustomers.size()).isEqualTo(3);

		
		
	}
	
	@After
	public void cleanTestEnvironment() {
		
		Handle handle = jdbi.open();
		
		handle.execute("DROP TABLE Customers");
		
		handle.close();
	}
}
