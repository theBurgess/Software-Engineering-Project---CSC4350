package hotelSystem;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** This is the homePage for all staff members.
 *  What is displayed here is dependent on staffAccount type.
 *  @author Stephen
 *
 */
public class Home {
		
	static ImageIcon myIcon = new ImageIcon("resource/icon.png"); 			//program icon
	static ImageIcon myBackground = new ImageIcon("resource/hotel.jpg");	//program background
	static JFrame frame = new JFrame("Hotel Management System");			//title bar text
	
		//lllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll
		// Attributes used by loginPanel
		static JPanel loginPanel = new JPanel();
		static Color bisque= new Color(255,228,196);
	
			static JLabel usernameLabel = new JLabel("Username: ");
			static JTextField usernameField = new JTextField(20);
			static JLabel passwordLabel = new JLabel("Password:  ");
			static JPasswordField passwordField = new JPasswordField(20);
			static JButton loginButton = new JButton("Login");
		//lllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll
			
	
	//parameter indicates whether user is currently logged in
	public static void run(Boolean loggedIn) {
		
		frame.setLayout(null);
		frame.setIconImage(myIcon.getImage()); 					//sets program icon
		frame.setContentPane(new JLabel(myBackground));			//sets program background
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);			//sets program window size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//sets close action
		
		//if no user is logged in:
		if(loggedIn == false) {
			loginPanel();
		}
		
		
		frame.pack();				//pack frame and set visible
		frame.setVisible(true);
	}
	
	//login panel to be displayed when no user is logged in
	private static void loginPanel() {
		loginPanel.setLayout(null);
		loginPanel.setBackground(bisque);
		loginPanel.setBounds(0,0,1920,40);
			
			usernameLabel.setBounds(10,10,180,20);
			usernameLabel.setFont(usernameLabel.getFont().deriveFont(20f));
			usernameField.setBounds(120,10,120,25);
			passwordLabel.setBounds(250,10,180,20);
			passwordLabel.setFont(passwordLabel.getFont().deriveFont(20f));
			passwordField.setBounds(360,10,120,25);
			loginButton.setLayout(null);
			loginButton.setBounds(490,10,65,25);
			loginButton.addActionListener(new myActionListener());
			
			
		loginPanel.add(usernameLabel);
		loginPanel.add(usernameField);
		loginPanel.add(passwordLabel);
		loginPanel.add(passwordField);
		loginPanel.add(loginButton);
		loginPanel.setVisible(true);
		frame.getContentPane().add(loginPanel);
	}
	
	//describes what happens when button is clicked
	private static class myActionListener implements ActionListener {
			
		public void actionPerformed(ActionEvent event){
						
			if(event.getSource() == loginButton){
				loginMethod();
			}
					
		}
	}
	
	private static void loginMethod() {
		
		String username = usernameField.getText();
		char[] password = passwordField.getPassword();
		int i = Login.run(username,password);
		if(i == -1){
			JOptionPane.showMessageDialog(null,"Username not found.");
			usernameField.setText("");
			passwordField.setText("");
			usernameField.requestFocus();
		}
		else if(i == 0) {
			JOptionPane.showMessageDialog(null,"Password is incorrect.");
			passwordField.setText("");
			passwordField.requestFocus();
		}
		else {
			loginPanel.setVisible(false);
		}
	}
	
	
	
	
}
