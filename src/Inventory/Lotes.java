package Inventory;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventObject;

import javax.swing.AbstractListModel;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import Main.Mecanics;
import Main.Menu;
import Main.Runner;
import Objects.Archivo;
import Objects.ComponentBuilder;
import Objects.Conexion;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Lotes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected JTextField lote;
	private JTextField price;

	private int theme = Mecanics.getMode(true);
	private boolean lenguaje = true;
	private static JTable table;
	private Color[] Fondo = { new Color(238, 248, 254), new Color(20, 35, 54) };
	private final ComponentBuilder cp;
	private static DefaultTableModel modelo;
	private String ld = "Light";
	private ArrayList<String> dp = new ArrayList<String>(Arrays.asList(Actual.Listar(0, 0, 0)));
	private String[] disponibles = Actual.Listar(0, 0, 0);
	private JList<Object> list;
	private JPanel panel;
	private JLabel txtlote;
	private JLabel txtObt;
	private JLabel txtAct;
	private JLabel txtsoldprice;
	private JLabel txtprice;
	private JLabel txtgan;
	private JLabel volver;
	private JLabel gan;
	private JLabel soldprice;
	protected String fech = "";

	static ArrayList<Selected> sl = new ArrayList<Selected>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lotes frame = new Lotes("", "", false);
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
	public Lotes(String User, String UserName, boolean acceso) {

		if (Mecanics.getLanguage(true) == 1) {
			lenguaje = false;
		}

		cp = new ComponentBuilder("./src/ResourcePackCaja", Fondo[theme]);

		final Lotes thisFrame = this;

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				if (acceso == false) {

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

				if (acceso) {

					history hs = new history(User, UserName);
					hs.setLocationRelativeTo(thisFrame);
					hs.setVisible(true);
					sl.removeAll(sl);
					dispose();

				}
			}
		});

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 1013, 609);
		contentPane = new JPanel();
		// contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBackground(new Color(238, 248, 254));
		panel.setBounds(40, 40, 917, 499);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane lscrollList = new JScrollPane();
		lscrollList.setBounds(591, 49, 305, 439);
		panel.add(lscrollList);

		list = new JList<>();
		list.setBackground(new Color(238, 248, 254));
		AbstractListModel<Object> alm = new AbstractListModel<Object>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] values = disponibles;

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		};

		list.setModel(alm);

		list.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent evt) {

				try {

					if (evt.getClickCount() == 2) {

						String selected = list.getSelectedValue().toString();
						String[] div = selected.split("/");
						float precio = 0;

						if (div.length == 3) {
							precio = Float.parseFloat(div[2]);
						}

						sl.add(new Selected(div[0], div[1], 0, precio));
						modelo.setRowCount(0);
						mostrar();

						dp.remove(selected);
						reloadList();

						repaint();

					}

				} catch (Exception e) {

					repaint();
				}

			}

		});
		lscrollList.setViewportView(list);

		String[] titulos = { "Codigo", "Producto", "#Añadir" };

		if (!lenguaje) {
			titulos = new String[] { "Code", "Product", "#Add" };
		}

		cp.buildTable(titulos, cp.doBounds(250, 49, 305, 439));
		JScrollPane scrollTable = cp.getJScrollPane();
		scrollTable.setBounds(250, 49, 305, 439);
		panel.add(scrollTable);

		modelo = cp.getDefaultTableModel();
		table = new JTable();
		table.setBackground(new Color(238, 248, 254));
		table.addMouseListener(new MouseAdapter() {
			// @Override
			public void mouseClicked(MouseEvent evt) {

				try {

					if (evt.getClickCount() == 2) {

						JTable rec = (JTable) evt.getSource();

						int delete = rec.getSelectedRow();

						String element = rec.getModel().getValueAt(rec.getSelectedRow(), 0).toString() + " / "
								+ rec.getModel().getValueAt(rec.getSelectedRow(), 1).toString();

						if (invMecanics
								.muestra(rec.getModel().getValueAt(rec.getSelectedRow(), 0).toString()) == null) {
							element += " / " + sl.get(delete).getPrice();
						}

						dp.add(element);

						reloadList();
						sl.remove(delete);
						modelo.setRowCount(0);
						mostrar();
						repaint();

					}

				} catch (Exception e) {

					repaint();
				}

			}
		});
		table.setModel(modelo);
		modelo.addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int row = e.getFirstRow();
					int column = e.getColumn();
					TableModel model = (TableModel) e.getSource();
					Object data = model.getValueAt(row, column);

					float neodata = Float.parseFloat(data.toString());

					sl.get(row).setCantidad(neodata);

					ventas();

				}
			}
		});

		inicio("");

		scrollTable.setViewportView(table);

		soldprice = new JLabel("0");
		soldprice.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(128, 0, 255)));
		soldprice.setHorizontalAlignment(SwingConstants.CENTER);
		soldprice.setBounds(45, 383, 195, 23);
		panel.add(soldprice);

		txtlote = new JLabel(lenguaje ? "Lote: " : "Batch: ");
