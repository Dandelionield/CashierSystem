package Inventory;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.EventObject;

import javax.swing.DefaultCellEditor;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Main.Mecanics;
import Main.Menu;
import Main.Runner;
import Objects.Archivo;
import Objects.ComponentBuilder;
import Objects.Conexion;

public class history extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JLabel lblDate;
	private int theme = Mecanics.getMode();
	private boolean lenguaje=true;
	private Color[] Fondo= { new Color(238, 248, 254), new Color(20, 35, 54) };
	private final ComponentBuilder cp;
	private DefaultTableModel modelo;
	private JLabel hdate;
	private JLabel huser;
	private JLabel htype;
	private JLabel hnummodify;
	private JLabel hlote;
	private JLabel title;
	ArrayList<String> selected= new ArrayList<String>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					history frame = new history("","");
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
	public history(String User, String UserName) {
		if(Mecanics.getLanguage()==1) {
			lenguaje=false;
		}
		
		cp = new ComponentBuilder("./src/ResourcePackCaja", Fondo[theme]);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1013, 609);
		setIconImage(Toolkit.getDefaultToolkit().getImage("./src/ResourcePackCaja/Inventario.png"));
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final history thisFrame = this;
		
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
		
		JPanel panel = new JPanel();
		panel.setBackground(Fondo[theme]);
		panel.setBounds(40, 40, 917, 499);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblDate = new JLabel(lenguaje?"Cargando Fecha...":"Loading Date...");
		lblDate.setFont(new Font("Bernard MT Condensed", Font.PLAIN, 15));
		//lblDate.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblDate.setBounds(30, 22, 172, 50);
		Clock();
		panel.add(lblDate);
			
		JLabel lblMenu = new JLabel("");
		//lblMenu.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblMenu.setBounds(836, 22, 49, 50);
		Mecanics.lblphoto("./src/ResourcePackCaja/inventario.png", lblMenu);
		lblMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblMenu.setBounds(839, 25, 43, 44);
				Mecanics.lblphoto("./src/ResourcePackCaja/inventario.png", lblMenu);
				lblMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblMenu.setBounds(836, 22, 49, 50);
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
		
		hdate = new JLabel(lenguaje?"Fecha de modificación":"Modification date");
		hdate.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		hdate.setHorizontalAlignment(SwingConstants.CENTER);
//		hdate.setBorder(new LineBorder(new Color(0, 0, 0)));
		hdate.setBounds(30, 100, 172, 50);
		panel.add(hdate);
		
		huser = new JLabel(lenguaje?"Usuario":"User");
//		huser.setBorder(new LineBorder(new Color(0, 0, 0)));
		huser.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		huser.setHorizontalAlignment(SwingConstants.CENTER);
		huser.setBounds(201, 100, 172, 50);
		panel.add(huser);
		
		htype = new JLabel(lenguaje?"Accion":"Action");
		htype.setHorizontalAlignment(SwingConstants.CENTER);
		htype.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		htype.setBounds(370, 100, 172, 50);
		panel.add(htype);
		
		hnummodify = new JLabel(lenguaje?"Elementos modificados":"Modified elements");
//		hnummodify.setBorder(new LineBorder(new Color(0, 0, 0)));
		hnummodify.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		hnummodify.setHorizontalAlignment(SwingConstants.CENTER);
		hnummodify.setBounds(541, 100, 172, 50);
		panel.add(hnummodify);
		
		hlote = new JLabel(lenguaje?"Lote":"Batch");
