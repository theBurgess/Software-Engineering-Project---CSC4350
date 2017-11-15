package hotelSystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StaffAccount{
	
	
	
	public static String getInfo(String request, int accountId) {
			
		String sql = "SELECT "+request+" FROM StaffAccounts WHERE AccountId = "+accountId;
		try(Connection conn = Database.connect("BLOP.db")){
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			return rs.getString(request);
				}
		catch(SQLException e) {
			System.out.println(e.getMessage());
				}	
		return "";
		
	}	
	
	
}
