package hotelSystem;

import java.util.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

//This page will be a superclass to add new user accounts for both customer and staff accounts to the database
public class CreateAccountPage extends SuperPage {
		
	static JFrame frame = new JFrame("Hotel Management System");
	static JPanel panel = new JPanel();
	static JLabel label1 = new JLabel("Username: ");
	static JTextField username = new JTextField(20);
	static JLabel label3 = new JLabel("Password:  ");
	static JPasswordField password = new JPasswordField(15);
	static JLabel label4 = new JLabel("First Name: ");
	static JTextField firstName= new JTextField(20);
	static JLabel label5 = new JLabel("Last Name: ");
	static JTextField lastName = new JTextField(20);
	static JLabel label6 = new JLabel("Address: ");
	static JTextField address = new JTextField(20);
	static JLabel label7= new JLabel("Phone: ");
	static JTextField phone1 = new JTextField(3);
	static JTextField phone2 = new JTextField(3);
	static JTextField phone3 = new JTextField(4);
	static JButton button = new JButton("Ok");
		
	
	public static void run() {		//this is where specific page layout goes
		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		panel.setLayout(null);
		
		label1.setBounds(10,40,150,20);
		username.setBounds(100,40,150,20);
		label3.setBounds(300,40,150,20);
		password.setBounds(390,40,150,20);
		label4.setBounds(10,70,150,20);
		firstName.setBounds(100,70,150,20);
		label5.setBounds(300,70,150,20);
		lastName.setBounds(390,70,150,20);
		
		button.setBounds(250, 250, 100, 20);
		
		panel.add(label1);
		panel.add(username);
		panel.add(label3);
		panel.add(password);
		panel.add(label4);
		panel.add(firstName);
		panel.add(label5);
		panel.add(lastName);
		panel.add(button);
		button.addActionListener(new myActionListener());
		
		frame.getContentPane().add(panel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		username.requestFocus();
		
	}
		
	private static class myActionListener implements ActionListener {
			
		public void actionPerformed(ActionEvent event){
			String user = username.getText();
			char[] pass = password.getPassword(); 
			String first = firstName.getText();
			String last = lastName.getText();
			if(checkUsername(user)==true) {
				JOptionPane.showMessageDialog(null,"Username is already in use.");
				username.setText("");
				password.setText("");
				username.requestFocus();
			}
			insertData(user,pass,first,last);
			frame.dispose();
		
		}
	}
			
	//queries database and returns true if user name is already in use.
	private static Boolean checkUsername(String username) {
		String sql = "SELECT username FROM staffAccounts";
		try(Connection conn = connect("BLOP.db");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)){
			
			while(rs.next()) {
				if(rs.getString("username").equals(username)) {
					return true;
				}
			
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	//adds new userAccount data to the database
	private static void insertData(String user,char[] pass,String first,String last) {
		String sql = "INSERT INTO staffAccounts(username,password,firstName,lastName)VALUES(?,?,?,?)";
		
		try(Connection conn = connect("BLOP.db");
			PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setString(1, user);
			pstmt.setString(2, Arrays.toString(pass)); //time permitting: encrypt passwords
			pstmt.setString(3, first);
			pstmt.setString(4, last);
			pstmt.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}	
		
}
