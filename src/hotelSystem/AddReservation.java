package hotelSystem;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Scanner;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;


public class AddReservation {
	
	static Boolean ready = false;
	static int AccountId;
	static SimpleDateFormat sdf =  new SimpleDateFormat("MM/dd/yyyy");
	
	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	static String checkIn;
	static String checkOut;
	static int nights = 0;
	
	static String roomType;
	static String boardBasis;
	static int adults = 0;
	static int children = 0;
	
	static int roomsBooked = 0;
	static int[] roomNums = new int[50];
	
	static JPanel addReservationPanel = new JPanel();
		
		static JPanel titlePanel = new JPanel();
			static JLabel titleLabel = new JLabel("New Reservation: ");
			
		
		static JPanel customerPanel = new JPanel();
			static String name = "Customer: ";
			static JLabel customerNameLabel = new JLabel(name);
		
		static JPanel datePanel = new JPanel();
		
			static JXDatePicker checkInDatePicker = new JXDatePicker();
			static JLabel checkInDateLabel = new JLabel("Check In: ");
			static JXDatePicker checkOutDatePicker = new JXDatePicker();
			static JLabel checkOutDateLabel = new JLabel("Check Out: ");
			
		static JPanel buttonPanel = new JPanel();
		
			static JButton addRoomButton = new JButton("Add Room");
			static JButton backButton1 = new JButton("Back");
			static JButton completeButton = new JButton("Complete");
		
		static JPanel roomPanel = new JPanel();
		static JPanel roomTypePanel =new JPanel();
			static JLabel boardBasisLabel = new JLabel("Board Basis: ");
			static String[] board = {"Bed & Breakfast", "Half Board","Full Board"};
			static JComboBox<String> boardBasisComboBox = new JComboBox<String>(board);
			static JLabel roomTypeLabel = new JLabel("Room Type: ");
			static String[] rooms = {"Single", "Double","Twin Double","Suite"};
			static JComboBox<String> roomTypeComboBox = new JComboBox<String>(rooms);
		
		static JPanel guestsPanel = new JPanel();	
			static ImageIcon minusIcon = new ImageIcon("resource/minus.png");
			static ImageIcon plusIcon = new ImageIcon("resource/plus.png"); 
			
			static JLabel adultsLabel = new JLabel("Adults: ");		
			static JButton minusButton1 = new JButton(minusIcon);
			static JTextField adultsField = new JTextField("1");		
			static JButton plusButton1 = new JButton(plusIcon);
			
			static JLabel childLabel = new JLabel("Children: ");		
			static JButton minusButton2 = new JButton(minusIcon);
			static JTextField childField = new JTextField("0");		
			static JButton plusButton2 = new JButton(plusIcon);
			
		static JPanel buttonPanel2 = new JPanel();
			static JButton confirmButton = new JButton("Confirm");
			static JButton backButton2 = new JButton("Back");
	
		
	public static void addReservationPanel(){
		Dimension labelSize = new Dimension(140,25);
			
		addReservationPanel.setLayout(new BoxLayout(addReservationPanel,BoxLayout.PAGE_AXIS));
		addReservationPanel.setBackground(Home.myColor);
		addReservationPanel.setBorder(CustomerAccount.border);
		
			titlePanel.setBackground(Home.myColor);
			titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			titlePanel.setBorder(CustomerAccount.border);
				titleLabel.setFont(Home.Serif.deriveFont(30f));
				titleLabel.setForeground(Home.fontColor);
			titlePanel.add(titleLabel);
			
			customerPanel.setBackground(Home.myColor);
			customerPanel.setLayout(new FlowLayout(5,20,FlowLayout.LEFT));
				customerNameLabel.setFont(Home.Serif.deriveFont(25f));
				customerNameLabel.setForeground(Home.fontColor);
				customerNameLabel.setPreferredSize(new Dimension(300,25));
			customerPanel.add(customerNameLabel);
			
			datePanel.setLayout(new FlowLayout(5,20,FlowLayout.LEFT));
			datePanel.setBackground(Home.myColor);
			
				checkInDateLabel.setPreferredSize(new Dimension(100,25));
				checkInDateLabel.setFont(Home.Serif.deriveFont(20f));
				checkInDateLabel.setForeground(Home.fontColor);
				checkInDatePicker.setFormats(sdf);
				checkInDatePicker.setDate(Calendar.getInstance().getTime());
				checkInDatePicker.setFont(Home.Serif.deriveFont(20f));
				checkInDatePicker.requestFocus();
				checkOutDateLabel.setPreferredSize(new Dimension(100,25));
				checkOutDateLabel.setFont(Home.Serif.deriveFont(20f));
				checkOutDateLabel.setForeground(Home.fontColor);
				checkOutDatePicker.setFormats(sdf);
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, 1);
				checkOutDatePicker.setDate(cal.getTime());
				checkOutDatePicker.setFont(Home.Serif.deriveFont(20f));
				checkOutDatePicker.requestFocus();
				
