/**
 * 
 */
package service.products;

import java.math.BigDecimal;
import java.util.List;

import dao.ProductsDAO;
import dao.entity.Product;

/**
 * @author KD
 *
 * 
 */
public final class ProductServiceImpl implements ProductService {

	private ProductsDAO productDao;
	
	/**
	 * @param productDao
	 */
	public ProductServiceImpl(ProductsDAO productDao) {
		super();
		this.productDao = productDao;
	}

	@Override
	public List<Product> getProducts() {
		return this.productDao.getAllProducts();
		
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
	public int createProduct(BigDecimal price, String name, int supplierID, boolean sale, String productID, String date_added) {

		int count = this.productDao.addProduct(productID, name, price, supplierID, sale, date_added);
		if(count == 1) {
			return count;
		}
		return 0;
	}

	@Override
	public int updateProduct(BigDecimal price, String name, int supplierID, boolean sale, String productID) {
		int count = this.productDao.updateProduct(name, price, supplierID, sale, productID);
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
