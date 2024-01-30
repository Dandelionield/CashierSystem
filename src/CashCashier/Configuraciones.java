package CashCashier;

import Main.Mecanics;
import Main.Runner;
import Main.Menu;
import Objects.ComponentBuilder;
import Objects.Factura;
import Objects.Cliente;
import Objects.Trabajador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

public class Configuraciones extends JPanel{
	
	private int m = Mecanics.getMode(true);
	private int l = Mecanics.getLanguage(true);
	
	private JFrame DashboardFrame;
	
	private Color[] Fondo = {new Color(238, 248, 254), new Color(20, 35, 54)};
	
	private JLabel Title;
	private JLabel Address;
	private JTextField TextPanelTitle;
	private JTextField TextPanelAddress;
	private JButton Logo;
	private JButton Edit;
	private JButton Save;
	
	private String[] ConfiguracionesEditar = {"EditarLight","EditarDark"};
	private String[] ConfiguracionesGuardar = {"GuardarLight","GuardarDark"};
	private String[] ConfiguracionesSalir = {"CerrarLight","CerrarDark"};
	
	private final ComponentBuilder cp = new ComponentBuilder("./src/ResourcePackCaja", Fondo[m]);
	
	public Configuraciones(JFrame frame){
		
		DashboardFrame = frame;
		
		if (m==1){cp.setForeground(Color.WHITE);}
		
		int z = 0;
		
		Trabajador p = Mecanics.Employe.get(Mecanics.getEmploye(Dashboard.User));
		
		setBounds(20, 45, 960, 415);
		setOpaque(false);
		
		Title = cp.buildLabel(Mecanics.getTitle(true), cp.doBounds((this.getWidth()/2)-200, 130, 400, 30), SwingConstants.CENTER, new Font("Clarendon Blk BT", Font.BOLD, 20));
		Address = cp.buildLabel(Mecanics.getAddress(true), cp.doBounds((this.getWidth()/2)-200, 165, 400, 30), SwingConstants.CENTER, new Font("Clarendon Blk BT", Font.BOLD, 15));
		TextPanelTitle = cp.buildTextField("", cp.doBounds((this.getWidth()/2)-200, 130, 400, 30), SwingConstants.CENTER, new Font("Clarendon Blk BT", Font.BOLD, 20), Color.BLUE, Color.BLUE, true, false);
		TextPanelAddress = cp.buildTextField("", cp.doBounds((this.getWidth()/2)-200, 165, 400, 30), SwingConstants.CENTER, new Font("Clarendon Blk BT", Font.BOLD, 15), Color.BLUE, Color.BLUE, true, false);
		Logo = cp.buildButton("Logo", cp.doBounds((this.getWidth()/2)-50, 10, 100, 100), true, true);
		Edit = cp.buildButton(ConfiguracionesEditar[m], cp.doBounds(410, 200, 25, 25), p.getAdmin(), false);
		Save = cp.buildButton(ConfiguracionesGuardar[m], cp.doBounds(520, 200, 25, 25), false, false);
		
		setLayout(null);
		setComponentZOrder(Title, z);z++;
		setComponentZOrder(Logo, z);z++;
		setComponentZOrder(Address, z);z++;
		setComponentZOrder(TextPanelTitle, z);	z++;
		setComponentZOrder(TextPanelAddress, z);z++;
		setComponentZOrder(Edit, z);	z++;
		setComponentZOrder(Save, z);	z++;
		
		Title.setVisible(true);
		Logo.setVisible(true);
		Address.setVisible(true);
		TextPanelTitle.setVisible(false);
		TextPanelAddress.setVisible(false);
		Edit.setVisible(true);
		Save.setVisible(true);
		
		Actions();
		
		setVisible(true);
		
	}
	
	protected void paintComponent(Graphics g){
		
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(0, 0, 940, 390, 60, 60);
		g2d.shear(0.0,0.0);
		g2d.setColor(Fondo[m]);
        g2d.fill(roundedRectangle);

        g2d.dispose();
		
    }
	
