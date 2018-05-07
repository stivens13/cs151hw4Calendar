import javax.swing.*;
import java.awt.*;

public class CalendarTester {

    public static void main(String [] args) {

        SwingUtilities.invokeLater(CalendarTester::startCalendar);
    }

    static void startCalendar() {

        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        Model model = new Model();
        JPanel calendar = new StudentCalendar(model);
        setQuitButton(frame, calendar, model);
        frame.add(calendar);
        frame.pack();
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
