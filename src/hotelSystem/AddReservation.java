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
import java.awt.event.ActionEvent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
		
	
		static String name = "Customer: ";
		static JPanel datePanel = new JPanel();
		
			static JLabel customerNameLabel = new JLabel(name);
			static JXDatePicker checkInDatePicker = new JXDatePicker();
			static JLabel checkInDateLabel = new JLabel("Check In: ");
			static JXDatePicker checkOutDatePicker = new JXDatePicker();
			static JLabel checkOutDateLabel = new JLabel("Check Out: ");
			static JButton addRoomButton = new JButton("Add Room");
			static JButton backButton1 = new JButton("Back");
			static JButton completeButton = new JButton("Complete");
		
		static JPanel roomPanel = new JPanel();
		
			static JLabel boardBasisLabel = new JLabel("Board Basis: ");
			static String[] board = {"Bed & Breakfast", "Half Board","Full Board"};
			static JComboBox<String> boardBasisComboBox = new JComboBox<String>(board);
			static JLabel roomTypeLabel = new JLabel("Room Type ");
			static String[] rooms = {"Single", "Double","Twin Double","Suite"};
			static JComboBox<String> roomTypeComboBox = new JComboBox<String>(rooms);
			
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
			
			static JButton confirmButton = new JButton("Confirm");
			static JButton backButton2 = new JButton("Back");
	
		
	public static void addReservationPanel(){
		
	
		
		addReservationPanel.setLayout(null);
		addReservationPanel.setBackground(Home.myColor);
		addReservationPanel.setBounds(170,100,540,540);
		addReservationPanel.setVisible(false);
			
			customerNameLabel.setBounds(10,10,520,30);
			customerNameLabel.setFont(customerNameLabel.getFont().deriveFont(25f));
			datePanel.setLayout(null);
			datePanel.setBackground(Home.myColor);
			datePanel.setBounds(10,40,520,240);
			datePanel.setVisible(true);
			
				checkInDateLabel.setBounds(20,40,120,25);
				checkInDateLabel.setFont(checkInDateLabel.getFont().deriveFont(20f));
				checkInDatePicker.setBounds(20,70,200,40);
				checkInDatePicker.setFormats(sdf);
				checkInDatePicker.setDate(Calendar.getInstance().getTime());
				checkInDatePicker.setFont(checkInDatePicker.getFont().deriveFont(20f));
				checkInDatePicker.requestFocus();
				checkOutDateLabel.setBounds(260,40,120,25);
				checkOutDateLabel.setFont(checkOutDateLabel.getFont().deriveFont(20f));
				checkOutDatePicker.setBounds(260,70,220,40);
				checkOutDatePicker.setFormats(sdf);
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, 1);
				checkOutDatePicker.setDate(cal.getTime());
				checkOutDatePicker.setFont(checkOutDatePicker.getFont().deriveFont(20f));
				checkOutDatePicker.requestFocus();
				addRoomButton.setBounds(10,180,140,30);
				addRoomButton.setFont(addRoomButton.getFont().deriveFont(20f));
				addRoomButton.setBackground(Color.WHITE);
				addRoomButton.addActionListener(new myActionListener2());
				backButton1.setBounds(170,180,140,30);
				backButton1.setFont(addRoomButton.getFont().deriveFont(20f));
				backButton1.setBackground(Color.WHITE);
				backButton1.addActionListener(new myActionListener2());
				completeButton.setBounds(330,180,140,30);
				completeButton.setFont(addRoomButton.getFont().deriveFont(20f));
				completeButton.setBackground(Color.WHITE);
				completeButton.addActionListener(new myActionListener2());
				
			datePanel.add(checkInDateLabel);
			datePanel.add(checkInDatePicker);
			datePanel.add(checkOutDateLabel);
			datePanel.add(checkOutDatePicker);
			datePanel.add(addRoomButton);
			datePanel.add(backButton1);
			datePanel.add(completeButton);
			
			roomPanel.setLayout(null);
			roomPanel.setBackground(Home.myColor);
			roomPanel.setBounds(10,40,520,520);
			roomPanel.setVisible(false);
				
				boardBasisLabel.setBounds(200,30,140,25);
				boardBasisLabel.setFont(boardBasisLabel.getFont().deriveFont(20f));
				boardBasisComboBox.setBounds(200,60,140,25);
				roomTypeLabel.setBounds(20,30,140,25);
				roomTypeLabel.setFont(boardBasisLabel.getFont().deriveFont(20f));
				roomTypeComboBox.setBounds(20,60,140,25);
				
				adultsLabel.setBounds(20,120,80,25);
				adultsLabel.setFont(adultsLabel.getFont().deriveFont(20f));
				minusButton1.setBounds(30,150,25,25);
				minusButton1.addActionListener(new myActionListener1());
				adultsField.setBounds(55,150,25,25);
				adultsField.setHorizontalAlignment(JTextField.CENTER);
				adultsField.setEditable(false);
				plusButton1.setBounds(80,150,25,25);
				plusButton1.addActionListener(new myActionListener1());
				
				childLabel.setBounds(190,120,120,25);
				childLabel.setFont(adultsLabel.getFont().deriveFont(20f));
				minusButton2.setBounds(200,150,25,25);
				minusButton2.addActionListener(new myActionListener1());
				childField.setBounds(225,150,25,25);
				childField.setHorizontalAlignment(JTextField.CENTER);
				childField.setEditable(false);
				plusButton2.setBounds(250,150,25,25);
				plusButton2.addActionListener(new myActionListener1());
				
				confirmButton.setBounds(40,200,140,30);
				confirmButton.setFont(confirmButton.getFont().deriveFont(20f));
				confirmButton.setBackground(Color.WHITE);
				confirmButton.addActionListener(new myActionListener2());
				
				backButton2.setBounds(200,200,140,30);
				backButton2.setFont(confirmButton.getFont().deriveFont(20f));
				backButton2.setBackground(Color.WHITE);
				backButton2.addActionListener(new myActionListener2());
				
				
			roomPanel.add(boardBasisLabel);
			roomPanel.add(boardBasisComboBox);
			roomPanel.add(roomTypeLabel);
			roomPanel.add(roomTypeComboBox);
			roomPanel.add(adultsLabel);
			roomPanel.add(minusButton1);
			roomPanel.add(adultsField);
			roomPanel.add(plusButton1);
			roomPanel.add(childLabel);
			roomPanel.add(minusButton2);
			roomPanel.add(childField);
			roomPanel.add(plusButton2);
			roomPanel.add(confirmButton);
			roomPanel.add(backButton2);
			
		addReservationPanel.add(customerNameLabel);	
		addReservationPanel.add(datePanel);
		addReservationPanel.add(roomPanel);	
		
		
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
		try(Connection conn = Database.connect("BLOP.db");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)){
			Boolean fail = false;
			while(rs.next()) {			
				if(rs.getString("roomType").equals(roomType)) {
					String s = rs.getString("datesBooked");
					s = s.substring(1,s.length()-1);
					
					for(int i=0;i<dates.length;i++) {
												
						Scanner scan = new Scanner(s).useDelimiter(",");
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
		
		try(Connection conn = Database.connect("BLOP.db");
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
