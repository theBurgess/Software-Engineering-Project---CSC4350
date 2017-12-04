package hotelSystem;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/** This is the homePage for all staff members.
 * 	This will contain gui elements.
 *  What is displayed here is dependent on staffAccount type.
 */
public class Home {
	
	static int AccountId;    //this is important: keeps track of who current user is
	
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	
	
	static Font Serif = new Font("Serif",3,15);
	static Color fontColor = new Color(139,69,19);
	
	static String pic1 = new String();
	
	static ImageIcon Icon = new ImageIcon("resource/icon.png"); 			//program icon
	static ImageIcon Background;
	static Color myColor= new Color(255,228,196);							//panel background color;								
	static JFrame frame = new JFrame("Hotel Management System");			//title bar text
	static JLabel label = new JLabel();
	static JPanel introPanel = new JPanel(); 								//when user is logged out
	static JPanel backPanel = new JPanel();									//""
	static JPanel homePanel = new JPanel();									//when user is logged in
	static JPanel mainPanel = new JPanel();									//center of homePanel where function panels will appear
	
	static JScrollPane scroll = new JScrollPane(introPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
		//***************************************************************************
		// Attributes used by activePanel		
		static JPanel activePanel = new JPanel();							//top bar of home panel
			
			static JPanel titlePanel = new JPanel();          				//what is this??
			static String name;
			static JLabel nameLabel = new JLabel();							//displays current user
			static JButton logOutButton = new JButton("Log out");
			static ImageIcon settingsIcon = new ImageIcon("resource/settings.png");
			static JButton settingsButton = new JButton(settingsIcon);		//for administrator settings
			static JPopupMenu popup = new JPopupMenu();
			
		
		
		//***************************************************************************		
	public static void createPopup() {
		popup.add(new JMenuItem(new AbstractAction("Create User Accounts: ") {
            public void actionPerformed(ActionEvent e) { 
            if(!StaffAccount.getInfo("admin", AccountId).equals("1")) {
            	JOptionPane.showMessageDialog(null,"Requires Administrator Privileges");
            }
            else {
               StaffAccount.createAccountPanel();
               StaffAccount.createAccountPanel.setVisible(true);
               JOptionPane.showOptionDialog(null,StaffAccount.createAccountPanel, "Create Account ",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);

            } 
        }
        }));
        popup.add(new JMenuItem(new AbstractAction("Option 2") {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Option 2 selected");
            }
        }));
        
        settingsButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        });
        
	}
				
	//parameter indicates whether user is currently logged in
	public static void run(int id) {
		if(screenSize.getWidth() < 1500) {
			pic1 = "resource/hotel1.jpg";
		}
		else {
			pic1 = "resource/hotel2.jpg";
		}
		Background = new ImageIcon(pic1);
		label.setIcon(Background);
		EventQueue.invokeLater(new Runnable() { public void run() {
			
			AccountId = id;
			
			frame.setLayout(new BorderLayout());
			frame.setIconImage(Icon.getImage()); 						//sets program icon	
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);				
			frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	//sets close action
			frame.addWindowListener(new WindowAdapter() {				//logs user out on close
				  	public void windowClosing(WindowEvent we) {
				  		Login.logOutMethod();
				  		System.exit(0);
				  	}
			});
			
			frame.setContentPane(scroll);			//sets program background		
			
					introPanel.setLayout(new BorderLayout());
					introPanel.add(backPanel,BorderLayout.CENTER);
					backPanel.add(label);
				
					//LOGIN SECTION:.........................................
					Login.loginPanel();
					introPanel.add(Login.loginPanel, BorderLayout.PAGE_START);
					
					//ACTIVE-PANEL SECTION.................................
					homePanel.setLayout(new BorderLayout());
					activePanel();
					homePanel.add(activePanel,BorderLayout.PAGE_START);
					activePanel.setBackground(myColor);
					
					//MENU SECTION:..........................................
					Menu.menuPanel();
					homePanel.add(Menu.menuPanel, BorderLayout.LINE_START);
					
					//CHECK-IN SECTION
					CheckIn.checkInPanel();
					//MainPanel.mainOne.add(CheckIn.checkInPanel);
					
					//RESERVATIONS SECTION
					Reservations.reservationsPanel();
	
						AddReservation.addReservationPanel();
						Reservations.reservationsPanel.add(AddReservation.addReservationPanel);	
					
					//CREATE ROOM SERVICE SECTION <<<
					Restaurant.roomServicePanel();
	
					//Housekeeping SECTION
					HousekeepingView.housekeepingPanel();
	
					//CUSTOMER ACCOUNT SECTION.................................
					CustomerAccount.customersPanel();
					
						CreateAccount.createAccountPanel();
						CustomerAccount.customersPanel.add(CreateAccount.createAccountPanel);
									
					//CREATE RESTAURANT SECTION   <<<
					Restaurant.restaurantPanel();
					
					//MAIN-PANEL SECTION
					MainPanel.mainPanel();
					homePanel.add(MainPanel.mainPanel, BorderLayout.CENTER);
	
				frame.pack();	
				frame.setVisible(true);
				
			//if no user is logged in: (ON PROGRAM START)..............
			if(AccountId == -1) {	
				Login.passwordField.requestFocus();
			}
			else if(AccountId == 0) {
				JOptionPane.showMessageDialog(null,"SQL Error");
			}
			//if user is logged in
			else{
				activePanel.setVisible(true);
				Menu.menuPanel.setVisible(true);
				scroll.setViewportView(homePanel);
			}
			//.....................................................
				

		}});
	}
	
	
	//describes what happens when button is clicked
	public static class myActionListener implements ActionListener {
			
		public void actionPerformed(ActionEvent event){
			if(event.getSource() == logOutButton) {		
				Login.logOutMethod();
			}		
		}
	}
	
	
	static JPanel userPanel = new JPanel();
	static JPanel settingsPanel = new JPanel();
	static JPanel logOutPanel = new JPanel();
	//active panel to be used when a user is logged in
	public static void activePanel() {
		
		TitledBorder aBorder = new TitledBorder(BorderFactory.createEtchedBorder(Home.fontColor,new Color(210,180,140)),"",TitledBorder.LEFT,TitledBorder.TOP,Home.Serif);
		aBorder.setTitleColor(Home.fontColor);
		activePanel.setLayout(new GridLayout(1,3,0,5));
		activePanel.setBorder(aBorder);
		activePanel.setBackground(myColor);
		
			TitledBorder nBorder = new TitledBorder(BorderFactory.createEtchedBorder(Home.fontColor,new Color(210,180,140)),"Current User: ",TitledBorder.LEFT,TitledBorder.TOP,Home.Serif);
			nBorder.setTitleColor(Home.fontColor);
			
			userPanel.setBorder(nBorder);
			userPanel.setBackground(myColor);
			
				nameLabel.setForeground(Home.fontColor);
				nameLabel.setFont(Serif.deriveFont(20f));
				
			userPanel.add(nameLabel);
			
			logOutPanel.setBackground(myColor);
			logOutPanel.setLayout(new FlowLayout(FlowLayout.LEFT,5,20));
				logOutButton.setBackground(Color.white);
				logOutButton.setFont(Home.Serif.deriveFont(20f));
				logOutButton.setForeground(fontColor);
				logOutButton.addActionListener(new myActionListener());
			logOutPanel.add(logOutButton);
			
			//settingsButton.setBackground(Home.myColor);
			settingsPanel.setBackground(myColor);
			settingsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
				settingsButton.setToolTipText("Settings");
				settingsButton.addActionListener(new myActionListener());
				createPopup();
				
			settingsPanel.add(settingsButton);
		
		activePanel.add(userPanel);
		activePanel.add(logOutPanel);
		activePanel.add(settingsPanel);
		
		activePanel.setVisible(false);	
	}
	
}