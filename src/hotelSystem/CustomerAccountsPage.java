package hotelSystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;

public class CustomerAccountsPage extends SuperPage{
	
	static JTextArea textArea1;
	
	static JPanel panel = new JPanel();
	
	
	
	
	
	
	public static void run(int userId, int customerId) {
			
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);   //maximizes the window automatically
		
		panel.setLayout(null);
		String s = getInfo(customerId);
		System.out.print(s);
		textArea1 = new JTextArea(s);
		textArea1.setBounds(10,0,350,50);
		
		panel.add(textArea1);
		
		frame.getContentPane().add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	
	private static String getInfo(int customerId) {
		String sql = "SELECT firstName, lastName, mailingAddressLine1, mailingAddressLine2, stateCode, zipCode FROM customerAccounts WHERE customerId = "+customerId;
		try(Connection conn = connect("BLOP.db");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)){
			
			String info = "First Name: "+rs.getString("firstName")+"\t"+"Last Name: "+rs.getString("lastName")+"\n"
			+"Mailing Address: "+rs.getString("mailingAddressLine1")+", "+rs.getString("mailingAddressLine2")+" "+rs.getString("stateCode")+", "+rs.getString("zipCode");
			return info;
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return "";
	}
	

}
