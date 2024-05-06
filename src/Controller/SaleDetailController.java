package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import Connection.ClsDBConnection;
import Model.BrandModel;
import Model.CompanyModel;
import Model.CustomerModel;
import Model.ItemModel;
import Model.PurchaseModel;
import Model.SaleDetailModel;
import Model.SaleModel;
import Model.StaffModel;
import Model.TypeModel;

public class SaleDetailController {
	private JTextField txtComName;
	private JTextField txtCompanyAddress;
	private JTextField txtCompanyPhone;
	private JTextField txtCompanyEmail;
	public static Connection con = null;
	static {
		ClsDBConnection cls = new ClsDBConnection();
		try {
			con = cls.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Insert Fail,Inter error","Fail", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public SaleDetailModel verifySerial(SaleDetailModel sale) throws SQLException{
		//List<SaleDetailModel> list = new ArrayList<SaleDetailModel>();
		SaleDetailModel sdm=new SaleDetailModel();
		String sql = "SELECT\r\n"
				+ "    i.serial, \r\n"
				+ "    c.customerid, \r\n"
				+ "    c.customername,\r\n"
				+ "    c.phone AS `customerphone`, \r\n"
				+ "    c.email AS `customeremail`,\r\n"
				+ "    c.address AS `customeraddress`,\r\n"
				+ "    so.saleorderid,\r\n"
				+ "    so.staffid AS `salestaffid`,\r\n"
				+ "    so.type, \r\n"
				+ "    so.date, \r\n"
				+ "    so.amount,\r\n"
				+ "    i.itemid, \r\n"
				+ "    i.typeid, \r\n"
				+ "    i.brandid,\r\n"
				+ "    i.warranty, \r\n"
				+ "    i.currentprice, \r\n"
				+ "    i.remark,\r\n"
				+ "    i.itemname, \r\n"
				+ "    p.purchaseid, \r\n"
				+ "    p.companyid,\r\n"
				+ "    p.purchasedate, \r\n"
				+ "    com.companyname, \r\n"
				+ "    com.address AS `companyaddress`,\r\n"
				+ "    com.phone AS `companyphone`, \r\n"
				+ "    com.email AS `companyemail` \r\n"
				+ "FROM\r\n"
				+ "    saleandservice.item i\r\n"
				+ "LEFT OUTER JOIN \r\n"
				+ "    saleandservice.saleorderdetail sod ON sod.serial = i.serial \r\n"
				+ "LEFT OUTER JOIN\r\n"
				+ "    saleandservice.customer c ON c.customerid = sod.customerid \r\n"
				+ "LEFT OUTER JOIN \r\n"
				+ "    saleandservice.saleorder so ON so.saleorderid = sod.saleorderid \r\n"
				+ "LEFT OUTER JOIN \r\n"
				+ "    saleandservice.staff s ON s.staffid = so.staffid \r\n"
				+ "LEFT OUTER JOIN\r\n"
				+ "    saleandservice.purchasedetail pd ON i.itemid = pd.itemid \r\n"
				+ "LEFT OUTER JOIN \r\n"
				+ "    saleandservice.purchase p ON pd.purchaseid = p.purchaseid\r\n"
				+ "RIGHT OUTER JOIN \r\n"
				+ "    saleandservice.company com ON p.companyid = com.companyid\r\n"
				+ "WHERE\r\n"
				+ "    i.serial =?;\r\n"
				+ "";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, sale.getSerial());
        ResultSet rs = ps.executeQuery();
        System.out.println(rs.getRow());
        while(rs.next()) {
        	//SaleDetailModel sdm = new SaleDetailModel();
        	sdm.setSaleorderid(rs.getString("saleorderid"));
        	sdm.setItemid(rs.getInt("itemid"));
        	sdm.setItemname(rs.getString("itemname"));
        	sdm.setCustomerid(rs.getString("customerid"));
        	sdm.setCustomername(rs.getString("customername"));
        	sdm.setCustomeraddress(rs.getString("customeraddress"));
        	sdm.setCustomerphone(rs.getString("customerphone"));
        	sdm.setCustomeremail(rs.getString("customeremail"));
        	sdm.setStaffid(rs.getString("salestaffid"));
        	//sdm.setStaffname(rs.getString("staffname"));
        	sdm.setSaleorderdate(rs.getString("date"));
        	sdm.setAmount(rs.getInt("amount"));
        	sdm.setCompanyid(rs.getString("companyid"));
          	sdm.setCompanyname(rs.getString("companyname"));
          	sdm.setCompanyphone(rs.getString("companyphone"));
          	sdm.setCompanyemail(rs.getString("companyemail"));
          	sdm.setCompanyaddress(rs.getString("companyaddress"));
          	sdm.setPurchaseid(rs.getString("purchaseid"));
        	sdm.setPurchasedate(rs.getString("purchasedate"));
        	sdm.setSerial(rs.getString("serial"));
        	sdm.setBrandid(rs.getString("brandid"));
        	sdm.setTypeid(rs.getString("typeid"));
        	
        	sdm.setCurrentprice(rs.getInt("currentprice"));
        	sdm.setWarranty(rs.getString("warranty"));
        	sdm.setRemark(rs.getString("remark"));
        	//System.out.println(sdm.getBrandname());
        	
        	BrandModel bm = new BrandModel();
			BrandController bc = new BrandController();
			bm.setBrandid(sdm.getBrandid());
			sdm.setBrandname(bc.searchBrandname(bm));
			
			TypeModel tm = new TypeModel();
			TypeController tc = new TypeController();
			tm.setTypeid(sdm.getTypeid());
			sdm.setTypename(tc.searchTypeName(tm));
			
			
			StaffModel sm=new StaffModel();
			StaffController sc=new StaffController();
			sm.setStaffid(sdm.getStaffid());
			sdm.setStaffname(sc.searchStaffName(sm));
			

        }
		return sdm;
	}
	
