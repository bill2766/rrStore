package jPac.dao;

import java.util.*;
import java.sql.*;
import jPac.model.*;

public class CityDAO {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/sj12?serverTimezone=UTC";
	private static final String ACCOUNT = "root";
	private static final String PASSWORD = "1234";
	
	private Connection getConn() {
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL,ACCOUNT,PASSWORD);
			return conn;
		}catch(Exception ex) {
			System.out.println("不能获取数据库连接！");
			ex.printStackTrace();
			return null;
		}
	}
	
	public String[] getCityByPro(String province) {
		Connection conn = getConn();
		Statement stmt = null;
		ResultSet rs = null;
		String sqlValue = "SELECT cityName FROM city where fromPro="+province;
		String[] list = new String[3];
		int i=0;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlValue);
			while (rs.next()) {
				list[i]=rs.getString("cityName");
				i=i+1;
			}
			return list;
		}catch(SQLException ex) {
			System.out.println("数据库操作发生错误！");
			ex.printStackTrace();
			return null;
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(SQLException ex){
				System.out.println("Close Error!!!!!!");
				ex.printStackTrace();
			}
		}
	}

}
