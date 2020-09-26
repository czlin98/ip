package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.ArrayList;

/**
 * Task list of the application.
 */
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

    /**
     * Adds new todo task to the task list.
     * @param todoDescription description string of the todo task.
     */
    public void addTodo(String todoDescription) {
        Todo todoTask = new Todo(todoDescription);
        taskList.add(todoTask);
    }

    /**
     * Adds new deadline task to the task list.
     * @param deadlineDescription description string of the deadline task.
     * @param deadlineDate date string of the deadline task.
     */
    public void addDeadline(String deadlineDescription, String deadlineDate) {
        Deadline deadlineTask = new Deadline(deadlineDescription, deadlineDate);
        taskList.add(deadlineTask);
    }

    /**
     * Adds new event task to the task list.
     * @param eventDescription description string of the event task.
     * @param eventDate date string of the event task.
     */
    public void addEvent(String eventDescription, String eventDate) {
        Event eventTask = new Event(eventDescription, eventDate);
        taskList.add(eventTask);
    }

    /**
     * Marks a task as done.
     * @param index index integer of the task in the list.
     * @return the task marked as done.
     */
    public Task markTaskAsDone(int index) {
        Task task = taskList.get(index);
        task.markAsDone();
        return task;
    }

    /**
     * Deletes a task from the task list.
     * @param index index integer of the task to be deleted.
     * @return the task that was deleted.
     */
    public Task deleteTask(int index) {
        Task task = taskList.get(index);
        taskList.remove(task);
        return task;
    }
}
