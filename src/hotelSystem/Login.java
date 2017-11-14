package hotelSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class Login  {
	
	public static int run(String username, char[] password) {
		int i = confirmLogin(username,password);
		if(!(i == 0 || i == -1)) {
			setLoggedIn(true, i);
		}
		return i;
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
	
	//checks database to see if userAccount exists
	public static int confirmLogin(String username, char[] password) {
		String sql = "SELECT userId, username, password FROM staffAccounts";
		try(Connection conn = connect("BLOP.db");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)){
			
			while(rs.next()) {
				if(rs.getString("username").equals(username)) {
					if(rs.getString("password").equals(Arrays.toString(password))) {
						return rs.getInt("userId");
					}
					else {
						return 0;
					}
				}
				
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return -1;
	}
	
		
	//changes value of boolean loggedIn in database;
	public static void setLoggedIn(Boolean b,int i) {
		String sql = "UPDATE staffAccounts SET loggedIn = ? WHERE userId = ?";
		
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
