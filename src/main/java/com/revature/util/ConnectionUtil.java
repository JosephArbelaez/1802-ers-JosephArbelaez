package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;


public class ConnectionUtil {


	private static Logger logger = Logger.getLogger(ConnectionUtil.class);
	public static Connection getConnection() throws SQLException{
		String url = "jdbc:oracle:thin:@josepharbelaezrevature.c5dmvhjfs1dd.us-east-1.rds.amazonaws.com:1521:ORCL";
		String username = "REIMBURSEMENT_DB";
		String password = "p4ssw0rd";

		return DriverManager.getConnection(url, username, password);
	}
}