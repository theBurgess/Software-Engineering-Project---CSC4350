package hotelSystem;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import org.jdesktop.swingx.JXDatePicker;

public class Restaurant {
	
	// restaurant Reservation 
	static JLabel calTitle = new JLabel("Date Selected: ");
	static JPanel restaurantPanel = new JPanel();
	static JXDatePicker picker = new JXDatePicker();
	static JLabel titleName = new JLabel("FirstName: ");
	static JLabel titleName2 = new JLabel("LastName: ");
	static JTextField ReservationName = new JTextField();
	static JTextField ReservationName2 = new JTextField();
	static JLabel lableDate = new JLabel("Date :" );
	static JLabel lableTime = new JLabel("Time :" );
	static JComboBox<String> ReservationTime;
	static String[] time = {"1:00","2:00","3:00","4:00","5:00","6:00","7:00","8:00","9:00","10:00","11:00","12:00" };
	static JLabel lableAMorPM = new JLabel("AM/PM :" );
	static JComboBox<String> ReservationTime2;
	static String[] time2 = {"AM","PM"};
	static int panelWidth = 380;
	static int panelHeight = 480;
	static JButton reserve = new JButton("Reserve");
	static JButton back = new JButton("Back");
	static JComboBox<Integer> ReservationSeats;
	static JLabel lableSeats = new JLabel("No. Seats");
	static Integer[] seats = new Integer[25];
	
	//RoomService 
	static JPanel roomService = new JPanel();
	static JList<String> menu; 
	static JTextArea menuEdit = new JTextArea();
	static JLabel menuTitle = new JLabel("Menu: ");
	static JLabel menuEditTitle = new JLabel("Current Order:");
	static JComboBox<String> floorSelect;
	static JComboBox<String> roomSelect;
	static JButton order = new JButton("Order");
	static JScrollPane scrollPane = new JScrollPane();
	static JScrollPane scrollPane2 = new JScrollPane();
	static JScrollPane scrollPane3 = new JScrollPane();
	static String[] menuArr = new String[30];
	static String[] floors;
	static String[] rooms;
	static JLabel roomServiceTitle = new JLabel(" Room Service");
	static int[] selectedItems;
	static JButton AddOrder = new JButton("AddOrders");
	static JLabel special = new JLabel("Special");
	static JLabel instructions = new JLabel("Instructions:");
	static JTextArea specialInst = new JTextArea();
	static JLabel roomNum = new JLabel("Room:");
	
	
	public static void roomServicePanel() {
		// Main Panel 2
		roomService.setLayout(new BoxLayout(roomService,BoxLayout.PAGE_AXIS));
		roomService.setBackground(Home.myColor);
		
		roomServiceTitle.setFont(roomServiceTitle.getFont().deriveFont(20f));
		menuTitle.setFont(roomServiceTitle.getFont().deriveFont(20f));
		menuEditTitle.setFont(roomServiceTitle.getFont().deriveFont(20f));
		special.setFont(roomServiceTitle.getFont().deriveFont(20f));
		instructions.setFont(roomServiceTitle.getFont().deriveFont(20f));
		roomNum.setFont(roomServiceTitle.getFont().deriveFont(20f));
		menuEdit.setEditable(false);
		
		
		createMenuArr();
		menu = new JList<String>(menuArr);
		menu.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPane.setViewportView(menu);
		scrollPane2.setViewportView(menuEdit);
		scrollPane3.setViewportView(specialInst);
		
		createRooms();
		floorSelect = new JComboBox<String>(floors);
		roomSelect = new JComboBox<String>(rooms);
		
		AddOrder.addActionListener(new myActionListener2());
		order.addActionListener(new myActionListener3());
		
		roomServiceTitle.setBounds(115, 0, 140, 25);
		menuTitle.setBounds(10, 130, 140, 25);
		scrollPane.setBounds(90, 50, 200, 200);
		AddOrder.setBounds(170, 270, 140, 25);
		menuEditTitle.setBounds(10, 320, 140, 25);
		scrollPane2.setBounds(170, 300, 140, 75);
		special.setBounds(10, 380, 140, 25);
		instructions.setBounds(10, 415, 140, 25);
		floorSelect.setBounds(110, 480, 50, 25);
		scrollPane3.setBounds(170, 380, 140, 75);
		roomSelect.setBounds(200, 480, 50, 25);
		roomNum.setBounds(10,480, 140, 25);
		order.setBounds(135, 550, 100, 25);
		
		roomService.add(roomServiceTitle);
		roomService.add(menuTitle);
		roomService.add(scrollPane);
		roomService.add(menuEditTitle);
		roomService.add(scrollPane2);
		roomService.add(floorSelect);
		roomService.add(roomSelect);
		roomService.add(order);
		roomService.add(AddOrder);
		roomService.add(special);
		roomService.add(instructions);
		roomService.add(scrollPane3);
		roomService.add(roomNum);
		
	}
	