//		hlote.setBorder(new LineBorder(new Color(0, 0, 0)));
		hlote.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		hlote.setHorizontalAlignment(SwingConstants.CENTER);
		hlote.setBounds(713, 100, 172, 50);
		panel.add(hlote);
		
		String[] titulos = { "", "", "", "", ""};
		
		cp.buildTable(titulos, cp.doBounds(30, 138, 855, 332));
		modelo = cp.getDefaultTableModel();
		table = new JTable();
		table.setBackground(Fondo[theme]);	
		table.setModel(modelo);
		table.addMouseListener(new MouseAdapter() {
			// @Override
			public void mouseClicked(MouseEvent evt) {
				
				Conexion a=new Conexion();
				
				try {

					if (evt.getClickCount() == 2) {

						JTable rec = (JTable) evt.getSource();

						String act=selected.get(rec.getSelectedRow());
						String date=rec.getModel().getValueAt(rec.getSelectedRow(), 0).toString();
						
//						System.out.println(rec.getSelectedRow());
						ResultSet res= a.consulta("SELECT * FROM Acciones WHERE `fecha`='" + date + "'");
						
						if(act.equals("DLT")) {
							
							String [] con= res.getString("content").split("//");
							
							Archivo prod=new Archivo(con[0],con[1],con[4],con[7],Float.parseFloat(con[6]),Float.parseFloat(con[2]),Float.parseFloat(con[3]),con[5],"");
							
							vision vis=new vision(prod,false);
							vis.setLocationRelativeTo(thisFrame);
							vis.setVisible(true);
							
						}
						
						
						if(act.equals("INS")||act.equals("UPT")) {
							
							String [] content=res.getString("content").split("//");
							boolean view=true;
							Archivo prod=invMecanics.muestra(content[0].toUpperCase().trim());
							
							if(prod==null) {
							String [] msj= {"Elemento no encontrado", "Item not found", "ADVERTENCIA", "WARNING"};
							JOptionPane.showMessageDialog(thisFrame, msj[Mecanics.getLanguage()],msj[(Mecanics.getLanguage()+2)],JOptionPane.WARNING_MESSAGE);
							}
							if(act.equals("INS")) {
								view=false;
							}
							if(prod!=null) {
							vision vis=new vision(prod,view);
							
							if(act.equals("UPT")) {
								vis.content= content;
							}
							
							vis.setLocationRelativeTo(thisFrame);
							vis.setVisible(true);
							}
						}
						
						if(act.equals("NL")) {
//							System.out.println("yes");
							
							Lotes lt=new Lotes(User,UserName,true);
							String [] con= res.getString("content").split("//");
							int add=0;
//							System.out.println("yes");
							for(int i=0;i<Integer.parseInt(res.getString("nelements"));i++) {
//								System.out.println(i);
								lt.sl.add(new Selected(con[0+add],con[1+add],Float.parseFloat(con[2+add]),Float.parseFloat(con[3+add])));
								add+=4;
							}
//							System.out.println("yes");
							lt.lote.setText(res.getString("lote"));
							lt.inicio(res.getString("fecha"));
							lt.setLocationRelativeTo(thisFrame);
							lt.setVisible(true);
							a.Close();
							dispose();
//							System.out.println("yes");
							
						}
						
						
						
						
						a.Close();
						
						

						repaint();

					}

				} catch (Exception e) {

					repaint();
				}

			}
		});
		mostrar();
		
		
		JScrollPane hscrollPane = cp.getJScrollPane();
		hscrollPane.setBounds(30, 138, 855, 332);
		hscrollPane.setViewportView(table);
		panel.add(hscrollPane);		
		
		title = new JLabel(lenguaje?"Historial de Acciones":"Stock History");
		//title.setBorder(new LineBorder(new Color(0, 0, 0)));
		title.setFont(new Font("Bernard MT Condensed", Font.PLAIN, 30));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(356, 22, 246, 50);
		panel.add(title);
		
		JLabel fondo = new JLabel("");
		fondo.setBounds(0, 0, 997, 570);
		Mecanics.lblphoto("./src/ResourcePackCaja/fondo.jpg", fondo);
		contentPane.add(fondo);
		
		changeMode();
	}
	
	private void changeMode() {

		if(theme==1) {
			
			lblDate.setForeground(new Color(238, 248, 254));
			hdate.setForeground(new Color(238, 248, 254));
			hlote.setForeground(new Color(238, 248, 254));
			hnummodify.setForeground(new Color(238, 248, 254));
			htype.setForeground(new Color(238, 248, 254));
			huser.setForeground(new Color(238, 248, 254));
			table.setForeground(new Color(238, 248, 254));
			title.setForeground(new Color(238, 248, 254));
			
		}
		
	}

	private void mostrar() {

		Conexion a = new Conexion();
		String Tipo="";
		try {

			ResultSet res = a.consulta("SELECT * FROM Acciones");

			while (res.next()) {
				selected.add(res.getString("tipo"));
				switch(res.getString("tipo")) {
				
				case "NL":
					if(lenguaje) {
						Tipo="Nuevo Lote";
					}else {
						Tipo="New Batch";
					}
					break;
					
				case "INS":
					if(lenguaje) {
						Tipo="Elemento Registrado";
					}else {
						Tipo="Registered Item";
					}
					break;
				
				case "UPT":
					if(lenguaje) {
						Tipo="Elemento Actualizado";
					}else {
						Tipo="Updated Item";
					}
					break;
				
				case "DLT":
					if(lenguaje) {
						Tipo="Elemento Eliminado";
					}else {
						Tipo="Deleted Item";
					}
					break;
					
				}

				Object[] products = { res.getString("fecha"), res.getString("user"), Tipo, res.getString("nelements"),
						res.getString("lote") };
				modelo.addRow(products);
				
			}

		} catch (Exception e) {
		}
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
	
		for (int i = 0; i < table.getColumnCount(); i++) {

			TableColumn column = table.getColumnModel().getColumn(i);
			
			column.setCellEditor(new DefaultCellEditor(new JTextField()) {

				
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(EventObject e) {

					return false;

				}
				

			});
			
			
			rightRenderer.setHorizontalAlignment(JLabel.CENTER);
			column.setCellRenderer(rightRenderer);
		
		}
		a.Close();
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
}
