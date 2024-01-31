package CashCashier;

import Main.Mecanics;
import Objects.ComponentBuilder;
import Objects.Table;
import Objects.Cliente;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JViewport;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.TableColumn;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.EventObject;

public class RegistrarUsuario extends JPanel{
	
	private int m = Mecanics.getMode();
	private int l = Mecanics.getLanguage();
	private String bupID = "";
	private String bupid = "";
	
	private boolean ed = false;
	
	private Color[] Fondo = {new Color(238, 248, 254), new Color(20, 35, 54)}; 
	
	private JLabel ID;
	private JLabel Name;
	private JLabel LastName;
	private JLabel Phone;
	private JLabel Email;
	private JLabel Address;
	private JLabel Lock;
	private JTextField TextPanelID;
	private JTextField TextPanelName;
	private JTextField TextPanelLastName;
	private JTextField TextPanelPhone;
	private JTextField TextPanelEmail;
	private JTextField TextPanelAddress;
	private JTextField TextPanelShow;
	private JButton Erase;
	private JButton Accept;
	private JButton Cancel;
	private JButton Edit;

	private Table Tabla;
	
	private String[] IDTexto = {"ID","ID"};
	private String[] NameTexto = {"Nombre/s","Name/s"};
	private String[] LastNameTexto = {"Apellido/s","Last Name/s"};
	private String[] PhoneTexto = {"Número Telefónico","Phone Number"};
	private String[] EmailTexto = {"Email","Email"};
	private String[] AddressTexto = {"Dirección","Address"};
	
	private String[][] ColumnaNombre = {{"ID","Nombre/s","Apellido/s","Número Telefónico","Email","Dirección"},{"ID","Name/s","Last Name/s","Phone Number","Email","Address"}};
	
	private final String url = "./src/ResourcePackCaja";
	private String[] CajaBorrar = {"BorrarLight","BorrarDark"};
	private String[] CajaAceptar = {"AceptarLight","AceptarDark"};
	private String[] CajaCancelar = {"CancelarLight","CancelarDark"};
	private String[] CajaEditar = {"EditarLight","EditarDark"};
	private String[] CajaCandado = {"CandadoAbierto","CandadoCerrado"};
	
	private final ComponentBuilder cp = new ComponentBuilder(url, Fondo[m]);

