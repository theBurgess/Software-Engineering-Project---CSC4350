package hotelSystem;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;



import java.io.File;


public class Console {
		
	/**
	 * this class contains the main method that dictates what happens each time the program is executed
	 */
	public static void main(String[] args) {
		
		String dbName = "BLOP.db"; //what the database is called
		
		/** if the program is being run for the first time the database will be built
		 * 
		 */
		File file = new File("db/"+dbName);
		Boolean initialRun = !file.exists();	
		if(initialRun) {
			Database.build(dbName);
			
		}
		else {
			connect(dbName);
		}
		
		//populate database
		//Demo.addAccounts();
		//first action when program is run: Change to 1 to skip login screen
		Home.run(-1);
		AddReservation.addReservationPanel.setVisible(true);
		
	}
	
	//connects to database.
	private static Connection connect(String name) {
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
	
	
		
	
