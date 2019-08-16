package dao;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SuppliersDAOTest {

	private Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/TestSupermarket?serverTimezone=America/New_York", "root", "Winner23");

	@Before
	public void setupTestEnvironment() {
		jdbi.installPlugin(new SqlObjectPlugin());
		
		jdbi.useHandle(handle -> {
			handle.execute("CREATE TABLE Suppliers (id int not null auto_increment, name varchar(20) not null, primary key(id))");
			handle.execute("INSERT INTO Suppliers (name) VALUES ('Dole')");
			handle.execute("INSERT INTO Suppliers (name) VALUES ('Walmart')");
			handle.execute("INSERT INTO Suppliers (name) VALUES ('Lancaster Farms')");
			handle.execute("INSERT INTO Suppliers (name) VALUES ('Pop Factory')");
		});		
		
	}

	@Test
	public void addNewSupplier() {
		
	}
	
	@Test
	public void getAllSuppliers() {
		
	}
	
	@Test
	public void getSupplierByID() {
		
	}
	
	@Test
	public void updateSupplierName() {
		
	}
	
	@Test
	public void deleteSupplier() {
		
	}
	
	@After
	public void cleanTestEnvironment() {
		
		Handle handle = jdbi.open();
		
		handle.execute("DROP TABLE Suppliers");
		
		handle.close();
		}
}
