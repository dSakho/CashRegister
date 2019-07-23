package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import dao.entity.Products;

public class FoodMapper implements RowMapper<Products> {

	public String productIdentifier;
	public Double price;
	public String productName;
	public String productSupplier;
	public boolean onSale;
	
	@Override
	public Products map(ResultSet rs, StatementContext ctx) throws SQLException {
		return new Products(rs.getString("Serial_Number"), rs.getBigDecimal("Price"), rs.getString("Product_Name"), rs.getString("Supplier"), rs.getBoolean("On_Sale"));
	}
}
	  			