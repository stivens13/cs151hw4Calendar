import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Event {

    private Calendar calendar;

    private String title;
    private String time;
    SimpleDateFormat format;

    private Integer eventBegins;
    private Integer eventEnds;

    public static final String FORMAT1 = "MM/dd/yyyy";
    public static final String FORMAT2 = "MM/dd/yyyy:HH:mm";

    public Event(String [] event) {

        calendar = new GregorianCalendar();
        format = new SimpleDateFormat( FORMAT1 );

        try { calendar.setTimeInMillis( Long.parseLong( event[0] ) ) ; title = event[1];  time = event[2]; setTime(event[2]); }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Overriden public constructor for multiple parameters
     * @param _title of event
     * @param _date of event
     * @param _time of event
     * @param _id is time in milliseconds
     */

    public Event(String _title, String _date, String _time, long _id) {


        calendar = new GregorianCalendar();
        format = new SimpleDateFormat( FORMAT1 );

        calendar.setTimeInMillis(_id);
        title = _title;
        time = _time;
//        date = _date;

        setTime(_time);
    }

    /**
     * Access time in milliseconds
     * @return time in milliseconds
     */

    public Long getTimeInMillis() {
        return calendar.getTimeInMillis();
    }

    private void setTime(String time) {
        String temp [] = time.split("-");
        eventBegins = Integer.parseInt( temp[0].replaceAll(":", "") );
        try {
            eventEnds = Integer.parseInt( temp[1].replaceAll(":", "") );
        } catch (Exception e) {
            eventBegins = 0;
        }
    }

    /**
     * Returns time in string
     * @return string time
     */
    public String getTime() {
        return time;
    }

    /**
     * Accesses title of the event
     * @return title of event
     */
    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return time + "," + title + "," + eventBegins + "-" + eventEnds;
    }

    public Integer getEventBegins() {
        return eventBegins;
    }

    public Integer getEventEnds() {
        return eventEnds;
    }

    public String getDate() {
        return format.format(calendar.getTime());
    }

    /**
     * Check for conflict
     * @return true is there is no conflict
     */
    public boolean checkConflict(String timeBeg, String timeEnd) {
        if(eventEnds == 0) return false;
        String temp [] = time.split("-");
        int a, b;
        a = Integer.parseInt( timeBeg.replaceAll(":", "") );
        try {
            b = Integer.parseInt( timeEnd.replaceAll(":", "") );
            if(b == 0) {
                return (a > eventBegins && a < eventEnds);
            } else return (a > eventBegins && a < eventEnds) || (b > eventBegins && b < eventEnds);
        } catch (Exception e) {
            return (a > eventBegins && a < eventEnds);
        }
    }

}