//		txtlote.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtlote.setBounds(45, 94, 72, 23);
		panel.add(txtlote);

		txtObt = new JLabel(lenguaje ? "Productos Seleccionados: " : "Selected Products: ");
//		txtObt.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtObt.setFont(new Font("Franklin Gothic Heavy", Font.PLAIN, 15));
		txtObt.setBounds(250, 15, 195, 23);
		panel.add(txtObt);

		txtAct = new JLabel(lenguaje ? "Productos Disponibles: " : "Available Products: ");
		// txtAct.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtAct.setFont(new Font("Franklin Gothic Heavy", Font.PLAIN, 15));
		txtAct.setBounds(591, 15, 195, 23);
		panel.add(txtAct);

		if (theme == 1) {
			ld = "Dark";
		}

		volver = new JLabel("");
		// volver.setBorder(new LineBorder(new Color(0, 0, 0)));
		volver.setBounds(25, 15, 50, 44);
		Mecanics.lblphoto("./src/ResourcePackCaja/back" + ld + ".png", volver);
		volver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				volver.setBounds(28, 18, 47, 41);
				Mecanics.lblphoto("./src/ResourcePackCaja/back" + ld + ".png", volver);
				volver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				volver.setBounds(25, 15, 50, 44);
				Mecanics.lblphoto("./src/ResourcePackCaja/back" + ld + ".png", volver);
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				if (acceso) {
					history hs = new history(User, UserName);
					hs.setLocationRelativeTo(thisFrame);
					hs.setVisible(true);
					dispose();
					sl.removeAll(sl);
				}

				if (acceso == false) {
					modify mdf = new modify(User, UserName);
					mdf.setLocationRelativeTo(thisFrame);
					mdf.setVisible(true);
					dispose();
					sl.removeAll(sl);
				}
			}
		});
		panel.add(volver);

		JLabel menu = new JLabel("");
//		menu.setBorder(new LineBorder(new Color(0, 0, 0)));
		menu.setBounds(174, 15, 50, 44);
		Mecanics.lblphoto("./src/ResourcePackCaja/inventario.png", menu);
		menu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				menu.setBounds(177, 18, 47, 41);
				Mecanics.lblphoto("./src/ResourcePackCaja/inventario.png", menu);
				menu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menu.setBounds(174, 15, 50, 44);
				Mecanics.lblphoto("./src/ResourcePackCaja/inventario.png", menu);
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				Inventario inv = new Inventario(User, UserName);
				inv.setLocationRelativeTo(thisFrame);
				inv.setVisible(true);
				sl.removeAll(sl);
				dispose();
			}
		});
		panel.add(menu);

		lote = cp.buildTextField("", cp.doBounds(98, 97, 126, 20), SwingConstants.LEFT,
				new Font("Microsoft JhengHei UI", Font.BOLD, 12), Color.BLUE, Color.BLUE, !acceso, true);
		panel.add(lote);

		txtsoldprice = new JLabel(lenguaje ? "Ventas estimadas: " : "Sales estimate: ");
//		txtsoldprice.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtsoldprice.setBounds(45, 349, 122, 23);
		panel.add(txtsoldprice);

		txtprice = new JLabel(lenguaje ? "Costo: " : "Cost: ");
