package service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import dao.ProductDAO;
import dao.entity.Product;
import service.products.ProductService;
import service.products.ProductServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

	@Mock
	private ProductDAO productDao;
	
	@Test
	public void TestScanAndGetPrice_Success() {
		Product productMock = mock(Product.class);
		productMock.setPrice(new BigDecimal(90.06));
		
		when(productDao.getProductByID(any())).thenReturn(productMock);
		
		ProductService service = new ProductServiceImpl(productDao);
		final BigDecimal price = service.scanAndGetPrice("123");
		
		assertThat(price).isEqualTo(productMock.getPrice());
	}
	
	@Test
	public void TestScanAndGetPrice_NullProduct() throws Exception {
		
		when(productDao.getProductByID(any())).thenReturn(null);
		
		ProductService service = new ProductServiceImpl(productDao);
		final BigDecimal price = service.scanAndGetPrice("123");

		assertThat(price).isNull();
	}

}
