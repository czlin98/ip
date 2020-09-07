package duke.tasks;

public abstract class Task {
    public static final String BRACKET_OPEN = "[";
    public static final String BRACKET_CLOSE = "] ";
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return BRACKET_OPEN + getStatusIcon() + BRACKET_CLOSE + description;
    }
}