	public boolean serialExist(SaleDetailModel sale) {
		boolean b = false;
		SaleDetailModel sdm=new SaleDetailModel();
		String sql = "SELECT\r\n"
				+ "    i.serial, \r\n"
				+ "    c.customerid, \r\n"
				+ "    c.customername,\r\n"
				+ "    c.phone AS `customerphone`, \r\n"
				+ "    c.email AS `customeremail`,\r\n"
				+ "    c.address AS `customeraddress`,\r\n"
				+ "    so.saleorderid,\r\n"
				+ "    so.staffid AS `salestaffid`,\r\n"
				+ "    so.type, \r\n"
				+ "    so.date, \r\n"
				+ "    so.amount,\r\n"
				+ "    i.itemid, \r\n"
				+ "    i.typeid, \r\n"
				+ "    i.brandid,\r\n"
				+ "    i.warranty, \r\n"
				+ "    i.currentprice, \r\n"
				+ "    i.remark,\r\n"
				+ "    i.itemname, \r\n"
				+ "    p.purchaseid, \r\n"
				+ "    p.companyid,\r\n"
				+ "    p.purchasedate, \r\n"
				+ "    com.companyname, \r\n"
				+ "    com.address AS `companyaddress`,\r\n"
				+ "    com.phone AS `companyphone`, \r\n"
				+ "    com.email AS `companyemail` \r\n"
				+ "FROM\r\n"
				+ "    saleandservice.item i\r\n"
				+ "LEFT OUTER JOIN \r\n"
				+ "    saleandservice.saleorderdetail sod ON sod.serial = i.serial \r\n"
				+ "LEFT OUTER JOIN\r\n"
				+ "    saleandservice.customer c ON c.customerid = sod.customerid \r\n"
				+ "LEFT OUTER JOIN \r\n"
				+ "    saleandservice.saleorder so ON so.saleorderid = sod.saleorderid \r\n"
				+ "LEFT OUTER JOIN \r\n"
				+ "    saleandservice.staff s ON s.staffid = so.staffid \r\n"
				+ "LEFT OUTER JOIN\r\n"
				+ "    saleandservice.purchasedetail pd ON i.itemid = pd.itemid \r\n"
				+ "LEFT OUTER JOIN \r\n"
				+ "    saleandservice.purchase p ON pd.purchaseid = p.purchaseid\r\n"
				+ "RIGHT OUTER JOIN \r\n"
				+ "    saleandservice.company com ON p.companyid = com.companyid\r\n"
				+ "WHERE\r\n"
				+ "    i.serial =?;\r\n"
				+ "";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, sale.getSerial());
	        ResultSet rs = ps.executeQuery();
	        System.out.println(rs.getRow());
	        while(rs.next()) {
	        	b = true;
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return b;
	}
	
