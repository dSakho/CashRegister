package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import dao.entity.Sold_Products;

public class SoldProductsMapper implements RowMapper<Sold_Products> {

	@Override
	public Sold_Products map(ResultSet rs, StatementContext ctx) throws SQLException {
		return new Sold_Products(rs.getString("Order_ID"), rs.getBigDecimal("Price"), rs.getString("Product_Name"));
	}

}
