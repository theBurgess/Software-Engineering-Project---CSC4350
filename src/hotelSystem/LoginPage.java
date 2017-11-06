package hotelSystem;

import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

public class LoginPage extends SuperPage{


	
	JPanel panel = new JPanel();
	JLabel label1 = new JLabel("Welcome to B.L.O.P - Hotel Management System.");
	JLabel label2 = new JLabel("Username: ");
	JTextField username = new JTextField(20);
	JLabel label3 = new JLabel("Password:  ");
	JPasswordField password = new JPasswordField(15);
	JButton button1 = new JButton("Login");
	
	
	public LoginPage() {
		super("Login:");
		
		setSize(300,250);
		setLocation(600,300);
		panel.setLayout(null);
		
		label1.setBounds(5,5,300,20);
		label2.setBounds(10,40,150,20);
		username.setBounds(100,40,150,20);
		label3.setBounds(10,80,150,20);
		password.setBounds(100,80,150,20);
		button1.setBounds(115, 120, 65, 20);
		
		
		panel.add(label1);
		panel.add(label2);
		panel.add(username);
		panel.add(label3);
		panel.add(password);
		panel.add(button1);
		
		
		getContentPane().add(panel);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		username.requestFocus();
		getRootPane().setDefaultButton(button1);
		button1.addActionListener(new myActionListener());
		
	}
	
	//describes what happens when button is clicked
	private class myActionListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event){
						
			String u = username.getText();
			String p = password.getText(); //need to convert to char[]
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
				String name = "name here.";
				HomePage HP = new HomePage(name);
				dispose();
			}		
		}
	}
	
	
	/**
	 * queries database for matching username and password
	 * returns userid
	 */
	public int isUser(String user, String pass) {
		String sql = "SELECT userid, username, password FROM userAccounts";
		try(Connection conn = this.connect("BLOP.db");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)){
			
			while(rs.next()) {
				if(rs.getString("username").equals(user)) {
					if(rs.getString("password").equals(pass)) {
						return rs.getInt(1);
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
