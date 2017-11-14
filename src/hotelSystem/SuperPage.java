package hotelSystem;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * 
 * this is a superclass for all page type classes that share common behaviour like connecting to the database. 
 * any gui page type class should extend this as a superclass 
 *
 */
public class SuperPage {
	
	static JPanel panel1 = new JPanel();
	static int userID;
	static ImageIcon myIcon = new ImageIcon("resource/icon.png");
	static ImageIcon myBackground = new ImageIcon("resource/hotel.jpg");
	static JFrame frame = new JFrame("Hotel Management System");
		
	public static void run(int userId) {
		
		frame.setLayout(null);
		frame.setContentPane(new JLabel(myBackground));
		frame.setIconImage(myIcon.getImage());
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);			
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		panel1.setBounds(0,0,1920,50);
		
		
		frame.getContentPane().add(panel1);
		frame.pack();
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
		String sql = "UPDATE staffAccounts SET loggedIn = ? WHERE userid = ?";
		
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
	
	public static int searchCustomer(String s) {
		String sql = "SELECT customerID, username, firstName, lastName FROM customerAccounts";
		try(Connection conn = connect("BLOP.db");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)){
			
			while(rs.next()) {
				if(rs.getString("username").equals(s)) {
					return rs.getInt("customerID");
					
				}	
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return -1;	
	}
	
}
