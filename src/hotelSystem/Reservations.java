package hotelSystem;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;


public class Reservations {
	
	
	static int selectedAccountId = 0;
	//***********************************************************************************
	// Attributes used by reservationsPanel
	
	static TitledBorder border = new TitledBorder(BorderFactory.createEtchedBorder(Home.fontColor,new Color(210,180,140)),"",TitledBorder.LEFT,TitledBorder.TOP,Home.Serif);
			
			static JPanel reservationsPanel = new JPanel();
				static JPanel titlePanel = new JPanel();
				static JLabel reservationsLabel = new JLabel("Reservations Manager: ");
				static ArrayList<Integer> searchResults = new ArrayList<Integer>();
				static JLabel searchCustomerLabel = new JLabel("Search Customer:");
				static JTextField searchCustomerField = new JTextField();
				static DefaultListModel<String> listModel = new DefaultListModel<String>();
				static JList<String> resultsList = new JList<String>(listModel);
				static ListSelectionModel lsm = resultsList.getSelectionModel();
				static JScrollPane resultsScrollPane = new JScrollPane(resultsList);
				static JPanel searchCustomerPanel = new JPanel();
				static JPanel customerInfoPanel = new JPanel();
				
					static JPanel customerNamePanel = new JPanel();
						static JLabel customerNameLabel = new JLabel();
						static JTextArea customerAddressArea = new JTextArea();
					static JPanel buttonPanel = new JPanel();	
						static JButton addReservationButton = new JButton("Add Reservation");
						static JButton editReservationButton = new JButton("Edit Reservation");
						static JButton deleteReservationButton = new JButton("Delete Reservation");	
					
				static JPanel customerReservationsPanel = new JPanel();
					static JLabel customerReservationsLabel = new JLabel("Current Reservations: ");
					static JList<String> reservationsList = new JList<String>(listModel);
					static JScrollPane reservationsScrollPane = new JScrollPane(reservationsList);
					
				
	//***********************************************************************************
	
	
	
	
	public static void reservationsPanel() {
		
		
		border.setTitleColor(Home.fontColor);
		reservationsPanel.setLayout(new BoxLayout(reservationsPanel,BoxLayout.PAGE_AXIS));
		reservationsPanel.setBackground(Home.myColor);
			
			titlePanel.setLayout(new FlowLayout(0,20,FlowLayout.LEADING));
			titlePanel.setBackground(Home.myColor);
			titlePanel.setBorder(border);
				reservationsLabel.setForeground(Home.fontColor);
				reservationsLabel.setFont(Home.Serif.deriveFont(25f));
			titlePanel.add(reservationsLabel);
			
			searchCustomerPanel.setLayout(new FlowLayout(0,20,FlowLayout.LEADING));
			searchCustomerPanel.setBackground(Home.myColor);
			searchCustomerPanel.setBorder(border);
				searchCustomerLabel.setFont(Home.Serif.deriveFont(20f));
				searchCustomerLabel.setForeground(Home.fontColor);
				searchCustomerField.setPreferredSize(new Dimension(200,25));
				searchCustomerField.addActionListener(new myActionListener());
			searchCustomerPanel.add(searchCustomerLabel);
			searchCustomerPanel.add(searchCustomerField);
			
			resultsScrollPane.setPreferredSize(new Dimension(5,50));
				resultsList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
				lsm.addListSelectionListener(new myListSelectionListener());
			
			customerInfoPanel.setLayout(new BoxLayout(customerInfoPanel,BoxLayout.PAGE_AXIS));
			customerInfoPanel.setBackground(Home.myColor);
			customerInfoPanel.setBorder(border);
				customerNamePanel.setLayout(new FlowLayout(5,5,FlowLayout.LEFT));
				customerNamePanel.setBackground(Home.myColor);
					customerNameLabel.setForeground(Home.fontColor);
					customerNameLabel.setFont(Home.Serif.deriveFont(20f));
				customerNamePanel.add(customerNameLabel);	
				customerAddressArea.setFont(Home.Serif.deriveFont(20f));
				customerAddressArea.setForeground(Home.fontColor);
				customerAddressArea.setBackground(Home.myColor);
				customerAddressArea.setPreferredSize(new Dimension(5,180));
				customerAddressArea.setEditable(false);
			customerInfoPanel.add(customerNamePanel);
			customerInfoPanel.add(customerAddressArea);
			customerInfoPanel.add(buttonPanel);
			
			customerReservationsPanel.setLayout(new BoxLayout(customerReservationsPanel,BoxLayout.PAGE_AXIS));
			customerReservationsPanel.setBackground(Home.myColor);
			customerReservationsPanel.setBorder(border);
			customerReservationsPanel.setPreferredSize(new Dimension(5,180));
				customerReservationsLabel.setFont(Home.Serif.deriveFont(20f));
				customerReservationsLabel.setForeground(Home.fontColor);
				reservationsScrollPane.setPreferredSize(new Dimension(5,50));
					reservationsList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
			customerReservationsPanel.add(customerReservationsLabel);
			customerReservationsPanel.add(reservationsScrollPane);
			
			
			buttonPanel.setLayout(new FlowLayout(5,40,FlowLayout.LEADING));
			buttonPanel.setBackground(Home.myColor);
			buttonPanel.setBorder(border);
				editReservationButton.setBackground(Color.WHITE);
				editReservationButton.addActionListener(new myActionListener());
				addReservationButton.setBackground(Color.WHITE);
				addReservationButton.addActionListener(new myActionListener());
				deleteReservationButton.setBackground(Color.WHITE);
				deleteReservationButton.addActionListener(new myActionListener());
			buttonPanel.add(editReservationButton);
			buttonPanel.add(deleteReservationButton);
			buttonPanel.add(addReservationButton);			
		
		reservationsPanel.add(titlePanel);
		reservationsPanel.add(searchCustomerPanel);
		reservationsPanel.add(resultsScrollPane);
		reservationsPanel.add(customerInfoPanel);
		reservationsPanel.add(customerReservationsPanel);
		reservationsPanel.add(buttonPanel);

	}
	
