package hotelSystem;

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

public class CustomerAccount {
	
	
	static int selectedAccountId = 0;
	//***********************************************************************************
	// Attributes used by customersPanel
			static JPanel customersPanel = new JPanel();
				static JLabel searchCustomerLabel = new JLabel("Search Customer:");
				static JTextField searchCustomerField = new JTextField();
				static JPanel customerInfoPanel = new JPanel();
					static JLabel customerNameLabel = new JLabel();
					static JTextArea customerAddressArea = new JTextArea();
					static JButton createAccountButton = new JButton("Create Account");
					static JButton editAccountButton = new JButton("Edit Account");
					static JButton deleteAccountButton = new JButton("Delete Account");
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
				createAccountButton.setBounds(10,150,130,25);
				createAccountButton.setBackground(Color.WHITE);
				createAccountButton.addActionListener(new myActionListener());
				editAccountButton.setBounds(160,150,130,25);
				editAccountButton.setBackground(Color.WHITE);
				editAccountButton.addActionListener(new myActionListener());
				deleteAccountButton.setBounds(310,150,130,25);
				deleteAccountButton.setBackground(Color.WHITE);
				deleteAccountButton.addActionListener(new myActionListener());
				
			customerInfoPanel.add(customerNameLabel);
			customerInfoPanel.add(customerAddressArea);
			customerInfoPanel.add(createAccountButton);
			customerInfoPanel.add(editAccountButton);
			customerInfoPanel.add(deleteAccountButton);
							
		customersPanel.add(searchCustomerLabel);
		customersPanel.add(searchCustomerField);
		customersPanel.add(customerInfoPanel);
		customersPanel.setVisible(false);

	}
	
	public static void searchCustomerMethod() {
		
		selectedAccountId = CustomerAccount.searchCustomer(searchCustomerField.getText());
		customerNameLabel.setText("Name: "+CustomerAccount.getInfo("firstName", selectedAccountId)+" "+CustomerAccount.getInfo("lastName", selectedAccountId));
		customerAddressArea.setText("Mailing Address: "+CustomerAccount.getInfo("street", selectedAccountId)+",\n"
				+CustomerAccount.getInfo("city", selectedAccountId)+", "
				+CustomerAccount.getInfo("stateCode", selectedAccountId)+", "+CustomerAccount.getInfo("zipCode", selectedAccountId)+"\n"
				+"Phone: "+CustomerAccount.getInfo("phone", selectedAccountId));
		customerAddressArea.setLineWrap(true);
		customerAddressArea.setWrapStyleWord(true);
	}
	
	//describes what happens when button is clicked
	private static class myActionListener implements ActionListener {
			
		public void actionPerformed(ActionEvent event){
						
			if(event.getSource() == searchCustomerField) {
				searchCustomerMethod();
			}
			else if(event.getSource() == createAccountButton) {
				CreateAccount.createAccountMethod();
			}
			else if(event.getSource() == editAccountButton) {
				//EditAccount.editAccountMethod();
			}
			else if(event.getSource() == deleteAccountButton) {
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
