package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import Connection.ClsDBConnection;
import Model.PurchaseModel;
import Model.SaleModel;
import Model.SaleOrderModel;

public class SaleOrderController {
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
	
	public int insert(SaleOrderModel dain) {
		int result = 0;
		String sql = "insert into saleandservice.saleorder(saleorderid,staffid,type,date,amount) values(?,?,?,?,?)";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, dain.getSaleorderid());
			ps.setString(2, dain.getStaffid());
			ps.setString(3, dain.getPaymenttype());
			ps.setString(4, dain.getDate());
			ps.setInt(5, dain.getAmount());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Insert Fail,Inter error","Fail", JOptionPane.ERROR_MESSAGE);
		}
		return result;
	}
	
	public ArrayList<SaleModel> showAllSale() throws SQLException{
		ArrayList<SaleModel> list = new ArrayList<SaleModel>();
		String sql = "SELECT DISTINCT so.saleorderid, so.staffid, so.type, so.date, so.amount, c.customerid, c.customername, c.phone, c.address, c.email FROM saleandservice.saleorder so INNER JOIN saleandservice.saleorderdetail sod ON so.saleorderid = sod.saleorderid LEFT OUTER JOIN saleandservice.customer c ON c.customerid = sod.customerid order by so.saleorderid desc;";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
        	SaleModel sm = new SaleModel();
        	sm.setSaleorderid(rs.getString("saleorderid"));
        	sm.setStaffid(rs.getString("staffid"));
        	sm.setPaymenttype(rs.getString("type"));
        	sm.setDate(rs.getString("date"));
        	sm.setAmount(rs.getInt("amount"));
        	
        	sm.setCustomerid(rs.getString("customerid"));
        	sm.setCustomername(rs.getString("customername"));
        	sm.setPhone(rs.getString("phone"));
        	sm.setAddress(rs.getString("address"));
        	sm.setEmail(rs.getString("email"));



        	list.add(sm);
        }
		return list;
	}
	
	public ArrayList<SaleModel> showAllSaleOne(String name) throws SQLException{
		ArrayList<SaleModel> list = new ArrayList<SaleModel>();
		String sql = "SELECT DISTINCT so.saleorderid, so.staffid, so.type, so.date, so.amount, c.customerid, c.customername, c.phone, c.address, c.email FROM saleandservice.saleorder so INNER JOIN saleandservice.saleorderdetail sod ON so.saleorderid = sod.saleorderid LEFT OUTER JOIN saleandservice.customer c ON c.customerid = sod.customerid WHERE so.saleorderid=?;";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
        	SaleModel sm = new SaleModel();
        	sm.setSaleorderid(rs.getString("saleorderid"));
        	sm.setStaffid(rs.getString("staffid"));
        	sm.setPaymenttype(rs.getString("type"));
        	sm.setDate(rs.getString("date"));
        	sm.setAmount(rs.getInt("amount"));
        	
        	sm.setCustomerid(rs.getString("customerid"));
        	sm.setCustomername(rs.getString("customername"));
        	sm.setPhone(rs.getString("phone"));
        	sm.setAddress(rs.getString("address"));
        	sm.setEmail(rs.getString("email"));



        	list.add(sm);
        }
		return list;
	}
	
	public ArrayList<SaleModel> searchbyid(String id) throws SQLException{
		ArrayList<SaleModel> list = new ArrayList<SaleModel>();
		String sql = "SELECT DISTINCT so.saleorderid, so.staffid, so.type, so.date, so.amount, c.customerid, c.customername, c.phone, c.address, c.email FROM saleandservice.saleorder so INNER JOIN saleandservice.saleorderdetail sod ON so.saleorderid = sod.saleorderid LEFT OUTER JOIN saleandservice.customer c ON c.customerid = sod.customerid WHERE so.saleorderid LIKE ?";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, "%"+id+"%");
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
        	SaleModel sm = new SaleModel();
        	sm.setSaleorderid(rs.getString("saleorderid"));
        	sm.setStaffid(rs.getString("staffid"));
        	sm.setPaymenttype(rs.getString("type"));
        	sm.setDate(rs.getString("date"));
        	sm.setAmount(rs.getInt("amount"));
        	
        	sm.setCustomerid(rs.getString("customerid"));
        	sm.setCustomername(rs.getString("customername"));
        	sm.setPhone(rs.getString("phone"));
        	sm.setAddress(rs.getString("address"));
        	sm.setEmail(rs.getString("email"));



        	list.add(sm);
        }
		return list;
	}

	
	public int deletesale(String id) throws SQLException {
		int result = 0;
		String query = "DELETE FROM saleandservice.saleorder WHERE saleorderid=?";
		String query2 = "DELETE FROM saleandservice.saleorderdetail WHERE saleorderid=?";
		String query3 = "DELETE FROM saleandservice.payment WHERE saleorderid=?";

		PreparedStatement ps ;

		try {
			ps = (PreparedStatement) con.prepareStatement(query3);
			ps.setString(1, id);
			result = ps.executeUpdate();
			
			ps = (PreparedStatement) con.prepareStatement(query2);
			ps.setString(1, id);
			result = ps.executeUpdate();
			
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1, id);
			result = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Delete Fail,Inter error","Fail", JOptionPane.ERROR_MESSAGE);
		}

		return result;
	}
	
}
