
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class StudentCalendar extends JPanel implements ChangeListener{

    enum MONTHS {
        January, February, March, April, May, June, July, August, September, October, November, December
    }

    enum DAYS {
        Sun, Mon, Tue, Wed, Thur, Fri, Sat,
    }

    JFrame frame;
    private Model model;

    private JButton quitButton;
    JPanel upperPanel;
    JPanel leftPanel;
    int currentMonth;
    int currentDay;

    public StudentCalendar(Model _model) {

        model = _model;

        currentDay = model.getCurrentDay();
        currentMonth = model.getCurrentMonth();

//        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        init();
    }

    public StudentCalendar(JFrame _frame, Model _model) {

        model = _model;
        frame = _frame;

        currentDay = model.getCurrentDay();
        currentMonth = model.getCurrentMonth();

//        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        init();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

//        Graphics2D g2 = (Graphics2D) g;

        init();
//        repaint();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        frame.repaint();
    }


    private void init() {

        upperPanel = new JPanel(new FlowLayout());
        upperPanel.setPreferredSize(new Dimension( 700, 50));
//        JLabel placeHolder = new JLabel("PLACEHOLDER");
//        upperPanel.add(placeHolder);


        JPanel leftPanel = new JPanel();
//        JPanel leftPanel = new JPanel(new GridBagLayout());
        setLeftViewPanel(leftPanel);

        JPanel eventsPanel = new JPanel(); //new GridLayout(6, 1));
//        JPanel eventsPanel = new JPanel();
        setUpperViewPanel(upperPanel);
        setEventsPanel(eventsPanel);

        add(upperPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(eventsPanel, BorderLayout.CENTER);
    }

    private void setUpperViewPanel(JPanel upperPanel) {

        JButton leftButton = new JButton("<");
        JButton rightButton = new JButton(">");

        leftButton.addActionListener(e -> {
            model.moveToPrevDay();
            if(currentMonth != model.getCurrentMonth()) {
                currentMonth = model.getCurrentMonth();
            }
            revalidate();
            frame.repaint();
        });

        rightButton.addActionListener(e -> {
            model.moveToNextDay();
            if(currentMonth != model.getCurrentMonth()) {
                currentMonth = model.getCurrentMonth();
            }
            revalidate();
            frame.repaint();
        });

        quitButton = new JButton("Quit");

        upperPanel.add(leftButton);
        upperPanel.add(rightButton);
        upperPanel.add(quitButton);


    }

    private void setLeftViewPanel(JPanel leftPanel) {

        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        GridBagConstraints c = new GridBagConstraints();
        JPanel monthView = new JPanel(new GridLayout(6, 6, 1, 1));

        setMonthView(monthView);
        monthView.setPreferredSize(new Dimension(150, 150));


        JButton button = new JButton("CREATE EVENT");
        button.setBackground(Color.RED);
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.addActionListener(e -> {
            System.out.println("Create pressed");
            createEvent();
        });

        leftPanel.add(button);

        JLabel monthYear = new JLabel( MONTHS.values()[model.getCal().get(Calendar.MONTH)] + " " + model.getCal().get(Calendar.YEAR) );
        monthYear.setAlignmentX(Component.CENTER_ALIGNMENT);

        leftPanel.add(monthYear);

        leftPanel.add(monthView);
    }

    private void setMonthView(JPanel monthView) {
        monthView.add(new JLabel("M"), 0, 0);
        monthView.add(new JLabel("T"), 0, 1);
        monthView.add(new JLabel("W"), 0, 2);
        monthView.add(new JLabel("T"), 0, 3);
        monthView.add(new JLabel("F"), 0, 4);
        monthView.add(new JLabel("S"), 0, 5);
        monthView.add(new JLabel("S"), 0, 6);

        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.DAY_OF_MONTH, 1);
//        Calendar cal = model.getCal();
        int thisMonth = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        for(int k = 0; k < day; k++) {
            monthView.add(new JLabel(""));
        }

        while (currentMonth == cal.get(Calendar.MONTH)) {
            JButton buttonDay = new JButton( String.valueOf( cal.get(Calendar.DAY_OF_MONTH)));
            if(cal.get(Calendar.DAY_OF_MONTH) == model.getCal().get(Calendar.DATE))
                buttonDay.setBorderPainted(true);
            else
                buttonDay.setBorder(BorderFactory.createEmptyBorder());
            buttonDay.addActionListener(e -> {
//                model.setDate(currentMonth, currentDay);
                currentDay = Integer.parseInt(buttonDay.getText());
                System.out.println("Day " + buttonDay.getText() + " is pressed");
                frame.repaint();
            });
            monthView.add( buttonDay);
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

    private void createEvent() {
        JPanel prompt = new JPanel();

        JTextField date = new JTextField(model.getDate(), 6);
        JTextField timeBegins = new JTextField("14:30", 3);
        JTextField timeEnds = new JTextField("16:00", 3);
        JLabel time = new JLabel("Enter Title");

        prompt.add(time);
        prompt.add(date);
        prompt.add(timeBegins);
        prompt.add(timeEnds);
        String title = JOptionPane.showInputDialog(prompt);

        if(title == null) {
            System.out.println("Event creating canceled");
            return;
        } else if(model.checkConflict(date.getText(), timeBegins.getText(), timeEnds.getText())) {
            JOptionPane.showMessageDialog(new JFrame(), "Time conflicts with another event, try different time.");
            createEvent();
        } else {
            model.createEvent(title, date.getText(), timeBegins.getText(), timeEnds.getText());
            JOptionPane.showMessageDialog(new JFrame(), "Event successfully created");
            System.out.println("Event created: " + title + ", " + date.getText() + ", " + timeBegins.getText());
        }
    }

    private void setEventsPanel(JPanel eventsPanel) {

        eventsPanel.setLayout(new BoxLayout(eventsPanel, BoxLayout.Y_AXIS));

        JPanel eventHolder;

        ArrayList<Event> dayEvents = model.getDayEvents();

        for(int c = 0; c < 6; c++) {
            eventHolder = new JPanel(new FlowLayout());
            JPanel timeCell = new JPanel();
            timeCell.add(new JLabel(c + "am"));
            JLabel eventTitle = new JLabel();
            for(Event event: dayEvents) {
                if(event.getEventBegins() == c) {
                    eventTitle.setText(event.getTime());
                }
            }

            timeCell.add(eventTitle);
            timeCell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            eventHolder.add(timeCell);
            eventHolder.add(eventTitle);
            eventHolder.setAlignmentX(Component.LEFT_ALIGNMENT);
            eventHolder.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            eventsPanel.add(eventHolder);
        }

        eventsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    public JButton getQuitButton() {
        return quitButton;
    }
}
