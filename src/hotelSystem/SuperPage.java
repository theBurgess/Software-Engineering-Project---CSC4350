package hotelSystem;

import java.sql.*;
//import javax.swing.*;
//import java.awt.event.*;

public class SuperPage {

		
	public void run(String name) {
		
	}
	
	//connects to database.
	protected static final Connection connect(String name) {
		String dbLoc = "jdbc:sqlite:db/"+name;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(dbLoc);
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
}
