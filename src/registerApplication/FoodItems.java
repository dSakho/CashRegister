package registerApplication;

import java.math.BigDecimal;

public class FoodItems {
	
	private String serialNumber;
	private BigDecimal price;
	private String productName;
	private String supplierID;
	private String locationID;
	private Boolean onSale;
	
	public FoodItems() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param serialNumber
	 * @param price
	 * @param productName
	 * @param supplierID
	 * @param locationID
	 * @param onSale
	 */
	public FoodItems(String serialNumber, BigDecimal price, String productName, String supplierID, String locationID,
			Boolean onSale) {
		super();
		this.serialNumber = serialNumber;
		this.price = price;
		this.productName = productName;
		this.supplierID = supplierID;
		this.locationID = locationID;
		this.onSale = onSale;
	}
	/**
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}
	/**
	 * @param serialNumber the serialNumber to set
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	/**
	 * @return the price
	 */
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
	 * @return the supplierID
	 */
	public String getSupplierID() {
		return supplierID;
	}
	/**
	 * @param supplierID the supplierID to set
	 */
	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}
	/**
	 * @return the locationID
	 */
	public String getLocationID() {
		return locationID;
	}
	/**
	 * @param locationID the locationID to set
	 */
	public void setLocationID(String locationID) {
		this.locationID = locationID;
	}
	/**
	 * @return the onSale
	 */
	public Boolean getOnSale() {
		return onSale;
	}
	/**
	 * @param onSale the onSale to set
	 */
	public void setOnSale(Boolean onSale) {
		this.onSale = onSale;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FoodItem [serialNumber=" + serialNumber + ", price=" + price + ", productName=" + productName
				+ ", supplierID=" + supplierID + ", locationID=" + locationID + ", onSale=" + onSale + "]";
	}

}
