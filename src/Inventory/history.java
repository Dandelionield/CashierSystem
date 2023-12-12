package Inventory;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class history extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable htable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					history frame = new history();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public history() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1013, 609);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.highlight"));
		panel.setBounds(40, 40, 917, 499);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblDate = new JLabel("");
		lblDate.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblDate.setBounds(30, 22, 172, 50);
		panel.add(lblDate);
		
		JLabel menu = new JLabel("");
		menu.setBorder(new LineBorder(new Color(0, 0, 0)));
		menu.setBounds(836, 22, 49, 50);
		panel.add(menu);
		
		JLabel hdate = new JLabel("");
		hdate.setBorder(new LineBorder(new Color(0, 0, 0)));
		hdate.setBounds(30, 100, 205, 50);
		panel.add(hdate);
		
		JLabel huser = new JLabel("");
		huser.setBorder(new LineBorder(new Color(0, 0, 0)));
		huser.setBounds(245, 100, 201, 50);
		panel.add(huser);
		
		JLabel hnummodify = new JLabel("");
		hnummodify.setBorder(new LineBorder(new Color(0, 0, 0)));
		hnummodify.setBounds(456, 100, 201, 50);
		panel.add(hnummodify);
		
		JLabel hlote = new JLabel("");
		hlote.setBorder(new LineBorder(new Color(0, 0, 0)));
		hlote.setBounds(667, 100, 218, 50);
		panel.add(hlote);
		
		JScrollPane hscrollPane = new JScrollPane();
		hscrollPane.setBounds(30, 161, 855, 309);
		panel.add(hscrollPane);
		
		htable = new JTable();
		htable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		hscrollPane.setViewportView(htable);
		
		JLabel title = new JLabel("");
		title.setBorder(new LineBorder(new Color(0, 0, 0)));
		title.setBounds(356, 22, 246, 50);
		panel.add(title);
	}
}
