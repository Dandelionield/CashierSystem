import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CustomButton extends JButton{
    private boolean isFocus = false;
    private boolean isPressed = false;
    private Color colorFocus = Color.GRAY;
    private Color colorPressed = Color.RED;

    public CustomButton() {
        setContentAreaFilled(false);
        setBorder(null);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                isFocus = false;

                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                isFocus = true;

                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                isPressed = true;

                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isPressed = false;

                repaint();
            }
        });
    }

    public void setIcon(String url) {
        setIcon(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance((getWidth() - 1) * 2/3, (getHeight() - 1) * 2/3, Image.SCALE_SMOOTH)));
    }

    public void setColorFocus(Color colorFocus) {
        this.colorFocus = colorFocus;
    }

    public void setColorPressed(Color colorPressed) {
        this.colorPressed = colorPressed;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        Ellipse2D ellipse = new Ellipse2D.Float(0, 0, getWidth() - 1, getHeight() - 1);

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (isFocus) {
            g2.setColor(colorFocus);
            g2.fill(ellipse);
        }

        if (isPressed) {
            g2.setColor(colorPressed);
            g2.fill(ellipse);
        }

        super.paintComponent(g);
    }     
}
