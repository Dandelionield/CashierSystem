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
import javax.swing.JLayeredPane;
import javax.swing.border.LineBorder;
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
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.EventObject;

public class Facturas extends JPanel{
	
	private int m = Mecanics.getMode();
	private int l = Mecanics.getLanguage();
	
	private Color[] Fondo = {new Color(238, 248, 254), new Color(20, 35, 54)};
	
	private JLabel Code;
	private JLabel Name;
	private JLabel Amount;
	private JLabel Price;
	private JLabel Total;
	private JLabel TotalPay;
	private JLabel Client;
	private JLabel Pay;
	private JLabel Change;
	private JLabel Search;
	private JLabel Unid;
	private JTextField TextPanelCode;
	private JTextField TextPanelName;
	private JTextField TextPanelAmount;
	private JTextField TextPanelPrice;
	private JTextField TextPanelTotal;
	private JTextField TextPanelTotalPay;
	private JTextField TextPanelClient;
	private JTextField TextPanelID;
	private JTextField TextPanelPay;
	private JTextField TextPanelChange;
	private JTextField TextPanelSearch;
	private JButton Erase;
	private JButton PDF;
	private JButton Edit;
	
	private Table Tabla1;
	private Table Tabla2;
	
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
	private String[] SearchTexto = {"Buscar:","Search:"};
	
	private String[] CajaBorrar = {"BorrarLight","BorrarDark"};
	private String[] CajaCliente = {"LupaLight","LupaDark"};
	private String[] CajaPDF = {"PdfLight","PdfDark"};
	private String[] CajaEditar = {"EditarLight","EditarDark"};

	private String[][] ColumnaNombre1 = {{"Código","Producto","Cantidad","Precio","Total"},{"Code","Product","Amount","Price","Total"}};
	private String[][] ColumnaNombre2 = {{"Código","Fecha"},{"Code","Date"}};
	
	private final ComponentBuilder cp = new ComponentBuilder("./src/ResourcePackCaja", Fondo[m]);
	
