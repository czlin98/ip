package duke.common;

public class Messages {
    public static final String MESSAGE_LOGO = "         ____        __      \n" +
            "        / __ \\__  __/ /_____ \n" +
            "       / / / / / / / //_/ _ \\\n" +
            "      / /_/ / /_/ / ,< /  __/\n" +
            "     /_____/\\__,_/_/|_|\\___/";
    public static final String MESSAGE_GREET_A = "Hello! I'm Duke";
    public static final String MESSAGE_GREET_B = "What can I do for you?";
    public static final String MESSAGE_COMMAND_LIST = "List of commands:";
    public static final String MESSAGE_HELP_COMMAND = "help - show list of commands";
    public static final String MESSAGE_EXIT_COMMAND = "exit - exit the application";
    public static final String MESSAGE_LIST_COMMAND = "list - show list of tasks";
    public static final String MESSAGE_TODO_COMMAND = "todo [description] - add a todo task";
    public static final String MESSAGE_DEADLINE_COMMAND = "deadline [description] /by [dd/MM/yyyy] - add a deadline task";
    public static final String MESSAGE_EVENT_COMMAND = "event [description] /at [dd/MM/yyyy]- add an event task";
    public static final String MESSAGE_DONE_COMMAND = "done [index] - mark a task as done";
    public static final String MESSAGE_DELETE_COMMAND = "delete [index] - delete a task";
    public static final String MESSAGE_FIND_COMMAND = "find [keyword] - find tasks from the keyword";
    public static final String MESSAGE_LIST = "Here are the tasks in your list:";
    public static final String MESSAGE_LIST_NO_TASKS = "There are no tasks in your list yet.";
    public static final String MESSAGE_ADD_TASK = "Got it. I've added this task:";
    public static final String MESSAGE_TASK_COUNT_A = "Now you have ";
    public static final String MESSAGE_TASK_COUNT_B = " tasks in the list.";
    public static final String MESSAGE_DONE = "Nice! I've marked this task as done:";
    public static final String MESSAGE_DELETE_TASK = "Noted. I've removed this task:";
    public static final String MESSAGE_FILTERED_LIST = "Here are the matching tasks in your list:";
    public static final String MESSAGE_FILTERED_LIST_NO_TASKS = "There are no matching tasks in your list.";
    public static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";
    public static final String MESSAGE_INVALID_COMMAND = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String MESSAGE_INVALID_TASK_FORMAT_A = "OOPS!!! The input format for the ";
    public static final String MESSAGE_INVALID_TASK_FORMAT_B = " is not valid.";
    public static final String MESSAGE_INVALID_DATE_FORMAT = "OOPS!!! The format of the date and time entered is not valid";
    public static final String MESSAGE_INVALID_FILE_FORMAT_A = "OOPS!!! The format of the file to be read is not valid.";
    public static final String MESSAGE_INVALID_FILE_FORMAT_B = "Exiting the application.";
    public static final String MESSAGE_NULL_DESCRIPTION_A = "OOPS!!! The description of a ";
    public static final String MESSAGE_NULL_DESCRIPTION_B = " cannot be empty.";
    public static final String MESSAGE_DONE_ERROR = "OOPS!!! The index of the task to be marked as done";
    public static final String MESSAGE_NULL_INDEX_DONE = "cannot be empty.";
    public static final String MESSAGE_DELETE_ERROR_A = "OOPS!!! The index of the task to be deleted cannot be";
    public static final String MESSAGE_DELETE_ERROR_B = "OOPS!!! The index of the task to be deleted must be";
    public static final String MESSAGE_NULL_INDEX_DELETE = "empty.";
    public static final String MESSAGE_NULL_INDEX = "OOPS!!! The index of the task cannot be empty";
    public static final String MESSAGE_INDEX_OUT_OF_BOUNDS_DONE = "must be within the task list.";
    public static final String MESSAGE_INDEX_OUT_OF_BOUNDS_DELETE = "within the task list.";
    public static final String MESSAGE_INDEX_OUT_OF_BOUNDS = "OOPS!!! The index of the task is not valid.";
    public static final String MESSAGE_NULL_FILTER_STRING_A = "OOPS!!! The keyword you want to search for cannot be";
    public static final String MESSAGE_NULL_FILTER_STRING_B = "empty.";
    public static final String MESSAGE_FILE_NOT_FOUND = "OOPS!!! No existing file detected.";
    public static final String MESSAGE_NEW_FILE_CREATED = "New file created.";
}
