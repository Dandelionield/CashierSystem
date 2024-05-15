import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.Color;

public class RoundedPanel extends JPanel {
    private Color borderColor = Color.BLACK;
    private int arc = 20;

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public void setArc(int arc) {
        this.arc = arc;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        
        RoundRectangle2D rounded = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);
        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fill(rounded);
        g2.setColor(borderColor);
        g2.draw(rounded);
    }
}