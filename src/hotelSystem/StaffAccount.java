package hotelSystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StaffAccount{
	
	
	public static String getInfo(String request, int accountId) {
		if(accountId >0) {	
			String sql = "SELECT "+request+" FROM staffAccounts WHERE AccountId = "+accountId;
			try(Connection conn = Database.connect()){
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				
				String s = rs.getString(request);
				if(s != null) {
					return s;
				}
			}
			catch(SQLException e) {
				System.out.println(e.getMessage());
					}
		}
		return "";
		
	}	
	
	
}
