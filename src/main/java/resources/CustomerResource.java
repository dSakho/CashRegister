package resources;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import authorization.User;
import dao.entity.Customer;
import io.dropwizard.auth.Auth;
import service.customers.CustomerService;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {
	
	private CustomerService customerService;

	public CustomerResource(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}
	
	@GET
	public Response FetchAllCustomers(@Auth User user, @QueryParam("begins-with") Optional<String> nameStartsWith) {
		List<Customer> customers = customerService.getAllCustomers();
		
		if(customers != null) {
			return Response
					.status(Status.OK)
					.entity(customers)
					.build();
		}
		
		throw new WebApplicationException(Status.NOT_FOUND);
	}
	
	@GET
	@Path("/{id}")
	public Response FetchCustomerById(@PathParam("id") int customer_Id) {
		Customer customer = customerService.getCustomerById(customer_Id);
		if(customer != null) {
			return Response
					.status(Status.OK)
					.entity(customer)
					.build();
		}
		
		throw new WebApplicationException(Status.NOT_FOUND);
	}
	
	@POST
	public Response CreateCustomer(@QueryParam("name") String customer_name) {
		
		int customerID = -1;
		customerID = customerService.saveNewCustomerInfo(customer_name);
		
		if(customerID >= 1) {
			Customer customer = customerService.getCustomerById(customerID);
			return Response
					.status(Status.OK)
					.entity(customer)
					.build();
			
		}
		throw new WebApplicationException(Status.BAD_REQUEST);
	}
	
	@PUT
	@Path("/{id}")
	public Response updateCustomerName(@QueryParam("name") String new_customer_name, @PathParam("id") int customer_id) {
		Customer customer = customerService.getCustomerById(customer_id);
		if(customer != null) {
			customerService.updateCustomerName(new_customer_name, customer_id);
			customer = customerService.getCustomerById(customer_id);
			
			return Response
					.status(Status.OK)
					.entity(customer)
					.build();
		}
		
		throw new WebApplicationException(Status.NOT_FOUND);

	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteCustomer(@PathParam("id") int customer_id) {
		
		Customer customer = customerService.getCustomerById(customer_id);
		if(customer != null) {
			int customersDeleted = customerService.deleteEntry(customer_id);

			if(customersDeleted == 1) {
				customer = customerService.getCustomerById(customer_id);
				
				return Response
						.status(Status.OK)
						.entity(customer)
						.build();
			}
		}
		throw new WebApplicationException(Status.NOT_FOUND);
	}
	
}
