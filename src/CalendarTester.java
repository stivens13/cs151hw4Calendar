import javax.swing.*;
import java.awt.*;

public class CalendarTester {

    public static void main(String [] args) {

        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        Model model = new Model();
        JPanel calendar = new StudentCalendar(model);
        setQuitButton(frame, calendar, model);
//        calendar.setLayout(new BorderLayout());
        frame.add(calendar);
        frame.pack();
//        frame.setSize(new Dimension(700, 700));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    static void setQuitButton(JFrame frame, JPanel calendar, Model model) {

        ((StudentCalendar) calendar).getQuitButton().addActionListener(e -> {
            frame.dispose();
            model.saveEvents();
            JOptionPane.showMessageDialog(new JFrame(), "Program is terminated and all events are saved");
            System.out.println("All events are saved");
        });
    }
}
