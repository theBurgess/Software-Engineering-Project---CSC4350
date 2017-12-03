package hotelSystem;

import java.io.File;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Console {
		
	/**
	 * this class contains the main method that dictates what happens each time the program is executed
	 */
	public static void main(String[] args) {
		
		//check for class libraries
		try {
			Class.forName("org.jdesktop.swingx.JXDatePicker");
			Class.forName("org.sqlite.JDBC");
		}
		catch(ClassNotFoundException e) {
			System.out.println("No class: org.sqlite.JDBC");
			System.out.println("No class: org.jdesktop.swingx.JXDatePicker");
		}
		/** if the program is being run for the first time the database will be built
		 * 
		 */
		
		    try {
		            // Set System L&F
		        UIManager.setLookAndFeel(
		            UIManager.getSystemLookAndFeelClassName());
		    } 
		    catch (UnsupportedLookAndFeelException e) {
		       // handle exception
		    }
		    catch (ClassNotFoundException e) {
		       // handle exception
		    }
		    catch (InstantiationException e) {
		       // handle exception
		    }
		    catch (IllegalAccessException e) {
		       // handle exception
		    }
		    
		File file = new File("db\\"+Database.dbName);
		boolean initialRun = !file.exists();	
		if(initialRun) {
			Database.build();	
		}
		
		//first action when program is run: Change to 1 to skip login screen
		
		 //gets screen resolution;
		//System.out.println(Home.screenSize);
		Home.run(-1);
		
			
		//Testing:
		//Housekeeping.housekeepingPanel.setVisible(true);
		//AddReservation.addReservationPanel.setVisible(true);
		
	}
	
}
	
	
		
	