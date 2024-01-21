package Objects;

import java.awt.Font;
import java.awt.Color;
import java.awt.Point;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.KeyboardFocusManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.MatteBorder;
import javax.swing.border.BevelBorder;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JComponent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;

import javax.swing.table.DefaultTableCellRenderer;

public class TableCell extends DefaultTableCellRenderer{
	
	private Color FocusCellBackground;
	private Color FocusCellForeground;
	
	private Color FocusRowBackground;
	private Color FocusRowForeground;
	
	private Color FocusColumnBackground;
	private Color FocusColumnForeground;
	
	private Color DefaultBackground;
	private Color DefaultForeground;
	
	private Font Format;
	
	private ImageIcon icono = null;
	
	private int SwingConstant;
	private int focusedRow = -1;
	private int focusedColumn = -1;
	
	public TableCell(){
		
		this.FocusCellBackground = new Color(46, 54, 59);
		this.FocusCellForeground = Color.WHITE;
		this.FocusRowBackground = Color.GRAY;
		this.FocusRowForeground = Color.WHITE;
		this.DefaultBackground = this.getBackground();
		this.DefaultForeground = this.getForeground();
		this.FocusColumnBackground = this.DefaultBackground;
		this.FocusColumnForeground = this.DefaultForeground;
		this.Format = new Font("Verdana", Font.PLAIN, 12);
		this.SwingConstant = SwingConstants.LEFT;
		
	}
	
	public TableCell(TableCell Cell){
		
		this.FocusCellBackground = Cell.FocusCellBackground;
		this.FocusCellForeground = Cell.FocusCellForeground;
		this.FocusRowBackground = Cell.FocusRowBackground;
		this.FocusRowForeground = Cell.FocusRowForeground;
		this.FocusColumnBackground = Cell.FocusColumnBackground;
		this.FocusColumnForeground = Cell.FocusColumnForeground;
		this.setBackground(Cell.DefaultBackground);
		this.setForeground(Cell.DefaultForeground);
		this.DefaultBackground = this.getBackground();
		this.DefaultForeground = this.getForeground();
		this.Format = Cell.Format;
		this.SwingConstant = Cell.SwingConstant;
		
	}
	
	public TableCell(Color FocusRowBackground, Color FocusRowForeground){
		
		this.FocusCellBackground = new Color(46, 54, 59);
		this.FocusCellForeground = Color.WHITE;
		this.FocusRowBackground = FocusRowBackground;
		this.FocusRowForeground = FocusRowForeground;
		this.FocusColumnBackground = Color.GRAY;
		this.FocusColumnForeground = Color.WHITE;
		this.DefaultBackground = this.getBackground();
		this.DefaultForeground = this.getForeground();
		this.Format = new Font("Verdana", Font.PLAIN, 12);
		this.SwingConstant = SwingConstants.LEFT;
		
	}
	
	public TableCell(Color FocusRowBackground, Color FocusRowForeground, Font Format){
		
		this.FocusCellBackground = new Color(46, 54, 59);
		this.FocusCellForeground = Color.WHITE;
		this.FocusRowBackground = FocusRowBackground;
		this.FocusRowForeground = FocusRowForeground;
		this.FocusColumnBackground = Color.GRAY;
		this.FocusColumnForeground = Color.WHITE;
		this.DefaultBackground = this.getBackground();
		this.DefaultForeground = this.getForeground();
		this.Format = Format;
		this.SwingConstant = SwingConstants.LEFT;
		
	}
	
	public TableCell(Color FocusRowBackground, Color FocusRowForeground, Font Format, int SwingConstant){
		
		this.FocusCellBackground = new Color(46, 54, 59);
		this.FocusCellForeground = Color.WHITE;
		this.FocusRowBackground = FocusRowBackground;
		this.FocusRowForeground = FocusRowForeground;
		this.FocusColumnBackground = Color.GRAY;
		this.FocusColumnForeground = Color.WHITE;
		this.DefaultBackground = this.getBackground();
		this.DefaultForeground = this.getForeground();
		this.Format = Format;
		this.SwingConstant = SwingConstant;
		
	}
	
	public TableCell(Color FocusCellBackground, Color FocusCellForeground, Color FocusRowBackground, Color FocusRowForeground, Color FocusColumnBackground, Color FocusColumnForeground, Color DefaultBackground, Color DefaultForeground, Font Format){
		
		this.FocusCellBackground = FocusCellBackground;
		this.FocusCellForeground = FocusCellForeground;
		this.FocusRowBackground = FocusRowBackground;
		this.FocusRowForeground = FocusRowForeground;
		this.FocusColumnBackground = FocusColumnBackground;
		this.FocusColumnForeground = FocusColumnForeground;
		this.setBackground(DefaultBackground);
		this.setForeground(DefaultForeground);
		this.DefaultBackground = this.getBackground();
		this.DefaultForeground = this.getForeground();
		this.Format = Format;
		this.SwingConstant = SwingConstants.LEFT;
		
	}
	
	public TableCell(Font Format, int SwingConstant){
		
		this.FocusCellBackground = new Color(46, 54, 59);
		this.FocusCellForeground = Color.WHITE;
		this.FocusRowBackground = Color.GRAY;
		this.FocusRowForeground = Color.WHITE;
		this.FocusColumnBackground = Color.GRAY;
		this.FocusColumnForeground = Color.WHITE;
		this.DefaultBackground = this.getBackground();
		this.DefaultForeground = this.getForeground();
		this.Format = Format;
		this.SwingConstant = SwingConstant;
		
	}
	
