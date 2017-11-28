package hotelSystem;

import java.awt.Color;
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
		static JLabel usernameLabel = new JLabel("E-mail: ");
		static JTextField usernameField = new JTextField();
		static JLabel passwordLabel = new JLabel("Password:  ");
		static JPasswordField passwordField = new JPasswordField();
		static JLabel firstNameLabel = new JLabel("First Name: ");
		static JTextField firstNameField = new JTextField();
		static JLabel lastNameLabel = new JLabel("Last Name: ");
		static JTextField lastNameField = new JTextField();
		static JLabel mailingAddressLabel = new JLabel("Mailing Address: ");
		static JLabel streetLabel = new JLabel("Street: ");
		static JLabel cityLabel = new JLabel("Town/City: ");
		static JTextField streetField = new JTextField();
		static JTextField cityField = new JTextField();
		static JLabel stateLabel = new JLabel("State: ");
		static String[] states = new String[57];
		static JComboBox<String> stateComboBox;
		static JLabel zipCodeLabel = new JLabel("ZIP Code: ");
		static JTextField zipCodeField = new JTextField();
		static JLabel phoneLabel = new JLabel("Phone: ");
		static JTextField phoneField = new JTextField();
		static JButton createButton = new JButton("Create");
		static JButton updateButton = new JButton("Update");
		static JButton backButton = new JButton("Back");
		
		
	public static void createAccountPanel() {
		
		createAccountPanel.setLayout(null);
		createAccountPanel.setBackground(Home.myColor);
		createAccountPanel.setBounds(170,100,480,480);
		
			usernameLabel.setBounds(10,10,140,25);
			usernameLabel.setFont(usernameLabel.getFont().deriveFont(20f));
			usernameField.setBounds(125,10,200,25);
			usernameField.requestFocus();
			passwordLabel.setBounds(10,50,140,25);
			passwordLabel.setFont(passwordLabel.getFont().deriveFont(20f));
			passwordField.setBounds(125,50,200,25);
			firstNameLabel.setBounds(10,100,140,25);
			firstNameLabel.setFont(firstNameLabel.getFont().deriveFont(20f));
			firstNameField.setBounds(125,100,200,25);
			lastNameLabel.setBounds(10,140,140,25);
			lastNameLabel.setFont(lastNameLabel.getFont().deriveFont(20f));
			lastNameField.setBounds(125,140,200,25);
			mailingAddressLabel.setBounds(10,180,180,25);
			mailingAddressLabel.setFont(mailingAddressLabel.getFont().deriveFont(20f));
			streetLabel.setBounds(15,220,140,25);
			streetLabel.setFont(streetLabel.getFont().deriveFont(20f));
			streetField.setBounds(125,220,200,25);
			cityLabel.setBounds(15,260,140,25);
			cityLabel.setFont(cityLabel.getFont().deriveFont(20f));
			cityField.setBounds(125,260,200,25);
			stateLabel.setBounds(15,300,180,25);
			stateLabel.setFont(stateLabel.getFont().deriveFont(20f));
			getStates(states);
			stateComboBox = new JComboBox<String>(states);
			stateComboBox.setSelectedIndex(-1);
			stateComboBox.setMaximumRowCount(4);
			stateComboBox.setBounds(125,300,50,25);
			zipCodeLabel.setBounds(15,340,180,25);
			zipCodeLabel.setFont(zipCodeLabel.getFont().deriveFont(20f));
			zipCodeField.setBounds(125,340,80,25);
			phoneLabel.setBounds(15,380,180,25);
			phoneLabel.setFont(phoneLabel.getFont().deriveFont(20f));
			phoneField.setBounds(125,380,240,25);
			createButton.setBounds(15, 420, 100, 20);
			createButton.setBackground(Color.WHITE);
			createButton.addActionListener(new myActionListener());
			updateButton.setBounds(15, 420, 100, 20);
			updateButton.setBackground(Color.WHITE);
			updateButton.addActionListener(new myActionListener());
			backButton.setBounds(150, 420, 100, 20);
			backButton.setBackground(Color.WHITE);
			backButton.addActionListener(new myActionListener());
		
		createAccountPanel.add(usernameLabel);
		createAccountPanel.add(usernameField);
		createAccountPanel.add(passwordLabel);
		createAccountPanel.add(passwordField);
		createAccountPanel.add(firstNameLabel);
		createAccountPanel.add(firstNameField);
		createAccountPanel.add(lastNameLabel);
		createAccountPanel.add(lastNameField);
		createAccountPanel.add(mailingAddressLabel);
		createAccountPanel.add(streetLabel);
		createAccountPanel.add(streetField);
		createAccountPanel.add(cityLabel);
		createAccountPanel.add(cityField);
		createAccountPanel.add(stateLabel);
		createAccountPanel.add(stateComboBox);	
		createAccountPanel.add(zipCodeLabel);
		createAccountPanel.add(zipCodeField);
		createAccountPanel.add(phoneLabel);
		createAccountPanel.add(phoneField);
		createAccountPanel.add(createButton);
		createAccountPanel.add(updateButton);
		createAccountPanel.add(backButton);
		createAccountPanel.setVisible(false);
	}
	
	public static void createAccountMethod() {
		CustomerAccount.customersPanel.setVisible(false);
		createAccountPanel.setVisible(true);
		createButton.setVisible(true);
		passwordField.setVisible(true);
		updateButton.setVisible(false);
	}
	
	public static void editAccountMethod() {
		CustomerAccount.customersPanel.setVisible(false);
		createAccountPanel.setVisible(true);
		createButton.setVisible(false);
		passwordField.setVisible(false);
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
		try(Connection conn = Database.connect("BLOP.db");
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
		
		try(Connection conn = Database.connect("BLOP.db");
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
			try(Connection conn = Database.connect("BLOP.db");
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