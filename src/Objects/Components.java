package Objects;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JViewport;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.TableColumn;
import javax.swing.DefaultCellEditor;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.Component;
import java.util.EventObject;

public class Components{
	
	private final String url;
	private final String extention;
	private final int m;
	private final Color Fondo;
	private final Color Default = new Color(0, 0, 0, 0);
	
	private JScrollPane Table = new JScrollPane();
	private DefaultTableModel Tablita = new DefaultTableModel();
	private JTable Tablitita = new JTable();
	
	public Components(String url, String extention, Color Fondo, int m){
		
		this.url = url;
		this.extention = "."+extention;
		this.m = m;
		this.Fondo = Fondo;
		
	}
	
	public Components(String url, Color Fondo, int m){
		
		this.url = url;
		this.extention = ".png";
		this.m = m;
		this.Fondo = Fondo;
		
	}
	
	public Components(String url, String extention){
		
		this.url = url;
		this.extention = "."+extention;
		this.m = 0;
		this.Fondo = Default;
		
	}
	
	public Components(String url){
		
		this.url = url;
		this.extention = ".png";
		this.m = 0;
		this.Fondo = Default;
		
	}
	
	public Components(){
		
		this.url = "";
		this.extention = ".png";
		this.m = 0;
		this.Fondo = Default;
		
	}
	
