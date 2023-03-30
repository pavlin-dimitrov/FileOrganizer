package com.company;

import calendarDatabase.CalendarDatabaseImpl;
import calendarManager.CalendarManager;
import communication.CommunicatorImpl;

public class Main {

    public static void main(String[] args) {
	    new CalendarManager(
                new CommunicatorImpl(),
                new CalendarDatabaseImpl()
        ).initialize();
    }
}
