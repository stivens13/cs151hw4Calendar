
import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class StudentCalendar extends JPanel {

    enum MONTHS {
        January, February, March, April, May, June, July, August, September, October, November, December
    }

    enum DAYS {
        Sun, Mon, Tue, Wed, Thur, Fri, Sat,
    }

    private Model model;

    public StudentCalendar() {

        model = new Model();

//        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        init();
    }

    public void init() {

        GridBagConstraints c = new GridBagConstraints();

        JPanel upperPanel = new JPanel(new FlowLayout());
        upperPanel.setPreferredSize(new Dimension( 700, 50));
        JLabel placeHolder = new JLabel("PLACEHOLDER");
        upperPanel.add(placeHolder);
//        c.weightx = 0.5;
//        c.gridx = 0;
//        c.gridy = 0;
//        add(upperPanel, c);


        JPanel leftPanel = new JPanel();
//        JPanel leftPanel = new JPanel(new GridBagLayout());
        setLeftViewPanel(leftPanel);
//        c.fill = GridBagConstraints.EAST;
//        c.weightx = 0.5;
//        c.gridx = 1;
//
//        c.gridy = 0;
//        add(leftPanel, c);

        JPanel eventsPanel = new JPanel(new GridLayout(6, 1));
//        JPanel eventsPanel = new JPanel();
        setEventsPanel(eventsPanel);

        add(upperPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(eventsPanel, BorderLayout.CENTER);
    }

    public void setEventsPanel(JPanel eventsPanel) {

//        eventsPanel.setLayout(new BoxLayout(eventsPanel, BoxLayout.Y_AXIS));
//        eventsPanel.setBackground(Color.lightGray);
        JScrollPane scrollPane = new JScrollPane(eventsPanel);
        JPanel eventHolder = new JPanel();
//        eventHolder.setLayout(new BoxLayout(eventHolder, BoxLayout.Y_AXIS));

        for(int c = 0; c < 6; c++) {
//            eventHolder.add();
//            eventsPanel.add(new JSeparator());
            JLabel event = new JLabel(c + 5 + " am");
            event.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            eventsPanel.add(event);
            eventsPanel.add(new JLabel("PLACEHOLDER"));
        }

        eventsPanel.setLayout(new GridLayout(6, 0));
//        eventsPanel.setSize();

        eventsPanel.setMinimumSize(new Dimension(700 - 150, 150));
        eventsPanel.setPreferredSize(new Dimension(700 - 150, 150));
        eventsPanel.setMaximumSize(new Dimension(700 - 150, 500));

        add(scrollPane, BorderLayout.CENTER);
    }

    public void setLeftViewPanel(JPanel leftPanel) {

        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        GridBagConstraints c = new GridBagConstraints();
        JPanel monthView = new JPanel(new GridLayout(6, 6, 1, 1));

        setMonthView(monthView);
        monthView.setPreferredSize(new Dimension(150, 150));

//        JPanel buttonHolder = new JPanel(new BorderLayout());
//        JLabel button = getButton("CREATE", Color.RED, Color.WHITE);
//        buttonHolder.add(button, BorderLayout.CENTER);
//        buttonHolder.setSize(new Dimension(40, 20));


        JButton button = new JButton("CREATE");
        button.setBackground(Color.RED);
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setBorderPainted(false);
//        button.setHorizontalAlignment(Component.LEFT_ALIGNMENT);

        button.addActionListener(e -> System.out.println("Create pressed"));

//        buttonHolder.setBorder(BorderFactory.createEmptyBorder(0,40,0,40));

//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.weightx = 0.5;
//        c.gridwidth = 1;
//        c.gridx = 0;
//        c.gridy = 0;
//        c.ipady = 40;
//        c.ipadx = 10;

//        leftPanel.add(buttonHolder, c);

        leftPanel.add(button);

        JLabel monthYear = new JLabel( MONTHS.values()[model.getCal().get(Calendar.MONTH)] + " " + model.getCal().get(Calendar.YEAR) );
        monthYear.setAlignmentX(Component.CENTER_ALIGNMENT);

//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.weightx = 0.5;
//        c.gridx = 0;
//        c.gridy = 1;
//        leftPanel.add(monthYear, c);

        leftPanel.add(monthYear);

//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.weightx = 0.5;
//        c.gridx = 0;
//        c.gridy = 2;
//        leftPanel.add(monthView, c);

        leftPanel.add(monthView);
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

    public JLabel getButton(String text, Color background, Color foregroung) {

        JLabel button = new JLabel(text);
        button.setBackground(background);
        button.setForeground(foregroung);
        button.setSize(new Dimension(200, 40));
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setOpaque(true);
        return button;
    }
}
