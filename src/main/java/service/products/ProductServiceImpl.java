/**
 * 
 */
package service.products;

import java.math.BigDecimal;

import dao.ProductDAO;
import dao.entity.Product;

/**
 * @author KD
 *
 * 
 */
public final class ProductServiceImpl implements ProductService {

	private ProductDAO productDao;
	
	/**
	 * @param productDao
	 */
	public ProductServiceImpl(ProductDAO productDao) {
		super();
		this.productDao = productDao;
	}

	@Override
	public BigDecimal scanAndGetPrice(String id) {
		Product p = this.productDao.getProductByID(id);
		if(p != null) {
			return p.getPrice();
		}
		return null;
	}
}
