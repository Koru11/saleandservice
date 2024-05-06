package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import Connection.ClsDBConnection;
import Model.BrandModel;
import Model.ItemModel;
import Model.TypeModel;

public class ItemController {
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

	public int insert(ItemModel dain) {
		int result = 0;
		String sql = "insert into saleandservice.item (brandid,typeid,itemname,currentprice,warranty,remark,serial) values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
//			ps.setString(1, dain.getItemid());
			ps.setString(1, dain.getBrandid());
			ps.setString(2, dain.getTypeid());
			ps.setString(3, dain.getItemname());
			ps.setInt(4, dain.getCurrentprice());
			ps.setString(5, dain.getWarranty());
			ps.setString(6, dain.getRemark());
			ps.setString(7, dain.getSerial());

			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Insert Fail,Inter error", "Fail", JOptionPane.ERROR_MESSAGE);
		}
		return result;

	}

	public int update(ItemModel dain) {
		int result = 0;
		String sql = "update saleandservice.item set brandid=?,typeid=?,itemname=?,currentprice=?,warranty=?,remark=?,serial=? where itemid=?";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);

			ps.setString(1, dain.getBrandid());
			ps.setString(2, dain.getTypeid());
			ps.setString(3, dain.getItemname());
			ps.setInt(4, dain.getCurrentprice());
			ps.setString(5, dain.getWarranty());
			ps.setString(6, dain.getRemark());
			ps.setString(7, dain.getSerial());
			// ps.setInt(8, dain.getTotalqty());
			ps.setString(8, dain.getItemid());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Update Fail,Inter error", "Fail", JOptionPane.ERROR_MESSAGE);
		}
		return result;

	}

	public int delete(ItemModel dain) {
		int result = 0;
		String sql = "delete from saleandservice.item where itemid=?";
		String sql2 = "delete from saleandservice.purchasedetail where itemid=?";
		try {
			PreparedStatement ps2 = (PreparedStatement) con.prepareStatement(sql2);
			ps2.setString(1, dain.getItemid());
			result = ps2.executeUpdate();

			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, dain.getItemid());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Delete Fail,Inter error", "Fail", JOptionPane.ERROR_MESSAGE);
		}
		return result;

	}

	public ArrayList<ItemModel> selectall() throws SQLException {
		ArrayList<ItemModel> list = new ArrayList<ItemModel>();
		String sql = "select * from saleandservice.item order by itemid desc";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ItemModel im = new ItemModel();

			im.setItemid(rs.getString("itemid"));
			im.setBrandid(rs.getString("brandid"));
			im.setTypeid(rs.getString("typeid"));
			im.setItemname(rs.getString("itemname"));
			im.setCurrentprice(rs.getInt("currentprice"));
//			im.setTotalqty(rs.getInt("totalqty"));
			im.setRemark(rs.getString("remark"));
			im.setWarranty(rs.getString("warranty"));
			im.setSerial(rs.getString("serial"));

			BrandModel bm = new BrandModel();
			BrandController bc = new BrandController();
			bm.setBrandid(im.getBrandid());
			im.setBrandname(bc.searchBrandname(bm));

			TypeModel tm = new TypeModel();
			TypeController tc = new TypeController();
			tm.setTypeid(im.getTypeid());
			im.setTypename(tc.searchTypeName(tm));
			list.add(im);
		}

		return list;
	}

	public int selectLast() throws SQLException {
		int id = 0;
		String sql = "SELECT MAX(itemid) FROM saleandservice.item;";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {

			id = (rs.getInt("MAX(itemid)"));
		}
		return id;
	}

	public List<ItemModel> selectone(ItemModel dain) {
		List<ItemModel> list = new ArrayList<ItemModel>();
		String sql = "select * from saleandservice.item where itemname like ? order by itemid desc";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, "%" + dain.getItemname() + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ItemModel im = new ItemModel();
				im.setItemid(rs.getString("itemid"));
				im.setBrandid(rs.getString("brandid"));
				im.setTypeid(rs.getString("typeid"));
				im.setItemname(rs.getString("itemname"));
				im.setCurrentprice(rs.getInt("currentprice"));
//				im.setTotalqty(rs.getInt("totalqty"));
				im.setRemark(rs.getString("remark"));
				im.setWarranty(rs.getString("warranty"));
				im.setSerial(rs.getString("serial"));

				BrandModel bm = new BrandModel();
				BrandController bc = new BrandController();
				bm.setBrandid(im.getBrandid());
				im.setBrandname(bc.searchBrandname(bm));

				TypeModel tm = new TypeModel();
				TypeController tc = new TypeController();
				tm.setTypeid(im.getTypeid());
				im.setTypename(tc.searchTypeName(tm));
				list.add(im);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<ItemModel> selectbySerial(ItemModel dain) {
		List<ItemModel> list = new ArrayList<ItemModel>();
		String sql = "select * from saleandservice.item where serial like ? order by itemid desc";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, "%" + dain.getSerial() + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ItemModel im = new ItemModel();
				im.setItemid(rs.getString("itemid"));
				im.setBrandid(rs.getString("brandid"));
				im.setTypeid(rs.getString("typeid"));
				im.setItemname(rs.getString("itemname"));
				im.setCurrentprice(rs.getInt("currentprice"));
//				im.setTotalqty(rs.getInt("totalqty"));
				im.setRemark(rs.getString("remark"));
				im.setWarranty(rs.getString("warranty"));
				im.setSerial(rs.getString("serial"));

				BrandModel bm = new BrandModel();
				BrandController bc = new BrandController();
				bm.setBrandid(im.getBrandid());
				im.setBrandname(bc.searchBrandname(bm));

				TypeModel tm = new TypeModel();
				TypeController tc = new TypeController();
				tm.setTypeid(im.getTypeid());
				im.setTypename(tc.searchTypeName(tm));
				list.add(im);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<ItemModel> searchItemDetail(ItemModel dain) {
		List<ItemModel> list = new ArrayList<ItemModel>();
		String sql = "select * from saleandservice.item where itemname=? order by itemid desc";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, dain.getItemname());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ItemModel im = new ItemModel();
				im.setItemid(rs.getString("itemid"));
				im.setBrandid(rs.getString("brandid"));
				im.setTypeid(rs.getString("typeid"));
				im.setItemname(rs.getString("itemname"));
				im.setCurrentprice(rs.getInt("currentprice"));
//				im.setTotalqty(rs.getInt("totalqty"));
				im.setRemark(rs.getString("remark"));
				im.setWarranty(rs.getString("warranty"));
				im.setSerial(rs.getString("serial"));

				BrandModel bm = new BrandModel();
				BrandController bc = new BrandController();
				bm.setBrandid(im.getBrandid());
				im.setBrandname(bc.searchBrandname(bm));

				TypeModel tm = new TypeModel();
				TypeController tc = new TypeController();
				tm.setTypeid(im.getTypeid());
				im.setTypename(tc.searchTypeName(tm));
				list.add(im);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public String searchItemName(ItemModel dain) {
		String result = null;
		String sql = "select itemname from saleandservice.item where itemid=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, dain.getItemid());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getString("itemname");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

	public int searchItemID(ItemModel dain) {
		int result = 0;
		String sql = "select itemid from saleandservice.item where itemname=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, dain.getItemname());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getInt("itemname");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

	public boolean serialExist(String serial) {
		boolean result = false;
		String sql = "select * from saleandservice.item where serial=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, serial);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public boolean itemidExist(String serial) {
		boolean result = false;
		String sql = "select * from saleandservice.item where itemid=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, serial);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public ItemModel searchbySerial(String serial) {
		ItemModel im = new ItemModel();
		boolean result = false;
		String sql = "select * from saleandservice.item where serial=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, serial);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				im.setItemid(rs.getString("itemid"));
				im.setBrandid(rs.getString("brandid"));
				im.setTypeid(rs.getString("typeid"));
				im.setItemname(rs.getString("itemname"));
				im.setCurrentprice(rs.getInt("currentprice"));
				im.setWarranty(rs.getString("warranty"));
				im.setRemark(rs.getString("remark"));
				im.setSerial(rs.getString("serial"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return im;
	}

	public ItemModel searchbyItemid(String itemid) {
		ItemModel im = new ItemModel();
		boolean result = false;
		String sql = "select * from saleandservice.item where itemid=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, itemid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				im.setItemid(rs.getString("itemid"));
				im.setBrandid(rs.getString("brandid"));
				im.setTypeid(rs.getString("typeid"));
				im.setItemname(rs.getString("itemname"));
				im.setCurrentprice(rs.getInt("currentprice"));
				im.setWarranty(rs.getString("warranty"));
				im.setRemark(rs.getString("remark"));
				im.setSerial(rs.getString("serial"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return im;
	}

	public ArrayList<ItemModel> selectStock() throws SQLException {
		ArrayList<ItemModel> list = new ArrayList<ItemModel>();
		String sql = "SELECT s.saleorderid,i.itemid,i.brandid,i.typeid,i.itemname,i.currentprice,i.warranty,i.remark,i.serial FROM saleandservice.saleorderdetail s RIGHT OUTER JOIN saleandservice.item i ON i.itemid = s.itemid WHERE saleorderid IS NULL;";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ItemModel im = new ItemModel();

			im.setItemid(rs.getString("itemid"));
			im.setBrandid(rs.getString("brandid"));
			im.setTypeid(rs.getString("typeid"));
			im.setItemname(rs.getString("itemname"));
			im.setCurrentprice(rs.getInt("currentprice"));
//			im.setTotalqty(rs.getInt("totalqty"));
			im.setRemark(rs.getString("remark"));
			im.setWarranty(rs.getString("warranty"));
			im.setSerial(rs.getString("serial"));

			BrandModel bm = new BrandModel();
			BrandController bc = new BrandController();
			bm.setBrandid(im.getBrandid());
			im.setBrandname(bc.searchBrandname(bm));

			TypeModel tm = new TypeModel();
			TypeController tc = new TypeController();
			tm.setTypeid(im.getTypeid());
			im.setTypename(tc.searchTypeName(tm));
			list.add(im);
		}

		return list;
	}

	public ArrayList<ItemModel> selectStockbyid(String name) throws SQLException {
		ArrayList<ItemModel> list = new ArrayList<ItemModel>();
		String sql = "SELECT s.saleorderid,i.itemid,i.brandid,i.typeid,i.itemname,i.currentprice,i.warranty,i.remark,i.serial FROM saleandservice.saleorderdetail s RIGHT OUTER JOIN saleandservice.item i ON i.itemid = s.itemid WHERE saleorderid IS NULL AND i.itemid LIKE ?;";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, "%" + name + "%");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ItemModel im = new ItemModel();

			im.setItemid(rs.getString("itemid"));
			im.setBrandid(rs.getString("brandid"));
			im.setTypeid(rs.getString("typeid"));
			im.setItemname(rs.getString("itemname"));
			im.setCurrentprice(rs.getInt("currentprice"));
//			im.setTotalqty(rs.getInt("totalqty"));
			im.setRemark(rs.getString("remark"));
			im.setWarranty(rs.getString("warranty"));
			im.setSerial(rs.getString("serial"));

			BrandModel bm = new BrandModel();
			BrandController bc = new BrandController();
			bm.setBrandid(im.getBrandid());
			im.setBrandname(bc.searchBrandname(bm));

			TypeModel tm = new TypeModel();
			TypeController tc = new TypeController();
			tm.setTypeid(im.getTypeid());
			im.setTypename(tc.searchTypeName(tm));
			list.add(im);
		}

		return list;
	}

	public ArrayList<ItemModel> selectStockbyserial(String serial) throws SQLException {
		ArrayList<ItemModel> list = new ArrayList<ItemModel>();
		String sql = "SELECT s.saleorderid,i.itemid,i.brandid,i.typeid,i.itemname,i.currentprice,i.warranty,i.remark,i.serial FROM saleandservice.saleorderdetail s RIGHT OUTER JOIN saleandservice.item i ON i.itemid = s.itemid WHERE saleorderid IS NULL AND i.serial LIKE ?;";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, "%" + serial + "%");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ItemModel im = new ItemModel();

			im.setItemid(rs.getString("itemid"));
			im.setBrandid(rs.getString("brandid"));
			im.setTypeid(rs.getString("typeid"));
			im.setItemname(rs.getString("itemname"));
			im.setCurrentprice(rs.getInt("currentprice"));
//			im.setTotalqty(rs.getInt("totalqty"));
			im.setRemark(rs.getString("remark"));
			im.setWarranty(rs.getString("warranty"));
			im.setSerial(rs.getString("serial"));

			BrandModel bm = new BrandModel();
			BrandController bc = new BrandController();
			bm.setBrandid(im.getBrandid());
			im.setBrandname(bc.searchBrandname(bm));

			TypeModel tm = new TypeModel();
			TypeController tc = new TypeController();
			tm.setTypeid(im.getTypeid());
			im.setTypename(tc.searchTypeName(tm));
			list.add(im);
		}

		return list;
	}

	public ArrayList<ItemModel> selectStockbyname(String name) throws SQLException {
		ArrayList<ItemModel> list = new ArrayList<ItemModel>();
		String sql = "SELECT s.saleorderid,i.itemid,i.brandid,i.typeid,i.itemname,i.currentprice,i.warranty,i.remark,i.serial FROM saleandservice.saleorderdetail s RIGHT OUTER JOIN saleandservice.item i ON i.itemid = s.itemid WHERE saleorderid IS NULL AND i.itemname LIKE ?;";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, "%" + name + "%");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ItemModel im = new ItemModel();

			im.setItemid(rs.getString("itemid"));
			im.setBrandid(rs.getString("brandid"));
			im.setTypeid(rs.getString("typeid"));
			im.setItemname(rs.getString("itemname"));
			im.setCurrentprice(rs.getInt("currentprice"));
//			im.setTotalqty(rs.getInt("totalqty"));
			im.setRemark(rs.getString("remark"));
			im.setWarranty(rs.getString("warranty"));
			im.setSerial(rs.getString("serial"));

			BrandModel bm = new BrandModel();
			BrandController bc = new BrandController();
			bm.setBrandid(im.getBrandid());
			im.setBrandname(bc.searchBrandname(bm));

			TypeModel tm = new TypeModel();
			TypeController tc = new TypeController();
			tm.setTypeid(im.getTypeid());
			im.setTypename(tc.searchTypeName(tm));
			list.add(im);
		}

		return list;
	}

	public int update1(ItemModel dain) {
		int result = 0;
		String sql = "update saleandservice.item set currentprice=?,totalqty=? where itemid=?";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, dain.getCurrentprice());
			ps.setInt(2, dain.getTotalqty());
			ps.setString(3, dain.getItemid());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public int update2(ItemModel dain) {
		int result = 0;
		String sql = "update saleandservice.item set totalqty=? where itemid=?";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, dain.getTotalqty());
			ps.setString(2, dain.getItemid());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public int namecount(String name) {
		int i = 0;
		String sql = "SELECT COUNT(*) FROM saleandservice.saleorderdetail s RIGHT OUTER JOIN saleandservice.item i ON i.itemid = s.itemid WHERE saleorderid IS NULL AND i.itemname LIKE ?;";
		try {

			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, "%" + name + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i = rs.getInt("COUNT(*)");
				// TODO Auto-generated catch block

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
}
