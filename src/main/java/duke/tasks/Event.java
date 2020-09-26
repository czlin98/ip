package duke.tasks;

/**
 * Represents an event. Contains the description, done status, and date of the event.
 */
public class Event extends Task {

    public static final String ICON_EVENT = "[E]";
    public static final String DATE_PREFIX = " (at: ";
    public static final String DATE_POSTFIX = ")";
    public static final String LETTER_EVENT = "E";
    public static final String DELIMITER = " | ";
    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return ICON_EVENT + super.toString() + DATE_PREFIX + at + DATE_POSTFIX;
    }

    @Override
    public String toSave() {
        return LETTER_EVENT + DELIMITER + super.toSave() + DELIMITER + at;
    }
}
