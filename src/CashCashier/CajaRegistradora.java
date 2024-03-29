package CashCashier;

import Main.Mecanics;
import Objects.ComponentBuilder;
import Objects.Table;
import Objects.Factura;
import Objects.Cliente;
import Objects.Trabajador;
import Objects.Archivo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.EventObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.Timer;


public class CajaRegistradora extends JPanel{

	private int m = Mecanics.getMode();
	private int l = Mecanics.getLanguage();
	
	private int s = -1;

	private boolean ed = true;
	private boolean Searcher = true;
	private boolean Allow = true;
	private boolean Facturar = true;

	private String FacturaCode;
	private String ClientID;
	private String ClientName;
	private String ClientPay;
	private String ClientChange;
	private String ClientTotal;
	private String EmployeName;
	private Object[][] Data;
	
	private final Color[] Fondo = {new Color(238, 248, 254), new Color(20, 35, 54)};

	private JLabel Code;
	private JLabel Name;
	private JLabel Amount;
	private JLabel Price;
	private JLabel Total;
	private JLabel TotalPay;
	private JLabel Client;
	private JLabel Pay;
	private JLabel Change;
	private JLabel Unid;
	private JLabel MessageUnid;
	private JLabel Date;
	private JTextField TextPanelCode;
	private JTextField TextPanelName;
	private JTextField TextPanelAmount;
	private JTextField TextPanelPrice;
	private JTextField TextPanelTotal;
	private JTextField TextPanelTotalPay;
	private JTextField TextPanelClient;
	public JTextField TextPanelID;
	private JTextField TextPanelPay;
	private JTextField TextPanelChange;
	private JButton Erase;
	private JButton Accept;
	private JButton Cancel;
	private JButton Add;
	private JButton Check;
	private JButton Print;

	private Table Tabla;

	private String[] CodeTexto = {"Código","Code"};
	private String[] NameTexto = {"Producto","Product"};
	private String[] AmountTexto = {"Cantidad","Amount"};
	private String[] PriceTexto = {"Precio","Price"};
	private String[] TotalTexto = {"Total","Total"};
	private String[] TotalPayTexto = {"Total a pagar","Total Bill"};
	private String[] ClientTexto = {"Cliente:","Client:"};
	private String[] AddTexto = {"Nuevo Cliente","New Client"};
	private String[] CheckTexto = {"Facturar","Check In"};
	private String[] PrintTexto = {"Facturar e Imprimir","Check In and Print"};
	private String[] PayTexto = {"Pago","Payment"};
	private String[] ChangeTexto = {"Vuelto","Change"};

	private String[][] ColumnaNombre = {{"Código","Producto","Cantidad","Precio","Total"},{"Code","Product","Amount","Price","Total"}};
	
	private String[] CajaBorrar = {"BorrarLight","BorrarDark"};
	private String[] CajaAceptar = {"AceptarLight","AceptarDark"};
	private String[] CajaCancelar = {"CancelarLight","CancelarDark"};
	private String[] CajaCliente = {"LupaLight","LupaDark"};
	private String[] CajaAgregar = {"AgregarLight","AgregarDark"};
	private String[] CajaFacturar = {"FacturarLight","FacturarDark"};
	private String[] CajaImprimir = {"ImprimirLight","ImprimirDark"};
	
	private final ComponentBuilder cp = new ComponentBuilder("./src/ResourcePackCaja", Fondo[m]);

