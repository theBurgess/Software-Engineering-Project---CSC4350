package hotelSystem;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.DatabaseMetaData;
import java.sql.Statement;

public class Database {
	
	
		/**
		 * 
		 * this will be run when the program is run for the first time and will build the database and add all appropriate tables
		 * that represent state of all the main classes
		 */
		public static void build(String dbName) { //builds database
			
			dbCreate(dbName);
			customerResTable("customerReservations");
			roomResTable("roomReservations");
			staffAccountsTable("staffAccounts");
			customerAccountsTable("customerAccounts");
			guestRoomsTable("guestRooms");
			functionRoomsTable("functionRooms");
			previousLoginTable("previousLogin");
			ReservationTable_resturant("restaurantReservations");
			
		}
				
		//this will create database and confirm connection
		private static void dbCreate(String name) {
			Connection conn = null;
			try {
				
				String dbLoc = "jdbc:sqlite:db/"+name;
				conn = DriverManager.getConnection(dbLoc);
				System.out.println("Database: "+name+" - created");
				System.out.println("Connection to "+name+" - established.");
				
				DatabaseMetaData meta = conn.getMetaData();
				System.out.println("The driver name is "+meta.getDriverName());
			}
			catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			finally {
				try {
					if(conn != null) {
						conn.close();
					}
				}
				catch(SQLException ex) {
					System.out.println(ex.getMessage());
				}
			}
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
		
		private static void guestRoomsTable(String tableName) {
			String dbLoc = "jdbc:sqlite:db/BLOP.db";
			
			String sql = "CREATE TABLE IF NOT EXISTS " +tableName+ "(\n"
					+" roomNumber INTEGER PRIMARY KEY NOT NULL, \n"
					+" roomType text NOT NULL, \n"
					+" isOccupied BOOLEAN NOT NULL DEFAULT(0), \n"
					+" housekeeping BOOLEAN NOT NULL DEFAULT(0), \n"
					+" capacity INTEGER NOT NULL\n"
					+");";
			
			try(Connection conn = DriverManager.getConnection(dbLoc);
					Statement stmt = conn.createStatement()){
				stmt.execute(sql);
			}
			catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			
			System.out.println("Table: "+tableName+" - added to database: BLOP.db");
		}
		
		//this will add a table representing customer reservations
		private static void customerResTable(String tableName) {
			String dbLoc = "jdbc:sqlite:db/BLOP.db";
			
			String sql = "CREATE TABLE IF NOT EXISTS " +tableName+ "(\n"
					+" AccountId INTEGER PRIMARY KEY NOT NULL, \n"
					+" roomsBooked text NOT NULL DEFAULT(0), \n"
					+" checkIn text NOT NULL, \n"
					+" checkOut text NOT NULL, \n"
					+" nights INTEGER NOT NULL\n"
					+");";
			
			try(Connection conn = DriverManager.getConnection(dbLoc);
					Statement stmt = conn.createStatement()){
				stmt.execute(sql);
			}
			catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			
			System.out.println("Table: "+tableName+" - added to database: BLOP.db");
		}
		
		//this will add a table representing rooms added to a reservation
				private static void roomResTable(String tableName) {
					String dbLoc = "jdbc:sqlite:db/BLOP.db";
					
					String sql = "CREATE TABLE IF NOT EXISTS " +tableName+ "(\n"
							+" roomResId INTEGER PRIMARY KEY NOT NULL, \n"
							+" roomNumber INTEGER NOT NULL DEFAULT(0), \n"
							+" boardBasis text NOT NULL, \n"
							+" roomType text NOT NULL, \n"
							+" adults INTEGER NOT NULL, \n"
							+" children INTEGER NOT NULL\n"
							+");";
					
					try(Connection conn = DriverManager.getConnection(dbLoc);
							Statement stmt = conn.createStatement()){
						stmt.execute(sql);
					}
					catch(SQLException e) {
						System.out.println(e.getMessage());
					}
					
					System.out.println("Table: "+tableName+" - added to database: BLOP.db");
				}
				
				
		//this will add a table storing the last user to log in
		private static void previousLoginTable(String tableName) {
			String dbLoc = "jdbc:sqlite:db/BLOP.db";
			
			String sql1 = "CREATE TABLE IF NOT EXISTS " +tableName+ "(\n"
					+" AccountId INTEGER NOT NULL\n"
					+");";
			
			String sql2 = "INSERT INTO "+tableName+"(AccountId)VALUES(?)";
			
			try(Connection conn = DriverManager.getConnection(dbLoc)){
					Statement stmt = conn.createStatement();
					stmt.execute(sql1);
					
					PreparedStatement pstmt = conn.prepareStatement(sql2);
					pstmt.setString(1, "0");
					pstmt.executeUpdate();
						
			}
			catch(SQLException e) {
				System.out.println(e.getMessage());
			}			
			
			System.out.println("Table: "+tableName+" - added to database: BLOP.db");
		}
		
		
		//this will add a table representing staff accounts
		private static void staffAccountsTable(String tableName) {
			String dbLoc = "jdbc:sqlite:db/BLOP.db";
			
			String sql = "CREATE TABLE IF NOT EXISTS " +tableName+ "(\n"
					+" AccountId INTEGER PRIMARY KEY NOT NULL, \n"
					+" loggedIn BOOLEAN NOT NULL DEFAULT(0), \n"
					+" username text NOT NULL, \n"
					+" password text NOT NULL, \n"
					+" firstName text NOT NULL, \n"
					+" lastName text NOT NULL\n"
					+");";
			
			try(Connection conn = DriverManager.getConnection(dbLoc);
					Statement stmt = conn.createStatement()){
				stmt.execute(sql);
			}
			catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			
			System.out.println("Table: "+tableName+" - added to database: BLOP.db");
			createAdmin();
		}
		
		//adds row with default administrator account to staffAccounts table.
		private static void createAdmin() {
			
				String sql = "INSERT INTO staffAccounts(username,password,firstName,lastName)VALUES(?,?,?,?)";
				
				try(Connection conn = connect("BLOP.db");
					PreparedStatement pstmt = conn.prepareStatement(sql)){
					
					pstmt.setString(1, "administrator");
					pstmt.setString(2, "[a, d, m, i, n, 1, 2, 3, 4]");
					pstmt.setString(3, "System");
					pstmt.setString(4, "Administrator");
					pstmt.executeUpdate();
				}
				catch(SQLException e) {
					System.out.println(e.getMessage());
				}
				System.out.println("userAccount: administrator - added to table: staffAccounts.");
		}
		
		
		

		//this will add a table representing customer accounts
		private static void customerAccountsTable(String tableName) {
			String dbLoc = "jdbc:sqlite:db/BLOP.db";
			
			String sql = "CREATE TABLE IF NOT EXISTS " +tableName+ "(\n"
					+" AccountId INTEGER PRIMARY KEY NOT NULL, \n"
					+" loggedIn BOOLEAN NOT NULL DEFAULT(0), \n"
					+" username text NOT NULL, \n"
					+" password text NOT NULL, \n"
					+" firstName text NOT NULL, \n"
					+" lastName text NOT NULL, \n"
					+" street text NOT NULL, \n"
					+" city text, \n"
					+" stateCode text NOT NULL, \n"
					+" zipCode text NOT NULL, \n"
					+" phone text NOT NULL\n"
					+");";
			
			try(Connection conn = DriverManager.getConnection(dbLoc);
					Statement stmt = conn.createStatement()){
				stmt.execute(sql);
			}
			catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			System.out.println("Table: "+tableName+" - added to database: BLOP.db");
		}
		
		
		//this will add a table representing function Rooms
		private static void functionRoomsTable(String tableName) {
			String dbLoc = "jdbc:sqlite:db/BLOP.db";
			
			String sql = "CREATE TABLE IF NOT EXISTS " +tableName+ "(\n"
					+" roomId INTEGER PRIMARY KEY NOT NULL, \n"
					+" isOccupied BOOLEAN NOT NULL DEFAULT(0), \n"
					+" roomType INTEGER NOT NULL, \n"	
					+" occupancy INTEGER NOT NULL, \n"
					+" roomNumber text NOT NULL, \n"
					+" periodLength INTEGER NOT NULL, \n"
					+" basePrice Double NOT NULL, \n"
					+" priceMult Double NOT NULL, \n"
					+" roomPrice Double NOT NULL\n"
					+");";
				
			try(Connection conn = DriverManager.getConnection(dbLoc);
					Statement stmt = conn.createStatement()){
				stmt.execute(sql);
			}
			catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			System.out.println("Table: "+tableName+" - added to database: BLOP.db");
		}
		
		private static void ReservationTable_resturant(String tableName) {
			String dbLoc = "jdbc:sqlite:db/BLOP.db";

			String sql = "CREATE TABLE IF NOT EXISTS " +tableName+ "(\n"
					
					+" firstName text NOT NULL, \n"
					+" lastName text NOT NULL, \n"
					+" seats int NOT NULL, \n"
					+" date text NOT NULL, \n"
					+" time text NOT NULL, \n"
					+" time2 text\n"
					+");";

			try(Connection conn = DriverManager.getConnection(dbLoc);
					Statement stmt = conn.createStatement()){
				stmt.execute(sql);
			}
			catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			System.out.println("Table: "+tableName+" - added to database: BLOP.db");


		}
		
		/**private static void ReservationTable_reservation(String tableName) {
			String dbLoc = "jdbc:sqlite:db/BLOP.db";

			String sql = "CREATE TABLE IF NOT EXISTS " +tableName+ "(\n"
					+" firstName text NOT NULL, \n"
					+" lastName text NOT NULL, \n"
					+" seats int NOT NULL, \n"
					+" date text NOT NULL, \n"
					+" time text NOT NULL, \n"
					+" time2 text\n"
					+");";

			try(Connection conn = DriverManager.getConnection(dbLoc);
					Statement stmt = conn.createStatement()){
				stmt.execute(sql);
			}
			catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			
			System.out.println("Table: "+tableName+" - added to database: BLOP.db");


		}
	*/
}
