package dao.entity;
import java.sql.*;
import java.util.List;
import java.math.BigDecimal;

public class Database {
	
	// Fields that are use used to open the connection with these credentials
	private String domain = new String("jdbc:mysql://localhost:3306/Supermarket");
	private String pass = new String("Winner23");
	private String user = new String("root");
	
	// Use the class credentials to open the connection
	private Connection con= (Connection) DriverManager.getConnection(domain, user, pass);
	
	public Database() throws ClassNotFoundException, SQLException {
		}
	
	public BigDecimal getPrice(String itemNum) throws SQLException {
		// Necessary code to make query and get the price back from the DB
		Statement stmt = con.createStatement();
		ResultSet itemInfo = stmt.executeQuery("SELECT PRICE,SALE FROM Food WHERE `Serial Number` = " + itemNum + ";");
		BigDecimal thePrice = new BigDecimal(0);
		BigDecimal sale = new BigDecimal(.90);
		
		// Move the cursor to the first row in the result set 
		while (itemInfo.next()) {
			thePrice = (itemInfo.getBigDecimal("Price"));
			
			if(itemInfo.getBoolean("Sale") == true)
				thePrice = thePrice.multiply(sale);
			
			System.out.println(thePrice);
		}
		
		// Close the connection to free resources
		stmt.close();
		
		return thePrice;
	}
	
	public void getOrderDetails(String orderID) throws SQLException {
		Statement stmt = con.createStatement();
		ResultSet orderInfo = stmt.executeQuery("SELECT * FROM Transactions WHERE `ORDER_ID` = " + orderID + ";");
		
		int row = 0;
		while (orderInfo.next()) {
			
			if(row == 0) {
				System.out.print("$" + orderInfo.getInt("Transaction_Total") + "\t | "); // Print the transaction total
				System.out.print(orderInfo.getString("Customer_Name") + "\t | "); // Print the name of customer
				System.out.print(orderID + "\t | "); // Print the orderID
				System.out.println(orderInfo.getString("Order_Date_Time")); // Print the date and time of the order
				row++;
			}
			System.out.print((row+1) + ". "); // 
			System.out.print(orderInfo.getString("Product_Name") + "\t |"); // Print Name of Item
			System.out.println(orderInfo.getDouble("Price") + "\t |"); // Print the Price of the item
		}
	}
	
	public void saveOrderDetails(String orderID, String customerName, List<Products> shoppingCart, String cashier, BigDecimal totalCost) {
	/**
	 * Create a new table to save purchases to
	 * Save a list of the name of each item as a String
	 * Save a list of the corresponding price of each item as a list
	 * 
	 * Use another table with name, price, orderID
	 * to search for specific items later
	 * 
	 * Table 1: OrderID, CustomerName, Cashier, PurchaseTotal, PurchaseDateTime
	 *				^
	 *				|
	 *				v 
	 * Table 2: OrderID, Name, Price
	 * 
	 * The OrderID fields in both represent the same information
	 */
	}
	
	public void closeConnection() throws SQLException {
		if(con != null)
			con.close();
	}

}

