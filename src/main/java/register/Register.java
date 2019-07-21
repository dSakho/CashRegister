package register;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Register {

	// Creating an instance of the Database class with every instance of the Register class
	private Database mainDatabase;

	// The total amount of money in the register - Starts off as $0
	private BigDecimal registerTotal = new BigDecimal (0);
	
	// When the class is called the register's serial number should be available
	private final String registerID = new String("182010460");
	
	// The user of the register will be included saved by the register
	private String registerUser = new String("");
	
	// Counter necessary to keep track of when the register is open
	private boolean drawerState = false;
	
	// The total value of goods scanned
	private BigDecimal purTotal = new BigDecimal(0);
	
	private int customerCounter = 0;
	
	// The constructor is going to need a valid USERID and total to begin with
	public Register(String usrID) throws ClassNotFoundException, SQLException {
		setUsername(usrID);
		this.mainDatabase = new Database();
		System.out.println("Welcome To The Party!");
	}
	
	// Call this method when first opening the register to insert change
	 public void setNewRegisterTotal(BigDecimal money) {
		this.registerTotal = money;
	}
	
	// Call this method when first opening the register to store the user
	private void setUsername(String s) {
		registerUser = this.registerID.concat(s);
	}
	
	private void setDrawerStateOpen() {
		drawerState = true;
	}
	
	private void setDrawerStateClosed() {
		drawerState = false;
	}	
	
	public String getRegID() {
		return this.registerID;
	}
	
	public String getRegUser() {
		return this.registerUser;
	}
	
	public BigDecimal getRegTotal() {
		return this.registerTotal;	
	}
	
	public BigDecimal payForCart(String paymentOption, BigDecimal purchaseTotal) throws IOException {
		
		InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
		BufferedReader buff = new BufferedReader(reader);
		
		System.out.println("Tender Recieved:\t");
		String input = new String(buff.readLine());
		
		// Check to make sure that the input is a valid dollar amount
		while( !(input.matches("\\d+(?:.(\\d+))?")) ) {
			input = buff.readLine();
		}
		
		BigDecimal tenderRec = new BigDecimal(input);
		
		if(tenderRec.compareTo(purchaseTotal) == 0) {
			
			setDrawerStateOpen();
			this.registerTotal = registerTotal.add(purchaseTotal);
			
			System.out.println("Change: $0");
			setDrawerStateClosed();

			return BigDecimal.ZERO;
		}
		else if(tenderRec.compareTo(purchaseTotal) == 1) {
			
			setDrawerStateOpen();
			registerTotal = registerTotal.add(purchaseTotal);
			
			System.out.println("Change: $" + tenderRec.subtract(purchaseTotal));
			setDrawerStateClosed();

			return BigDecimal.ZERO;
		}
		else if(tenderRec.compareTo(purchaseTotal) == -1 && tenderRec.compareTo(BigDecimal.ZERO) > 0) {
			setDrawerStateOpen();
			System.out.println("Balance: " + purchaseTotal.subtract(tenderRec));
			purchaseTotal = purchaseTotal.subtract(tenderRec);
			setDrawerStateClosed();
			return purchaseTotal;
		}
		else
			return purchaseTotal;
	}

	public BigDecimal scanItems() throws IOException, SQLException {
		
		InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
		BufferedReader buff = new BufferedReader(reader);
		String scanIn = "";
		BigDecimal purTotal = new BigDecimal(0);
		BigDecimal newItem = new BigDecimal(0);
		
		// Good enough for now, maybe change later
		boolean moreItems = true;

		while(moreItems) {
			
			System.out.println("Enter Item");
			scanIn = buff.readLine();

			if(scanIn.contentEquals("Done")) {
				moreItems=false;
			}
			else if(scanIn.contentEquals("Cancel")) {
				// Canceling a purchase should do more without failure 
				moreItems=false;
				purTotal = BigDecimal.ZERO;
			}
			// Checks if the input matches a string of digits 
			else if(scanIn.matches("^[0-9]+$")){
				try {
				newItem = mainDatabase.getPrice(scanIn);
				} catch (SQLException sqlEx) {
					sqlEx.getMessage();
					System.out.println("Unavailable");
				}
				// Add the returned price to the total
				purTotal = purTotal.add(newItem);
			}
		}			
		return purTotal;
	}
	
	public void transactionHistory(String orderID) throws SQLException {
		// Call a Database function to get the items from that order
		mainDatabase.getOrderDetails(orderID);
	}

	public boolean isOpen() {
		return drawerState;
	}
	
	public String saveTransaction() {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		
		String orderID = new String();
		
		if(customerCounter >= 100) {
		    orderID = (dateFormat.format(date) + (++customerCounter));
		}
		else if(customerCounter >= 10) {
		    orderID = (dateFormat.format(date) + "0" + (++customerCounter));
		}
		else {
		    orderID = (dateFormat.format(date) + "00" + (++customerCounter));
		}
		
		// Do some stuff to add the transaction to the DB
		mainDatabase.saveOrderDetails(orderID, "Dameka Dowdy", null, this.registerUser, purTotal);
		
		return orderID;	
	}
}
