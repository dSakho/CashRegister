package dao.entity;

import java.math.BigDecimal;

public class Products {
	
	public Products(String string, BigDecimal bigDecimal, String string2, String string3, boolean boolean1) {
		
	productIdentifier = string;
	price = bigDecimal;
	productName = string2;
	productSupplier = string3;
	onSale = boolean1;
	
	}
	public String productIdentifier;
	public BigDecimal price;
	public String productName;
	public String productSupplier;
	public boolean onSale;
	
}
