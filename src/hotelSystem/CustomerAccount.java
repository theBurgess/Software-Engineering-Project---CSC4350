package hotelSystem;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CustomerAccount {
	
	//***********************************************************************************
	// Attributes used by customersPanel
			static JPanel customersPanel = new JPanel();
				static JLabel searchCustomerLabel = new JLabel("Search Customer:");
				static JTextField searchCustomerField = new JTextField();
				static JPanel customerInfoPanel = new JPanel();
					static JLabel customerNameLabel = new JLabel();
					static JTextArea customerAddressArea = new JTextArea();
					static JButton createAccountButton = new JButton("Create Account");
	//***********************************************************************************
	
	
	
	
	public static void customersPanel() {
		
		customersPanel.setLayout(null);
		customersPanel.setBackground(Home.bisque);
		customersPanel.setBounds(50,80,480,480);
		
			searchCustomerLabel.setBounds(10,10,180,25);
			searchCustomerLabel.setFont(searchCustomerLabel.getFont().deriveFont(20f));
			searchCustomerField.setBounds(190,10,280,25);
			searchCustomerField.addActionListener(new myActionListener());
			customerInfoPanel.setLayout(null);
			customerInfoPanel.setBackground(Color.WHITE);
			customerInfoPanel.setBounds(15,45,450,200);
				
				customerNameLabel.setBounds(10,10,430,25);
				customerNameLabel.setFont(customerNameLabel.getFont().deriveFont(20f));
				customerAddressArea.setBounds(10,40,430,100);
				customerAddressArea.setFont(customerNameLabel.getFont().deriveFont(20f));
				customerAddressArea.setEditable(false);
				createAccountButton.setBounds(10,150,150,25);
				createAccountButton.setBackground(Color.WHITE);
				createAccountButton.addActionListener(new myActionListener());
				
			customerInfoPanel.add(customerNameLabel);
			customerInfoPanel.add(customerAddressArea);
			customerInfoPanel.add(createAccountButton);
							
		customersPanel.add(searchCustomerLabel);
		customersPanel.add(searchCustomerField);
		customersPanel.add(customerInfoPanel);
		customersPanel.setVisible(false);

	}
	
	public static void searchCustomerMethod() {
		
		int AccountId = CustomerAccount.searchCustomer(searchCustomerField.getText());
		customerNameLabel.setText("Name: "+CustomerAccount.getInfo("firstName", AccountId)+" "+CustomerAccount.getInfo("lastName", AccountId));
		customerAddressArea.setText("Mailing Address: "+CustomerAccount.getInfo("street", AccountId)+",\n"
				+CustomerAccount.getInfo("city", AccountId)+", "
				+CustomerAccount.getInfo("stateCode", AccountId)+", "+CustomerAccount.getInfo("zipCode", AccountId)+"\n"
				+"Phone: "+CustomerAccount.getInfo("phone", AccountId));
		customerAddressArea.setLineWrap(true);
		customerAddressArea.setWrapStyleWord(true);
	}
	
	//describes what happens when button is clicked
	private static class myActionListener implements ActionListener {
			
		public void actionPerformed(ActionEvent event){
						
			if(event.getSource() == searchCustomerField) {
				CustomerAccount.searchCustomerMethod();
			}
			else if(event.getSource() == createAccountButton) {
				CreateAccount.createAccountMethod();
			}
					
		}
	}
		
	private static String getInfo(String request, int accountId) {
		if(accountId > 0) {
			String sql = "SELECT "+request+" FROM CustomerAccounts WHERE AccountId = "+accountId;
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
	
	public static int searchCustomer(String s) {
		String sql = "SELECT AccountId, username, firstName, lastName FROM customerAccounts";
		try(Connection conn = Database.connect("BLOP.db");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)){
			
			while(rs.next()) {
				if(rs.getString("username").equals(s)) {
					return rs.getInt("AccountId");
					
				}	
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return -1;	
	}
}
