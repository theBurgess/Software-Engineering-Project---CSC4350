package hotelSystem;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Demo {

		
	//adds fake customer accounts to db. (takes forever, find better way to do this)
	public static void addAccounts() {
		
		int i = 0;
		String username, password, firstName, lastName, street, city, stateCode, zipCode, phone;
		String sql = "INSERT INTO customerAccounts(username,password,firstName,lastName,street,city,stateCode,zipCode,phone)VALUES(?,?,?,?,?,?,?,?,?)";
		
		try {
			Scanner scan = new Scanner(new File("resource/accounts.csv"));
			scan.useDelimiter(",|\\n");
			scan.nextLine();
			while(scan.hasNextLine()) {
				
				i++;
				username = scan.next();
				password = "password";
				firstName = scan.next();
				lastName = scan.next();
				street = scan.next();
				city = scan.next();
				stateCode = scan.next();
				zipCode = scan.next();
				phone = scan.next();
				
				
				try(Connection conn = Database.connect("BLOP.db");
						PreparedStatement pstmt = conn.prepareStatement(sql)){
						
						pstmt.setString(1, username);
						pstmt.setString(2, password);
						pstmt.setString(3, firstName);
						pstmt.setString(4, lastName);
						pstmt.setString(5, street);
						pstmt.setString(6, city);
						pstmt.setString(7, stateCode);
						pstmt.setString(8, zipCode);
						pstmt.setString(9, phone);
						pstmt.executeUpdate();
					}
					catch(SQLException e) {
						System.out.println(e.getMessage());
					}
				System.out.println(i);
			}
			scan.close();
		}
		catch(IOException ex) {
			System.out.println("file not found: accounts.csv");
		}
		
		
		
		
}
}
