package service.sales;

import java.util.List;

import dao.entity.Sale;

public interface SalesService {

	List<String> getProductSaleHistoryByOrderID(String order_ID);

	List<Sale> getListOfSales();

	int saveProductSale(String order_ID, String pointOfSale, String product_ID);
	
	int returnedItem(String order_ID, String pid);

	int deleteHistory(String orderID);

}
