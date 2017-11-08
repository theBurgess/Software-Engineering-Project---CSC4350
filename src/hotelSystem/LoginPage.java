package hotelSystem;

import java.sql.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;


/** 
 * 
 * first page that should appear when program is run. will match input username and password with database and log in 
 * the input user
 *
 */
public class LoginPage extends SuperPage{

	static JFrame frame = new JFrame("Hotel Management System");
	static JPanel panel = new JPanel();
	static JLabel label1 = new JLabel("Username: ");
	static JTextField username = new JTextField(20);
	static JLabel label3 = new JLabel("Password:  ");
	static JPasswordField password = new JPasswordField(15);
	static JButton button1 = new JButton("Login");
	
	
	
	public static void run() {
		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		panel.setSize(200,200);
		panel.setLocation(0,0);
		
		
		label1.setBounds(10,40,150,20);
		username.setBounds(100,40,150,20);
		label3.setBounds(10,80,150,20);
		password.setBounds(100,80,150,20);
		
		button1.setBounds(115, 120, 65, 20);
		button1.addActionListener(new myActionListener());
		
		
		panel.add(label1);
		panel.add(username);
		panel.add(label3);
		panel.add(password);
		panel.add(button1);
		
		
		frame.getContentPane().add(panel);
		frame.getRootPane().setDefaultButton(button1); //makes enter key = push button
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		username.requestFocus(); //where cursor appears
		
		
	}
		
	//describes what happens when button is clicked
	private static class myActionListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event){
						
			String u = username.getText();
			char[] p = password.getPassword();
			int i = isUser(u,p);
			if(i == 0){
				JOptionPane.showMessageDialog(null,"Username not found.");
				username.setText("");
				password.setText("");
				username.requestFocus();
			}
			else if(i == -1) {
				JOptionPane.showMessageDialog(null,"Password is incorrect.");
				password.setText("");
				password.requestFocus();
			}
			else{
				frame.dispose();
				setLoggedIn(true,i); //mark userid as logged in
				HomePage.run(i); //opens homepage and passes the userid as parameter
				
			}		
		}
	}
	
	
	/**
	 * queries database for matching username and password
	 * returns userid
	 */
	public static int isUser(String user, char[] pass) {
		String sql = "SELECT userid, username, password FROM staffAccounts";
		try(Connection conn = connect("BLOP.db");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)){
			
			while(rs.next()) {
				if(rs.getString("username").equals(user)) {
					if(rs.getString("password").equals(Arrays.toString(pass))) {
						return rs.getInt("userid");
					}
					else {
						return -1;
					}
				}
				
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}
	
	
}
