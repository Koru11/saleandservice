package Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;

import Model.DeptModel;
import Model.ItemModel;

public class SQLQueries {
	private static Connection con = null;

	static {
		ClsDBConnection dbcon = new ClsDBConnection();

		try {
			con = (Connection) dbcon.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void addComboBox(String tableName, String columnName, JComboBox<String> comboBox) {
		String sql = "select " + columnName + " FROM " + tableName;

//		System.out.println(tableName);
//		System.out.println(columnName);

		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			comboBox.removeAllItems();
			comboBox.addItem("-Select-");
			while (rs.next()) {
				String value = rs.getString(columnName);
				comboBox.addItem(value);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void addStaffComboBox(String tableName, String columnName, JComboBox<String> comboBox,String data) {
		String sql = "select " + columnName + " FROM " + tableName+" WHERE deptid = ?";

		System.out.println(tableName);
		System.out.println(columnName);

		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, data);
			ResultSet rs = ps.executeQuery();
			comboBox.removeAllItems();
			comboBox.addItem("-Select-");
			while (rs.next()) {
				String value = rs.getString(columnName);
				comboBox.addItem(value);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public static String[] getItemData(ItemModel dain)throws SQLException{
		String str[] = new String[7];
		String sql = "select * from saleandservice.item where itemname = ?";
		PreparedStatement ps = (PreparedStatement)con.prepareStatement(sql);
        ps.setString(1, dain.getItemname());
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
        	str[0] = rs.getString(1); //id
        	str[1] = rs.getString(4);  //name
        	str[2] = rs.getString(5); //price
        	str[3] = rs.getString(2);//brand_id
        	str[4] = rs.getString(3);//typeid
        	str[5] = rs.getString(6);//qty
        	str[6] = rs.getString(7);//remark
        }
        
		return str;
	}
	
	
	
	public static String getBrandName(String brandid)throws SQLException {
		String brandname = null;
		String sql = "select * from saleandservice.brand where brandid = ?";
		PreparedStatement ps = (PreparedStatement)con.prepareStatement(sql);
        ps.setString(1, brandid);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
        	brandname = rs.getString(2);
        }
		return brandname;
	}
	public static String getTypeName(String typeid)throws SQLException{
		String typename = null;
		String sql = "select * from saleandservice.type where typeid=?";
		PreparedStatement ps = (PreparedStatement)con.prepareStatement(sql);
		ps.setString(1,typeid);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			typename = rs.getString(2);
		}
		return typename;
				
	}
	
	public static String[] getItemData1(ItemModel dain)throws SQLException{
		String str[] = new String[7];
		String sql = "select * from saleandservice.item where itemid = ?";
		PreparedStatement ps = (PreparedStatement)con.prepareStatement(sql);
        ps.setString(1, dain.getItemid());
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
        	str[0] = rs.getString(1); //id
        	str[1] = rs.getString(4);  //name
        	str[2] = rs.getString(5); //price
        	str[3] = rs.getString(2);//brand_id
        	str[4] = rs.getString(3);//typeid
        	str[5] = rs.getString(6);//qty
        	str[6] = rs.getString(7);//remark
        }
		return str;
	}
	
}
