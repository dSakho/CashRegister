package resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import dao.entity.Supplier;
import service.suppliers.SupplierService;

@Path("/suppliers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SupplierResource {

	private SupplierService supplierService;

	public SupplierResource(SupplierService supplierService) {
		super();
		this.supplierService = supplierService;
	}
	
	@GET
	public Response FetchAllSuppliers() {
		
		List<Supplier> suppliers = supplierService.getAllSuppliers();
		
		if(suppliers != null) {
			return Response
					.status(Status.OK)
					.entity(suppliers)
					.build();
		}
		throw new WebApplicationException(Status.NOT_FOUND);
	}
	
	@GET
	@Path("/{id}")
	public Response FetchSupplierById(@PathParam("id") int supplier_Id) {
		
		Supplier supplier = supplierService.getSupplierByID(supplier_Id);
		
		if(supplier != null) {
			return Response
					.status(Status.OK)
					.entity(supplier)
					.build();
		}
		
		throw new WebApplicationException(Status.NOT_FOUND);
	}
	
	@POST
	public Response SaveSupplier(String supplierName) {
		
		// I need to get this to return the id that is generated
		int supplierID = supplierService.addNewSupplier(supplierName);
		
		if(supplierID > 1) {
			
			Supplier supplier = supplierService.getSupplierByID(supplierID);
			return Response
					.status(Status.OK)
					.entity(supplier)
					.build();
		}
		return null;
	}
	
	@PUT
	@Path("/{id}")
	public Response updateSupplier(@PathParam("id") int supplier_Id, @PathParam("name") String newName) {
		Supplier supplier = supplierService.getSupplierByID(supplier_Id);
		
		if(supplier != null) {
			supplierService.updateSupplierName(newName, supplier_Id);
			supplier = supplierService.getSupplierByID(supplier_Id);
			
			return Response
					.status(Status.OK)
					.entity(supplier)
					.build();
		}
		
		throw new WebApplicationException(Status.NOT_FOUND);

	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteSupplier(@PathParam("id") int supplier_id) {
		
		Supplier supplier = supplierService.getSupplierByID(supplier_id);
		
		if(supplier != null) {
			
			int suppliersDeleted = supplierService.deleteSupplier(supplier_id);
			
			if(suppliersDeleted == 1) {
				return Response
						.status(Status.OK)
						.entity(supplier)
						.build();
				}
			}
		throw new WebApplicationException(Status.NOT_FOUND);
		}
}
