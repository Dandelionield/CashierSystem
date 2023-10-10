package Main;

import javax.swing.JFrame;
import java.awt.geom.RoundRectangle2D;
import java.awt.Toolkit;

import javax.swing.JLayeredPane;
import java.awt.Dimension;

public class Runner extends JFrame{
	
	public static Login Inicio = new Login();
	public static Menu Opciones;
	public static Configuraciones Setting = new Configuraciones();
	
	private int m = Mecanics.getMode(true);
	private int l = Mecanics.getLanguage(true);
	
	public static JLayeredPane contentPane;
	
	public Runner(){
		
		setResizable(false);
		setTitle("Login");
		setIconImage(Toolkit.getDefaultToolkit().getImage("./src/ResourcePackCaja/Icono.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200,200,400,600);
		setLocationRelativeTo(null);
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 60, 60));
		
		contentPane = new JLayeredPane();
        contentPane.setPreferredSize(new Dimension(getWidth(), getHeight()));
		
		contentPane.add(Inicio, Integer.valueOf(0));
		
		Opciones = new Menu(this,"","");
		
		this.setContentPane(contentPane);
		
		setVisible(true);
		
	}
	
}