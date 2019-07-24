package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import dao.entity.Product;

public class ProductMapper implements RowMapper<Product> {

	public String productIdentifier;
	public Double price;
	public String productName;
	public String productSupplier;
	public boolean onSale;
	
	@Override
	public Product map(ResultSet rs, StatementContext ctx) throws SQLException {
		return new Product(rs.getString("Serial Number"), rs.getBigDecimal("Price"), rs.getString("Product Name"), rs.getString("Supplier"), rs.getBoolean("Sale"));
	}
}
	  			