	public TableCell(Color FocusBackground, Color FocusForeground, Color DefaultBackground, Color DefaultForeground, Font Format, int SwingConstant){
		
		this.FocusCellBackground = new Color(46, 54, 59);
		this.FocusCellForeground = Color.WHITE;
		this.FocusRowBackground = FocusBackground;
		this.FocusRowForeground = FocusForeground;
		this.FocusColumnBackground = Color.GRAY;
		this.FocusColumnForeground = Color.WHITE;
		this.setBackground(DefaultBackground);
		this.setForeground(DefaultForeground);
		this.DefaultBackground = this.getBackground();
		this.DefaultForeground = this.getForeground();
		this.Format = Format;
		this.SwingConstant = SwingConstant;
		
	}
	
	public TableCell(Color FocusCellBackground, Color FocusCellForeground, Color FocusRowBackground, Color FocusRowForeground, Color FocusColumnBackground, Color FocusColumnForeground,  Color DefaultBackground, Color DefaultForeground, Font Format, int SwingConstant){
		
		this.FocusCellBackground = FocusCellBackground;
		this.FocusCellForeground = FocusCellForeground;
		this.FocusRowBackground = FocusRowBackground;
		this.FocusRowForeground = FocusRowForeground;
		this.FocusColumnBackground = FocusColumnBackground;
		this.FocusColumnForeground = FocusColumnForeground;
		this.setBackground(DefaultBackground);
		this.setForeground(DefaultForeground);
		this.DefaultBackground = this.getBackground();
		this.DefaultForeground = this.getForeground();
		this.Format = Format;
		this.SwingConstant = SwingConstant;
		
	}
	
	public Color getFocusCellBackground(){
		
		return this.FocusCellBackground;
		
	}
	
	public Color getFocusCellForeground(){
		
		return this.FocusCellForeground;
		
	}
	
	public Color getFocusRowBackground(){
		
		return this.FocusRowBackground;
		
	}
	
	public Color getFocusRowForeground(){
		
		return this.FocusRowForeground;
		
	}
	
	public Color getFocusColumnBackground(){
		
		return this.FocusColumnBackground;
		
	}
	
	public Color getFocusColumnForeground(){
		
		return this.FocusColumnForeground;
		
	}
	
	public Font getFont(){
		
		return this.Format;
		
	}
	
	public int getHorizontalAlignment(){
		
		return this.SwingConstant;
		
	}
	
	public boolean hasImage(){
		
		return this.icono!=null;
		
	}
	
	public void setFocusCellBackground(Color FocusBackground){
		
		this.FocusCellBackground = FocusBackground;
		
	}
	
	public void setFocusCellForeground(Color FocusForeground){
		
		this.FocusCellForeground = FocusForeground;
		
	}
	
	public void setFocusRowBackground(Color FocusBackground){
		
		this.FocusRowBackground = FocusBackground;
		
	}
	
	public void setFocusRowForeground(Color FocusForeground){
		
		this.FocusRowForeground = FocusForeground;
		
	}
	
	public void setFocusColumnBackground(Color FocusBackground){
		
		this.FocusColumnBackground = FocusBackground;
		
	}
	
	public void setFocusColumnForeground(Color FocusForeground){
		
		this.FocusColumnForeground = FocusForeground;
		
	}
	
	public void setDefaultBackground(Color DefaultBackground){
		
		this.DefaultBackground = DefaultBackground;
		this.FocusColumnBackground = this.DefaultBackground;
		
	}
	
	public void setDefaultForeground(Color DefaultForeground){
		
		this.DefaultForeground = DefaultForeground;
		this.FocusColumnForeground = this.DefaultForeground;
		
	}
	
	public void setHorizontalAlignment(int SwingConstant){
		
		this.SwingConstant = SwingConstant;
		
	}
	
	public void setFocusedRow(int Row){
		
		this.focusedRow = Row;
		
	}
	
	public void setFocusedColumn(int Column){
		
		this.focusedColumn = Column;
		
	}
	
	public void setImage(ImageIcon icono){
		
		this.icono = icono;
		
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocused, int Row, int Column) {
		
		hasFocused = (Row==focusedRow && Column==focusedColumn) ? true : false;
		
		Component cp = super.getTableCellRendererComponent(table, value, isSelected, hasFocused, Row, Column);
		
		boolean bd = false;
		
		if (Row==focusedRow || Column==focusedColumn){
			
			if (Row==focusedRow){
			
				this.setBackground(FocusRowBackground);
				this.setForeground(FocusRowForeground);
				
			}
			
			if (Column==focusedColumn){
				
				this.setBackground(FocusColumnBackground);
				this.setForeground(FocusColumnForeground);
				
			}
			
			bd = true;
			
		}else{
			
			this.setBackground(DefaultBackground);
			this.setForeground(DefaultForeground);
			
			bd = false;
			
		}
		
		if (Row==focusedRow && Column==focusedColumn){
			
            this.setBackground(FocusCellBackground);
			this.setForeground(FocusCellForeground);
			
        }else{
			
			if (bd==false){
				
				this.setBackground(DefaultBackground);
				this.setForeground(DefaultForeground);
				
			}
			
		}
		
		this.setFont(Format);
		this.setHorizontalAlignment(SwingConstant);
		
		if (icono!=null){
			
			JLabel Label = (JLabel) cp;
			
			Label.setIcon(new ImageIcon(icono.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH)));
			
			this.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			
			return Label;
			
		}
	
		return cp;
		
	}
	
}