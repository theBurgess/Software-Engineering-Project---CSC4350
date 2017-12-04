package hotelSystem;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import org.jdesktop.swingx.JXDatePicker;

public class Restaurant {
	
	
	
	static TitledBorder border = new TitledBorder(BorderFactory.createEtchedBorder(Home.fontColor,new Color(210,180,140)),"",TitledBorder.LEFT,TitledBorder.TOP,Home.Serif);
	
	//RoomService 
		static JPanel roomService = new JPanel();
		
			static JPanel titlePanel1 = new JPanel();
				static JLabel roomServiceTitle = new JLabel("Room Service:");
			
			static JPanel menuPanel = new JPanel();
				static JPanel mLabelPanel = new JPanel();
					static JLabel menuTitle = new JLabel("Menu: ");
				static String[] menuArr = new String[30];
				static JList<String> menu = new JList<String>(menuArr); 
				static JScrollPane scrollPane = new JScrollPane(menu);		
				static JPanel buttonPanel1 = new JPanel();
					static JButton addOrder = new JButton("Add");
					static JButton removeOrder = new JButton("Remove");
				
				
			static JPanel currentOrderPanel = new JPanel();
				static JPanel cLabelPanel = new JPanel();
					static JLabel currentOrderTitle = new JLabel("Current Order:");
				static JTextArea menuEdit = new JTextArea();
				static JScrollPane scrollPane2 = new JScrollPane(menuEdit);
			
			static JPanel instructionsPanel = new JPanel();
				static JPanel iLabelPanel = new JPanel();
					static JLabel instructionsLabel = new JLabel("Special Instructions:");
				
				static JTextArea specialInst = new JTextArea();	
				static JScrollPane scrollPane3 = new JScrollPane(specialInst);
			
				static JPanel buttonPanel2 = new JPanel();
					static JLabel roomNum = new JLabel("Room Number:");
					static JComboBox<String> roomSelect;
					static JButton order = new JButton("Order");

		
		static String[] rooms;
		
		static int[] selectedItems;
		
		
	// restaurant Reservation
	static JPanel restaurantPanel = new JPanel();
		static JPanel titlePanel2 = new JPanel();
		static JLabel restaurantTitle = new JLabel("Restaurant - Create Reservation: ");
		
		static JPanel namePanel = new JPanel();
			static JPanel firstPanel = new JPanel();
				static JLabel titleName = new JLabel("First Name: ");
				static JTextField ReservationName = new JTextField();
			static JPanel lastPanel = new JPanel();
				static JLabel titleName2 = new JLabel("Last Name: ");
				static JTextField ReservationName2 = new JTextField();
		
		static JPanel datePanel = new JPanel();
			static JLabel lableDate = new JLabel("Date :" );
			static JXDatePicker picker = new JXDatePicker();
		
		static JPanel seatsPanel = new JPanel();
			static JLabel lableSeats = new JLabel("No. of Seats: ");
			static Integer[] seats = new Integer[25];
			static JComboBox<Integer> ReservationSeats;
		
		static JPanel timePanel1 = new JPanel();
			static JLabel lableTime = new JLabel("Time :" );
			static String[] time = {"1:00","2:00","3:00","4:00","5:00","6:00","7:00","8:00","9:00","10:00","11:00","12:00" };
			static JComboBox<String> ReservationTime;
		
		static JPanel timePanel2 = new JPanel();
			static JLabel lableAMorPM = new JLabel("AM/PM :" );
			static String[] time2 = {"AM","PM"};
			static JComboBox<String> ReservationTime2;
		
		static JPanel buttonPanel = new JPanel();
			static JButton reserve = new JButton("Reserve");
			static JButton back = new JButton("Back");
		
	static JLabel calTitle = new JLabel("Date Selected: ");