//		txtprice.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtprice.setBounds(45, 150, 72, 23);
		panel.add(txtprice);

		price = cp.buildTextField("", cp.doBounds(45, 184, 179, 20), SwingConstants.LEFT,
				new Font("Microsoft JhengHei UI", Font.BOLD, 12), Color.BLUE, Color.BLUE, true, true);
		price.getDocument().addDocumentListener(new DocumentListener() {

			public void insertUpdate(DocumentEvent e) {
				verifyprice();
			}

			public void removeUpdate(DocumentEvent e) {
				verifyprice();
			}

			public void changedUpdate(DocumentEvent e) {
				verifyprice();
			}

			private void verifyprice() {

				try {

					float data = (Float.parseFloat(soldprice.getText()) - Float.parseFloat(price.getText()));
					gan.setText(data + "");

				} catch (Exception e) {
					gan.setText("0");

					int ln = 0;
					String[] error = { "Introducir sólo números", "Enter only numbers" };
					if (lenguaje == false) {
						ln = 1;
					}
					Mecanics.txtErrorMessage(price, error[ln]);
				}

			}
		});
		panel.add(price);

		gan = new JLabel("0");
		gan.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(128, 0, 255)));
		gan.setHorizontalAlignment(SwingConstants.CENTER);
		gan.setBounds(45, 451, 195, 23);
		panel.add(gan);

		txtgan = new JLabel(lenguaje ? "Ganancias estimadas: " : "Estimated earnings: ");
