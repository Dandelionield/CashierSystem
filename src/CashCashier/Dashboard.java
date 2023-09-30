package CashCashier;

import Main.Mecanics;
import Objects.Cliente;
import Objects.Factura;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLayeredPane;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
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
	public static RegistrarUsuario CRUD = new RegistrarUsuario("");
	public static Facturas Cuenta = new Facturas();
	public static Configuraciones Settings;
	
	//public static Factura iniciar = new Factura("","","",0,0,0,{{000,"Prueba",0,0,0}},"",""));

	private int m = Mecanics.getMode(true);
	private int l = Mecanics.getLanguage(true);
	
	public static Object[][] datos = {{000,"Prueba",0,0,0}};
	public static String User;
	
	private Color[] Fondo = {new Color(238, 248, 254), new Color(20, 35, 54)};
	
	private String[] CajaTitulo = {"Caja Registradora","Cash Register"};
	
	public static JPanel Background;
	public static JPanel Window1st;
	public static JPanel Window2nd;
	public static JPanel Window3rd;
	public static JPanel Window4th;
	public static JPanel WindowSelected;
	
	public static JLayeredPane contentPane;
	
	public Dashboard(String User, String Name){
		
		Dashboard.User = User;
		
		Caja = new CajaRegistradora(true,new Factura("",0,0,0,datos,"",Mecanics.Employe.get(Mecanics.getEmploye(User)), new Cliente(" "," "," "," "," "," ")));
		
		setResizable(false);
		setTitle(CajaTitulo[l]+" - "+Name);
		setIconImage(Toolkit.getDefaultToolkit().getImage("./src/ResourcePackCaja/Icono.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200,200,1000,500);
		setLocationRelativeTo(null);

		contentPane = new JLayeredPane();
        contentPane.setPreferredSize(new Dimension(getWidth(), getHeight()));
		
		Background = FondoPantalla();
        Window1st = Ventana1ra(new Color(175, 186, 199));
        Window2nd = Ventana2da(new Color(175, 186, 199));
        Window3rd = Ventana3ra(new Color(175, 186, 199));
        Window4th = Ventana4ta(new Color(175, 186, 199));
		WindowSelected = VentanaSeleccionada(Fondo[m]);
		
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
	
	private JPanel Ventana1ra(Color color){

		JPanel Window1st = new JPanel(){

            protected void paintComponent(Graphics g){

                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(12, 0, 215, 25, 0, 60);
				g2d.shear(-0.6,0.0);
                g2d.setColor(color);
                g2d.fill(roundedRectangle);

				g2d.dispose();

            }

        };

		Window1st.setBounds(45, 21, 225, 25);
		Window1st.setOpaque(false);

		return Window1st;

	}

	private JPanel Ventana2da(Color color){

		JPanel Window2nd = new JPanel(){

            protected void paintComponent(Graphics g){

                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(12, 0, 215, 25, 0, 60);
				g2d.shear(-0.6,0.0);
                g2d.setColor(color);
                g2d.fill(roundedRectangle);

				g2d.dispose();

            }

        };

		Window2nd.setBounds(270, 21, 225, 25);
		Window2nd.setOpaque(false);

		return Window2nd;

	}

	private JPanel Ventana3ra(Color color){

		JPanel Window3rd = new JPanel(){

            protected void paintComponent(Graphics g){

                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(12, 0, 215, 25, 0, 60);
				g2d.shear(-0.6,0.0);
                g2d.setColor(color);
                g2d.fill(roundedRectangle);

				g2d.dispose();

            }

        };

		Window3rd.setBounds(495, 21, 225, 25);
		Window3rd.setOpaque(false);

		return Window3rd;

	}

	private JPanel Ventana4ta(Color color){

		JPanel Window4th = new JPanel(){

            protected void paintComponent(Graphics g){

                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(12, 0, 215, 25, 0, 60);
				g2d.shear(-0.6,0.0);
                g2d.setColor(color);
                g2d.fill(roundedRectangle);

				g2d.dispose();

            }

        };

		Window4th.setBounds(720, 21, 225, 25);
		Window4th.setOpaque(false);

		return Window4th;

	}
	
	public static JPanel VentanaSeleccionada(Color color){

		JPanel WindowSelected = new JPanel(){

            protected void paintComponent(Graphics g){

                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(12, 0, 215, 25, 0, 60);
				g2d.shear(-0.6,0.0);
                g2d.setColor(color);
                g2d.fill(roundedRectangle);

				g2d.dispose();

            }

        };

		WindowSelected.setBounds(45, 21, 225, 50);
		WindowSelected.setOpaque(false);

		return WindowSelected;

	}
	
	private void Actions(){
		
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