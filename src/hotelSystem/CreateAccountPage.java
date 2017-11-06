package hotelSystem;

import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

	//This page will be used to add new user accounts to the database
	public class CreateAccountPage extends SuperPage {
		
		JPanel panel = new JPanel();
		JLabel label1 = new JLabel("Welcome to B.L.O.P - Hotel Management System.");
		JLabel label2 = new JLabel("Username: ");
		JTextField username = new JTextField(20);
		JLabel label3 = new JLabel("Password:  ");
		JPasswordField password = new JPasswordField(15);
		JLabel label4 = new JLabel("First Name: ");
		JTextField firstName= new JTextField(20);
		JLabel label5 = new JLabel("Last Name: ");
		JTextField lastName = new JTextField(20);
		JLabel label6 = new JLabel("Address: ");
		JTextField address = new JTextField(20);
		JLabel label7= new JLabel("Phone: ");
		JTextField phone1 = new JTextField(3);
		JTextField phone2 = new JTextField(3);
		JTextField phone3 = new JTextField(4);
		JButton button = new JButton("Ok");
		
		
		
		//Constructor
		/**Creates an object of the customer with their information
		*/
		public CreateAccountPage(){
			super("Create User");			
			
			panel.setLayout(null);
			label1.setBounds(5,5,300,20);
			label2.setBounds(10,40,150,20);
			username.setBounds(100,40,150,20);
			label3.setBounds(300,40,150,20);
			password.setBounds(390,40,150,20);
			label4.setBounds(10,70,150,20);
			firstName.setBounds(100,70,150,20);
			label5.setBounds(300,70,150,20);
			lastName.setBounds(390,70,150,20);
			button.setBounds(250, 250, 100, 20);
			
			panel.add(label1);
			panel.add(label2);
			panel.add(username);
			panel.add(label3);
			panel.add(password);
			panel.add(label4);
			panel.add(firstName);
			panel.add(label5);
			panel.add(lastName);
			panel.add(button);
			
			getContentPane().add(panel);
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			username.requestFocus();
			button.addActionListener(new myActionListener());
		
		}
		
		private class myActionListener implements ActionListener {
			
			public void actionPerformed(ActionEvent event){
				String user = username.getText();
				String pass = password.getText(); //need to convert to char[]
				String first = firstName.getText();
				String last = lastName.getText();
				if(checkUsername(user)==true) {
					JOptionPane.showMessageDialog(null,"Username is already in use.");
					username.setText("");
					password.setText("");
					username.requestFocus();
				}
				insertData(user,pass,first,last);
				dispose();
			
			}
		}
				
		//queries database and returns true if user name is already in use.
		private Boolean checkUsername(String username) {
			String sql = "SELECT username FROM userAccounts";
			try(Connection conn = this.connect("BLOP.db");
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
		private void insertData(String user,String pass,String first,String last) {
			String sql = "INSERT INTO userAccounts(username,password,firstName,lastName)VALUES(?,?,?,?)";
			
			try(Connection conn = this.connect("BLOP.db");
				PreparedStatement pstmt = conn.prepareStatement(sql)){
				
				pstmt.setString(1, user);
				pstmt.setString(2, pass);
				pstmt.setString(3, first);
				pstmt.setString(4, last);
				pstmt.executeUpdate();
			}
			catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		
		
		
		
		
}
