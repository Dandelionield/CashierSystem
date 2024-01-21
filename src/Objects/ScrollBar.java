package Objects;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import javax.swing.plaf.basic.BasicScrollBarUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.RenderingHints;

public class ScrollBar extends BasicScrollBarUI {
	
	private Color Background;
	private Color ThumbBackground;
	
	public ScrollBar(){
		
		this.Background = Color.WHITE;
		this.ThumbBackground = Color.GRAY;
		
		innit();
		
	}
	
	public ScrollBar(Color Background){
		
		this.Background = Background;
		this.ThumbBackground = Color.GRAY;
		
		innit();
		
	}
	
	public ScrollBar(Color Background, Color ThumbBackground){
		
		this.Background = Background;
		this.ThumbBackground = ThumbBackground;
		
		innit();
		
	}
	
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds){
		
        Graphics2D g2 = (Graphics2D) g;
		
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(this.ThumbBackground);
        g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 5, 5);
		
    }

    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds){
		
        Graphics2D g2 = (Graphics2D) g;
		
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(this.Background);
        g2.fillRoundRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height, 5, 5);
		
    }
	
	protected JButton createIncreaseButton(int orientation){
		
		JButton Arrow = new JButton(new Icon(){
            
            public void paintIcon(Component c, Graphics g, int x, int y){
				
				Graphics2D g2d = (Graphics2D) g.create();
				
				RenderingHints Render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2d.setRenderingHints(Render);
			
                g2d.setColor(Color.BLACK);
				g2d.fillOval(x - 1, y, getIconWidth(), getIconHeight());
                g2d.dispose();
			
            }

            public int getIconWidth(){
		
               return 5;
				
            }

            public int getIconHeight(){
			
				return 5;
				
            }
			
        });
		
		Arrow.setBackground(Background);
		Arrow.setFocusable(false);
		Arrow.setBorderPainted(false);
		
		return Arrow;
	
    }
	
	protected JButton createDecreaseButton(int orientation){
		
		JButton Arrow = new JButton(new Icon(){
            
            public void paintIcon(Component c, Graphics g, int x, int y){
				
				Graphics2D g2d = (Graphics2D) g.create();
				
				RenderingHints Render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2d.setRenderingHints(Render);
			
                g2d.setColor(Color.BLACK);
				g2d.fillOval(x - 1, y, getIconWidth(), getIconHeight());
                g2d.dispose();
				
            }

            public int getIconWidth(){
		
               return 5;
				
            }

            public int getIconHeight(){
			
				return 5;
				
            }
			
        });
		
		Arrow.setBackground(Background);
		Arrow.setFocusable(false);
		Arrow.setBorderPainted(false);
		
		return Arrow;
		
    }
	
	private void setScrollBarWidth(JScrollBar scrollBar, int width){
		
        Dimension dim = new Dimension(width, Integer.MAX_VALUE);
        scrollBar.setPreferredSize(dim);
		
    }
	
	public Dimension getPreferredSize(JComponent c){
		
        setScrollBarWidth((JScrollBar) c, 5);
        return super.getPreferredSize(c);
		
    }
	
	private void innit(){
		
		//this.setBackground(this.Background);
		
	}
	
}
	