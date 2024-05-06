package Model;

public class SaleOrderModel {
	private String saleorderid;
	private String staffid;
	private String paymenttype;
	private String date;
	private int amount;
	
	public SaleOrderModel() {
		
	}
	
	public SaleOrderModel(String saleorderid,String staffid,String paymenttype, String date) {
		this.saleorderid = saleorderid;
		this.staffid = staffid;
		this.paymenttype = paymenttype;
		this.date = date;	
	}
	
	public String getSaleorderid() {
		return saleorderid;
	}
	public void setSaleorderid(String saleorderid) {
		this.saleorderid = saleorderid;
	}
	public String getStaffid() {
		return staffid;
	}
	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	

}