			datePanel.add(checkInDateLabel);
			datePanel.add(checkInDatePicker);
			datePanel.add(checkOutDateLabel);
			datePanel.add(checkOutDatePicker);
			
			buttonPanel.setBackground(Home.myColor);
			buttonPanel.setBorder(CustomerAccount.border);
			buttonPanel.setLayout(new FlowLayout(5,40,FlowLayout.LEADING));
			
				addRoomButton.setPreferredSize(labelSize);
				addRoomButton.setFont(Home.Serif.deriveFont(20f));
				addRoomButton.setForeground(Home.fontColor);
				addRoomButton.setBackground(Color.WHITE);
				addRoomButton.addActionListener(new myActionListener2());
				backButton1.setPreferredSize(labelSize);
				backButton1.setFont(Home.Serif.deriveFont(20f));
				backButton1.setForeground(Home.fontColor);
				backButton1.setBackground(Color.WHITE);
				backButton1.addActionListener(new myActionListener2());
				completeButton.setPreferredSize(labelSize);
				completeButton.setFont(Home.Serif.deriveFont(20f));
				completeButton.setForeground(Home.fontColor);
				completeButton.setBackground(Color.WHITE);
				completeButton.addActionListener(new myActionListener2());
				
			buttonPanel.add(addRoomButton);
			buttonPanel.add(backButton1);
			buttonPanel.add(completeButton);
			
			roomPanel.setLayout(new GridLayout(8,2,0,5));
			roomPanel.setBackground(Home.myColor);
			roomPanel.setVisible(false);
			
				roomTypePanel.setLayout(new FlowLayout(5,20,FlowLayout.LEADING));
				roomTypePanel.setBackground(Home.myColor);
					
					boardBasisLabel.setPreferredSize(new Dimension(120,25));
					boardBasisLabel.setFont(Home.Serif.deriveFont(20f));
					boardBasisLabel.setForeground(Home.fontColor);
					roomTypeLabel.setPreferredSize(new Dimension(120,25));
					roomTypeLabel.setFont(Home.Serif.deriveFont(20f));
					roomTypeLabel.setForeground(Home.fontColor);
					
					
				roomTypePanel.add(roomTypeLabel);
				roomTypePanel.add(roomTypeComboBox);	
				roomTypePanel.add(boardBasisLabel);
				roomTypePanel.add(boardBasisComboBox);

				guestsPanel.setLayout(new FlowLayout(5,20,FlowLayout.LEADING));
				guestsPanel.setBackground(Home.myColor);

					adultsLabel.setFont(Home.Serif.deriveFont(20f));
					adultsLabel.setForeground(Home.fontColor);
					minusButton1.addActionListener(new myActionListener1());
					minusButton1.setPreferredSize(new Dimension(25,25));
					adultsField.setHorizontalAlignment(JTextField.CENTER);
					adultsField.setEditable(false);
					adultsField.setPreferredSize(new Dimension(25,25));
					plusButton1.addActionListener(new myActionListener1());
					plusButton1.setPreferredSize(new Dimension(25,25));
					
