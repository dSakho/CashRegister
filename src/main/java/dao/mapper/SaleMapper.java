package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import dao.entity.Sale;

public class SaleMapper implements RowMapper<Sale> {

	@Override
	public Sale map(ResultSet rs, StatementContext ctx) throws SQLException {
		return new Sale(rs.getString("order_ID"), rs.getString("pos_ID"), rs.getString("pid"));
	}

}
