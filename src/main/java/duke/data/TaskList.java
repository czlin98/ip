package duke.data;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

    public int indexOf(Task task) {
        return taskList.indexOf(task);
    }

    public int size() {
        return taskList.size();
    }

    /**
     * Adds new todo task to the task list.
     * @param todoDescription description string of the todo task.
     * @return the todo task added.
     */
    public Task addTodo(String todoDescription) {
        Todo todoTask = new Todo(todoDescription);
        taskList.add(todoTask);
        return todoTask;
    }

    /**
     * Adds new deadline task to the task list.
     * @param deadlineDescription description string of the deadline task.
     * @param deadlineDate formatted date object of the deadline task.
     * @return the deadline task added.
     */
    public Task addDeadline(String deadlineDescription, LocalDate deadlineDate, LocalTime deadlineTime) {
        Deadline deadlineTask = new Deadline(deadlineDescription, deadlineDate, deadlineTime);
        taskList.add(deadlineTask);
        return deadlineTask;
    }

    /**
     * Adds new evebt task to the task list.
     * @param eventDescription description string of the event task.
     * @param eventDate formatted date object of the event task.
     * @return the event task added.
     */
    public Task addEvent(String eventDescription, LocalDate eventDate, LocalTime eventTime) {
        Event eventTask = new Event(eventDescription, eventDate, eventTime);
        taskList.add(eventTask);
        return eventTask;
    }

    /**
     * Marks a task as done.
     * @param index index integer of the task in the list.
     * @return the task marked as done.
     */
    public Task markTaskAsDone(int index) throws IndexOutOfBoundsException {
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
