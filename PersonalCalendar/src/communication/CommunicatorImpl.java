package communication;

import event.Event;

import java.util.Date;
import java.util.Scanner;

public class CommunicatorImpl implements Communicator {

    private final Scanner scanner;

    public CommunicatorImpl() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String getTextInput() {
        return scanner.nextLine();
    }

    @Override
    public void show(String text) {
        System.out.println(text);
    }

    @Override
    public Date getDate() {
        return new Date();
    }

    @Override
    public int getUserOption() {
        return Integer.parseInt(getTextInput());
    }

    @Override
    public void showDailyEvents(Event[] eventsForTheDay) {
        for (Event event : eventsForTheDay) {
            event.toString();
        }
    }
}