	public void Table(String[] Column, int[] Bounds){
		
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

				if (m==1){

					component.setForeground(Color.WHITE);

				}else{
					
					component.setForeground(Color.BLACK);
					
				}

				component.setBackground(Fondo);

				if (row==-1){

					if (m==1){

						component.setForeground(Color.WHITE);
						
					}else{
						
						component.setForeground(Color.BLACK);
						
					}

				}

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
	
	public void Table(String[] Column, Object[][] Data, int[] Bounds){

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

				if (m==0){

					component.setForeground(Color.BLACK);

				}else if (m==1){

					component.setForeground(Color.WHITE);

				}

				component.setBackground(Fondo);

				if (row==-1){

					if (m==0){

						component.setForeground(Color.BLACK);

					}else if (m==1){

						component.setForeground(Color.WHITE);

					}

				}

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
	
	public DefaultTableModel getDefaultTableModel(){
		
		return Tablita;
		
	}
	
	public JTable getJTable(){
		
		return Tablitita;
		
	}
	
	public JScrollPane getJScrollPane(){
		
		return Table;
		
	}
	
	public JLabel Label(String Text, int[] Bounds, int HAlignment, int size){
		
		JLabel Etiqueta = new JLabel(Text);

		if (m==1){
			
			Etiqueta.setForeground(Color.WHITE);

		}else{

			Etiqueta.setForeground(Color.BLACK);

		}

		Etiqueta.setBounds(Bounds[0], Bounds[1], Bounds[2], Bounds[3]);
		Etiqueta.setHorizontalAlignment(HAlignment);
		Etiqueta.setBackground(new Color(0, 0, 0, 0));
		Etiqueta.setOpaque(true);
		Etiqueta.setFont(new Font("Clarendon Blk BT", Font.BOLD, size));
		
		return Etiqueta;
		
	}
	
	public JLabel Label(String Text, int[] Bounds, int HAlignment, Font Format){
		
		JLabel Etiqueta = new JLabel(Text);

		if (m==1){
			
			Etiqueta.setForeground(Color.WHITE);

		}else{

			Etiqueta.setForeground(Color.BLACK);

		}

		Etiqueta.setBounds(Bounds[0], Bounds[1], Bounds[2], Bounds[3]);
		Etiqueta.setHorizontalAlignment(HAlignment);
		Etiqueta.setBackground(new Color(0, 0, 0, 0));
		Etiqueta.setOpaque(true);
		Etiqueta.setFont(Format);
		
		return Etiqueta;
		
	}
	
	public JLabel Label(String Text, int[] Bounds, String Name, int Width, int Height, int VTextPosition, int HTextPosition, int HAlignment, int size){
		
		JLabel Etiqueta = new JLabel(Text);

		if (m==1){
			
			Etiqueta.setForeground(Color.WHITE);

		}else{

			Etiqueta.setForeground(Color.BLACK);

		}
		
		Etiqueta.setBounds(Bounds[0], Bounds[1], Bounds[2], Bounds[3]);

		Image icono = new ImageIcon(url+Name+extention).getImage().getScaledInstance(Width, Height, Image.SCALE_SMOOTH);

        Etiqueta.setIcon(new ImageIcon(icono));
		Etiqueta.setVerticalTextPosition(VTextPosition);
        Etiqueta.setHorizontalTextPosition(HTextPosition);
		Etiqueta.setHorizontalAlignment(HAlignment);
		Etiqueta.setBackground(new Color(0, 0, 0, 0));
		Etiqueta.setOpaque(true);
		Etiqueta.setFont(new Font("Clarendon Blk BT", Font.BOLD, size));
		
		return Etiqueta;
		
	}
	
	public JTextField TextPanel(String Text, int[] Bounds, int HAlignment, int size, Color Border, Color Caret, boolean Editable, boolean Visible){

		JTextField PanelTexto = new JTextField(Text);

		if (m==1){

			PanelTexto.setForeground(Color.WHITE);

		}else{

			PanelTexto.setForeground(Color.BLACK);

		}
		
		PanelTexto.setBounds(Bounds[0], Bounds[1], Bounds[2], Bounds[3]);
		PanelTexto.setHorizontalAlignment(HAlignment);
		PanelTexto.setOpaque(true);
		PanelTexto.setFont(new Font("Clarendon Blk BT", Font.PLAIN, size));
		PanelTexto.setBorder(new MatteBorder(0, 0, 2, 0, Border));
		PanelTexto.setCaretColor(Caret);
		PanelTexto.setBackground(Fondo);
		PanelTexto.setEditable(Editable);
		PanelTexto.setVisible(Visible);
		
		return PanelTexto;
		
	}
	
	public JTextField TextPanel(String Text, int[] Bounds, int HAlignment, Font Format, Color Border, Color Caret, boolean Editable, boolean Visible){

		JTextField PanelTexto = new JTextField(Text);

		if (m==1){

			PanelTexto.setForeground(Color.WHITE);

		}else{

			PanelTexto.setForeground(Color.BLACK);

		}
		
		PanelTexto.setBounds(Bounds[0], Bounds[1], Bounds[2], Bounds[3]);
		PanelTexto.setHorizontalAlignment(HAlignment);
		PanelTexto.setOpaque(true);
		PanelTexto.setFont(Format);
		PanelTexto.setBorder(new MatteBorder(0, 0, 2, 0, Border));
		PanelTexto.setCaretColor(Caret);
		PanelTexto.setBackground(Fondo);
		PanelTexto.setEditable(Editable);
		PanelTexto.setVisible(Visible);
		
		return PanelTexto;
		
	}
	
	public JButton Button(String Text, int[] Bounds, String Name, int Width, int Height, int VTextPosition, int HTextPosition, int HAlignment, boolean Editable, boolean Visible){

		JButton Boton = new JButton(Text);

		if (m==1){

			Boton.setForeground(Color.WHITE);

		}else{

			Boton.setForeground(Color.BLACK);

		}
		
		Boton.setBounds(Bounds[0], Bounds[1], Bounds[2], Bounds[3]);
		
        Image icono = new ImageIcon(url+Name+extention).getImage().getScaledInstance(Width, Height, Image.SCALE_SMOOTH);

        Boton.setIcon(new ImageIcon(icono));
        Boton.setVerticalTextPosition(VTextPosition);
        Boton.setHorizontalTextPosition(HTextPosition);
		Boton.setHorizontalAlignment(HAlignment);
		Boton.setBackground(new Color(0, 0, 0, 0));
		Boton.setFocusable(false);
		Boton.setOpaque(true);
        Boton.setContentAreaFilled(false);
		Boton.setBorder(null);
        Boton.setBorderPainted(false);
		
		Boton.setVisible(Visible);
		Boton.setEnabled(Editable);

		return Boton;

	}
	
	public JButton Button(String Text, int[] Bounds, String Name, int Width, int Height, boolean Editable, boolean Visible){

		JButton Boton = new JButton(Text);

		if (m==1){

			Boton.setForeground(Color.WHITE);

		}else{

			Boton.setForeground(Color.BLACK);

		}
		
		Boton.setBounds(Bounds[0], Bounds[1], Bounds[2], Bounds[3]);
		
        Image icono = new ImageIcon(url+Name+extention).getImage().getScaledInstance(Width, Height, Image.SCALE_SMOOTH);

        Boton.setIcon(new ImageIcon(icono));
		Boton.setBackground(new Color(0, 0, 0, 0));
		Boton.setFocusable(false);
		Boton.setOpaque(true);
        Boton.setContentAreaFilled(false);
		Boton.setBorder(null);
        Boton.setBorderPainted(false);
		
		Boton.setVisible(Visible);
		Boton.setEnabled(Editable);

		return Boton;

	}
	
	public int[] setBounds(int X, int Y, int Width, int Height){
		
		int[] Bounds = {X, Y, Width, Height};
		
		return Bounds;
		
	}

}