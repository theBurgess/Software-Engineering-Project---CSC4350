package hotelSystem;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login  {
	
	//**************************************************************************
		// Attributes used by loginPanel
		static JPanel loginPanel = new JPanel();
			
			static JLabel usernameLabel = new JLabel("Username: ");
			static JTextField usernameField = new JTextField();
			static JLabel passwordLabel = new JLabel("Password:  ");
			static JPasswordField passwordField = new JPasswordField();
			static JButton loginButton = new JButton("Login");
	//**************************************************************************
	
			
	//login panel to be displayed when no user is logged in
	public static void loginPanel() {
		loginPanel.setLayout(null);
		loginPanel.setBackground(Home.myColor);
		loginPanel.setBounds(0,0,1920,40);
			
			usernameLabel.setBounds(10,10,180,25);
			usernameLabel.setFont(usernameLabel.getFont().deriveFont(20f));
			usernameLabel.setForeground(Home.fontColor);
			usernameField.setBounds(120,10,120,25);
			usernameField.setText(StaffAccount.getInfo("username", Login.getPreviousLogin()));
			passwordLabel.setBounds(250,10,180,25);
			passwordLabel.setFont(passwordLabel.getFont().deriveFont(20f));
			passwordLabel.setForeground(Home.fontColor);
			passwordField.setBounds(360,10,120,25);
			loginButton.setBackground(Color.white);
			loginButton.setBounds(490,10,65,25);
			loginButton.addActionListener(new myActionListener());
			
			
		loginPanel.add(usernameLabel);
		loginPanel.add(usernameField);
		loginPanel.add(passwordLabel);
		loginPanel.add(passwordField);
		loginPanel.add(loginButton);
		loginPanel.setVisible(false);
		
	}	
	
	public static void loginMethod() {
		
		String username = usernameField.getText();
		char[] password = passwordField.getPassword();
		Home.AccountId = run(username,password);
		if(Home.AccountId == -1){
			JOptionPane.showMessageDialog(null,"Username not found.");
			usernameField.setText("");
			passwordField.setText("");
			usernameField.requestFocus();
		}
		else if(Home.AccountId == 0) {
			JOptionPane.showMessageDialog(null,"Password is incorrect.");
			passwordField.setText("");
			passwordField.requestFocus();
		}
		else {
			loginPanel.setVisible(false);
			Home.activePanel.setVisible(true);
			Home.name = StaffAccount.getInfo("firstName",Home.AccountId)+" "+StaffAccount.getInfo("lastName",Home.AccountId);
			Home.nameLabel.setText("Current User: "+Home.name+":");
			CustomerAccount.customersPanel.setVisible(true);
			Menu.menuPanel.setVisible(true);
			
			Home.frame.repaint();
		}
	}	
	
	public static void logOutMethod() {
		setLoggedIn(false,Home.AccountId);
		passwordField.setText("");
		passwordField.requestFocus();
		Home.activePanel.setVisible(false);
		CustomerAccount.customersPanel.setVisible(false);
		CreateAccount.createAccountPanel.setVisible(false);
		loginPanel.setVisible(true);
		Home.frame.repaint();
		
	}
	
	private static class myActionListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event){
			
			loginMethod();		
		
		}
	}
			
	private static int run(String username, char[] password) {
		int i = confirmLogin(username,password);
		if(!(i == 0 || i == -1)) {
			
			setLoggedIn(true, i);
			setPreviousLogin(i);
		}
		return i;
	}
	
		
	private static void setPreviousLogin(int AccountId) {
		String sql = "UPDATE previousLogin SET AccountId = ?";
		PreparedStatement pstmt = null;
		try(Connection conn = Database.connect("BLOP.db")){
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, AccountId);
			pstmt.executeUpdate();
			
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}		
	}
	
	private static int getPreviousLogin() {
		String sql = "SELECT AccountId FROM previousLogin";
				
		try(Connection conn = Database.connect("BLOP.db");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)){

			return rs.getInt("AccountId");
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}
	
	//checks database to see if userAccount exists and is not currently logged in
	private static int confirmLogin(String username, char[] password) {
		String sql = "SELECT AccountId, username, password FROM staffAccounts";
		try(Connection conn = Database.connect("BLOP.db")){
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			int AccountId = -1;
			while(rs.next()) {
				if(rs.getString("username").equals(username)) {
					if(rs.getString("password").equals(Arrays.toString(password))) {
						AccountId = rs.getInt("AccountId");					
					}
					else {
						return 0; //incorrect password
					}
				}	
			}
			if(getLoggedIn(AccountId) == true) {    // to prevent multiple logins of same user
				setLoggedIn(false,AccountId);
			}
			return AccountId;
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return -1; //incorrect username
	}
	
	//returns true if user is currently LoggedIn
	private static Boolean getLoggedIn(int AccountId) {
		String sql = "SELECT LoggedIn FROM staffAccounts";
		
		try(Connection conn = Database.connect("BLOP.db")){
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			return rs.getBoolean("LoggedIn");
		
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
		
	//changes value of boolean loggedIn in database;
	private static void setLoggedIn(Boolean b,int i) {
		String sql = "UPDATE staffAccounts SET loggedIn = ? WHERE AccountId = ?";
		PreparedStatement pstmt = null;
		try(Connection conn = Database.connect("BLOP.db")){
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setBoolean(1, b);
			pstmt.setInt(2, i);
			pstmt.executeUpdate();
			
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}		
	}	

}
