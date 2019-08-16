package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import dao.entity.Customer;

public class CustomerMapper implements RowMapper<Customer> {

	@Override
	public Customer map(ResultSet rs, StatementContext ctx) throws SQLException {
		return new Customer(rs.getInt("id"), rs.getString("name"));
	}

}
