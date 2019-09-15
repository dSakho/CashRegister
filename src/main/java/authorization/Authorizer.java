package authorization;

import java.util.Optional;

import dao.UserDAO;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

public class Authorizer implements Authenticator<BasicCredentials, User>{

	private UserDAO userdao;

	public Authorizer(UserDAO userdao) {
		this.userdao = userdao;
	}
	
	@Override
	public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
		
		/* 
		 * 		When Given Credentials I Want To 
		 * 			1. Verify That The credentials.username Is In The Database Of Users
		 * 			2. Verify That The credentials.password Is Equal To The password that is for the entry of that username In The Database Of Users
		 * 
		 * 		If both of the above return true then I return a User object
		 * 			Else I return an empty user
		 */
	
		User user = userdao.getUserById(credentials.getUsername());
		
		if(user.getName() == credentials.getUsername() && user.getPassword() == credentials.getPassword()) {
			return Optional.of(new User(user, userdao.getUserRolesById(user.getName())));
		}
		return Optional.empty();
	}
}
