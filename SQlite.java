package project;

import java.sql.*;
import javax.swing.*;

public class SQlite {
	
Connection con = null;

public static Connection dbConnector() 
{
	try {
		Class.forName("org.sqlite.JDBC");
		Connection con = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Eunice\\Documents\\XU-Files\\Sayge\\VSC\\GRADE\\src\\project\\grade.db");
		
		return con;
	} catch (Exception e) {
		JOptionPane.showMessageDialog(null , "connected");
		return null;
	}

	
}
}
