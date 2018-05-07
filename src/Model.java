import java.io.*;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;

public class Model {

    private Map<String, Event> events;
    private Calendar cal;

    public static final String FORMAT1 = "MM/dd/yy";

    public Model() {
        cal = new GregorianCalendar();
        events = new HashMap<>();
        loadEvents();
    }

    public void moveToNextDay() {
        System.out.println("It was " + cal.get(Calendar.DAY_OF_MONTH));
        cal.add(Calendar.DAY_OF_MONTH, 1);
        System.out.println("Now it's " + cal.get(Calendar.DAY_OF_MONTH));
    }

    public void setDate(int month, int day) {
        if(month >= 0 && month < 12 && day >= 0 && day < 31) {
            cal.set(Calendar.MONTH, month);
            cal.set(Calendar.DAY_OF_MONTH, day);
        }
    }

    public void moveToPrevDay() {
        System.out.println("It was " + cal.get(Calendar.DAY_OF_MONTH));
        cal.add(Calendar.DAY_OF_MONTH, -1);
        System.out.println("Now it's " + cal.get(Calendar.DAY_OF_MONTH));
    }

    public Model(Map<String, Event> newEvents) {
        events = newEvents;
        cal = new GregorianCalendar();
    }

    public boolean checkConflict(String date, String start, String end) {
        for(Event event: events.values()) {
            if(event.checkConflict(start, end) && event.getDate().equals(date))
                return true;
        }

        return false;
    }

    public Calendar getCal() {
        return cal;
    }

    public void createEvent(String _title, String _date, String start, String end ) {

    }

    public String getToday() {
        return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
    }

    public int getCurrentDay() {
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public int getCurrentMonth() {
        return cal.get(Calendar.MONTH);
    }

    public String getDate() {
        SimpleDateFormat format = new SimpleDateFormat(FORMAT1);

        return format.format( cal.getTime() );
    }

    public Map<String, Event> getEvents() {
        return events;
    }

    public ArrayList<Event> getDayEvents(String date) {
        ArrayList<Event> dayEvents = new ArrayList<>();
        for(Event event: events.values()) {
            if(event.getDate().equals(date))
                dayEvents.add(event);
        }

        return dayEvents;
    }

    public ArrayList<Event> getDayEvents() {
        SimpleDateFormat format = new SimpleDateFormat(FORMAT1);
        String date = format.format( cal.getTime() );
        ArrayList<Event> dayEvents = new ArrayList<>();

        for(Event event: events.values()) {
            if(event.getDate().equals(date))
                dayEvents.add(event);
        }

        return dayEvents;
    }

    /**
     * Loads events from events.txt
     */
    public void loadEvents() {

        File f = new File("events.txt");
        if( f.exists() ) {
            String [] temp;
            try {
                List<String> lines = Files.readAllLines(f.toPath());
                for(String line: lines) {
                    temp = line.split(",");
                    events.put(temp[0], new Event(temp) );
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

        } else {
            System.out.println("This is first run, not events created yet");
        }

        System.out.println("Events successfully loaded");
    }

    public void saveEvents() {

        String time;
        String title;


        try (FileWriter fw = new FileWriter("events.txt", false);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            for(Event event: events.values()) {
                time = event.getTime();
                title = event.getTitle();

                out.println(createEventString(title, Long.toString(event.getTimeInMillis()), time));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("Events were saved");
    }

    private String createEventString(String title, String date, String time) {
        return date + "," + title + "," + time;
    }
}
