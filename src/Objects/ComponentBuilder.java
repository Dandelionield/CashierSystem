package Objects;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import javax.swing.JViewport;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.TableColumn;
import javax.swing.DefaultCellEditor;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;

import javax.swing.border.MatteBorder;
import javax.swing.border.EmptyBorder;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.Component;
import java.awt.Desktop;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EventObject;

public class ComponentBuilder{
	
	private String url;
	private String extention;
	private Color Fondo;
	private Color Colour = Color.BLACK;
	
	private JScrollPane Table;
	private DefaultTableModel Tablita;
	private JTable Tablitita;
	
	public ComponentBuilder(String url, String extention, Color Fondo){
		
		this.url = url+"/";
		this.extention = "."+extention;
		this.Fondo = Fondo;
		
	}
	
	public ComponentBuilder(String url, Color Fondo){
		
		this.url = url+"/";
		this.extention = ".png";
		this.Fondo = Fondo;
		
	}
	
	public ComponentBuilder(String url, String extention){
		
		this.url = url+"/";
		this.extention = "."+extention;
		this.Fondo = null;
		
	}
	
	public ComponentBuilder(String url){
		
		this.url = url+"/";
		this.extention = ".png";
		this.Fondo = null;
		
	}
	
	public ComponentBuilder(Color Fondo){
		
		this.url = "";
		this.extention = ".png";
		this.Fondo = Fondo;
		
	}
	
	public ComponentBuilder(){
		
		this.url = "";
		this.extention = ".png";
		this.Fondo = null;
		
	}
	
	public String getPath(){
		
		return this.url.substring(0, url.length()-2);
		
	}
	
	public void setPath(String url){
		
		this.url = url+"/";
		
	}
	
	public void setPathDownloads(){
		
		this.url = PathDriver("Downloads").toString()+"/";
		
	}
	
	public void setPathDocuments(){
		
		this.url = PathDriver("Documents").toString()+"/";
		
	}
	
	public void setPathDesktop(){
		
		this.url = PathDriver("Desktop").toString()+"/";
		
	}
	
	public String getExtention(){
		
		return this.extention.substring(1, extention.length()-1);
		
	}
	
	public void setExtention(String extention){
		
		this.extention = "."+extention;
		
	}
	
	public Color getBackground(){
		
		return this.Fondo;
		
	}
	
	public void setBackground(Color Fondo){
		
		this.Fondo = Fondo;
		
	}
	
	public void setForeground(Color Colour){
		
		this.Colour = Colour;
		
	}
	
	/*public static String actionimage(JPanel panel) {
		
		File archivo=new File("./src/ResourcePackCaja/image-not-found.png");
		
		try {
		
			try {
			  
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

			} catch (Exception e) {}

			JFileChooser jf=new JFileChooser();
			jf.setDialogTitle("Searcher");
			jf.showSaveDialog(panel);
			
			archivo= jf.getSelectedFile();
			String arc= Mecanics.getExtension(archivo);
			
			if(arc.equalsIgnoreCase("png")==false && arc.equalsIgnoreCase("jpg")==false) {
				
				String []error= {"ERROR, Seleccione un archivo con los formatos permitidos.","ERROR, Select a file with the allowed formats."};
				
			    JOptionPane.showMessageDialog(null, error[getLanguage(true)]+" \n\n->PNG\n->JPG\n", "Error", JOptionPane.ERROR_MESSAGE);
			    archivo=new File("./src/ResourcePackCaja/image-not-found.png");
				
			}

		}catch(Exception e) {
			
			String []error= {"Error de búsqueda: Documento no encontrado.","Search error: Document not found."};
			JOptionPane.showMessageDialog(null, error[getLanguage(true)], "Error", JOptionPane.ERROR_MESSAGE);
			archivo=new File("./src/ResourcePackCaja/image-not-found.png");
			
		}

		return archivo;
	}//*/
	
	public void buildTable(String[] Column, int[] Bounds){
		
		Object[][] Data = new Object[1][Column.length];
		
		for (int i=0; i<Column.length; i++){
			
			Data[0][i] = 0;
			
		}
		
		Tablita = new DefaultTableModel(Data,Column);	
		Tablita.setRowCount(0);

		Tablitita = new JTable(Tablita);

		for (int i = 0; i < Tablitita.getColumnCount(); i++){

			TableColumn column = Tablitita.getColumnModel().getColumn(i);

			column.setCellEditor(new DefaultCellEditor(new JTextField()){

				public boolean isCellEditable(EventObject e) {

					return false;

				}

			});

		}

		DefaultTableCellRenderer Render = new DefaultTableCellRenderer(){

			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){

				Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

				component.setForeground(Colour);

				component.setBackground(Fondo);

				return component;

			}
		};

