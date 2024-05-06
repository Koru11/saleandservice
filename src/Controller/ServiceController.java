package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Connection.ClsDBConnection;
import Model.PostModel;
import Model.ServiceModel;
import Model.StaffModel;


public class ServiceController {
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

	public int insert(ServiceModel sm) {
		int result = 0;

		String query = "INSERT INTO saleandservice.service(serviceid,item,staffid,price,bag,fault,charger,processor,ram,harddisk,serial,date,brand,warranty) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1, sm.getServiceid());
			ps.setString(2, sm.getItem());
			ps.setString(3, sm.getStaffid());
			ps.setInt(4, sm.getPrice());
			ps.setString(5, sm.getBag());
			ps.setString(6, sm.getFault());
			ps.setString(7, sm.getCharger());
			ps.setString(8, sm.getProcessor());
			ps.setString(9, sm.getRam());
			ps.setString(10, sm.getHarddisk());
			ps.setString(11, sm.getSerial());
			ps.setString(12, sm.getDate());
			ps.setString(13, sm.getBrand());
			ps.setString(14, sm.getWarranty());
				
			result = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public int update(ServiceModel sm) {
		int result = 0;

		String query = "UPDATE saleandservice.service SET item=?,staffid=?,price=?,bag=?,fault=?,charger=?,processor=?,ram=?,harddisk=?,serial=?,date=?,brand=?,warranty=? WHERE serviceid=?";

		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1, sm.getItem());
			ps.setString(2, sm.getStaffid());
			ps.setInt(3, sm.getPrice());
			ps.setString(4, sm.getBag());
			ps.setString(5, sm.getFault());
			ps.setString(6, sm.getCharger());
			ps.setString(7, sm.getProcessor());
			ps.setString(8, sm.getRam());
			ps.setString(9, sm.getHarddisk());
			ps.setString(10, sm.getSerial());
			ps.setString(11, sm.getDate());
			ps.setString(12, sm.getBrand());
			ps.setString(13, sm.getWarranty());
			ps.setString(14, sm.getServiceid());

		
			result = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}
	
	public int delete(ServiceModel sm) {
		int result = 0;

		String query = "DELETE FROM saleandservice.service WHERE serviceid=?";
		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1, sm.getServiceid());


