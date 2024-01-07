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

import javax.swing.border.LineBorder;

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
import Objects.Archivo;
import Objects.ComponentBuilder;
import Objects.Conexion;

import javax.swing.AbstractListModel;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EventObject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;

public class Actual extends JFrame {

	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	private JTable table;
	private JLabel lblDate;
	private JLabel Title;
	private int theme = Mecanics.getMode(true);
	private boolean lenguaje = true;
	private Color[] Fondo = { new Color(238, 248, 254), new Color(20, 35, 54) };
	private final ComponentBuilder cp;
	private DefaultTableModel modelo;
	private JPanel panel;
	private String ld = "Light";
	private JList<Object> list;

	protected static int fitroventa = 0;
	protected static int fitronombre = 0;
	protected static int fitrocodigo = 0;

	protected static boolean filterActive = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Actual frame = new Actual("", "");
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
	public Actual(String User, String UserName) {

		if (Mecanics.getLanguage(true) == 1) {
			lenguaje = false;
		}

		if (Mecanics.getMode(true) == 1) {
			ld = "Dark";
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

		lblDate = new JLabel(lenguaje ? "Cargando Fecha..." : "Loading Date...");
		lblDate.setFont(new Font("ARIAL", Font.BOLD, 15));
		// lblDate.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblDate.setBounds(20, 29, 163, 50);
		panel.add(lblDate);
		Clock();

		Title = new JLabel(lenguaje ? "INVENTARIO ACTUAL" : "CURRENT INVENTORY");
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setFont(new Font("Segoe Script", Font.BOLD | Font.ITALIC, 25));
		// Title.setBorder(new LineBorder(new Color(0, 0, 0)));
		Title.setBounds(293, 29, 328, 50);
		panel.add(Title);

		JLabel lblImprimir = new JLabel("");
		// lblImprimir.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblImprimir.setBounds(712, 29, 55, 50);
		Mecanics.lblphoto("./src/ResourcePackCaja/pdf" + ld + ".png", lblImprimir);
		lblImprimir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblImprimir.setBounds(717, 34, 45, 40);
				Mecanics.lblphoto("./src/ResourcePackCaja/pdf" + ld + ".png", lblImprimir);
				lblImprimir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblImprimir.setBounds(712, 29, 55, 50);
				Mecanics.lblphoto("./src/ResourcePackCaja/pdf" + ld + ".png", lblImprimir);
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				invMecanics.reporte1();
			}
		});
		panel.add(lblImprimir);

		JLabel lblFiltro = new JLabel("");
		// lblFiltro.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblFiltro.setBounds(777, 29, 55, 50);
		Mecanics.lblphoto("./src/ResourcePackCaja/filtrar" + ld + ".png", lblFiltro);
		lblFiltro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblFiltro.setBounds(782, 34, 45, 40);
				Mecanics.lblphoto("./src/ResourcePackCaja/filtrar" + ld + ".png", lblFiltro);
				lblFiltro.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblFiltro.setBounds(777, 29, 55, 50);
				Mecanics.lblphoto("./src/ResourcePackCaja/filtrar" + ld + ".png", lblFiltro);
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				if (filterActive == false) {
					emergente filtro = new emergente(lenguaje);
					filtro.setLocationRelativeTo(thisFrame);
					filtro.setVisible(true);
					filterActive = true;
				}

			}
		});

		panel.add(lblFiltro);

		JLabel lblMenu = new JLabel("");
		// lblMenu.setBorder(new LineBorder(new Color(0, 0, 0)));
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

				Inventario inv = new Inventario(User, UserName);
				inv.setLocationRelativeTo(null);
				inv.setVisible(true);
				dispose();
			}
		});
		panel.add(lblMenu);

		String[] titulos = { "Codigo", "Producto", "Precio", "Existencias", "Marca" };

		if (!lenguaje) {
			titulos = new String[] { "Code", "Product", "Price", "Amount", "Brand" };
		}

		cp.buildTable(titulos, cp.doBounds(20, 101, 877, 377));
		modelo = cp.getDefaultTableModel();
		table = new JTable();
		table.setBackground(new Color(238, 248, 254));
		table.setModel(modelo);
		mostrar();

		JScrollPane scrollPane = cp.getJScrollPane();
		scrollPane.setBounds(20, 101, 877, 377);
		scrollPane.setViewportView(table);
		panel.add(scrollPane);

		JLabel fondo = new JLabel("");
		fondo.setBounds(0, 0, 997, 570);
		Mecanics.lblphoto("./src/ResourcePackCaja/fondo.jpg", fondo);
		contentPane.add(fondo);

		list = new JList<>();

		AbstractListModel<Object> alm = new AbstractListModel<Object>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] values = Listar(0, 0, 0);

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		};

		list.setModel(alm);
		list.setBorder(new LineBorder(Color.black));
		list.setBackground(new Color(238, 248, 254));
		list.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent evt) {

				try {

					if (evt.getClickCount() == 2) {

						String selected = list.getSelectedValue().toString();
						String[] div = selected.split("/");
						String code = div[0].trim();

						vision vis = new vision(invMecanics.muestra(code), false);
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

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(EventObject e) {

					return false;

				}

			});

		}

		a.Close();

	}

	static String[] Listar(int fc, int fn, int fv) {
		Conexion a = new Conexion();

		String[] lista = {};
		ArrayList<Archivo> productos = new ArrayList<>();
		ArrayList<String> med = new ArrayList<>();

		try {

			ResultSet res = a.consulta("SELECT * FROM Inventario");

			while (res.next()) {

				// productos.add( res.getString("Code")+" / "+res.getString("Product")+" ");
				productos.add(invMecanics.muestra(res.getString("Code")));
			}

		} catch (Exception e) {
		}

		if (fc == 1) {
			Collections.sort(productos, new Comparator<Archivo>() {
				@Override
				public int compare(Archivo p1, Archivo p2) {
					return p1.getCode().compareTo(p2.getCode());
				}
			});

		}
		if (fn == 1) {
			Collections.sort(productos, new Comparator<Archivo>() {
				@Override
				public int compare(Archivo p1, Archivo p2) {
					return p1.getProduct().compareTo(p2.getProduct());
				}
			});

		}

		if (fv == 1) {
			Collections.sort(productos, new Comparator<Archivo>() {
				@Override
				public int compare(Archivo p1, Archivo p2) {
					return Float.compare(p2.getSold(), p1.getSold());
				}
			});

		}

		if (fc == 2) {
			Collections.sort(productos, new Comparator<Archivo>() {
				@Override
				public int compare(Archivo p1, Archivo p2) {
					return p1.getCode().compareTo(p2.getCode());
				}
			});

			Collections.reverse(productos);
		}

		if (fn == 2) {
			Collections.sort(productos, new Comparator<Archivo>() {
				@Override
				public int compare(Archivo p1, Archivo p2) {
					return p1.getProduct().compareTo(p2.getProduct());
				}
			});

			Collections.reverse(productos);
		}

		if (fv == 2) {
			Collections.sort(productos, new Comparator<Archivo>() {
				@Override
				public int compare(Archivo p1, Archivo p2) {
					return Float.compare(p2.getSold(), p1.getSold());
				}
			});

			Collections.reverse(productos);
		}

		for (int i = 0; i < productos.size(); i++) {

			med.add(productos.get(i).getCode() + " / " + productos.get(i).getProduct() + " ");
		}

		lista = med.toArray(new String[med.size()]);

		a.Close();

		return lista;
	}

	public void Clock() {

		ActionListener TimerDate = new ActionListener() {

			public void actionPerformed(ActionEvent e) {

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

		if (theme == 1) {
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

	class emergente extends JFrame {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JPanel content;
		private JComboBox<String> cmbnombre;
		private JComboBox<String> cmbventas;
		private JComboBox<String> cmbcode;

		public emergente(boolean lenguaje) {

			setResizable(false);
			setAlwaysOnTop(true);
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			setBounds(100, 100, 300, 300);
			content = new JPanel();
			content.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(content);
			content.setLayout(null);

			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {

					filterActive = false;
					dispose();

				}
			});

			JLabel txttitle = new JLabel(lenguaje ? "Filtros" : "Filters");
			txttitle.setBounds(109, 11, 59, 23);
			txttitle.setFont(new Font("Futura Md BT", Font.PLAIN, 20));
			// txttitle.setBorder(new LineBorder(new Color(0, 0, 0)));
			content.add(txttitle);

			JLabel lblVentas = new JLabel(lenguaje ? "Ventas: " : "Sales: ");
			// lblVentas.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblVentas.setBounds(40, 121, 59, 23);
			content.add(lblVentas);

			String[] fv = { "Desactivado", "Mayor a menor", "Menor a mayor" };
			String[] fn = { "Desactivado", "A-Z", "Z-A" };
			String[] fc = { "Desactivado", "A-Z", "Z-A" };

			if (lenguaje == false) {

				fv = new String[] { "Off", "High to low", "Low to high" };
				fn = new String[] { "Off", "A - Z", "Z - A" };
				fc = new String[] { "Off", "A - Z", "Z - A" };
			}

			cmbventas = new JComboBox<String>();
			cmbventas.setBounds(109, 121, 125, 23);
			cmbventas.setModel(new DefaultComboBoxModel<String>(fv));
			cmbventas.setSelectedIndex(fitroventa);
			content.add(cmbventas);

			JLabel lblNombre = new JLabel(lenguaje ? "Nombre: " : "Name: ");
			// lblNombre.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblNombre.setBounds(40, 75, 59, 23);
			content.add(lblNombre);

			cmbnombre = new JComboBox<String>();
			cmbnombre.setBounds(109, 75, 125, 23);
			cmbnombre.setModel(new DefaultComboBoxModel<String>(fn));
			cmbnombre.setSelectedIndex(fitronombre);
			content.add(cmbnombre);

			JLabel lblCodigo = new JLabel(lenguaje ? "Codigo: " : "Code: ");
			// lblCodigo.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblCodigo.setBounds(40, 163, 59, 23);
			content.add(lblCodigo);

			cmbcode = new JComboBox<String>();
			cmbcode.setBounds(109, 163, 125, 23);
			cmbcode.setModel(new DefaultComboBoxModel<String>(fc));
			cmbcode.setSelectedIndex(fitrocodigo);
			content.add(cmbcode);

			JButton apply = new JButton(lenguaje ? "Aplicar" : "Apply");
			apply.setBounds(97, 212, 89, 23);
			apply.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					Actual.fitronombre = cmbnombre.getSelectedIndex();
					Actual.fitrocodigo = cmbcode.getSelectedIndex();
					Actual.fitroventa = cmbventas.getSelectedIndex();

					AbstractListModel<Object> alm = new AbstractListModel<Object>() {
						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;
						String[] values = Listar(Actual.fitrocodigo, Actual.fitronombre, Actual.fitroventa);

						public int getSize() {
							return values.length;
						}

						public Object getElementAt(int index) {
							return values[index];
						}
					};

					list.setModel(alm);

					filterActive = false;
					dispose();
				}
			});
			content.add(apply);

		}
	}
}