		Render.setBackground(Fondo);

		for (int i = 0; i < Tablitita.getColumnCount(); i++) {

			Tablitita.getColumnModel().getColumn(i).setCellRenderer(Render);

		}

		JViewport viewport = new JViewport();
		viewport.setView(Tablitita);
		viewport.setBackground(Fondo);

		Table = new JScrollPane();
		Table.setViewport(viewport);
		Table.setBounds(Bounds[0], Bounds[1], Bounds[2], Bounds[3]);

	}
	
	public void buildTable(String[] Column, Object[][] Data, int[] Bounds){

		Tablita = new DefaultTableModel(Data,Column);
		Tablitita = new JTable(Tablita);

		for (int i = 0; i < Tablitita.getColumnCount(); i++){

			TableColumn column = Tablitita.getColumnModel().getColumn(i);

			column.setCellEditor(new DefaultCellEditor(new JTextField()){

				public boolean isCellEditable(EventObject e) {

					return false;

				}

			});

		}

		DefaultTableCellRenderer Render = new DefaultTableCellRenderer(){

			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){

				Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

				component.setForeground(Colour);

				component.setBackground(Fondo);

				return component;

			}
		};

		Render.setBackground(Fondo);

		for (int i = 0; i < Tablitita.getColumnCount(); i++) {

			Tablitita.getColumnModel().getColumn(i).setCellRenderer(Render);

		}

		JViewport viewport = new JViewport();
		viewport.setView(Tablitita);
		viewport.setBackground(Fondo);

		Table = new JScrollPane();
		Table.setViewport(viewport);
		Table.setBounds(Bounds[0], Bounds[1], Bounds[2], Bounds[3]);

	}
	
	public JScrollPane buildJScrollPane(Table Tabla, int[] Bounds){
		
		JViewport View = new JViewport();
		View.setView(Tabla);
		View.setBackground(Tabla.getBackground());
		
		JScrollPane Scroll = new JScrollPane();
		Scroll.setViewport(View);
		Scroll.setBorder(new EmptyBorder(0, 0, 0, 0));
		Scroll.getVerticalScrollBar().setUI(new ScrollBar(Tabla.getBackground(), (Tabla.getColumn(0)!=null) ? Tabla.getColumn(0).getFocusRowBackground() : Color.GRAY));
		Scroll.setBackground(Tabla.getBackground());
		
		Scroll.setBounds(Bounds[0], Bounds[1], Bounds[2], Bounds[3]);
		
		return Scroll;
		
	}
	
	public DefaultTableModel getDefaultTableModel(){
		
		return Tablita;
		
	}
	
	public JTable getJTable(){
		
		return Tablitita;
		
	}
	
	public JScrollPane getJScrollPane(){
		
		return Table;
		
	}
	
	public static JPanel buildPanel(int[] Bounds, int[] Rectangle, Color color){

		return buildPanel(Bounds, Rectangle, 0, 0, color);

	}
	
	public static JPanel buildPanel(int[] Bounds, int[] Rectangle, float sx, float sy, Color color){

		JPanel Panel = new JPanel(){

            protected void paintComponent(Graphics g){

                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(Rectangle[0], Rectangle[1], Rectangle[2], Rectangle[3], Rectangle[4], Rectangle[5]);//x,y,width,height,gear_up,gear_down
				g2d.shear(sx,sy);//sesgado x,y
                g2d.setColor(color);
                g2d.fill(roundedRectangle);

				g2d.dispose();

            }

        };

		Panel.setBounds(Bounds[0], Bounds[1], Bounds[2], Bounds[3]);
		Panel.setOpaque(false);

		return Panel;

	}
	
	public JLabel buildLabel(String Text, int[] Bounds, int HAlignment, Font Format){
		
		JLabel Etiqueta = new JLabel(Text);

		Etiqueta.setBounds(Bounds[0], Bounds[1], Bounds[2], Bounds[3]);
		Etiqueta.setHorizontalAlignment(HAlignment);
		Etiqueta.setForeground(Colour);
		Etiqueta.setBackground(Fondo);
		Etiqueta.setOpaque(true);
		Etiqueta.setFont(Format);
		
		return Etiqueta;
		
	}
	
	public JLabel buildLabel(String Text, int[] Bounds, int VTextPosition, int HTextPosition, Font Format){
		
		JLabel Etiqueta = new JLabel(Text);
		
		Etiqueta.setBounds(Bounds[0], Bounds[1], Bounds[2], Bounds[3]);
		Etiqueta.setVerticalTextPosition(VTextPosition);
        Etiqueta.setHorizontalTextPosition(HTextPosition);
		Etiqueta.setForeground(Colour);
		Etiqueta.setBackground(Fondo);
		Etiqueta.setOpaque(true);
		Etiqueta.setFont(Format);
		
		return Etiqueta;
		
	}
	
	public JLabel buildLabel(String Text, int[] Bounds, String Name, int Width, int Height, int VTextPosition, int HTextPosition, int HAlignment, Font Format){
		
		JLabel Etiqueta = new JLabel(Text);

		Image icono = new ImageIcon(url+Name+extention).getImage().getScaledInstance(Width, Height, Image.SCALE_SMOOTH);
        Etiqueta.setIcon(new ImageIcon(icono));
		
		Etiqueta.setBounds(Bounds[0], Bounds[1], Bounds[2], Bounds[3]);
		Etiqueta.setVerticalTextPosition(VTextPosition);
        Etiqueta.setHorizontalTextPosition(HTextPosition);
		Etiqueta.setHorizontalAlignment(HAlignment);
		Etiqueta.setForeground(Colour);
		Etiqueta.setBackground(Fondo);
		Etiqueta.setOpaque(true);
		Etiqueta.setFont(Format);
		
		return Etiqueta;
		
	}
	
	public JLabel buildLabel(String Text, int[] Bounds, String Name, int VTextPosition, int HTextPosition, int HAlignment, Font Format){
		
		JLabel Etiqueta = new JLabel(Text);
		
		Etiqueta.setBounds(Bounds[0], Bounds[1], Bounds[2], Bounds[3]);

		Image icono = new ImageIcon(url+Name+extention).getImage().getScaledInstance(Bounds[3], Bounds[3], Image.SCALE_SMOOTH);
        Etiqueta.setIcon(new ImageIcon(icono));
		
		Etiqueta.setVerticalTextPosition(VTextPosition);
        Etiqueta.setHorizontalTextPosition(HTextPosition);
		Etiqueta.setHorizontalAlignment(HAlignment);
		Etiqueta.setForeground(Colour);
		Etiqueta.setBackground(Fondo);
		Etiqueta.setOpaque(true);
		Etiqueta.setFont(Format);
		
		return Etiqueta;
		
	}
	
	public JTextField buildTextField(String Text, int[] Bounds, int HAlignment, Font Format, Color Border, Color Caret, boolean Editable, boolean Visible){

		JTextField PanelTexto = new JTextField(Text);

		PanelTexto.setBounds(Bounds[0], Bounds[1], Bounds[2], Bounds[3]);
		PanelTexto.setHorizontalAlignment(HAlignment);
		PanelTexto.setOpaque(true);
		PanelTexto.setFont(Format);
		PanelTexto.setBorder(new MatteBorder(0, 0, 2, 0, Border));
		PanelTexto.setForeground(Colour);
		PanelTexto.setCaretColor(Caret);
		PanelTexto.setBackground(Fondo);
		PanelTexto.setEditable(Editable);
		PanelTexto.setVisible(Visible);
		
		return PanelTexto;
		
	}
	
	public JTextField buildTextField(String Text, int[] Bounds, int HAlignment, Font Format, MatteBorder Border, Color Caret, boolean Editable, boolean Visible){

		JTextField PanelTexto = new JTextField(Text);

		PanelTexto.setBounds(Bounds[0], Bounds[1], Bounds[2], Bounds[3]);
		PanelTexto.setHorizontalAlignment(HAlignment);
		PanelTexto.setOpaque(true);
		PanelTexto.setFont(Format);
		PanelTexto.setBorder(Border);
		PanelTexto.setForeground(Colour);
		PanelTexto.setCaretColor(Caret);
		PanelTexto.setBackground(Fondo);
		PanelTexto.setEditable(Editable);
		PanelTexto.setVisible(Visible);
		
		return PanelTexto;
		
	}
	
	public JTextField buildTextField(String Text, int[] Bounds, int HAlignment, Font Format, Color Caret, boolean Editable, boolean Visible){

		JTextField PanelTexto = new JTextField(Text);

		PanelTexto.setBounds(Bounds[0], Bounds[1], Bounds[2], Bounds[3]);
		PanelTexto.setHorizontalAlignment(HAlignment);
		PanelTexto.setOpaque(true);
		PanelTexto.setFont(Format);
		PanelTexto.setForeground(Colour);
		PanelTexto.setCaretColor(Caret);
		PanelTexto.setBackground(Fondo);
		PanelTexto.setEditable(Editable);
		PanelTexto.setVisible(Visible);
		
		return PanelTexto;
		
	}
	
	public JTextArea buildTextArea(String Text, int[] Bounds, boolean LineWrap, boolean WrapStyle, Font Format, Color Caret, boolean Editable, boolean Visible){

		JTextArea PanelTexto = new JTextArea(Text);
		
		PanelTexto.setLineWrap(LineWrap);
        PanelTexto.setWrapStyleWord(WrapStyle);
		PanelTexto.setBounds(Bounds[0], Bounds[1], Bounds[2], Bounds[3]);
		PanelTexto.setAlignmentX(0);
		PanelTexto.setAlignmentY(0);
		PanelTexto.setOpaque(true);
		PanelTexto.setFont(Format);
		PanelTexto.setForeground(Colour);
		PanelTexto.setCaretColor(Caret);
		PanelTexto.setBackground(Fondo);
		PanelTexto.setEditable(Editable);
		PanelTexto.getCaret().setVisible(Editable);
		PanelTexto.getCaret().setSelectionVisible(Editable);
		PanelTexto.setVisible(Visible);
		
		return PanelTexto;
		
	}
	
	public JTextArea buildTextArea(String Text, int[] Bounds, Font Format, Color Caret, boolean Editable, boolean Visible){

		JTextArea PanelTexto = new JTextArea(Text);
		
		PanelTexto.setBounds(Bounds[0], Bounds[1], Bounds[2], Bounds[3]);
		PanelTexto.setAlignmentX(0);
		PanelTexto.setAlignmentY(0);
		PanelTexto.setOpaque(true);
		PanelTexto.setFont(Format);
		PanelTexto.setForeground(Colour);
		PanelTexto.setCaretColor(Caret);
		PanelTexto.setBackground(Fondo);
		PanelTexto.setEditable(Editable);
		PanelTexto.getCaret().setVisible(Editable);
		PanelTexto.getCaret().setSelectionVisible(Editable);
		PanelTexto.setVisible(Visible);
		
		return PanelTexto;
		
	}
	
	public JTextArea buildTextArea(String Text, Font Format, Color Caret, boolean Editable, boolean Visible){

		JTextArea PanelTexto = new JTextArea(Text);
		
		PanelTexto.setAlignmentX(0);
		PanelTexto.setAlignmentY(0);
		PanelTexto.setOpaque(true);
		PanelTexto.setFont(Format);
		PanelTexto.setForeground(Colour);
		PanelTexto.setCaretColor(Caret);
		PanelTexto.setBackground(Fondo);
		PanelTexto.setEditable(Editable);
		PanelTexto.getCaret().setVisible(Editable);
		PanelTexto.getCaret().setSelectionVisible(Editable);
		PanelTexto.setVisible(Visible);
		
		return PanelTexto;
		
	}
	
	public JButton buildButton(String Text, int[] Bounds, String Name, int Width, int Height, int VTextPosition, int HTextPosition, int HAlignment, boolean Editable, boolean Visible){

		JButton Boton = new JButton(Text);
		
        Image icono = new ImageIcon(url+Name+extention).getImage().getScaledInstance(Width, Height, Image.SCALE_SMOOTH);
        Boton.setIcon(new ImageIcon(icono));
		
		Boton.setBounds(Bounds[0], Bounds[1], Bounds[2], Bounds[3]);
        Boton.setVerticalTextPosition(VTextPosition);
        Boton.setHorizontalTextPosition(HTextPosition);
		Boton.setHorizontalAlignment(HAlignment);
		Boton.setForeground(Colour);
		Boton.setBackground(null);
		Boton.setFocusable(false);
		Boton.setOpaque(true);
        Boton.setContentAreaFilled(false);
		Boton.setBorder(null);
        Boton.setBorderPainted(false);
		
		Boton.setVisible(Visible);
		Boton.setEnabled(Editable);

		return Boton;

	}
	
	public JButton buildButton(String Text, int[] Bounds, String Name, int VTextPosition, int HTextPosition, int HAlignment, boolean Editable, boolean Visible){

		JButton Boton = new JButton(Text);
		
		Boton.setBounds(Bounds[0], Bounds[1], Bounds[2], Bounds[3]);
		
        Image icono = new ImageIcon(url+Name+extention).getImage().getScaledInstance(Bounds[3], Bounds[3], Image.SCALE_SMOOTH);

        Boton.setIcon(new ImageIcon(icono));
        Boton.setVerticalTextPosition(VTextPosition);
        Boton.setHorizontalTextPosition(HTextPosition);
		Boton.setHorizontalAlignment(HAlignment);
		Boton.setForeground(Colour);
		Boton.setBackground(null);
		Boton.setFocusable(false);
		Boton.setOpaque(true);
        Boton.setContentAreaFilled(false);
		Boton.setBorder(null);
        Boton.setBorderPainted(false);
		
		Boton.setVisible(Visible);
		Boton.setEnabled(Editable);

		return Boton;

	}
	
	public JButton buildButton(String Name, int[] Bounds, int Width, int Height, boolean Editable, boolean Visible){

		JButton Boton = new JButton();
		
        Image icono = new ImageIcon(url+Name+extention).getImage().getScaledInstance(Width, Height, Image.SCALE_SMOOTH);
        Boton.setIcon(new ImageIcon(icono));
		
		Boton.setBounds(Bounds[0], Bounds[1], Bounds[2], Bounds[3]);
		Boton.setBackground(null);
		Boton.setFocusable(false);
		Boton.setOpaque(true);
        Boton.setContentAreaFilled(false);
		Boton.setBorder(null);
        Boton.setBorderPainted(false);
		Boton.setVisible(Visible);
		Boton.setEnabled(Editable);

		return Boton;

	}
	
	public JButton buildButton(String Name, int[] Bounds, boolean Editable, boolean Visible){

		JButton Boton = new JButton();
		
		Boton.setBounds(Bounds[0], Bounds[1], Bounds[2], Bounds[3]);
		
        Image icono = new ImageIcon(url+Name+extention).getImage().getScaledInstance(Bounds[3], Bounds[3], Image.SCALE_SMOOTH);
		
        Boton.setIcon(new ImageIcon(icono));
		Boton.setBackground(null);
		Boton.setFocusable(false);
		Boton.setOpaque(true);
        Boton.setContentAreaFilled(false);
		Boton.setBorder(null);
        Boton.setBorderPainted(false);
		Boton.setVisible(Visible);
		Boton.setEnabled(Editable);

		return Boton;

	}
	
	public JButton buildButton(String Text, int[] Bounds, int[] Rectangle, int VTextPosition, int HTextPosition, Font Format, Color color, boolean Editable, boolean Visible){

		JButton Boton = new JButton(Text){
			
			protected void paintComponent(Graphics g) {
				
				Graphics2D g2d = (Graphics2D) g.create();
				g2d.setColor(getBackground());
				g2d.fill(new RoundRectangle2D.Double(Rectangle[0], Rectangle[1], Rectangle[2], Rectangle[3], Rectangle[4], Rectangle[5]));
				super.paintComponent(g2d);
				g2d.dispose();
				
			}
			
		};
		
		Boton.setBounds(Bounds[0], Bounds[1], Bounds[2], Bounds[3]);
		Boton.setForeground(Colour);
		Boton.setFont(Format);
		Boton.setVerticalTextPosition(VTextPosition);
        Boton.setHorizontalTextPosition(HTextPosition);
		Boton.setBackground(color);
		Boton.setForeground(Colour);
		Boton.setFocusable(false);
		Boton.setOpaque(true);
        Boton.setContentAreaFilled(false);
		Boton.setBorder(null);
        Boton.setBorderPainted(false);
		Boton.setVisible(Visible);
		Boton.setEnabled(Editable);

		return Boton;

	}
	
	public int[] doBounds(int X, int Y, int Width, int Height){
		
		return new int[] {X, Y, Width, Height};
		
	}
	
	private Path PathDriver(String Location){
		
		return Paths.get(System.getProperty("user.home"), Location);
		
	}

}