	public RegistrarUsuario(String id){
		
		if (m==1){cp.setForeground(Color.WHITE);}
		
		int z = 0;
		Font Format = new Font("Clarendon Blk BT", Font.BOLD, 12);
		
		bupid = id;
		
		setBounds(20, 45, 960, 415);
		setOpaque(false);
		
		String[] Column = {ColumnaNombre[l][0],ColumnaNombre[l][1],ColumnaNombre[l][2],ColumnaNombre[l][3],ColumnaNombre[l][4],ColumnaNombre[l][5]};
        Object[][] Data;

		if (Cliente.length()!=0){
			
			Data = new Object[Cliente.length()][6];
			
			Cliente[] p = Cliente.get();

			for (int i=0; i<p.length; i++){

				Data[i][0] = p[i].getID();
				Data[i][1] = p[i].getName();
				Data[i][2] = p[i].getLastName();
				Data[i][3] = p[i].getPhone();
				Data[i][4] = p[i].getEmail();
				Data[i][5] = p[i].getAddress();

			}
			
			Tabla = getCustomTable(Data, Column);

		}else{
			
			Tabla = getCustomTable(Column);
			
		}
		
		ID = cp.buildLabel(IDTexto[l], cp.doBounds(30, 50, 50, 20), SwingConstants.LEFT, Format);
		Name = cp.buildLabel(NameTexto[l], cp.doBounds(30, 155, 70, 20), SwingConstants.LEFT, Format);
		LastName = cp.buildLabel(LastNameTexto[l], cp.doBounds(30, 260, 100, 20), SwingConstants.LEFT, Format);
		Phone = cp.buildLabel(PhoneTexto[l], cp.doBounds(180, 50, 120, 20), SwingConstants.LEFT, Format);
		Email = cp.buildLabel(EmailTexto[l], cp.doBounds(180, 155, 100, 20), SwingConstants.LEFT, Format);
		Address = cp.buildLabel(AddressTexto[l], cp.doBounds(180, 260, 100, 20), SwingConstants.LEFT, Format);
		Lock = cp.buildLabel("", cp.doBounds(260, 20, 22, 22), CajaCandado[0], 22, 22, SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER, null);	Format = new Font("Clarendon Blk BT", Font.PLAIN, 15);
		TextPanelID = cp.buildTextField(bupid, cp.doBounds(30, 95, 100, 20), SwingConstants.LEFT, Format, Color.BLUE, Color.BLUE, true, false);
		TextPanelName = cp.buildTextField("", cp.doBounds(30, 200, 100, 20), SwingConstants.LEFT, Format, Color.BLUE, Color.BLUE, true, false);
		TextPanelLastName = cp.buildTextField("", cp.doBounds(30, 305, 100, 20), SwingConstants.LEFT, Format, Color.BLUE, Color.BLUE, true, false);
		TextPanelPhone = cp.buildTextField("", cp.doBounds(180, 95, 100, 20), SwingConstants.LEFT, Format, Color.BLUE, Color.BLUE, true, false);
		TextPanelEmail = cp.buildTextField("", cp.doBounds(180, 200, 100, 20), SwingConstants.LEFT, Format, Color.BLUE, Color.BLUE, true, false);
		TextPanelAddress = cp.buildTextField("", cp.doBounds(180, 305, 100, 20), SwingConstants.LEFT, Format, Color.BLUE, Color.BLUE, true, false);
		TextPanelShow = cp.buildTextField("", cp.doBounds(370, 340, 480, 30), SwingConstants.CENTER, new Font("Clarendon Blk BT", Font.BOLD, 14), Color.BLUE, Color.BLUE, false, false);
		Erase = cp.buildButton(CajaBorrar[m], cp.doBounds(870, 340, 35, 35), true, false);
		Accept = cp.buildButton(CajaAceptar[m], cp.doBounds(60, 340, 35, 35), true, false);
		Cancel = cp.buildButton(CajaCancelar[m], cp.doBounds(210, 340, 35, 35), true, false);
		Edit = cp.buildButton(CajaEditar[m], cp.doBounds(30, 20, 22, 22), false, false);
		
		setLayout(null);
		setComponentZOrder(cp.buildJScrollPane(Tabla, cp.doBounds(325, 30, 600, 300)), z);	z++;
		setComponentZOrder(ID, z);	z++;
		setComponentZOrder(Name, z);z++;
		setComponentZOrder(LastName, z);z++;
		setComponentZOrder(Phone, z);	z++;
		setComponentZOrder(Email, z);	z++;
		setComponentZOrder(Address, z);	z++;
		setComponentZOrder(Lock, z);	z++;
		setComponentZOrder(TextPanelID, z);		z++;
		setComponentZOrder(TextPanelName, z);	z++;
		setComponentZOrder(TextPanelLastName, z);z++;
		setComponentZOrder(TextPanelPhone, z);	z++;
		setComponentZOrder(TextPanelEmail, z);	z++;
		setComponentZOrder(TextPanelAddress, z);z++;
		setComponentZOrder(TextPanelShow, z);	z++;
		setComponentZOrder(Erase, z);	z++;
		setComponentZOrder(Accept, z);	z++;
		setComponentZOrder(Cancel, z);	z++;
		setComponentZOrder(Edit, z);	z++;
		
		Tabla.setVisible(true);
		ID.setVisible(true);
		Name.setVisible(true);
		LastName.setVisible(true);
		Phone.setVisible(true);
		Email.setVisible(true);
		Address.setVisible(true);
		Lock.setVisible(true);
		TextPanelID.setVisible(true);
		TextPanelName.setVisible(true);
		TextPanelLastName.setVisible(true);
		TextPanelPhone.setVisible(true);
		TextPanelEmail.setVisible(true);
		TextPanelAddress.setVisible(true);
		TextPanelShow.setVisible(false);
		Erase.setVisible(true);
		Accept.setVisible(true);
		Cancel.setVisible(true);
		Edit.setVisible(true);
		
		Actions();
		
	}
	
