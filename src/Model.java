import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

public class Model {

    private Map<String, Event> events;
    Calendar cal;

    public Model() {
        cal = new GregorianCalendar();
    }

    public Model(Map<String, Event> newEvents) {
        events = newEvents;
        cal = new GregorianCalendar();
    }

    public Calendar getCal() {
        return cal;
    }

    public Map<String, Event> getEvents() {
        return events;
    }
}
