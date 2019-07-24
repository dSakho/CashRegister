package dao.entity;

import java.math.BigDecimal;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Product {

	public Product() {
	}

	public Product(String string, BigDecimal bigDecimal, String string2, String string3, boolean boolean1) {
		productIdentifier = string;
		price = bigDecimal;
		productName = string2;
		productSupplier = string3;
		onSale = boolean1;
	}

	private String productIdentifier;
	private BigDecimal price;
	private String productName;
	private String productSupplier;
	private boolean onSale;

	/**
	 * @return the productIdentifier
	 */
	@ColumnName("Serial Number")
	public String getProductIdentifier() {
		return productIdentifier;
	}

	/**
	 * @param productIdentifier the productIdentifier to set
	 */
	public void setProductIdentifier(String productIdentifier) {
		this.productIdentifier = productIdentifier;
	}

	/**
	 * @return the price
	 */
	@ColumnName("Price")
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return the productName
	 */
	@ColumnName("Product Name")
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the productSupplier
	 */
	@ColumnName("Supplier")
	public String getProductSupplier() {
		return productSupplier;
	}

	/**
	 * @param productSupplier the productSupplier to set
	 */
	public void setProductSupplier(String productSupplier) {
		this.productSupplier = productSupplier;
	}

	/**
	 * @return the onSale
	 */
	@ColumnName("Sale")
	public boolean isOnSale() {
		return onSale;
	}

	/**
	 * @param onSale the onSale to set
	 */
	public void setOnSale(boolean onSale) {
		this.onSale = onSale;
	}

}
