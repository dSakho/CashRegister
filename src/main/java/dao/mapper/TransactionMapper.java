package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import dao.entity.Transaction;

public class TransactionMapper implements RowMapper<Transaction> {

	@Override
	public Transaction map(ResultSet rs, StatementContext ctx) throws SQLException {
		return new Transaction(rs.getString("id"), rs.getBigDecimal("total"), rs.getInt("customer_ID"), rs.getString("date"));
	}

}
