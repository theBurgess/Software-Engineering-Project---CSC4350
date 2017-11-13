package hotelSystem;

import java.sql.*;
import javax.swing.*;


/**
 * 
 * this is a superclass for all page type classes that share common behaviour like connecting to the database. 
 * any gui page type class should extend this as a superclass 
 *
 */
public class SuperPage {
	
	static int userID;
	static JFrame frame = new JFrame("Hotel Management System");
		
	public static void run(int userId) {
		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);   //maximizes the window automatically
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	//connects to database.
	public static Connection connect(String name) {
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
	
	//changes value of boolean logged in in database;
	public static void setLoggedIn(Boolean b,int i) {
		String sql = "UPDATE staffAccounts SET loggedIn = ? where userid = ?";
		
		try(Connection conn = connect("BLOP.db");
			PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setBoolean(1, b);
			pstmt.setInt(2, i);
			pstmt.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}	
	
}
