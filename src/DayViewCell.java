import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class DayViewCell extends JPanel {

    public DayViewCell(JLabel label) {
        Shape shape;
        add(label);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.draw(new Rectangle2D.Double(0,0, 10, 10));
    }
}
