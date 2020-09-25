package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    public static final String ICON_EVENT = "[E]";
    public static final String DATE_PREFIX = " (at: ";
    public static final String DATE_POSTFIX = ")";
    public static final String LETTER_EVENT = "E";
    public static final String DELIMITER = " | ";
    public static final String OUTPUT_FORMAT = "MMM d yyyy";
    private LocalDate date;

    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return ICON_EVENT + super.toString() + DATE_PREFIX + date.format(DateTimeFormatter.ofPattern(OUTPUT_FORMAT)) + DATE_POSTFIX;
    }

    @Override
    public String toSave() {
        return LETTER_EVENT + DELIMITER + super.toSave() + DELIMITER + date.format(DateTimeFormatter.ofPattern(OUTPUT_FORMAT));
    }
}