	public SaleDetailModel verifyItemID(SaleDetailModel sale) throws SQLException{
		//List<SaleDetailModel> list = new ArrayList<SaleDetailModel>();
		SaleDetailModel sdm=new SaleDetailModel();
		String sql = "SELECT i.serial, c.customerid, c.customername, c.phone AS `customerphone`, c.email AS `customeremail`, c.address AS `customeraddress`, so.saleorderid,			 so.staffid AS `salestaffid`, so.type, so.date,so.amount, i.itemid, i.typeid, i.brandid, i.warranty, i.currentprice, i.remark, i.itemname,p.purchaseid, p.companyid,p.purchasedate, com.companyname, com.address AS `companyaddress`,com.phone AS `companyphone`, com.email AS `companyemail` FROM saleandservice.item i LEFT OUTER JOIN saleandservice.saleorderdetail sod ON sod.serial = i.serial LEFT OUTER JOIN saleandservice.customer c ON c.customerid = sod.customerid LEFT OUTER JOIN saleandservice.saleorder so ON so.saleorderid = sod.saleorderid LEFT OUTER JOIN saleandservice.staff s ON s.staffid = so.staffid LEFT OUTER JOIN saleandservice.purchasedetail pd ON i.itemid = pd.itemid LEFT OUTER JOIN saleandservice.purchase p ON pd.purchaseid = p.purchaseid RIGHT OUTER JOIN saleandservice.company com ON p.companyid = com.companyid WHERE i.itemid =?";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
		ps.setInt(1, sale.getItemid());
        ResultSet rs = ps.executeQuery();
        System.out.println(rs.getRow());
        while(rs.next()) {
        	//SaleDetailModel sdm = new SaleDetailModel();
        	sdm.setSaleorderid(rs.getString("saleorderid"));
        	sdm.setItemid(rs.getInt("itemid"));
        	sdm.setItemname(rs.getString("itemname"));
        	sdm.setCustomerid(rs.getString("customerid"));
        	sdm.setCustomername(rs.getString("customername"));
        	sdm.setCustomeraddress(rs.getString("customeraddress"));
        	sdm.setCustomerphone(rs.getString("customerphone"));
        	sdm.setCustomeremail(rs.getString("customeremail"));
        	sdm.setStaffid(rs.getString("salestaffid"));
        	//sdm.setStaffname(rs.getString("staffname"));
        	sdm.setSaleorderdate(rs.getString("date"));
        	sdm.setAmount(rs.getInt("amount"));
        	sdm.setCompanyid(rs.getString("companyid"));
          	sdm.setCompanyname(rs.getString("companyname"));
          	sdm.setCompanyphone(rs.getString("companyphone"));
          	sdm.setCompanyemail(rs.getString("companyemail"));
          	sdm.setCompanyaddress(rs.getString("companyaddress"));
          	sdm.setPurchaseid(rs.getString("purchaseid"));
        	sdm.setPurchasedate(rs.getString("purchasedate"));
        	sdm.setSerial(rs.getString("serial"));
        	sdm.setBrandid(rs.getString("brandid"));
        	sdm.setTypeid(rs.getString("typeid"));
        	
        	sdm.setCurrentprice(rs.getInt("currentprice"));
        	sdm.setWarranty(rs.getString("warranty"));
        	sdm.setRemark(rs.getString("remark"));
        	//System.out.println(sdm.getBrandname());
        	
        	BrandModel bm = new BrandModel();
			BrandController bc = new BrandController();
			bm.setBrandid(sdm.getBrandid());
			sdm.setBrandname(bc.searchBrandname(bm));
			
			TypeModel tm = new TypeModel();
			TypeController tc = new TypeController();
			tm.setTypeid(sdm.getTypeid());
			sdm.setTypename(tc.searchTypeName(tm));
			
			
			StaffModel sm=new StaffModel();
			StaffController sc=new StaffController();
			sm.setStaffid(sdm.getStaffid());
			sdm.setStaffname(sc.searchStaffName(sm));
			

        }
		return sdm;
	}
	
