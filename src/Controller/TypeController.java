package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import Connection.ClsDBConnection;
import Model.TypeModel;
public class TypeController {
	public static Connection con = null;
	static {
		ClsDBConnection cls = new ClsDBConnection();
		try {
			con = (Connection) cls.getConnection();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int insert(TypeModel dain) {
		int result =0;
		String sql = "insert into saleandservice.type (typeid,typename) values(?,?)";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, dain.getTypeid());
			ps.setString(2, dain.getTypename());
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Fail insert,Inter Error","Fail",JOptionPane.ERROR_MESSAGE);
		}
		return result;
	}
	public int update(TypeModel dain) {
		int result =0;
		String sql = "update saleandservice.type set typename=? where typeid=?;";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, dain.getTypename());
			ps.setString(2, dain.getTypeid());
			System.out.println(dain.getTypename() + " " + dain.getTypeid());
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
	
	public int delete(TypeModel dain) {
		int result =0;
		String sql = "delete from saleandservice.type where typeid=?";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, dain.getTypeid());
			
			result =  ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Fail delete,Inter Error","Fail",JOptionPane.ERROR_MESSAGE);
		}
		return result;
	}
	
	public List<TypeModel> selectall()throws SQLException{
		List<TypeModel> list = new ArrayList<TypeModel>();
		String sql = "select * from saleandservice.type order by typeid desc";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			TypeModel bm = new TypeModel();
			bm.setTypeid(rs.getString("typeid"));
		    bm.setTypename(rs.getString("typename"));
		    list.add(bm);
		}
		return list;
	}
	
	public List<TypeModel> selectone(TypeModel dain) throws SQLException{
		List<TypeModel> list = new ArrayList<TypeModel>();
		String sql = "select * from saleandservice.type where typename like ? order by typeid desc";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, "%"+dain.getTypename()+"%");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			TypeModel bm = new TypeModel();
			bm.setTypeid(rs.getString("typeid"));
			bm.setTypename(rs.getString("typename"));
			list.add(bm);
		}
		return list;
	}
	
	
	public String searchTypeName(TypeModel dain) throws SQLException {
		String result=null;
		String sql="select typename from saleandservice.type where typeid=?";
		PreparedStatement ps = null;
		try {
			ps=(PreparedStatement) con.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ps.setString(1, dain.getTypeid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rs.next()) {
			result=rs.getString("typename");
			
		}else {
			System.out.println("This type cant found");
		}
		
		return result;
	}
	public String searchTypeID(TypeModel dain) {
		String result = null;
		String sql = "select typeid from saleandservice.type where typename=?";
		try {
			PreparedStatement ps =(PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, dain.getTypename());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getString("typeid") ;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	    return result;
	}
	
	public boolean isduplicate(TypeModel dain) throws SQLException{
		boolean duplicate = false;
		String sql = "select * from saleandservice.type where typename=?";
		PreparedStatement ps =(PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, dain.getTypename());
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			duplicate = true;
		}else {
			duplicate = false;
		}
		return duplicate;
	}

}
