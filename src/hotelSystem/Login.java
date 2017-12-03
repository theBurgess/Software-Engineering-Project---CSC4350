package hotelSystem;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class Login  {
	
	//**************************************************************************
		// Attributes used by loginPanel
		static JPanel loginPanel = new JPanel();
			
			
			
			static JPanel usernamePanel = new JPanel();
			static JTextField usernameField = new JTextField(20);
			static JPanel passwordPanel = new JPanel();
			static JPasswordField passwordField = new JPasswordField(20);
			static JButton loginButton = new JButton("Login");
	//**************************************************************************
			
	//login panel to be displayed when no user is logged in
	public static void loginPanel() {
		

		TitledBorder lBorder = new TitledBorder(BorderFactory.createEtchedBorder(Home.fontColor,new Color(210,180,140)),"",TitledBorder.LEFT,TitledBorder.TOP,Home.Serif);
		loginPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		loginPanel.setBackground(Home.myColor);
		loginPanel.setBorder(lBorder);	
		
			TitledBorder uBorder = new TitledBorder(BorderFactory.createEtchedBorder(Home.fontColor,new Color(210,180,140)),"Username",TitledBorder.LEFT,TitledBorder.TOP,Home.Serif);
			uBorder.setTitleColor(Home.fontColor);
			usernamePanel.setBorder(uBorder);
			usernamePanel.setBackground(Home.myColor);
			usernamePanel.add(usernameField);
			usernameField.setText(StaffAccount.getInfo("username", Login.getPreviousLogin()));
			
			TitledBorder pBorder = new TitledBorder(BorderFactory.createEtchedBorder(Home.fontColor,new Color(210,180,140)),"Password",TitledBorder.LEFT,TitledBorder.TOP,Home.Serif);
			pBorder.setTitleColor(Home.fontColor);
			passwordPanel.setBorder(pBorder);
			passwordPanel.setBackground(Home.myColor);
			passwordPanel.add(passwordField);
			
			loginButton.setBackground(Color.white);
			loginButton.setFont(Home.Serif.deriveFont(20f));
			loginButton.setForeground(Home.fontColor);
			loginButton.addActionListener(new myActionListener());
		
		loginPanel.add(usernamePanel);
		loginPanel.add(passwordPanel);
		loginPanel.add(loginButton);
		
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
			
			Home.activePanel.setVisible(true);
			Home.scroll.setViewportView(Home.homePanel);
			
			//this gets name of the active user
			Home.name = StaffAccount.getInfo("firstName",Home.AccountId)+" "+StaffAccount.getInfo("lastName",Home.AccountId);
			Home.nameLabel.setText(Home.name);
			Menu.menuPanel.setVisible(true);
		}
	}	
	
	public static void logOutMethod() {
		setLoggedIn(false,Home.AccountId);
		passwordField.setText("");
		passwordField.requestFocus();
		Home.scroll.setViewportView(Home.introPanel);	
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
		try(Connection conn = Database.connect()){
			
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
		try(Connection conn = Database.connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)){
			return rs.getInt("AccountId");
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("fail");
		}
		return 0;
	}
	
	//checks database to see if userAccount exists and is not currently logged in
	private static int confirmLogin(String username, char[] password) {
		String sql = "SELECT AccountId, username, password FROM staffAccounts";
		try(Connection conn = Database.connect()){
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
		
		try(Connection conn = Database.connect()){
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
		try(Connection conn = Database.connect()){
			
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
