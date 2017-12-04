package hotelSystem;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.BorderFactory;
import javax.swing.Box;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class HousekeepingView {
	
	
	static TitledBorder border = new TitledBorder(BorderFactory.createEtchedBorder(Home.fontColor,new Color(210,180,140)),"",TitledBorder.LEFT,TitledBorder.TOP,Home.Serif);
	
	static JFrame frame; //?
	
	static JPanel housekeepingPanel = new JPanel();
	
		static JPanel titlePanel = new JPanel();
			static JLabel txtHousekeeping = new JLabel("Housekeeping: ");
	
	static ArrayList<Housekeeping> records = new ArrayList<Housekeeping>();
	
	public static void housekeepingPanel() {
		
		housekeepingPanel.setLayout(new BorderLayout());
		housekeepingPanel.setBackground(Home.myColor);
			titlePanel.setLayout(new FlowLayout(0,20,FlowLayout.LEADING));
			titlePanel.setBackground(Home.myColor);
			titlePanel.setBorder(border);
				txtHousekeeping.setBackground(Home.myColor);
				txtHousekeeping.setFont(Home.Serif.deriveFont(30f));
				txtHousekeeping.setForeground(Home.fontColor);
			titlePanel.add(txtHousekeeping);
			
			//*****************************************************
			Box horizontalBox = Box.createHorizontalBox();
				Object[][] data = (Object[][]) getHouseKeepingData();
				String[] columnNames = {
						"Room No", 
				        "Room Type",
				        "Status",
				        "Assign."
				};
				JTable table = new JTable(data, columnNames);
				JScrollPane scrollPane = new JScrollPane(table);
				table.setFillsViewportHeight(true);
			horizontalBox.add(scrollPane);
			//*****************************************************
			
		housekeepingPanel.add(titlePanel, BorderLayout.PAGE_START);	
		housekeepingPanel.add(horizontalBox, BorderLayout.CENTER);
		housekeepingPanel.setVisible(false);
		
	}

	private static Object[][] getHouseKeepingData() {
		// Generate empty records double array for JTable consumption.		
		Object[][] records = new Object[0][0];
		
		// SQL Select
		String sql = "SELECT roomNumber, roomType, status, assignment FROM guestRooms;";
		try(Connection conn = Database.connect();
				Statement stmt = conn.createStatement();
				ResultSet result = stmt.executeQuery(sql)){
			
			// Re-initialise records array to length of result set.
			records = new Object[35][4];
			int len = 0;
			while(result.next()) {	
				Housekeeping record = new Housekeeping(
					result.getInt("roomNumber"), 
					result.getString("roomType"), 
					result.getString("status"), 
					result.getString("assignment")
				);
				
				// Parse housekeeping record as Object[];
				Object[] rec = new Object[]{
					record.getRoomId(),
					record.getRoomType(),
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