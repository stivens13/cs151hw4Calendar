
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class StudentCalendar extends JPanel { //} implements ChangeListener{

    enum MONTHS {
        January, February, March, April, May, June, July, August, September, October, November, December
    }

    enum DAYS {
        Sun, Mon, Tue, Wed, Thur, Fri, Sat,
    }

//    JFrame frame;
    private Model model;

    private JButton quitButton;
    private JPanel upperPanel;
    JPanel leftPanel;
    private int currentMonth;
    private int currentDay;

    public StudentCalendar(Model _model) {

        model = _model;

        currentDay = model.getCurrentDay();
        currentMonth = model.getCurrentMonth();

//        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        draw();
    }


    public void updateView() {
//        removeAll();
        draw();
//        revalidate();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

//        Graphics2D g2 = (Graphics2D) g;

        draw();
////        repaint();
//        revalidate();
    }

//    @Override
//    public void stateChanged(ChangeEvent e) {
////        frame.repaint();
//        update(g);
//    }


    private void draw() {

        upperPanel = new JPanel(new FlowLayout());
        upperPanel.setPreferredSize(new Dimension( 700, 50));
//        JLabel placeHolder = new JLabel("PLACEHOLDER");
//        upperPanel.add(placeHolder);


        JPanel leftPanel = new JPanel();
//        JPanel leftPanel = new JPanel(new GridBagLayout());
        setLeftViewPanel(leftPanel);

        JPanel eventsPanel = new JPanel(); //new GridLayout(6, 1));
        setUpperViewPanel();
        setEventsPanel(eventsPanel);

        JScrollPane scrollPane = new JScrollPane(eventsPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(upperPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
//        add(eventsPanel, BorderLayout.CENTER);
    }

    private void setUpperViewPanel() {

        JButton leftButton = new JButton("<");
        JButton rightButton = new JButton(">");

        leftButton.addActionListener(e -> {
            model.moveToPrevDay();
//            currentDay = model.getCurrentDay();
            if(currentMonth != model.getCurrentMonth()) {
                currentMonth = model.getCurrentMonth();
            }
            updateView();
        });

        rightButton.addActionListener(e -> {
            model.moveToNextDay();
//            currentDay = model.getCurrentDay();
            if(currentMonth != model.getCurrentMonth()) {
                currentMonth = model.getCurrentMonth();
            }
            updateView();
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
                model.setDay(currentDay);
                System.out.println("Day " + currentDay + " is pressed");
//                frame.repaint();
                updateView();
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
        } else if(title.equals("")) {
            JOptionPane.showMessageDialog(new JFrame(), "Please, type proper title");
            createEvent();
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

        for(int c = 6; c < 24; c++) {
            eventHolder = new JPanel(new FlowLayout(FlowLayout.LEADING,1,2));
            JPanel timeCell = new JPanel();
            timeCell.add(new JLabel(c + "am"));
            JLabel eventTitle = new JLabel();
            for(Event event: dayEvents) {
                if(event.getEventBegins() == c) {
                    eventTitle.setText(event.getTitle());
                    break;
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

        eventsPanel.setPreferredSize(new Dimension(500, 200));
        eventsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    public JButton getQuitButton() {
        return quitButton;
    }
}
