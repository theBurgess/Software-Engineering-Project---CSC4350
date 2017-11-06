package hotelSystem;

import java.sql.*;
import java.util.*;
import java.io.File;

public class Console {

public static void main(String[] args) {
		
		String dbName = "BLOP.db";
		File file = new File("db/"+dbName);
		Boolean initialRun = !file.exists();
		
		
		if(initialRun) {
			dbCreate(dbName);
			createNewTable("userAccounts");
			createAdmin();
		}
		else {
			connect(dbName);
		}
		
		LoginPage LP = new LoginPage();	
		//CreateAccountPage cap = new CreateAccountPage();
		//HomePage hp = new HomePage("name");

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
	
	//this will add a table(representing an object) to the database
	private static void createNewTable(String tableName) {
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
	}
	
	//creates default administrator account if one does not exist. (initial run)
	private static void createAdmin() {
		
		if(hasAdmin()==false) {
			String sql = "INSERT INTO userAccounts(username,password,firstName,lastName)VALUES(?,?,?,?)";
			
			try(Connection conn = connect("BLOP.db");
				PreparedStatement pstmt = conn.prepareStatement(sql)){
				
				pstmt.setString(1, "administrator");
				pstmt.setString(2, "admin1234");
				pstmt.setString(3, "System");
				pstmt.setString(4, "Administrator");
				pstmt.executeUpdate();
			}
			catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			System.out.println("userAccount: administrator - added to table: userAccounts.");
		}
	}
	
	private static Boolean hasAdmin() {
		String sql = "SELECT username FROM userAccounts";
		try(Connection conn = connect("BLOP.db");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)){
			
			while(rs.next()) {
				if(rs.getString("username").equals("administrator")) {
					return true;
				}
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
}