	static int panelWidth = 380;
	static int panelHeight = 480;

	
	public static void roomServicePanel() {
		// Main Panel 2
		roomService.setLayout(new BoxLayout(roomService,BoxLayout.PAGE_AXIS));
		roomService.setBackground(Home.myColor);
		
			titlePanel1.setLayout(new FlowLayout(0,20,FlowLayout.LEADING));
			titlePanel1.setBackground(Home.myColor);
			titlePanel1.setBorder(border);
			//titlePanel.setPreferredSize(new Dimension(5,25));
				roomServiceTitle.setForeground(Home.fontColor);
				roomServiceTitle.setFont(Home.Serif.deriveFont(25f));
			titlePanel1.add(roomServiceTitle);
			
			menuPanel.setLayout(new BoxLayout(menuPanel,BoxLayout.PAGE_AXIS));
			menuPanel.setBackground(Home.myColor);
			menuPanel.setBorder(border);
				mLabelPanel.setLayout(new FlowLayout(0,20,FlowLayout.LEADING));
				mLabelPanel.setBackground(Home.myColor);
				mLabelPanel.setBorder(border);
					menuTitle.setFont(Home.Serif.deriveFont(20f));
					menuTitle.setForeground(Home.fontColor);
				mLabelPanel.add(menuTitle);
				
				createMenuArr();
				menu.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				
				buttonPanel1.setLayout(new FlowLayout(5,40,FlowLayout.LEADING));
				buttonPanel1.setBackground(Home.myColor);
				buttonPanel1.setBorder(border);
					addOrder.setBackground(Color.WHITE);
					addOrder.addActionListener(new addOrder());
					addOrder.setForeground(Home.fontColor);
					addOrder.setFont(Home.Serif);
					removeOrder.setBackground(Color.WHITE);
					removeOrder.addActionListener(new addOrder());	
					removeOrder.setForeground(Home.fontColor);
					removeOrder.setFont(Home.Serif);
				buttonPanel1.add(addOrder);	
				buttonPanel1.add(removeOrder);			
			menuPanel.add(mLabelPanel);
			menuPanel.add(scrollPane);
			menuPanel.add(buttonPanel1);
			
			currentOrderPanel.setLayout(new BoxLayout(currentOrderPanel,BoxLayout.PAGE_AXIS));
			currentOrderPanel.setBackground(Home.myColor);
			currentOrderPanel.setBorder(border);
				cLabelPanel.setLayout(new FlowLayout(0,20,FlowLayout.LEADING));
				cLabelPanel.setBackground(Home.myColor);
				cLabelPanel.setBorder(border);
					currentOrderTitle.setFont(Home.Serif.deriveFont(20f));
					currentOrderTitle.setForeground(Home.fontColor);
				cLabelPanel.add(currentOrderTitle);
				menuEdit.setEditable(false);
			currentOrderPanel.add(cLabelPanel);
			currentOrderPanel.add(scrollPane2);
			
			instructionsPanel.setLayout(new BoxLayout(instructionsPanel,BoxLayout.PAGE_AXIS));
			instructionsPanel.setBackground(Home.myColor);
			instructionsPanel.setBorder(border);
				iLabelPanel.setLayout(new FlowLayout(0,20,FlowLayout.LEADING));
				iLabelPanel.setBackground(Home.myColor);
				iLabelPanel.setBorder(border);
					instructionsLabel.setFont(Home.Serif.deriveFont(20f));
					instructionsLabel.setForeground(Home.fontColor);
				iLabelPanel.add(instructionsLabel);
			instructionsPanel.add(iLabelPanel);
			instructionsPanel.add(scrollPane3);
			
			buttonPanel2.setLayout(new FlowLayout(5,40,FlowLayout.LEADING));
			buttonPanel2.setBackground(Home.myColor);
			buttonPanel2.setBorder(border);
				roomNum.setFont(Home.Serif.deriveFont(20f));
				roomNum.setForeground(Home.fontColor);
				createRooms();
				roomSelect = new JComboBox<String>(rooms);
				order.setBackground(Color.WHITE);
				order.setForeground(Home.fontColor);
				order.setFont(Home.Serif);
				order.addActionListener(new Order());
			buttonPanel2.add(roomNum);	
			buttonPanel2.add(roomSelect);
			buttonPanel2.add(order);
			
		roomService.add(titlePanel1);
		roomService.add(menuPanel);
		roomService.add(currentOrderPanel);
		roomService.add(instructionsPanel);
		roomService.add(buttonPanel2);

	}

