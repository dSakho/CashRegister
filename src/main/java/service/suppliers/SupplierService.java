package service.suppliers;

import java.util.List;

import dao.entity.Supplier;

public interface SupplierService {

	int addNewSupplier(String name);
	
	List<Supplier> getAllSuppliers();

	Supplier getSupplierByID(int id);
	
	int updateSupplierName(String name, int id);
	
	int deleteSupplier(int id);
}
