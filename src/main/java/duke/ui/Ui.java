package duke.ui;

import duke.data.TaskList;
import duke.exceptions.NullStringException;
import duke.tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

import static duke.common.Messages.MESSAGE_GREET_A;
import static duke.common.Messages.MESSAGE_GREET_B;
import static duke.common.Messages.MESSAGE_COMMAND_LIST;
import static duke.common.Messages.MESSAGE_HELP_COMMAND;
import static duke.common.Messages.MESSAGE_EXIT_COMMAND;
import static duke.common.Messages.MESSAGE_LIST_COMMAND;
import static duke.common.Messages.MESSAGE_TODO_COMMAND;
import static duke.common.Messages.MESSAGE_DEADLINE_COMMAND;
import static duke.common.Messages.MESSAGE_EVENT_COMMAND;
import static duke.common.Messages.MESSAGE_DONE_COMMAND;
import static duke.common.Messages.MESSAGE_DELETE_COMMAND;
import static duke.common.Messages.MESSAGE_FIND_COMMAND;
import static duke.common.Messages.MESSAGE_LIST;
import static duke.common.Messages.MESSAGE_LIST_NO_TASKS;
import static duke.common.Messages.MESSAGE_ADD_TASK;
import static duke.common.Messages.MESSAGE_TASK_COUNT_A;
import static duke.common.Messages.MESSAGE_TASK_COUNT_B;
import static duke.common.Messages.MESSAGE_DONE;
import static duke.common.Messages.MESSAGE_DELETE_TASK;
import static duke.common.Messages.MESSAGE_FILTERED_LIST;
import static duke.common.Messages.MESSAGE_FILTERED_LIST_NO_TASKS;
import static duke.common.Messages.MESSAGE_BYE;
import static duke.common.Messages.MESSAGE_INVALID_COMMAND;
import static duke.common.Messages.MESSAGE_INVALID_TASK_FORMAT_A;
import static duke.common.Messages.MESSAGE_INVALID_TASK_FORMAT_B;
import static duke.common.Messages.MESSAGE_INVALID_DATE_FORMAT;
import static duke.common.Messages.MESSAGE_INVALID_FILE_FORMAT_A;
import static duke.common.Messages.MESSAGE_INVALID_FILE_FORMAT_B;
import static duke.common.Messages.MESSAGE_NULL_DESCRIPTION_A;
import static duke.common.Messages.MESSAGE_NULL_DESCRIPTION_B;
import static duke.common.Messages.MESSAGE_DONE_ERROR;
import static duke.common.Messages.MESSAGE_NULL_INDEX_DONE;
import static duke.common.Messages.MESSAGE_DELETE_ERROR_A;
import static duke.common.Messages.MESSAGE_DELETE_ERROR_B;
import static duke.common.Messages.MESSAGE_NULL_INDEX_DELETE;
import static duke.common.Messages.MESSAGE_NULL_INDEX;
import static duke.common.Messages.MESSAGE_INDEX_OUT_OF_BOUNDS_DONE;
import static duke.common.Messages.MESSAGE_INDEX_OUT_OF_BOUNDS_DELETE;
import static duke.common.Messages.MESSAGE_INDEX_OUT_OF_BOUNDS;
import static duke.common.Messages.MESSAGE_NULL_FILTER_STRING_A;
import static duke.common.Messages.MESSAGE_NULL_FILTER_STRING_B;
import static duke.common.Messages.MESSAGE_FILE_NOT_FOUND;
import static duke.common.Messages.MESSAGE_NEW_FILE_CREATED;

import static java.util.stream.Collectors.toList;

/**
 * UI of the application
 */
