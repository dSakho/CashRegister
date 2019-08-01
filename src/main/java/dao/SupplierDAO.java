package dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import dao.entity.Supplier;
import dao.mapper.SupplierMapper;

public interface SupplierDAO {

	@SqlUpdate("INSERT INTO Suppliers (name) VALUES (?)")
	int addNewSupplier(String name);
	
	@SqlQuery("SELECT id, name FROM Suppliers")
	@RegisterRowMapper(SupplierMapper.class)
	List<Supplier> getAllSuppliers();
	
	@SqlQuery("SELECT id, name FROM Suppliers WHERE id = ?")
	@RegisterRowMapper(SupplierMapper.class)
	Supplier getSupplierByID(int id);
	
	@SqlUpdate("UPDATE Suppliers SET name = ? WHERE id = ?")
	int updateSupplierName(String name, int id);
	
	@SqlUpdate("DELETE FROM Suppliers WHERE id = ?")
	int deleteSupplier(int id);
	
}
