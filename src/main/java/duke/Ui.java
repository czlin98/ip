package duke;

import duke.tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * UI of the application
 */
public class Ui {

    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_DELETE = "delete";
    public static final String DIVIDER_TOP = "    ____________________________________________________________";
    public static final String DIVIDER_BOTTOM = "    ____________________________________________________________\n";
    public static final String MESSAGE_PREFIX = "     ";
    public static final String TASK_PREFIX = "       ";
    public static final String MESSAGE_GREET_A = "Hello! I'm Duke";
    public static final String MESSAGE_GREET_B = "What can I do for you?";
    public static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";
    public static final String MESSAGE_LIST = "Here are the tasks in your list:";
    public static final String MESSAGE_LIST_NO_TASKS = "There are no tasks in your list yet.";
    public static final String MESSAGE_ADD_TASK = "Got it. I've added this task:";
    public static final String MESSAGE_TASK_COUNT_A = "Now you have ";
    public static final String MESSAGE_TASK_COUNT_B = " tasks in the list.";
    public static final String MESSAGE_DONE = "Nice! I've marked this task as done:";
    public static final String PERIOD = ".";
    public static final String MESSAGE_NULL_COMMAND_ARGS_A = "☹ OOPS!!! The description of a ";
    public static final String NULL_COMMAND_ARGS_B = " cannot be empty.";
    public static final String MESSAGE_NULL_INDEX_DONE_A = "☹ OOPS!!! The index of the task to be marked as done";
    public static final String MESSAGE_NULL_INDEX_DONE_B = "cannot be empty.";
    public static final String MESSAGE_NULL_INDEX_DELETE_A = "☹ OOPS!!! The index of the task to be deleted cannot be";
    public static final String MESSAGE_NULL_INDEX_DELETE_B = "empty.";
    public static final String MESSAGE_INVALID_COMMAND = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String MESSAGE_DELETE_TASK = "Noted. I've removed this task:";
    public static final String MESSAGE_FILE_NOT_FOUND = "No existing file detected";
    public static final String MESSAGE_NEW_FILE_CREATED = "New file created";

    /**
     * Prompts for the command and reads the text entered by the user.
     * @return user input entered by the user
     */
    public String getUserInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    /**
     * Generates and prints the welcome message upon the start of the application.
     */
    public void showWelcomeMessage() {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + MESSAGE_GREET_A);
        System.out.println(MESSAGE_PREFIX + MESSAGE_GREET_B);
        System.out.println(DIVIDER_BOTTOM);
    }

    /**
     * Shows an InvalidCommandException message to the user.
     */
    public void showInvalidCommandExceptionMessage() {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + MESSAGE_INVALID_COMMAND);
        System.out.println(DIVIDER_BOTTOM);
    }

    /**
     * Shows an InvalidDateFormatException message to the user.
     */
    public void showInvalidDateFormatExceptionMessage(String commandType) {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + "☹ OOPS!!! The format for the " + commandType + " is not valid.");
        System.out.println(DIVIDER_BOTTOM);
    }

    /**
     * Shows a NullDescriptionException message to the user.
     */
    public void showNullDescriptionExceptionMessage(String commandType) {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + MESSAGE_NULL_COMMAND_ARGS_A + commandType + NULL_COMMAND_ARGS_B);
        System.out.println(DIVIDER_BOTTOM);
    }

    /**
     * Shows a NullIndexException message to the user.
     */
    public void showNullIndexExceptionMessage(String commandType) {
        System.out.println(DIVIDER_TOP);
        switch (commandType) {
        case COMMAND_DONE:
            System.out.println(MESSAGE_PREFIX + MESSAGE_NULL_INDEX_DONE_A);
            System.out.println(MESSAGE_PREFIX + MESSAGE_NULL_INDEX_DONE_B);
            break;
        case COMMAND_DELETE:
            System.out.println(MESSAGE_PREFIX + MESSAGE_NULL_INDEX_DELETE_A);
            System.out.println(MESSAGE_PREFIX + MESSAGE_NULL_INDEX_DELETE_B);
            break;
        }
        System.out.println(DIVIDER_BOTTOM);
    }

    /**
     * Shows the task added message to the user, echos the task added back to the user,
     * and shows the current number of tasks to the user.
     * @param tasks current task list.
     */
    public void showAddTaskMessage(TaskList tasks) {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + MESSAGE_ADD_TASK);
        System.out.println(TASK_PREFIX + tasks.getTaskList().get(tasks.getTaskList().size()-1).toString());
        System.out.println(MESSAGE_PREFIX + MESSAGE_TASK_COUNT_A + tasks.getTaskList().size() + MESSAGE_TASK_COUNT_B);
        System.out.println(DIVIDER_BOTTOM);
    }

    public void echoTask(TaskList tasks) {
        System.out.println(TASK_PREFIX + tasks.getTaskList().get(tasks.getTaskList().size()-1).toString());
    }

    public void showTaskCountMessage(TaskList tasks) {
        System.out.println(MESSAGE_PREFIX + MESSAGE_TASK_COUNT_A + tasks.getTaskList().size() + MESSAGE_TASK_COUNT_B);
        System.out.println(DIVIDER_BOTTOM);
    }

    /**
     * Shows the task done message to the user and echos the task marked as done back to the user.
     * @param task task marked as done.
     */
    public void showTaskDoneMessage(Task task) {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + MESSAGE_DONE);
        System.out.println(TASK_PREFIX + task.toString());
        System.out.println(DIVIDER_BOTTOM);
    }

    /**
     * Shows a list of tasks to the user, formatted as an indexed list.
     */
    public void showList(TaskList tasks) {
        System.out.println(DIVIDER_TOP);
        if (tasks.getTaskList().size() == 0) {
            System.out.println(MESSAGE_PREFIX + MESSAGE_LIST_NO_TASKS);
        }
        else {
            System.out.println(MESSAGE_PREFIX + MESSAGE_LIST);
            for (Task task : tasks.getTaskList()) {
                System.out.println(MESSAGE_PREFIX + (tasks.getTaskList().indexOf(task)+1)  + PERIOD + task.toString());
            }
        }
        System.out.println(DIVIDER_BOTTOM);
    }
    /**
     * Shows the delete task message to the user, echos the task deleted back to the user,
     * and show the number of tasks left to the user.
     * @param tasks current task list.
     * @param task task to be deleted.
     */
    public void showTaskDeletedMessage(TaskList tasks, Task task) {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + MESSAGE_DELETE_TASK);
        System.out.println(TASK_PREFIX + task.toString());
        System.out.println(MESSAGE_PREFIX + MESSAGE_TASK_COUNT_A + tasks.getTaskList().size() + MESSAGE_TASK_COUNT_B);
        System.out.println(DIVIDER_BOTTOM);
    }

    /**
     * Shows an exit message to the user upon exiting the application.
     */
    public void showExitMessage() {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + MESSAGE_BYE);
        System.out.println(DIVIDER_BOTTOM);
    }

    /**
     * Shows a loading error message to the user if there is an error reading the file.
     */
    public void showLoadingError() {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + MESSAGE_FILE_NOT_FOUND);
        System.out.println(MESSAGE_PREFIX + MESSAGE_NEW_FILE_CREATED);
    }
}
