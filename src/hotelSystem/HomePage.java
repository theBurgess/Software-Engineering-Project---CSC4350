package hotelSystem;

import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

public class HomePage extends SuperPage {
	
	JPanel panel = new JPanel();
	JLabel label1 = new JLabel("Welcome to B.L.O.P - Hotel Management System.");
	JLabel label2 = new JLabel("Choose action:");
	JButton button1 = new JButton("Reservations");
	JButton button2 = new JButton("Create Account");
	JButton button3 = new JButton("Services");
	
	
	
	public HomePage(String name) {
		super("Home: "+name);
		
		setSize(300,200);
		setLocation(600,300);
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
		
		getContentPane().add(panel);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getRootPane().setDefaultButton(button2);
		button2.addActionListener(new myActionListener());
		
		
	}
	
	//describes what happens when button is clicked
		private class myActionListener implements ActionListener {
			
			public void actionPerformed(ActionEvent event){
					
				CreateAccountPage cap = new CreateAccountPage();
					
			}
		}
	
	
	

}