	public static void restaurantPanel() {
		
		// Main Panel 1
		restaurantPanel.setLayout(null);
		restaurantPanel.setBackground(Home.myColor);
		restaurantPanel.setBounds(170,100,480,480);
		restaurantPanel.setVisible(false);
		// Calendar control
		picker.setDate(Calendar.getInstance().getTime());
		picker.setFormats(new SimpleDateFormat("MM.dd.yyyy"));
		picker.requestFocus();
		
		//Time Control
		ReservationTime = new JComboBox<String>(time);
		// Time 2 Control
		ReservationTime2 = new JComboBox<String>(time2);
		// Seats Control
		createSeatArr();
		ReservationSeats = new JComboBox<Integer>(seats);
		
		reserve.addActionListener(new myActionListener());
		
		
		titleName.setFont(titleName.getFont().deriveFont(20f));
		titleName2.setFont(titleName2.getFont().deriveFont(20f));
		lableDate.setFont(lableDate.getFont().deriveFont(20f));
		lableTime.setFont(lableTime.getFont().deriveFont(20f));
		lableAMorPM.setFont(lableAMorPM.getFont().deriveFont(20f));
		lableSeats.setFont(lableSeats.getFont().deriveFont(20f));
		
		
		//Labels
		titleName.setBounds(10, 30, 140, 25);
		titleName2.setBounds(10, 70, 140, 25);
		lableDate.setBounds(10,110,140,25);
		lableSeats.setBounds(10, 150, 140, 25);
		lableTime.setBounds(10, 190, 140, 25);
		lableAMorPM.setBounds(10, 230, 140, 25);
		
		// Input Fields
		ReservationName.setBounds(125, 30, 200, 25);
		ReservationName2.setBounds(125, 70, 200, 25);
		picker.setBounds(125, 110, 100, 25);
		ReservationSeats.setBounds(125, 150, 50, 25);
		ReservationTime.setBounds(125, 190, 70, 25);
		ReservationTime2.setBounds(125, 230, 50, 25);
		reserve.setBounds(10,270,100,20);
		back.setBounds(125,270,100,20);
		
		restaurantPanel.add(ReservationName);
		restaurantPanel.add(ReservationName2);
		restaurantPanel.add(picker);
		restaurantPanel.add(titleName);
		restaurantPanel.add(titleName2);
		restaurantPanel.add(lableDate);
		restaurantPanel.add(lableTime);
		restaurantPanel.add(lableAMorPM);
		restaurantPanel.add(ReservationTime);
		restaurantPanel.add(ReservationTime2);
		restaurantPanel.add(reserve);
		restaurantPanel.add(back);
		restaurantPanel.add(ReservationSeats);
		restaurantPanel.add(lableSeats);
	restaurantPanel.setVisible(false);
			
	}
	
	public static void createSeatArr() {
		for(int iter = 0; iter < seats.length; iter++) {
			seats[iter] = iter + 1;
		}
	}
	