//		txtgan.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtgan.setBounds(45, 417, 151, 23);
		panel.add(txtgan);

		JButton confirmacion = new JButton(lenguaje ? "Confirmar" : "confirm");
		if (acceso) {
			confirmacion.setText(lenguaje ? "Editar" : "Edit");
		}

		confirmacion.setBounds(45, 248, 179, 44);
		confirmacion.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Conexion a = new Conexion();
				String Content = "";

				for (int i = 0; i < sl.size(); i++) {

					try {

						String code = modelo.getValueAt(i, 0).toString();
						Archivo producto = invMecanics.muestra(code);

						if (producto == null) {
//							System.out.println("SIUUUUUU");
							Content += sl.get(i).getCode() + "//" + sl.get(i).getName() + "//"
									+ modelo.getValueAt(i, 2).toString() + "//" + sl.get(i).getPrice();
						}

						if (producto != null) {
							float cantidad = producto.getAmount()
									+ Float.parseFloat(modelo.getValueAt(i, 2).toString());

							Content += producto.getCode() + "//" + producto.getProduct() + "//"
									+ modelo.getValueAt(i, 2).toString() + "//" + producto.getPrice();

							a.sentence("UPDATE `Inventario` SET  `Product`='" + producto.getProduct() + "', `Brand`='"
									+ producto.getBrand() + "', `Description`='" + producto.getDescription()
									+ "', `Amount`='" + cantidad + "',`Price`='" + producto.getPrice() + "',`Unid`='"
									+ producto.getUnid() + "',`Image`='" + producto.getImage() + "'  WHERE `Code`='"
									+ code + "';");

						}
						if (i != sl.size() - 1) {
							Content += "//";
						}

					} catch (Exception b) {
//						System.out.println("UIS: "+b);
						repaint();
					}

				}

				String usun = User + "/" + UserName;

				if (acceso) {
					try {
						a.sentence("UPDATE `Acciones` SET  `tipo`='" + "NL" + "', `fecha`='" + invMecanics.ActualDate()
								+ "', `user`='" + usun + "', `content`='" + Content + "',`lote`='" + lote.getText()
								+ "'  WHERE `fecha`='" + fech + "';");
					} catch (SQLException e1) {
//						System.out.println(e1);
					}
//					System.out.println("yes");

				}

				a.Close();

				if (acceso == false) {
					invMecanics.addAction("NL", sl.size(), invMecanics.ActualDate(), usun, Content, lote.getText());
				}
				modelo.setRowCount(0);
				list.setModel(alm);
				soldprice.setText("0");
				gan.setText("0");
				price.setText("");
				lote.setText("");
				sl.removeAll(sl);

				if (acceso) {
					history hs = new history(User, UserName);
					hs.setLocationRelativeTo(thisFrame);
					hs.setVisible(true);
					sl.removeAll(sl);
					dispose();
				}

			}
		});
		panel.add(confirmacion);

		JLabel fondo = new JLabel("");
		fondo.setBounds(0, 0, 997, 570);
		Mecanics.lblphoto("./src/ResourcePackCaja/fondo.jpg", fondo);
		contentPane.add(fondo);

		changeMode();

	}

	public void inicio(String fec) {

		fech = fec;

		try {

			for (int i = 0; i < sl.size(); i++) {

				Selected prod = sl.get(i);
				Object[] products = { prod.getCode(), prod.getName(), prod.getCantidad() };
				modelo.addRow(products);

				String rem = prod.getCode() + " / " + prod.getName() + " ";

				dp.remove(rem);
			}

			reloadList();

		} catch (Exception e) {
			System.out.println("uis: " + e);
		}

		for (int i = 0; i < table.getColumnCount() - 1; i++) {

			TableColumn column = table.getColumnModel().getColumn(i);

			column.setCellEditor(new DefaultCellEditor(new JTextField()) {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(EventObject e) {

					return false;

				}

			});

		}

	}

	protected void ventas() {

		double venta = 0;
		int row = 0;

		try {

			for (int i = 0; i < sl.size(); i++) {
				row = i;

				Archivo prod = invMecanics.muestra(modelo.getValueAt(i, 0).toString());

				if (prod == null) {
					venta += sl.get(i).getPrice() * Float.parseFloat(modelo.getValueAt(i, 2).toString());
				}

				if (prod != null) {
					venta += prod.getPrice() * Float.parseFloat(modelo.getValueAt(i, 2).toString());
				}
			}

		} catch (Exception e) {

			modelo.setValueAt(0, row, 2);
		}

		soldprice.setText(venta + "");
	}

	protected void reloadList() {

		disponibles = dp.toArray(new String[dp.size()]);

		AbstractListModel<Object> alm = new AbstractListModel<Object>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] values = disponibles;

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		};
		list.setModel(alm);

	}

	public static void mostrar() {

		try {

			for (int i = 0; i < sl.size(); i++) {

				Object[] products = null;
				try {

					Archivo prod = invMecanics.muestra(sl.get(i).getCode().trim());

					products = new Object[] { prod.getCode(), prod.getProduct(), sl.get(i).getCantidad() };

				} catch (Exception e) {

					products = new Object[] { sl.get(i).getCode(), sl.get(i).getName(), sl.get(i).getCantidad() };
				}

				modelo.addRow(products);

			}

		} catch (Exception e) {
			System.out.println("uis: " + e);
		}

		for (int i = 0; i < table.getColumnCount() - 1; i++) {

			TableColumn column = table.getColumnModel().getColumn(i);

			column.setCellEditor(new DefaultCellEditor(new JTextField()) {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(EventObject e) {

					return false;

				}

			});

		}
	}

	public void changeMode() {

		if (theme == 1) {

			panel.setBackground(new Color(20, 35, 54));
			list.setBackground(new Color(20, 35, 54));
			txtAct.setForeground(new Color(238, 248, 254));
			txtgan.setForeground(new Color(238, 248, 254));
			txtlote.setForeground(new Color(238, 248, 254));
			txtObt.setForeground(new Color(238, 248, 254));
			txtprice.setForeground(new Color(238, 248, 254));
			txtsoldprice.setForeground(new Color(238, 248, 254));
			list.setForeground(new Color(238, 248, 254));
			table.setBackground(new Color(20, 35, 54));
			table.setForeground(new Color(238, 248, 254));
			soldprice.setForeground(new Color(238, 248, 254));
			gan.setForeground(new Color(238, 248, 254));
			lote.setForeground(new Color(238, 248, 254));
			price.setForeground(new Color(238, 248, 254));
		}

	}

}

class Selected {

	private String code;
	private float cantidad;
	private String Name;
	private float price;

	public Selected(String code, String Name, float cantidad, float price) {
		this.code = code;
		this.Name = Name;
		this.cantidad = cantidad;
		this.price = price;
	}

	public String getName() {
		return Name;
	}

	public String getCode() {
		return code;
	}

	public float getCantidad() {
		return cantidad;
	}

	public float getPrice() {
		return price;
	}

	public void setCantidad(float neo) {
		cantidad = neo;
	}

	public Selected muestra(String code) {

		for (int i = 0; i < Lotes.sl.size(); i++) {

			if (code.equals(Lotes.sl.get(i).getCode())) {
				return Lotes.sl.get(i);
			}
		}

		return null;
	}

}
