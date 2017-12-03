package hotelSystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class CreateAccount {
	
	static JPanel createAccountPanel = new JPanel();
		static JPanel titlePanel = new JPanel();
			static JLabel titleLabel = new JLabel("Account Details: ");
		static JPanel userPanel = new JPanel();
			static JLabel usernameLabel = new JLabel("E-mail: ");
			static JTextField usernameField = new JTextField();
			static JLabel passwordLabel = new JLabel("Password:  ");
			static JPasswordField passwordField = new JPasswordField();
		static JPanel namePanel = new JPanel();
			static JLabel firstNameLabel = new JLabel("First Name: ");
			static JTextField firstNameField = new JTextField();
			static JLabel lastNameLabel = new JLabel("Last Name: ");
			static JTextField lastNameField = new JTextField();
		static JPanel addressPanel = new JPanel();
			static JLabel mailingAddressLabel = new JLabel("Mailing Address: ");
		static JPanel streetPanel = new JPanel();
			static JLabel streetLabel = new JLabel("Street: ");
			static JTextField streetField = new JTextField();
			static JLabel cityLabel = new JLabel("Town/City: ");
			static JTextField cityField = new JTextField();
		static JPanel statePanel = new JPanel();
			static JLabel stateLabel = new JLabel("State: ");
			static String[] states = new String[57];
			static JComboBox<String> stateComboBox;
		static JPanel zipPanel = new JPanel();	
			static JLabel zipCodeLabel = new JLabel("ZIP Code: ");
			static JTextField zipCodeField = new JTextField();
		static JPanel phonePanel = new JPanel();
			static JLabel phoneLabel = new JLabel("Phone: ");
			static JTextField phoneField = new JTextField();
		static JPanel buttonPanel = new JPanel();	
			static JButton createButton = new JButton("Create");
			static JButton updateButton = new JButton("Update");
			static JButton backButton = new JButton("Back");
		
		
	public static void createAccountPanel() {
		Dimension labelSize = new Dimension(120,25);
		
		createAccountPanel.setLayout(new GridLayout(8,2,0,5));
		createAccountPanel.setBackground(Home.myColor);
		createAccountPanel.setBorder(CustomerAccount.border);
			
			titlePanel.setBackground(Home.myColor);
			titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			titlePanel.setBorder(CustomerAccount.border);
				titleLabel.setFont(Home.Serif.deriveFont(30f));
				titleLabel.setForeground(Home.fontColor);
			titlePanel.add(titleLabel);
			
			userPanel.setBackground(Home.myColor);
			userPanel.setLayout(new FlowLayout(5,20,FlowLayout.LEFT));
				usernameLabel.setFont(Home.Serif.deriveFont(20f));
				usernameLabel.setForeground(Home.fontColor);
				usernameLabel.setPreferredSize(labelSize);
				usernameField.requestFocus();
				usernameField.setPreferredSize(new Dimension(200,25));
				passwordLabel.setFont(Home.Serif.deriveFont(20f));
				passwordLabel.setForeground(Home.fontColor);
				passwordLabel.setPreferredSize(labelSize);
				passwordField.setPreferredSize(new Dimension(200,25));
			userPanel.add(usernameLabel);
			userPanel.add(usernameField);
			userPanel.add(passwordLabel);
			userPanel.add(passwordField);	
			
			namePanel.setBackground(Home.myColor);
			namePanel.setLayout(new FlowLayout(5,20,FlowLayout.LEFT));
				firstNameLabel.setFont(Home.Serif.deriveFont(20f));
				firstNameLabel.setForeground(Home.fontColor);
				firstNameLabel.setPreferredSize(labelSize);
				firstNameField.setPreferredSize(new Dimension(200,25));
				lastNameLabel.setFont(Home.Serif.deriveFont(20f));
				lastNameLabel.setForeground(Home.fontColor);
				lastNameLabel.setPreferredSize(labelSize);
				lastNameField.setPreferredSize(new Dimension(200,25));
			namePanel.add(firstNameLabel);
			namePanel.add(firstNameField);
			namePanel.add(lastNameLabel);
			namePanel.add(lastNameField);
			
			addressPanel.setBackground(Home.myColor);
			addressPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
				mailingAddressLabel.setFont(Home.Serif.deriveFont(20f));
				mailingAddressLabel.setForeground(Home.fontColor);
			addressPanel.add(mailingAddressLabel);
			
			streetPanel.setBackground(Home.myColor);
			streetPanel.setLayout(new FlowLayout(5,20,FlowLayout.LEFT));
				streetLabel.setFont(Home.Serif.deriveFont(20f));
				streetLabel.setForeground(Home.fontColor);
				streetLabel.setPreferredSize(labelSize);
				streetField.setPreferredSize(new Dimension(200,25));
				cityLabel.setFont(Home.Serif.deriveFont(20f));
				cityLabel.setForeground(Home.fontColor);
				cityLabel.setPreferredSize(labelSize);
				cityField.setPreferredSize(new Dimension(200,25));
			streetPanel.add(streetLabel);
			streetPanel.add(streetField);
			streetPanel.add(cityLabel);
			streetPanel.add(cityField);
			
			statePanel.setBackground(Home.myColor);
			statePanel.setLayout(new FlowLayout(5,5,FlowLayout.LEFT));
				stateLabel.setFont(Home.Serif.deriveFont(20f));
				stateLabel.setForeground(Home.fontColor);
				getStates(states);
				stateComboBox = new JComboBox<String>(states);
				stateComboBox.setSelectedIndex(-1);
				stateComboBox.setMaximumRowCount(4);
			statePanel.add(stateLabel);
			statePanel.add(stateComboBox);	
			
			zipPanel.setBackground(Home.myColor);
			zipPanel.setLayout(new FlowLayout(5,15,FlowLayout.LEFT));
				zipCodeLabel.setFont(Home.Serif.deriveFont(20f));
				zipCodeLabel.setForeground(Home.fontColor);
				zipCodeField.setPreferredSize(new Dimension(100,25));
			zipPanel.add(statePanel);	
			zipPanel.add(zipCodeLabel);
			zipPanel.add(zipCodeField);
			
			phonePanel.setLayout(new FlowLayout(5,20,FlowLayout.LEFT));
			phonePanel.setBackground(Home.myColor);	
				phoneLabel.setFont(phoneLabel.getFont().deriveFont(20f));
				phoneLabel.setForeground(Home.fontColor);
				phoneField.setPreferredSize(new Dimension(200,25));
			phonePanel.add(phoneLabel);
			phonePanel.add(phoneField);
			
			buttonPanel.setBackground(Home.myColor);
			buttonPanel.setBorder(CustomerAccount.border);
			buttonPanel.setLayout(new FlowLayout(20,200,FlowLayout.LEADING));
				createButton.setBackground(Color.WHITE);
				createButton.setPreferredSize(labelSize);
				createButton.addActionListener(new myActionListener());
				updateButton.setBackground(Color.WHITE);
				updateButton.setPreferredSize(labelSize);
				updateButton.addActionListener(new myActionListener());
				backButton.setBackground(Color.WHITE);
				backButton.setPreferredSize(labelSize);
				backButton.addActionListener(new myActionListener());
			buttonPanel.add(createButton);
			buttonPanel.add(updateButton);
			buttonPanel.add(backButton);
		
		createAccountPanel.add(titlePanel);
		createAccountPanel.add(userPanel);
		createAccountPanel.add(namePanel);
		createAccountPanel.add(addressPanel);
		createAccountPanel.add(streetPanel);
		createAccountPanel.add(zipPanel);
		createAccountPanel.add(phonePanel);
		createAccountPanel.add(buttonPanel);
		createAccountPanel.setVisible(false);
	}
	
	public static void createAccountMethod() {
		//CustomerAccount.customersPanel.setVisible(false);
		createAccountPanel.setVisible(true);
		createButton.setVisible(true);
		passwordField.setVisible(false);
		passwordLabel.setVisible(false);
		updateButton.setVisible(false);
	}
	
	public static void editAccountMethod() {
		
		createAccountPanel.setVisible(true);
		createButton.setVisible(false);
		passwordField.setVisible(false);
		passwordLabel.setVisible(false);
		updateButton.setVisible(true);
		usernameField.setText(CustomerAccount.getInfo("username", CustomerAccount.selectedAccountId));
		firstNameField.setText(CustomerAccount.getInfo("firstName", CustomerAccount.selectedAccountId));
		lastNameField.setText(CustomerAccount.getInfo("lastName", CustomerAccount.selectedAccountId));
		streetField.setText(CustomerAccount.getInfo("street", CustomerAccount.selectedAccountId));
		cityField.setText(CustomerAccount.getInfo("city", CustomerAccount.selectedAccountId));
		String s = CustomerAccount.getInfo("stateCode", CustomerAccount.selectedAccountId);
		for(int i=0;i<states.length;i++) {
			if(s.equals(states[i])) {
				stateComboBox.setSelectedIndex(i);
			}
		}
		zipCodeField.setText(CustomerAccount.getInfo("zipCode", CustomerAccount.selectedAccountId));
		phoneField.setText(CustomerAccount.getInfo("phone", CustomerAccount.selectedAccountId));
		
		
	}
	
	
	private static class myActionListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event){
			
			if(event.getSource() == backButton) {
				createAccountPanel.setVisible(false);
				
				CustomerAccount.customersPanel.setVisible(true);
				usernameField.setText("");	// this resets all the fields after back button is clicked
				passwordField.setText(""); 			
				firstNameField.setText("");			
				lastNameField.setText("");
				streetField.setText("");
				cityField.setText("");
				stateComboBox.setAction(null);
				zipCodeField.setText("");
				phoneField.setText("");
			}
			else {
				String username = usernameField.getText();
				if(checkUsername(username).equals(true)) {
					JOptionPane.showMessageDialog(null,"Username is already in use.");
					usernameField.setText("");
					passwordField.setText("");
					usernameField.requestFocus();
					
				}
				else if(event.getSource() == createButton){
					
					char[] password = passwordField.getPassword(); //to do: add methods to ensure password contains uppercase, lowercase, numbers etc...
					String firstName = firstNameField.getText();
					String lastName = lastNameField.getText();
					String street = streetField.getText();
					String city = cityField.getText();
					String stateCode = stateComboBox.getItemAt(stateComboBox.getSelectedIndex());
					String zipCode = zipCodeField.getText();
					String phone = phoneField.getText();
					phone.replaceAll("\\D","");
					if(check(username, password,firstName,lastName,street,city,zipCode,phone,stateCode)) {
						insertData(username,password,firstName,lastName,street,city,stateCode,zipCode,phone);//inserts fields into database
						JOptionPane.showMessageDialog(null,"Customer Added");
						createAccountPanel.setVisible(false);
						CustomerAccount.customersPanel.setVisible(true);//Home.frame.repaint();
						usernameField.setText("");	// this resets all the fields after account created
						passwordField.setText(""); 			
						firstNameField.setText("");			
						lastNameField.setText("");
						streetField.setText("");
						cityField.setText("");
						stateComboBox.setAction(null);
						zipCodeField.setText("");
						phoneField.setText("");
					}
				}
				else if(event.getSource() == updateButton){
					
					char[] password = new char[8];
					String firstName = firstNameField.getText();
					String lastName = lastNameField.getText();
					String street = streetField.getText();
					String city = cityField.getText();
					String stateCode = stateComboBox.getItemAt(stateComboBox.getSelectedIndex());
					String zipCode = zipCodeField.getText();
					String phone = phoneField.getText();
					phone.replaceAll("\\D","");
					if(check(username, password,firstName,lastName,street,city,zipCode,phone,stateCode)) {
						updateData(username,password,firstName,lastName,street,city,stateCode,zipCode,phone);//inserts fields into database
						createAccountPanel.setVisible(false);
						CustomerAccount.customersPanel.setVisible(true);//Home.frame.repaint();
						usernameField.setText("");	// this resets all the fields after account created
						passwordField.setText(""); 			
						firstNameField.setText("");			
						lastNameField.setText("");
						streetField.setText("");
						cityField.setText("");
						stateComboBox.setAction(null);
						zipCodeField.setText("");
						phoneField.setText("");
					}
				}
			}		
		}
	}
	
	//checks to make sure the correct information is entered for creating account
	public static Boolean check(String username,char[] password,String firstName,String lastName,String street,String city,String zipCode,String phone, String stateCode) {
		if(username.equals("")||firstName.equals("")||lastName.equals("")||street.equals("")||city.equals("")||zipCode.equals("")||phone.equals("")) {
			JOptionPane.showMessageDialog(null,"All Fields must be filled!");	// checks that all fields are filled out
			createAccountPanel.setVisible(true);
			CustomerAccount.customersPanel.setVisible(false);
		}
		else if(username.matches(".*@.*")==false) {
			JOptionPane.showMessageDialog(null, "Please enter a valid e-mail.");
		}
		else if(firstName.matches("[a-zA-Z]+")==false) {
			JOptionPane.showMessageDialog(null, "Invalid First name");//First name must be [a-z] or [A-Z}
		}
		else if(lastName.matches("[a-zA-Z]+")==false) {
			JOptionPane.showMessageDialog(null, "Invalid Last name");//Last name must be [a-z] or [A-Z}
		}
		else if(password.length<8||password.length==0) {
			JOptionPane.showMessageDialog(null, "Password must be 8 characters long!"); //password must be 8 characters long
		}
		else if(zipCode.length()!=5) {
			JOptionPane.showMessageDialog(null, "Incorrect Zip Code (Must be 5 Digits)");	//Zip code must be 5 digits
		}
		else if(phone.length()!=10||phone.matches("[0-9]+")==false) {
			JOptionPane.showMessageDialog(null, "Invalid Phone Number (Must be 10 Digits)");	//phone number must be 10 digits and between [0-9]
			}
		else {
			return true;
		}
		return false;
	}
		
	//reads file containing state codes for address - state comboBox
	private static void getStates (String[] states){
		
		try {
			Scanner	scan = new Scanner(new File("resource/stateCodes.txt"));
			for(int i=0;i<states.length;i++) {
				states[i] = scan.next();
				scan.nextLine();			
			}
			scan.close();
		}
		catch(IOException ex) {
			System.out.println("File not found: stateCodes");
		}
						
	}
	
	//queries database and returns true if user name is already in use. FOR ACTION LISTENER
	private static Boolean checkUsername(String username) {
		String sql = "SELECT username FROM staffAccounts";
		try(Connection conn = Database.connect();
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
	private static void insertData(String username,char[] password,String firstName,String lastName,String street,String city,String stateCode,String zipCode,String phone) {
		String sql = "INSERT INTO customerAccounts(username,password,firstName,lastName,street,city,stateCode,zipCode,phone)VALUES(?,?,?,?,?,?,?,?,?)";
		
		try(Connection conn = Database.connect();
			PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setString(1, username);
			pstmt.setString(2, Arrays.toString(password)); //to do: time permitting: encrypt passwords.
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
	}
	
	//adds new userAccount data to the database
		private static void updateData(String username,char[] password,String firstName,String lastName,String street,String city,String stateCode,String zipCode,String phone) {
			
			String sql = "UPDATE customerAccounts SET username = ?,firstName = ?, lastName = ?, street = ?, city = ?, stateCode = ?, zipCode = ?, phone = ? where AccountId = "+CustomerAccount.selectedAccountId;
			try(Connection conn = Database.connect();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
				
				pstmt.setString(1, username);
				pstmt.setString(2, firstName);
				pstmt.setString(3, lastName);
				pstmt.setString(4, street);
				pstmt.setString(5, city);
				pstmt.setString(6, stateCode);
				pstmt.setString(7, zipCode);
				pstmt.setString(8, phone);
				
				pstmt.executeUpdate();
			}
			catch(SQLException e) {
			System.out.println(e.getMessage());
			}
		}
	
}