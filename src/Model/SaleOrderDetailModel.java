package Model;

public class SaleOrderDetailModel {

	private String saleorderid;
	private String itemid;
	private String customerid;
	private String serial;
	private int price;

	public String getSaleorderid() {
		return saleorderid;
	}

	public void setSaleorderid(String saleorderid) {
		this.saleorderid = saleorderid;
	}

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public SaleOrderDetailModel() {

	}

	public SaleOrderDetailModel(String saleorderid, String itemid, String custoemrid, String serial) {
		this.saleorderid = saleorderid;
		this.itemid = itemid;
		this.customerid = customerid;
		this.serial = serial;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
