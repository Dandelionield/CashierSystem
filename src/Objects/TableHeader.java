package Objects;

import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JComponent;
import javax.swing.SwingConstants;

import javax.swing.border.MatteBorder;
import javax.swing.border.LineBorder;

import javax.swing.table.TableCellRenderer;

public class TableHeader implements TableCellRenderer{
	
	private Color Background;
	private Color Foreground;
	private Font Format;
	
	private String Title;
	
	private int InnerBorder = 1;
	
	public TableHeader(){
		
		this.Background = null;
		this.Foreground = Color.BLACK;
		this.Format = new Font("Clarendon Blk BT", Font.BOLD, 12);
		this.Title = "Data Table";
		
	}
	
	public TableHeader(Color Background){
		
		this.Background = Background;
		this.Foreground = Color.BLACK;
		this.Format = new Font("Clarendon Blk BT", Font.BOLD, 12);
		this.Title = "Data Table";
		
	}
	
	public TableHeader(Color Background, Color Foreground){
		
		this.Background = Background;
		this.Foreground = Foreground;
		this.Format = new Font("Clarendon Blk BT", Font.BOLD, 12);
		this.Title = "Data Table";
		
	}
	
	public TableHeader(Font Format){
		
		this.Background = null;
		this.Foreground = Color.BLACK;
		this.Format = Format;
		this.Title = "Data Table";
		
	}
	
	public TableHeader(String Title){
		
		this.Background = null;
		this.Foreground = Color.BLACK;
		this.Format = new Font("Clarendon Blk BT", Font.BOLD, 12);
		this.Title = Title;
		
	}
	
	public TableHeader(Color Background, Color Foreground, Font Format, String Title){
		
		this.Background = Background;
		this.Foreground = Foreground;
		this.Format = Format;
		this.Title = Title;
		
	}
	
	public Color getBackground(){
		
		return this.Background;
		
	}
	
	public Color getForeground(){
		
		return this.Foreground;
		
	}
	
	public Font getFont(){
		
		return this.Format;
		
	}
	
	public void setBackground(Color Background){
		
		this.Background = Background;
		
	}
	
	public void setForeground(Color Foreground){
		
		this.Foreground = Foreground;
		
	}
	
	public void setFont(Font Format){
		
		this.Format = Format;
		
	}
	
	public String getTitle(){
		
		return this.Title;
		
	}
	
	public void showInnerBorder(boolean value){
		
		this.InnerBorder = value ? 1 : 0;
		
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int Row, int Column) {
        
        JComponent Header = Header = new JLabel((String) value);
		
		((JLabel) Header).setHorizontalAlignment(SwingConstants.CENTER);
        ((JLabel) Header).setSize(20, Header.getWidth());   
        ((JLabel) Header).setPreferredSize(new Dimension(6, Header.getWidth()));	         
   
        Header.setOpaque(true);
        Header.setBackground(this.Background);
        Header.setToolTipText(Title);
        Header.setForeground(this.Foreground);
		Header.setFont(this.Format);
		Header.setBorder(new MatteBorder(0, 0, 1, (table.getColumnCount()-1!=Column) ? InnerBorder : 0, Color.GRAY));
		
        return Header;
    }
	
}