package hotelSystem;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
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


public class CustomerAccount {
	
	
	static int selectedAccountId;
	//***********************************************************************************
	// Attributes used by customersPanel
			static JPanel customersPanel = new JPanel();
				static JLabel customerLabel = new JLabel("Customer Accounts Manager: ");
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
					static JButton createAccountButton = new JButton("Create Account");
					static JButton editAccountButton = new JButton("Edit Account");
					static JButton deleteAccountButton = new JButton("Delete Account");
	//***********************************************************************************
	
	
	
	
	public static void customersPanel() {
		
		customersPanel.setLayout(null);
		customersPanel.setBackground(Home.myColor);
		customersPanel.setBounds(170,100,480,480);
			
			customerLabel.setBounds(5,5,400,30);
			customerLabel.setFont(customerLabel.getFont().deriveFont(25f));
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
				editAccountButton.setBounds(70,150,130,25);
				editAccountButton.setBackground(Color.WHITE);
				editAccountButton.addActionListener(new myActionListener());
				deleteAccountButton.setBounds(260,150,130,25);
				deleteAccountButton.setBackground(Color.WHITE);
				deleteAccountButton.addActionListener(new myActionListener());
				
			customerInfoPanel.add(customerNameLabel);
			customerInfoPanel.add(customerAddressArea);
			customerInfoPanel.add(editAccountButton);
			customerInfoPanel.add(deleteAccountButton);
			
			createAccountButton.setBounds(110,420,260,30);
			createAccountButton.setFont(createAccountButton.getFont().deriveFont(20f));
			createAccountButton.setBackground(Color.WHITE);
			createAccountButton.addActionListener(new myActionListener());
		
		customersPanel.add(customerLabel);
		customersPanel.add(searchCustomerLabel);
		customersPanel.add(searchCustomerField);
		customersPanel.add(resultsScrollPane);
		customersPanel.add(customerInfoPanel);
		customersPanel.add(createAccountButton);
		customersPanel.setVisible(false);

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
				results[i] = toString(searchResults.get(i));
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
				else if(event.getSource() == createAccountButton) {
					CreateAccount.createAccountMethod();
				}
				else if(event.getSource() == editAccountButton) {
					CreateAccount.editAccountMethod();
				}
				else if(event.getSource() == deleteAccountButton) {
					resultsScrollPane.setVisible(false);
					customerInfoPanel.setVisible(false);
					deleteCustomerMethod(selectedAccountId);
					customerNameLabel.setText("");
					customerAddressArea.setText("");
					searchCustomerField.setText("");
					selectedAccountId = 0;
					
				}
						
			}
		}
	
	private static void deleteCustomerMethod(int i) {
		
		if(i<1) {
			JOptionPane.showMessageDialog(null,"No Customer Selected");
		}
		String sql = "DELETE FROM customerAccounts WHERE AccountId = ?";
 
        try (Connection conn = Database.connect("BLOP.db")){
        	
            PreparedStatement pstmt = conn.prepareStatement(sql);
 
            pstmt.setInt(1, i);
            pstmt.executeUpdate();
 
        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

	
	public static String toString(int accountId) {
		String s = getInfo("username",accountId)+" - "+getInfo("firstName",accountId)+" "+getInfo("lastName",accountId)+".";
		return s;
	}
	
	public static String getInfo(String request, int accountId) {
		if(accountId > 0) {
			String sql = "SELECT "+request+" FROM customerAccounts WHERE AccountId = "+accountId;
			try(Connection conn = Database.connect("BLOP.db")){
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				
				String s = rs.getString(request);
				if(s != null) {
					return s;
				}
					}
			catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return "";
		
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
