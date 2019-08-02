package service.products;

import java.math.BigDecimal;

public interface ProductService {

	public BigDecimal scanAndGetPrice(final String id);
	
}
