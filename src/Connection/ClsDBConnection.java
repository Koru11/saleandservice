package Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class ClsDBConnection {
	private final String CONNECTION = "jdbc:mysql://localhost:3306/saleandservice?characterEncoding=latin1&useConfigs=maxPerformance";
	private final String PASSWORD = "root";
	private static Connection con = null;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		if (null == con) {
			con = (Connection) DriverManager.getConnection(this.CONNECTION, this.PASSWORD, this.PASSWORD);
		}
		return con;
	}

	public static void main(String[] args) throws SQLException {
		ClsDBConnection cls = new ClsDBConnection();
		System.out.println(cls.getConnection());
	}

	public ResultSet sqlQuery(String field, String table) throws ClassNotFoundException {
		ResultSet rs = null;
		try {
			Statement stm = (Statement) getConnection().createStatement();
			rs = stm.executeQuery("SELECT " + field + " FROM " + table);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;

	}

	public String AutoID(String field, String table, String prefix) throws ClassNotFoundException {

		ResultSet rs = sqlQuery(field, table);
		int current;

		try {
			java.util.ArrayList result = new java.util.ArrayList<>();
			while (rs.next()) {
				result.add(rs.getString(field));
			}
			if (!result.isEmpty()) {
				current = Integer.parseInt(result.get(result.size() - 1).toString().substring(3, 10)) + 1;
				return String.format("%s%07d", prefix, current);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return prefix + "0000001";
	}

	public String AutoIDforitem(String id, String prefix) throws ClassNotFoundException {

		int current;
		current = Integer.parseInt(id.substring(3, 10) + 1);

		return String.format("%s%07d", prefix, current);

	}

}
