package register;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Application {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException, InterruptedException {
		
		Date date = new Date();
		ArrayList<String> history = new ArrayList<>();
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
				System.out.println(reg.getRegTotal());
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
				System.out.println(reg.getRegTotal());
				System.out.println("Amount to be removed:\t");
				String subAmountToRegInput = buff.readLine();
				if (subAmountToRegInput.matches("^[0-9]+$") || subAmountToRegInput.matches("^[0-9]*.[0-9]{2}$")) {
					BigDecimal tempNum = reg.getRegTotal();
					BigDecimal amountTaken = new BigDecimal(subAmountToRegInput);
					if(tempNum.compareTo(amountTaken) == -1)
						break;
					reg.setNewRegisterTotal(tempNum.subtract(amountTaken));
					System.out.println("Total in Register\t$" + reg.getRegTotal());
				}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				break;
				
			case "3":
			
/////////////// LOG OUT OF REGISTER
					if(option.contentEquals("3")) {
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
				int transaction = buff.read();
				if(transaction < history.size() && transaction > 0)
				{
					reg.transactionHistory(history.get(transaction-1));
				}
				
				break;
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
			case "5":
				
				// Begin section to scan items
				
				BigDecimal total = reg.scanItems();
				System.out.println("Total:\t$" + total);
				
				// End section to scan items
				
				
				// Begin section to pay for purchase
				
				// Print the balance and offer payment options
				System.out.println("Pay with Cash or Card");
				genInput = buff.readLine();
				
				// Give the input from user to the Register object as long as the total isn't 0
				while(!(total.compareTo(BigDecimal.ZERO)==0))
				{
					total = reg.payForCart(genInput, total);
					while(reg.isOpen()) {
						System.out.println("Close the drawer");
					}
				}
				
				// End section to pay for purchase

				history.add(reg.saveTransaction());
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
			default:
				continue;
				
			}
		}
	}
}
