package hotelSystem;

import java.sql.*;
import javax.swing.*;

public class SuperPage {
	
	static int userID;
	static JFrame frame = new JFrame("Hotel Management System");
		
	public static void run() {
		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
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
