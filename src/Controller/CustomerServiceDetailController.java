package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Connection.ClsDBConnection;
import Model.CustomerModel;
import Model.CustomerServiceDetailModel;

public class CustomerServiceDetailController {
	PreparedStatement ps;

	private static Connection con;

	static {
		ClsDBConnection dbcon = new ClsDBConnection();
		try {
			con = (Connection) dbcon.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int insert(CustomerServiceDetailModel cm) {
		int result = 0;

		String query = "INSERT INTO saleandservice.customerservicedetail(customerid,serviceid,date,status) VALUES(?,?,?,?)";

		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1, cm.getCustomerid());
			ps.setString(2, cm.getServiceid());
			ps.setString(3, cm.getDate());
			ps.setString(4, cm.getStatus());

			result = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public int update(CustomerServiceDetailModel cm) {
		int result = 0;

		String query = "UPDATE saleandservice.customerservicedetail SET customerid=?,date=?,status=?,solution=? WHERE serviceid=?";

		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1, cm.getCustomerid());
			ps.setString(2, cm.getDate());
			ps.setString(3, cm.getStatus());
			ps.setString(4, cm.getSolution());
			ps.setString(5, cm.getServiceid());

			result = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

	public int delete(CustomerServiceDetailModel cm) {
		int result = 0;

		String query = "DELETE FROM saleandservice.customerservicedetail WHERE customerid=? AND serviceid=?";
		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1, cm.getCustomerid());
			ps.setString(2, cm.getServiceid());

			result = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Delete Fail,Inter error","Fail", JOptionPane.ERROR_MESSAGE);

		}

		return result;

	}

	// wrong code
	public ArrayList<CustomerServiceDetailModel> selectAll() {
		ArrayList<CustomerServiceDetailModel> list = new ArrayList<>();

		String query = "SELECT * FROM saleandservice.customerservicedetail order by customerid desc";

		try {
			ps = (PreparedStatement) con.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				CustomerServiceDetailModel cm = new CustomerServiceDetailModel();
				ps.setString(1, cm.getCustomerid());
				ps.setString(2, cm.getServiceid());
				ps.setString(3, cm.getDate());
				ps.setString(4, cm.getStatus());

				list.add(cm);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
	// wrong code

	public String SearchCustomer(String serviceid) {
		String name = "";
		String query = "SELECT customerid FROM saleandservice.customerservicedetail WHERE serviceid=?";
		try {

			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1, serviceid);
			ResultSet rs;
			rs = ps.executeQuery();
			while (rs.next()) {
				name = rs.getString("customerid");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return name;

	}

	public ArrayList<CustomerServiceDetailModel> selectbycustomerid(String customerid) {
		ArrayList<CustomerServiceDetailModel> list = new ArrayList<>();
		String query = "SELECT * FROM saleandservice.customerservicedetail WHERE customerid LIKE ?";
		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1, "%" + customerid + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CustomerServiceDetailModel csdm = new CustomerServiceDetailModel();
				csdm.setServiceid(rs.getString("serviceid"));
				csdm.setCustomerid(rs.getString("customerid"));
				csdm.setDate(rs.getString("date"));
				csdm.setStatus(rs.getString("status"));
				csdm.setSolution(rs.getString("solution"));
				list.add(csdm);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	

	public ArrayList<CustomerServiceDetailModel> searchbyserviceid(String serviceid) {
		ArrayList<CustomerServiceDetailModel> list = new ArrayList<>();
		String query = "SELECT * FROM saleandservice.customerservicedetail WHERE serviceid = ?";
		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1, serviceid );
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CustomerServiceDetailModel csdm = new CustomerServiceDetailModel();
				csdm.setServiceid(rs.getString("serviceid"));
				csdm.setCustomerid(rs.getString("customerid"));
				csdm.setDate(rs.getString("date"));
				csdm.setStatus(rs.getString("status"));
				csdm.setSolution(rs.getString("solution"));
				list.add(csdm);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public String searchStatus(String serviceid,String customerid) {
		String returnString = "";
		String query = "SELECT * FROM saleandservice.customerservicedetail WHERE serviceid = ? AND customerid=?";
		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1, serviceid );
			ps.setString(2, customerid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CustomerServiceDetailModel csdm = new CustomerServiceDetailModel();

				returnString = rs.getString(("status"));
						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnString;
	}

//	public ArrayList<CustomerServiceDetailModel> selectOne(CustomerServiceDetailModel cus) {
//		ArrayList<CustomerServiceDetailModel> list = new ArrayList<>();
//		
//		String query = "SELECT * FROM saleandservice.customerservicedetail WHERE customername LIKE ?";
//		
//		try {
//			ps = (PreparedStatement) con.prepareStatement(query);
//			
//			ps.setString(1, "%"+cus.getCustomername()+"%");
//			
//			ResultSet rs = ps.executeQuery();
//			
//			while(rs.next()) {
//				CustomerServiceDetailModel cm = new CustomerServiceDetailModel();
//				ps.setString(1, cm.getCustomerid());
//				ps.setString(2, cm.getServiceid());
//				ps.setString(3, cm.getDate());
//				ps.setString(4, cm.getStatus());
//				
//				list.add(cm);
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return list;
//	}

//	public ArrayList<CustomerModel> searchCustomerDetail(CustomerServiceDetailModel cus){
//		ArrayList<CustomerModel> smList = new ArrayList<>();
//		String query = "SELECT * FROM saleandservice.customerservicedetail WHERE customername=?";
//
//		try {
//			ps = (PreparedStatement) con.prepareStatement(query);
//			ps.setString(1,cus.getCustomername());
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//				CustomerModel cm = new CustomerModel();
//				cm.setCustomerid(rs.getString("customerid"));
//				cm.setCustomername(rs.getString("customername"));
//				cm.setAddress(rs.getString("address"));
//				cm.setPhone(rs.getString("phone"));
//
//				cm.setEmail(rs.getString("email"));
//				
//				smList.add(cm);
//			}
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return smList;
//	}
//	
//	public ArrayList<CustomerModel> searchCustomerDetail1(CustomerServiceDetailModel cus){
//		ArrayList<CustomerModel> smList = new ArrayList<>();
//		String query = "SELECT * FROM saleandservice.customerservicedetail WHERE customerid=?";
//
//		try {
//			ps = (PreparedStatement) con.prepareStatement(query);
//			ps.setString(1,cus.getCustomername());
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//				CustomerModel cm = new CustomerModel();
//				cm.setCustomerid(rs.getString("customerid"));
//				cm.setCustomername(rs.getString("customername"));
//				cm.setAddress(rs.getString("address"));
//				cm.setPhone(rs.getString("phone"));
//				cm.setEmail(rs.getString("email"));
//				
//
//				smList.add(cm);
//			}
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return smList;
//	}
}
