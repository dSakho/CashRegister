package registerApplication;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Scanner;

public class Application {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
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

			String inputNewRegTotal = buff.readLine();
			if (inputNewRegTotal.matches("^[0-9]+$") || inputNewRegTotal.matches("^[0-9]*.[0-9]+$")) {
				BigDecimal amountAdded = new BigDecimal(inputNewRegTotal);
				reg.setNewRegisterTotal(amountAdded);
			}
			
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
			
			genInput = buff.readLine();
			if(genInput.contentEquals("CLOSE DRAWER")) {
				runApp = false;
				System.out.println("Goodbye");
				continue;
			}
			else {
				continue;
			}
			
		}
	}
}
