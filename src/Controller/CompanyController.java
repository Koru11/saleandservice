package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Connection.ClsDBConnection;
import Model.CompanyModel;


public class CompanyController {
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

	public int insert(CompanyModel cm) {
		int result = 0;

		String query = "INSERT INTO saleandservice.company(companyid,companyname,address,phone,email) VALUES(?,?,?,?,?)";

		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1, cm.getCompanyid());
			ps.setString(2, cm.getCompanyname());
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

	public int update(CompanyModel cm) {
		int result = 0;

		String query = "UPDATE saleandservice.company SET companyname=?,address=?,phone=?,email=? WHERE companyid=?";

		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(5, cm.getCompanyid());
			ps.setString(1, cm.getCompanyname());
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
	
	public int delete(CompanyModel cm) {
		int result = 0;

		String query = "DELETE FROM saleandservice.company WHERE companyid=?";
		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1, cm.getCompanyid());


			result = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Delete Fail,Inter error","Fail", JOptionPane.ERROR_MESSAGE);

		}

		return result;

	}
	
	public ArrayList<CompanyModel> selectAll() {
		ArrayList<CompanyModel> list = new ArrayList<>();
		
		String query = "SELECT * FROM saleandservice.company order by companyid desc";
		
		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				CompanyModel cm = new CompanyModel();
				cm.setCompanyid(rs.getString("companyid"));
				cm.setCompanyname(rs.getString("companyname"));
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

	public ArrayList<CompanyModel> selectOne(CompanyModel cus) {
		ArrayList<CompanyModel> list = new ArrayList<>();
		
		String query = "SELECT * FROM saleandservice.company WHERE companyname LIKE ?";
		
		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			
			ps.setString(1, "%"+cus.getCompanyname()+"%");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				CompanyModel cm = new CompanyModel();
				cm.setCompanyid(rs.getString("companyid"));
				cm.setCompanyname(rs.getString("companyname"));
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
	
	
	public ArrayList<CompanyModel> searchcompanyDetail(CompanyModel cus){
		ArrayList<CompanyModel> smList = new ArrayList<>();
		String query = "SELECT * FROM saleandservice.company WHERE companyname=?";

		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1,cus.getCompanyname());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CompanyModel cm = new CompanyModel();
				cm.setCompanyid(rs.getString("companyid"));
				cm.setCompanyname(rs.getString("companyname"));
				cm.setAddress(rs.getString("address"));
				cm.setPhone(rs.getString("phone"));

				cm.setEmail(rs.getString("email"));
				
				smList.add(cm);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return smList;
	}
	
	public ArrayList<CompanyModel> searchcompanyDetail1(CompanyModel cus){
		ArrayList<CompanyModel> smList = new ArrayList<>();
		String query = "SELECT * FROM saleandservice.company WHERE companyid=?";

		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1,cus.getCompanyname());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CompanyModel cm = new CompanyModel();
				cm.setCompanyid(rs.getString("companyid"));
				cm.setCompanyname(rs.getString("companyname"));
				cm.setAddress(rs.getString("address"));
				cm.setPhone(rs.getString("phone"));
				cm.setEmail(rs.getString("email"));
				

				smList.add(cm);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return smList;
	}
}
