package service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import dao.ProductsDAO;
import dao.entity.Product;
import service.products.ProductService;
import service.products.ProductServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

	@Mock
	private ProductsDAO productDao;
	
	@Test
	public void TestScanAndGetProduct() {
		
		Product productMock = mock(Product.class);
		productMock.setPrice(new BigDecimal(90.06));
		
		when(productDao.getProductByID(any())).thenReturn(productMock);
		
		
		ProductService service = new ProductServiceImpl(productDao);
		final Product product = service.scanAndGetProduct("123");

		assertThat(product).isNull();
		
		final BigDecimal price = product.getPrice();
		
		assertThat(price).isEqualTo(productMock.getPrice());
	}
	
	@Test
	public void TestGetAllProducts() throws Exception {
		
        final List<Product> mockedList = mock(List.class);
        
		assertThat(mockedList).isNotNull();
		
		mockedList.add(new Product());
		mockedList.add(new Product());
		
		mockedList.get(0).setOnSale(true);
		mockedList.get(1).setName("Cabbage");
		
		when(productDao.getAllProducts()).thenReturn(mockedList);
		
		ProductService service = new ProductServiceImpl(productDao);
		final List<Product> products = service.getProducts();

		assertThat(products).isNotNull();
		assertThat(products.get(1).getName()).isEqualToIgnoringWhitespace("Cabbage");
		assertThat(products.get(0).isOnSale()).isEqualTo(true);
		
	}
	
	@Test
	public void TestCreateProduct() {
		
		Product productMock = mock(Product.class);
		productMock.setPrice(new BigDecimal(26.25));
		productMock.setOnSale(true);
		productMock.setId("3452");
		productMock.setName("Beans");
		productMock.setSupplier_ID(1232);
		
		when(productDao.getProductByID("3452")).thenReturn(productMock);
		when(productDao.addProduct(any(), any(), any(), any(), any())).thenReturn(1);
		
		ProductService service = new ProductServiceImpl(productDao);
		int productsCreated = service.createProduct(null, null, null, false, null);
		
		assertThat(productsCreated).isEqualTo(1);
		
	}
	
	@Test
	public void TestUpdateProduct() {
		
		Product productMock = mock(Product.class);
		productMock.setPrice(new BigDecimal(26.25));
		productMock.setOnSale(true);
		productMock.setSupplier_ID(3452);
		productMock.setName("Beans");
		productMock.setSupplier_ID(90666);
		
		when(productDao.getProductByID(any())).thenReturn(productMock);
		when(productDao.updateProduct(any(), any(), any(), any(), any())).thenReturn(1);
		
		ProductService service = new ProductServiceImpl(productDao);
		int productsUpdated = service.createProduct(null, null, null, false, null);

		assertThat(productsUpdated).isEqualTo(1);

	}
	
	@Test
	public void TestDeleteProduct() {
		
		when(productDao.deleteProduct(any())).thenReturn(1);
		ProductService service = new ProductServiceImpl(productDao);
		int productsDeleted = service.deleteProduct("767");

		assertThat(productsDeleted).isEqualTo(1);
		
	}

}
