package duke;

import duke.exceptions.NullStringException;
import duke.tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

public class Ui {

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
    public static final String MESSAGE_INVALID_DATE_ARGS_A = "☹ OOPS!!! The input format for the ";
    public static final String MESSAGE_INVALID_DATE_ARGS_B = " is not valid.";
    public static final String MESSAGE_INVALID_DATE_INPUT = "☹ OOPS!!! The format of the date entered is not valid";
    public static final String MESSAGE_INVALID_FILE_FORMAT = "☹ OOPS!!! The format of the file to be read is not valid.";
    public static final String MESSAGE_FILTERED_LIST = "Here are the matching tasks in your list:";
    public static final String MESSAGE_NULL_STRING_A = "☹ OOPS!!! The keyword you want to search for cannot be";
    public static final String MESSAGE_NULL_STRING_B = "empty.";

    public String getUserInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public void showWelcomeMessage() {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + MESSAGE_GREET_A);
        System.out.println(MESSAGE_PREFIX + MESSAGE_GREET_B);
        System.out.println(DIVIDER_BOTTOM);
    }

    public void showInvalidCommandExceptionMessage() {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + MESSAGE_INVALID_COMMAND);
        System.out.println(DIVIDER_BOTTOM);
    }

    public void showInvalidDateArgsExceptionMessage(String commandType) {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + MESSAGE_INVALID_DATE_ARGS_A + commandType + MESSAGE_INVALID_DATE_ARGS_B);
        System.out.println(DIVIDER_BOTTOM);
    }

    public void showInvalidDateInputExceptionMessage() {
        System.out.println(MESSAGE_INVALID_DATE_INPUT);
    }

    public void showInvalidFileFormatExceptionMessage() {
        System.out.println(MESSAGE_INVALID_FILE_FORMAT);
    }

    public void showNullDescriptionExceptionMessage(String commandType) {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + MESSAGE_NULL_COMMAND_ARGS_A + commandType + NULL_COMMAND_ARGS_B);
        System.out.println(DIVIDER_BOTTOM);
    }

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

    public void showAddTaskMessage(TaskList tasks, Task task) {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + MESSAGE_ADD_TASK);
        System.out.println(TASK_PREFIX + task.toString());
        System.out.println(MESSAGE_PREFIX + MESSAGE_TASK_COUNT_A + tasks.getTaskList().size() + MESSAGE_TASK_COUNT_B);
        System.out.println(DIVIDER_BOTTOM);
    }

    public void showTaskDoneMessage(Task task) {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + MESSAGE_DONE);
        System.out.println(TASK_PREFIX + task.toString());
        System.out.println(DIVIDER_BOTTOM);
    }

    public void showTaskList(ArrayList<Task> tasks) {
        System.out.println(DIVIDER_TOP);
        if (tasks.size() == 0) {
            System.out.println(MESSAGE_PREFIX + MESSAGE_LIST_NO_TASKS);
        }
        else {
            System.out.println(MESSAGE_PREFIX + MESSAGE_LIST);
            tasks.stream()
                    .forEach((t) -> System.out.println(MESSAGE_PREFIX + (tasks.indexOf(t) + 1) + PERIOD + t.toString()));
        }
        System.out.println(DIVIDER_BOTTOM);
    }

    public void showTaskDeletedMessage(TaskList tasks, Task task) {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + MESSAGE_DELETE_TASK);
        System.out.println(TASK_PREFIX + task.toString());
        System.out.println(MESSAGE_PREFIX + MESSAGE_TASK_COUNT_A + tasks.getTaskList().size() + MESSAGE_TASK_COUNT_B);
        System.out.println(DIVIDER_BOTTOM);
    }

    public void showExitMessage() {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + MESSAGE_BYE);
        System.out.println(DIVIDER_BOTTOM);
    }

    public void showLoadingError() {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + MESSAGE_FILE_NOT_FOUND);
        System.out.println(MESSAGE_PREFIX + MESSAGE_NEW_FILE_CREATED);
    }

    public void showFilteredTaskList(ArrayList<Task> tasks, String filterString) throws NullStringException {
        if (filterString == null || filterString.trim().length() == 0) {
            throw new NullStringException();
        }
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + MESSAGE_FILTERED_LIST);
        ArrayList<Task> filteredTaskList = (ArrayList<Task>) tasks.stream()
                .filter((s) -> s.getDescription().contains(filterString))
                .collect(toList());
        for (Task t : filteredTaskList) {
            System.out.println(MESSAGE_PREFIX + (filteredTaskList.indexOf(t) + 1) + PERIOD + t.toString());
        }
        System.out.println(DIVIDER_BOTTOM);
    }

    public void showNullStringExceptionMessage() {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + MESSAGE_NULL_STRING_A);
        System.out.println(MESSAGE_PREFIX + MESSAGE_NULL_STRING_B);
        System.out.println(DIVIDER_BOTTOM);
    }
}