	public static void restaurantPanel() {

		// Main Panel 1
		restaurantPanel.setLayout(new BoxLayout(restaurantPanel,BoxLayout.PAGE_AXIS));
		restaurantPanel.setBackground(Home.myColor);

			titlePanel2.setLayout(new FlowLayout(0,20,FlowLayout.LEADING));
			titlePanel2.setBackground(Home.myColor);
			titlePanel2.setBorder(border);
				restaurantTitle.setForeground(Home.fontColor);
				restaurantTitle.setFont(Home.Serif.deriveFont(25f));
			titlePanel2.add(restaurantTitle);
			
			namePanel.setLayout(new BoxLayout(namePanel,BoxLayout.PAGE_AXIS));
			namePanel.setBackground(Home.myColor);
			namePanel.setBorder(border);
				firstPanel.setBackground(Home.myColor);
				firstPanel.setLayout(new FlowLayout(0,20,FlowLayout.LEADING));
					titleName.setForeground(Home.fontColor);
					titleName.setFont(Home.Serif.deriveFont(20f));
					ReservationName.setPreferredSize(new Dimension(150,25));
				firstPanel.add(titleName);
				firstPanel.add(ReservationName);
					
				lastPanel.setBackground(Home.myColor);
				lastPanel.setLayout(new FlowLayout(0,20,FlowLayout.LEADING));
					titleName2.setForeground(Home.fontColor);
					titleName2.setFont(Home.Serif.deriveFont(20f));
					ReservationName2.setPreferredSize(new Dimension(150,25));
				lastPanel.add(titleName2);
				lastPanel.add(ReservationName2);
			namePanel.add(firstPanel);
			namePanel.add(lastPanel);
			
			datePanel.setLayout(new FlowLayout(0,20,FlowLayout.LEADING));
			datePanel.setBackground(Home.myColor);
			datePanel.setBorder(border);
				lableDate.setForeground(Home.fontColor);
				lableDate.setFont(Home.Serif.deriveFont(20f));
				picker.setPreferredSize(new Dimension(150,20));
				picker.setDate(Calendar.getInstance().getTime());
				picker.setFormats(new SimpleDateFormat("MM.dd.yyyy"));
				picker.requestFocus();
			datePanel.add(lableDate);
			datePanel.add(picker);
			
			seatsPanel.setLayout(new FlowLayout(0,20,FlowLayout.LEADING));
			seatsPanel.setBackground(Home.myColor);
			seatsPanel.setBorder(border);
				lableSeats.setForeground(Home.fontColor);
				lableSeats.setFont(Home.Serif.deriveFont(20f));
				createSeatArr();
				ReservationSeats = new JComboBox<Integer>(seats);	
			seatsPanel.add(lableSeats);
			seatsPanel.add(ReservationSeats);
			
			timePanel1.setLayout(new FlowLayout(0,20,FlowLayout.LEADING));
			timePanel1.setBackground(Home.myColor);
			timePanel1.setBorder(border);
				ReservationTime = new JComboBox<String>(time);
				lableTime.setForeground(Home.fontColor);
				lableTime.setFont(Home.Serif.deriveFont(20f));
			timePanel1.add(lableTime);
			timePanel1.add(ReservationTime);
			
			timePanel2.setLayout(new FlowLayout(0,20,FlowLayout.LEADING));
			timePanel2.setBackground(Home.myColor);
			timePanel2.setBorder(border);
				ReservationTime2 = new JComboBox<String>(time2);
				lableAMorPM.setForeground(Home.fontColor);
				lableAMorPM.setFont(Home.Serif.deriveFont(20f));
			timePanel2.add(lableAMorPM);
			timePanel2.add(ReservationTime2);
			
			buttonPanel.setLayout(new FlowLayout(0,20,FlowLayout.LEADING));
			buttonPanel.setBackground(Home.myColor);
			buttonPanel.setBorder(border);
				reserve.setBackground(Color.WHITE);
				reserve.setForeground(Home.fontColor);
				reserve.setFont(Home.Serif.deriveFont(20f));
				reserve.addActionListener(new myActionListener());
				back.setBackground(Color.WHITE);
				back.setForeground(Home.fontColor);
				back.setFont(Home.Serif.deriveFont(20f));
			buttonPanel.add(reserve);
			buttonPanel.add(back);
				
		restaurantPanel.add(titlePanel2);
		restaurantPanel.add(namePanel);
		restaurantPanel.add(datePanel);
		restaurantPanel.add(seatsPanel);
		restaurantPanel.add(timePanel1);
		restaurantPanel.add(timePanel2);
		restaurantPanel.add(buttonPanel);
		restaurantPanel.add(Box.createVerticalGlue());
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
		rooms = new String[35];

		for(int iter = 0; iter < rooms.length; iter++ ) {
			if(iter < 10) {
				rooms[iter] = "0" + Integer.toString(iter+1);
			}else {
				rooms[iter] = Integer.toString(iter+1);
			}
		}
	}