public class Ui {

    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_DELETE = "delete";
    public static final String DIVIDER_TOP = "    ____________________________________________________________";
    public static final String DIVIDER_BOTTOM = "    ____________________________________________________________\n";
    public static final String MESSAGE_PREFIX = "     ";
    public static final String TASK_PREFIX = "       ";
    public static final String PERIOD = ".";


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
     * Shows the list of available commands to the user.
     */
    public void showHelpMessage() {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + MESSAGE_COMMAND_LIST);
        System.out.println(MESSAGE_PREFIX + MESSAGE_HELP_COMMAND);
        System.out.println(MESSAGE_PREFIX + MESSAGE_EXIT_COMMAND);
        System.out.println(MESSAGE_PREFIX + MESSAGE_LIST_COMMAND);
        System.out.println(MESSAGE_PREFIX + MESSAGE_TODO_COMMAND);
        System.out.println(MESSAGE_PREFIX + MESSAGE_DEADLINE_COMMAND);
        System.out.println(MESSAGE_PREFIX + MESSAGE_EVENT_COMMAND);
        System.out.println(MESSAGE_PREFIX + MESSAGE_DONE_COMMAND);
        System.out.println(MESSAGE_PREFIX + MESSAGE_DELETE_COMMAND);
        System.out.println(MESSAGE_PREFIX + MESSAGE_FIND_COMMAND);
        System.out.println(DIVIDER_BOTTOM);
    }

    /**
     * Shows the task added message to the user, echos the task added back to the user,
     * and shows the current number of tasks to the user.
     * @param tasks current task list.
     * @param task task that was added.
     */
    public void showAddTaskMessage(TaskList tasks, Task task) {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + MESSAGE_ADD_TASK);
        System.out.println(TASK_PREFIX + task.toString());
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
     * Shows a list of tasks to the user, formatted as an indexed list.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println(DIVIDER_TOP);
        if (tasks.size() == 0) {
            System.out.println(MESSAGE_PREFIX + MESSAGE_LIST_NO_TASKS);
        }
        else {
            System.out.println(MESSAGE_PREFIX + MESSAGE_LIST);
            tasks.getTaskList().stream()
                    .forEach((t) -> System.out.println(MESSAGE_PREFIX + (tasks.indexOf(t) + 1) + PERIOD + t.toString()));
        }
        System.out.println(DIVIDER_BOTTOM);
    }

    /**
     * Shows a list of tasks filtered by the filter string to the user.
     * @param tasks task list.
     * @param filterString filter string.
     * @throws NullStringException if the filter string is empty.
     */
    public void showFilteredTaskList(TaskList tasks, String filterString) throws NullStringException {
        if (filterString == null || filterString.trim().length() == 0) {
            throw new NullStringException();
        }
        System.out.println(DIVIDER_TOP);
        ArrayList<Task> filteredTaskList = (ArrayList<Task>) tasks.getTaskList().stream()
                .filter((s) -> s.getDescription().toLowerCase().contains(filterString.trim().toLowerCase()))
                .collect(toList());
        if (filteredTaskList.size() == 0) {
            System.out.println(MESSAGE_PREFIX + MESSAGE_FILTERED_LIST_NO_TASKS);
        }
        else {
            System.out.println(MESSAGE_PREFIX + MESSAGE_FILTERED_LIST);
            filteredTaskList.stream()
                    .forEach((t) -> System.out.println(MESSAGE_PREFIX + (filteredTaskList.indexOf(t) + 1) + PERIOD + t.toString()));
        }
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
     * Shows an InvalidCommandException message to the user.
     */
    public void showInvalidCommandExceptionMessage() {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + MESSAGE_INVALID_COMMAND);
        System.out.println(DIVIDER_BOTTOM);
    }

    /**
     * Shows an InvalidDateArgsException message to the user.
     * @param commandType command type string.
     */
    public void showInvalidTaskFormatExceptionMessage(String commandType) {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + MESSAGE_INVALID_TASK_FORMAT_A + commandType + MESSAGE_INVALID_TASK_FORMAT_B);
        System.out.println(DIVIDER_BOTTOM);
    }

    /**
     * Shows an InvalidDateInputException message to the user.
     */
    public void showInvalidDateFormatExceptionMessage() {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + MESSAGE_INVALID_DATE_FORMAT);
        System.out.println(DIVIDER_BOTTOM);
    }

    /**
     * Shows an InvalidFileFormatException message to the user.
     */
    public void showInvalidFileFormatExceptionMessage() {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + MESSAGE_INVALID_FILE_FORMAT_A);
        System.out.println(MESSAGE_PREFIX + MESSAGE_INVALID_FILE_FORMAT_B);
        System.out.println(DIVIDER_BOTTOM);
    }

    /**
     * Shows a NullDescriptionException message to the user.
     * @param commandType command type string.
     */
    public void showNullDescriptionExceptionMessage(String commandType) {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + MESSAGE_NULL_DESCRIPTION_A + commandType + MESSAGE_NULL_DESCRIPTION_B);
        System.out.println(DIVIDER_BOTTOM);
    }

    /**
     * Shows a NullIndexException message to the user.
     * @param commandType command type string.
     */
    public void showNullIndexExceptionMessage(String commandType) {
        System.out.println(DIVIDER_TOP);
        switch (commandType) {
        case COMMAND_DONE:
            System.out.println(MESSAGE_PREFIX + MESSAGE_DONE_ERROR);
            System.out.println(MESSAGE_PREFIX + MESSAGE_NULL_INDEX_DONE);
            break;
        case COMMAND_DELETE:
            System.out.println(MESSAGE_PREFIX + MESSAGE_DELETE_ERROR_A);
            System.out.println(MESSAGE_PREFIX + MESSAGE_NULL_INDEX_DELETE);
            break;
        default:
            System.out.println(MESSAGE_NULL_INDEX);
        }
        System.out.println(DIVIDER_BOTTOM);
    }

    public void showIndexOutOfBoundsExceptionMessage(String commandType) {
        System.out.println(DIVIDER_TOP);
        switch (commandType) {
        case COMMAND_DONE:
            System.out.println(MESSAGE_PREFIX + MESSAGE_DONE_ERROR);
            System.out.println(MESSAGE_PREFIX + MESSAGE_INDEX_OUT_OF_BOUNDS_DONE);
            break;
        case COMMAND_DELETE:
            System.out.println(MESSAGE_PREFIX + MESSAGE_DELETE_ERROR_B);
            System.out.println(MESSAGE_PREFIX + MESSAGE_INDEX_OUT_OF_BOUNDS_DELETE);
            break;
        default:
            System.out.println(MESSAGE_INDEX_OUT_OF_BOUNDS);
        }
        System.out.println(DIVIDER_BOTTOM);
    }

    /**
     * Shows a FileNotFoundException message to the user.
     */
    public void showFileNotFoundExceptionMessage() {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + MESSAGE_FILE_NOT_FOUND);
        System.out.println(MESSAGE_PREFIX + MESSAGE_NEW_FILE_CREATED);
    }

    /**
     * Shows a NullStringException message to the user.
     */
    public void showNullStringExceptionMessage() {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + MESSAGE_NULL_FILTER_STRING_A);
        System.out.println(MESSAGE_PREFIX + MESSAGE_NULL_FILTER_STRING_B);
        System.out.println(DIVIDER_BOTTOM);
    }
}
