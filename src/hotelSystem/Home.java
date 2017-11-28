package hotelSystem;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
	
	static int AccountId;    //this is important: keeps track of who current user is
	
	static ImageIcon myIcon = new ImageIcon("resource/icon.png"); 			//program icon
	static ImageIcon myBackground = new ImageIcon("resource/hotel.jpg");	//program background
	static Color myColor= new Color(204,225,255);							//panel background color
	static Color fontColor= new Color(0,0,0);								//font color
	static JFrame frame = new JFrame("Hotel Management System");			//title bar text
	
		//***************************************************************************
		// Attributes used by activePanel
		static JPanel activePanel = new JPanel();
			
			static String name;
			static JLabel nameLabel = new JLabel();
			static JButton logOutButton = new JButton("Log out");
			static ImageIcon settingsIcon = new ImageIcon("resource/settings.png");
			static JButton settingsButton = new JButton(settingsIcon);
		
		
		
		//***************************************************************************		
				
				
	//parameter indicates whether user is currently logged in
	public static void run(int id) {
		
		AccountId = id;
		
		frame.setLayout(null);
		frame.setIconImage(myIcon.getImage()); 					//sets program icon
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);			//sets program window size
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	//sets close action
		frame.addWindowListener(new WindowAdapter() {				//logs user out on close
			  	public void windowClosing(WindowEvent we) {
			  		Login.logOutMethod();
			  		System.exit(0);
			  	}
		});
		frame.setContentPane(new JLabel(myBackground));			//sets program background
			
			//LOGIN SECTION:.........................................
			Login.loginPanel();
			frame.getContentPane().add(Login.loginPanel);
			
			//MENU SECTION:..........................................
			Menu.menuPanel();
			frame.getContentPane().add(Menu.menuPanel);
			
			//ACTIVE-PANEL SECTION.................................
			activePanel();
			frame.getContentPane().add(activePanel);
			
			//CUSTOMER ACCOUNT SECTION.................................
			CustomerAccount.customersPanel();
			frame.getContentPane().add(CustomerAccount.customersPanel);
			
			//CREATE ACCOUNT SECTION
			CreateAccount.createAccountPanel();
			frame.getContentPane().add(CreateAccount.createAccountPanel);
			
			//RESERVATIONS SECTION
			Reservations.reservationsPanel();
			frame.getContentPane().add(Reservations.reservationsPanel);
			AddReservation.addReservationPanel();
			frame.getContentPane().add(AddReservation.addReservationPanel);
			
			//CHECK-IN SECTION
			CheckIn.checkInPanel();
			frame.getContentPane().add(CheckIn.checkInPanel);
			
			//CREATE RESTAURANT SECTION   <<<
			Restaurant.restaurantPanel();
			frame.getContentPane().add(Restaurant.restaurantPanel);
			
			//CREATE ROOM SERVICE SECTION <<<
			Restaurant.roomServicePanel();
			frame.getContentPane().add(Restaurant.roomService);

				
		//if no user is logged in: (ON PROGRAM START)..............
		if(AccountId == -1) {
			Login.loginPanel.setVisible(true);	
		}
		else if(AccountId == 0) {
			JOptionPane.showMessageDialog(null,"SQL Error");
		}
		//if user is logged in
		else{
			activePanel.setVisible(true);
			Menu.menuPanel.setVisible(true);
		}
		//.....................................................
			
		frame.pack();				//pack frame and set visible
		frame.setVisible(true);
	}

	
	//describes what happens when button is clicked
	public static class myActionListener implements ActionListener {
			
		public void actionPerformed(ActionEvent event){
			if(event.getSource() == logOutButton) {		
				Login.logOutMethod();
			}		
		}
	}
	
	//active panel to be used when a user is logged in
	public static void activePanel() {
		activePanel.setLayout(null);
		activePanel.setBackground(myColor);
		activePanel.setBounds(0,0,1920,40);
			
			
			nameLabel.setBounds(10,5,360,25);
			nameLabel.setFont(nameLabel.getFont().deriveFont(20f));
			nameLabel.setForeground(Home.fontColor);
			logOutButton.setBackground(Color.white);
			logOutButton.setBounds(370,8,80,25);
			logOutButton.addActionListener(new myActionListener());
			settingsButton.setBackground(Home.myColor);
			settingsButton.setBounds(1875,0,40,40);
			settingsButton.setToolTipText("Settings");
			settingsButton.addActionListener(new myActionListener());
		
		activePanel.add(nameLabel);
		activePanel.add(logOutButton);
		activePanel.add(settingsButton);
		activePanel.setVisible(false);	
	}
	
}
