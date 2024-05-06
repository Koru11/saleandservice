package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Connection.ClsDBConnection;

import Model.PostModel;

public class PostController {
	public static Connection con = null;

	static {
		ClsDBConnection db = new ClsDBConnection();

		try {
			con = (Connection) db.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	PreparedStatement ps;// prepared statement declared here reuse everywhere
	ResultSet rs;

	public int insert(PostModel dm) {
		int result = 0;
		String query = "INSERT INTO saleandservice.post(postid,postname) VALUES(?,?)";
		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1, dm.getPostid());
			ps.setString(2, dm.getPost());
			
			result = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public int update(PostModel dm) {
		int result = 0;
		String query = "UPDATE saleandservice.post SET postname=? WHERE postid=?;";
		
		try {
			ps = (PreparedStatement)con.prepareStatement(query);
			ps.setString(1, dm.getPost());
			ps.setString(2, dm.getPostid());
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public int delete(PostModel dm) {
		int result = 0;
		String query = "DELETE FROM saleandservice.post WHERE postid=?";
		
		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1, dm.getPostid());
			result = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Delete Fail,Inter error","Fail", JOptionPane.ERROR_MESSAGE);

		}
		return result;
	}
	
	public ArrayList<PostModel> selectAll(){
		ArrayList<PostModel> dmList = new ArrayList<>();
		String query = "SELECT * FROM saleandservice.post  order by postid desc";
		
		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PostModel dm = new PostModel();
				dm.setPostid(rs.getString("postid"));
				dm.setPost(rs.getString("postname"));
				
				dmList.add(dm);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dmList;
	}
	
	public ArrayList<PostModel> selectOne(PostModel pm) throws SQLException{
		ArrayList<PostModel> dmList = new ArrayList<PostModel>();
		String query = "SELECT * FROM saleandservice.post WHERE postname LIKE ?  order by postid desc";
		
		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1, "%"+pm.getPost()+"%");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PostModel dm = new PostModel();
				dm.setPostid(rs.getString("postid"));
				dm.setPost(rs.getString("postname"));
				
				dmList.add(dm);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dmList;
	}
	
	public boolean hasDuplicate(PostModel dm) {
		boolean dupe = false;
		
		String query = "SELECT * FROM saleandservice.post WHERE postname=?";
		
		try {
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1, dm.getPost());
			
			rs = ps.executeQuery();
			if(rs.next()) {
				dupe = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return dupe;
	}
	
	

	public String searchPostName(PostModel dain) {
		String result=null;
		String sql = "select postname from saleandservice.post where postid=?";
		PreparedStatement ps ;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, dain.getPostid());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getString("postname");
			}else{
				System.out.println("This post is not found");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	public String searchPostID(PostModel dain) {
		String result = null;
		String sql = "select postid from saleandservice.post where postname=?";
		try {
			PreparedStatement ps =(PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, dain.getPost());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getString("postid") ;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	    return result;
	}

}