			result = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Delete Fail,Inter error","Fail", JOptionPane.ERROR_MESSAGE);
		}

		return result;

	}
	
	public ArrayList<ServiceModel> selectAll() {
		ArrayList<ServiceModel> list = new ArrayList<>();
		
		String query = "SELECT * FROM saleandservice.service order by serviceid desc";
		
		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				ServiceModel sm = new ServiceModel();
				sm.setServiceid(rs.getString("serviceid"));
				sm.setStaffid(rs.getString("staffid"));
				sm.setItem(rs.getString("item"));
				sm.setBrand(rs.getString("brand"));
				sm.setBag(rs.getString("bag"));
				sm.setFault(rs.getString("fault"));
				sm.setCharger(rs.getString("charger"));
				sm.setProcessor(rs.getString("processor"));
				sm.setRam(rs.getString("ram"));
				sm.setHarddisk(rs.getString("harddisk"));
				sm.setPrice(rs.getInt("price"));
				sm.setSerial(rs.getString("serial"));
				sm.setWarranty(rs.getString("warranty"));
				sm.setDate(rs.getString("date"));
				StaffModel pm = new StaffModel();
				StaffController pc = new StaffController();
				pm.setStaffid(sm.getStaffid());
				sm.setStaffname(pc.searchStaffName(pm));
			

				list.add(sm);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	public ServiceModel selectSerial(String name) {
		ServiceModel sm = new ServiceModel();
		
		String query = "SELECT * FROM saleandservice.service WHERE serviceid = ?";
		
		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				sm.setServiceid(rs.getString("serviceid"));
				sm.setStaffid(rs.getString("staffid"));
				sm.setItem(rs.getString("item"));
				sm.setBrand(rs.getString("brand"));
				sm.setBag(rs.getString("bag"));
				sm.setFault(rs.getString("fault"));
				sm.setCharger(rs.getString("charger"));
				sm.setProcessor(rs.getString("processor"));
				sm.setRam(rs.getString("ram"));
				sm.setHarddisk(rs.getString("harddisk"));
				sm.setPrice(rs.getInt("price"));
				sm.setSerial(rs.getString("serial"));
				sm.setWarranty(rs.getString("warranty"));
				sm.setDate(rs.getString("date"));
				StaffModel pm = new StaffModel();
				StaffController pc = new StaffController();
				pm.setStaffid(sm.getStaffid());
				sm.setStaffname(pc.searchStaffName(pm));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sm;
	}

	public ArrayList<ServiceModel> selectbyitem(ServiceModel ser) {
		ArrayList<ServiceModel> list = new ArrayList<>();
		
		String query = "SELECT * FROM saleandservice.service WHERE item LIKE ?";
		
		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			
			ps.setString(1, "%"+ser.getItem()+"%");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				ServiceModel sm = new ServiceModel();
				sm.setServiceid(rs.getString("serviceid"));
				sm.setStaffid(rs.getString("staffid"));
				sm.setItem(rs.getString("item"));
				sm.setBrand(rs.getString("brand"));
				sm.setBag(rs.getString("bag"));
				sm.setFault(rs.getString("fault"));
				sm.setCharger(rs.getString("charger"));
				sm.setProcessor(rs.getString("processor"));
				sm.setRam(rs.getString("ram"));
				sm.setHarddisk(rs.getString("harddisk"));
				sm.setPrice(rs.getInt("price"));
				sm.setSerial(rs.getString("serial"));
				sm.setWarranty(rs.getString("warranty"));
				sm.setDate(rs.getString("date"));
				StaffModel pm = new StaffModel();
				StaffController pc = new StaffController();
				pm.setStaffid(sm.getStaffid());
				sm.setStaffname(pc.searchStaffName(pm));
			

				list.add(sm);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public ArrayList<ServiceModel> selectbyserviceid(ServiceModel ser) {
		ArrayList<ServiceModel> list = new ArrayList<>();
		
		String query = "SELECT * FROM saleandservice.service WHERE serviceid LIKE ?";
		
		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			
			ps.setString(1, "%"+ser.getServiceid()+"%");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				ServiceModel sm = new ServiceModel();
				sm.setServiceid(rs.getString("serviceid"));
				sm.setStaffid(rs.getString("staffid"));
				sm.setItem(rs.getString("item"));
				sm.setBrand(rs.getString("brand"));
				sm.setBag(rs.getString("bag"));
				sm.setFault(rs.getString("fault"));
				sm.setCharger(rs.getString("charger"));
				sm.setProcessor(rs.getString("processor"));
				sm.setRam(rs.getString("ram"));
				sm.setHarddisk(rs.getString("harddisk"));
				sm.setPrice(rs.getInt("price"));
				sm.setSerial(rs.getString("serial"));
				sm.setWarranty(rs.getString("warranty"));
				sm.setDate(rs.getString("date"));
				StaffModel pm = new StaffModel();
				StaffController pc = new StaffController();
				pm.setStaffid(sm.getStaffid());
				sm.setStaffname(pc.searchStaffName(pm));
			

				list.add(sm);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public ArrayList<ServiceModel> selectbyserial(ServiceModel ser) {
		ArrayList<ServiceModel> list = new ArrayList<>();
		
		String query = "SELECT * FROM saleandservice.service WHERE serial LIKE ?";
		
		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			
			ps.setString(1, "%"+ser.getSerial()+"%");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				ServiceModel sm = new ServiceModel();
				sm.setServiceid(rs.getString("serviceid"));
				sm.setStaffid(rs.getString("staffid"));
				sm.setItem(rs.getString("item"));
				sm.setBrand(rs.getString("brand"));
				sm.setBag(rs.getString("bag"));
				sm.setFault(rs.getString("fault"));
				sm.setCharger(rs.getString("charger"));
				sm.setProcessor(rs.getString("processor"));
				sm.setRam(rs.getString("ram"));
				sm.setHarddisk(rs.getString("harddisk"));
				sm.setPrice(rs.getInt("price"));
				sm.setSerial(rs.getString("serial"));
				sm.setWarranty(rs.getString("warranty"));
				sm.setDate(rs.getString("date"));
				StaffModel pm = new StaffModel();
				StaffController pc = new StaffController();
				pm.setStaffid(sm.getStaffid());
				sm.setStaffname(pc.searchStaffName(pm));
			

				list.add(sm);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	public ArrayList<ServiceModel> searchserviceDetail(ServiceModel ser){
		ArrayList<ServiceModel> smList = new ArrayList<>();
		String query = "SELECT * FROM saleandservice.service WHERE fault=?";

		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1,ser.getFault());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ServiceModel sm = new ServiceModel();
				sm.setServiceid(rs.getString("serviceid"));
				sm.setStaffid(rs.getString("staffid"));

				sm.setPrice(rs.getInt("price"));
				sm.setBag(rs.getString("bag"));
				sm.setFault(rs.getString("fault"));
				
				sm.setCharger(rs.getString("charger"));
				sm.setProcessor(rs.getString("processor"));
				sm.setRam(rs.getString("ram"));
				sm.setHarddisk(rs.getString("harddisk"));
				
			

				smList.add(sm);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return smList;
	}
	
	public ArrayList<ServiceModel> searchserviceDetail1(ServiceModel ser){
		ArrayList<ServiceModel> smList = new ArrayList<>();
		String query = "SELECT * FROM saleandservice.service WHERE serviceid=?";

		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1,ser.getServiceid());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ServiceModel sm = new ServiceModel();
				sm.setServiceid(rs.getString("serviceid"));
				sm.setStaffid(rs.getString("staffid"));
				sm.setItem(rs.getString("item"));
				sm.setBrand(rs.getString("brand"));
				sm.setBag(rs.getString("bag"));
				sm.setFault(rs.getString("fault"));
				sm.setCharger(rs.getString("charger"));
				sm.setProcessor(rs.getString("processor"));
				sm.setRam(rs.getString("ram"));
				sm.setHarddisk(rs.getString("harddisk"));
				sm.setPrice(rs.getInt("price"));
				sm.setSerial(rs.getString("serial"));
				sm.setWarranty(rs.getString("warranty"));
				sm.setDate(rs.getString("date"));
				StaffModel pm = new StaffModel();
				StaffController pc = new StaffController();
				pm.setStaffid(sm.getStaffid());
				sm.setStaffname(pc.searchStaffName(pm));
				
			

				smList.add(sm);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return smList;
	}
}
