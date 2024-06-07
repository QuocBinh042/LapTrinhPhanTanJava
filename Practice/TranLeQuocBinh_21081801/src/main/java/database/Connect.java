package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Connect {
	String url = "jdbc:sqlserver://localhost:1433;databaseName=CourseDB";
	String username = "sa";
	String password = "sapassword";
	public Connect() {
		super();
	}

	public Connection con() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, username, password);
			// JOptionPane.showMessageDialog(null, "Thành công");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return con;
	}

	public Statement stmt() {
		Statement stmt = null;
		try {
			stmt = con().createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return stmt;
	}

	public ResultSet resultSet(String sqlQuery) {
		ResultSet rs = null;
		try {
			rs = stmt().executeQuery(sqlQuery);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return rs;
	}
}