					childLabel.setFont(Home.Serif.deriveFont(20f));
					childLabel.setForeground(Home.fontColor);
					minusButton2.addActionListener(new myActionListener1());
					minusButton2.setPreferredSize(new Dimension(25,25));
					childField.setHorizontalAlignment(JTextField.CENTER);
					childField.setEditable(false);
					childField.setPreferredSize(new Dimension(25,25));
					plusButton2.addActionListener(new myActionListener1());
					plusButton2.setPreferredSize(new Dimension(25,25));
					
				guestsPanel.add(adultsLabel);
				guestsPanel.add(minusButton1);
				guestsPanel.add(adultsField);
				guestsPanel.add(plusButton1);
				guestsPanel.add(childLabel);
				guestsPanel.add(minusButton2);
				guestsPanel.add(childField);
				guestsPanel.add(plusButton2);
				
				buttonPanel2.setLayout(new FlowLayout(5,80,FlowLayout.LEADING));
				buttonPanel2.setBackground(Home.myColor);
				buttonPanel.setBorder(CustomerAccount.border);

					confirmButton.setFont(Home.Serif.deriveFont(20f));
					confirmButton.setForeground(Home.fontColor);
					confirmButton.setBackground(Color.WHITE);
					confirmButton.addActionListener(new myActionListener2());
					confirmButton.setPreferredSize(labelSize);
					
					backButton2.setFont(Home.Serif.deriveFont(20f));
					backButton2.setForeground(Home.fontColor);
					backButton2.setBackground(Color.WHITE);
					backButton2.addActionListener(new myActionListener2());
					backButton2.setPreferredSize(labelSize);
					
				buttonPanel2.add(confirmButton);
				buttonPanel2.add(backButton2);		
					
