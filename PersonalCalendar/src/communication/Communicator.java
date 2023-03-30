package communication;

import event.Event;

import java.util.Date;

public interface Communicator {

    String getTextInput();

    void show(String text);

    Date getDate();

    int getUserOption();

    void showDailyEvents(Event[] dailyEvents);
}
