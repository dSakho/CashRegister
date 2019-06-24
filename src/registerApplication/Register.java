package registerApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import registerApplication.Database;


public class Register {

	// Creating an instance of the Database class with every instance of the Register class
	private Database mainDatabase;

	// The total amount of money in the register - Starts off as $0
	private BigDecimal registerTotal = new BigDecimal (0);
	
	// When the class is called the register's serial number should be available
	private final String regID = new String("182010460");
	
	// The user of the register will be included saved by the register
	private String regUser = new String("");
	
	// Counter necessary to keep track of when the register is open
	private boolean drawerState = false;
	
	// The total value of goods scanned
	private BigDecimal purTotal = new BigDecimal(0);
	
	// The constructor is going to need a valid USERID and total to begin with
	public Register(String usrID) throws ClassNotFoundException, SQLException {
		setUsername(usrID);
		this.mainDatabase = new Database();
		System.out.println("Welcome To The Party!");
	}
	
	// Call this method when first opening the register to insert change
	 public void setNewRegisterTotal(BigDecimal money) {
		registerTotal = this.registerTotal.add(money);
	}
	
	// Call this method when first opening the register to store the user
	private void setUsername(String s) {
		regUser = this.regID.concat(s);
	}
	
	private void setDrawerStateOpen() {
		drawerState = true;
	}
	
	private void setDrawerStateClosed() {
		drawerState = false;
	}	
	
	public String getRegID() {
		return this.regID;
	}
	
	public String getRegUser() {
		return this.regUser;
	}
	
	public BigDecimal getRegTotal() {
		return this.registerTotal;	
	}
	
	private void payWithCash(BigDecimal balance, BigDecimal tender) {

		// signum() returns the sign of the BigDecimal object
		if(balance.signum() == -1 || tender.signum() == -1) {
			System.out.println("Error: Incorrect Amount!");
		}
		else {
			setDrawerStateOpen();
			registerTotal = registerTotal.add( (tender.subtract(balance)) );
		} 
	}
	
	// Going to have the Register Class handle the input
	public BigDecimal scanItems() throws IOException, SQLException {
		
		InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
		BufferedReader buff = new BufferedReader(reader);
		String scanIn = "";
		BigDecimal purTotal = new BigDecimal(0);
		BigDecimal newItem = new BigDecimal(0);
		
		// Good enough for now, maybe change later
		@SuppressWarnings("deprecation")
		Boolean moreItems = new Boolean(true);

		while(moreItems) {
			System.out.println("Enter Item");
			scanIn = buff.readLine();

			if(scanIn.contentEquals("Done")) {
				moreItems=false;
				mainDatabase.closeConnection();
			}
			else if(scanIn.contentEquals("Cancel")) {
				// Canceling a purchase should do more without failure 
				moreItems=false;
				mainDatabase.closeConnection();
			}
			// Checks if the input matches a string of digits 
			else if(scanIn.matches("^[0-9]+$")){	
				// Add a method in Database class to take the barcode string and return the price of the item
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
	
	
	// Cash it will call the Private payWithCash() method
	// Credit and Debit will 
	public boolean payForPurchase(String meth) {
		
		switch(meth) {
		
		case ("Cash"):
			BigDecimal tender = new BigDecimal(0);
			
			do {
				// Read some input to get the tender
				// Tender can never be less than the purchase price
			} while((tender.subtract(purTotal)).signum() == -1);
			
			payWithCash(purTotal, tender);
			setDrawerStateClosed();
			break;
		case ("Credit"):
			break;
		case ("Debit"):
			break;
		}
		return true;
	}
	
	public boolean isOpen() {
		return drawerState;
	}
	

}
