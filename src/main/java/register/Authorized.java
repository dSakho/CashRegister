package register;

import java.util.HashMap;

public class Authorized {
	
	private HashMap<String, String> usernamePassword = new HashMap<>();

	// Later I can have this look at another database to check validity
	public Authorized() {
		usernamePassword.put("Kevin", "12345");
		usernamePassword.put("Kam", "54321");
		usernamePassword.put("Dowdy", "00000");
		usernamePassword.put("KIPP", "12345");
		usernamePassword.put("Java", "01234");
		usernamePassword.put("Natdizzle", "sunshinesandrainbows");
	}
	
	private void addNewPerson(String name, String password) {
		usernamePassword.put(name, password);
	}
	
	public boolean isAuthorized(String name, String password) {
		boolean valid = false;
		if (usernamePassword.containsKey(name) && (usernamePassword.get(name).equals(password) == true))
		{
			valid = true;
			return valid;
		}
		return valid;
	}

}