	public boolean idExist(SaleDetailModel sale) {
		boolean b = false;
		SaleDetailModel sdm=new SaleDetailModel();
		String sql = "SELECT\r\n"
				+ "    i.serial, \r\n"
				+ "    c.customerid, \r\n"
				+ "    c.customername,\r\n"
				+ "    c.phone AS `customerphone`, \r\n"
				+ "    c.email AS `customeremail`,\r\n"
				+ "    c.address AS `customeraddress`,\r\n"
				+ "    so.saleorderid,\r\n"
				+ "    so.staffid AS `salestaffid`,\r\n"
				+ "    so.type, \r\n"
				+ "    so.date, \r\n"
				+ "    so.amount,\r\n"
				+ "    i.itemid, \r\n"
				+ "    i.typeid, \r\n"
				+ "    i.brandid,\r\n"
				+ "    i.warranty, \r\n"
				+ "    i.currentprice, \r\n"
				+ "    i.remark,\r\n"
				+ "    i.itemname, \r\n"
				+ "    p.purchaseid, \r\n"
				+ "    p.companyid,\r\n"
				+ "    p.purchasedate, \r\n"
				+ "    com.companyname, \r\n"
				+ "    com.address AS `companyaddress`,\r\n"
				+ "    com.phone AS `companyphone`, \r\n"
				+ "    com.email AS `companyemail` \r\n"
				+ "FROM\r\n"
				+ "    saleandservice.item i\r\n"
				+ "LEFT OUTER JOIN \r\n"
				+ "    saleandservice.saleorderdetail sod ON sod.serial = i.serial \r\n"
				+ "LEFT OUTER JOIN\r\n"
				+ "    saleandservice.customer c ON c.customerid = sod.customerid \r\n"
				+ "LEFT OUTER JOIN \r\n"
				+ "    saleandservice.saleorder so ON so.saleorderid = sod.saleorderid \r\n"
				+ "LEFT OUTER JOIN \r\n"
				+ "    saleandservice.staff s ON s.staffid = so.staffid \r\n"
				+ "LEFT OUTER JOIN\r\n"
				+ "    saleandservice.purchasedetail pd ON i.itemid = pd.itemid \r\n"
				+ "LEFT OUTER JOIN \r\n"
				+ "    saleandservice.purchase p ON pd.purchaseid = p.purchaseid\r\n"
				+ "RIGHT OUTER JOIN \r\n"
				+ "    saleandservice.company com ON p.companyid = com.companyid\r\n"
				+ "WHERE\r\n"
				+ "    i.itemid =?;\r\n"
				+ "";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, sale.getItemid()+"");
	        ResultSet rs = ps.executeQuery();
	        System.out.println(rs.getRow());
	        while(rs.next()) {
	        	b = true;
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return b;
	}
	
   }    
//


	

