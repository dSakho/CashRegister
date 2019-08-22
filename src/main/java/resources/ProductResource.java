package resources;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

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
import service.products.ProductService;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {
	
	private ProductService productService;

	public ProductResource(ProductService productService) {
		super();
		this.productService = productService;
	}
	
	@GET
	public Response FetchAllProducts(@QueryParam("supplier") OptionalInt supplier, @QueryParam("on_sale") Optional<Boolean> getProductsOnSale) {
		
		List<Product> products = this.productService.getProducts();
		
		if(products != null) {	

			if(getProductsOnSale.isPresent() && supplier.isPresent()) {
				
				List<Product> productsOnSale =
						products.stream().filter(p->p.isOnSale()==getProductsOnSale.get()).collect(Collectors.toList());
				
				List<Product> productsWithSaleStatusFromSupplier =
						productsOnSale.stream().filter(p->p.getSupplier_ID()==supplier.getAsInt()).collect(Collectors.toList());
				
				return Response
						.status(Status.OK)
						.entity(productsWithSaleStatusFromSupplier)
						.build();
			}
			
			else if(getProductsOnSale.isPresent()) {
				
				List<Product> productsOnSale =
						products.stream().filter(p->p.isOnSale()==getProductsOnSale.get()).collect(Collectors.toList());
			
			return Response
					.status(Status.OK)
					.entity(productsOnSale)
					.build();
			}
			
			else if(supplier.isPresent()) {
				List<Product> productsFromSupplier =
						products.stream().filter(p->p.getSupplier_ID()==supplier.getAsInt()).collect(Collectors.toList());
				
				return Response
						.status(Status.OK)
						.entity(productsFromSupplier)
						.build();
			}
			return Response
					.status(Status.OK)
					.entity(products)
					.build();
		}
		throw new WebApplicationException(Status.NOT_FOUND);	
	}
	
	@GET
	@Path("/{id}/price")
	public BigDecimal FetchPrice(@PathParam("id") String productId) {
		
		final Product product= this.productService.scanAndGetProduct(productId);
		if(product != null) {
			return product.getPrice();
		}			
		throw new WebApplicationException(Status.NOT_FOUND);
	}
	
	@GET
	@Path("/{id}")
	public Response FetchProduct(@PathParam("id") String productId) {
		
		final Product product= this.productService.scanAndGetProduct(productId);
		if(product != null) {
			return Response
					.status(Status.OK)
					.entity(product)
					.build();
		}			
		throw new WebApplicationException(Status.NOT_FOUND);
	}
	
	@POST
	public Response postProduct(Product product) {
		
		BigDecimal productPrice = product.getPrice();
		String productName = product.getName();
		String productPID = product.getId();
		int productSupplier = product.getSupplier_ID();
		boolean productOnSale = product.isOnSale();
		String date_added = product.getDate_added();
		
		if(this.productService.scanAndGetProduct(productPID) != null) {
			throw new WebApplicationException(Status.BAD_REQUEST);
		}
		
		final int count = this.productService.createProduct(productPrice, productName, productSupplier, productOnSale, productPID, date_added);
		
		if(count == 1) {
			return Response
					.status(Status.OK)
					.entity(product)
					.build();
		}
		throw new WebApplicationException(Status.NOT_FOUND);	
	}
	
	@PUT
	@Path("/{id}")
	public Response updateProduct(@PathParam("id") String productPID, Product product){
		
		BigDecimal productPrice = product.getPrice();
		String productName = product.getName();
		int productSupplier = product.getSupplier_ID();
		boolean productOnSale = product.isOnSale();
		
		Product originalProduct = this.productService.scanAndGetProduct(productPID);
		
		if(originalProduct == null) {
			throw new WebApplicationException(Status.NOT_FOUND);	
		}
		
		this.productService.updateProduct(productPrice, productName, productSupplier, productOnSale, productPID);
		
		return Response
				.status(Status.OK)
				.entity(product)
				.build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteProduct(@PathParam("id") String productId) {
		
		Product deletedProduct = this.productService.scanAndGetProduct(productId);

		if(deletedProduct == null) {
			throw new WebApplicationException(Status.NOT_FOUND);	
		}
		
		this.productService.deleteProduct(productId);
		
		return Response
				.status(Status.OK)
				.entity(deletedProduct)
				.build();
	}
}
