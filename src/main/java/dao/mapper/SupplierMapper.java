package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import dao.entity.Supplier;

public class SupplierMapper implements RowMapper<Supplier> {

	@Override
	public Supplier map(ResultSet rs, StatementContext ctx) throws SQLException {
		return new Supplier(rs.getInt("id"), rs.getString("name"));
	}
}