	private Table getCustomTable(String[] ColumnName){
		
		Object[][] Data = new Object[1][ColumnName.length];
		
		for (int i=0; i<ColumnName.length; i++){
			
			Data[0][i] = 0;
			
		}
		
		Table Tabla = getCustomTable(Data, ColumnName);
		
		Tabla.setRowCount(0);
		
		return Tabla;
		
	}
	
	private Table getCustomTable(Object[][] Data, String[] ColumnName){
		
		Table Tabla = new Table(Data, ColumnName, false);
		
		for(int i=0; i<Tabla.getColumnCount(); i++){
			
			Tabla.getColumn(i).setHorizontalAlignment(SwingConstants.CENTER);
			Tabla.getColumn(i).setFocusCellBackground(Fondo[Math.abs(m-1)]);
			Tabla.getColumn(i).setDefaultForeground((m==0) ? Color.BLACK : Color.WHITE);
			Tabla.getColumn(i).setFocusCellForeground((m==0) ? Color.WHITE : Color.BLACK);
			
		}
		
		Tabla.getHeader().setBackground((m==0) ? Color.BLACK : Color.WHITE);
		Tabla.getHeader().setForeground((m==0) ? Color.WHITE : Color.BLACK);
		Tabla.getHeader().showInnerBorder(false);
		
		Tabla.setBackground(Fondo[m]);
		Tabla.setShowHorizontalLines(false);
		Tabla.setShowVerticalLines(false);
		
		return Tabla;
		
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
		
		Accept.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int indice = Tabla.getValueAtColumn(0, bupID);
				String bup = "";
				String ID = "";
				String Nombre = "";
				String Apellido = "";
				String Telefono = " ";
				String Email = " ";
				String Direccion = " ";
				Object[] NewRow = new Object[6];
				String[] ErrorMessages;

				boolean Pass = true;

				bup = TextPanelID.getText().trim();

				if (bup!=null && bup.equals("")==false && bup.contains(",")==false){

					if (Mecanics.Allowed(bup)==true){

						if (Double.parseDouble(bup)>0 && Double.parseDouble(bup)%1==0 && (bup.length()>=8 && bup.length()<=10)){
							
							ID = bup;

						}else{

							Pass = false;

							TextPanelID.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
							
							ErrorMessages = new String[] {"Formato ID No Reconocido", "ID Format Not Recognized"};
							Mecanics.txtErrorMessage(TextPanelID, ErrorMessages[l]);

						}

					}else{

						Pass = false;

						TextPanelID.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
						
						ErrorMessages = new String[] {"Valor Ingresado No Númerico", "Entered Value Not Numeric"};
						Mecanics.txtErrorMessage(TextPanelID, ErrorMessages[l]);

					}

				}else{

					TextPanelID.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
					
					if (bup.contains(",")==true){
						
						ErrorMessages = new String[] {"ID No Puede Contener Comas (,)", "ID Cannot Contain Commas (,)"};
						Mecanics.txtErrorMessage(TextPanelID, ErrorMessages[l]);
						
					}else{
						
						ErrorMessages = new String[] {"ID No Ser Vacio", "ID Cannot Be Empty"};
						Mecanics.txtErrorMessage(TextPanelID, ErrorMessages[l]);
						
					}

					Pass = false;

				}

				bup = TextPanelName.getText().trim();

				if (bup!=null && bup.equals("")==false && bup.contains(",")==false){
					
					Nombre = bup;

				}else{

					TextPanelName.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
					
					if (bup.contains(",")==true){
						
						ErrorMessages = new String[] {"Nombre No Puede Contener Comas (,)", "Name Cannot Contain Commas (,)"};
						Mecanics.txtErrorMessage(TextPanelName, ErrorMessages[l]);
						
					}else{
						
						ErrorMessages = new String[] {"Nombre No Ser Vacio", "Name Cannot be Empty"};
						Mecanics.txtErrorMessage(TextPanelName, ErrorMessages[l]);
						
					}

					Pass = false;

				}

				bup = TextPanelLastName.getText().trim();

				if (bup!=null && bup.equals("")==false && bup.contains(",")==false){

					if (Mecanics.Allowed(bup)==false){
						
						Apellido = bup;

					}else{

						Pass = false;

						TextPanelLastName.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
						
						ErrorMessages = new String[] {"Valor Ingresado No Puede Ser Númerico", "Entered Value Cannot Be Numeric"};
						Mecanics.txtErrorMessage(TextPanelLastName, ErrorMessages[l]);

					}

				}else{

					Pass = false;

					TextPanelLastName.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
					
					if (bup.contains(",")==true){
						
						ErrorMessages = new String[] {"Apellido No Puede Contener Comas (,)", "Last Name Cannot Contain Commas (,)"};
						Mecanics.txtErrorMessage(TextPanelLastName, ErrorMessages[l]);
						
					}else{
						
						ErrorMessages = new String[] {"Apellido No Ser Vacio", "Last Name Cannot be Empty"};
						Mecanics.txtErrorMessage(TextPanelLastName, ErrorMessages[l]);
						
					}

				}

				bup = TextPanelPhone.getText().trim();

				if (bup!=null && bup.equals("")==false && bup.contains(",")==false){

					if (Mecanics.Allowed(bup)==true){

						if (Double.parseDouble(bup)>0 && Double.parseDouble(bup)%1==0 && bup.length()==10){
							
							Telefono = bup;

						}else{

							Pass = false;

							TextPanelPhone.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
							
							ErrorMessages = new String[] {"Formato Contacto No Reconocido", "Contact Format Not Recognized"};
							Mecanics.txtErrorMessage(TextPanelPhone, ErrorMessages[l]);

						}

					}else{

						Pass = false;

						TextPanelPhone.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
						
						ErrorMessages = new String[] {"Valor Ingresado No Númerico", "Entered Value Not Numeric"};
						Mecanics.txtErrorMessage(TextPanelPhone, ErrorMessages[l]);

					}

				}else{
					
					if (bup.contains(",")==true){
						
						Pass = false;

						TextPanelPhone.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
						
						ErrorMessages = new String[] {"Contacto No Puede Contener Comas (,)", "Contact Cannot Contain Commas (,)"};
						Mecanics.txtErrorMessage(TextPanelPhone, ErrorMessages[l]);
						
					}
					
				}

				bup = TextPanelEmail.getText().trim();

				if (bup!=null && bup.equals("")==false && bup.contains(",")==false){

					if (Mecanics.ValidarEmail(bup)==true){
						
						Email = bup;

					}else{
						
						Pass = false;

						TextPanelEmail.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
						
						ErrorMessages = new String[] {"Debe Ingresar un Email", "Must Entered an Email"};
						Mecanics.txtErrorMessage(TextPanelEmail, ErrorMessages[l]);

					}

				}else{
					
					if (bup.contains(",")==true){
						
						Pass = false;

						TextPanelEmail.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
						
						ErrorMessages = new String[] {"Email No Puede Contener Comas (,)", "Email Cannot Contain Commas (,)"};
						Mecanics.txtErrorMessage(TextPanelEmail, ErrorMessages[l]);
						
					}
					
				}
				
				bup = TextPanelAddress.getText().trim();

				if (bup!=null && bup.equals("")==false){

					if (bup.contains(",")==false){
						
						Direccion = bup;

					}else{
						
						Pass = false;

						TextPanelAddress.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
						
						ErrorMessages = new String[] {"Dirección No Puede Contener Comas (,)", "Address Cannot Contain Commas (,)"};
						Mecanics.txtErrorMessage(TextPanelAddress, ErrorMessages[l]);

					}

				}

				if (ed==false){
					
					for (int i=0; i<Tabla.getRowCount(); i++){

						if (ID.equals(Tabla.getValueAt(i, 0).toString())==true){
							
							Pass = false;
							
							if (TextPanelID.isEditable()==true){TextPanelID.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));}
							
							break;

						}

					}

