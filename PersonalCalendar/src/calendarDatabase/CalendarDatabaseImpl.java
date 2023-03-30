package calendarDatabase;

import event.Event;
import java.util.Date;
import calendarManager.DataParser;

public class CalendarDatabaseImpl implements CalendarDatabase {

    private Event[] events;
    private int eventCount;

    public CalendarDatabaseImpl() {
        this.events = new Event[1000];
        this.eventCount = 0;
        addDefaultEvents();
    }

    @Override
    public void addEvent(String eventName, String startDate, String startTime, String endDate, String endTime, String comment) {
        Date start = DataParser.parse(startDate, startTime);
        Date end = DataParser.parse(endDate, endTime);
        Event event = new Event(eventName, start, end, comment);
        events[eventCount] = event;
        eventCount++;
    }

    @Override
    public void addDefaultEvents(){
        addEvent("Dentist", "01/04/2022", "09:00", "01/04/2022", "10:00","Not good!");
        addEvent("Phone call", "01/04/2022", "12:00", "01/04/2022", "13:00","good!");
        addEvent("Shopping", "02/04/2022", "09:00", "01/04/2022", "10:00","good!");
        addEvent("Gym session", "02/04/2022", "09:00", "01/04/2022", "10:00","good!");
    }

    @Override
    public Event[] getEventsByDate(Date date) {
        int countEventsForTheDay = 0;
        for (int i = 0; i < eventCount; i++) {
            if (events[i].getStart().getTime() == date.getDate()){
                countEventsForTheDay++;
            }
        }

        Event[] eventsForThisDate = new Event[countEventsForTheDay];
        int eventIndex = 0;
        for (int i = 0; i < eventCount; i++) {
            if (events[i].getStart().getDate() == date.getDate()){
                eventsForThisDate[eventIndex] = events[i];
                eventIndex++;
            }
        }
        return eventsForThisDate;
    }

    @Override
    public Event getEventByName(String desiredEventName) {
        for (int i = 0; i < eventCount; i++) {
            if(events[i].getName().equalsIgnoreCase(desiredEventName)){
                return events[i];
            }
        }
        return null;
    }

    @Override
    public boolean setEventTime(String eventName, String startTime, String endTime) {
        Event thisEvent = getEventByName(eventName);
        Date setNewStartTime = DataParser.parse(startTime);
        Date setNewEndTime = DataParser.parse(endTime);

        if (setNewStartTime != null && setNewEndTime != null){
            thisEvent.getStart().setHours(setNewStartTime.getHours());
            thisEvent.getStart().setMinutes(setNewStartTime.getMinutes());

            thisEvent.getEnd().setHours(setNewEndTime.getHours());
            thisEvent.getEnd().setMinutes(setNewEndTime.getMinutes());
            return true;
        }
        return false;
    }
}
