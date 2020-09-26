package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline. Contains the description, done status, and date of the deadline.
 */
public class Deadline extends Task {

    public static final String ICON_DEADLINE = "[D]";
    public static final String DATE_PREFIX = " (by: ";
    public static final String DATE_POSTFIX = ")";
    public static final String LETTER_DEADLINE = "D";
    public static final String DELIMITER = " | ";
    public static final String OUTPUT_FORMAT = "MMM d yyyy";
    private LocalDate date;

    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return ICON_DEADLINE + super.toString() + DATE_PREFIX + date.format(DateTimeFormatter.ofPattern(OUTPUT_FORMAT)) + DATE_POSTFIX;
    }

    @Override
    public String toSave() {
        return LETTER_DEADLINE + DELIMITER + super.toSave() + DELIMITER + date.format(DateTimeFormatter.ofPattern(OUTPUT_FORMAT));
    }
}