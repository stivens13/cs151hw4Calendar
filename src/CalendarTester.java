import javax.swing.*;
import java.awt.*;

public class CalendarTester {

    public static void main(String [] args) {

        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        JPanel calendar = new StudentCalendar();
//        calendar.setLayout(new BorderLayout());
        frame.add(calendar);
//        frame.pack();
        frame.setSize(new Dimension(700, 700));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
