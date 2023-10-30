package Main;

import Objects.ComponentBuilder;
import CashCashier.Dashboard;
import Inventory.Inventario;
import Employees.RegistroEmpleados;

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

public class Menu extends JPanel{
	
	private int m = Mecanics.getMode(true);
	private int l = Mecanics.getLanguage(true);
	
	public static String User;
	public static String Name;
	
	public static JFrame LoginFrame;
	private JButton Leave;
	private JButton Setting;
	private JButton Cashier;
	private JButton Inventory;
	private JButton newEmploye;
	private JButton Logout;
	
	private Color[] Fondo = {new Color(238, 248, 254), new Color(20, 35, 54)};
	
	private String[] CashierTexto = {"Caja Registradora","Cashier"};
	private String[] InventoryTexto = {"Inventario","Inventory"};
	private String[] newEmployeTexto = {"Trabajadores","Employees"};
	private String[] LogoutTexto = {"Salir","Logout"};
	
	private String[] LoginSalir = {"SalirLight","SalirDark"};
	private String[] MenuAjustes = {"SettingsLight","SettingsDark"};
	private String[] ConfiguracionesSalir = {"CerrarLight","CerrarDark"};
	
	private final ComponentBuilder cp = new ComponentBuilder("./src/ResourcePackCaja", Fondo[m]);
	
	public Menu(JFrame frame, String Usuario, String Nombre){
		
		if (m==1){cp.setForeground(Color.WHITE);}
		
		int z = 0;
		boolean Editable = false;
		
		LoginFrame = frame;
		User = Usuario;
		Name = Nombre;
		
		int indice = Mecanics.getEmploye(User);
		
		if (indice!=-1){
			
			Editable = Mecanics.Employe.get(indice).getAdmin();
			
		}
		
		setBounds(0, 0, 400, 600);
		setOpaque(false);
		
		Leave = cp.buildButton(LoginSalir[m], cp.doBounds(this.getWidth()-50, 20, 25, 25), true, false);
		Setting = cp.buildButton(MenuAjustes[m], cp.doBounds(this.getWidth()/2-10, 20, 25, 25), true, false);
		Cashier = cp.buildButton("", cp.doBounds(this.getWidth()/2-100, this.getHeight()/6, 200, 50), "Icono", JButton.CENTER, JButton.RIGHT, JButton.CENTER, true, false);
		Inventory = cp.buildButton("", cp.doBounds(this.getWidth()/2-100, this.getHeight()/2-50, 200, 50), "Inventario", JButton.CENTER, JButton.RIGHT, JButton.CENTER, Editable, false);
		newEmploye = cp.buildButton("", cp.doBounds(this.getWidth()/2-100, this.getHeight()-this.getHeight()/3, 200, 50), "Registro", JButton.CENTER, JButton.RIGHT, JButton.CENTER, Editable, false);
		Logout = cp.buildButton("", cp.doBounds(30, 20, 130, 25), ConfiguracionesSalir[m], JButton.CENTER, JButton.RIGHT, JButton.LEFT, true, false);
		

		setLayout(null);
		setComponentZOrder(Leave, z);	z++;
		setComponentZOrder(Setting, z);	z++;
		setComponentZOrder(Cashier, z);	z++;
		setComponentZOrder(Inventory, z);	z++;
		setComponentZOrder(newEmploye, z);	z++;
		setComponentZOrder(Logout, z);	z++;
		
		Leave.setVisible(true);
		Setting.setVisible(true);
		Cashier.setVisible(true);
		Inventory.setVisible(true);
		newEmploye.setVisible(true);
		Logout.setVisible(true);
		
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
		
		Setting.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){
				
				Runner.contentPane.removeAll();
				
				Runner.contentPane.add(Runner.Setting, Integer.valueOf(0));
				
				repaint();

			}

		});
		
		Logout.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){
				
				Runner.contentPane.removeAll();
				
				Runner.Inicio = new Login();
				
				Runner.contentPane.add(Runner.Inicio, Integer.valueOf(0));
				
				repaint();

			}

		});
		
		Cashier.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){
				
				Dashboard Dash = new Dashboard(User,Name);
				
				Dash.setVisible(true);
				
				LoginFrame.dispose();
				
				repaint();

			}

		});
		
		Inventory.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){
				
				Inventario Inv = new Inventario(User, Name);
				Inv.setLocationRelativeTo(null);
				Inv.setVisible(true);
				
				LoginFrame.dispose();
				
				repaint();

			}

		});
		
		newEmploye.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){
				RegistroEmpleados empleados = new RegistroEmpleados();
				LoginFrame.dispose();
				
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
		
		Setting.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				
				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){

				Setting.setSize(35,35);

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				Setting.setSize(25,25);

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				repaint();

			}

		});
		
		Cashier.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				Cashier.setText(CashierTexto[l]);

				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){

				Cashier.setText("");

				Cashier.setSize(210, 60);

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				Cashier.setText(CashierTexto[l]);

				Cashier.setSize(200, 50);

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				Cashier.setText("");

				repaint();

			}

		});
		
		Inventory.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				Inventory.setText(InventoryTexto[l]);

				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){

				Inventory.setText("");

				Inventory.setSize(210, 60);

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				Inventory.setText(InventoryTexto[l]);

				Inventory.setSize(200, 50);

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				Inventory.setText("");

				repaint();

			}

		});
		
		newEmploye.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				newEmploye.setText(newEmployeTexto[l]);

				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){

				newEmploye.setText("");

				newEmploye.setSize(210, 60);

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				newEmploye.setText(newEmployeTexto[l]);

				newEmploye.setSize(200, 50);

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				newEmploye.setText("");

				repaint();

			}

		});
		
		Logout.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				Logout.setText(LogoutTexto[l]);

				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){

				Logout.setText("");

				Logout.setSize(140, 35);

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				Logout.setText(LogoutTexto[l]);

				Logout.setSize(130, 25);

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				Logout.setText("");

				repaint();

			}

		});
		
	}
	
}
