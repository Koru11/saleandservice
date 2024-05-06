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
public class BrandController {
	public static Connection con = null;
	static {
		ClsDBConnection cls = new ClsDBConnection();
		try {
			con = (Connection) cls.getConnection();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int insert(BrandModel dain) {
		int result =0;
		String sql = "insert into saleandservice.brand (brandid,brandname) values(?,?)";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, dain.getBrandid());
			ps.setString(2, dain.getBrandname());
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Fail insert,Inter Error","Fail",JOptionPane.ERROR_MESSAGE);
		}
		return result;
	}
	public int update(BrandModel dain) {
		int result =0;
		String sql = "update saleandservice.brand set brandname=? where brandid=?;";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, dain.getBrandname());
			ps.setString(2, dain.getBrandid());
			System.out.println(dain.getBrandname() + " " + dain.getBrandid());
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
	
	public int delete(BrandModel dain) {
		int result =0;
		String sql = "delete from saleandservice.brand where brandid=?";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, dain.getBrandid());
			
			result =  ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Fail delete,Inter Error","Fail",JOptionPane.ERROR_MESSAGE);
		}
		return result;
	}
	
	public List<BrandModel> selectall()throws SQLException{
		List<BrandModel> list = new ArrayList<BrandModel>();
		String sql = "select * from saleandservice.brand order by brandid desc";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			BrandModel bm = new BrandModel();
			bm.setBrandid(rs.getString("brandid"));
		    bm.setBrandname(rs.getString("brandname"));
		    list.add(bm);
		}
		return list;
	}
	
	public List<BrandModel> selectone(BrandModel dain) throws SQLException{
		List<BrandModel> list = new ArrayList<BrandModel>();
		String sql = "select * from saleandservice.brand where brandname like ? order by brandid desc";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, "%"+dain.getBrandname()+"%");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			BrandModel bm = new BrandModel();
			bm.setBrandid(rs.getString("brandid"));
			bm.setBrandname(rs.getString("brandname"));
			list.add(bm);
		}
		return list;
	}
	
	public String searchBrandname(BrandModel dain) {
		String result=null;
		String sql = "select brandname from saleandservice.brand where brandid=?";
		PreparedStatement ps ;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, dain.getBrandid());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getString("brandname");
			}else{
				System.out.println("This brand is not found");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	public String searchBrandId(BrandModel dain) {
		String result = null;
		String sql = "select brandid from saleandservice.brand where brandname=?";
		try {
			PreparedStatement ps =(PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, dain.getBrandname());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getString("brandid") ;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	    return result;
	}
	
	public boolean isduplicate(BrandModel dain) throws SQLException{
		boolean duplicate = false;
		String sql = "select * from saleandservice.brand where brandname=?";
		PreparedStatement ps =(PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, dain.getBrandname());
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			duplicate = true;
		}else {
			duplicate = false;
		}
		return duplicate;
	}

}
