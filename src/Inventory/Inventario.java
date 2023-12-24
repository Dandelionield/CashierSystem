package Inventory;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;

import Main.Mecanics;
import Main.Menu;
import Main.Runner;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import java.awt.Cursor;


public class Inventario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel Title;
	private int theme = Mecanics.getMode(true);
	private boolean lenguaje = true;
	private JPanel panel;
	private JLabel UserName;
	private JLabel userLogo;
	private JLabel Lenguaje;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inventario frame = new Inventario("", "");
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
	public Inventario(String user, String userName) {

		if (Mecanics.getLanguage(true) == 1) {
			lenguaje = false;
		}

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 400, 560);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		final Inventario thisFrame = this;

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				boolean scp = Mecanics.Leave(thisFrame);

				if (scp == true) {

					Runner lg = new Runner();

					Runner.contentPane.removeAll();

					Runner.Opciones = new Menu(lg, user, userName);

					Runner.contentPane.add(Runner.Opciones, Integer.valueOf(0));

					lg.setVisible(true);

					dispose();

					repaint();
				}

			}
		});

		panel = new JPanel();
		// invMecanics.roundPanel(panel, 60);
		panel.setBackground(new Color(238, 248, 254));
		panel.setBounds(20, 20, 344, 481);
		panel.setLayout(null);
		contentPane.add(panel);

		userLogo = new JLabel("");
		userLogo.setBounds(15, 13, 35, 35);
		userLogo.setIcon(new ImageIcon(new ImageIcon("./src/ResourcePackCaja/UsuarioLight.png").getImage()
				.getScaledInstance(userLogo.getWidth(), userLogo.getHeight(), Image.SCALE_SMOOTH)));
		// userLogo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.add(userLogo);

		UserName = new JLabel(userName);
		// UserName.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		UserName.setBounds(60, 28, 107, 20);
		panel.add(UserName);

		JLabel Logo = new JLabel("");
		// Logo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		Logo.setBounds(82, 59, 176, 145);
		Logo.setIcon(new ImageIcon(new ImageIcon("./src/ResourcePackCaja/Logo.PNG").getImage()
				.getScaledInstance(Logo.getWidth(), Logo.getHeight(), Image.SCALE_SMOOTH)));
		panel.add(Logo);

		JLabel lblActual = new JLabel("");
		lblActual.setBounds(35, 224, 84, 100);
		lblActual.setIcon(new ImageIcon(new ImageIcon("./src/ResourcePackCaja/inva.jpeg").getImage()
				.getScaledInstance(lblActual.getWidth(), lblActual.getHeight(), Image.SCALE_SMOOTH)));
		lblActual.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Title.setText(lenguaje ? "Inventario Actual" : "Current Inventory");
				lblActual.setBounds(38, 227, 78, 94);
				lblActual.setIcon(new ImageIcon(new ImageIcon("./src/ResourcePackCaja/inva.jpeg").getImage()
						.getScaledInstance(lblActual.getWidth(), lblActual.getHeight(), Image.SCALE_SMOOTH)));
				lblActual.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Title.setText("");
				lblActual.setBounds(35, 224, 84, 100);
				lblActual.setIcon(new ImageIcon(new ImageIcon("./src/ResourcePackCaja/inva.jpeg").getImage()
						.getScaledInstance(lblActual.getWidth(), lblActual.getHeight(), Image.SCALE_SMOOTH)));
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				Actual act = new Actual(user, userName);
				act.setLocationRelativeTo(null);
				act.setVisible(true);
				dispose();
			}
		});
		lblActual.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.add(lblActual);

		JLabel lblHistory = new JLabel("");
		lblHistory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Title.setText(lenguaje ? "Historial de Acciones" : "Stock History");
				lblHistory.setBounds(132, 227, 78, 94);
				lblHistory.setIcon(new ImageIcon(new ImageIcon("./src/ResourcePackCaja/invh.jpeg").getImage()
						.getScaledInstance(lblHistory.getWidth(), lblHistory.getHeight(), Image.SCALE_SMOOTH)));
				lblHistory.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Title.setText("");
				lblHistory.setBounds(129, 224, 84, 100);
				lblHistory.setIcon(new ImageIcon(new ImageIcon("./src/ResourcePackCaja/invh.jpeg").getImage()
						.getScaledInstance(lblHistory.getWidth(), lblHistory.getHeight(), Image.SCALE_SMOOTH)));

			}

			@Override
			public void mouseClicked(MouseEvent e) {

				history hst = new history(user, userName);
				hst.setLocationRelativeTo(null);
				hst.setVisible(true);
				dispose();
			}
		});
		lblHistory.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblHistory.setBounds(129, 224, 84, 100);
		lblHistory.setIcon(new ImageIcon(new ImageIcon("./src/ResourcePackCaja/invh.jpeg").getImage()
				.getScaledInstance(lblHistory.getWidth(), lblHistory.getHeight(), Image.SCALE_SMOOTH)));
		panel.add(lblHistory);

		JLabel lblModify = new JLabel(" ");
		lblModify.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Title.setText(lenguaje ? "Acciones" : "Actions");
				lblModify.setBounds(229, 227, 78, 94);
				lblModify.setIcon(new ImageIcon(new ImageIcon("./src/ResourcePackCaja/inv.jpeg").getImage()
						.getScaledInstance(lblModify.getWidth(), lblModify.getHeight(), Image.SCALE_SMOOTH)));
				lblModify.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Title.setText("");
				lblModify.setBounds(226, 224, 84, 100);
				lblModify.setIcon(new ImageIcon(new ImageIcon("./src/ResourcePackCaja/inv.jpeg").getImage()
						.getScaledInstance(lblModify.getWidth(), lblModify.getHeight(), Image.SCALE_SMOOTH)));

			}

			@Override
			public void mouseClicked(MouseEvent e) {

				modify mdf = new modify(user, userName);
				mdf.setLocationRelativeTo(null);
				mdf.setVisible(true);
				dispose();
			}
		});
		lblModify.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblModify.setBounds(226, 224, 84, 100);
		lblModify.setIcon(new ImageIcon(new ImageIcon("./src/ResourcePackCaja/inv.jpeg").getImage()
				.getScaledInstance(lblModify.getWidth(), lblModify.getHeight(), Image.SCALE_SMOOTH)));
		panel.add(lblModify);

		Title = new JLabel("");
		Title.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(128, 0, 255)));
		Title.setBounds(35, 357, 275, 20);
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(Title);

		Lenguaje = new JLabel("");
		Lenguaje.setFont(new Font("Arial", Font.BOLD, 12));
		// Lenguaje.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		Lenguaje.setBounds(10, 450, 84, 20);
		Lenguaje.setText(lenguaje ? "(ESP)" : "(ENG)");
		panel.add(Lenguaje);

		JLabel fondo = new JLabel("");
		fondo.setBounds(0, 0, 384, 521);
		fondo.setIcon(new ImageIcon(new ImageIcon("./src/ResourcePackCaja/fondo.jpg").getImage()
				.getScaledInstance(fondo.getWidth(), fondo.getHeight(), Image.SCALE_SMOOTH)));
		contentPane.add(fondo);

		changetheme();
	}

	private void changetheme() {

		if (theme == 1) {
			panel.setBackground(new Color(20, 35, 54));
			Title.setForeground(new Color(238, 248, 254));
			UserName.setForeground(new Color(238, 248, 254));
			userLogo.setIcon(new ImageIcon(new ImageIcon("./src/ResourcePackCaja/UsuarioDark.png").getImage()
					.getScaledInstance(userLogo.getWidth(), userLogo.getHeight(), Image.SCALE_SMOOTH)));
			Lenguaje.setForeground(new Color(238, 248, 254));

		}

	}
}
