package hotelSystem;

//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

public class CheckIn {
	
	static JPanel checkInPanel = new JPanel();
		
		static JLabel checkInLabel = new JLabel("Checking In: ");
		static JLabel checkOutLabel = new JLabel("Checking Out: ");
		static JTextArea checkInText = new JTextArea("hello");
		static JTextArea checkOutText = new JTextArea("goodbye");
		static JScrollPane checkInScrollPane = new JScrollPane(checkInText);
		static JScrollPane checkOutScrollPane = new JScrollPane(checkOutText);
		static JSplitPane checkInSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,checkInScrollPane,checkOutScrollPane);
			
	public static void checkInPanel() {
		
		checkInPanel.setLayout(new BoxLayout(checkInPanel,BoxLayout.PAGE_AXIS));
		checkInPanel.setBackground(Home.myColor);
		//checkInPanel.setPreferredSize(Home.panelSize);
			

			checkInLabel.setFont(checkInLabel.getFont().deriveFont(20f));

			checkOutLabel.setFont(checkOutLabel.getFont().deriveFont(20f));

			checkInSplitPane.setResizeWeight(0.5);
			
		checkInPanel.add(checkInLabel);
		checkInPanel.add(checkOutLabel);
		checkInPanel.add(checkInSplitPane);
		
		
	}
	
	/**private static class myActionListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event){
			
		
		}
	}
	*/
}