					if (Pass==true){

						NewRow[0] = ID;
						NewRow[1] = Nombre;
						NewRow[2] = Apellido;
						NewRow[3] = Telefono;
						NewRow[4] = Email;
						NewRow[5] = Direccion;
						
						new Cliente(Nombre,Apellido,ID,Telefono,Email,Direccion).add();
						
						Tabla.addRow(NewRow);
						Tabla.repaint();
						
					}

				}else{

					if (Pass==true){
						
						if (indice!=-1){
							
							Tabla.setValueAt(ID, indice, 0);
							Tabla.setValueAt(Nombre, indice, 1);
							Tabla.setValueAt(Apellido, indice, 2);
							Tabla.setValueAt(Telefono, indice, 3);
							Tabla.setValueAt(Email, indice, 4);
							Tabla.setValueAt(Direccion, indice, 5);
							
							Cliente p = Cliente.get(bupID);
							
							p.setID(ID);
							p.setName(Nombre);
							p.setLastName(Apellido);
							p.setPhone(Telefono);
							p.setEmail(Email);
							p.setAddress(Direccion);
							
							p.edit(bupID);
							
							ed = false;

						}

					}

				}

				if (Pass==true){
					
					Cancel.doClick();

				}

				repaint();

			}

		});
		
		Cancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){
				
				TextPanelID.setText("");
				TextPanelName.setText("");
				TextPanelLastName.setText("");
				TextPanelPhone.setText("");
				TextPanelEmail.setText("");
				TextPanelAddress.setText("");
				
				TextPanelID.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
				TextPanelName.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
				TextPanelLastName.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
				TextPanelPhone.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
				TextPanelEmail.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
				TextPanelAddress.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
				
				TextPanelID.setEditable(true);
				TextPanelName.setEditable(true);
				TextPanelLastName.setEditable(true);
				TextPanelPhone.setEditable(true);
				TextPanelEmail.setEditable(true);
				TextPanelAddress.setEditable(true);
				
				TextPanelShow.setVisible(false);
				Edit.setEnabled(false);
				
				ImageIcon icono = new ImageIcon(url+"/"+CajaCandado[0]+".png");
				Image imagen = icono.getImage();
				Image imagenRedimensionada = imagen.getScaledInstance(Lock.getWidth(), Lock.getHeight(), Image.SCALE_SMOOTH);
				Lock.setIcon(new ImageIcon(imagenRedimensionada));
				
				ed = false;
				
				bupID = "";
				
				Tabla.clearSelection();
				
				TextPanelID.requestFocus();

				repaint();

			}

		});
		
		Erase.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){
	
				int Row = Tabla.getSelectedRow();
				
				Cliente p = Cliente.get(Tabla.getValueAt(Row, 0).toString());
				
				if (Row!=-1 && p!=null){
					
					Tabla.removeRow(Row);
					
					p.remove();
					
					Cancel.doClick();
					
				}

				repaint();

			}

		});
		
		Edit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){
				
				TextPanelID.setEditable(true);
				TextPanelName.setEditable(true);
				TextPanelLastName.setEditable(true);
				TextPanelPhone.setEditable(true);
				TextPanelEmail.setEditable(true);
				TextPanelAddress.setEditable(true);
				
				TextPanelShow.setVisible(false);
				Edit.setEnabled(false);
				
				ImageIcon icono = new ImageIcon(url+"/"+CajaCandado[0]+".png");
				Image imagen = icono.getImage();
				Image imagenRedimensionada = imagen.getScaledInstance(Lock.getWidth(), Lock.getHeight(), Image.SCALE_SMOOTH);
				Lock.setIcon(new ImageIcon(imagenRedimensionada));
				
				TextPanelID.requestFocus();
				
				ed = true;
				
				Tabla.clearSelection();

				repaint();

			}

		});
		
		TextPanelID.addKeyListener(new KeyAdapter(){

            public void keyPressed(KeyEvent e){
				
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					
					TextPanelName.requestFocus();

				}else if (e.getKeyCode() == KeyEvent.VK_DOWN){
					
					TextPanelName.requestFocus();
					
				}else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
					
					TextPanelPhone.requestFocus();
					
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
		
		TextPanelName.addKeyListener(new KeyAdapter(){

            public void keyPressed(KeyEvent e){

				if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_DOWN){
					
					TextPanelLastName.requestFocus();

				}else if (e.getKeyCode() == KeyEvent.VK_UP){
					
					TextPanelID.requestFocus();
					
				}else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
					
					TextPanelEmail.requestFocus();
					
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
		
		TextPanelLastName.addKeyListener(new KeyAdapter(){

            public void keyPressed(KeyEvent e){

				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					
					TextPanelPhone.requestFocus();

				}else if (e.getKeyCode() == KeyEvent.VK_UP){
					
					TextPanelName.requestFocus();
					
				}else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
					
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
		
		TextPanelPhone.addKeyListener(new KeyAdapter(){

            public void keyPressed(KeyEvent e){

				if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_DOWN){
					
					TextPanelEmail.requestFocus();

				}else if (e.getKeyCode() == KeyEvent.VK_LEFT){
					
					TextPanelID.requestFocus();
					
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
		
		TextPanelEmail.addKeyListener(new KeyAdapter(){

            public void keyPressed(KeyEvent e){

				if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_DOWN){
					
					TextPanelAddress.requestFocus();

				}else if (e.getKeyCode() == KeyEvent.VK_UP){
					
					TextPanelPhone.requestFocus();
					
				}else if (e.getKeyCode() == KeyEvent.VK_LEFT){
					
					TextPanelName.requestFocus();
					
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
					
					Accept.doClick();
					
					TextPanelID.requestFocus();

				}else if (e.getKeyCode() == KeyEvent.VK_UP){
					
					TextPanelEmail.requestFocus();
					
				}else if (e.getKeyCode() == KeyEvent.VK_LEFT){
					
					TextPanelLastName.requestFocus();
					
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
		
		TextPanelID.getDocument().addDocumentListener (new DocumentListener(){

            public void insertUpdate(DocumentEvent e){
				
				SwingUtilities.invokeLater(() -> setTextPanelLimit(64));
				
				Cliente p = Cliente.get(TextPanelID.getText().trim());
				
				if (p!=null){
					
					bupID = p.getID();
					
					for (int i=0; i<Tabla.getRowCount(); i++){
						
						if (bupID.equals(Tabla.getValueAt(i, 0).toString())==true){
							
							Tabla.setRowSelectionInterval(i, i);
							
							break;
							
						}
						
					}
					
					TextPanelName.setText(p.getName());
					TextPanelLastName.setText(p.getLastName());
					TextPanelPhone.setText(p.getPhone().trim());
					TextPanelEmail.setText(p.getEmail().trim());
					TextPanelAddress.setText(p.getAddress().trim());
					TextPanelShow.setText("");
					
					TextPanelID.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
					TextPanelName.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
					TextPanelLastName.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
					TextPanelPhone.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
					TextPanelEmail.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
					TextPanelAddress.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
					
					requestFocusInWindow();
					
					TextPanelID.setEditable(false);
					TextPanelName.setEditable(false);
					TextPanelLastName.setEditable(false);
					TextPanelPhone.setEditable(false);
					TextPanelEmail.setEditable(false);
					TextPanelAddress.setEditable(false);
					
					TextPanelShow.setVisible(true);
					Edit.setEnabled(true);
					
					ImageIcon icono = new ImageIcon(url+"/"+CajaCandado[1]+".png");
					Image imagen = icono.getImage();
					Image imagenRedimensionada = imagen.getScaledInstance(Lock.getWidth(), Lock.getHeight(), Image.SCALE_SMOOTH);
					Lock.setIcon(new ImageIcon(imagenRedimensionada));
					
				}
				
				repaint();

            }

            public void removeUpdate(DocumentEvent e){

				repaint();

            }

            public void changedUpdate(DocumentEvent e){

				repaint();

            }
			
			private void setTextPanelLimit(int limite) {
				
                String Text = TextPanelID.getText();
				
                if (Text.length()>limite){
					
                    Text = Text.substring(0, limite);
                    TextPanelID.setText(Text);
					
                }
            }

        });
		
		TextPanelName.getDocument().addDocumentListener (new DocumentListener(){

            public void insertUpdate(DocumentEvent e){
				
				SwingUtilities.invokeLater(() -> setTextPanelLimit(64));

				repaint();

            }

            public void removeUpdate(DocumentEvent e){

				repaint();

            }

            public void changedUpdate(DocumentEvent e){

				repaint();

            }
			
			private void setTextPanelLimit(int limite) {
				
                String Text = TextPanelName.getText();
				
                if (Text.length()>limite){
					
                    Text = Text.substring(0, limite);
                    TextPanelName.setText(Text);
					
                }
            }

        });
		
		TextPanelLastName.getDocument().addDocumentListener (new DocumentListener(){

            public void insertUpdate(DocumentEvent e){
				
				SwingUtilities.invokeLater(() -> setTextPanelLimit(64));

				repaint();

            }

            public void removeUpdate(DocumentEvent e){

				repaint();

            }

            public void changedUpdate(DocumentEvent e){

				repaint();

            }
			
			private void setTextPanelLimit(int limite) {
				
                String Text = TextPanelLastName.getText();
				
                if (Text.length()>limite){
					
                    Text = Text.substring(0, limite);
                    TextPanelLastName.setText(Text);
					
                }
            }

        });
		
		TextPanelEmail.getDocument().addDocumentListener (new DocumentListener(){

            public void insertUpdate(DocumentEvent e){
				
				SwingUtilities.invokeLater(() -> setTextPanelLimit(64));

				repaint();

            }

            public void removeUpdate(DocumentEvent e){

				repaint();

            }

            public void changedUpdate(DocumentEvent e){

				repaint();

            }
			
			private void setTextPanelLimit(int limite) {
				
                String Text = TextPanelEmail.getText();
				
                if (Text.length()>limite){
					
                    Text = Text.substring(0, limite);
                    TextPanelEmail.setText(Text);
					
                }
            }

        });
		
		TextPanelAddress.getDocument().addDocumentListener (new DocumentListener(){

            public void insertUpdate(DocumentEvent e){
				
				SwingUtilities.invokeLater(() -> setTextPanelLimit(64));

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
		
		Tabla.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				repaint();

			}

			public void mouseClicked(MouseEvent e){

				if (e.getButton() == MouseEvent.BUTTON1){

					int row = Tabla.getSelectedRow();

					Object value = Tabla.getValueAt(row, 0);

					TextPanelID.setText(value.toString());

				}

				repaint();

			}

			public void mousePressed(MouseEvent e){

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				Tabla.clearFocus();
				
				repaint();

			}

		});
		
		Erase.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){

				Erase.setSize(45,45);

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				Erase.setSize(35,35);

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				repaint();

			}

		});

		Accept.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){

				Accept.setSize(45,45);

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				Accept.setSize(35,35);

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				repaint();

			}

		});

		Cancel.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){

				Cancel.setSize(45,45);

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				Cancel.setSize(35,35);

				repaint();

			}

			public void mouseExited(MouseEvent e) {

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

				Edit.setSize(32,32);

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				Edit.setSize(22,22);

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				repaint();

			}

		});
		
		TextPanelID.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				
				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){
				
				TextPanelShow.setText(TextPanelID.getText());

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				repaint();

			}

		});
		
		TextPanelName.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				
				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){
				
				TextPanelShow.setText(TextPanelName.getText());

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				repaint();

			}

		});
		
		TextPanelLastName.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				
				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){
				
				TextPanelShow.setText(TextPanelLastName.getText());

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				repaint();

			}

		});
		
		TextPanelPhone.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){
				
				TextPanelShow.setText(TextPanelPhone.getText());

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				repaint();

			}

		});
		
		TextPanelEmail.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){
				
				TextPanelShow.setText(TextPanelEmail.getText());

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				repaint();

			}

		});
		
		TextPanelAddress.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){
				
				TextPanelShow.setText(TextPanelAddress.getText());

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				repaint();

			}

		});
		
		/*this.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				repaint();

			}

			public void mouseClicked(MouseEvent e) {
				
				repaint();

			}

			public void mousePressed(MouseEvent e){
				
				Cancel.doClick();

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				repaint();

			}

		});*/
		
	}

}