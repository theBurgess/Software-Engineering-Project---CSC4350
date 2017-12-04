package hotelSystem;


import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MainPanel {

	static JPanel mainPanel = new JPanel();									//center of homePanel where function panels will appear
	static Dimension panelSize = new Dimension(560,1);
	
		static JPanel mainOne = new JPanel();
			static JScrollPane scrollOne = new JScrollPane(mainOne,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		static JPanel mainTwo = new JPanel();
			static JScrollPane scrollTwo = new JScrollPane(mainTwo,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		static JPanel mainThree = new JPanel();
			static JScrollPane scrollThree = new JScrollPane(mainThree,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	public static void mainPanel() {
		
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.LINE_AXIS));
		mainPanel.setBackground(Home.fontColor);
		
		
		mainPanel.add(Box.createHorizontalGlue());
		mainPanel.add(scrollOne);
			
			mainOne.setLayout(new BoxLayout(mainOne,BoxLayout.LINE_AXIS));
			mainOne.setPreferredSize(panelSize);
			mainOne.add(CheckIn.checkInPanel);
			mainOne.add(HousekeepingView.housekeepingPanel);
		
		mainPanel.add(Box.createHorizontalGlue());
		mainPanel.add(scrollTwo);

			mainTwo.setLayout(new BoxLayout(mainTwo,BoxLayout.LINE_AXIS));
			mainTwo.setPreferredSize(panelSize);
			mainTwo.add(Reservations.reservationsPanel);
			mainTwo.add(CustomerAccount.customersPanel);
		
		mainPanel.add(Box.createHorizontalGlue());
		mainPanel.add(scrollThree);

			mainThree.setLayout(new BoxLayout(mainThree,BoxLayout.LINE_AXIS));
			mainThree.setPreferredSize(panelSize);
			mainThree.add(Restaurant.roomService);
			mainThree.add(Restaurant.restaurantPanel);

		mainPanel.add(Box.createHorizontalGlue());	

		
			
		
		
		

	}
}