	public static void createMenuArr() {
		for(int iter = 0; iter < menuArr.length; iter++) {
			menuArr[iter] = "Food Item #: "+ (iter +1);
		}
	}
	
	public static void createRooms() {
		floors = new String[15];
		rooms = new String[30];
		
		for(int iter = 0; iter < floors.length; iter++ ) {
			floors[iter] = Integer.toString(iter+1);
		}
		
		for(int iter = 0; iter < rooms.length; iter++ ) {
			if(iter < 10) {
			rooms[iter] = "0" + Integer.toString(iter);
			}else {
				rooms[iter] = Integer.toString(iter);
			}
		}
	}
	
	private static class myActionListener implements ActionListener{
		
	
		public void actionPerformed(ActionEvent event) {
			
			String firstName = ReservationName.getText();
			String lastName = ReservationName2.getText();
			Integer seats = ReservationSeats.getItemAt(ReservationSeats.getSelectedIndex());
			String date = picker.getDate().toString();
			String time = ReservationTime.getItemAt(ReservationTime.getSelectedIndex());
			String time2 = ReservationTime2.getItemAt(ReservationTime2.getSelectedIndex());
			
			insertData(firstName,lastName,seats,date,time,time2);
			
		}
			
	}
	
	private static class myActionListener2 implements ActionListener{

		
		public void actionPerformed(ActionEvent arg0) {

			selectedItems = menu.getSelectedIndices();
			
			for(int iter = 0; iter < selectedItems.length; iter++) {
				String sel = menu.getModel().getElementAt(selectedItems[iter]) + "\n";
				menuEdit.append(sel);
			}
		}
		
	}
	
	private static class myActionListener3 implements ActionListener{

		
		public void actionPerformed(ActionEvent arg0) {
			
			
			JFrame pop = new JFrame();
			JLabel txt = new JLabel("Reservation for Room : "+floorSelect.getItemAt(floorSelect.getSelectedIndex()) +""+ roomSelect.getItemAt(roomSelect.getSelectedIndex())+ " Successful !!!");
			txt.setFont(lableSeats.getFont().deriveFont(20f));
			JButton close = new JButton("Close");
			
			pop.setVisible(true);
			pop.setSize(300, 300);
			pop.setLayout(new BorderLayout());
			
			close.addActionListener(new ActionListener() {

				
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					pop.dispose();
				}
				
			});
			
			//txt.setBounds(100, 150, 140, 25);
			//close.setBounds(250,200, 100,100);
			
			pop.add(txt, BorderLayout.CENTER);
			pop.add(close, BorderLayout.PAGE_END);
			
			
			
			
			
		}
		
	}
	
	private static void insertData(String firstName,String lastName,Integer seats,String date,String time,String time2) {
		String sql = "INSERT INTO restaurantReservations(firstName,lastName,seats,date,time,time2)VALUES(?,?,?,?,?,?)";
		
		try(Connection conn = Database.connect();
			PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName); //to do: time permitting: encrypt passwords.
			pstmt.setInt(3, seats);
			pstmt.setString(4, date);
			pstmt.setString(5, time);
			pstmt.setString(5, time2);
			
			
			int  count = pstmt.executeUpdate();
			if(count > 0) {
				JFrame pop = new JFrame();
				JLabel txt = new JLabel("Reservation for : "+ lastName+ " Successful !!!");
				txt.setFont(lableSeats.getFont().deriveFont(20f));
				JButton close = new JButton("Close");
				
				pop.setVisible(true);
				pop.setSize(300, 300);
				pop.setLayout(new BorderLayout());
				
				close.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						pop.dispose();
					}
					
				});
				
				//txt.setBounds(100, 150, 140, 25);
				//close.setBounds(250,200, 100,100);
				
				pop.add(txt, BorderLayout.CENTER);
				pop.add(close, BorderLayout.PAGE_END);
				
				
			}
			
		}
		catch(SQLException e) {
		System.out.println(e.getMessage());
		}
	}	

}