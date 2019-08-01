package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import dao.entity.RegisterSales;

public class RegisterSalesMapper implements RowMapper<RegisterSales> {

	@Override
	public RegisterSales map(ResultSet rs, StatementContext ctx) throws SQLException {
		return new RegisterSales(rs.getString("Order_ID"), rs.getString("Register_ID"), rs.getString("Cashier_Name"), rs.getDate("Purchase_Date"));
	}

}
