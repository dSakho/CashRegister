package register;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public class Application {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException, InterruptedException {
		
		Authorized crew = new Authorized();
		boolean runApp = true;
		InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
		BufferedReader buff = new BufferedReader(reader);
		System.out.print("Enter Username:\t");
		String genInput = buff.readLine();
		System.out.print("Enter Passcode:\t");
		String pass = buff.readLine();
		
		if(crew.isAuthorized(genInput, pass) == false) {
			System.out.print("Invalid User!\n");
			return;
		}
		
		Register reg = new Register(genInput);

		while(runApp) {

			System.out.println("Choose a function");
			String option = buff.readLine();

			switch (option) {
			case "1":
				
/////////////// ADD MONEY TO REGISTER
				System.out.println("Amount to be added:\t");
				String addAmountToRegInput = buff.readLine();
				if (addAmountToRegInput.matches("^[0-9]+$") || addAmountToRegInput.matches("^[0-9]*.[0-9]{2}$")) {
					BigDecimal tempNum = reg.getRegTotal();
					BigDecimal amountAdded = new BigDecimal(addAmountToRegInput);
					reg.setNewRegisterTotal(amountAdded.add(tempNum));
					System.out.println("Total in Register\t$" + reg.getRegTotal());
				}
				break;		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			case "2":
				
/////////////// REMOVE MONEY FROM REGISTER
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				break;
				
			case "3":
			
/////////////// LOG OUT OF REGISTER
					if(true) {
						System.out.println("Goodbye");
						runApp = false;
						continue;
						}
					break;
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			case "4":

//////////////// SEE TRANSACTION HISTORY
				/**
				 * I need to add a method in the Register class to add info about each
				 * transaction to a database. Then create another method to query that info 
				 * and output it.
				 **/
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
			case "5":
				BigDecimal total = reg.scanItems();
				System.out.println("Total:\t$" + total.toString());
				// Select option to pay with Cash or Card
				genInput = buff.readLine();

				// I think this function should handle the payment and return a message about whether it was a success or failure
				reg.payForPurchase(genInput);

				// In theory there would some type of way to check this
				while(reg.isOpen())
					System.out.println("Close the drawer");

				// Create another Register method for the receipt (Email, Print or Both)
				// Maybe the Application itself should store the info from this purchase in the database
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
			default:
				continue;
				
			}
		}
	}
}
