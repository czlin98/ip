package duke.tasks;

/**
 * Represents a todo task. Contains the description and the done status of the todo task.
 */
public class Todo extends Task {

    public static final String ICON_TODO = "[T]";
    public static final String LETTER_TODO = "T";
    public static final String DELIMITER = " | ";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return ICON_TODO + super.toString();
    }

    @Override
    public String toSave() {
        return LETTER_TODO + DELIMITER + super.toSave();
    }
}
