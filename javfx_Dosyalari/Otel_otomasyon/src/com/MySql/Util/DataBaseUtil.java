package com.MySql.Util;
import java.sql.*;
import application.CustomAlertButton;

public class DataBaseUtil {

	static Connection cnn=null;
	static CustomAlertButton Alert;
	public static Connection Connect()
	{
		try {
			String url = "jdbc:mysql://localhost/oteldb";
			String user = "root";
			String password = "mysql";	 
			cnn=DriverManager.getConnection(url,user,password);
			return cnn;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
			return null;
		}
	}

}
