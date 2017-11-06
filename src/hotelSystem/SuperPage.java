package hotelSystem;

import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

public class SuperPage extends JFrame {

	public SuperPage(String name) {
		setSize(600,400);
		setLocation(600,300);
		
	}
	
	//connects to database.
	protected final Connection connect(String name) {
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
