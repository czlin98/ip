package duke.tasks;

/**
 * Represents a task. Contains the description and the done status of the task.
 */
public abstract class Task {
    public static final String YES = "Y";
    public static final String NO = "N";
    public static final String BRACKET_OPEN = "[";
    public static final String BRACKET_CLOSE = "] ";
    public static final String TRUE = "1";
    public static final String FALSE = "0";
    public static final String DELIMITER = " | ";
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? YES : NO);
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return BRACKET_OPEN + getStatusIcon() + BRACKET_CLOSE + description;
    }

    public String toSave() {
        return (isDone ? TRUE : FALSE) + DELIMITER + description;
    }
}