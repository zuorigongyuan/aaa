package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class JdbcDemo {
	public static Connection getConnection() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/test";
		String username = "root";
		String password = "123456";
		Connection connection = null;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	// 管闭流
	public static void closeStream(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void insertUser() {
		Connection connection = getConnection();
		String sql = "insert into user(name,pwd)values(?,?)";
		PreparedStatement pstmt = null;
		try {
			for (int i = 0; i < 1000000000; i++) {
				pstmt = (PreparedStatement) connection.prepareStatement(sql);
				pstmt.setString(1, "nacy" + i);
				pstmt.setString(2, "123" + i);
				pstmt.executeUpdate();

			}
		 
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void selectUser() {
		Connection connection = getConnection();
		String sql = "select*from user";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = (PreparedStatement) connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 获取列数
			int col = rs.getMetaData().getColumnCount();
			System.out.println(col);
			while (rs.next()) {
				for (int i = 1; i <= col; i++) {
					System.out.print(rs.getString(i) + "\t");
					if ((i == 2)) {
						System.out.print("\t");
					}
				}
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStream(connection, pstmt, rs);
		}

	}

	public static void main(String[] args) {
		insertUser();
	}
}