			roomPanel.add(roomTypePanel);
			roomPanel.add(guestsPanel);
			roomPanel.add(buttonPanel2);
			
		
		addReservationPanel.add(titlePanel);
		addReservationPanel.add(customerPanel);	
		addReservationPanel.add(datePanel);
		addReservationPanel.add(buttonPanel);
		addReservationPanel.add(roomPanel);
		addReservationPanel.setVisible(false);
		
		
	}
		
	private static class myActionListener1 implements ActionListener {
		
		public void actionPerformed(ActionEvent event){
			
			String adults = adultsField.getText();
			String children = childField.getText();
			int max = roomTypeComboBox.getSelectedIndex()+1;
			if(max == 3) {
				max = 4;
			}
			int adult = Integer.valueOf(adults);
			int child = Integer.valueOf(children);
			int sum = adult+child;
			if(event.getSource() == minusButton1) {
				if(adult>1) {
					adults = Integer.toString(--adult);
					adultsField.setText(adults);
				}
			} 
			else if(event.getSource() == plusButton1) {
				if(adult<max&&sum<max) {
					adults = Integer.toString(++adult);
					adultsField.setText(adults);
				}
				else {
					JOptionPane.showMessageDialog(null,"Maximum Occupancy Reached");
				}
			} 
			else if(event.getSource() == minusButton2) {
				if(child>0) {
					children = Integer.toString(--child);
					childField.setText(children);
				}
			} 
			else if(event.getSource() == plusButton2) {
				
				if(child<max&&sum<max) { 
					children = Integer.toString(++child);
					childField.setText(children);
				}
				else {
					JOptionPane.showMessageDialog(null,"Maximum Occupancy Reached");
				}
			} 			
		}
	}
	
	private static class myActionListener2 implements ActionListener {
		
		public void actionPerformed(ActionEvent event){
			
			if(event.getSource() == backButton1) {
				addReservationPanel.setVisible(false);
				Reservations.reservationsPanel.setVisible(true);
			}
			else if(event.getSource() == addRoomButton) {
				nights = checkDates();
				if(nights<1) {
					JOptionPane.showMessageDialog(null,"Selected Dates are Invalid");
					checkInDatePicker.requestFocus();
				}
				else {
					datePanel.setVisible(false);
					roomPanel.setVisible(true);
					checkIn = sdf.format(checkInDatePicker.getDate());
					checkOut = sdf.format(checkOutDatePicker.getDate());
				}
			}
			else if(event.getSource() == backButton2) {
				datePanel.setVisible(true);
				roomPanel.setVisible(false);
				adultsField.setText("1");
				childField.setText("0");
				roomTypeComboBox.setSelectedIndex(0);
				boardBasisComboBox.setSelectedIndex(0);
			}
			else if(event.getSource() == confirmButton) {
				roomType = (String) roomTypeComboBox.getSelectedItem();
				boardBasis = (String) boardBasisComboBox.getSelectedItem();
				adults = Integer.parseInt(adultsField.getText());
				children = Integer.parseInt(childField.getText());
				addReservationMethod();
			}
			else if(event.getSource() == completeButton) {
				if(ready == false) {
					JOptionPane.showMessageDialog(null,"Reservation not valid");
				}
				else {
					insertCustomerRes();
					JOptionPane.showMessageDialog(null,"Reservation Added");
				}
				
			}
			
		}
	}
	
	private static void addReservationMethod() {
		
		LocalDate date = LocalDate.parse(sdf.format(checkInDatePicker.getDate()), dtf);
		String[] dates = new String[nights];
		
		for(int i=0;i<nights;i++) {
			dates[i] = date.plusDays(i).format(dtf);
		}
		//get matching room and add dates
		int i = getRoom(dates);
		if( i == 0) {
			JOptionPane.showMessageDialog(null,"No rooms available");
		}
		else {
			roomNums[0+roomsBooked] = i;
			roomsBooked++;
			JOptionPane.showMessageDialog(null,"Room Added");
			ready = true;
		}
	}
	
	private static int getRoom(String[] dates) {
		
		String sql = "SELECT roomNumber, roomType, datesBooked FROM guestRooms";
		try(Connection conn = Database.connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)){
			Boolean fail = false;
			while(rs.next()) {			
				if(rs.getString("roomType").equals(roomType)) {
					String s = rs.getString("datesBooked");
					s = s.substring(1,s.length()-1);
					
					for(int i=0;i<dates.length;i++) {
												
						Scanner scan = new Scanner(s);
						scan.useDelimiter(",");
						while(scan.hasNext()&&fail==false) {
							
							String str = scan.next();
							if(dates[i].equals(str)) {	
								fail = true;
								i=dates.length;
							}
						}
						scan.close();	
					}
					if(fail == false) {
						return rs.getInt("roomNumber");
					}
				}		
			}
			return 0;
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
			return 0;
		}	
	}
	
	private static void insertCustomerRes() {
		String sql = "INSERT INTO customerReservations(AccountId,roomsBooked,checkIn,checkOut,nights)VALUES(?,?,?,?,?)";
		
		try(Connection conn = Database.connect();
			PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setInt(1, AccountId);
			pstmt.setString(2, Arrays.toString(roomNums)); 
			pstmt.setString(3, checkIn);
			pstmt.setString(4, checkOut);
			pstmt.setInt(5, nights);
			
			pstmt.executeUpdate();
		}
		catch(SQLException e) {
		System.out.println(e.getMessage());
		}
	}
	
	
	private static int checkDates() {
		
		LocalDate in = LocalDate.parse(sdf.format(checkInDatePicker.getDate()), dtf);
		LocalDate out = LocalDate.parse(sdf.format(checkOutDatePicker.getDate()), dtf);
		LocalDate now = LocalDate.now();
		if(in.isBefore(now)) {
			return -1;
		}
		return (int) ChronoUnit.DAYS.between(in,out);
	
	}
}
