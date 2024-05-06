package Controller;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import Connection.ClsDBConnection;
import Model.CompanyModel;
import Model.LoginModel;

public class LoginController {
	public static Connection con = null;
	static {
		ClsDBConnection cls = new ClsDBConnection();
		try {
			con = (Connection) cls.getConnection();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public int insert(String username, String password) {
		int result = 0;

		String query = "INSERT INTO saleandservice.admin(username,password) VALUES(?,?)";

		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);

			result = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	 public boolean login(String username, String password) throws SQLException {
	        //boolean status = false;
	    
	           String sql="SELECT * FROM saleandservice.admin WHERE username=? AND password=?"; 
	           PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
	           ps.setString(1, username);
	           ps.setString(2,password);
	           ResultSet resultSet = ps.executeQuery();
	           return resultSet.next();
	    }

	 private void printSQLException(SQLException ex) {
	        for (Throwable e: ex) {
	            if (e instanceof SQLException) {
	                e.printStackTrace(System.err);
	                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
	                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
	                System.err.println("Message: " + e.getMessage());
	                Throwable t = ex.getCause();
	                while (t != null) {
	                    System.out.println("Cause: " + t);
	                    t = t.getCause();
	                }
	            }
	        }
	    }

}
