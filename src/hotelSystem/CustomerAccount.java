package hotelSystem;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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


public class CustomerAccount {
	
	
	static int selectedAccountId;
	//***********************************************************************************
	// Attributes used by customersPanel
			
	static TitledBorder border = new TitledBorder(BorderFactory.createEtchedBorder(Home.fontColor,new Color(210,180,140)),"",TitledBorder.LEFT,TitledBorder.TOP,Home.Serif);
	
			static JPanel customersPanel = new JPanel();
				static JPanel titlePanel = new JPanel();
				static JLabel customerLabel = new JLabel("Customer Accounts Manager: ");
				static ArrayList<Integer> searchResults = new ArrayList<Integer>();
				static JLabel searchCustomerLabel = new JLabel("Search Customer:");
				static JTextField searchCustomerField = new JTextField();
				static DefaultListModel<String> listModel = new DefaultListModel<String>();
				static JList<String> resultsList = new JList<String>(listModel);
				static String[] blank = {""};
				static ListSelectionModel lsm = resultsList.getSelectionModel();
				static JScrollPane resultsScrollPane = new JScrollPane(resultsList);
				static JPanel searchCustomerPanel = new JPanel();
				static JPanel customerInfoPanel = new JPanel();
					static JPanel customerNamePanel = new JPanel();
						static JLabel customerNameLabel = new JLabel();
						static JTextArea customerAddressArea = new JTextArea();
					static JPanel buttonPanel = new JPanel();
						static JButton createAccountButton = new JButton("Create Account");
						static JButton editAccountButton = new JButton("Edit Account");
						static JButton deleteAccountButton = new JButton("Delete Account");
	//***********************************************************************************
	
	
	
	
	public static void customersPanel() {
		
		
		border.setTitleColor(Home.fontColor);
		customersPanel.setLayout(new BoxLayout(customersPanel,BoxLayout.PAGE_AXIS));
		customersPanel.setBackground(Home.myColor);
		
					
			titlePanel.setLayout(new FlowLayout(0,20,FlowLayout.LEADING));
			titlePanel.setBackground(Home.myColor);
			titlePanel.setBorder(border);
				customerLabel.setFont(Home.Serif.deriveFont(25f));
				customerLabel.setForeground(Home.fontColor);
			titlePanel.add(customerLabel);
			
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
			customerInfoPanel.setPreferredSize(new Dimension(5,180));
				customerNamePanel.setLayout(new FlowLayout(5,5,FlowLayout.LEFT));
				customerNamePanel.setBackground(Home.myColor);
					customerNameLabel.setFont(Home.Serif.deriveFont(20f));
					customerNameLabel.setForeground(Home.fontColor);
				customerNamePanel.add(customerNameLabel);	
				customerAddressArea.setFont(Home.Serif.deriveFont(20f));
				customerAddressArea.setForeground(Home.fontColor);
				customerAddressArea.setBackground(Home.myColor);
				customerAddressArea.setEditable(false);
			customerInfoPanel.add(customerNamePanel);
			customerInfoPanel.add(customerAddressArea);
			customerInfoPanel.add(buttonPanel);
			
			buttonPanel.setLayout(new FlowLayout(5,60,FlowLayout.LEADING));
			buttonPanel.setBackground(Home.myColor);
			buttonPanel.setBorder(border);
				editAccountButton.setBackground(Color.WHITE);
				editAccountButton.addActionListener(new myActionListener());
				deleteAccountButton.setBackground(Color.WHITE);
				deleteAccountButton.addActionListener(new myActionListener());
				createAccountButton.setBackground(Color.WHITE);
				createAccountButton.addActionListener(new myActionListener());
			buttonPanel.add(editAccountButton);
			buttonPanel.add(deleteAccountButton);
			buttonPanel.add(createAccountButton);	
		
		customersPanel.add(titlePanel);
		customersPanel.add(searchCustomerPanel);
		customersPanel.add(resultsScrollPane);
		customersPanel.add(customerInfoPanel);
		customersPanel.add(buttonPanel);
		customersPanel.add(Box.createVerticalGlue());
		customersPanel.setVisible(false);
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
				results[i] = toString(searchResults.get(i));
			}
			resultsList.setListData(results);
		}
	}
	
	private static class myListSelectionListener implements ListSelectionListener {
		
		public void valueChanged(ListSelectionEvent event) {
			
			
			selectedAccountId = searchResults.get(lsm.getLeadSelectionIndex());
			customerAddressArea.setVisible(true);
			customerNameLabel.setVisible(true);
			customerNameLabel.setText("Name: "+CustomerAccount.getInfo("firstName", selectedAccountId)+" "+CustomerAccount.getInfo("lastName", selectedAccountId)+",");
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
					if(selectedAccountId<1) {
						JOptionPane.showMessageDialog(null,"No Customer Selected");
					}
					else {
						CreateAccount.editAccountMethod();
					}
				}
				else if(event.getSource() == deleteAccountButton) {
					
					resultsList.clearSelection();
					resultsList.setListData(blank);
					
					//searchResults.clear();
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
		else {
			String sql = "DELETE FROM customerAccounts WHERE AccountId = ?";
			String sql2 = "DELETE FROM customerReservations WHERE AccountId = ?";
	        try (Connection conn = Database.connect()){
	        	
	            PreparedStatement pstmt = conn.prepareStatement(sql);
	 
	            pstmt.setInt(1, i);
	            pstmt.executeUpdate();
	 
	        } 
	        catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        try (Connection conn = Database.connect()){
	        	
	            PreparedStatement pstmt = conn.prepareStatement(sql2);
	 
	            pstmt.setInt(1, i);
	            pstmt.executeUpdate();
	            JOptionPane.showMessageDialog(null,"Customer Deleted");
	        } 
	        catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
		}
    }

	
	public static String toString(int accountId) {
		String s = getInfo("username",accountId)+" - "+getInfo("firstName",accountId)+" "+getInfo("lastName",accountId)+".";
		return s;
	}
	
	public static String getInfo(String request, int accountId) {
		if(accountId > 0) {
			String sql = "SELECT "+request+" FROM customerAccounts WHERE AccountId = "+accountId;
			try(Connection conn = Database.connect()){
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
		try(Connection conn = Database.connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)){
			
			while(rs.next()) {
				if(rs.getString("username").equalsIgnoreCase(s)) {
					searchResults.add(rs.getInt("AccountId"));
				}
				else if(rs.getString("firstName").equalsIgnoreCase(s)) {
					searchResults.add(rs.getInt("AccountId"));
				}
				else if(rs.getString("lastName").equalsIgnoreCase(s)) {
					searchResults.add(rs.getInt("AccountId"));
				}
				else if(rs.getString("phone").equalsIgnoreCase(s)) {
					searchResults.add(rs.getInt("AccountId"));
				}
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}	
	}
}
