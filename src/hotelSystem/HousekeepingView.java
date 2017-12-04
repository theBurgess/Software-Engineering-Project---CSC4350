package hotelSystem;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Box;

import java.awt.Color;
import java.awt.Font;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

public class HousekeepingView {

	static JFrame frame;
	
	static JPanel housekeepingPanel = new JPanel();
	static JTextField txtHousekeeping;
	
	static ArrayList<HouseKeeping> records = new ArrayList<HouseKeeping>();
	
	public static void housekeepingPanel() {
		
		housekeepingPanel.setBackground(Home.myColor);
		housekeepingPanel.setBounds(170,100,480,480);
		housekeepingPanel.setLayout(null);
		
			txtHousekeeping = new JTextField();
			txtHousekeeping.setBackground(Home.myColor);
			txtHousekeeping.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
			txtHousekeeping.setEditable(false);
			txtHousekeeping.setForeground(Color.BLACK);
			txtHousekeeping.setText("Housekeeping: ");
			txtHousekeeping.setFont(txtHousekeeping.getFont().deriveFont(20f));
			txtHousekeeping.setBounds(5, 5, 160, 30);
			housekeepingPanel.add(txtHousekeeping);
			txtHousekeeping.setColumns(10);
			
			Box horizontalBox = Box.createHorizontalBox();
			horizontalBox.setBounds(0, 61, 450, 217);
			housekeepingPanel.add(horizontalBox);
			
			String[] columnNames = {
					"Room No", 
			        "Room Type",
			        "Status",
			        "Assign"
			};
			
			Object[][] data = (Object[][]) getHouseKeepingData();
									
			JTable table = new JTable(data, columnNames);
			JScrollPane scrollPane = new JScrollPane(table);
			table.setFillsViewportHeight(true);
			horizontalBox.add(scrollPane);
			
		housekeepingPanel.setVisible(false);
		
	}

	private static Object[][] getHouseKeepingData() {
		// Generate empty records double array for JTable consumption.		
		Object[][] records = new Object[0][0];
		
		// SQL Select
		// TODO FIX Connection cannot find table housekeeping.
		String sql = "SELECT id, roomId, status, assignment FROM housekeeping;";
		try(Connection conn = Database.connect();
				Statement stmt = conn.createStatement();
				ResultSet result = stmt.executeQuery(sql)){
			
			// Re-initialize records array to length of result set.
			records = new Object[result.getFetchSize()][5];
			int len = 0;
			while(result.next()) {	
				HouseKeeping record = new HouseKeeping(
					result.getInt("id"), 
					result.getInt("roomId"), 
					result.getString("status"), 
					result.getString("assignment")
				);
				
				// Parse housekeeping record as Object[];
				Object[] rec = new Object[]{
					record.getRoomId(),
					"Basic",
					record.getStatus(),
					record.getAsssignment()
				};
				
				// Add new housekeeping record to records.
				records[len] = rec;
				len++;
			}	
			return records;
		
		}
		catch(SQLException e) 	{
			System.out.println(e.getMessage());
		}
		return records;
	}

	
}