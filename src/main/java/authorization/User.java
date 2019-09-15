package authorization;

import java.security.Principal;
import java.util.Set;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class User implements Principal {

    private final String name;
    
    private final String password;
    
    private final int user_id;
 
    private Set<String> roles;
    
    public User(User user, Set<String> roles) {
    	this.name = user.getName();
        this.password = user.getPassword();
        this.user_id = user.getUser_id();
        this.setRoles(roles);
        
    }
    public User(String name, String password, int user_id) {
        this.name = name;
        this.password = password;
        this.user_id = user_id;
    }

    @ColumnName("username")
	public String getName() {
		return name;
	}

    @ColumnName("password")
	public String getPassword() {
		return password;
	}

    @ColumnName("user_id")
	public int getUser_id() {
		return user_id;
	}
    
	public Set<String> getRoles() {
		return roles;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
    
}
