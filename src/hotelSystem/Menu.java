package hotelSystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


public class Menu {

		static Color transparent = new Color(0,0,0,0);	
		static JPanel menuPanel = new JPanel();

		static TitledBorder border = new TitledBorder(BorderFactory.createEtchedBorder(Home.fontColor,new Color(210,180,140)),"Menu: ",TitledBorder.LEFT,TitledBorder.TOP,Home.Serif);
	
		
			static ImageIcon checkInIcon = new ImageIcon("resource/checkIn.png"); 
			static ImageIcon customersIcon = new ImageIcon("resource/customers.png"); 
			static ImageIcon reservationsIcon = new ImageIcon("resource/reservations.png"); 
			static ImageIcon housekeepingIcon = new ImageIcon("resource/housekeeping.png");
			static ImageIcon roomServiceIcon = new ImageIcon("resource/roomService.png");
			static ImageIcon restaurantIcon = new ImageIcon("resource/restaurant.png"); 
			
			static Dimension buttonSize = new Dimension(80,80);
			static JButton checkInButton = new JButton(checkInIcon);
			static JButton customersButton = new JButton(customersIcon);
			static JButton reservationsButton = new JButton(reservationsIcon);
			static JButton housekeepingButton = new JButton(housekeepingIcon);
			static JButton roomServiceButton = new JButton(roomServiceIcon);
			static JButton restaurantButton = new JButton(restaurantIcon);
			
			
	
			
		public static void menuPanel() {
			
			menuPanel.setLayout(new BoxLayout(menuPanel,BoxLayout.PAGE_AXIS));
			menuPanel.setBackground(Home.myColor);
			border.setTitleColor(Home.fontColor);
			menuPanel.setBorder(border);
			//menuPanel.setPreferredSize(new Dimension(80,0));
				
				checkInButton.setToolTipText("Check-in Manager");
				checkInButton.setPreferredSize(buttonSize);
				checkInButton.addActionListener(new myActionListener());
			
				customersButton.setToolTipText("Customer Manager");
				customersButton.addActionListener(new myActionListener());
				customersButton.setPreferredSize(buttonSize);
				
				reservationsButton.setToolTipText("Reservation Manager");
				reservationsButton.addActionListener(new myActionListener());
				reservationsButton.setPreferredSize(buttonSize);
				
				housekeepingButton.setToolTipText("Housekeeping");
				housekeepingButton.addActionListener(new myActionListener());
				housekeepingButton.setPreferredSize(buttonSize);

				roomServiceButton.setToolTipText("Room Service");
				roomServiceButton.addActionListener(new myActionListener());
				roomServiceButton.setPreferredSize(buttonSize);

				restaurantButton.setToolTipText("Restaurant Manager");
				restaurantButton.addActionListener(new myActionListener());
				restaurantButton.setPreferredSize(buttonSize);
				
			menuPanel.add(Box.createVerticalGlue());	
			menuPanel.add(checkInButton);
			menuPanel.add(Box.createVerticalGlue());
			menuPanel.add(housekeepingButton);
			menuPanel.add(Box.createVerticalGlue());
			menuPanel.add(reservationsButton);	
			menuPanel.add(Box.createVerticalGlue());
			menuPanel.add(customersButton);
			menuPanel.add(Box.createVerticalGlue());
			menuPanel.add(roomServiceButton);
			menuPanel.add(Box.createVerticalGlue());
			menuPanel.add(restaurantButton);
			menuPanel.add(Box.createVerticalGlue());
			menuPanel.setVisible(false);
			
			
		}
		
		private static class myActionListener implements ActionListener {
			
			public void actionPerformed(ActionEvent event){
				
				if(event.getSource() == checkInButton) {
					if(CheckIn.checkInPanel.isVisible()) {
						CheckIn.checkInPanel.setVisible(false);
					}
					else {
						HousekeepingView.housekeepingPanel.setVisible(false);
						CheckIn.checkInPanel.setVisible(true);
					}
					
				}
				else if(event.getSource() == customersButton) {
					if(CustomerAccount.customersPanel.isVisible()) {
						CustomerAccount.customersPanel.setVisible(false);
					}
					else {
						Reservations.reservationsPanel.setVisible(false);
						CustomerAccount.customersPanel.setVisible(true);
						CustomerAccount.searchCustomerField.requestFocus();
					}
					
				}
				else if(event.getSource() == reservationsButton) {
					if(Reservations.reservationsPanel.isVisible()) {
						Reservations.reservationsPanel.setVisible(false);
					}
					else {
						CustomerAccount.customersPanel.setVisible(false);
						Reservations.reservationsPanel.setVisible(true);
						AddReservation.addReservationPanel.setVisible(false);
						Reservations.searchCustomerField.requestFocus();
					}
				}
				else if(event.getSource() == housekeepingButton) {
					if(HousekeepingView.housekeepingPanel.isVisible()) {
						HousekeepingView.housekeepingPanel.setVisible(false);
					}
					else {
						CheckIn.checkInPanel.setVisible(false);
						HousekeepingView.housekeepingPanel.setVisible(true);
					}
				}
				else if(event.getSource() == roomServiceButton) {
					if(Restaurant.roomService.isVisible()) {
						Restaurant.roomService.setVisible(false);
					}
					else {
						Restaurant.restaurantPanel.setVisible(false);
						Restaurant.roomService.setVisible(true);
					}
				}
				else if(event.getSource() == restaurantButton) {
					if(Restaurant.restaurantPanel.isVisible()) {
						Restaurant.restaurantPanel.setVisible(false);
					}
					else {
						Restaurant.roomService.setVisible(false);
						Restaurant.restaurantPanel.setVisible(true);
					}
				}			
			}
		}
}
