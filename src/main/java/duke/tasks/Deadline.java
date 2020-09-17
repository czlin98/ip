package duke.tasks;

public class Deadline extends Task {

    public static final String ICON_DEADLINE = "[D]";
    public static final String DATE_PREFIX = " (by: ";
    public static final String DATE_POSTFIX = ")";
    public static final String LETTER_DEADLINE = "D";
    public static final String DELIMITER = " | ";
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return ICON_DEADLINE + super.toString() + DATE_PREFIX + by + DATE_POSTFIX;
    }

    @Override
    public String toSave() {
        return LETTER_DEADLINE + DELIMITER + super.toSave() + DELIMITER + by;
    }
}