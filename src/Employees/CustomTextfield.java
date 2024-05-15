import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

class CustomTextField extends JTextField{
    private Color colorSelect = Color.GRAY;
    private Color colorDeselect = Color.BLACK;
    private Color colorPlaceholder = Color.GRAY;

    private String placeholder;
    private String matchesString = ".*";
    private int arc = 10;
    private int heightFont;
    private int widthFont;
    private boolean isFocus = false;
    private boolean isMatches = false;

    public CustomTextField(String placeholder) {
        this.placeholder = placeholder;

        Rectangle2D fontBounds = getFont().getStringBounds(placeholder, new FontRenderContext(new AffineTransform(),true,true));
        heightFont = (int) fontBounds.getHeight()/2;
        widthFont = (int) fontBounds.getWidth();

        setBorder(new EmptyBorder(heightFont/2+heightFont, 5, 0, 5));
        setOpaque(false);

        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                isFocus = true;

                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                isFocus = false;

                repaint();
            }
        });
    }

    public void setBorderColor(Color select, Color deselect) {
        colorSelect = select;
        colorDeselect = deselect;
    }

    public void setPlaceHolderColor(Color color) {
        colorPlaceholder = color;
    }

    public void setMatchesString(String matchesString) {
        this.matchesString = matchesString;
    }

    public boolean getIsMatches() {
        return isMatches;
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y - heightFont, width, height + heightFont);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        RoundRectangle2D rounded = new RoundRectangle2D.Float(1, heightFont, getWidth() - 2, getHeight() - heightFont - 1, arc, arc);

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(rounded);

        isMatches = !getText().matches(matchesString) && !getText().isEmpty();
        
        g2.setColor(isMatches ? Color.RED : (isFocus ? colorSelect : colorDeselect));
        g2.draw(rounded);

        if (isFocus || !getText().isEmpty()) {
            g2.setColor(getBackground());
            g2.drawLine(5, heightFont, widthFont + 5, heightFont);
        }
        
        if (isFocus) { //Si se le hace focus
            g2.setColor(isMatches ? Color.RED : colorSelect);
            g2.drawString(placeholder, 5, heightFont+(heightFont/2));
        } else { //Si no se le hace focus
            if (getText().isEmpty()) { //Si esta vacio
                g2.setColor(colorPlaceholder);
                g2.drawString(placeholder, 5, (getHeight()+2*heightFont)/2);
            } else { //Si no esta vacio
                g2.setColor(isMatches ? Color.RED : colorPlaceholder);
                g2.drawString(placeholder, 5, heightFont+(heightFont/2));
            }
        }
        
        super.paintComponent(g);
    }
}