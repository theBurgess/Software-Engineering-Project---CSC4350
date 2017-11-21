package hotelSystem;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Menu {

	
		static JPanel menuPanel = new JPanel();
		
			static JButton reservationsButton = new JButton();
			static JButton restaurantButton = new JButton();
			static JButton housekeepingButton = new JButton();
	
			
		public static void menuPanel() {
			
			menuPanel.setLayout(null);
			menuPanel.setBackground(Home.myColor);
			menuPanel.setBounds(1620,0,300,1080);
				
				reservationsButton.setBackground(Color.white);
				reservationsButton.setFont(reservationsButton.getFont().deriveFont(20f));
				reservationsButton.setBounds(25,80,250,40);
				reservationsButton.setText("Reservations");
				
				restaurantButton.setBackground(Color.white);
				restaurantButton.setFont(restaurantButton.getFont().deriveFont(20f));
				restaurantButton.setBounds(25,150,250,40);
				restaurantButton.setText("Restaurant");
				
				housekeepingButton.setBackground(Color.white);
				housekeepingButton.setFont(housekeepingButton.getFont().deriveFont(20f));
				housekeepingButton.setBounds(25,230,250,40);
				housekeepingButton.setText("Housekeeping");
				
			menuPanel.add(restaurantButton);
			menuPanel.add(reservationsButton);
			menuPanel.add(housekeepingButton);
			menuPanel.setVisible(false);
			
			
		}
		
		private static class myActionListener implements ActionListener {
			
			public void actionPerformed(ActionEvent event){
				
				if(event.getSource() == restaurantButton) {
					//Restaurant.restaurantPanel.setVisible(true);
				}
			
			}
		}
}
