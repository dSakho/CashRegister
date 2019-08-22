package resources;

import java.util.ArrayList;
import java.util.List;

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

import dao.entity.Product;
import dao.entity.Sale;
import service.products.ProductService;
import service.sales.SalesService;

@Path("/sales")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SaleResource {

	private SalesService salesService;
	private ProductService productService;
	
	public SaleResource(SalesService salesService, ProductService productService) {
		super();
		this.salesService = salesService;
		this.productService = productService;
	}
	
	@GET
	public Response FetchAllSales() {
	
		List<Sale> sales = salesService.getListOfSales();
		
		if(sales != null) {
			return Response
					.status(Status.OK)
					.entity(sales)
					.build();
		}
		
		throw new WebApplicationException(Status.NOT_FOUND);
	}
	
	@GET
	@Path("/{id}")
	public Response GetReceiptForOrderID(@PathParam("id") String order_Id) {
	List<String> productIdsFromSale = salesService.getProductSaleHistoryByOrderID(order_Id);
	List<Product> productsOrdered = new ArrayList<Product>();
	
	if(productIdsFromSale.size() > 0) {
		
		productIdsFromSale.stream().forEach(
				pid -> productsOrdered.add(productService.scanAndGetProduct(pid))
				);
	}
	
	if(productsOrdered.size() > 0) {
		return Response
				.status(Status.OK)
				.entity(productsOrdered)
				.build();
	}
	throw new WebApplicationException(Status.NOT_FOUND);

	}
	
	@POST
	public Response SaveSale(@QueryParam("pos") String pos, @QueryParam("order_id") String order_id, List<String> pids) {
		
		pids.stream()
		.forEach(pid -> salesService.saveProductSale(order_id, pos, pid));
		
		List<Product> productsOrdered = new ArrayList<Product>();

		pids.stream().forEach(
					pid -> productsOrdered.add(productService.scanAndGetProduct(pid))
					);
		
		return Response
				.status(Status.OK)
				.entity(productsOrdered)
				.build();	
	}
	
	@PUT
	@Path("/{id}")
	public Response returnItem(@PathParam("order_ID") String order_ID, String pidInReciept) {
		
		if(salesService.getProductSaleHistoryByOrderID(order_ID) == null) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		
		else {
			
			int countReturned = salesService.returnedItem(order_ID, pidInReciept);
			List<String> productIdsFromSale = salesService.getProductSaleHistoryByOrderID(order_ID);
			List<Product> productsOrdered = new ArrayList<Product>();
			
			if(productIdsFromSale.size() > 0) {
				
				productIdsFromSale.stream().forEach(
						pid -> productsOrdered.add(productService.scanAndGetProduct(pid))
						);
			}
			
			if(productsOrdered.size() > 0) {
				return Response
						.status(Status.OK)
						.entity(productsOrdered)
						.build();
			}
		}
		throw new WebApplicationException(Status.NOT_FOUND);
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteSale(@PathParam("id") String order_ID) {
		
		if(salesService.getProductSaleHistoryByOrderID(order_ID) == null) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		
		salesService.deleteHistory(order_ID);
		
		return Response
				.status(Status.OK)
				.build();

	}
}
