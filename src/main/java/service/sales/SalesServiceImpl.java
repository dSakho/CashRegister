package service.sales;

import java.util.List;

import dao.SalesDAO;
import dao.entity.Sale;

public class SalesServiceImpl implements SalesService {

	private SalesDAO salesDAO;
	
	public SalesServiceImpl(SalesDAO salesDAO) {
		super();
		this.salesDAO = salesDAO;
	}

	@Override
	public List<String> getProductSaleHistoryByOrderID(String order_ID) {
		return salesDAO.getProductsSoldHistoryByOrderID(order_ID);
	}

	@Override
	public List<Sale> getListOfSales() {
		return salesDAO.getListOfSales();
	}

	@Override
	public int saveProductSale(String productID, String pointOfSale, String pid) {
		return salesDAO.saveProductSale(productID, pointOfSale, pid);
	}

	@Override
	public int returnedItem(String order_ID, String pid) {
		return salesDAO.returnedItem(order_ID, pid);
	}

	@Override
	public int deleteHistory(String orderID) {
		
		if(salesDAO.getProductsSoldHistoryByOrderID(orderID) != null) {
			return salesDAO.deleteHistory(orderID);
		}
		else {
			return 0;
		}
	}

}