	public CajaRegistradora(boolean a, Factura p){
		
		if (m==1){cp.setForeground(Color.WHITE);}
		
		int z = 0;
		final Color Money = new Color(60, 133, 100);
		Font Format = new Font("Clarendon Blk BT", Font.BOLD, 12);
		
		FacturaCode = p.getCode();
		ClientID = p.getClient().getID();
		ClientName = p.getClient().getName();
		ClientPay = p.getPay()+"";
		
		if (p.getPay()==0){ClientPay="";}
		
		ClientChange = p.getChange()+"$";
		
		if (p.getChange()==0 && p.getPay()==0){ClientChange="";}
		
		ClientTotal = p.getTotal()+"$";
		EmployeName = p.getEmploye().getCode();
		Data = p.getBuyout();
		Allow = a;

		setBounds(20, 45, 960, 415);
		setOpaque(false);
		
		String[] Column = {ColumnaNombre[l][0],ColumnaNombre[l][1],ColumnaNombre[l][2],ColumnaNombre[l][3],ColumnaNombre[l][4]};

		if (Allow==true){
			
			Tabla = getCustomTable(Column);
			
		}else{
			
			Tabla = getCustomTable(Data, Column);
			
		}
		
		Code = cp.buildLabel(CodeTexto[l], cp.doBounds(30, 60, 50, 20), SwingConstants.LEFT, Format);
		Name = cp.buildLabel(NameTexto[l], cp.doBounds(210, 60, 70, 20), SwingConstants.LEFT, Format);
		Amount = cp.buildLabel(AmountTexto[l], cp.doBounds(390, 60, 50, 20), SwingConstants.LEFT, Format);
		Price = cp.buildLabel(PriceTexto[l], cp.doBounds(565, 60, 50, 20), SwingConstants.LEFT, Format);
		Total = cp.buildLabel(TotalTexto[l], cp.doBounds(740, 60, 100, 20), SwingConstants.LEFT, Format);
		TotalPay = cp.buildLabel(TotalPayTexto[l], cp.doBounds(740, 310, 100, 20), SwingConstants.LEFT, Format);
		Client = cp.buildLabel(ClientTexto[l], cp.doBounds(30, 5, 70, 40), CajaCliente[m], 20, 20, JButton.CENTER, JButton.RIGHT, JButton.LEFT, Format);
		Pay = cp.buildLabel(PayTexto[l], cp.doBounds(300, 310, 100, 20), SwingConstants.LEFT, Format);
		Change = cp.buildLabel(ChangeTexto[l], cp.doBounds(500, 310, 100, 20), SwingConstants.LEFT, Format);
		Unid = cp.buildLabel("", cp.doBounds(490, 105, 30, 20), SwingConstants.CENTER, Format);
		MessageUnid = cp.buildLabel("", cp.doBounds(390, 125, 220, 20), SwingConstants.LEFT, Format);
		Date = cp.buildLabel("", cp.doBounds(565, 10, 220, 20), SwingConstants.CENTER, new Font("Clarendon Blk BT", Font.BOLD, 14));	Format = new Font("Clarendon Blk BT", Font.PLAIN, 15);
		TextPanelCode = cp.buildTextField("", cp.doBounds(30, 105, 100, 20), SwingConstants.LEFT, Format, Color.BLUE, Color.BLUE, Allow, false);
		TextPanelName = cp.buildTextField("", cp.doBounds(210, 105, 100, 20), SwingConstants.LEFT, Format, Color.BLUE, Color.BLUE, Allow, false);
		TextPanelAmount = cp.buildTextField("", cp.doBounds(390, 105, 100, 20), SwingConstants.LEFT, Format, Color.BLUE, Color.BLUE, true, false);
		TextPanelPrice = cp.buildTextField("", cp.doBounds(565, 105, 100, 20), SwingConstants.LEFT, Format, Color.BLUE, new Color(0, 0, 0, 0), false, false);	Format = new Font("Clarendon Blk BT", Font.PLAIN, 13);
		TextPanelTotal = cp.buildTextField("", cp.doBounds(740, 105, 100, 20), SwingConstants.LEFT, Format, Color.BLUE, new Color(0, 0, 0, 0), false, false);
		TextPanelTotalPay = cp.buildTextField(ClientTotal, cp.doBounds(740, 355, 100, 20), SwingConstants.LEFT, Format, Money, new Color(0, 0, 0, 0), false, false);
		TextPanelID = cp.buildTextField(ClientID.trim(), cp.doBounds(40+Client.getWidth(), 15, 100, 20), SwingConstants.LEFT, Format, Color.BLUE, Color.BLUE, Allow, false);
		TextPanelClient = cp.buildTextField(ClientName, cp.doBounds(50+Client.getWidth()+TextPanelID.getWidth(), 15, 200, 20), SwingConstants.CENTER, Format, Color.BLUE, new Color(0, 0, 0, 0), false, false);
		TextPanelPay = cp.buildTextField(ClientPay, cp.doBounds(300, 355, 100, 20), SwingConstants.LEFT, Format, Money, Money, true, false);
		TextPanelChange = cp.buildTextField(ClientChange, cp.doBounds(500, 355, 100, 20), SwingConstants.LEFT, Format, Money, new Color(0, 0, 0, 0), false, false);
		Erase = cp.buildButton(CajaBorrar[m], cp.doBounds(870, 310, 35, 35), true, false);
		Accept = cp.buildButton(CajaAceptar[m], cp.doBounds(870, 50, 35, 35), true, false);
		Cancel = cp.buildButton(CajaCancelar[m], cp.doBounds(870, 95, 35, 35), true, false);
		Add = cp.buildButton("", cp.doBounds(65+Client.getWidth()+TextPanelID.getWidth()+TextPanelClient.getWidth(), 20, 120, 20), CajaAgregar[m], JButton.CENTER, JButton.RIGHT, JButton.LEFT, true, false);
		Check = cp.buildButton("", cp.doBounds(30, 305, 120, 35), CajaFacturar[m], 60, 35, JButton.CENTER, JButton.RIGHT, JButton.LEFT, true, false);
		Print = cp.buildButton("", cp.doBounds(30, 350, 190, 35), CajaImprimir[m], 60, 35, JButton.CENTER, JButton.RIGHT, JButton.LEFT, true, false);

		setLayout(null);
		setComponentZOrder(cp.buildJScrollPane(Tabla, cp.doBounds(20, 150, 900, 150)), z);	z++;
		setComponentZOrder(Code, z);	z++;
		setComponentZOrder(Name, z);	z++;
		setComponentZOrder(Amount, z);	z++;
		setComponentZOrder(Price, z);	z++;
		setComponentZOrder(Total, z);	z++;
		setComponentZOrder(TotalPay, z);z++;
		setComponentZOrder(Client, z);	z++;
		setComponentZOrder(Pay, z);		z++;
		setComponentZOrder(Change, z);	z++;
		setComponentZOrder(Unid, z);	z++;
		setComponentZOrder(MessageUnid, z);	z++;
		setComponentZOrder(Date, z);	z++;
		setComponentZOrder(TextPanelCode, z);	z++;
		setComponentZOrder(TextPanelName, z);	z++;
		setComponentZOrder(TextPanelAmount, z);z++;
		setComponentZOrder(TextPanelPrice, z);	z++;
		setComponentZOrder(TextPanelTotal, z);	z++;
		setComponentZOrder(TextPanelTotalPay, z);z++;
		setComponentZOrder(TextPanelID, z);	z++;
		setComponentZOrder(TextPanelClient, z);z++;
		setComponentZOrder(TextPanelPay, z);	z++;
		setComponentZOrder(TextPanelChange, z);z++;
		setComponentZOrder(Erase, z);	z++;
		setComponentZOrder(Accept, z);	z++;
		setComponentZOrder(Cancel, z);	z++;
		setComponentZOrder(Add, z);	z++;
		setComponentZOrder(Check, z);	z++;
		setComponentZOrder(Print, z);	z++;

		Tabla.setVisible(true);
		Code.setVisible(true);
		Name.setVisible(true);
		Amount.setVisible(true);
		Price.setVisible(true);
		Total.setVisible(true);
		TotalPay.setVisible(true);
		Client.setVisible(true);
		Unid.setVisible(true);
		MessageUnid.setVisible(true);
		Date.setVisible(true);
		TextPanelCode.setVisible(true);
		TextPanelName.setVisible(true);
		TextPanelAmount.setVisible(true);
		TextPanelPrice.setVisible(true);
		TextPanelTotal.setVisible(true);
		TextPanelTotalPay.setVisible(true);
		TextPanelID.setVisible(true);
		TextPanelClient.setVisible(true);
		TextPanelPay.setVisible(true);
		TextPanelChange.setVisible(true);
		Erase.setVisible(true);
		Accept.setVisible(true);
		Cancel.setVisible(true);
		Add.setVisible(true);
		Check.setVisible(true);
		Print.setVisible(true);
		Pay.setVisible(true);
		Change.setVisible(true);

		Actions();

		setVisible(true);
		
		repaint();

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
		
		Tabla.getColumn(3).setDefaultForeground(new Color(60, 133, 100));
		Tabla.getColumn(4).setDefaultForeground(new Color(60, 133, 100));
		
		Tabla.getColumn(3).setFocusCellBackground(new Color(60, 133, 100));
		Tabla.getColumn(4).setFocusCellBackground(new Color(60, 133, 100));
		
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

				String bup = "";
				String Codigo = "";
				String Producto = "";
				float Cantidad = 0;
				float Precio = 0;
				float plus = 0;
				double Total = 0;
				Object[] NewRow = new Object[5];
				Archivo p;
				
				String[] ErrorMessages;

				boolean Pass = true;

				Codigo = TextPanelCode.getText().trim().toUpperCase();

				if (Codigo!=null && Codigo.equals("")==false){

					TextPanelCode.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));

				}else{

					TextPanelCode.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
					
					ErrorMessages = new String[] {"Debe Ingresar un Código", "Must Enter a Code"};
					
					Mecanics.txtErrorMessage(TextPanelCode, ErrorMessages[l]);

					Pass = false;

				}

				TextPanelCode.revalidate();
				TextPanelCode.repaint();

				Producto = TextPanelName.getText().trim();

				if (Producto!=null && Producto.equals("")==false){

					TextPanelName.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));

				}else{

					TextPanelName.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
					
					ErrorMessages = new String[] {"Debe Ingresar un Nombre", "Must Enter a Name"};
					
					Mecanics.txtErrorMessage(TextPanelName, ErrorMessages[l]);

					Pass = false;

				}

				TextPanelName.revalidate();
				TextPanelName.repaint();

				bup = TextPanelAmount.getText().trim();

				if (bup!=null && bup.equals("")==false){

					if (Mecanics.Allowed(bup)==true){

						if (Double.parseDouble(bup)>0){

							Cantidad = Float.parseFloat(bup);

							TextPanelAmount.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));

						}else{

							Pass = false;

							TextPanelAmount.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
							
							ErrorMessages = new String[] {"Valor Ingresado no Positvo", "Entered Value not Positive"};
					
							Mecanics.txtErrorMessage(TextPanelAmount, ErrorMessages[l]);

						}

					}else{

						Pass = false;

						TextPanelAmount.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
						
						ErrorMessages = new String[] {"Valor Ingresado no Numerico", "Entered Value not Numeric"};
					
						Mecanics.txtErrorMessage(TextPanelAmount, ErrorMessages[l]);

					}

				}else{

					Pass = false;

					TextPanelAmount.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
					
					ErrorMessages = new String[] {"Debe Ingresar una Cantidad", "Must Enter an Amount"};
					
					Mecanics.txtErrorMessage(TextPanelAmount, ErrorMessages[l]);

				}

				TextPanelAmount.revalidate();
				TextPanelAmount.repaint();

				bup = TextPanelPrice.getText().trim();

				if (bup!=null && bup.equals("")==false){

					bup = Mecanics.DeleteChar(bup,bup.length()-1);

					if (Mecanics.Allowed(bup)==true){

						if (Double.parseDouble(bup)>0){

							Precio = Float.parseFloat(bup);

							Total = Cantidad*Precio;

							TextPanelPrice.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));

						}else{

							Pass = false;

							TextPanelPrice.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));

						}

					}else{

						Pass = false;

						TextPanelPrice.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));

					}

				}else{

					Pass = false;

					TextPanelPrice.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));

				}

				TextPanelPrice.revalidate();
				TextPanelPrice.repaint();

				if (Allow==true && Pass==true){
					
					p = Archivo.get(Codigo);
					
					if (((p!=null) ? p.getProduct().equalsIgnoreCase(Producto) : false) && p!=null){
						
						Codigo = p.getCode();
						Producto = p.getProduct();

						TextPanelCode.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
						TextPanelName.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
						
					}else{
						
						TextPanelCode.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
						TextPanelName.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
						
						Pass = false;
						
					}

				}
				
				p = Archivo.get(Codigo);
				
				if (p!=null){
					
					if (Codigo.equalsIgnoreCase(p.getCode())==true){
					
						for (Object[] q : Data){
							
							if (Codigo.equalsIgnoreCase(q[0].toString())==true && Allow==false){
							
								plus = Float.parseFloat(q[2].toString());
								
								break;
								
							}else{
								
								plus = 0;
								
							}
							
						}
						
					}
					
					if (p.getUnid().equalsIgnoreCase("u") && Cantidad%1!=0){
						
						Pass = false;
					
						TextPanelAmount.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
						
						ErrorMessages = new String[] {" No Puede Ser Decimal", " Cannot Be Decimal"};
					
						Mecanics.txtErrorMessage(TextPanelAmount, p.getProduct()+ErrorMessages[l]);
						
					}else if (((plus+p.getAmount())-Cantidad)<0){
						
						Pass = false;
						
						TextPanelAmount.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
						
						ErrorMessages = new String[] {" Disponible", " Availiable"};
					
						Mecanics.txtErrorMessage(TextPanelAmount, (plus+p.getAmount())+" "+p.getUnid()+ErrorMessages[l]);
						
					}else{
						
						if (Pass!=false){
							
							TextPanelAmount.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
							
						}
						
					}
					
				}

				if (ed==true){

					if (Pass==true){

						NewRow[0] = Codigo;
						NewRow[1] = Producto;
						NewRow[2] = Cantidad;
						NewRow[3] = Precio+"$";
						NewRow[4] = Total+"$";

						for (int i=0; i<Tabla.getRowCount(); i++){

							if (Codigo.equals(Tabla.getValueAt(i, 0).toString())==true){

								bup = Tabla.getValueAt(i, 2).toString();

								Tabla.setValueAt((Float.parseFloat(bup)+Cantidad)+"", i, 2);

								bup = Tabla.getValueAt(i, 4).toString();

								Tabla.setValueAt((Float.parseFloat(Mecanics.DeleteChar(bup,bup.length()-1))+Total)+"$", i, 4);

								Pass = false;

								break;

							}else{

								Pass = true;

							}

						}

						if (Pass==true){

							Tabla.insertRow(0, NewRow);
							Tabla.clearSelection();
							Tabla.repaint();
							
						}
						
						Pass = true;
						
					}

				}else{

					if (Pass==true){
						
						for (int i=0; i<Tabla.getRowCount(); i++){

							if (Codigo.equals(Tabla.getValueAt(i, 0).toString())==true){

								Tabla.setValueAt((Cantidad)+"", i, 2);

								bup = Tabla.getValueAt(i, 4).toString();

								Tabla.setValueAt((Total)+"$", i, 4);
								
								break;

							}

						}
						
						Pass = true;

					}

				}

				if (Pass==true){

					Total = 0;

					for (int i=0; i<Tabla.getRowCount(); i++){

						bup = Tabla.getValueAt(i, 4).toString();

						Total+= Double.parseDouble(Mecanics.DeleteChar(bup,bup.length()-1));

					}

					TextPanelCode.setText("");
					TextPanelName.setText("");
					TextPanelAmount.setText("");
					TextPanelPrice.setText("");
					TextPanelTotal.setText("");
					MessageUnid.setText("");
					Unid.setText("");
					TextPanelTotalPay.setText(Total+"$");
					bup = TextPanelPay.getText();
					TextPanelPay.setText(bup+" ");
					TextPanelPay.setText(bup);

					TextPanelCode.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
					TextPanelName.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
					TextPanelAmount.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
					TextPanelPrice.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
					TextPanelTotal.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));

					TextPanelCode.repaint();
					TextPanelName.repaint();
					TextPanelAmount.repaint();
					TextPanelPrice.repaint();
					TextPanelTotal.repaint();
					TextPanelTotalPay.repaint();
					
				}

				repaint();

			}

		});

		Cancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){

				double Total = 0;
				String bup = "";

				if (ed==true){

					TextPanelCode.setText("");
					TextPanelName.setText("");
					TextPanelAmount.setText("");
					TextPanelPrice.setText("");
					TextPanelTotal.setText("");
					MessageUnid.setText("");
					Unid.setText("");

					TextPanelCode.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
					TextPanelName.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
					TextPanelAmount.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
					TextPanelPrice.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
					TextPanelTotal.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));

					TextPanelCode.repaint();
					TextPanelName.repaint();
					TextPanelAmount.repaint();
					TextPanelPrice.repaint();
					TextPanelTotal.repaint();

				}else{

					Tabla.removeRow(Tabla.getSelectedRow());

					TextPanelCode.setText("");
					TextPanelName.setText("");
					TextPanelAmount.setText("");
					TextPanelPrice.setText("");
					TextPanelTotal.setText("");
					MessageUnid.setText("");
					Unid.setText("");

					TextPanelCode.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
					TextPanelName.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
					TextPanelAmount.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
					TextPanelPrice.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
					TextPanelTotal.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));

					TextPanelCode.repaint();
					TextPanelName.repaint();
					TextPanelAmount.repaint();
					TextPanelPrice.repaint();
					TextPanelTotal.repaint();

				}

				if (Tabla.getRowCount()>=1){

					Total = 0;

					for (int i=0; i<Tabla.getRowCount(); i++){

						bup = Tabla.getValueAt(i, 4).toString();

						Total+= Double.parseDouble(Mecanics.DeleteChar(bup,bup.length()-1));
						
					}
					
				}else{
					
					Erase.doClick();
					
				}

				TextPanelTotalPay.setText(Total+"$");
				bup = TextPanelPay.getText();
				TextPanelPay.setText(bup+" ");
				TextPanelPay.setText(bup);

				repaint();

			}

		});

		Erase.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){


				Tabla.setRowCount(0);

				TextPanelCode.setText("");
				TextPanelName.setText("");
				TextPanelAmount.setText("");
				TextPanelPrice.setText("");
				TextPanelTotal.setText("");
				TextPanelID.setText("");
				TextPanelClient.setText("");
				TextPanelPay.setText("");
				TextPanelChange.setText("");
				MessageUnid.setText("");
				Unid.setText("");
				TextPanelTotalPay.setText("0.0$");

				TextPanelCode.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
				TextPanelName.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
				TextPanelAmount.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
				TextPanelPrice.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
				TextPanelTotal.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
				TextPanelPay.setBorder(new MatteBorder(0, 0, 2, 0, new Color(60, 133, 100)));
				TextPanelChange.setBorder(new MatteBorder(0, 0, 2, 0, new Color(60, 133, 100)));

				TextPanelCode.repaint();
				TextPanelName.repaint();
				TextPanelAmount.repaint();
				TextPanelPrice.repaint();
				TextPanelTotal.repaint();
				TextPanelID.repaint();
				TextPanelClient.repaint();
				TextPanelPay.repaint();
				TextPanelChange.repaint();
				TextPanelTotalPay.repaint();
				
				Allow = true;
				EmployeName = Trabajador.get(Dashboard.User).getCode();
				TextPanelID.setEditable(true);
				TextPanelCode.setEditable(true);
				TextPanelName.setEditable(true);

				repaint();

			}

		});

		Check.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){

				CheckIn();

			}

		});

		Print.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){

				Factura p = CheckIn();
				
				if (p!=null){
					
					Mecanics.Facturar(p);
					
				}

				repaint();

			}

		});
		
		Add.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){

				String bup = TextPanelID.getText().trim();
				String bup2 = TextPanelClient.getText().trim();
				
				if ((bup!=null && bup.equals("")==false) && (bup2.equals("")==true || bup2==null)){
					
					Dashboard.CRUD = new RegistrarUsuario(bup);
					
					Dashboard.contentPane.removeAll();
				
					Dashboard.WindowSelected.setBounds(270, 21, 225, 50);
					
					Dashboard.contentPane.add(Dashboard.Background, Integer.valueOf(0));
					Dashboard.contentPane.add(Dashboard.CRUD, Integer.valueOf(1));
					Dashboard.contentPane.add(Dashboard.Window1st, Integer.valueOf(2));
					Dashboard.contentPane.add(Dashboard.Window2nd, Integer.valueOf(3));
					Dashboard.contentPane.add(Dashboard.Window3rd, Integer.valueOf(4));
					Dashboard.contentPane.add(Dashboard.Window4th, Integer.valueOf(5));
					Dashboard.contentPane.add(Dashboard.WindowSelected, Integer.valueOf(6));
					
					repaint();
					
				}

				repaint();

			}

		});
		
		ActionListener TimerDate = new ActionListener(){
			
            public void actionPerformed (ActionEvent e){
				
                LocalDateTime Hora = LocalDateTime.now();
                String formattedDateTime = Hora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss"));
                Date.setText(formattedDateTime);
				
				repaint();
				
            }
			
        };
		
		Timer timer = new Timer(1000, TimerDate);
        timer.start();

		TextPanelID.addKeyListener(new KeyAdapter(){

            public void keyPressed(KeyEvent e){

				if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_DOWN){

					ed = true;
					Searcher = true;

					TextPanelCode.requestFocus();

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

		TextPanelCode.addKeyListener(new KeyAdapter(){

            public void keyPressed(KeyEvent e){

				if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_RIGHT){

					TextPanelAmount.requestFocus();

				}else if (e.getKeyCode() == KeyEvent.VK_DOWN){
					
					TextPanelPay.requestFocus();
					
				}else if (e.getKeyCode() == KeyEvent.VK_UP){
					
					TextPanelID.requestFocus();
					
				}

				repaint();

            }

			public void keyTyped(KeyEvent e){

				repaint();

			}

			public void keyReleased(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					Accept.setSize(35,35);

				}

                repaint();

            }

        });

		TextPanelName.addKeyListener(new KeyAdapter(){

            public void keyPressed(KeyEvent e){

				if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_RIGHT){

					TextPanelAmount.requestFocus();

				}else if (e.getKeyCode() == KeyEvent.VK_DOWN){
					
					TextPanelPay.requestFocus();
					
				}else if (e.getKeyCode() == KeyEvent.VK_UP){
					
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

		TextPanelAmount.addKeyListener(new KeyAdapter(){

            public void keyPressed(KeyEvent e){

				if (e.getKeyCode() == KeyEvent.VK_ENTER){

					Accept.doClick();

					Accept.setSize(45,45);

					if (Searcher==true){
						
						TextPanelCode.requestFocus();
						
					}else{
						
						TextPanelName.requestFocus();
						
					}

				}else if (e.getKeyCode() == KeyEvent.VK_DOWN){
					
					TextPanelPay.requestFocus();
					
				}else if (e.getKeyCode() == KeyEvent.VK_LEFT){
					
					if (Searcher==true){
						
						TextPanelCode.requestFocus();
						
					}else{
						
						TextPanelName.requestFocus();
						
					}
					
				}else if (e.getKeyCode() == KeyEvent.VK_UP){
					
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
		
		TextPanelPay.addKeyListener(new KeyAdapter(){

            public void keyPressed(KeyEvent e){

				if (e.getKeyCode() == KeyEvent.VK_UP){
					
					if (Searcher==true){
						
						TextPanelCode.requestFocus();
						
					}else{
						
						TextPanelName.requestFocus();
						
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

		TextPanelCode.getDocument().addDocumentListener (new DocumentListener(){

            public void insertUpdate (DocumentEvent e){

				if (Searcher==true){

					TextPanelName.setText("");
					TextPanelAmount.setText("");
					TextPanelPrice.setText("");
					TextPanelTotal.setText("");
					MessageUnid.setText("");
					Unid.setText("");

					TextPanelName.repaint();
					TextPanelAmount.repaint();
					TextPanelPrice.repaint();
					TextPanelTotal.repaint();
					
					Archivo p = Archivo.get(TextPanelCode.getText().trim().toUpperCase());
					
					if (p!=null){
						
						TextPanelName.setText(p.getProduct()+"");
						TextPanelPrice.setText(p.getPrice()+"$");
						Unid.setText(p.getUnid());

						TextPanelName.repaint();
						TextPanelPrice.repaint();
						
					}//*/
					
					/*Archivo[] q = Archivo.get();

					for (Archivo p : q){

						if (p.getCode().equalsIgnoreCase(TextPanelCode.getText().trim())==true){

							TextPanelName.setText(p.getProduct()+"");
							TextPanelPrice.setText(p.getPrice()+"$");
							Unid.setText(p.getUnid());

							TextPanelCode.repaint();
							TextPanelPrice.repaint();

							break;

						}

					}//*/
					
				}

				ed = true;

				repaint();

            }

            public void removeUpdate (DocumentEvent e){

				if (Searcher==true){

					TextPanelName.setText("");
					TextPanelAmount.setText("");
					TextPanelPrice.setText("");
					TextPanelTotal.setText("");
					MessageUnid.setText("");
					Unid.setText("");

					TextPanelName.repaint();
					TextPanelAmount.repaint();
					TextPanelPrice.repaint();
					TextPanelTotal.repaint();

				}

				insertUpdate(e);

				ed = true;

				repaint();

            }

            public void changedUpdate (DocumentEvent e){

				ed = true;

				repaint();

            }

        });

		TextPanelName.getDocument().addDocumentListener (new DocumentListener(){

            public void insertUpdate(DocumentEvent e){

				if (Searcher==false){

					TextPanelCode.setText("");
					TextPanelAmount.setText("");
					TextPanelPrice.setText("");
					TextPanelTotal.setText("");
					MessageUnid.setText("");
					Unid.setText("");

					TextPanelCode.repaint();
					TextPanelAmount.repaint();
					TextPanelPrice.repaint();
					TextPanelTotal.repaint();
					
					Archivo[] q = Archivo.get();

					for (Archivo p : q){

						if (p.getProduct().equalsIgnoreCase(TextPanelName.getText().trim())==true){

							TextPanelCode.setText(p.getCode()+"");
							TextPanelPrice.setText(p.getPrice()+"$");
							Unid.setText(p.getUnid());

							TextPanelCode.repaint();
							TextPanelPrice.repaint();

							break;

						}

					}

				}

				ed = true;

				repaint();

            }

            public void removeUpdate(DocumentEvent e){

				if (Searcher==false){

					TextPanelCode.setText("");
					TextPanelAmount.setText("");
					TextPanelPrice.setText("");
					TextPanelTotal.setText("");
					MessageUnid.setText("");
					Unid.setText("");

					TextPanelCode.repaint();
					TextPanelAmount.repaint();
					TextPanelPrice.repaint();
					TextPanelTotal.repaint();

				}

				insertUpdate(e);

				ed = true;

				repaint();

            }

            public void changedUpdate(DocumentEvent e){

				ed = true;

				repaint();

            }

        });

		TextPanelAmount.getDocument().addDocumentListener (new DocumentListener(){

            public void insertUpdate (DocumentEvent e){

				boolean Pass = true;
				float Cantidad = 0;
				float Precio = 0;
				String bup = TextPanelAmount.getText().trim();

				if (bup!=null && bup.equals("")==false){

					if (Mecanics.Allowed(bup)==true){

						if (Double.parseDouble(bup)>0){

							Cantidad = Float.parseFloat(bup);

						}else{

							Pass = false;

						}

					}else{

						Pass = false;

					}

				}else{

					Pass = false;

				}

				bup = TextPanelPrice.getText().trim();

				if (bup!=null && bup.equals("")==false){

					bup = Mecanics.DeleteChar(bup,bup.length()-1);

					if (Mecanics.Allowed(bup)==true){

						if (Double.parseDouble(bup)>0){

							Precio = Float.parseFloat(bup);

						}else{

							Pass = false;

						}

					}else{

						Pass = false;

					}

				}else{

					Pass = false;

				}

				if (Pass==true){

					TextPanelTotal.setText((Cantidad*Precio)+"$");
					TextPanelTotal.revalidate();
					TextPanelTotal.repaint();

				}

				repaint();

            }

            public void removeUpdate (DocumentEvent e){

				boolean Pass = true;
				float Cantidad = 0;
				float Precio = 0;
				String bup = TextPanelAmount.getText().trim();

				TextPanelTotal.setText("");
				TextPanelTotal.revalidate();
				TextPanelTotal.repaint();

				if (bup==null || bup.equals("")==true){bup="0$";}

				if (bup!=null && bup.equals("")==false){

					if (Mecanics.Allowed(bup)==true){

						if (Double.parseDouble(bup)>0){

							Cantidad = Float.parseFloat(bup);

						}else{

							Pass = false;

						}

					}else{

						Pass = false;

					}

				}else{

					Pass = false;

				}

				bup = TextPanelPrice.getText().trim();

				if (bup!=null && bup.equals("")==false){

					bup = Mecanics.DeleteChar(bup,bup.length()-1);

					if (Mecanics.Allowed(bup)==true){

						if (Double.parseDouble(bup)>0){

							Precio = Float.parseFloat(bup);

						}else{

							Pass = false;

						}

					}else{

						Pass = false;

					}

				}else{

					Pass = false;

				}

				if (Pass==true){

					TextPanelTotal.setText((Cantidad*Precio)+"$");
					TextPanelTotal.revalidate();
					TextPanelTotal.repaint();

				}

				repaint();

            }

            public void changedUpdate (DocumentEvent e){

				repaint();

            }

        });

		TextPanelTotal.getDocument().addDocumentListener (new DocumentListener(){

            public void insertUpdate(DocumentEvent e){

				repaint();

            }

            public void removeUpdate(DocumentEvent e){

				repaint();

            }

            public void changedUpdate(DocumentEvent e){

				repaint();

            }

        });

		TextPanelID.getDocument().addDocumentListener (new DocumentListener(){

			public void insertUpdate (DocumentEvent e){

				TextPanelClient.setText("");
				TextPanelClient.repaint();

				boolean Pass = true;
				Cliente p;
				String ID = "";
				String[] ErrorMessages;

				String bup = TextPanelID.getText().trim();

				if (bup!=null && bup.equals("")==false){

					if (Mecanics.Allowed(bup)==true){

						if (Double.parseDouble(bup)%1==0 && Double.parseDouble(bup)>0){

							ID = bup;

							TextPanelID.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));

						}else{

							Pass = false;

							TextPanelID.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
							
							ErrorMessages = new String[] {"ID No Puede Ser Decimal o Negativo", "ID Cannot Be Decimal or Nevative"};
							Mecanics.txtErrorMessage(TextPanelID, ErrorMessages[l]);

						}

					}else{

						Pass = false;

						TextPanelID.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
						
						ErrorMessages = new String[] {"Valor Ingresado No Númerico", "Entered Value Not Numeric"};
						Mecanics.txtErrorMessage(TextPanelID, ErrorMessages[l]);

					}

				}else{

					Pass = false;

					TextPanelID.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
					
					ErrorMessages = new String[] {"Pago No Ser Vacio", "Payment Cannot Be Empty"};
					Mecanics.txtErrorMessage(TextPanelID, ErrorMessages[l]);

				}

				TextPanelID.revalidate();
				TextPanelID.repaint();

				if (Pass==true){
					
					p = Cliente.get(ID);
					
					if (p!=null){
						
						TextPanelClient.setText(p.getName()+" "+p.getLastName());
						
					}

				}


				ed = true;

				repaint();

            }

            public void removeUpdate (DocumentEvent e){

				insertUpdate(e);

				if (TextPanelID.getText().equals("")==true){

					TextPanelID.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));

				}

				ed = true;

				repaint();

            }

            public void changedUpdate(DocumentEvent e){

				repaint();

            }

        });

		TextPanelPay.getDocument().addDocumentListener(new DocumentListener(){

            public void insertUpdate(DocumentEvent e){

				String bup = TextPanelTotalPay.getText().trim();
				double Total = Double.parseDouble(Mecanics.DeleteChar(bup,bup.length()-1));
				double Pago = 0;
				double Vuelto = 0;

				boolean Pass = true;
				
				String[] ErrorMessages;

				bup = TextPanelPay.getText().trim();

				if (bup!=null && bup.equals("")==false){

					if (Mecanics.Allowed(bup)==true){

						if (Double.parseDouble(bup)%1==0){

							Pago = Double.parseDouble(bup);

							TextPanelPay.setBorder(new MatteBorder(0, 0, 2, 0, new Color(60, 133, 100)));

						}else{

							Pass = false;

							TextPanelPay.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
							
							ErrorMessages = new String[] {"Pago No Puede Ser Decimal", "Payment Cannot Be Decimal"};
							Mecanics.txtErrorMessage(TextPanelPay, ErrorMessages[l]);

						}

					}else{

						Pass = false;

						TextPanelPay.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
						
						ErrorMessages = new String[] {"Valor Ingresado No Númerico", "Entered Value Not Numeric"};
						Mecanics.txtErrorMessage(TextPanelPay, ErrorMessages[l]);

					}

				}else{

					Pass = false;

					TextPanelPay.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
					
					ErrorMessages = new String[] {"Pago No Ser Vacio", "Payment Cannot be Empty"};
					Mecanics.txtErrorMessage(TextPanelPay, ErrorMessages[l]);

				}

				TextPanelPay.revalidate();
				TextPanelPay.repaint();

				if (Pass==true){

					Vuelto = Pago-Total;

					if (Vuelto<0){

						TextPanelChange.setForeground(Color.RED);
						
						TextPanelChange.setText(Vuelto+"$");
						
						ErrorMessages = new String[] {"Pago Insuficiente", "Payment Insuficient"};
						Mecanics.txtErrorMessage(TextPanelChange, ErrorMessages[l]);

					}else{
						
						TextPanelChange.setText(Vuelto+"$");

						TextPanelChange.setForeground(Color.BLACK);

					}


					TextPanelChange.revalidate();
					TextPanelChange.repaint();

				}

				repaint();

            }

            public void removeUpdate(DocumentEvent e){

				TextPanelChange.setText("");

				insertUpdate(e);

				if (TextPanelPay.getText().equals("")==true || Mecanics.Allowed(TextPanelPay.getText())==true){

					TextPanelPay.setBorder(new MatteBorder(0, 0, 2, 0, new Color(60, 133, 100)));

				}

				repaint();

            }

            public void changedUpdate(DocumentEvent e){

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

		Add.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				Add.setText(AddTexto[l]);

				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){

				Add.setText("");

				Add.setSize(130, 30);

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				Add.setText(AddTexto[l]);

				Add.setSize(120, 20);

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				Add.setText("");

				repaint();

			}

		});

		Check.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				Check.setText(CheckTexto[l]);

				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){

				Check.setText("");

				Check.setSize(130, 45);

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				Check.setSize(120, 35);

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				Check.setText("");

				repaint();

			}

		});

		Print.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				Print.setText(PrintTexto[l]);

				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){

				Print.setText("");

				Print.setSize(200, 45);

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				Print.setSize(190, 35);

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				Print.setText("");

				repaint();

			}

		});

		Tabla.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				repaint();

			}

			public void mouseClicked(MouseEvent e){

				if (e.getButton() == MouseEvent.BUTTON1){

					int row = Tabla.getSelectedRow();

					Object[] value = new Object[5];

					value[0] = Tabla.getValueAt(row, 0);
					value[1] = Tabla.getValueAt(row, 1);
					value[2] = Tabla.getValueAt(row, 2);
					value[3] = Tabla.getValueAt(row, 3);
					value[4] = Tabla.getValueAt(row, 4);
					
					String bup = value[2].toString();
					
					if (Float.parseFloat(bup)%1==0){
						
						bup = Mecanics.DeleteChar(bup,bup.length()-1);
						bup = Mecanics.DeleteChar(bup,bup.length()-1);
						
						value[2] = bup;
						
					}
					
					Archivo p = Archivo.get(value[0].toString());
					
					if (p!=null){
						
						Unid.setText(p.getUnid());
						
					}

					TextPanelCode.setText(value[0]+"");
					TextPanelName.setText(value[1]+"");
					TextPanelAmount.setText(value[2]+"");
					TextPanelPrice.setText(value[3]+"");
					TextPanelTotal.setText(value[4]+"");

					TextPanelCode.repaint();
					TextPanelName.repaint();
					TextPanelAmount.repaint();
					TextPanelPrice.repaint();
					TextPanelTotal.repaint();

					ed = false;

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

		TextPanelCode.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){

				Searcher = true;

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

				Searcher = false;

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				repaint();

			}

		});

		TextPanelAmount.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				repaint();

			}

		});

		TextPanelPrice.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				repaint();

			}

		});

		TextPanelTotal.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				repaint();

			}

		});

		TextPanelTotalPay.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

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

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				repaint();

			}

		});

		TextPanelClient.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				repaint();

			}

		});

		TextPanelPay.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				repaint();

			}

		});

		TextPanelChange.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){

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
				
				TextPanelCode.setText("");
				TextPanelName.setText("");
				TextPanelAmount.setText("");
				TextPanelPrice.setText("");
				TextPanelTotal.setText("");
				MessageUnid.setText("");
				Unid.setText("");

				TextPanelCode.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
				TextPanelName.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
				TextPanelAmount.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
				TextPanelPrice.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
				TextPanelTotal.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
				
				repaint();

			}

			public void mousePressed(MouseEvent e){

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
	
	private Factura CheckIn(){
		
		String ID = " ";
		String Nombre = " ";
		String bup = TextPanelTotalPay.getText().trim();
		double Total = Double.parseDouble(Mecanics.DeleteChar(bup,bup.length()-1));
		long Pago = 0;
		double Vuelto = 0;
		boolean Pass = true;
		int Counter = 0;
		Object[][] Buyout;
		String[] Missing;
		Factura p = null;
		Archivo d;
		Cliente c;
		s = -1;
		
		String[] ErrorMessages;

		bup = TextPanelID.getText().trim();

		if (bup!=null && bup.equals("")==false){
			
			if (TextPanelClient.getText().equals("")==true || TextPanelClient.getText()==null){

				TextPanelID.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
				
				ErrorMessages = new String[] {"Cliente Inexistente", "Unexistance Client"};
				
				Mecanics.txtErrorMessage(TextPanelID, ErrorMessages[l]);

				Pass = false;
				
			}else if (Mecanics.Allowed(bup)==true){

				if (Double.parseDouble(bup)%1==0 && Double.parseDouble(bup)>0){

					ID = bup;
					Nombre = TextPanelClient.getText();

					TextPanelID.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
					
				}else{

					Pass = false;
					
				}
				
			}else{

				Pass = false;
				
			}
			
		}

		TextPanelID.revalidate();
		TextPanelID.repaint();

		bup = TextPanelPay.getText().trim();

		if (bup!=null && bup.equals("")==false){

			if (Mecanics.Allowed(bup)==true){

				if (Double.parseDouble(bup)%1==0){

					Pago = Long.parseLong(bup);

				}else{

					Pass = false;

				}

			}else{

				Pass = false;

			}

		}else{

			Pass = false;

		}

		if (Tabla.getRowCount()<=0){

			Pass = false;

		}

		if (Pass==true){
		
			Buyout = new Object[Tabla.getRowCount()][5];

			bup = TextPanelChange.getText();

			Vuelto = Double.parseDouble(Mecanics.DeleteChar(bup,bup.length()-1));

			if (Vuelto>=0 && Pago>=Total){

				for (int i=0; i<Tabla.getRowCount(); i++){
					
					bup = Tabla.getValueAt(i, 0).toString();
					
					if (Allow==true){
						
						d = Archivo.get(bup);
						
						if (d!=null){
							
							d.withDraw(Float.parseFloat(Tabla.getValueAt(i, 2).toString()));
							
							d.edit(d.getCode());
							
						}
						
					}

					Buyout[i][0] = Tabla.getValueAt(i, 0);
					Buyout[i][1] = Tabla.getValueAt(i, 1);
					Buyout[i][2] = Tabla.getValueAt(i, 2);
					Buyout[i][3] = Tabla.getValueAt(i, 3);
					Buyout[i][4] = Tabla.getValueAt(i, 4);
					
				}
				
				if (Allow==true){
					
					c = Cliente.get(ID);
					
					if (c!=null){
						
						p = new Factura(Mecanics.FacturaCode(Date.getText()), Pago, Vuelto, Total, Buyout, Date.getText(), Trabajador.get(EmployeName), c);
						
					}else{
						
						p = new Factura(Mecanics.FacturaCode(Date.getText()), Pago, Vuelto, Total, Buyout,Date.getText(), Trabajador.get(EmployeName));
						
					}
					
					p.add();
					
				}else{
					
					bup = "";
					
					p = Factura.get(FacturaCode);
					
					if (p!=null){
						
						Missing = new String[p.getBuyout().length-Buyout.length];
						
						if (Missing.length!=0){
						
							for (Object[] q : p.getBuyout()){
								
								for (Object[] b : Buyout){
									
									if (q[0].toString().equalsIgnoreCase(b[0].toString())==false){
										
										bup = q[0].toString();
										
									}else{
										
										bup = "";
										
										break;
										
									}
									
								}
								
								if (bup.equals("")==false){
									
									Missing[Counter] = bup;
									
									Counter++;
									
								}
								
							}
							
							for (String q : Missing){
							
								for (Object[] b : p.getBuyout()){
									
									if (q.equalsIgnoreCase(b[0].toString())){
										
										d = Archivo.get(q);
										
										if (d!=null){
										
											d.withDraw(-Float.parseFloat(b[2].toString()));
											
											d.edit(d.getCode());
											
										}
										
									}
									
								}
								
							}
							
						}
						
						for (Object[] q : p.getBuyout()){
							
							for (Object[] b : Buyout){
								
								if (q[0].toString().equalsIgnoreCase(b[0].toString())){
							
									d = Archivo.get(q[0].toString());
									
									if (d!=null){
										
										d.withDraw(Float.parseFloat(b[2].toString())-Float.parseFloat(q[2].toString()));
										
										d.edit(d.getCode());
										
									}
								
								}
							
							}
						
						}
						
						p.setPay(Pago);
						p.setChange(Vuelto);
						p.setTotal(Total);
						p.setBuyout(Buyout);
						
						p.edit(p.getCode());
						
					}
					
				}
				
				Allow = true;
				
				Erase.doClick();

			}else{
				
				Pass = false;
				
			}
		
			Dashboard.Cuenta = new Facturas();

		}

		TextPanelPay.revalidate();
		TextPanelPay.repaint();
		
		repaint();
		
		return Pass ? p : null;
		
	}

}