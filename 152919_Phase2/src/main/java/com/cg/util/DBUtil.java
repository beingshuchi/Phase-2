package com.cg.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.cg.exception.WalletException;

public class DBUtil {
	
	public static Connection getConnection() throws WalletException{
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "orcl11g");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new WalletException(e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new WalletException(e.getMessage());
		}

		
		
		return con;
		
	}
}
