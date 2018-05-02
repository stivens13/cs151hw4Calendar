import com.sun.org.apache.xpath.internal.operations.Mod;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class StudentCalendar extends JPanel {

    enum MONTHS {
        Jan, Feb, March, Apr, May, June, July, Aug, Sep, Oct, Nov, Dec
    }

    enum DAYS {
        Sun, Mon, Tue, Wed, Thur, Fri, Sat,
    }

    private Model model;

    public StudentCalendar() {

        model = new Model();

        JPanel leftPanel = new JPanel(new FlowLayout());
        JPanel monthView = new JPanel(new GridLayout(0, 7));

        setMonthView(monthView);
        monthView.setPreferredSize(new Dimension(200, 300));

        leftPanel.add(monthView);

        JPanel upperPanel = new JPanel(new FlowLayout());
        upperPanel.setPreferredSize(new Dimension( 700, 50));

        add(leftPanel);
        add(upperPanel);
    }

    public void setMonthView(JPanel monthView) {
        monthView.add(new JLabel("M"), 0, 0);
        monthView.add(new JLabel("T"), 0, 1);
        monthView.add(new JLabel("W"), 0, 2);
        monthView.add(new JLabel("T"), 0, 3);
        monthView.add(new JLabel("F"), 0, 4);
        monthView.add(new JLabel("S"), 0, 5);
        monthView.add(new JLabel("S"), 0, 6);

//        Calendar cal = model.getCal();
//        int thisMonth = cal.get(Calendar.MONTH);
//        while (thisMonth == cal.get(Calendar.MONTH)) {
//            monthView.add(new JLabel());
//
//        }
    }
}
