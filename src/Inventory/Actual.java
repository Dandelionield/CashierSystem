package Inventory;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Main.Mecanics;
import Main.Menu;
import Main.Runner;
import Objects.ComponentBuilder;
import Objects.Conexion;

import javax.swing.AbstractListModel;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;

import java.beans.VetoableChangeListener;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.EventObject;
import java.beans.PropertyChangeEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyEvent;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;

public class Actual extends JFrame {

	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	private JTable table;
	private JLabel lblDate;
	private JLabel Title;
	private int theme = Mecanics.getMode(true);
	private boolean lenguaje=true;
	private Color[] Fondo= { new Color(238, 248, 254), new Color(20, 35, 54) };
	private final ComponentBuilder cp;
	private DefaultTableModel modelo;
	private JPanel panel;
	private String ld="Light";
	private JList list;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Actual frame = new Actual("","");
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
	public Actual(String User,String UserName) {
		
		if(Mecanics.getLanguage(true)==1) {
			lenguaje=false;
		}
		
		if(Mecanics.getMode(true)==1) {
			ld="Dark";
		}
		
		cp = new ComponentBuilder("./src/ResourcePackCaja", Fondo[theme]);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1013, 609);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final Actual thisFrame = this;

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				boolean scp = Mecanics.Leave(thisFrame);

