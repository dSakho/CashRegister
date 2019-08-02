package resources;

import java.math.BigDecimal;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import service.products.ProductService;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {
	
	private ProductService productService;

	/**
	 * @param productService
	 */
	public ProductResource(ProductService productService) {
		super();
		this.productService = productService;
	}
	
	@GET
	@Path("/{id}/price")
	public BigDecimal FetchPrice(@PathParam("id") String productId) {
		final BigDecimal price = this.productService.scanAndGetPrice(productId);
		if(price != null) {
			return price;
		}
			
		throw new WebApplicationException(Status.NOT_FOUND);
	}
}
