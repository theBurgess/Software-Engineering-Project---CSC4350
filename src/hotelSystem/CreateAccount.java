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
		static JTextField usernameField = new JTextField(20);
		static JLabel passwordLabel = new JLabel("Password:  ");
		static JPasswordField passwordField = new JPasswordField(20);
		static JLabel firstNameLabel = new JLabel("First Name: ");
		static JTextField firstNameField = new JTextField(20);
		static JLabel lastNameLabel = new JLabel("Last Name: ");
		static JTextField lastNameField = new JTextField(20);
		static JLabel mailingAddressLabel = new JLabel("Mailing Address: ");
		static JLabel streetLabel = new JLabel("Street: ");
		static JLabel cityLabel = new JLabel("Town/City: ");
		static JTextField streetField = new JTextField(50);
		static JTextField cityField = new JTextField(50);
		static JLabel stateLabel = new JLabel("State: ");
		static String[] states = new String[50];
		static JComboBox<String> stateComboBox;
		static JLabel zipCodeLabel = new JLabel("ZIP Code: ");
		static JTextField zipCodeField = new JTextField(10);
		static JButton createButton = new JButton("Create");
	
	public static void createAccountPanel() {
		
		createAccountPanel.setLayout(null);
		createAccountPanel.setBackground(Home.bisque);
		createAccountPanel.setBounds(50,80,380,480);
		
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
			createButton.setBounds(15, 400, 100, 20);
			createButton.setBackground(Color.WHITE);
			createButton.addActionListener(new myActionListener());
		
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
		createAccountPanel.add(createButton);	
		createAccountPanel.setVisible(false);
	}
	
	public static void createAccountMethod() {
		CustomerAccount.customersPanel.setVisible(false);
		createAccountPanel.setVisible(true);
	}
	
	private static class myActionListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event){
			
			String username = usernameField.getText();
			if(checkUsername(username)==true) {
				JOptionPane.showMessageDialog(null,"Username is already in use.");
				usernameField.setText("");
				passwordField.setText("");
				usernameField.requestFocus();
			}
			else {
				char[] password = passwordField.getPassword(); 				//to do: add methods to ensure password contains uppercase, lowercase, numbers etc...
				String firstName = firstNameField.getText();
				String lastName = lastNameField.getText();
				String street = streetField.getText();
				String city = cityField.getText();
				String stateCode = stateComboBox.getItemAt(stateComboBox.getSelectedIndex());
				String zipCode = zipCodeField.getText();
				
				insertData(username,password,firstName,lastName,street,city,stateCode,zipCode);
				createAccountPanel.setVisible(false);
				CustomerAccount.customersPanel.setVisible(true);
				//Home.frame.repaint();
				
			}		
		
		}
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
	private static void insertData(String username,char[] password,String firstName,String lastName,String street,String city,String stateCode,String zipCode) {
		String sql = "INSERT INTO customerAccounts(username,password,firstName,lastName,street,city,stateCode,zipCode)VALUES(?,?,?,?,?,?,?,?)";
		
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
			
			pstmt.executeUpdate();
		}
		catch(SQLException e) {
		System.out.println(e.getMessage());
		}
	}
	
}