	public Facturas(){
		
		if (m==1){cp.setForeground(Color.WHITE);}
		
		int z = 0;
		final Color Money = new Color(60, 133, 100);
		Font Format = new Font("Clarendon Blk BT", Font.BOLD, 12);
		
		setBounds(20, 45, 960, 415);
		setOpaque(false);
		
		String[] Column = {ColumnaNombre1[l][0],ColumnaNombre1[l][1],ColumnaNombre1[l][2],ColumnaNombre1[l][3],ColumnaNombre1[l][4]};
	
		Tabla1 = getCustomTable(Column);
		
		Column = new String[] {ColumnaNombre2[l][0], ColumnaNombre2[l][1]};
		
		Object[][] Data;

		if (Factura.length()!=0){
			
			Data = new Object[Factura.length()][2];
			
			Factura[] p = Factura.get();

			for (int i=0; i<p.length; i++){

				Data[i][0] = p[i].getCode();
				Data[i][1] = p[i].getDate();

			}
			
			Tabla2 = getCustomTable(Data, Column);

		}else{
			
			Tabla2 = getCustomTable(Column);
			
		}
		
		Code = cp.buildLabel(CodeTexto[l], cp.doBounds(30, 125, 50, 20), SwingConstants.LEFT, Format);
		Name = cp.buildLabel(NameTexto[l], cp.doBounds(210, 125, 70, 20), SwingConstants.LEFT, Format);
		Amount = cp.buildLabel(AmountTexto[l], cp.doBounds(390, 125, 50, 20), SwingConstants.LEFT, Format);
		Price = cp.buildLabel(PriceTexto[l], cp.doBounds(565, 125, 50, 20), SwingConstants.LEFT, Format);
		Total = cp.buildLabel(TotalTexto[l], cp.doBounds(740, 125, 100, 20), SwingConstants.LEFT, Format);
		TotalPay = cp.buildLabel(TotalPayTexto[l]+":", cp.doBounds(710, 355, 80, 20), SwingConstants.RIGHT, Format);
		Client = cp.buildLabel(ClientTexto[l], cp.doBounds(30, 345, 75, 40), CajaCliente[m], 20, 20, JButton.CENTER, JButton.RIGHT, JButton.LEFT, Format);
		Pay = cp.buildLabel(PayTexto[l]+":", cp.doBounds(360, 355, 70, 20), SwingConstants.RIGHT, Format);
		Change = cp.buildLabel(ChangeTexto[l]+":", cp.doBounds(530, 355, 70, 20), SwingConstants.RIGHT, Format);
		Search = cp.buildLabel(SearchTexto[l], cp.doBounds(30, 5, 75, 40), CajaCliente[m], 20, 20, JButton.CENTER, JButton.RIGHT, JButton.LEFT, Format);
		Unid = cp.buildLabel("", cp.doBounds(490, 165, 30, 20), SwingConstants.CENTER, Format);	Format = new Font("Clarendon Blk BT", Font.PLAIN, 15);
		TextPanelCode = cp.buildTextField("", cp.doBounds(30, 165, 100, 20), SwingConstants.LEFT, Format, Color.BLUE, new Color(0, 0, 0, 0), false, false);
		TextPanelName = cp.buildTextField("", cp.doBounds(210, 165, 100, 20), SwingConstants.LEFT, Format, Color.BLUE, new Color(0, 0, 0, 0), false, false);
		TextPanelAmount = cp.buildTextField("", cp.doBounds(390, 165, 100, 20), SwingConstants.LEFT, Format, Color.BLUE, new Color(0, 0, 0, 0), false, false);
		TextPanelPrice = cp.buildTextField("", cp.doBounds(565, 165, 100, 20), SwingConstants.LEFT, Format, Color.BLUE, new Color(0, 0, 0, 0), false, false);	Format = new Font("Clarendon Blk BT", Font.PLAIN, 13);
		TextPanelTotal = cp.buildTextField("", cp.doBounds(740, 165, 100, 20), SwingConstants.LEFT, Format, Color.BLUE, new Color(0, 0, 0, 0), false, false);
		TextPanelTotalPay = cp.buildTextField("0.0$", cp.doBounds(800, 355, 100, 20), SwingConstants.LEFT, Format, Money, new Color(0, 0, 0, 0), false, false);
		TextPanelID = cp.buildTextField("", cp.doBounds(105, 355, 80, 20), SwingConstants.CENTER, Format, Color.BLUE, new Color(0, 0, 0, 0), false, false);
		TextPanelClient = cp.buildTextField("", cp.doBounds(195, 355, 170, 20), SwingConstants.CENTER, Format, Color.BLUE, new Color(0, 0, 0, 0), false, false);
		TextPanelPay = cp.buildTextField("", cp.doBounds(440, 355, 100, 20), SwingConstants.LEFT, Format, Money, new Color(0, 0, 0, 0), false, false);
		TextPanelChange = cp.buildTextField("", cp.doBounds(610, 355, 100, 20), SwingConstants.LEFT, Format, Money, new Color(0, 0, 0, 0), false, false);
		TextPanelSearch = cp.buildTextField("", cp.doBounds(105, 15, 100, 20), SwingConstants.LEFT, Format, Color.BLUE, Color.BLUE, true, false);
		Erase = cp.buildButton(CajaBorrar[m], cp.doBounds(880, 20, 35, 35), true, false);
		PDF = cp.buildButton(CajaPDF[m], cp.doBounds(880, 140, 35, 35), 35, 35, true, false);
		Edit = cp.buildButton(CajaEditar[m], cp.doBounds(420, 20, 22, 22), false, false);
		
		setLayout(null);
		setComponentZOrder(cp.buildJScrollPane(Tabla1, cp.doBounds(20, 200, 900, 140)), z);	z++;
		setComponentZOrder(cp.buildJScrollPane(Tabla2, cp.doBounds(460, 20, 400, 100)), z);	z++;
		setComponentZOrder(Code, z);	z++;
		setComponentZOrder(Name, z);	z++;
		setComponentZOrder(Amount, z);	z++;
		setComponentZOrder(Price, z);	z++;
		setComponentZOrder(Total, z);	z++;
		setComponentZOrder(TotalPay, z);z++;
		setComponentZOrder(Client, z);	z++;
		setComponentZOrder(Pay, z);		z++;
		setComponentZOrder(Change, z);	z++;
		setComponentZOrder(Search, z);	z++;
		setComponentZOrder(Unid, z);	z++;
		setComponentZOrder(TextPanelCode, z);	z++;
		setComponentZOrder(TextPanelName, z);	z++;
		setComponentZOrder(TextPanelAmount, z);z++;
		setComponentZOrder(TextPanelPrice, z);	z++;
		setComponentZOrder(TextPanelTotal, z);	z++;
		setComponentZOrder(TextPanelTotalPay, z);	z++;
		setComponentZOrder(TextPanelID, z);		z++;
		setComponentZOrder(TextPanelClient, z);	z++;
		setComponentZOrder(TextPanelPay, z);	z++;
		setComponentZOrder(TextPanelChange, z);z++;
		setComponentZOrder(TextPanelSearch, z);z++;
		setComponentZOrder(Erase, z);	z++;
		setComponentZOrder(PDF, z);	z++;
		setComponentZOrder(Edit, z);	z++;
		
		Tabla1.setVisible(true);
		Tabla2.setVisible(true);
		Code.setVisible(true);
		Name.setVisible(true);
		Amount.setVisible(true);
		Price.setVisible(true);
		Total.setVisible(true);
		TotalPay.setVisible(true);
		Client.setVisible(true);
		Pay.setVisible(true);
		Change.setVisible(true);
		Search.setVisible(true);
		Unid.setVisible(true);
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
		TextPanelSearch.setVisible(true);
		Erase.setVisible(true);
		PDF.setVisible(true);
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
		
		if (ColumnName.length>2){
			
			Tabla.getColumn(3).setDefaultForeground(new Color(60, 133, 100));
			Tabla.getColumn(4).setDefaultForeground(new Color(60, 133, 100));
			
			Tabla.getColumn(3).setFocusCellBackground(new Color(60, 133, 100));
			Tabla.getColumn(4).setFocusCellBackground(new Color(60, 133, 100));
			
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
		
		PDF.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){
	
				int Row = Tabla2.getSelectedRow();
				
				Factura p = Factura.get(Tabla2.getValueAt(Row, 0).toString());
				
				if (p!=null){
					
					Mecanics.Facturar(p);
					
				}

				repaint();

			}

		});
		
		Erase.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){
	
				int Row = Tabla2.getSelectedRow();
				String Code = TextPanelSearch.getText().trim();
				
				Factura b = Factura.get(Tabla2.getValueAt(Row, 0).toString());
				Archivo q;
				
				if (Row!=-1 && b!=null){
					
					for (Object[] p : b.getBuyout()){
						
						q = Archivo.get(p[0].toString());
						
						if (q!=null){
							
							q.withDraw(-Float.parseFloat(p[2].toString()));
							
							q.edit(q.getCode());
							
						}
						
					}
					
					Tabla2.removeRow(Row);
					b.remove();
					
					TextPanelSearch.setText("");
					TextPanelSearch.setText(Code);
					
				}

				repaint();

			}

		});
		
		Edit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){
	
				Factura q = Factura.get(TextPanelSearch.getText().trim());
				
				if (q!=null){
				
					Dashboard.Caja = new CajaRegistradora(false, q);
					
					Dashboard.contentPane.removeAll();
					
					Dashboard.WindowSelected.setBounds(45, 21, 225, 50);
					
					Dashboard.contentPane.add(Dashboard.Background, Integer.valueOf(0));
					Dashboard.contentPane.add(Dashboard.Caja, Integer.valueOf(1));
					Dashboard.contentPane.add(Dashboard.Window1st, Integer.valueOf(2));
					Dashboard.contentPane.add(Dashboard.Window2nd, Integer.valueOf(3));
					Dashboard.contentPane.add(Dashboard.Window3rd, Integer.valueOf(4));
					Dashboard.contentPane.add(Dashboard.Window4th, Integer.valueOf(5));
					Dashboard.contentPane.add(Dashboard.WindowSelected, Integer.valueOf(6));

					repaint();
					
				}

			}

		});
		
		TextPanelSearch.getDocument().addDocumentListener (new DocumentListener(){

            public void insertUpdate(DocumentEvent e){
				
				Tabla1.setRowCount(0);
				Tabla2.clearSelection();
				Edit.setEnabled(false);

				TextPanelCode.setText("");
				TextPanelName.setText("");
				TextPanelAmount.setText("");
				TextPanelPrice.setText("");
				TextPanelTotal.setText("");
				TextPanelID.setText("");
				TextPanelClient.setText("");
				TextPanelPay.setText("");
				TextPanelChange.setText("");
				Unid.setText("");
				TextPanelTotalPay.setText("0.0$");
				
				String Code = TextPanelSearch.getText().trim();
				
				Factura q = Factura.get(Code);
				int indice = -1;
				
				if (q!=null){
					
					for (Object[] Fila : q.getBuyout()){
						
						Tabla1.addRow(Fila);
						
					}

					TextPanelID.setText(q.getClient().getID());
					TextPanelClient.setText(q.getClient().getName()+" "+q.getClient().getLastName());
					TextPanelPay.setText(q.getPay()+"$");
					TextPanelChange.setText(q.getChange()+"$");
					TextPanelTotalPay.setText(q.getTotal()+"$");
					
					Edit.setEnabled(true);
					
					indice = Tabla2.getValueAtColumn(0, q.getCode());
					
					Tabla2.setRowSelectionInterval(indice, indice);
					
				}

				repaint();

            }

            public void removeUpdate(DocumentEvent e){

				insertUpdate(e);

				repaint();

            }

            public void changedUpdate(DocumentEvent e){

				repaint();

            }

        });
		
		PDF.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				repaint();

			}

			public void mouseClicked(MouseEvent e) {

				repaint();

			}

			public void mousePressed(MouseEvent e){

				PDF.setSize(45,45);

				repaint();

			}

			public void mouseReleased(MouseEvent e) {

				PDF.setSize(35,35);

				repaint();

			}

			public void mouseExited(MouseEvent e) {

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
		
		Tabla1.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				repaint();

			}

			public void mouseClicked(MouseEvent e){

				if (e.getButton() == MouseEvent.BUTTON1){

					int row = Tabla1.getSelectedRow();

					Object[] value = new Object[5];

					value[0] = Tabla1.getValueAt(row, 0);
					value[1] = Tabla1.getValueAt(row, 1);
					value[2] = Tabla1.getValueAt(row, 2);
					value[3] = Tabla1.getValueAt(row, 3);
					value[4] = Tabla1.getValueAt(row, 4);
					
					Archivo q = Archivo.get(value[0].toString());
					
					if (q!=null){
						
						Unid.setText(q.getUnid());
						
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

					//ed = false;

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
				
				Tabla1.clearFocus();
				
				repaint();

			}

		});
		
		Tabla2.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				repaint();

			}

			public void mouseClicked(MouseEvent e){

				if (e.getButton() == MouseEvent.BUTTON1){

					int Row = Tabla2.getSelectedRow();
					
					Factura q = Factura.get(Tabla2.getValueAt(Row, 0).toString());
					
					if (q!=null){
						
						TextPanelSearch.setText(q.getCode());
						
					}

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

				Tabla2.clearFocus();
				
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

				//Searcher = true;

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

				//Searcher = false;

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
		
		TextPanelSearch.addMouseListener(new MouseAdapter() {

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

				repaint();

			}

			public void mousePressed(MouseEvent e){
				
				Tablita1.setRowCount(0);
				
				TextPanelSearch.setText("");
				TextPanelCode.setText("");
				TextPanelName.setText("");
				TextPanelAmount.setText("");
				TextPanelPrice.setText("");
				TextPanelTotal.setText("");
				TextPanelID.setText("");
				TextPanelClient.setText("");
				TextPanelPay.setText("");
				TextPanelChange.setText("");
				Unid.setText("");
				TextPanelTotalPay.setText("0.0$");
				
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