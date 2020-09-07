package duke.tasks;

public class Todo extends Task {

    public static final String ICON_TODO = "[T]";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return ICON_TODO + super.toString();
    }
}
