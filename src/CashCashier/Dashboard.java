package CashCashier;

import Main.Mecanics;
import Main.Runner;
import Main.Menu;
import Objects.Trabajador;
import Objects.Factura;
import Objects.ComponentBuilder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLayeredPane;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Image;

public class Dashboard extends JFrame{
	
	public static CajaRegistradora Caja;
	public static RegistrarUsuario CRUD;
	public static Facturas Cuenta;
	public static Configuraciones Settings;

	private int m = Mecanics.getMode(true);
	private int l = Mecanics.getLanguage(true);
	
	public static Object[][] datos = {{000,"Prueba",0,0,0}};
	public static String User;
	private String Name;
	
	private Color[] Fondo = {new Color(238, 248, 254), new Color(20, 35, 54)};
	
	private String[] CajaTitulo = {"Caja Registradora","Cash Register"};
	
	public static JPanel Background;
	public static JPanel Window1st;
	public static JPanel Window2nd;
	public static JPanel Window3rd;
	public static JPanel Window4th;
	public static JPanel WindowSelected;
	
	public static JLayeredPane contentPane;
	
	private final ComponentBuilder cp = new ComponentBuilder("./src/ResourcePackCaja", "jpg", Fondo[m]);
	
	public Dashboard(String User, String Name){
		
		this.User = User;
		this.Name = Name;
		
		Caja = new CajaRegistradora(true,new Factura(Trabajador.get(User)));
		
		setResizable(false);
		setTitle(CajaTitulo[l]+" - "+Name);
		setIconImage(Toolkit.getDefaultToolkit().getImage("./src/ResourcePackCaja/Icono.png"));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(200,200,1000,500);
		setLocationRelativeTo(null);

		contentPane = new JLayeredPane();
        contentPane.setPreferredSize(new Dimension(getWidth(), getHeight()));
		
		Background = FondoPantalla();
        Window1st = cp.buildPanel(cp.doBounds(45, 21, 225, 25), new int[] {12, 0, 215, 25, 0, 60}, (float) -0.6, 0, new Color(175, 186, 199));
        Window2nd = cp.buildPanel(cp.doBounds(270, 21, 225, 25), new int[] {12, 0, 215, 25, 0, 60}, (float) -0.6, 0, new Color(175, 186, 199));
        Window3rd = cp.buildPanel(cp.doBounds(495, 21, 225, 25), new int[] {12, 0, 215, 25, 0, 60}, (float) -0.6, 0, new Color(175, 186, 199));
        Window4th = cp.buildPanel(cp.doBounds(720, 21, 225, 25), new int[] {12, 0, 215, 25, 0, 60}, (float) -0.6, 0, new Color(175, 186, 199));
		WindowSelected = cp.buildPanel(cp.doBounds(45, 21, 225, 50), new int[] {12, 0, 215, 25, 0, 60}, (float) -0.6, 0, Fondo[m]);
		
		contentPane.add(Background, Integer.valueOf(0));
        contentPane.add(Caja, Integer.valueOf(1));
        contentPane.add(Window1st, Integer.valueOf(2));
        contentPane.add(Window2nd, Integer.valueOf(3));
        contentPane.add(Window3rd, Integer.valueOf(4));
        contentPane.add(Window4th, Integer.valueOf(5));
		contentPane.add(WindowSelected, Integer.valueOf(6));
		
		Background.setVisible(true);
		Window1st.setVisible(true);
		Window2nd.setVisible(true);
		Window3rd.setVisible(true);
		Window4th.setVisible(true);
		
		Cuenta = new Facturas();
		CRUD = new RegistrarUsuario("");
		Settings = new Configuraciones(this);

		Actions();

		this.setContentPane(contentPane);

		setVisible(true);

	}
	
	private JPanel FondoPantalla(){
		
		JPanel Background = new JPanel();
		
		Background.setLayout(null);
		Background.setBackground(new Color(0, 0, 0, 0));
		Background.setBounds(0,0,this.getWidth(),this.getHeight());
		
		JLabel Wallpaper = new JLabel();
		
		Wallpaper.setBounds(0, 0, Background.getWidth(), Background.getHeight());
		ImageIcon fondo = new ImageIcon(new ImageIcon("./src/ResourcePackCaja/Fondo.jpg").getImage().getScaledInstance(Wallpaper.getWidth(), Wallpaper.getHeight(), Image.SCALE_DEFAULT));
		
		Wallpaper.setIcon(fondo);
		Background.add(Wallpaper);
		
		return Background;
		
	}
	
	private void Actions(){
		
		final Dashboard thisFrame = this;
		
		addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e) {
				
				boolean scp = Mecanics.Leave(thisFrame);
				
				if(scp==true) {
					
					removeAll();
				
					Runner lg = new Runner();
					
					lg.contentPane.removeAll();
					
					lg.Opciones = new Menu(lg, User, Name);
					
					lg.contentPane.add(lg.Opciones, Integer.valueOf(0));

					lg.setVisible(true);
					
					dispose();
					
					repaint();
					
				}
				
			}
			
		});
		
		Window1st.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){
				
				contentPane.removeAll();
				
				WindowSelected.setBounds(45, 21, 225, 50);
				
				contentPane.add(Background, Integer.valueOf(0));
				contentPane.add(Caja, Integer.valueOf(1));
				contentPane.add(Window1st, Integer.valueOf(2));
				contentPane.add(Window2nd, Integer.valueOf(3));
				contentPane.add(Window3rd, Integer.valueOf(4));
				contentPane.add(Window4th, Integer.valueOf(5));
				contentPane.add(WindowSelected, Integer.valueOf(6));
				
				String bup = Caja.TextPanelID.getText();
				Caja.TextPanelID.setText("");
				Caja.TextPanelID.setText(bup);
				
				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				repaint();

			}

		});
		
		Window2nd.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){
				
				contentPane.removeAll();
				
				WindowSelected.setBounds(270, 21, 225, 50);
				
				contentPane.add(Background, Integer.valueOf(0));
				contentPane.add(CRUD, Integer.valueOf(1));
				contentPane.add(Window1st, Integer.valueOf(2));
				contentPane.add(Window2nd, Integer.valueOf(3));
				contentPane.add(Window3rd, Integer.valueOf(4));
				contentPane.add(Window4th, Integer.valueOf(5));
				contentPane.add(WindowSelected, Integer.valueOf(6));
				
				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				repaint();

			}

		});
		
		Window3rd.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){
				
				contentPane.removeAll();
				
				WindowSelected.setBounds(495, 21, 225, 50);
				
				contentPane.add(Background, Integer.valueOf(0));
				contentPane.add(Cuenta, Integer.valueOf(1));
				contentPane.add(Window1st, Integer.valueOf(2));
				contentPane.add(Window2nd, Integer.valueOf(3));
				contentPane.add(Window3rd, Integer.valueOf(4));
				contentPane.add(Window4th, Integer.valueOf(5));
				contentPane.add(WindowSelected, Integer.valueOf(6));
				
				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				repaint();

			}

		});
		
		Window4th.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){
				
				contentPane.removeAll();
				
				WindowSelected.setBounds(720, 21, 225, 50);
				
				contentPane.add(Background, Integer.valueOf(0));
				contentPane.add(Settings, Integer.valueOf(1));
				contentPane.add(Window1st, Integer.valueOf(2));
				contentPane.add(Window2nd, Integer.valueOf(3));
				contentPane.add(Window3rd, Integer.valueOf(4));
				contentPane.add(Window4th, Integer.valueOf(5));
				contentPane.add(WindowSelected, Integer.valueOf(6));
				
				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				repaint();

			}

		});
		
	}
	
}