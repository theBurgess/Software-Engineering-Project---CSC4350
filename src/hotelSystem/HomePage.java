package hotelSystem;

import javax.swing.*;
import java.awt.event.*;



/**
 * 
 * this page should be the main hub for staff type users to access all functions of the program. 
 * so far, have added create user account, and log out function
 *
 */
public class HomePage extends SuperPage {
	
	
	static JFrame frame = new JFrame("Hotel Management System");
	static JPanel panel = new JPanel();
	static JLabel label1 = new JLabel("Choose Action: ");
	static JButton button1 = new JButton("Log Out");
	static JButton button2 = new JButton("Customer Accounts");
	static JButton button3 = new JButton("Services");
	
	public static void run(int userId) {
		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	
		panel.setLayout(null);
		
		label1.setBounds(10,40,150,20);
		button1.setBounds(10, 70, 120, 20);
		button1.addActionListener(new myActionListener(userId));
		button2.setBounds(155, 70, 150, 20);
		button2.addActionListener(new myActionListener(userId));
		button3.setBounds(10, 100, 120, 20);
		
		panel.add(label1);
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		
		frame.getContentPane().add(panel);
		frame.getRootPane().setDefaultButton(button2);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
	}
	
	//describes what happens when button is clicked
		private static class myActionListener implements ActionListener  {
			int id;
			public myActionListener(int i) {
				id = i;
			}
			public void actionPerformed(ActionEvent event){
				if(event.getSource() == button1) {
					frame.dispose();
					setLoggedIn(false, id);
					LoginPage.run();
				}
				else if(event.getSource() == button2) {
					frame.dispose();	
					CreateAccountSuperPage.run();	
				}		
			}
		}
		
	
	
	

}
