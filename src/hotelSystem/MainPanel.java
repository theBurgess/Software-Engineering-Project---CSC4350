package hotelSystem;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class MainPanel {

	static JPanel mainPanel = new JPanel();									//center of homePanel where function panels will appear
		
		static JPanel mainOne = new JPanel();
		static JPanel mainTwo = new JPanel();
		static JPanel mainThree = new JPanel();
	
	public static void mainPanel() {
		
		mainPanel.setBackground(Color.GREEN);
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.LINE_AXIS));
		
			mainOne.setBackground(Color.BLUE);
			//mainOne.setPreferredSize(Home.panelSize);
			mainTwo.setBackground(Home.fontColor);
			mainTwo.setLayout(new BoxLayout(mainTwo,BoxLayout.LINE_AXIS));
			mainTwo.setPreferredSize(Home.panelSize);
			mainThree.setBackground(Color.GREEN);
			//mainThree.setPreferredSize(new Dimension(580,930));
		
			
		mainPanel.add(Box.createRigidArea(new Dimension(0,5)));	
		mainPanel.add(mainOne);
		mainPanel.add(Box.createRigidArea(new Dimension(0,5)));	
		mainPanel.add(mainTwo);
		mainPanel.add(Box.createRigidArea(new Dimension(0,5)));	
		mainPanel.add(mainThree);
		mainPanel.add(Box.createRigidArea(new Dimension(0,5)));	
	}
}