	private static class myActionListener implements ActionListener{


		public void actionPerformed(ActionEvent event) {
			
			if(StaffAccount.getInfo("loggedIn", Home.AccountId).equals("0")) {
            	JOptionPane.showMessageDialog(null,"Must be Logged In");
			}
			else {
				String firstName = ReservationName.getText();
				String lastName = ReservationName2.getText();
				Integer seats = ReservationSeats.getItemAt(ReservationSeats.getSelectedIndex());
				String date = picker.getDate().toString();
				String time = ReservationTime.getItemAt(ReservationTime.getSelectedIndex());
				String time2 = ReservationTime2.getItemAt(ReservationTime2.getSelectedIndex());
	
				insertData(firstName,lastName,seats,date,time,time2);
			}
		}

	}

	private static class addOrder implements ActionListener{


		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==addOrder) {
				selectedItems = menu.getSelectedIndices();
	
				for(int iter = 0; iter < selectedItems.length; iter++) {
					String sel = menu.getModel().getElementAt(selectedItems[iter]) + "\n";
					menuEdit.append(sel);
				}
			}
			else {
				//to-do add action for remove button
			}
		}

	}

	private static class Order implements ActionListener{


		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()== order) {
				if(StaffAccount.getInfo("loggedIn", Home.AccountId).equals("0")) {
	            	JOptionPane.showMessageDialog(null,"Must be Logged In");
				}
				else {
					String room = roomSelect.getItemAt(roomSelect.getSelectedIndex());
					String[] orders = menuEdit.getText().split("\n");
					String order = "";
					for(int iter = 0; iter < orders.length; iter++) {
						order = order + " " +orders[iter];
					}
			
					String instructions = specialInst.getText();
					
					insertData(room,order,instructions);
				}
			}
		}

	}

	private static void insertData(String room, String order, String instruction) {
		String sql = "INSERT INTO roomService(room,meal,spInstructions)VALUES(?,?,?)";
		try(Connection conn = Database.connect();
				PreparedStatement pstmt = conn.prepareStatement(sql)){

			pstmt.setString(1, room);
			pstmt.setString(2, order); 
			pstmt.setString(3, instruction);

			int count = pstmt.executeUpdate();
			if(count > 0) {
				JOptionPane.showMessageDialog(null,"Reservation for Room : "+ roomSelect.getItemAt(roomSelect.getSelectedIndex())+ " Successful !!!");
				
				/**
				JFrame pop = new JFrame();
				JLabel txt = new JLabel("Reservation for Room : "+ roomSelect.getItemAt(roomSelect.getSelectedIndex())+ " Successful !!!");
				txt.setFont(Home.Serif.deriveFont(20f));
				txt.setForeground(Home.fontColor);
				JButton close = new JButton("Close");

				pop.setVisible(true);
				pop.setSize(300, 300);
				pop.setLayout(new BorderLayout());

				close.addActionListener(new ActionListener() {


					public void actionPerformed(ActionEvent arg0) {

						pop.dispose();
					}

				});
				txt.setBounds(100, 150, 140, 25);
				close.setBounds(250,200, 100,100);
				pop.add(txt, BorderLayout.CENTER);
				pop.add(close, BorderLayout.PAGE_END);
				*/


			}

		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
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
				
				JOptionPane.showMessageDialog(null,"Reservation for : "+ lastName+ " Successful !!!");
				
				/**	Again... WHY???
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

				pop.add(txt, BorderLayout.CENTER);
				pop.add(close, BorderLayout.PAGE_END);
				*/

			}

		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}	

}