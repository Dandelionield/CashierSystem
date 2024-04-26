package Inventory;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import Main.Mecanics;
import Objects.Archivo;
import javax.swing.border.MatteBorder;

public class vision extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel foto;
	private JLabel nombre;
	private JLabel precio;
	private JLabel marca;
	private JLabel unidad;
	private JLabel cantidad;
	private boolean lenguaje = true;
	private int theme = Mecanics.getMode();
	private JLabel ventas;
	private JLabel codigo;
	private JTextArea descripcion;
	private JLabel fondo;
	private JComponent panel;
	private JLabel txtprecio;
	private JLabel txtmarca;
	private JLabel txtunidad;
	private JLabel txtcantidad;
	private JScrollPane scrolldescripcion;
	private JLabel view;
	protected String[] content;
	boolean now = true;

	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vision frame = new vision(invMecanics.muestra("CP1"), false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}//*/

	/**
	 * Create the frame.
	 */
	public vision(Archivo prod, boolean acceso) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("./src/ResourcePackCaja/Inventario.png"));
		setResizable(false);

		if (Mecanics.getLanguage() == 1) {
			lenguaje = false;
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 610, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(20, 20, 554, 296);
		contentPane.add(panel);
		panel.setLayout(null);

		foto = new JLabel("");
		foto.setBorder(new LineBorder(new Color(0, 0, 0)));
		foto.setBounds(24, 48, 190, 190);
		Mecanics.lblphoto("./src/ResourcePackCaja/image-not-found.png", foto);
		panel.add(foto);

		nombre = new JLabel("");
		nombre.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.MAGENTA));
		nombre.setBounds(24, 247, 190, 26);
		nombre.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(nombre);

		precio = new JLabel("");
		precio.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.MAGENTA));
		precio.setBounds(297, 11, 226, 26);
		precio.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(precio);

		marca = new JLabel("");
		marca.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.MAGENTA));
		marca.setBounds(297, 48, 226, 26);
		marca.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(marca);

		unidad = new JLabel("");
		unidad.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.MAGENTA));
		unidad.setBounds(297, 85, 226, 26);
		unidad.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(unidad);

		cantidad = new JLabel("");
		cantidad.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.MAGENTA));
		cantidad.setBounds(297, 122, 226, 26);
		cantidad.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(cantidad);

		String[] ven = { "Vendido", "Sold" };
		Color[] color = { Color.BLACK, Color.WHITE };

		ventas = new JLabel("");
		ventas.setBorder(
				new TitledBorder(null, ven[theme], TitledBorder.LEADING, TitledBorder.TOP, null, color[theme]));
		ventas.setBounds(118, 11, 95, 32);
		ventas.setHorizontalAlignment(SwingConstants.CENTER);
		ventas.setFont(new Font("Agency FB", Font.BOLD, 13));
		panel.add(ventas);

		codigo = new JLabel("");
		codigo.setBorder(new LineBorder(color[theme], 2, true));
		codigo.setBounds(24, 17, 89, 26);
		codigo.setHorizontalAlignment(SwingConstants.CENTER);
		codigo.setFont(new Font("Agency FB", Font.BOLD, 13));
		panel.add(codigo);

		String[] des = { "Descripción", "Description" };

		scrolldescripcion = new JScrollPane();
		scrolldescripcion.setViewportBorder(
				new TitledBorder(null, des[theme], TitledBorder.LEADING, TitledBorder.TOP, null, color[theme]));
		scrolldescripcion.setBounds(237, 159, 286, 114);
		panel.add(scrolldescripcion);

		descripcion = new JTextArea();
		descripcion.setLineWrap(true);
		descripcion.setWrapStyleWord(true);
		descripcion.setEditable(false);
		scrolldescripcion.setViewportView(descripcion);

		txtprecio = new JLabel(lenguaje ? "Precio: " : "Price: ");
		// txtprecio.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtprecio.setBounds(237, 11, 50, 26);
		txtprecio.setFont(new Font("Agency FB", Font.BOLD, 13));
		panel.add(txtprecio);

		txtmarca = new JLabel(lenguaje ? "Marca: " : "Brand: ");
		// txtmarca.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtmarca.setBounds(237, 48, 50, 26);
		txtmarca.setFont(new Font("Agency FB", Font.BOLD, 13));
		panel.add(txtmarca);

		txtunidad = new JLabel(lenguaje ? "Unidad: " : "Unit: ");
		// txtunidad.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtunidad.setBounds(237, 85, 50, 26);
		txtunidad.setFont(new Font("Agency FB", Font.BOLD, 13));
		panel.add(txtunidad);

		txtcantidad = new JLabel(lenguaje ? "Cantidad: " : "Amount: ");
		// txtcantidad.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtcantidad.setBounds(237, 122, 50, 26);
		txtcantidad.setFont(new Font("Agency FB", Font.BOLD, 13));
		panel.add(txtcantidad);

		fondo = new JLabel("");
		fondo.setBounds(0, 0, 594, 336);
		Mecanics.lblphoto("./src/ResourcePackCaja/fondo.jpg", fondo);
		contentPane.add(fondo);

		view = new JLabel(lenguaje ? "<html><u>Ver antiguo </u></html>" : "<html><u>Old view</u> </html>");
		view.setBounds(491, 0, 63, 14);
		view.setForeground(Color.GRAY);
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				view.setForeground(Color.BLUE);
				view.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				view.setForeground(Color.GRAY);

			}

			@Override
			public void mouseClicked(MouseEvent e) {

				now = (!now);

				view.setText(lenguaje ? "<html><u>Ver antiguo </u></html>" : "<html><u>Old view</u> </html>");

				Archivo producto = invMecanics.muestra(content[0]);

				if (now == false) {
					view.setText(lenguaje ? "<html><u>Ver nuevo </u></html>" : "<html><u>New view</u> </html>");
					producto = new Archivo(content[0], content[1], content[2], content[3], Float.parseFloat(content[4]),
							Float.parseFloat(content[5]), Float.parseFloat(content[6]), content[7], content[8]);
				}

				fill(producto);

			}
		});
		view.setVisible(acceso);
		view.setEnabled(acceso);
		panel.add(view);

		fill(prod);
		changeMode();
	}

	public void fill(Archivo prod) {

		Mecanics.lblphoto(prod.getImage(), foto);
		codigo.setText(prod.getCode());
		ventas.setText(prod.getSold() + "");
		nombre.setText(prod.getProduct());
		precio.setText(prod.getPrice() + "");
		marca.setText(prod.getBrand());
		unidad.setText(prod.getUnid());
		cantidad.setText(prod.getAmount() + "");
		descripcion.setText(prod.getDescription());

	}

	public void changeMode() {

		if (theme == 1) {

			panel.setBackground(new Color(20, 35, 54));
			txtprecio.setForeground(new Color(238, 248, 254));
			txtmarca.setForeground(new Color(238, 248, 254));
			txtunidad.setForeground(new Color(238, 248, 254));
			txtcantidad.setForeground(new Color(238, 248, 254));
			codigo.setForeground(new Color(238, 248, 254));
			nombre.setForeground(new Color(238, 248, 254));
			ventas.setForeground(new Color(238, 248, 254));
			precio.setForeground(new Color(238, 248, 254));
			marca.setForeground(new Color(238, 248, 254));
			unidad.setForeground(new Color(238, 248, 254));
			scrolldescripcion.setForeground(new Color(238, 248, 254));
			cantidad.setForeground(new Color(238, 248, 254));
			descripcion.setForeground(new Color(238, 248, 254));
			descripcion.setBackground(new Color(20, 35, 54));
			scrolldescripcion.setBackground(new Color(20, 35, 54));

		}

	}

}
