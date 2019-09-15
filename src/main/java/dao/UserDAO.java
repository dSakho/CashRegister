package dao;

import java.util.List;
import java.util.Set;

import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import authorization.User;

public interface UserDAO {

	@SqlQuery("SELECT username, password, user_id FROM Users")
	List<User> getAllUsers();
	
	@SqlQuery("SELECT username, password, user_id FROM Users WHERE username = ?")
	User getUserById(String username);

	@SqlUpdate("SELECT role FROM Roles WHERE username = ?")
	Set<String> getUserRolesById(String username);
	
	@SqlQuery("SELECT password FROM Users WHERE username = ?")
	String getPasswords(String username);
	
	// THE REST IS NOT NECESSARY TO VALIDATE A USER
	////////////////////////////////////////////////////
	
	@SqlUpdate("INSERT INTO Users (username, password) VALUES (?, ?)")
	@GetGeneratedKeys
	int addUser(String username, String password);
	
	@SqlUpdate("UPDATE Users SET username = ? && password = ? WHERE user_id = ?")
	int updateUserCredentials(String username, String password, int user_id);
	
	@SqlUpdate("DELETE FROM Users WHERE user_id = ?")
	int deleteUser(int user_ig);
	
	@SqlUpdate("DELETE FROM Roles WHERE user_id = ? && role = ?")
	int deleteRole(String user_id, String role);
	
	@SqlUpdate("INSERT INTO Roles (user_id, role) VALUES (?, ?)")
	int grantRole(String user_id, String role);
	
}
