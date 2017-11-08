package hotelSystem;


import javax.swing.*;
import java.awt.event.*;

public class HomePage extends SuperPage {
	
	static JFrame frame = new JFrame("Login");
	static JPanel panel = new JPanel();
	static JLabel label1 = new JLabel("Welcome to B.L.O.P - Hotel Management System.");
	static JLabel label2 = new JLabel("Choose action:");
	static JButton button1 = new JButton("Reservations");
	static JButton button2 = new JButton("Create Account");
	static JButton button3 = new JButton("Services");
	
	
	
	public static void run() {
		
		frame.setSize(300,200);
		frame.setLocation(600,300);
		panel.setLayout(null);
		
		label1.setBounds(5,5,300,20);
		label2.setBounds(5,40,150,20);
		button1.setBounds(10, 70, 120, 20);
		button2.setBounds(155, 70, 125, 20);
		button3.setBounds(10, 100, 120, 20);
		
		panel.add(label1);
		panel.add(label2);
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		
		frame.getContentPane().add(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getRootPane().setDefaultButton(button2);
		button2.addActionListener(new myActionListener());
		
		
	}
	
	//describes what happens when button is clicked
		private static class myActionListener implements ActionListener {
			
			public void actionPerformed(ActionEvent event){
					
				CreateAccountPage.run();
					
			}
		}
	
	
	

}
