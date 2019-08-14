/**
 * 
 */
package service.products;

import java.math.BigDecimal;
import java.util.List;

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
	public List<Product> getProducts() {
		return this.productDao.getProducts();
		
	}

	@Override
	public Product scanAndGetProduct(String id) {
		Product p = this.productDao.getProductByID(id);
		if(p != null) {
			return p;
		}
		return null;
	}

	@Override
	public int createProduct(BigDecimal price, String name, String supplierID, boolean sale, String productID) {
		
		int count = this.productDao.addProduct(productID, price, name, supplierID, sale);
		if(count == 1) {
			return count;
		}
		return 0;
	}

	@Override
	public int updateProduct(BigDecimal price, String name, String supplierID, boolean sale, String productID) {
		int count = this.productDao.updateProduct(price, name, supplierID, sale, productID);
		if(count == 1) {
			return count;
		}
		return 0;	}

	@Override
	public int deleteProduct(String pid) {
		int count = this.productDao.deleteProduct(pid);
		if(count == 1) {
			return count;
		}
		return 0;
	}
}
