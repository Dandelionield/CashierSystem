package Main;

import Objects.ComponentBuilder;
import Objects.Trabajador;

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

public class Login extends JPanel{
	
	private int m = Mecanics.getMode(true);
	private int l = Mecanics.getLanguage(true);
	
	private JLabel Logo;
	private JLabel Welcome;
	private JLabel User;
	private JLabel UserImage;
	private JLabel Password;
	private JTextField TextPanelUser;
	private JTextField TextPanelPassword1;
	private JPasswordField TextPanelPassword2;
	private JButton Show;
	private JButton Login;
	private JButton Leave;
	
	private Color[] Fondo = {new Color(238, 248, 254), new Color(20, 35, 54)};
	
	private String[] WelcomeTexto = {"Bienvenid@","Welcome"};
	private String[] UserTexto = {"Usuario","User"};
	private String[] PasswordTexto = {"Contraseña","Password"};
	private String[] LoginTexto = {"Entrar","Login"};
	
	private String[] LoginLogo = {"MultiplesLight","MultiplesDark"};
	private String[] LoginUsuario = {"UsuarioLight","UsuarioDark"};
	private String[] LoginVer = {"VerLight","VerDark"};
	private String[] LoginSalir = {"SalirLight","SalirDark"};
	
	private final ComponentBuilder cp = new ComponentBuilder("./src/ResourcePackCaja", Fondo[m]);
	
