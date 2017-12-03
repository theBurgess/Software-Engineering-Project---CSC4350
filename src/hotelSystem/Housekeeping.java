package hotelSystem;

//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.Box;
import java.awt.ScrollPane;

public class Housekeeping {

	static JFrame frame;
	
	static JPanel housekeepingPanel = new JPanel();
	static JTextField txtHousekeeping;
	static JTextField txtRoomNo;
	static JTextField txtRoomType;
	static JTextField txtStatus;
	static JTextField txtAssign;

	/**
	 * Launch the application.
	 */
	
	public static void housekeepingPanel() {
		
		housekeepingPanel.setBackground(Home.myColor);
		housekeepingPanel.setBounds(170,100,480,480);
		housekeepingPanel.setLayout(null);
		
			txtHousekeeping = new JTextField();
			txtHousekeeping.setBackground(Home.myColor);
			txtHousekeeping.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
			txtHousekeeping.setEditable(false);
			txtHousekeeping.setForeground(Color.BLACK);
			txtHousekeeping.setText("Housekeeping: ");
			txtHousekeeping.setFont(txtHousekeeping.getFont().deriveFont(20f));
			txtHousekeeping.setBounds(5, 5, 160, 30);
			housekeepingPanel.add(txtHousekeeping);
			txtHousekeeping.setColumns(10);
			
			txtRoomNo = new JTextField();
			txtRoomNo.setText("Room No: ");
			txtRoomNo.setForeground(Color.BLACK);
			txtRoomNo.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			txtRoomNo.setEditable(false);
			txtRoomNo.setColumns(10);
			txtRoomNo.setBackground(Color.WHITE);
			txtRoomNo.setBounds(5, 38, 111, 26);
			housekeepingPanel.add(txtRoomNo);
			
			txtRoomType = new JTextField();
			txtRoomType.setText("Room Type: ");
			txtRoomType.setForeground(Color.BLACK);
			txtRoomType.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			txtRoomType.setEditable(false);
			txtRoomType.setColumns(10);
			txtRoomType.setBackground(Color.WHITE);
			txtRoomType.setBounds(113, 38, 125, 26);
			housekeepingPanel.add(txtRoomType);
			
			txtStatus = new JTextField();
			txtStatus.setText("Status: ");
			txtStatus.setForeground(Color.BLACK);
			txtStatus.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			txtStatus.setEditable(false);
			txtStatus.setColumns(10);
			txtStatus.setBackground(Color.WHITE);
			txtStatus.setBounds(235, 38, 111, 26);
			housekeepingPanel.add(txtStatus);
			
			txtAssign = new JTextField();
			txtAssign.setText("Assign: ");
			txtAssign.setForeground(Color.BLACK);
			txtAssign.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			txtAssign.setEditable(false);
			txtAssign.setColumns(10);
			txtAssign.setBackground(Color.WHITE);
			txtAssign.setBounds(344, 38, 111, 26);
			housekeepingPanel.add(txtAssign);
			
			Box horizontalBox = Box.createHorizontalBox();
			horizontalBox.setBounds(0, 61, 450, 217);
			housekeepingPanel.add(horizontalBox);
			
			ScrollPane scrollPane = new ScrollPane();
			horizontalBox.add(scrollPane);
			
		housekeepingPanel.setVisible(false);
	}
}