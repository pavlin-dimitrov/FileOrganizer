package calendarDatabase;

import event.Event;
import java.util.Date;

public interface CalendarDatabase {

    void addEvent(String eventName, String startDate, String startTime,
                  String endDate, String endTime, String comment);

    void addDefaultEvents();

    Event[] getEventsByDate(Date date);

    Event getEventByName(String desiredEventName);

    boolean setEventTime(String eventName, String startTime, String endTime);
}
