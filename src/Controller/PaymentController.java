package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import Connection.ClsDBConnection;
import Model.BrandModel;
import Model.ItemModel;
import Model.PaymentModel;
import Model.TypeModel;

public class PaymentController {
	public static Connection con = null;
	static {
		ClsDBConnection cls = new ClsDBConnection();
		try {
			con = cls.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Insert Fail,Inter error", "Fail", JOptionPane.ERROR_MESSAGE);
		}

	}

	public int insert(PaymentModel dain) {
		int result = 0;
		String sql = "insert into saleandservice.payment (paymentid,saleorderid,staffid,amount,paymenttype,remark,date) values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);

			ps.setString(1, dain.getPaymentid());
			ps.setString(2, dain.getSaleorderid());
			ps.setString(3, dain.getStaffid());
			ps.setInt(4, dain.getAmount());
			ps.setString(5, dain.getPaymenttype());
			ps.setString(6, dain.getRemark());
			ps.setString(7, dain.getDate());

			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Insert Fail,Inter error", "Fail", JOptionPane.ERROR_MESSAGE);
		}
		return result;

	}

	public int update(PaymentModel dain) {
		int result = 0;
		String sql = "update saleandservice.payment set saleorderid=?,staffid=?,amount=?,paymenttype=?,remark=? where paymentid=?";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);

			ps.setString(6, dain.getPaymentid());
			ps.setString(1, dain.getSaleorderid());
			ps.setString(2, dain.getStaffid());
			ps.setInt(3, dain.getAmount());
			ps.setString(4, dain.getPaymenttype());
			ps.setString(5, dain.getRemark());

			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Update Fail,Inter error", "Fail", JOptionPane.ERROR_MESSAGE);
		}
		return result;

	}

	public int delete(PaymentModel dain) {
		int result = 0;
		String sql = "delete from saleandservice.payment where paymentid=?";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, dain.getPaymentid());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Delete Fail,Inter error", "Fail", JOptionPane.ERROR_MESSAGE);
		}
		return result;

	}

	public ArrayList<PaymentModel> selectall() throws SQLException {
		ArrayList<PaymentModel> list = new ArrayList<PaymentModel>();
		String sql = "select * from saleandservice.payment order by paymentid desc";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			PaymentModel im = new PaymentModel();

			im.setPaymentid(rs.getString("paymentid"));
			im.setSaleorderid(rs.getString("saleorderid"));
			im.setStaffid(rs.getString("staffid"));
			im.setAmount(rs.getInt("amount"));
			im.setPaymenttype(rs.getString("paymenttype"));
			im.setRemark(rs.getString("remark"));
			im.setDate(rs.getString("date"));

			list.add(im);
		}

		return list;
	}

	public ArrayList<PaymentModel> selectbyid(String id) throws SQLException {
		ArrayList<PaymentModel> list = new ArrayList<PaymentModel>();
		String sql = "select * from saleandservice.payment WHERE paymentid LIKE ? order by paymentid desc";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, "%" + id + "%");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			PaymentModel im = new PaymentModel();

			im.setPaymentid(rs.getString("paymentid"));
			im.setSaleorderid(rs.getString("saleorderid"));
			im.setStaffid(rs.getString("staffid"));
			im.setAmount(rs.getInt("amount"));
			im.setPaymenttype(rs.getString("paymenttype"));
			im.setRemark(rs.getString("remark"));
			im.setDate(rs.getString("date"));

			list.add(im);
		}

		return list;
	}

	public ArrayList<PaymentModel> selectbysaleid(String id) throws SQLException {
		ArrayList<PaymentModel> list = new ArrayList<PaymentModel>();
		String sql = "select * from saleandservice.payment WHERE saleorderid LIKE ? order by saleorderid desc";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, "%" + id + "%");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			PaymentModel im = new PaymentModel();

			im.setPaymentid(rs.getString("paymentid"));
			im.setSaleorderid(rs.getString("saleorderid"));
			im.setStaffid(rs.getString("staffid"));
			im.setAmount(rs.getInt("amount"));
			im.setPaymenttype(rs.getString("paymenttype"));
			im.setRemark(rs.getString("remark"));
			im.setDate(rs.getString("date"));

			list.add(im);
		}

		return list;
	}

	public int countbysaleid(String id) throws SQLException {
		int i = 0;
		String sql = "select COUNT(*) from saleandservice.payment WHERE saleorderid=?";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			i = rs.getInt("COUNT(*)");
		}
		return i;
	}

}
