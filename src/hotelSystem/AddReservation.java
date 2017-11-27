package hotelSystem;

import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

public class AddReservation {
	
	static int AccountId;
	static JPanel addReservationPanel = new JPanel();
		
	
		static String name = "Customer: ";
		static JLabel customerNameLabel = new JLabel(name);
		static JXDatePicker checkInDatePicker = new JXDatePicker();
		static JLabel checkInDateLabel = new JLabel("Check In: ");
		static JXDatePicker checkOutDatePicker = new JXDatePicker();
		static JLabel checkOutDateLabel = new JLabel("Check Out: ");		
		static JLabel boardBasisLabel = new JLabel("Board Basis: ");
		static String[] board = {"Bed & Breakfast", "Half Board","Full Board"};
		static JComboBox<String> boardBasisComboBox = new JComboBox<String>(board);
		static JLabel roomTypeLabel = new JLabel("Room Type ");
		static String[] rooms = {"Single", "Double","Twin Double","Queen","Suite"};
		static JComboBox<String> roomTypeComboBox = new JComboBox<String>(rooms);
		
		static JLabel adultsLabel = new JLabel("Adults: ");
		static ImageIcon minusIcon = new ImageIcon("resource/minus.png"); 
		static JButton minusButton1 = new JButton(minusIcon);
		static JTextField adultsField = new JTextField("1");
		static ImageIcon plusIcon = new ImageIcon("resource/plus.png"); 
		static JButton plusButton1 = new JButton(plusIcon);
	
		
	public static void addReservationPanel(){
		
		addReservationPanel.setLayout(null);
		addReservationPanel.setBackground(Home.myColor);
		addReservationPanel.setBounds(170,100,480,480);
		addReservationPanel.setVisible(false);
			
			customerNameLabel.setBounds(10,10,400,20);
			customerNameLabel.setFont(customerNameLabel.getFont().deriveFont(25f));
			checkInDateLabel.setBounds(20,40,120,25);
			checkInDateLabel.setFont(checkInDateLabel.getFont().deriveFont(20f));
			checkInDatePicker.setBounds(20,70,200,40);
			checkInDatePicker.setDate(Calendar.getInstance().getTime());
			checkInDatePicker.setFont(checkInDatePicker.getFont().deriveFont(20f));
			checkInDatePicker.requestFocus();
			checkOutDateLabel.setBounds(260,40,120,25);
			checkOutDateLabel.setFont(checkOutDateLabel.getFont().deriveFont(20f));
			checkOutDatePicker.setBounds(260,70,200,40);
			checkOutDatePicker.setDate(Calendar.getInstance().getTime());
			checkOutDatePicker.setFont(checkOutDatePicker.getFont().deriveFont(20f));
			checkOutDatePicker.requestFocus();
			boardBasisLabel.setBounds(40,130,140,25);
			boardBasisLabel.setFont(boardBasisLabel.getFont().deriveFont(20f));
			boardBasisComboBox.setBounds(40,160,140,25);
			roomTypeLabel.setBounds(260,130,140,25);
			roomTypeLabel.setFont(boardBasisLabel.getFont().deriveFont(20f));
			roomTypeComboBox.setBounds(260,160,140,25);
			
			adultsLabel.setBounds(20,220,80,25);
			adultsLabel.setFont(adultsLabel.getFont().deriveFont(20f));
			minusButton1.setBounds(20,250,25,25);
			adultsField.setBounds(45,250,25,25);
			adultsField.setHorizontalAlignment(JTextField.CENTER);
			plusButton1.setBounds(70,250,25,25);
			
			
			
		addReservationPanel.add(customerNameLabel);	
		addReservationPanel.add(checkInDatePicker);
		addReservationPanel.add(checkInDateLabel);
		addReservationPanel.add(checkOutDatePicker);
		addReservationPanel.add(checkOutDateLabel);
		addReservationPanel.add(boardBasisLabel);
		addReservationPanel.add(boardBasisComboBox);
		addReservationPanel.add(roomTypeLabel);
		addReservationPanel.add(roomTypeComboBox);
		addReservationPanel.add(adultsLabel);
		addReservationPanel.add(minusButton1);
		addReservationPanel.add(adultsField);
		addReservationPanel.add(plusButton1);
		
	}

}
