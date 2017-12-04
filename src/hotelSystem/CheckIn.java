package hotelSystem;

//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import org.jdesktop.swingx.JXDatePicker;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

public class CheckIn {
	
	static SimpleDateFormat sdf =  new SimpleDateFormat("MM-dd-yyyy");
	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy");
	static TitledBorder border = new TitledBorder(BorderFactory.createEtchedBorder(Home.fontColor,new Color(210,180,140)),"",TitledBorder.LEFT,TitledBorder.TOP,Home.Serif);
	static JPanel checkInPanel = new JPanel();
		
		static JPanel textPanel = new JPanel();
			static JPanel titlePanel = new JPanel();
				static JLabel checkInLabel = new JLabel("Checking In: ");
				static JLabel checkOutLabel = new JLabel("Checking Out: ");
		
			static JPanel datePanel = new JPanel();
				static JXDatePicker picker = new JXDatePicker();
				static JLabel dateLabel = new JLabel("Date: ");
			
		static JPanel listPanel = new JPanel();
			static JTextArea checkInText = new JTextArea("hello");
			static JTextArea checkOutText = new JTextArea("goodbye");
			
			static DefaultListModel<String> listModel1 = new DefaultListModel<String>();
			static JList<String> checkInList = new JList<String>(listModel1);
			static ListSelectionModel lsm1 = checkInList.getSelectionModel();
			
			static DefaultListModel<String> listModel2 = new DefaultListModel<String>();
			static JList<String> checkOutList = new JList<String>(listModel2);
			static ListSelectionModel lsm2 = checkOutList.getSelectionModel();
			
			static JScrollPane checkInScrollPane = new JScrollPane(checkInList,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			static JScrollPane checkOutScrollPane = new JScrollPane(checkOutList,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			static JSplitPane checkInSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,checkInScrollPane,checkOutScrollPane);
		
		static JPanel buttonPanel = new JPanel();

	public static void checkInPanel() {
		
		checkInPanel.setLayout(new BorderLayout());
		checkInPanel.setBackground(Home.myColor);
		
			textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.PAGE_AXIS));
			textPanel.setBackground(Home.myColor);
			textPanel.setBorder(border);
			
				titlePanel.setLayout(new FlowLayout(0,100,FlowLayout.LEADING));
				titlePanel.setBackground(Home.myColor);
				titlePanel.setBorder(border);
					checkInLabel.setFont(Home.Serif.deriveFont(20f));
					checkInLabel.setForeground(Home.fontColor);
					checkOutLabel.setFont(Home.Serif.deriveFont(20f));
					checkOutLabel.setForeground(Home.fontColor);
				titlePanel.add(checkInLabel);
				titlePanel.add(checkOutLabel);
				
				datePanel.setLayout(new FlowLayout(0,20,FlowLayout.LEADING));
				datePanel.setBackground(Home.myColor);
				datePanel.setBorder(border);
					dateLabel.setPreferredSize(new Dimension(100,25));
					dateLabel.setFont(Home.Serif.deriveFont(20f));
					dateLabel.setForeground(Home.fontColor);
					picker.setFormats(AddReservation.sdf);
					picker.setDate(Calendar.getInstance().getTime());
					picker.setFont(Home.Serif.deriveFont(20f));
					picker.requestFocus();
					picker.addActionListener(new myActionListener());
				datePanel.add(dateLabel);	
				datePanel.add(picker);
			
			textPanel.add(datePanel);
			textPanel.add(titlePanel);
				
				
			listPanel.setLayout(new BorderLayout());
			listPanel.setBackground(Home.myColor);
			listPanel.setBorder(border);
			listPanel.setPreferredSize(new Dimension(5,150));
				checkInSplitPane.setResizeWeight(0.5);
				checkInList.setFont(Home.Serif);
				checkOutList.setFont(Home.Serif);
			listPanel.add(checkInSplitPane, BorderLayout.CENTER);
			
			buttonPanel.setLayout(new BorderLayout());
			buttonPanel.setBackground(Home.myColor);
			buttonPanel.setBorder(border);
			buttonPanel.setPreferredSize(new Dimension(5,30));
			
		checkInPanel.add(textPanel, BorderLayout.PAGE_START);;
		checkInPanel.add(listPanel, BorderLayout.CENTER);
		checkInPanel.add(buttonPanel,BorderLayout.PAGE_END);
		
		
		getCheckIn();
		getCheckOut();
		
		
	}
	
	
	private static void getCheckIn() {
		
		ArrayList<String> inList = new ArrayList<String>();
		int n = 0;
		Date date = new Date(picker.getDate().getTime());
		//date = date.valueOf(date.toString());
		String sql = "SELECT AccountId,checkIn,checkOut,nights,roomsBooked FROM customerReservations";
		try(Connection conn = Database.connect()){
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String d = rs.getDate("checkIn").toString();
				if(d.equals(date.toString())) {
					
					int rooms = 0;
					String roomsList = rs.getString("roomsBooked").substring(1,rs.getString("roomsBooked").length()-1);
					Scanner scan = new Scanner(roomsList);
					scan.useDelimiter(", ");
					for(int i = 0; i<50;i++) {
						String r = scan.next();
						if(!r.equals("0")) {
							rooms++;
						}
					}
					scan.close();
				
					inList.add(reservationToString(rs.getInt("AccountId"),sdf.format(rs.getDate("checkIn")),sdf.format(rs.getDate("checkOut")),rs.getString("nights"),rooms));
				}
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		String[] r = new String[inList.size()];
		for(int j=0;j<r.length;j++) {
			r[n] = inList.get(n);
		}

		checkInList.setListData(r);
	}
	
private static void getCheckOut() {
		
		ArrayList<String> outList = new ArrayList<String>();
		int n = 0;
		Date date = new Date(picker.getDate().getTime());
		//date = date.valueOf(date.toString());
		String sql = "SELECT AccountId,checkIn,checkOut,nights,roomsBooked FROM customerReservations";
		try(Connection conn = Database.connect()){
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String d = rs.getDate("CheckOut").toString();
				if(d.equals(date.toString())) {
					int rooms = 0;
					String roomsList = rs.getString("roomsBooked").substring(1,rs.getString("roomsBooked").length()-1);
					Scanner scan = new Scanner(roomsList);
					scan.useDelimiter(", ");
					for(int i = 0; i<50;i++) {
						String r = scan.next();
						if(!r.equals("0")) {
							rooms++;
						}
					}
					scan.close();
				
					outList.add(reservationToString(rs.getInt("AccountId"),sdf.format(rs.getDate("checkIn")),sdf.format(rs.getDate("checkOut")),rs.getString("nights"),rooms));
				}
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		String[] r = new String[outList.size()];
		for(int j=0;j<r.length;j++) {
			r[n] = outList.get(n);
		}

		checkOutList.setListData(r);
	}
	
	private static String reservationToString(int accountId, String checkIn, String checkOut, String nights,int roomsBooked) {
		String name = CustomerAccount.getInfo("firstName", accountId)+" "+CustomerAccount.getInfo("lastName", accountId);
		return name+": "+checkIn+" - "+checkOut+". Nights: "+nights+". Rooms: "+roomsBooked+".";	
	}
	
	
	
	
	private static class myActionListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event){
			
			if(event.getSource() == picker) {
				getCheckIn();
				getCheckOut();
			}
		
		}
	}

}
