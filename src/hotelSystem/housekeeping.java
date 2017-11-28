package hotelSystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Button;
import javax.swing.Box;
import java.awt.SystemColor;
import java.awt.ScrollPane;

public class Housekeeping {

	private JFrame frame;
	private JTextField txtHousekeeping;
	private JTextField txtRoomNo;
	private JTextField txtRoomType;
	private JTextField txtStatus;
	private JTextField txtAssign;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					housekeeping window = new housekeeping();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public housekeeping() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.textHighlight);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtHousekeeping = new JTextField();
		txtHousekeeping.setBackground(Color.YELLOW);
		txtHousekeeping.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		txtHousekeeping.setEditable(false);
		txtHousekeeping.setForeground(Color.BLACK);
		txtHousekeeping.setText("Housekeeping");
		txtHousekeeping.setBounds(0, 0, 111, 26);
		frame.getContentPane().add(txtHousekeeping);
		txtHousekeeping.setColumns(10);
		
		txtRoomNo = new JTextField();
		txtRoomNo.setText("Room No.");
		txtRoomNo.setForeground(Color.BLACK);
		txtRoomNo.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		txtRoomNo.setEditable(false);
		txtRoomNo.setColumns(10);
		txtRoomNo.setBackground(Color.MAGENTA);
		txtRoomNo.setBounds(0, 38, 111, 26);
		frame.getContentPane().add(txtRoomNo);
		
		txtRoomType = new JTextField();
		txtRoomType.setText("Room Type");
		txtRoomType.setForeground(Color.BLACK);
		txtRoomType.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		txtRoomType.setEditable(false);
		txtRoomType.setColumns(10);
		txtRoomType.setBackground(Color.MAGENTA);
		txtRoomType.setBounds(108, 38, 125, 26);
		frame.getContentPane().add(txtRoomType);
		
		txtStatus = new JTextField();
		txtStatus.setText("Status");
		txtStatus.setForeground(Color.BLACK);
		txtStatus.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		txtStatus.setEditable(false);
		txtStatus.setColumns(10);
		txtStatus.setBackground(Color.MAGENTA);
		txtStatus.setBounds(230, 38, 111, 26);
		frame.getContentPane().add(txtStatus);
		
		txtAssign = new JTextField();
		txtAssign.setText("Assign");
		txtAssign.setForeground(Color.BLACK);
		txtAssign.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		txtAssign.setEditable(false);
		txtAssign.setColumns(10);
		txtAssign.setBackground(Color.MAGENTA);
		txtAssign.setBounds(339, 38, 111, 26);
		frame.getContentPane().add(txtAssign);
		
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBounds(0, 61, 450, 217);
		frame.getContentPane().add(horizontalBox);
		
		ScrollPane scrollPane = new ScrollPane();
		horizontalBox.add(scrollPane);
	}
}
