
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
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

        setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel(new FlowLayout());
        JPanel monthView = new JPanel(new GridLayout(6, 6, 1, 1));

//        Shape shape = new Rectangle2D.Double(0, 0, 100, 100);
//        add(shape, BorderLayout.SOUTH);
        setMonthView(monthView);
        monthView.setPreferredSize(new Dimension(150, 150));

        leftPanel.add(monthView);

        JPanel upperPanel = new JPanel(new FlowLayout());
        upperPanel.setPreferredSize(new Dimension( 700, 50));

        add(leftPanel, BorderLayout.WEST);
        add(upperPanel, BorderLayout.NORTH);
    }

    public void setMonthView(JPanel monthView) {
        monthView.add(new JLabel("M"), 0, 0);
        monthView.add(new JLabel("T"), 0, 1);
        monthView.add(new JLabel("W"), 0, 2);
        monthView.add(new JLabel("T"), 0, 3);
        monthView.add(new JLabel("F"), 0, 4);
        monthView.add(new JLabel("S"), 0, 5);
        monthView.add(new JLabel("S"), 0, 6);

        Calendar cal = model.getCal();
        int thisMonth = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        for(int k = 0; k < day; k++) {
            monthView.add(new JLabel(""));
        }
        while (thisMonth == cal.get(Calendar.MONTH)) {
            monthView.add(new JLabel( String.valueOf( cal.get(Calendar.DAY_OF_MONTH))) );
            cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);

        }
    }
}
