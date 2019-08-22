package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import authorization.User;

public class UserMapper implements RowMapper<User> {

	@Override
	public User map(ResultSet rs, StatementContext ctx) throws SQLException {
		return new User(
				rs.getString("username"), 
				rs.getString("password"),
				rs.getInt("user_id"));
		}
}
