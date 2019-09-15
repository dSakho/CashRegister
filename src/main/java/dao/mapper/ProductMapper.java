package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import dao.entity.Product;

public class ProductMapper implements RowMapper<Product> {
	
	@Override
	public Product map(ResultSet rs, StatementContext ctx) throws SQLException {
		return new Product(rs.getString("id"),
				rs.getString("name"),
				rs.getBigDecimal("price"),
				rs.getInt("supplier_id"),
				rs.getBoolean("onSale"),
				rs.getString("date_added"));
	}
}
	  			