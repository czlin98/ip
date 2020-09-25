package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.ArrayList;

public class TaskList {

    public ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void addTodo(String todoDescription) {
        Todo todoTask = new Todo(todoDescription);
        taskList.add(todoTask);
    }

    public void addDeadline(String deadlineDescription, String deadlineDate) {
        Deadline deadlineTask = new Deadline(deadlineDescription, deadlineDate);
        taskList.add(deadlineTask);
    }

    public void addEvent(String eventDescription, String eventDate) {
        Event eventTask = new Event(eventDescription, eventDate);
        taskList.add(eventTask);
    }

    public Task markTaskAsDone(int index) {
        Task task = taskList.get(index);
        task.markAsDone();
        return task;
    }

    public Task deleteTask(int index) {
        Task task = taskList.get(index);
        taskList.remove(task);
        return task;
    }
}