	public static void searchCustomerMethod(String search) {
		
		resultsList.clearSelection();		
		searchResults.clear();
		searchCustomer(search);
		
		if(searchResults.isEmpty()) {
			JOptionPane.showMessageDialog(null,"Account not found.");
			searchCustomerField.requestFocus();
		}
		else {
			resultsScrollPane.setVisible(true);
			String[] results = new String[searchResults.size()];
			for(int i=0;i<searchResults.size();i++) {
				results[i] = CustomerAccount.toString(searchResults.get(i));
			}
			resultsList.setListData(results);
		}
	}
	
	private static class myListSelectionListener implements ListSelectionListener {
		
		public void valueChanged(ListSelectionEvent event) {
			
			
			selectedAccountId = searchResults.get(lsm.getLeadSelectionIndex());
			customerInfoPanel.setVisible(true);
			customerNameLabel.setText("Name: "+CustomerAccount.getInfo("firstName", selectedAccountId)+" "+CustomerAccount.getInfo("lastName", selectedAccountId));
			customerAddressArea.setText("Mailing Address: "+CustomerAccount.getInfo("street", selectedAccountId)+",\n"
					+CustomerAccount.getInfo("city", selectedAccountId)+", "
					+CustomerAccount.getInfo("stateCode", selectedAccountId)+", "+CustomerAccount.getInfo("zipCode", selectedAccountId)+"\n"
					+"Phone: "+CustomerAccount.getInfo("phone", selectedAccountId));
			customerAddressArea.setLineWrap(true);
			customerAddressArea.setWrapStyleWord(true);
			
		}
	}
	
	//describes what happens when button is clicked
	private static class myActionListener implements ActionListener {
			
		public void actionPerformed(ActionEvent event){
						
			if(event.getSource() == searchCustomerField) {
				String search = searchCustomerField.getText();
				searchCustomerMethod(search);
			}
			else if(event.getSource() == addReservationButton) {
				if(selectedAccountId > 0) {
					AddReservation.addReservationPanel.setVisible(true);
					AddReservation.name = "Customer: "+CustomerAccount.getInfo("firstName",selectedAccountId)+" "+CustomerAccount.getInfo("lastName",selectedAccountId);
					AddReservation.customerNameLabel.setText(AddReservation.name);
					AddReservation.AccountId = selectedAccountId;
				}
				else {
					JOptionPane.showMessageDialog(null,"Please select a valid account.");
				}
				
				
			}
					
		}
	}
	
	//searches database for matches and adds to arrayList
	public static void searchCustomer(String s) {
		
		String sql = "SELECT AccountId, username, firstName, lastName, phone FROM customerAccounts";
		try(Connection conn = Database.connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)){
			
			while(rs.next()) {
				if(rs.getString("username").equals(s)) {
					searchResults.add(rs.getInt("AccountId"));
				}
				else if(rs.getString("firstName").equals(s)) {
					searchResults.add(rs.getInt("AccountId"));
				}
				else if(rs.getString("lastName").equals(s)) {
					searchResults.add(rs.getInt("AccountId"));
				}
				else if(rs.getString("phone").equals(s)) {
					searchResults.add(rs.getInt("AccountId"));
				}
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}	
	}
}
