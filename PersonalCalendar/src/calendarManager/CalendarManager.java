package calendarManager;

import calendarDatabase.CalendarDatabase;
import communication.Communicator;
import event.Event;
import java.util.Date;

public record CalendarManager(Communicator communicator,
                              CalendarDatabase calendarDatabase) {

    public void initialize() {
        while (true) {
            communicator.show("Personal Calendar");
            communicator.show(previewSystemOptions());
            int userChoice = communicator.getUserOption();
            switch (userChoice) {
                case 1 -> createEvent();
                case 2 -> previewDailyProgram();
                case 3 -> previewEventDetails();
                case 4 -> changeEventTime();
                //TODO option 5 search for free space for event
            }
        }
    }

    private void changeEventTime() {
        communicator.show("Enter event name: ");
        String eventName = communicator.getTextInput();
        while (true) {
            System.out.println("Event start time " + DataParser.TIME_FORMAT);
            String startTime = communicator.getTextInput();
            System.out.println("Event end time " + DataParser.TIME_FORMAT);
            String endTime = communicator.getTextInput();

            if (DataParser.isCorrectTime(startTime) &&
                    DataParser.isCorrectTime(endTime)) {
                calendarDatabase.setEventTime(eventName, startTime, endTime);
                break;
            } else {
                communicator.show("Invalid input!");
            }
        }
    }

    private void previewEventDetails() {
        communicator.show("Enter event name: ");
        String desiredEventName = communicator.getTextInput();
        Event currentEvent = calendarDatabase.getEventByName(desiredEventName);
        if (currentEvent != null) {
            communicator.show(currentEvent.toString());
        } else {
            communicator.show("No such event exists!");
        }
    }

    private void previewDailyProgram() {
        Date desiredDate = communicator.getDate();
        communicator.show("Program for today");
        communicator.showDailyEvents(calendarDatabase.getEventsByDate(desiredDate));
    }

    private void createEvent() {
        System.out.println("Enter event details");
        System.out.println("Event name:");
        String name = communicator.getTextInput();
        System.out.println("Event start date " + DataParser.DATE_FORMAT);
        String startDate = communicator.getTextInput();
        System.out.println("Event start time " + DataParser.TIME_FORMAT);
        String startTime = communicator.getTextInput();
        System.out.println("Event end date " + DataParser.DATE_FORMAT);
        String endDate = communicator.getTextInput();
        System.out.println("Event end time " + DataParser.TIME_FORMAT);
        String endTime = communicator.getTextInput();
        System.out.println("Event comment:");
        String comment = communicator.getTextInput();

        calendarDatabase.addEvent(name, startDate, startTime, endDate, endTime, comment);
    }

    private String previewSystemOptions() {
        return """
                1. Create new event
                2. View events for today
                3. View event details
                4. Change event time""";
    }
}
