package service.suppliers;

import java.util.List;

import dao.SupplierDAO;
import dao.entity.Supplier;

public class SupplierServiceImpl implements SupplierService {

	
	private SupplierDAO supplierDAO;
	
	public SupplierServiceImpl(SupplierDAO supplierDAO) {
		super();
		this.supplierDAO = supplierDAO;
	}

	@Override
	public int addNewSupplier(String name) {
		// https://docs.oracle.com/cd/E17952_01/connector-j-en/connector-j-usagenotes-last-insert-id.html
		return supplierDAO.addNewSupplier(name);
	}

	@Override
	public List<Supplier> getAllSuppliers() {
		return supplierDAO.getAllSuppliers();
	}

	@Override
	public Supplier getSupplierByID(int id) {
		Supplier supplier = supplierDAO.getSupplierByID(id);
		if(supplier == null) {
			return null;
		}
		else {
			return supplier;
		}
	}

	@Override
	public int updateSupplierName(String name, int id) {
		Supplier supplier = supplierDAO.getSupplierByID(id);
		if(supplier == null) {
			return 0;
		}
		else {
			return supplierDAO.updateSupplierName(name, id);
			}
		}

	@Override
	public int deleteSupplier(int id) {
		Supplier supplier = supplierDAO.getSupplierByID(id);
		if(supplier == null) {
			return 0;
		}
		else {
			return supplierDAO.deleteSupplier(id);
			}
		}
}
