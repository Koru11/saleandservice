package Model;

public class SaleModel {
	private String saleorderid;
	private String itemid;
	private String itemname;
	private String customerid;
	private String serial;
	private String customername;
	private String address;
	private String phone;
	private String email;
	private String brandid;
	private String typeid;
	private int currentprice;
	private String warranty;
	private String remark;
	private String staffid;
	private String date;
	private String paymenttype;
	private int amount;

	public String getPaymenttype() {
		return paymenttype;
	}

	public void setPaymenttype(String paymenttype) {
		this.paymenttype = paymenttype;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStaffid() {
		return staffid;
	}

	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}

	public SaleModel() {

	}

	public SaleModel(String saleorderid, String itemid, String itemname, String customerid, String serial,
			String customername, String address, String phone, String email, String brandid, String typeid,
			int currentprice, String warranty, String remark, String staffid) {
		
		this.saleorderid=saleorderid;
		this.itemid=itemid;
		this.itemname=itemname;
		this.customerid=customerid;
		this.serial=serial;
		this.customername=customername;
		this.address=address;
		this.phone=phone;
		this.email=email;
		this.brandid=brandid;
		this.typeid=typeid;
		this.currentprice=currentprice;
		this.warranty=warranty;
		this.remark=remark;
	}

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

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
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

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBrandid() {
		return brandid;
	}

	public void setBrandid(String brandid) {
		this.brandid = brandid;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public int getCurrentprice() {
		return currentprice;
	}

	public void setCurrentprice(int currentprice) {
		this.currentprice = currentprice;
	}

	public String getWarranty() {
		return warranty;
	}

	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