	public Login(){
		
		if (m==1){cp.setForeground(Color.WHITE);}
		
		int z = 0;
		
		setBounds(0, 0, 400, 600);
		setOpaque(false);
		
		Logo = cp.buildLabel("", cp.doBounds((this.getWidth()/2)-70, 10, 130, 130), LoginLogo[m], 130, 130, SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER, null);
		Welcome = cp.buildLabel(WelcomeTexto[l], cp.doBounds((this.getWidth()/2)-100, 160, 200, 30), SwingConstants.CENTER, new Font("Clarendon Blk BT", Font.BOLD, 20));
		User = cp.buildLabel(UserTexto[l], cp.doBounds(100, 250, 80, 20), SwingConstants.LEFT, new Font("Clarendon Blk BT", Font.BOLD, 14));
		Password = cp.buildLabel(PasswordTexto[l], cp.doBounds(100, 355, 100, 20), SwingConstants.LEFT, new Font("Clarendon Blk BT", Font.BOLD, 14));
		UserImage = cp.buildLabel("", cp.doBounds(65, 288, 25, 25), LoginUsuario[m], 25, 25, SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER, null);
		TextPanelUser = cp.buildTextField("", cp.doBounds(100, 295, 200, 20), SwingConstants.LEFT, new Font("Clarendon Blk BT", Font.PLAIN, 15), Color.BLUE, Color.BLUE, true, false);
		TextPanelPassword1 = cp.buildTextField("", cp.doBounds(100, 400, 200, 20), SwingConstants.LEFT, new Font("Clarendon Blk BT", Font.PLAIN, 15), Color.BLUE, Color.BLUE, true, false);
		TextPanelPassword2 = PassPanelTexto();
		Show = cp.buildButton(LoginVer[m], cp.doBounds(64, 393, 25, 25), true, false);
		Login = Entrar();
		Leave = cp.buildButton(LoginSalir[m], cp.doBounds(this.getWidth()-50, 20, 25, 25), true, false);
		
		setLayout(null);
		setComponentZOrder(Logo, z);	z++;
		setComponentZOrder(Welcome, z);	z++;
		setComponentZOrder(User, z);	z++;
		setComponentZOrder(Password, z);	z++;
		setComponentZOrder(UserImage, z);	z++;
		setComponentZOrder(TextPanelUser, z);	z++;
		setComponentZOrder(TextPanelPassword1, z);	z++;
		setComponentZOrder(TextPanelPassword2, z);	z++;
		setComponentZOrder(Show, z);	z++;
		setComponentZOrder(Login, z);	z++;
		setComponentZOrder(Leave, z);	z++;
		
		Logo.setVisible(true);
		Welcome.setVisible(true);
		User.setVisible(true);
		Password.setVisible(true);
		UserImage.setVisible(true);
		TextPanelUser.setVisible(true);
		TextPanelPassword1.setVisible(false);
		TextPanelPassword2.setVisible(true);
		Show.setVisible(true);
		Login.setVisible(true);
		Leave.setVisible(true);
		
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
	
	private JPasswordField PassPanelTexto(){

		JPasswordField TextPanel = new JPasswordField("");

		if (m==0){

			TextPanel.setForeground(Color.BLACK);

		}else if (m==1){

			TextPanel.setForeground(Color.WHITE);

		}

		TextPanel.setHorizontalAlignment(SwingConstants.LEFT);
		TextPanel.setOpaque(true);
		TextPanel.setFont(new Font("Clarendon Blk BT", Font.PLAIN, 15));
		TextPanel.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
		TextPanel.setCaretColor(Color.BLUE);
		TextPanel.setBackground(Fondo[m]);
		TextPanel.setBounds(100, 400, 200, 20);
		TextPanel.setEditable(true);
		TextPanel.setVisible(false);
	
		return TextPanel;
	
	}
	
	private JButton Entrar(){
		
		Color[] FondoB = {new Color(180, 201, 255),new Color(0, 53, 94)};

		JButton Login = new JButton(LoginTexto[l]){
			
			protected void paintComponent(Graphics g) {
				
				Graphics2D g2d = (Graphics2D) g.create();
				g2d.setColor(getBackground());
				g2d.fill(new RoundRectangle2D.Double(0, 0, 200, 45, 45, 45));
				super.paintComponent(g2d);
				g2d.dispose();
				
			}
			
		};
		
		

		Login.setBounds(((this.getWidth())/2)-100, 470, 200, 45);
		/*ImageIcon icono = new ImageIcon(new ImageIcon(LoginVer[m]).getImage().getScaledInstance(Show.getWidth(), Show.getHeight(), Image.SCALE_SMOOTH));
		Show.setIcon(icono);*/
		
		if (m==0){

			Login.setForeground(Color.BLACK);

		}else if (m==1){

			Login.setForeground(Color.WHITE);

		}
		
		Login.setFont(new Font("Clarendon Blk BT", Font.BOLD, 15));
		Login.setVerticalTextPosition(JButton.CENTER);
        Login.setHorizontalTextPosition(JButton.CENTER);
		Login.setBackground(FondoB[m]);
		Login.setFocusable(false);
		Login.setOpaque(true);
        Login.setContentAreaFilled(false);
		Login.setBorder(null);
        Login.setBorderPainted(false);
		Login.setEnabled(true);
		Login.setVisible(false);

		return Login;

	}
	
	private void Actions(){
		
		Show.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){
				
				if (TextPanelPassword1.isVisible()){
					
					TextPanelPassword2.setText(TextPanelPassword1.getText());
					TextPanelPassword1.setVisible(false);
					TextPanelPassword2.setVisible(true);
					TextPanelPassword2.requestFocus();
					
				}else{
					
					TextPanelPassword1.setText(new String(TextPanelPassword2.getPassword()));
					TextPanelPassword2.setVisible(false);
					TextPanelPassword1.setVisible(true);
					TextPanelPassword1.requestFocus();
					
				}
				
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
		
		Login.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){
				
				String bup = "";
				String Usuario = "";
				String UsuarioNombre = "";
				boolean Pass = true;
				int indice = 0;
				Trabajador p;
				
				String[] ErrorMessages;
				
				bup = TextPanelUser.getText().trim();
				
				if (bup!=null){// && bup.equals("")==false){
					
					indice = Mecanics.getEmploye(bup);
					
					if (indice!=-1){
						
						p = Mecanics.Employe.get(indice);
						
						Usuario = p.getCode();
						UsuarioNombre = p.getName()+" "+p.getLastName();
						
						TextPanelUser.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
						
					}else{
						
						TextPanelUser.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
						
						Pass = false;
						
						ErrorMessages = new String[] {"Usuario Inexistente", "Unexistance User"};
						Mecanics.txtErrorMessage(TextPanelUser, ErrorMessages[l]);
						
					}
					
				}else{
					
					TextPanelUser.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
					
					Pass = false;
					
					ErrorMessages = new String[] {"Usuario No Ser Vacio", "User Cannot Be Empty"};
					Mecanics.txtErrorMessage(TextPanelUser, ErrorMessages[l]);
					
				}
				
				bup = "";
				
				if (TextPanelPassword1.isVisible() && Pass==true){
					
					bup = TextPanelPassword1.getText().trim();
					
				}else if (TextPanelPassword2.isVisible() && Pass==true){
					
					bup = new String(TextPanelPassword2.getPassword());
					bup = bup.trim();
					
				}
				
				if (bup!=null){// && bup.equals("")==false){
					
					indice = Mecanics.getEmploye(bup);
					
					if (indice!=-1 && (indice==Mecanics.getEmploye(Usuario))){
						
						TextPanelPassword1.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
						TextPanelPassword2.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
						
					}else{
						
						TextPanelPassword1.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
						TextPanelPassword2.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
						
						Pass = false;
						
						ErrorMessages = new String[] {"Contraseña Incorrecta", "Wrong Password"};
						Mecanics.txtErrorMessage(TextPanelPassword1, ErrorMessages[l]);
						
					}
					
				}else{
					
					TextPanelPassword1.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
					TextPanelPassword2.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
					
					Pass = false;
					
					ErrorMessages = new String[] {"Contraseña No Ser Vacio", "Password Cannot Be Empty"};
					Mecanics.txtErrorMessage(TextPanelPassword1, ErrorMessages[l]);
					
				}
				
				if (Pass==true){
					
					Runner.contentPane.removeAll();
					
					Runner.Opciones = new Menu(Menu.LoginFrame,Usuario,UsuarioNombre);
					
					Runner.contentPane.add(Runner.Opciones, Integer.valueOf(0));
					
				}
				
				repaint();

			}

		});
		
		TextPanelUser.getDocument().addDocumentListener (new DocumentListener(){

            public void insertUpdate(DocumentEvent e){
				
				SwingUtilities.invokeLater(() -> setTextPanelLimit(24));

				repaint();

            }

            public void removeUpdate(DocumentEvent e){

				repaint();

            }

            public void changedUpdate(DocumentEvent e){

				repaint();

            }
			
			private void setTextPanelLimit(int limite) {
				
                String Text = TextPanelUser.getText();
				
                if (Text.length()>limite){
					
                    Text = Text.substring(0, limite);
                    TextPanelUser.setText(Text);
					
                }
            }

        });
		
		TextPanelPassword1.getDocument().addDocumentListener (new DocumentListener(){

            public void insertUpdate(DocumentEvent e){
				
				SwingUtilities.invokeLater(() -> setTextPanelLimit(24));

				repaint();

            }

            public void removeUpdate(DocumentEvent e){

				repaint();

            }

            public void changedUpdate(DocumentEvent e){

				repaint();

            }
			
			private void setTextPanelLimit(int limite) {
				
                String Text = TextPanelPassword1.getText();
				
                if (Text.length()>limite){
					
                    Text = Text.substring(0, limite);
                    TextPanelPassword1.setText(Text);
					
                }
            }

        });
		
		TextPanelPassword2.getDocument().addDocumentListener (new DocumentListener(){

            public void insertUpdate(DocumentEvent e){
				
				SwingUtilities.invokeLater(() -> setTextPanelLimit(24));

				repaint();

            }

            public void removeUpdate(DocumentEvent e){

				repaint();

            }

            public void changedUpdate(DocumentEvent e){

				repaint();

            }
			
			private void setTextPanelLimit(int limite) {
				
                String Text = new String(TextPanelPassword2.getPassword());
				
                if (Text.length()>limite){
					
                    Text = Text.substring(0, limite);
                    TextPanelPassword2.setText(Text);
					
                }
            }

        });
		
		TextPanelUser.addKeyListener(new KeyAdapter(){

            public void keyPressed(KeyEvent e){

				if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_DOWN){
					
					if (TextPanelPassword1.isVisible()){
					
						TextPanelPassword1.requestFocus();
						
					}else{
						
						TextPanelPassword2.requestFocus();
						
					}

				}

				repaint();

            }

			public void keyTyped(KeyEvent e){

				repaint();

			}

			public void keyReleased(KeyEvent e) {

                repaint();

            }

        });
		
		TextPanelPassword1.addKeyListener(new KeyAdapter(){

            public void keyPressed(KeyEvent e){

				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					
					Login.doClick();

				}else if (e.getKeyCode() == KeyEvent.VK_UP){
					
					TextPanelUser.requestFocus();
					
				}

				repaint();

            }

			public void keyTyped(KeyEvent e){

				repaint();

			}

			public void keyReleased(KeyEvent e) {

                repaint();

            }

        });
		
		TextPanelPassword2.addKeyListener(new KeyAdapter(){

            public void keyPressed(KeyEvent e){

				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					
					Login.doClick();

				}else if (e.getKeyCode() == KeyEvent.VK_UP){
					
					TextPanelUser.requestFocus();
					
				}

				repaint();

            }

			public void keyTyped(KeyEvent e){

				repaint();

			}

			public void keyReleased(KeyEvent e) {

                repaint();

            }

        });
		
		Show.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				
				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){

				Show.setSize(35,35);

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				Show.setSize(25,25);

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				repaint();

			}

		});
		
		Login.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				
				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){

				Login.setSize(210,55);

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				Login.setSize(200,45);

				repaint();

			}

			public void mouseExited(MouseEvent e) {

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
		
		this.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				
				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){
				
				TextPanelUser.setText("");
				TextPanelPassword1.setText("");
				TextPanelPassword2.setText("");
				
				TextPanelUser.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
				TextPanelPassword1.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
				TextPanelPassword2.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));

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