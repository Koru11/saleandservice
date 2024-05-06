package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Connection.ClsDBConnection;
import Model.CustomerModel;


public class CustomerController {
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

	public int insert(CustomerModel cm) {
		int result = 0;

		String query = "INSERT INTO saleandservice.customer(customerid,customername,address,phone,email) VALUES(?,?,?,?,?)";

		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1, cm.getCustomerid());
			ps.setString(2, cm.getCustomername());
			ps.setString(3, cm.getAddress());
			ps.setString(4, cm.getPhone());
			ps.setString(5, cm.getEmail());

			result = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public int update(CustomerModel cm) {
		int result = 0;

		String query = "UPDATE saleandservice.customer SET customername=?,address=?,phone=?,email=? WHERE customerid=?";

		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(5, cm.getCustomerid());
			ps.setString(1, cm.getCustomername());
			ps.setString(2, cm.getAddress());
			ps.setString(3, cm.getPhone());
			ps.setString(4, cm.getEmail());

			result = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}
	
	public int delete(CustomerModel cm) {
		int result = 0;

		String query = "DELETE FROM saleandservice.customer WHERE customerid=?";
		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1, cm.getCustomerid());


			result = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Delete Fail,Inter error","Fail", JOptionPane.ERROR_MESSAGE);

		}

		return result;

	}
	
	public ArrayList<CustomerModel> selectAll() {
		ArrayList<CustomerModel> list = new ArrayList<>();
		
		String query = "SELECT * FROM saleandservice.customer order by customerid desc";
		
		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				CustomerModel cm = new CustomerModel();
				cm.setCustomerid(rs.getString("customerid"));
				cm.setCustomername(rs.getString("customername"));
				cm.setAddress(rs.getString("address"));
				cm.setPhone(rs.getString("phone"));

				cm.setEmail(rs.getString("email"));
				
				list.add(cm);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	public ArrayList<CustomerModel> selectOne(CustomerModel cus) {
		ArrayList<CustomerModel> list = new ArrayList<>();
		
		String query = "SELECT * FROM saleandservice.customer WHERE customername LIKE ?";
		
		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			
			ps.setString(1, "%"+cus.getCustomername()+"%");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				CustomerModel cm = new CustomerModel();
				cm.setCustomerid(rs.getString("customerid"));
				cm.setCustomername(rs.getString("customername"));
				cm.setAddress(rs.getString("address"));
				cm.setPhone(rs.getString("phone"));

				cm.setEmail(rs.getString("email"));
				
				list.add(cm);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	public CustomerModel searchCustomerDetail(CustomerModel cus){
		CustomerModel cm = new CustomerModel();
		String query = "SELECT * FROM saleandservice.customer WHERE customername=?";

		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1,cus.getCustomername());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				cm.setCustomerid(rs.getString("customerid"));
				cm.setCustomername(rs.getString("customername"));
				cm.setAddress(rs.getString("address"));
				cm.setPhone(rs.getString("phone"));
				cm.setEmail(rs.getString("email"));
				
				return cm;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cm;
	}
	
	public CustomerModel searchCustomerDetail1(CustomerModel cus){
		CustomerModel cm = new CustomerModel();
		String query = "SELECT * FROM saleandservice.customer WHERE customerid=?";

		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1,cus.getCustomerid());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				cm.setCustomerid(rs.getString("customerid"));
				cm.setCustomername(rs.getString("customername"));
				cm.setAddress(rs.getString("address"));
				cm.setPhone(rs.getString("phone"));
				cm.setEmail(rs.getString("email"));
				
				return cm;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cm;
	}
	
	public CustomerModel searchbyPhone(CustomerModel cus){
		CustomerModel cm = new CustomerModel();
		String query = "SELECT * FROM saleandservice.customer WHERE phone=?";

		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1,cus.getPhone());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				cm.setCustomerid(rs.getString("customerid"));
				cm.setCustomername(rs.getString("customername"));
				cm.setAddress(rs.getString("address"));
				cm.setPhone(rs.getString("phone"));
				cm.setEmail(rs.getString("email"));
				
				return cm;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cm;
	}
	
	public boolean CustomerExist(CustomerModel cus){
		
		String query = "SELECT * FROM saleandservice.customer WHERE customerid=?";

		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1,cus.getCustomerid());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean phoneExist(CustomerModel cus){
		
		String query = "SELECT * FROM saleandservice.customer WHERE phone=?";

		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1,cus.getPhone());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
