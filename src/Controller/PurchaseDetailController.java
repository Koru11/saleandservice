package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import Connection.ClsDBConnection;
import Model.ItemModel;
import Model.PurchaseDetailModel;
import Model.PurchaseModel;


public class PurchaseDetailController {
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
	
	public int insert(PurchaseDetailModel dain) {
		int result =0;
		String sql = "insert into saleandservice.purchasedetail (purchaseid,itemname,purchaseprice,purchaseqty,itemid) values(?,?,?,?,?)";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, dain.getPurchaseid());
//			System.out.println(dain.getPurchaseid());
			ps.setString(2, dain.getItemName());
			ps.setInt(3, dain.getPurchaseprice());
			ps.setInt(4, dain.getPurchaseqty());
			ps.setInt(5, dain.getItemid());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	

	
	public List<PurchaseDetailModel> showAll() throws SQLException{
		List<PurchaseDetailModel> list = new ArrayList<PurchaseDetailModel>();
		String sql = "select * from saleandservice.purchasedetail GROUP BY purchaseid, itemname order by purchaseid desc";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
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
	
	public List<PurchaseDetailModel> showOne(PurchaseDetailModel dain) throws SQLException{
		List<PurchaseDetailModel> list = new ArrayList<PurchaseDetailModel>();
		String sql = "select * from saleandservice.purchasedetail where itemname LIKE ? GROUP BY purchaseid, itemname order by purchaseid desc";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, "%"+dain.getItemName()+"%");
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
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
