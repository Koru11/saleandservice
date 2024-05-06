package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import Connection.ClsDBConnection;
import Model.PurchaseDetailModel;
import Model.SaleModel;
import Model.SaleOrderDetailModel;
import Model.SaleOrderModel;

public class SaleOrderDetailController {
	public static Connection con = null;
	static {
		ClsDBConnection cls = new ClsDBConnection();
		try {
			con = cls.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Insert Fail,Inter error", "Fail", JOptionPane.ERROR_MESSAGE);
		}

	}

	public int insert(SaleOrderDetailModel dain) {
		int result = 0;
		String sql = "insert into saleandservice.saleorderdetail(saleorderid,itemid,customerid,serial,price) values(?,?,?,?,?)";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, dain.getSaleorderid());
			ps.setString(2, dain.getItemid());
			ps.setString(3, dain.getCustomerid());
			ps.setString(4, dain.getSerial());
			ps.setInt(5, dain.getPrice());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

	public boolean itemBought(String name) {
		boolean b = false;
		String query = "SELECT * FROM saleandservice.saleorderdetail WHERE itemid=?";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1, name);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				b = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Fail delete,Inter Error", "Fail", JOptionPane.ERROR_MESSAGE);
		}
		return b;
	}

	public List<SaleModel> showAll() throws SQLException {
		List<SaleModel> list = new ArrayList<SaleModel>();
		String sql = "SELECT * FROM saleandservice.saleorderdetail sod INNER JOIN saleandservice.customer c ON c.customerid = sod.customerid INNER JOIN saleandservice.item i ON i.itemid = sod.itemid";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			SaleModel sm = new SaleModel();
			sm.setSaleorderid(rs.getString("saleorderid"));
			sm.setItemid(rs.getString("itemid"));
			sm.setItemname(rs.getString("itemname"));
			sm.setCustomerid(rs.getString("customerid"));
			sm.setSerial(rs.getString("serial"));
			sm.setCustomername(rs.getString("customername"));
			sm.setAddress(rs.getString("address"));
			sm.setPhone(rs.getString("phone"));
			sm.setEmail(rs.getString("email"));
			sm.setBrandid(rs.getString("brandid"));
			sm.setTypeid(rs.getString("typeid"));
			sm.setCurrentprice(rs.getInt("currentprice"));
			sm.setWarranty(rs.getString("warranty"));
			sm.setRemark(rs.getString("remark"));

			list.add(sm);
		}
		return list;
	}

	public List<SaleModel> showAllbyname(String name) throws SQLException {
		List<SaleModel> list = new ArrayList<SaleModel>();
		String sql = "SELECT * FROM saleandservice.saleorderdetail sod INNER JOIN saleandservice.customer c ON c.customerid = sod.customerid INNER JOIN saleandservice.item i ON i.itemid = sod.itemid WHERE i.itemname LIKE ?";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, "%" + name + "%");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			SaleModel sm = new SaleModel();
			sm.setSaleorderid(rs.getString("saleorderid"));
			sm.setItemid(rs.getString("itemid"));
			sm.setItemname(rs.getString("itemname"));
			sm.setCustomerid(rs.getString("customerid"));
			sm.setSerial(rs.getString("serial"));
			sm.setCustomername(rs.getString("customername"));
			sm.setAddress(rs.getString("address"));
			sm.setPhone(rs.getString("phone"));
			sm.setEmail(rs.getString("email"));
			sm.setBrandid(rs.getString("brandid"));
			sm.setTypeid(rs.getString("typeid"));
			sm.setCurrentprice(rs.getInt("currentprice"));
			sm.setWarranty(rs.getString("warranty"));
			sm.setRemark(rs.getString("remark"));

			list.add(sm);
		}
		return list;
	}

	public int showCount(String name) throws SQLException {
		int i =0;
		String sql = "	SELECT COUNT(*) FROM saleandservice.saleorderdetail sod INNER JOIN saleandservice.customer c ON c.customerid = sod.customerid INNER JOIN saleandservice.item i ON i.itemid = sod.itemid WHERE i.itemname LIKE ?";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, "%" + name + "%");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			SaleModel sm = new SaleModel();
			i = rs.getInt("COUNT(*)");
		}
		return i;
	}

	public List<PurchaseDetailModel> showOne(PurchaseDetailModel dain) throws SQLException {
		List<PurchaseDetailModel> list = new ArrayList<PurchaseDetailModel>();
		String sql = "select * from saleandservice.purchasedetail where itemname LIKE ? order by purchaseid desc";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, "%" + dain.getItemName() + "%");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			PurchaseDetailController pdc = new PurchaseDetailController();
			PurchaseDetailModel pdm = new PurchaseDetailModel();
			pdm.setPurchaseid(rs.getString("purchaseid"));
			pdm.setItemName(rs.getString("itemname"));
			pdm.setPurchaseprice(rs.getInt("purchaseprice"));
			pdm.setPurchaseqty(rs.getInt("purchaseqty"));

//        	ItemModel im = new ItemModel();
//        	ItemController ic = new ItemController();
//        	im.setItemname(pdm.getItemName());
//        	pdm.setItemid(ic.searchItemID(im));
			list.add(pdm);
		}
		return list;
	}

}
