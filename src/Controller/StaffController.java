package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Connection.ClsDBConnection;
import Model.BrandModel;
import Model.DeptModel;
import Model.PostModel;
import Model.StaffModel;
import Model.TypeModel;


public class StaffController {
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

	public int insert(StaffModel sm) {
		int result = 0;

		String query = "INSERT INTO saleandservice.staff(staffid,staffname,postid,deptid,address,phone,email) VALUES(?,?,?,?,?,?,?)";

		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1, sm.getStaffid());
			ps.setString(2, sm.getStaffname());
			ps.setString(3, sm.getPostid());
			ps.setString(4, sm.getDepid());
			ps.setString(5, sm.getAddress());
			ps.setString(6, sm.getPhone());
			ps.setString(7, sm.getEmail());

			result = ps.executeUpdate();

		} catch(SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Fail delete,Inter Error","Fail",JOptionPane.ERROR_MESSAGE);
		}

		return result;
	}

	public int update(StaffModel sm) {
		int result = 0;

		String query = "UPDATE saleandservice.staff SET staffname=?,postid=?,deptid=?,address=?,phone=?,email=? WHERE staffid=?";

		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(7, sm.getStaffid());
			ps.setString(1, sm.getStaffname());
			ps.setString(2, sm.getPostid());
			ps.setString(3, sm.getDepid());
			ps.setString(4, sm.getAddress());
			ps.setString(5, sm.getPhone());
			ps.setString(6, sm.getEmail());

			result = ps.executeUpdate();

		} catch(SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Fail delete,Inter Error","Fail",JOptionPane.ERROR_MESSAGE);
		}

		return result;

	}
	
	public int delete(StaffModel sm) {
		int result = 0;

		String query = "DELETE FROM saleandservice.staff WHERE staffid=?";
		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1, sm.getStaffid());


			result = ps.executeUpdate();

		} catch(SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Fail delete,Inter Error","Fail",JOptionPane.ERROR_MESSAGE);
		}
		return result;

	}
	
	public boolean dupeStaffName(String name) {
		boolean b = false;
		String query = "SELECT * FROM saleandservice.staff WHERE staffname=?";
		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1, name);


			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				b = true;
			}

		} catch(SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Fail delete,Inter Error","Fail",JOptionPane.ERROR_MESSAGE);
		}
		return b;
	}
	
	public ArrayList<StaffModel> selectAll() {
		ArrayList<StaffModel> list = new ArrayList<>();
		
		String query = "SELECT * FROM saleandservice.staff order by staffid desc";
		
		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				StaffModel sm = new StaffModel();
				sm.setStaffid(rs.getString("staffid"));
				sm.setStaffname(rs.getString("staffname"));
				sm.setPostid(rs.getString("postid"));
				sm.setDepid(rs.getString("deptid"));
				sm.setAddress(rs.getString("address"));
				sm.setPhone(rs.getString("phone"));

				sm.setEmail(rs.getString("email"));
				PostModel pm = new PostModel();
				PostController pc = new PostController();
				pm.setPostid(sm.getPostid());
				sm.setPostname(pc.searchPostName(pm));
				
				DeptModel dm = new DeptModel();
				DeptController dc = new DeptController();
				dm.setDeptid(sm.getDepid());
				sm.setDeptname(dc.searchDeptName(dm));
				
				list.add(sm);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	public ArrayList<StaffModel> selectOne(StaffModel cus) {
		ArrayList<StaffModel> list = new ArrayList<>();
		
		String query = "SELECT * FROM saleandservice.staff WHERE staffname LIKE ?  order by staffid desc";
		
		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			
			ps.setString(1, "%"+cus.getStaffname()+"%");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				StaffModel sm = new StaffModel();
				sm.setStaffid(rs.getString("staffid"));
				sm.setStaffname(rs.getString("staffname"));
				sm.setPostid(rs.getString("postid"));
				sm.setDepid(rs.getString("deptid"));
				sm.setAddress(rs.getString("address"));
				sm.setPhone(rs.getString("phone"));

				sm.setEmail(rs.getString("email"));
				PostModel pm = new PostModel();
				PostController pc = new PostController();
				pm.setPostid(sm.getPostid());
				sm.setPostname(pc.searchPostName(pm));
				
				DeptModel dm = new DeptModel();
				DeptController dc = new DeptController();
				dm.setDeptid(sm.getDepid());
				sm.setDeptname(dc.searchDeptName(dm));
				
				list.add(sm);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	public ArrayList<StaffModel> searchstaffDetail(StaffModel cus){
		ArrayList<StaffModel> smList = new ArrayList<>();
		String query = "SELECT * FROM saleandservice.staff WHERE staffname=?";

		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1,cus.getStaffname());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				StaffModel sm = new StaffModel();
				sm.setStaffid(rs.getString("staffid"));
				sm.setStaffname(rs.getString("staffname"));
				sm.setPostid(rs.getString("postid"));
				sm.setDepid(rs.getString("deptid"));
				sm.setAddress(rs.getString("address"));
				sm.setPhone(rs.getString("phone"));

				sm.setEmail(rs.getString("email"));
				
				smList.add(sm);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return smList;
	}
	
	public ArrayList<StaffModel> searchstaffDetail1(StaffModel cus){
		ArrayList<StaffModel> smList = new ArrayList<>();
		String query = "SELECT * FROM saleandservice.staff WHERE staffid=?";

		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1,cus.getStaffname());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				StaffModel sm = new StaffModel();
				sm.setStaffid(rs.getString("staffid"));
				sm.setStaffname(rs.getString("staffname"));
				sm.setPostid(rs.getString("postid"));
				sm.setDepid(rs.getString("deptid"));
				sm.setAddress(rs.getString("address"));
				sm.setPhone(rs.getString("phone"));
				sm.setEmail(rs.getString("email"));
				

				smList.add(sm);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return smList;
	}
	public String searchStaffName(StaffModel dain) {
		String result=null;
		String sql = "select staffname from saleandservice.staff where staffid=?";
		PreparedStatement ps ;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, dain.getStaffid());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getString("staffname");
			}else{
				System.out.println("This staff is not found");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	public String searchStaffId(StaffModel dain) {
		String result = null;
		String sql = "select staffid from saleandservice.staff where staffname=?";
		try {
			PreparedStatement ps =(PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, dain.getStaffname());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getString("staffid") ;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	    return result;
	}

}
