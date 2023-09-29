package CashCashier;

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
	
	private int m = Mecanics.getMode(true);
	private int l = Mecanics.getLanguage(true);
	
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
	
	private JScrollPane Table1;
	private DefaultTableModel Tablita1;
	private JTable Tablitita1;
	
	private JScrollPane Table2;
	private DefaultTableModel Tablita2;
	private JTable Tablitita2;
	
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
	
	private final Components cp = new Components("./src/ResourcePackCaja/", m, l, Fondo);
	
	public Facturas(){
		
		int z = 0;
		final Color Money = new Color(60, 133, 100);
		
		setBounds(20, 45, 960, 415);
		setOpaque(false);
		
		String[] Column = {ColumnaNombre1[l][0],ColumnaNombre1[l][1],ColumnaNombre1[l][2],ColumnaNombre1[l][3],ColumnaNombre1[l][4]};
	
		cp.Table(Column, cp.setBounds(20, 200, 900, 140));
		
		Tablita1 = cp.getDefaultTableModel();
		Tablitita1 = cp.getJTable();
		Table1 = cp.getJScrollPane();
		
		Column = new String[2];
		Column[0] = ColumnaNombre2[l][0];
		Column[1] = ColumnaNombre2[l][1];
		
		Object[][] Data = new Object[Mecanics.Receipt.size()][2];

		if (Mecanics.Receipt.size()!=0){

			for (int i=0; i<Mecanics.Receipt.size(); i++){

				Data[i][0] = Mecanics.Receipt.get(i).getCode();
				Data[i][1] = Mecanics.Receipt.get(i).getDate();

			}
			
			cp.Table(Column, Data, cp.setBounds(460, 20, 400, 100));

		}else{
			
			cp.Table(Column, cp.setBounds(460, 20, 400, 100));
			
		}
		
		Tablita2 = cp.getDefaultTableModel();
		Tablitita2 = cp.getJTable();
		Table2 = cp.getJScrollPane();
		
		Code = cp.Label(CodeTexto[l], cp.setBounds(30, 125, 50, 20), SwingConstants.LEFT, 12);
		Name = cp.Label(NameTexto[l], cp.setBounds(210, 125, 70, 20), SwingConstants.LEFT, 12);
		Amount = cp.Label(AmountTexto[l], cp.setBounds(390, 125, 50, 20), SwingConstants.LEFT, 12);
		Price = cp.Label(PriceTexto[l], cp.setBounds(565, 125, 50, 20), SwingConstants.LEFT, 12);
		Total = cp.Label(TotalTexto[l], cp.setBounds(740, 125, 100, 20), SwingConstants.LEFT, 12);
		TotalPay = cp.Label(TotalPayTexto[l]+":", cp.setBounds(710, 355, 80, 20), SwingConstants.RIGHT, 12);
		Client = cp.Label(ClientTexto[l], cp.setBounds(30, 345, 100, 40), CajaCliente[m], 20, 20, JButton.CENTER, JButton.RIGHT, JButton.LEFT, 12);
		Pay = cp.Label(PayTexto[l]+":", cp.setBounds(360, 355, 70, 20), SwingConstants.RIGHT, 12);
		Change = cp.Label(ChangeTexto[l]+":", cp.setBounds(530, 355, 70, 20), SwingConstants.RIGHT, 12);
		Search = cp.Label(SearchTexto[l], cp.setBounds(30, 5, 100, 40), CajaCliente[m], 20, 20, JButton.CENTER, JButton.RIGHT, JButton.LEFT, 12);
		Unid = cp.Label("", cp.setBounds(490, 165, 30, 20), SwingConstants.CENTER, 12);
		TextPanelCode = cp.TextPanel("", cp.setBounds(30, 165, 100, 20), SwingConstants.LEFT, 15, Color.BLUE, new Color(0, 0, 0, 0), false, false);
		TextPanelName = cp.TextPanel("", cp.setBounds(210, 165, 100, 20), SwingConstants.LEFT, 15, Color.BLUE, new Color(0, 0, 0, 0), false, false);
		TextPanelAmount = cp.TextPanel("", cp.setBounds(390, 165, 100, 20), SwingConstants.LEFT, 15, Color.BLUE, new Color(0, 0, 0, 0), false, false);
		TextPanelPrice = cp.TextPanel("", cp.setBounds(565, 165, 100, 20), SwingConstants.LEFT, 15, Color.BLUE, new Color(0, 0, 0, 0), false, false);
		TextPanelTotal = cp.TextPanel("", cp.setBounds(740, 165, 100, 20), SwingConstants.LEFT, 13, Color.BLUE, new Color(0, 0, 0, 0), false, false);
		TextPanelTotalPay = cp.TextPanel("0.0$", cp.setBounds(800, 355, 100, 20), SwingConstants.LEFT, 13, Money, new Color(0, 0, 0, 0), false, false);
		TextPanelID = cp.TextPanel("", cp.setBounds(105, 355, 80, 20), SwingConstants.CENTER, 13, Color.BLUE, new Color(0, 0, 0, 0), false, false);
		TextPanelClient = cp.TextPanel("", cp.setBounds(195, 355, 170, 20), SwingConstants.CENTER, 13, Color.BLUE, new Color(0, 0, 0, 0), false, false);
		TextPanelPay = cp.TextPanel("", cp.setBounds(440, 355, 100, 20), SwingConstants.LEFT, 13, Money, new Color(0, 0, 0, 0), false, false);
		TextPanelChange = cp.TextPanel("", cp.setBounds(610, 355, 100, 20), SwingConstants.LEFT, 13, Money, new Color(0, 0, 0, 0), false, false);
		TextPanelSearch = cp.TextPanel("", cp.setBounds(105, 15, 100, 20), SwingConstants.LEFT, 13, Color.BLUE, Color.BLUE, true, false);
		Erase = cp.Button("", cp.setBounds(880, 20, 35, 35), CajaBorrar[m], 35, 35, true, false);
		PDF = cp.Button("", cp.setBounds(880, 140, 35, 35), CajaPDF[m], 35, 35, true, false);
		Edit = cp.Button("", cp.setBounds(420, 20, 22, 22), CajaEditar[m], 22, 22, false, false);
		
		add(Table1);
		add(Table2);
		add(Code);
		add(Name);
		add(Amount);
		add(Price);
		add(Total);
		add(TotalPay);
		add(Client);
		add(Pay);
		add(Change);
		add(Search);
		add(Unid);
		add(TextPanelCode);
		add(TextPanelName);
		add(TextPanelAmount);
		add(TextPanelPrice);
		add(TextPanelTotal);
		add(TextPanelTotalPay);
		add(TextPanelID);
		add(TextPanelClient);
		add(TextPanelPay);
		add(TextPanelChange);
		add(TextPanelSearch);
		add(Erase);
		add(PDF);
		add(Edit);
		
		setLayout(null);
		setComponentZOrder(Table1, z);	z++;
		setComponentZOrder(Table2, z);	z++;
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
		
		Table1.setVisible(true);
		Table2.setVisible(true);
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
	
				int row = Tablitita2.getSelectedRow();
				
				if (row>=0){
					
					Mecanics.Facturar(Mecanics.Receipt.get(row));
					
				}

				repaint();

			}

		});
		
		Erase.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){
	
				int row = Tablitita2.getSelectedRow();
				int indice = -1;
				String Code = TextPanelSearch.getText().trim();
				
				if (row>=0){
					
					for (Object[] p : Mecanics.Receipt.get(row).getBuyout()){
						
						indice = Mecanics.getArchive(p[0].toString());
						
						if (indice!=-1){
							
							Archivo q =  Mecanics.Archive.get(indice);
							
							q.setAmount(q.getAmount()+Float.parseFloat(p[2].toString()));
							
							Mecanics.Archive.remove(indice);
							Mecanics.Archive.add(indice,q);
							Mecanics.setFile(true);
							
						}
						
					}
					
					Tablita2.removeRow(row);
					Mecanics.Receipt.remove(row);
					
					Mecanics.setReceipt(true);
					
					TextPanelSearch.setText("");
					TextPanelSearch.setText(Code);
					
				}

				repaint();

			}

		});
		
		Edit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){
	
				int row = Tablitita2.getSelectedRow();
				
				Dashboard.Caja = new CajaRegistradora(false,Mecanics.Receipt.get(row));
				
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

		});
		
		TextPanelSearch.getDocument().addDocumentListener (new DocumentListener(){

            public void insertUpdate(DocumentEvent e){
				
				Tablita1.setRowCount(0);
				Tablitita2.clearSelection();
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
				int row = -1;
				
				for (int i=0; i<Tablita2.getRowCount(); i++){

					if (Code.equalsIgnoreCase(Tablitita2.getValueAt(i, 0).toString())==true){
						
						Tablitita2.setRowSelectionInterval(i, i);
						row = i;
						
						break;

					}

				}
				
				if (row!=-1){
					
					for (Object[] Fila : Mecanics.Receipt.get(row).getBuyout()){
						
						Tablita1.addRow(Fila);
						
					}

					TextPanelID.setText(Mecanics.Receipt.get(row).getClient().getID());
					TextPanelClient.setText(Mecanics.Receipt.get(row).getClient().getName()+" "+Mecanics.Receipt.get(row).getClient().getLastName());
					TextPanelPay.setText(Mecanics.Receipt.get(row).getPay()+"$");
					TextPanelChange.setText(Mecanics.Receipt.get(row).getChange()+"$");
					TextPanelTotalPay.setText(Mecanics.Receipt.get(row).getTotal()+"$");
					
					Edit.setEnabled(true);
					
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
		
		Tablitita1.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				repaint();

			}

			public void mouseClicked(MouseEvent e){

				if (e.getButton() == MouseEvent.BUTTON1){

					int row = Tablitita1.getSelectedRow();

					Object[] value = new Object[5];

					value[0] = Tablitita1.getValueAt(row, 0);
					value[1] = Tablitita1.getValueAt(row, 1);
					value[2] = Tablitita1.getValueAt(row, 2);
					value[3] = Tablitita1.getValueAt(row, 3);
					value[4] = Tablitita1.getValueAt(row, 4);
					
					for (Archivo p : Mecanics.Archive){

						if (p.getCode().equalsIgnoreCase(value[0].toString())==true){

							Unid.setText(p.getUnid());

							break;

						}

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

				repaint();

			}

		});
		
		Tablitita2.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				repaint();

			}

			public void mouseClicked(MouseEvent e){

				if (e.getButton() == MouseEvent.BUTTON1){

					int row = Tablitita2.getSelectedRow();
					
					TextPanelSearch.setText(Mecanics.Receipt.get(row).getCode());

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