				if (scp == true) {

					Runner lg = new Runner();

					Runner.contentPane.removeAll();

					Runner.Opciones = new Menu(lg, User, UserName);

					Runner.contentPane.add(Runner.Opciones, Integer.valueOf(0));

					lg.setVisible(true);

					dispose();

					repaint();
				}

			}
		});
		
		panel = new JPanel();
		panel.setBackground(new Color(238, 248, 254));
		panel.setBounds(40, 40, 917, 499);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblDate = new JLabel(lenguaje?"Cargando Fecha...":"Loading Date...");
		lblDate.setFont(new Font("ARIAL", Font.BOLD, 15));
		//lblDate.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblDate.setBounds(20, 29, 163, 50);
		panel.add(lblDate);
		Clock();
		
		
		Title = new JLabel(lenguaje? "INVENTARIO ACTUAL": "CURRENT INVENTORY");
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setFont(new Font("Segoe Script", Font.BOLD | Font.ITALIC, 25));
		//Title.setBorder(new LineBorder(new Color(0, 0, 0)));
		Title.setBounds(293, 29, 328, 50);
		panel.add(Title);
		
		JLabel lblImprimir = new JLabel("");
		//lblImprimir.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblImprimir.setBounds(712, 29, 55, 50);
		Mecanics.lblphoto("./src/ResourcePackCaja/pdf"+ld+".png", lblImprimir);
		lblImprimir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblImprimir.setBounds(717, 34, 45, 40);
				Mecanics.lblphoto("./src/ResourcePackCaja/pdf"+ld+".png", lblImprimir);
				lblImprimir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblImprimir.setBounds(712, 29, 55, 50);
				Mecanics.lblphoto("./src/ResourcePackCaja/pdf"+ld+".png", lblImprimir);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				invMecanics.reporte1();
			}
		});
		panel.add(lblImprimir);
		
		
		JLabel lblFiltro = new JLabel("");
		//lblFiltro.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblFiltro.setBounds(777, 29, 55, 50);
		Mecanics.lblphoto("./src/ResourcePackCaja/filtrar"+ld+".png", lblFiltro);
		lblFiltro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblFiltro.setBounds(782, 34, 45, 40);
				Mecanics.lblphoto("./src/ResourcePackCaja/filtrar"+ld+".png", lblFiltro);
				lblFiltro.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblFiltro.setBounds(777, 29, 55, 50);
				Mecanics.lblphoto("./src/ResourcePackCaja/filtrar"+ld+".png", lblFiltro);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//proximamente//Coming soon
			}
		});
		panel.add(lblFiltro);
		
		JLabel lblMenu = new JLabel("");
		//lblMenu.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblMenu.setBounds(840, 27, 57, 52);
		Mecanics.lblphoto("./src/ResourcePackCaja/inventario.png", lblMenu);
		lblMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblMenu.setBounds(847, 34, 45, 40);
				Mecanics.lblphoto("./src/ResourcePackCaja/inventario.png", lblMenu);
				lblMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblMenu.setBounds(842, 29, 55, 50);
				Mecanics.lblphoto("./src/ResourcePackCaja/inventario.png", lblMenu);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Inventario inv=new Inventario(User,UserName);
				inv.setLocationRelativeTo(null);
				inv.setVisible(true);
				dispose();
			}
		});
		panel.add(lblMenu);
		
		String[] titulos = { "Codigo", "Producto", "Precio", "Existencias", "Marca" };
		
		if(!lenguaje) {
			titulos =new String[] { "Code", "Product", "Price", "Amount", "Brand" };
		}

		cp.buildTable(titulos, cp.doBounds(20, 101, 877, 377));
		modelo = cp.getDefaultTableModel();
		table =  new JTable();
		table.setBackground(new Color(238, 248, 254));
		table.setModel(modelo);
		mostrar();
		
		JScrollPane scrollPane =cp.getJScrollPane();
		scrollPane.setBounds(20, 101, 877, 377);
		scrollPane.setViewportView(table);
		panel.add(scrollPane);
		
		
		
		JLabel fondo = new JLabel("");
		fondo.setBounds(0, 0, 997, 570);
		Mecanics.lblphoto("./src/ResourcePackCaja/fondo.jpg", fondo);
		contentPane.add(fondo);
		
	
		
		list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = Listar();
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBorder(new LineBorder(Color.black));
		list.setBackground(new Color(238, 248, 254));
		list.addMouseListener(new MouseAdapter() {
		
			public void mouseClicked(MouseEvent evt) {

				try {

					if (evt.getClickCount() == 2) {

						String selected= list.getSelectedValue().toString();
						String [] div=selected.split("/");
						String code=div[0];

						vision vis=new vision(code.trim());
						vis.setLocationRelativeTo(thisFrame);
						vis.setVisible(true);

						repaint();

					}

				} catch (Exception e) {

					repaint();
				}

			}
		
		});
		scrollPane.setRowHeaderView(list);
		
		ChangeMode();
	}
	
	private void mostrar() {
		Conexion a = new Conexion();

		try {

			ResultSet res = a.consulta("SELECT * FROM Inventario");

			while (res.next()) {

				Object[] products = { res.getString("Code"), res.getString("Product"), res.getString("Price"),
						res.getString("Amount"), res.getString("Brand") };
				modelo.addRow(products);

			}

		} catch (Exception e) {
		}

		for (int i = 0; i < table.getColumnCount(); i++) {

			TableColumn column = table.getColumnModel().getColumn(i);

			column.setCellEditor(new DefaultCellEditor(new JTextField()) {

				public boolean isCellEditable(EventObject e) {

					return false;

				}
				

			});
			
		
		}
		
		
		
		
	}
	
	private String[] Listar() {
		Conexion a = new Conexion();
		
		String[] lista= {};
		ArrayList<String> productos=new ArrayList<>();
		
		try {

			ResultSet res = a.consulta("SELECT * FROM Inventario");

			while (res.next()) {
				
				productos.add( res.getString("Code")+" / "+res.getString("Product")+" ");

			}

		} catch (Exception e) {
		}
		
		
		lista = productos.toArray(new String[productos.size()]);
		
		return lista;
	}

	public void Clock() {
			
		
		ActionListener TimerDate = new ActionListener(){
			
            public void actionPerformed (ActionEvent e){
				
                LocalDateTime Hora = LocalDateTime.now();
                String formattedDateTime = Hora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss"));
                lblDate.setText(formattedDateTime);
				
				repaint();
				
            }
			
        }; 
		Timer timer = new Timer(1000, TimerDate);
        timer.start();
	}
	
	public void ChangeMode() {
		
		if(theme==1) {
			panel.setBackground(new Color(20, 35, 54));
			Title.setForeground(new Color(238, 248, 254));
			lblDate.setForeground(new Color(238, 248, 254));
			table.setForeground(Color.WHITE);
			table.setBackground(new Color(20, 35, 54));
			list.setBackground(new Color(20, 35, 54));
			list.setForeground(new Color(238, 248, 254));
			list.setBorder(new LineBorder(Color.white));
			
		}
		
	}
}
