package hotelSystem;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateAccountSuperPage  {
	

	static JPanel panel = new JPanel();
		static JLabel label1 = new JLabel("Username: ");
		static JTextField username = new JTextField(20);
		static JLabel label2 = new JLabel("Password:  ");
		static JPasswordField password = new JPasswordField(20);
		static JLabel label3 = new JLabel("First Name: ");
		static JTextField firstName= new JTextField(20);
		static JLabel label4 = new JLabel("Last Name: ");
		static JTextField lastName = new JTextField(20);
		static JLabel label5 = new JLabel("Mailing Address: ");
		static JLabel label6 = new JLabel("Line 1: ");
		static JLabel label7 = new JLabel("Line 2: ");
		static JTextField address1 = new JTextField(50);
		static JTextField address2 = new JTextField(50);
		static JLabel label8 = new JLabel("State: ");
		static String[] states = new String[50];
		static JComboBox<String> comboBox1;
		static JLabel label9 = new JLabel("ZIP Code: ");
		static JTextField zipCode = new JTextField(10);
		static JButton button = new JButton("Ok");
		
	
	public static void run(int id){		//this is where specific page layout goes
		
		
		panel.setLayout(null);
		
		label1.setBounds(10,40,150,20);
		username.setBounds(100,40,150,20);
		label2.setBounds(300,40,150,20);
		password.setBounds(390,40,150,20);
		label3.setBounds(10,70,150,20);
		firstName.setBounds(100,70,150,20);
		label4.setBounds(300,70,150,20);
		lastName.setBounds(390,70,150,20);
		label5.setBounds(10,100,100,20);
		label6.setBounds(15,130,50,20);
		address1.setBounds(70,130,300,20);
		label7.setBounds(15,160,50,20);
		address2.setBounds(70,160,300,20);
		
		label8.setBounds(15,190,50,20);
		getStates(states);
		comboBox1 = new JComboBox<String>(states);
		comboBox1.setSelectedIndex(-1);
		comboBox1.setMaximumRowCount(4);
		comboBox1.setBounds(70,190,50,20);
		
		label9.setBounds(160,190,80,20);
		zipCode.setBounds(240,190,80,20);
		button.setBounds(250, 250, 100, 20);
		
		panel.add(label1);
		panel.add(username);
		panel.add(label2);
		panel.add(password);
		panel.add(label3);
		panel.add(firstName);
		panel.add(label4);
		panel.add(lastName);
		panel.add(label5);
		panel.add(label6);
		panel.add(address1);
		panel.add(label7);
		panel.add(address2);
		panel.add(label8);
		panel.add(button);
		panel.add(comboBox1);
		panel.add(label9);
		panel.add(zipCode);
		button.addActionListener(new myActionListener());		
		username.requestFocus();
		
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
		
	private static class myActionListener implements ActionListener {
			
		public void actionPerformed(ActionEvent event){
			String user = username.getText();
			char[] pass = password.getPassword(); 				//to do: add methods to ensure password contains uppercase, lowercase, numbers etc...
			String first = firstName.getText();
			String last = lastName.getText();
			String maddress1 = address1.getText();
			String maddress2 = address2.getText();
			String state = comboBox1.getItemAt(comboBox1.getSelectedIndex());
			String zip = zipCode.getText();
			
			if(checkUsername(user)==true) {
				JOptionPane.showMessageDialog(null,"Username is already in use.");
				username.setText("");
				password.setText("");
				username.requestFocus();
			}
			
			insertData(user,pass,first,last,maddress1,maddress2,state,zip);
			
		
		}
	}
			
	//queries database and returns true if user name is already in use.
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
	private static void insertData(String user,char[] pass,String first,String last,String maddress1,String maddress2,String state,String zip) {
		String sql = "INSERT INTO customerAccounts(username,password,firstName,lastName,mailingAddressLine1,mailingAddressLine2,stateCode,zipCode)VALUES(?,?,?,?,?,?,?,?)";
		
		try(Connection conn = Database.connect("BLOP.db");
			PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setString(1, user);
			pstmt.setString(2, Arrays.toString(pass)); //to do: time permitting: encrypt passwords.
			pstmt.setString(3, first);
			pstmt.setString(4, last);
			pstmt.setString(5, maddress1);
			pstmt.setString(6, maddress2);
			pstmt.setString(7, state);
			pstmt.setString(8, zip);
			
			pstmt.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}	
		

}
