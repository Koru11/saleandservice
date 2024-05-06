package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import Connection.ClsDBConnection;
import Model.DeptModel;
public class DeptController {
	public static Connection con = null;
	static {
		ClsDBConnection cls = new ClsDBConnection();
		try {
			con = (Connection) cls.getConnection();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int insert(DeptModel dain) {
		int result =0;
		String sql = "insert into saleandservice.department (deptid,deptname) values(?,?)";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, dain.getDeptid());
			ps.setString(2, dain.getDeptname());
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Fail insert,Inter Error","Fail",JOptionPane.ERROR_MESSAGE);
		}
		return result;
	}
	public int update(DeptModel dain) {
		int result =0;
		String sql = "update saleandservice.department set deptname=? where deptid=?;";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, dain.getDeptname());
			ps.setString(2, dain.getDeptid());
			System.out.println(dain.getDeptname() + " " + dain.getDeptid());
			result = ps.executeUpdate();
			//System.out.println("===" + result);
		}catch(SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Fail update,Inter Error","Fail",JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}
	
	public int delete(DeptModel dain) {
		int result =0;
		String sql = "delete from saleandservice.department where deptid=?";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, dain.getDeptid());
			
			result =  ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Fail delete,Inter Error","Fail",JOptionPane.ERROR_MESSAGE);
		}
		return result;
	}
	
	public List<DeptModel> selectall()throws SQLException{
		List<DeptModel> list = new ArrayList<DeptModel>();
		String sql = "select * from saleandservice.department order by deptid desc";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			DeptModel bm = new DeptModel();
			bm.setDeptid(rs.getString("deptid"));
		    bm.setDeptname(rs.getString("deptname"));
		    list.add(bm);
		}
		return list;
	}
	
	public List<DeptModel> selectone(DeptModel dain) throws SQLException{
		List<DeptModel> list = new ArrayList<DeptModel>();
		String sql = "select * from saleandservice.department where deptname like ? order by deptid desc";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, dain.getDeptname()+"%");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			DeptModel bm = new DeptModel();
			bm.setDeptid(rs.getString("deptid"));
			bm.setDeptname(rs.getString("deptname"));
			list.add(bm);
		}
		return list;
	}
	
	public String searchDeptName(DeptModel dain) {
		String result=null;
		String sql = "select deptname from saleandservice.department where deptid=?";
		PreparedStatement ps ;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, dain.getDeptid());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getString("deptname");
			}else{
				System.out.println("This dept is not found");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	public String searchDeptID(DeptModel dain) {
		String result = null;
		String sql = "select deptid from saleandservice.department where deptname=?";
		try {
			PreparedStatement ps =(PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, dain.getDeptname());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getString("deptid") ;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	    return result;
	}
	
	public boolean isduplicate(DeptModel dain) throws SQLException{
		boolean duplicate = false;
		String sql = "select * from saleandservice.department where deptname=?";
		PreparedStatement ps =(PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, dain.getDeptname());
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			duplicate = true;
		}else {
			duplicate = false;
		}
		return duplicate;
	}

}
