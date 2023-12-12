package Inventory;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JTextField;

public class Lotes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable ltable;
	private JTextField lote;
	private JTextField price;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lotes frame = new Lotes();
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
	public Lotes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1013, 609);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(40, 40, 917, 499);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane lscrollPane = new JScrollPane();
		lscrollPane.setBounds(591, 49, 305, 439);
		panel.add(lscrollPane);
		
		ltable = new JTable();
		lscrollPane.setViewportView(ltable);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(215, 49, 305, 439);
		panel.add(scrollPane_1);
		
		JList llist = new JList();
		scrollPane_1.setViewportView(llist);
		
		JLabel soldprice = new JLabel("");
		soldprice.setBorder(new LineBorder(new Color(0, 0, 0)));
		soldprice.setBounds(10, 383, 195, 23);
		panel.add(soldprice);
		
		JLabel enter = new JLabel("");
		enter.setBorder(new LineBorder(new Color(0, 0, 0)));
		enter.setBounds(530, 182, 50, 50);
		panel.add(enter);
		
		JLabel outer = new JLabel("");
		outer.setBorder(new LineBorder(new Color(0, 0, 0)));
		outer.setBounds(530, 243, 50, 50);
		panel.add(outer);
		
		JLabel txtlote = new JLabel("");
		txtlote.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtlote.setBounds(10, 94, 72, 23);
		panel.add(txtlote);
		
		JLabel txtObt = new JLabel("");
		txtObt.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtObt.setBounds(215, 15, 195, 23);
		panel.add(txtObt);
		
		JLabel txtAct = new JLabel("");
		txtAct.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtAct.setBounds(591, 15, 195, 23);
		panel.add(txtAct);
		
		JLabel menu = new JLabel("");
		menu.setBorder(new LineBorder(new Color(0, 0, 0)));
		menu.setBounds(10, 15, 50, 44);
		panel.add(menu);
		
		lote = new JTextField();
		lote.setBounds(92, 97, 97, 20);
		panel.add(lote);
		lote.setColumns(10);
		
		JLabel txtsoldprice = new JLabel("");
		txtsoldprice.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtsoldprice.setBounds(10, 349, 72, 23);
		panel.add(txtsoldprice);
		
		JLabel txtprice = new JLabel("");
		txtprice.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtprice.setBounds(10, 150, 72, 23);
		panel.add(txtprice);
		
		price = new JTextField();
		price.setBounds(10, 184, 179, 20);
		panel.add(price);
		price.setColumns(10);
		
		JLabel gan = new JLabel("");
		gan.setBorder(new LineBorder(new Color(0, 0, 0)));
		gan.setBounds(10, 451, 195, 23);
		panel.add(gan);
		
		JLabel txtgan = new JLabel("");
		txtgan.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtgan.setBounds(10, 417, 72, 23);
		panel.add(txtgan);
	}
}
