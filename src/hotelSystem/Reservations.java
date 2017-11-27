package hotelSystem;

import java.util.ArrayList;
import java.awt.Color;
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
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;


public class Reservations {
	
	
	static int selectedAccountId;
	//***********************************************************************************
	// Attributes used by reservationsPanel
			static JPanel reservationsPanel = new JPanel();
				static JLabel reservationsLabel = new JLabel("Reservations Manager: ");
				static ArrayList<Integer> searchResults = new ArrayList<Integer>();
				static JLabel searchCustomerLabel = new JLabel("Search Customer:");
				static JTextField searchCustomerField = new JTextField();
				static DefaultListModel<String> listModel = new DefaultListModel<String>();
				static JList<String> resultsList = new JList<String>(listModel);
				static ListSelectionModel lsm = resultsList.getSelectionModel();
				static JScrollPane resultsScrollPane = new JScrollPane(resultsList);
				static JPanel customerInfoPanel = new JPanel();
				
					
					static JLabel customerNameLabel = new JLabel();
					static JTextArea customerAddressArea = new JTextArea();
					static JButton addReservationButton = new JButton("Add Reservation");
					static JButton editAccountButton = new JButton("Edit Account");
					static JButton deleteAccountButton = new JButton("Delete Account");			
				
	//***********************************************************************************
	
	
	
	
	public static void reservationsPanel() {
		
		reservationsPanel.setLayout(null);
		reservationsPanel.setBackground(Home.myColor);
		reservationsPanel.setBounds(170,100,480,480);
			
			reservationsLabel.setBounds(5,5,400,30);
			reservationsLabel.setFont(reservationsLabel.getFont().deriveFont(25f));
			searchCustomerLabel.setBounds(10,40,180,25);
			searchCustomerLabel.setFont(searchCustomerLabel.getFont().deriveFont(20f));
			searchCustomerLabel.setForeground(Home.fontColor);
			searchCustomerField.setBounds(190,40,280,30);
			searchCustomerField.addActionListener(new myActionListener());
			
			resultsScrollPane.setBounds(10,70,460,75);
			resultsScrollPane.setVisible(false);
				resultsList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
				lsm.addListSelectionListener(new myListSelectionListener());
			
			customerInfoPanel.setLayout(null);
			customerInfoPanel.setBackground(Color.WHITE);
			customerInfoPanel.setBounds(10,150,460,180);
			customerInfoPanel.setVisible(false);
				
				customerNameLabel.setBounds(10,10,430,25);
				customerNameLabel.setFont(customerNameLabel.getFont().deriveFont(20f));
				customerAddressArea.setBounds(10,40,430,100);
				customerAddressArea.setFont(customerNameLabel.getFont().deriveFont(20f));
				customerAddressArea.setEditable(false);
				addReservationButton.setBounds(70,150,130,25);
				addReservationButton.setBackground(Color.WHITE);
				addReservationButton.addActionListener(new myActionListener());
				deleteAccountButton.setBounds(260,150,130,25);
				deleteAccountButton.setBackground(Color.WHITE);
				deleteAccountButton.addActionListener(new myActionListener());
				
			customerInfoPanel.add(customerNameLabel);
			customerInfoPanel.add(customerAddressArea);
			customerInfoPanel.add(addReservationButton);			
		
		reservationsPanel.add(reservationsLabel);
		reservationsPanel.add(searchCustomerLabel);
		reservationsPanel.add(searchCustomerField);
		reservationsPanel.add(resultsScrollPane);
		reservationsPanel.add(customerInfoPanel);
		reservationsPanel.setVisible(false);

	}
	
	public static void searchCustomerMethod(String search) {
		
		resultsList.clearSelection();
		customerInfoPanel.setVisible(false);		
		searchResults.clear();
		searchCustomer(search);
		
		if(searchResults.isEmpty()) {
			JOptionPane.showMessageDialog(null,"Username not found.");
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
				reservationsPanel.setVisible(false);
				AddReservation.addReservationPanel.setVisible(true);
				AddReservation.name = "Customer: "+CustomerAccount.getInfo("firstName",selectedAccountId)+" "+CustomerAccount.getInfo("lastName",selectedAccountId);
				AddReservation.customerNameLabel.setText(AddReservation.name);
				AddReservation.AccountId = selectedAccountId;
				
			}
					
		}
	}
	
	//searches database for matches and adds to arrayList
	public static void searchCustomer(String s) {
		
		String sql = "SELECT AccountId, username, firstName, lastName, phone FROM customerAccounts";
		try(Connection conn = Database.connect("BLOP.db");
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
