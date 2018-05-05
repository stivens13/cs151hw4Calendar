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

    public Event(String _title, String _date, String _time, long _id) {


        calendar = new GregorianCalendar();
        format = new SimpleDateFormat( FORMAT1 );

        calendar.setTimeInMillis(_id);
        title = _title;
        time = _time;
//        date = _date;

        setTime(_time);
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

    public Integer getEventBegins() {
        return eventBegins;
    }

    public Integer getEventEnds() {
        return eventEnds;
    }

    public String getDate() {
        return format.format(calendar.getTime());
    }

    public boolean checkConflict(String time) {
        if(eventEnds == 0) return false;
        String temp [] = time.split("-");
        int a, b;
        a = Integer.parseInt( temp[0].replaceAll(":", "") );
        try {
            b = Integer.parseInt( temp[1].replaceAll(":", "") );
            if(b == 0) {
                return (a > eventBegins && a < eventEnds);
            } else return (a > eventBegins && a < eventEnds) || (b > eventBegins && b < eventEnds);
        } catch (Exception e) {
            return (a > eventBegins && a < eventEnds);
        }
    }

}
