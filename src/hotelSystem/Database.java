package hotelSystem;

import java.sql.*;

public class Database {
		
		public static void build(String dbName) { //builds database
			
			dbCreate(dbName);
			staffAccountsTable("staffAccounts");
			customerAccountsTable("customerAccounts");
			
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
		
		//this will add a table representing staff accounts
		private static void staffAccountsTable(String tableName) {
			String dbLoc = "jdbc:sqlite:db/BLOP.db";
			
			String sql = "CREATE TABLE IF NOT EXISTS " +tableName+ "(\n"
					+" userid INTEGER PRIMARY KEY NOT NULL, \n"
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
				System.out.println("userAccount: administrator - added to table: userAccounts.");
		}
		
		
		

		//this will add a table representing customer accounts
		private static void customerAccountsTable(String tableName) {
			String dbLoc = "jdbc:sqlite:db/BLOP.db";
			
			String sql = "CREATE TABLE IF NOT EXISTS " +tableName+ "(\n"
					+" userid INTEGER PRIMARY KEY NOT NULL, \n"
					+" loggedIn BOOLEAN NOT NULL DEFAULT(0), \n"
					+" username text NOT NULL, \n"
					+" password text NOT NULL, \n"
					+" firstName text NOT NULL, \n"
					+" lastName text NOT NULL, \n"
					+" address text NOT NULL, \n"
					+" payment text NOT NULL\n"
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
	
}
