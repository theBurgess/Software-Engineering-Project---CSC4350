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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/** This is the homePage for all staff members.
 * 	This will contain gui elements.
 *  What is displayed here is dependent on staffAccount type.
 */
public class Home {
	
	static int userId;
	
	static ImageIcon myIcon = new ImageIcon("resource/icon.png"); 			//program icon
	static ImageIcon myBackground = new ImageIcon("resource/hotel.jpg");	//program background
	static Color bisque= new Color(255,228,196);							//panel background color
	static JFrame frame = new JFrame("Hotel Management System");			//title bar text
	
		//lllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll
		// Attributes used by loginPanel
		static JPanel loginPanel = new JPanel();
			
			static JLabel usernameLabel = new JLabel("Username: ");
			static JTextField usernameField = new JTextField(20);
			static JLabel passwordLabel = new JLabel("Password:  ");
			static JPasswordField passwordField = new JPasswordField(20);
			static JButton loginButton = new JButton("Login");
		//lllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll
		
			
		//aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
		// Attributes used by activePanel
		static JPanel activePanel = new JPanel();
			
			static String name;
			static JLabel nameLabel;
			static JButton logOutButton = new JButton("Log out");
		
		
		
		//aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
	
	//parameter indicates whether user is currently logged in
	public static void run(int i) {
		
		userId = i;
		
		frame.setLayout(null);
		frame.setIconImage(myIcon.getImage()); 					//sets program icon
		frame.setContentPane(new JLabel(myBackground));			//sets program background
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);			//sets program window size
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	//sets close action
		frame.addWindowListener(new WindowAdapter() {				//logs user out on close
			  	public void windowClosing(WindowEvent we) {
			  		Login.setLoggedIn(false,userId);
			  		System.exit(0);
			  	}
		}
		);
		
		
		//if no user is logged in:
		if(userId == -1) {
			loginPanel();
		}
		else if(userId == 0) {
			JOptionPane.showMessageDialog(null,"SQL Error");
		}
		//if user is logged in
		else{
			activePanel(userId);
			
		}
		
		
		frame.pack();				//pack frame and set visible
		frame.setVisible(true);
	}

	
	
	//login panel to be displayed when no user is logged in
	private static void loginPanel() {
		loginPanel.setLayout(null);
		loginPanel.setBackground(bisque);
		loginPanel.setBounds(0,0,1920,40);
			
			usernameLabel.setBounds(10,10,180,25);
			usernameLabel.setFont(usernameLabel.getFont().deriveFont(20f));
			usernameField.setBounds(120,10,120,25);
			passwordLabel.setBounds(250,10,180,25);
			passwordLabel.setFont(passwordLabel.getFont().deriveFont(20f));
			passwordField.setBounds(360,10,120,25);
			loginButton.setBackground(Color.white);
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
	
	//active panel to be used when a user is logged in
	private static void activePanel(int userId) {
		activePanel.setLayout(null);
		activePanel.setBackground(bisque);
		activePanel.setBounds(0,0,1920,40);
		
			name = StaffAccount.getName(userId);
			nameLabel = new JLabel("Logged in as: "+name+":");
			//nameLabel.setFont();
			nameLabel.setBounds(10,5,360,25);
			nameLabel.setFont(usernameLabel.getFont().deriveFont(20f));
			logOutButton.setBackground(Color.white);
			logOutButton.setBounds(360,8,80,25);
			logOutButton.addActionListener(new myActionListener());
		
		activePanel.add(nameLabel);
		activePanel.add(logOutButton);
		activePanel.setVisible(true);	
		frame.getContentPane().add(activePanel);
	}
	
	//describes what happens when button is clicked
	private static class myActionListener implements ActionListener {
			
		public void actionPerformed(ActionEvent event){
						
			if(event.getSource() == loginButton){
				loginMethod();
			}
			else if(event.getSource() == logOutButton) {
				logOutMethod();
			}
					
		}
	}
	
	private static void loginMethod() {
		
		String username = usernameField.getText();
		char[] password = passwordField.getPassword();
		userId = Login.run(username,password);
		if(userId == -1){
			JOptionPane.showMessageDialog(null,"Username not found.");
			usernameField.setText("");
			passwordField.setText("");
			usernameField.requestFocus();
		}
		else if(userId == 0) {
			JOptionPane.showMessageDialog(null,"Password is incorrect.");
			passwordField.setText("");
			passwordField.requestFocus();
		}
		else {
			loginPanel.setVisible(false);
			activePanel(userId);
		}
	}
	
	private static void logOutMethod() {
		Login.setLoggedIn(false,userId);
		activePanel.setVisible(false);
		loginPanel.setVisible(true);
	}
	
	
	
}
