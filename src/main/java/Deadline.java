public class Deadline extends Task {

    public static final String ICON_DEADLINE = "[D]";
    public static final String DATE_PREFIX = " (by: ";
    public static final String DATE_POSTFIX = ")";
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return ICON_DEADLINE + super.toString() + DATE_PREFIX + by + DATE_POSTFIX;
    }
}