package Inventory;

import java.awt.EventQueue;
import Objects.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.SwingConstants;

import java.awt.Color;


import java.util.EventObject;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JComboBox;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Main.Mecanics;
import Main.Menu;
import Main.Runner;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;


public class modify extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					modify frame = new modify("", "Beta Tester");
					frame.setLocationRelativeTo(null);
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

	// private static final long serialVersionUID = 1L;

	private JPanel contentPane, panel;

	private JLabel txttabla, txtcodigo, txtdescripcion, txtunidad, txtmarca, txtexistencia, txtproductos, txtprecio,
			txtInformacion, txtidioma, txtunpopular, txtpopular, txtsold;
	private JLabel editar;
	private JLabel foto;
	private JLabel sold;
	private JLabel popular;
	private JLabel unpopular;

	private JTextField codigo, producto, precio, existencias, marca;

	private JButton btnEliminar, btnimage, btnguardar, imprimir;

	private JScrollPane scrolldescripcion;
	private JTextArea descripcion;

	static boolean access = false;
	static String[] len = new String[20];
	public static int theme = 0, lenguaje = 0;
	private String ruta = "";

	private JTable table;
	private DefaultTableModel modelo;

	private ImageIcon candadoIcon;
	private JComboBox<String> unidad;

	private final Color[] Fondo = { new Color(238, 248, 254), new Color(20, 35, 54) };
	private final ComponentBuilder cp;

	private Archivo[] mayor;

	public modify(String User, String UserName) {

		theme = Mecanics.getMode(true);
		lenguaje = Mecanics.getLanguage(true);

		cp = new ComponentBuilder("./src/ResourcePackCaja", Fondo[theme]);

		if (theme == 1) {
			cp.setForeground(Color.WHITE);
		}

		setIconImage(Toolkit.getDefaultToolkit().getImage("./src/ResourcePackCaja/Inventario.png"));

		setResizable(false);
		setTitle(len[19]);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1013, 609);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		final modify thisFrame = this;

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

		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(56, 24, 903, 504);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel fondo = new JLabel("");
		fondo.setBounds(0, 0, 1013, 609);
		ImageIcon imgfondo = new ImageIcon(new ImageIcon("./src/ResourcePackCaja/Fondo.JPG").getImage().getScaledInstance(fondo.getWidth(), fondo.getHeight(), Image.SCALE_SMOOTH));	
		fondo.setIcon(imgfondo);
		contentPane.add(fondo);

		foto = new JLabel("");
		foto.setBounds(25, 25, 204, 176);
		ImageIcon pfoto = new ImageIcon(new ImageIcon("./src/ResourcePackCaja/image-not-found.png").getImage().getScaledInstance(foto.getWidth(), foto.getHeight(), Image.SCALE_SMOOTH));	
		foto.setIcon(pfoto);
		foto.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(foto);

		String[] titulos = { len[2], len[3], len[4], len[5], len[7] };

		cp.buildTable(titulos, cp.doBounds(512, 62, 381, 431));

		modelo = cp.getDefaultTableModel();
		table = cp.getJTable();
		JScrollPane scrolltabla = cp.getJScrollPane();

		table.setBackground(Fondo[theme]);
		if (theme == 1) {
			table.setForeground(Color.WHITE);
		}

		table.addMouseListener(new MouseAdapter() {
			// @Override
			public void mouseClicked(MouseEvent evt) {

				try {

					if (evt.getClickCount() == 1) {

						JTable rec = (JTable) evt.getSource();

						codigo.setText(rec.getModel().getValueAt(rec.getSelectedRow(), 0).toString());

						fill(codigo.getText());

						repaint();

					}

				} catch (Exception e) {

					repaint();
				}

			}
		});

		mostrar();

		panel.add(scrolltabla);

		JLabel foto = this.foto;
		txtInformacion = new JLabel(len[0]);
		txtInformacion.setBounds(262, 21, 191, 26);
		txtInformacion.setFont(new java.awt.Font("Arial", 3, 14));
		panel.add(txtInformacion);

		txttabla = new JLabel(len[1]);
		txttabla.setBounds(512, 25, 218, 21);
		txttabla.setFont(new java.awt.Font("Arial", 3, 14));
		panel.add(txttabla);

		txtcodigo = new JLabel(len[2]);
		txtcodigo.setBounds(262, 61, 60, 26);
		txtcodigo.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 12));
		panel.add(txtcodigo);

		codigo = cp.buildTextField("", cp.doBounds(344, 65, 86, 20), SwingConstants.LEFT,new Font("Microsoft JhengHei UI", Font.BOLD, 12), Color.BLUE, Color.BLUE, true, true);			
		codigo.getDocument().addDocumentListener(new DocumentListener() {

			public void insertUpdate(DocumentEvent e) {
				verifycode();
			}

			public void removeUpdate(DocumentEvent e) {
				verifycode();
			}

			public void changedUpdate(DocumentEvent e) {
				verifycode();
			}

			private void verifycode() {

				ResultSet res = new Conexion()
						.consulta("SELECT * FROM Inventario WHERE `Code`='" + codigo.getText().toUpperCase() + "'");

				try {
					if (codigo.getText().equalsIgnoreCase(res.getString("Code"))) {

						if (access == false) {
							String[] error = { "Codigo ya registrado", "Code already registered" };
							codigo.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
							Mecanics.txtErrorMessage(codigo, error[lenguaje]);
						}
						fill(codigo.getText().toUpperCase());
					}
					res.close();

				} catch (SQLException e) {

					codigo.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
					fill(codigo.getText().toUpperCase());
				}
				codigo.repaint();

			}

		});
		panel.add(codigo);
		codigo.setColumns(10);

		txtproductos = new JLabel(len[3]);
		txtproductos.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		txtproductos.setBounds(262, 98, 72, 26);
		panel.add(txtproductos);

		producto = cp.buildTextField("", cp.doBounds(344, 102, 144, 20), SwingConstants.LEFT,new Font("Microsoft JhengHei UI", Font.BOLD, 12), Color.BLUE, Color.BLUE, true, true);		
		panel.add(producto);
		producto.setColumns(10);

		txtprecio = new JLabel(len[4]);
		txtprecio.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		txtprecio.setBounds(262, 133, 72, 26);
		panel.add(txtprecio);

		precio = cp.buildTextField("", cp.doBounds(344, 137, 144, 20), SwingConstants.LEFT,new Font("Microsoft JhengHei UI", Font.BOLD, 12), Color.BLUE, Color.BLUE, true, true);	
		panel.add(precio);
		precio.getDocument().addDocumentListener(new DocumentListener() {

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

				if (Mecanics.Allowed(precio.getText()) == false) {
					String[] error = { "El campo debe ser sólo números", "The field must be only numbers" };
					precio.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
					Mecanics.txtErrorMessage(precio, error[lenguaje]);

				}
				if (Mecanics.Allowed(precio.getText()) == true || precio.getText().equals("")) {
					precio.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
				}
			}

		});
		precio.setColumns(10);

		txtexistencia = new JLabel(len[5]);
		txtexistencia.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		txtexistencia.setBounds(262, 170, 72, 26);
		panel.add(txtexistencia);

		existencias = cp.buildTextField("", cp.doBounds(344, 174, 144, 20), SwingConstants.LEFT,new Font("Microsoft JhengHei UI", Font.BOLD, 12), Color.BLUE, Color.BLUE, true, true);		
		panel.add(existencias);
		existencias.getDocument().addDocumentListener(new DocumentListener() {

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

				if (Mecanics.Allowed(existencias.getText()) == false) {
					String[] error = { "El campo debe ser sólo números", "The field must be only numbers" };
					existencias.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
					Mecanics.txtErrorMessage(existencias, error[lenguaje]);
				}
				if (Mecanics.Allowed(existencias.getText()) == true || existencias.getText().equals("")) {
					existencias.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
				}
			}

		});
		existencias.setColumns(10);

		txtunidad = new JLabel(len[6]);
		txtunidad.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		txtunidad.setBounds(262, 207, 72, 26);
		panel.add(txtunidad);

		DefaultComboBoxModel<String> modl = new DefaultComboBoxModel<>(new String[] { "U", "Kg", "g", "L" });
		unidad = new JComboBox<>(modl);
		unidad.setBounds(344, 213, 60, 22);
		panel.add(unidad);

		txtmarca = new JLabel(len[7]);
		txtmarca.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		txtmarca.setBounds(262, 242, 72, 26);
		panel.add(txtmarca);

		marca = cp.buildTextField("", cp.doBounds(344, 246, 144, 20), SwingConstants.LEFT,new Font("Microsoft JhengHei UI", Font.BOLD, 12), Color.BLUE, Color.BLUE, true, true);		
		panel.add(marca);
		marca.setColumns(10);

		txtdescripcion = new JLabel(len[8]);
		txtdescripcion.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		txtdescripcion.setBounds(262, 280, 72, 26);
		panel.add(txtdescripcion);

		descripcion = new JTextArea();
		descripcion.setBounds(262, 317, 226, 176);
		descripcion.setLineWrap(true);
		descripcion.setWrapStyleWord(true);
		descripcion.setBorder(new LineBorder(new Color(0, 0, 0)));
		JScrollPane scrollarea = new JScrollPane(descripcion);
		scrollarea.setBounds(262, 317, 226, 176);
		panel.add(scrollarea);

		btnimage = new JButton(len[9]);
		btnimage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent d) {

				File archivo = Mecanics.actionimage(panel);

				if (archivo != null) {

					ruta = archivo.getAbsolutePath();

					ImageIcon ima = new ImageIcon(new ImageIcon(ruta).getImage().getScaledInstance(foto.getWidth(),
							foto.getHeight(), Image.SCALE_SMOOTH));
					foto.setIcon(ima);
					foto.repaint();
				}

			}
		});
		btnimage.setBounds(25, 200, 204, 23);
		panel.add(btnimage);

		btnguardar = new JButton(len[10]);
		btnguardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean limpieza = true;

				if (access == false) {

					Conexion a = new Conexion();

					try {

						Archivo prod = new Archivo(codigo.getText(), producto.getText(), marca.getText(),
								descripcion.getText(), Float.parseFloat(existencias.getText()), 0,
								Float.parseFloat(precio.getText()), unidad.getSelectedItem().toString(),
								"./src/Inventory/productPhoto/" + codigo.getText() + "."
										+ Mecanics.getExtension(new File(ruta)));

						a.sentence(
								"INSERT INTO `Inventario` (`Code`, `Product`, `Brand`, `Description`, `Amount`, `Sold`,`Price`,`Unid`,`Image`) "
										+ "VALUES ('" + prod.getCode().toUpperCase() + "', '" + prod.getProduct()
										+ "', '" + prod.getBrand() + "', '" + prod.getDescription() + "', '"
										+ prod.getAmount() + "', 0, '" + prod.getPrice() + "', '" + prod.getUnid()
										+ "', '" + prod.getImage() + "');");

						Mecanics.iArchivo(ruta, "./src/Inventory/productPhoto", codigo.getText().toUpperCase());

					} catch (Exception b) {

						limpieza = false;
						repaint();
					}
				}

				if (access == true) {

					Conexion a = new Conexion();

					try {

						String imgurl = "./src/Inventory/productPhoto/" + codigo.getText() + "."
								+ Mecanics.getExtension(new File(ruta));

						a.sentence("UPDATE `Inventario` SET  `Product`='" + producto.getText() + "', `Brand`='"
								+ marca.getText() + "', `Description`='" + descripcion.getText() + "', `Amount`='"
								+ Float.parseFloat(existencias.getText()) + "',`Price`='"
								+ Float.parseFloat(precio.getText()) + "',`Unid`='"
								+ unidad.getSelectedItem().toString() + "',`Image`='" + imgurl + "'  WHERE `Code`='"
								+ codigo.getText().toUpperCase() + "';");

						Mecanics.iArchivo(ruta, "./src/Inventory/productPhoto", codigo.getText().toUpperCase());

					} catch (Exception b) {

						limpieza = false;
						repaint();
					}

				}
				Mecanics.getFile(true);
				modelo.setRowCount(0);
				mostrar();

				if (limpieza) {
					limpiar();
				}

			}
		});
		btnguardar.setBounds(25, 390, 204, 36);
		panel.add(btnguardar);

		btnEliminar = new JButton(len[11]);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent d) {

				try {
					Conexion a = new Conexion();

					a.sentence("DELETE FROM `inventario` WHERE `Code`='" + codigo.getText().toUpperCase() + "';");

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Verifique los datos\nError: " + e.toString());
					repaint();
				}
				Mecanics.getFile(true);
				modelo.setRowCount(0);
				mostrar();

			}
		});
		btnEliminar.setBounds(25, 437, 204, 36);
		panel.add(btnEliminar);

		JLabel candado = new JLabel("");
		candado.setBounds(843, 11, 35, 35);
		ImageIcon can = new ImageIcon(new ImageIcon("./src/ResourcePackCaja/CandadoCerrado.png").getImage()
				.getScaledInstance(candado.getWidth(), candado.getHeight(), Image.SCALE_AREA_AVERAGING));
		candado.setIcon(can);
		panel.add(candado);

		editar = new JLabel("");
		editar.setBounds(452, 11, 35, 35);
		ImageIcon ed = new ImageIcon(new ImageIcon("./src/ResourcePackCaja/EditarLight.png").getImage()
				.getScaledInstance(editar.getWidth(), editar.getHeight(), Image.SCALE_AREA_AVERAGING));
		editar.setIcon(ed);

		editar.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {

				access = (!access);

				if (access == true) {

					candadoIcon = new ImageIcon(new ImageIcon("./src/ResourcePackCaja/CandadoAbierto.png").getImage()
							.getScaledInstance(candado.getWidth(), candado.getHeight(), Image.SCALE_AREA_AVERAGING));
					candado.setIcon(candadoIcon);
					btnguardar.setText(len[13]);
					codigo.setEditable(false);
					codigo.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
				}

				if (access == false) {

					candadoIcon = new ImageIcon(new ImageIcon("./src/ResourcePackCaja/CandadoCerrado.png").getImage()
							.getScaledInstance(candado.getWidth(), candado.getHeight(), Image.SCALE_AREA_AVERAGING));
					candado.setIcon(candadoIcon);
					codigo.setEditable(true);
					btnguardar.setText(len[10]);

				}

				repaint();

			}

			public void mousePressed(MouseEvent e) {

				editar.setSize(45, 45);

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				editar.setSize(35, 35);

				repaint();

			}
		});

		panel.add(editar);

		txtidioma = new JLabel(len[12]);
		txtidioma.setBounds(10, 484, 42, 21);
		panel.add(txtidioma);

		JPanel propiedades = new JPanel();
		propiedades.setBounds(25, 230, 204, 149);
		panel.add(propiedades);
		propiedades.setLayout(null);

		txtsold = new JLabel(len[14]);
		txtsold.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtsold.setBounds(10, 11, 58, 24);
		propiedades.add(txtsold);

		sold = new JLabel("");
		sold.setBounds(69, 11, 58, 24);
		propiedades.add(sold);

		mayor = invMecanics.MayorMenor();
		String may = mayor[0].getCode() + " - " + mayor[0].getProduct();
		String men = mayor[1].getCode() + " - " + mayor[1].getProduct();

		txtpopular = new JLabel("Mas popular: ");
		txtpopular.setFont(new Font("Agency FB", Font.BOLD, 13));
		txtpopular.setBounds(10, 48, 117, 14);
		propiedades.add(txtpopular);

		popular = new JLabel(may);
		popular.setFont(new Font("Agency FB", Font.BOLD, 13));
		popular.setBounds(10, 73, 172, 14);
		propiedades.add(popular);

		txtunpopular = new JLabel("Menos popular: ");
		txtunpopular.setFont(new Font("Agency FB", Font.BOLD, 13));
		txtunpopular.setBounds(10, 98, 117, 14);
		propiedades.add(txtunpopular);

		unpopular = new JLabel(men);
		unpopular.setFont(new Font("Agency FB", Font.BOLD, 13));
		unpopular.setBounds(10, 123, 172, 14);
		propiedades.add(unpopular);

		imprimir = cp.buildButton("", cp.doBounds(143, 11, 53, 53), "Lotes", JButton.CENTER, JButton.RIGHT,
				JButton.LEFT, true, true);
		imprimir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			 Lotes lt=new Lotes(User, Username);
   lt.setLocationRelativeTo(thisFrame); 
   lt.setVisible(true);
   dispose();


			}

			public void mousePressed(MouseEvent e) {

				imprimir.setSize(63, 63);

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				imprimir.setSize(53, 53);

				repaint();

			}
		});

		propiedades.add(imprimir);

		boolean aspect = false;

		if (theme == 0) {
			aspect = true;
		}
		if (lenguaje == 1) {
			ing();
			setTitle(len[19]);
		} else {
			esp();
			setTitle(len[19]);
		}

		modifymode(aspect);
		modifylen();

	}
	
	//===============================================================================================================================================================

	private void fill(String code) {

		Archivo miu;
		String a = "", b = "";
		miu = invMecanics.muestra(code);

		if (miu == null) {
			miu = new Archivo("", "", "", "", 0, 0, 0, "", "");

		} else {
			a = miu.getPrice() + "";
			b = miu.getAmount() + "";
		}

		producto.setText(miu.getProduct());
		precio.setText(a);
		existencias.setText(b);

		unidad.setSelectedItem(miu.getUnid());
		marca.setText(miu.getBrand());
		descripcion.setText(miu.getDescription());
		sold.setText(miu.getSold() + "");

		ruta = miu.getImage();

		if (new File(miu.getImage()).exists() == false) {

			ruta = "./src/ResourcePackCaja/image-not-found.png";
		}

		ImageIcon ima = new ImageIcon(new ImageIcon(ruta).getImage().getScaledInstance(foto.getWidth(),
		foto.getHeight(), Image.SCALE_AREA_AVERAGING));
		foto.setIcon(ima);
		foto.repaint();

	}

	

	public void esp() {
		String leno[] = { "Informacion de producto:", "Productos Disponibles:", "Codigo:", "Producto:", "Precio:",
				"Existencias:", "Unidad:", "Marca:", "Descripcion:", "Seleccionar Imagen", "Guardar", "Eliminar",
				"(ESP)", "Editar", "Vendido:", "Mas popular:", "Menos popular:", "Producto mas vendido: ",
				"Producto menos vendido: ", "Inventario" };
		len = leno;
	}

	public void ing() {
		String leno[] = { "Product information:", "Available products:", "Code:", "Product:", "Price:", "Stock:",
				"Unit:", "Brand:", "Description:", "Select Image", "Save", "Delete", "(ENG)", "Edit", "Sold:",
				"Most popular:", "Least popular:", "Best selling product: ", "Least selling product: ", "Inventory" };
		len = leno;
	}

	public void modifylen() {

		txtInformacion.setText(len[0]);
		txttabla.setText(len[1]);
		txtcodigo.setText(len[2]);
		txtproductos.setText(len[3]);
		txtprecio.setText(len[4]);
		txtexistencia.setText(len[5]);
		txtunidad.setText(len[6]);
		txtmarca.setText(len[7]);
		txtdescripcion.setText(len[8]);
		btnimage.setText(len[9]);
		btnguardar.setText(len[10]);
		btnEliminar.setText(len[11]);
		txtidioma.setText(len[12]);
		if (access == true) {
			btnguardar.setText(len[13]);
		}
		txtsold.setText(len[14]);
		txtpopular.setText(len[15]);
		txtunpopular.setText(len[16]);

		String[] titulos = { len[2], len[3], len[4], len[5], len[7] };
		modelo = new DefaultTableModel(null, titulos);
		table.setModel(modelo);
		mostrar();
	}

	public void modifymode(boolean b) {

		Color colorfuente = new Color(20, 35, 54), colorfondo = new Color(238, 248, 254);
		String labelcolor = "Light";

		if (b == false) {
			colorfuente = new Color(238, 248, 254);
			colorfondo = new Color(20, 35, 54);
			labelcolor = "Dark";
		}

		panel.setBackground(colorfondo);
		txtInformacion.setForeground(colorfuente);
		txttabla.setForeground(colorfuente);
		txtcodigo.setForeground(colorfuente);
		txtproductos.setForeground(colorfuente);
		txtprecio.setForeground(colorfuente);
		txtexistencia.setForeground(colorfuente);
		txtunidad.setForeground(colorfuente);
		txtmarca.setForeground(colorfuente);
		txtdescripcion.setForeground(colorfuente);
		txtidioma.setForeground(colorfuente);

		editar.setIcon(new ImageIcon(new ImageIcon("./src/ResourcePackCaja/Editar" + labelcolor + ".png").getImage()
				.getScaledInstance(editar.getWidth(), editar.getHeight(), Image.SCALE_DEFAULT)));

		codigo.setForeground(colorfuente);
		precio.setForeground(colorfuente);
		producto.setForeground(colorfuente);
		existencias.setForeground(colorfuente);
		marca.setForeground(colorfuente);

	}

	public void limpiar() {

		codigo.setText("");
		producto.setText("");
		precio.setText("");
		existencias.setText("");
		marca.setText("");
		descripcion.setText("");
		sold.setText("");

		ImageIcon ima = new ImageIcon(new ImageIcon("./src/ResourcePackCaja/image-not-found.png").getImage()
				.getScaledInstance(foto.getWidth(), foto.getHeight(), Image.SCALE_DEFAULT));
		foto.setIcon(ima);
		foto.repaint();

	}

	public void mostrar() {

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

	
}
