package hotelSystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StaffAccount extends SuperPage {

	
	public static String getName(int userId) {
		String sql = "SELECT firstName, lastName FROM staffAccounts";
		
		try(Connection conn = Database.connect("BLOP.db");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)){
			
			String name = rs.getString("firstName")+" "+rs.getString("lastName");;
			return name;
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
}