	private void Actions(){
		
		Logo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){
				
				if (Save.isEnabled()){
					
					String url = Mecanics.actionimage(Dashboard.Settings).getAbsolutePath();
					
					Mecanics.iArchivo(url,"./src/ResourcePackCaja","Logo");
					
					Save.doClick();
					
					Dashboard.contentPane.removeAll();
					
					Dashboard.Settings = new Configuraciones(DashboardFrame);
					Dashboard.contentPane.add(Dashboard.Background, Integer.valueOf(0));
					Dashboard.contentPane.add(Dashboard.Settings, Integer.valueOf(1));
					Dashboard.contentPane.add(Dashboard.Window1st, Integer.valueOf(2));
					Dashboard.contentPane.add(Dashboard.Window2nd, Integer.valueOf(3));
					Dashboard.contentPane.add(Dashboard.Window3rd, Integer.valueOf(4));
					Dashboard.contentPane.add(Dashboard.Window4th, Integer.valueOf(5));
					Dashboard.contentPane.add(Dashboard.WindowSelected, Integer.valueOf(6));
					
				}
				
				repaint();

			}

		});
		
		Edit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){
				
				Title.setVisible(false);
				Address.setVisible(false);
				TextPanelTitle.setVisible(true);
				TextPanelAddress.setVisible(true);
				
				TextPanelTitle.setText(Title.getText());
				TextPanelAddress.setText(Address.getText());
				
				Edit.setEnabled(false);
				Save.setEnabled(true);
				
				repaint();

			}

		});
		
		Save.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){
				
				Title.setVisible(true);
				Address.setVisible(true);
				TextPanelTitle.setVisible(false);
				TextPanelAddress.setVisible(false);
				
				Title.setText(TextPanelTitle.getText());
				Address.setText(TextPanelAddress.getText());
				
				Mecanics.setTitle(true,TextPanelTitle.getText());
				Mecanics.setAddress(true,TextPanelAddress.getText());
				
				Edit.setEnabled(true);
				Save.setEnabled(false);
				
				repaint();

			}

		});
		
		TextPanelTitle.getDocument().addDocumentListener (new DocumentListener(){

            public void insertUpdate(DocumentEvent e){
				
				SwingUtilities.invokeLater(() -> setTextPanelLimit(38));

				repaint();

            }

            public void removeUpdate(DocumentEvent e){

				repaint();

            }

            public void changedUpdate(DocumentEvent e){

				repaint();

            }
			
			private void setTextPanelLimit(int limite) {
				
                String Text = TextPanelTitle.getText();
				
                if (Text.length()>limite){
					
                    Text = Text.substring(0, limite);
                    TextPanelTitle.setText(Text);
					
                }
            }

        });
		
		TextPanelAddress.getDocument().addDocumentListener (new DocumentListener(){

            public void insertUpdate(DocumentEvent e){
				
				SwingUtilities.invokeLater(() -> setTextPanelLimit(55));

				repaint();

            }

            public void removeUpdate(DocumentEvent e){

				repaint();

            }

            public void changedUpdate(DocumentEvent e){

				repaint();

            }
			
			private void setTextPanelLimit(int limite) {
				
                String Text = TextPanelAddress.getText();
				
                if (Text.length()>limite){
					
                    Text = Text.substring(0, limite);
                    TextPanelAddress.setText(Text);
					
                }
            }

        });
		
		TextPanelTitle.addKeyListener(new KeyAdapter(){

            public void keyPressed(KeyEvent e){

				if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_DOWN){
					
					TextPanelAddress.requestFocus();

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
		
		TextPanelAddress.addKeyListener(new KeyAdapter(){

            public void keyPressed(KeyEvent e){

				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					
					Save.doClick();

				}else if (e.getKeyCode() == KeyEvent.VK_UP){
					
					TextPanelTitle.requestFocus();
					
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
		
		Edit.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){

				Edit.setSize(35,35);

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				Edit.setSize(25,25);

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				repaint();

			}

		});
		
		Save.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){

				Save.setSize(35,35);

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				Save.setSize(25,25);

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				repaint();

			}

		});
		
	}

}
