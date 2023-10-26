package Main;

import Objects.ComponentBuilder;
import CashCashier.Dashboard;
import Inventory.Inventario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

public class Configuraciones extends JPanel{
	
	private int m = Mecanics.getMode(true);
	private int l = Mecanics.getLanguage(true);
	
	private JButton Leave;
	private JButton BackReturn;
	private JButton Mode;
	private JButton Language;
	
	private Color[] Fondo = {new Color(238, 248, 254), new Color(20, 35, 54)};
	
	private String[] CashierTexto = {"Caja Registradora","Cashier"};
	private String[] LogoutTexto = {"Salir","Logout"};
	private String[][] ModoTexto = {{"Modo Oscuro","Modo Claro"},{"Dark Mode","Light Mode"}};
	private String[] IdiomaTexto = {"English","Español"};
	
	private String[] ConfiguracionesAtras = {"BackLight","BackDark"};
	private String[] ConfiguracionesSalir = {"SalirLight","SalirDark"};
	private String[] ConfiguracionesModo = {"Dark","Light"};
	private String[] ConfiguracionesIdioma = {"idiomaLight","idiomaDark"};
	
	private final ComponentBuilder cp = new ComponentBuilder("./src/ResourcePackCaja", Fondo[m]);
	
	public Configuraciones(){
		
		if (m==1){cp.setForeground(Color.WHITE);}
		
		int z = 0;
		
		setBounds(0, 0, 400, 600);
		setOpaque(false);
		
		Leave = cp.buildButton(ConfiguracionesSalir[m], cp.doBounds(this.getWidth()-50, 20, 25, 25), true, false);
		BackReturn = cp.buildButton("", cp.doBounds(30, 20, 130, 25), ConfiguracionesAtras[m], JButton.CENTER, JButton.RIGHT, JButton.LEFT, true, true);
		Mode = cp.buildButton("", cp.doBounds(this.getWidth()/2-100, this.getHeight()/5, 200, 50), ConfiguracionesModo[m], JButton.CENTER, JButton.RIGHT, JButton.CENTER, true, false);
		Language = cp.buildButton("", cp.doBounds(this.getWidth()/2-100, this.getHeight()-this.getHeight()/2, 200, 50), ConfiguracionesIdioma[m], JButton.CENTER, JButton.RIGHT, JButton.CENTER, true, false);
		
		setLayout(null);
		setComponentZOrder(Leave, z);	z++;
		setComponentZOrder(BackReturn, z);	z++;
		setComponentZOrder(Mode, z);	z++;
		setComponentZOrder(Language, z);	z++;
		
		Leave.setVisible(true);
		BackReturn.setVisible(true);
		Mode.setVisible(true);
		Language.setVisible(true);
		
		Actions();
		
		setVisible(true);
		
	}
	
	protected void paintComponent(Graphics g){
		
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 60, 60);
        g2d.setColor(Fondo[m]);
        g2d.fill(roundedRectangle);

		g2d.dispose();

    }
	
	private void Actions(){
		
		Mode.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){
				
				Mecanics.setMode(true,Math.abs(m-1));
				
				Runner.contentPane.removeAll();
				
				Runner.Inicio = new Login();
				Runner.Opciones = new Menu(Menu.LoginFrame, Menu.User, Menu.Name);
				Runner.Setting = new Configuraciones();
				
				Runner.contentPane.add(Runner.Setting, Integer.valueOf(0));

				repaint();

			}

		});
		
		Language.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){
				
				Mecanics.setLanguage(true,Math.abs(l-1));
				
				Runner.contentPane.removeAll();
				
				Runner.Inicio = new Login();
				Runner.Opciones = new Menu(Menu.LoginFrame, Menu.User, Menu.Name);
				Runner.Setting = new Configuraciones();
				
				Runner.contentPane.add(Runner.Setting, Integer.valueOf(0));

				repaint();

			}

		});
		
		Leave.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){
				
				try {
					
					Thread.sleep(200);
					
				} catch (InterruptedException ev){
					
					ev.printStackTrace();
					
				}
				
				System.exit(0);
				
				repaint();

			}

		});
		
		BackReturn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){
				
				Runner.contentPane.removeAll();
				
				Runner.contentPane.add(Runner.Opciones, Integer.valueOf(0));
				
				repaint();

			}

		});
		
		Leave.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				
				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){

				Leave.setSize(35,35);

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				Leave.setSize(25,25);

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				repaint();

			}

		});
		
		BackReturn.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				
				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){

				BackReturn.setSize(35,35);

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				BackReturn.setSize(25,25);

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				repaint();

			}

		});
		
		Mode.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				Mode.setText(ModoTexto[l][m]);

				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){

				Mode.setText("");

				Mode.setSize(210, 60);

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				Mode.setText(ModoTexto[l][m]);

				Mode.setSize(200, 50);

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				Mode.setText("");

				repaint();

			}

		});
		
		Language.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				Language.setText(IdiomaTexto[l]);

				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){

				Language.setText("");

				Language.setSize(210, 60);

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				Language.setText(IdiomaTexto[l]);

				Language.setSize(200, 50);

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				Language.setText("");

				repaint();

			}

		});
		
	}
	
}