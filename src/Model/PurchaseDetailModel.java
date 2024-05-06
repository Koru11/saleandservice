package Model;

public class PurchaseDetailModel {
	private String purchaseid;
	private int itemid;
	private String itemName;	
	private int purchaseprice;
    private int purchaseqty;
    
	public String getPurchaseid() {
		return purchaseid;
	}
	public void setPurchaseid(String purchaseid) {
		this.purchaseid = purchaseid;
	}
	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	public int getPurchaseprice() {
		return purchaseprice;
	}
	public void setPurchaseprice(int purchaseprice) {
		this.purchaseprice = purchaseprice;
	}
	public int getPurchaseqty() {
		return purchaseqty;
	}
	public void setPurchaseqty(int purchaseqty) {
		this.purchaseqty = purchaseqty;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
}
