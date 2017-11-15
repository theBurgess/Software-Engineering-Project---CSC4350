package hotelSystem;

import java.sql.Connection;
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
	
	
	//checks database to see if userAccount exists and is not currently logged in
	private static int confirmLogin(String username, char[] password) {
		String sql = "SELECT userId,  username, password FROM staffAccounts";
		try(Connection conn = Database.connect("BLOP.db")){
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			int userId = -1;
			while(rs.next()) {
				if(rs.getString("username").equals(username)) {
					if(rs.getString("password").equals(Arrays.toString(password))) {
						userId = rs.getInt("userId");					
					}
					else {
						return 0; //incorrect password
					}
				}	
			}
			if(getLoggedIn(userId) == true) {    // to prevent multiple logins of same user
				setLoggedIn(false,userId);
			}
			return userId;
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return -1; //incorrect username
	}
	
	//returns true if user is currently LoggedIn
	public static Boolean getLoggedIn(int userId) {
		String sql = "SELECT LoggedIn FROM staffAccounts";
		
		try(Connection conn = Database.connect("BLOP.db")){
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			return rs.getBoolean("LoggedIn");
		
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
		
	//changes value of boolean loggedIn in database;
	public static void setLoggedIn(Boolean b,int i) {
		String sql = "UPDATE staffAccounts SET loggedIn = ? WHERE userId = ?";
		PreparedStatement pstmt = null;
		try(Connection conn = Database.connect("BLOP.db")){
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setBoolean(1, b);
			pstmt.setInt(2, i);
			pstmt.executeUpdate();
			
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}		
	}	

}
