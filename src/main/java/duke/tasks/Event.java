package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event. Contains the description, done status, and date of the event.
 */
public class Event extends Task {

    public static final String ICON_EVENT = "[E]";
    public static final String OPEN_BRACKET = " (at: ";
    public static final String CLOSE_BRACKET = ")";
    public static final String LETTER_EVENT = "E";
    public static final String COLUMN = " | ";
    public static final String OUTPUT_DATE_FORMAT = "MMM d yyyy";
    public static final String OUTPUT_TIME_FORMAT = "hh:mm a";
    private LocalDate date;
    private LocalTime time;
    private DateTimeFormatter outputDateFormat = DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT);
    private DateTimeFormatter outputTimeFormat = DateTimeFormatter.ofPattern(OUTPUT_TIME_FORMAT);

    public Event(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return ICON_EVENT + super.toString() + OPEN_BRACKET + date.format(outputDateFormat)
                + getTimeToString(time) + CLOSE_BRACKET;
    }

    @Override
    public String toSave() {
        return LETTER_EVENT + COLUMN + super.toSave() + COLUMN + date.format(outputDateFormat)
                + getTimeToSave(time);
    }

    private String getTimeToString(LocalTime time) {
        String formattedTime;
        if (time == LocalTime.MAX) {
            formattedTime = "";
        }
        else {
            formattedTime = " " + time.format(outputTimeFormat);
        }
        return formattedTime;
    }

    private String getTimeToSave(LocalTime time) {
        String formattedTime;
        if (time == LocalTime.MAX) {
            formattedTime = "";
        }
        else {
            formattedTime = COLUMN + time.format(outputTimeFormat);
        }
        return formattedTime;
    }
}
