package dao.entity;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Sale {

	private String order_ID;
	private String pos_ID;
	private String productID;


	public Sale(String order_ID, String pos_ID, String productID) {
		this.order_ID = order_ID;
		this.pos_ID = pos_ID;
		this.productID = productID;
	}

	@ColumnName("order_ID")
	public String getOrder_ID() {
		return order_ID;
	}

	@ColumnName("pos_ID")
	public String getPos_ID() {
		return pos_ID;
	}

	@ColumnName("pid")
	public String getProductID() {
		return productID;
	}

	public void setOrder_ID(String order_ID) {
		this.order_ID = order_ID;
	}


	public void setPos_ID(String pos_ID) {
		this.pos_ID = pos_ID;
	}


	public void setProductID(String productID) {
		this.productID = productID;
	}

}
