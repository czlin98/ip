package duke;

import duke.exceptions.InvalidCommandException;
import duke.exceptions.NullCommandArgsException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_BYE = "bye";
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
    public static final String SPACE = " ";
    public static final String DELIMITER_DEADLINE = " /by ";
    public static final String DELIMITER_EVENT = " /at ";
    public static final String MESSAGE_NULL_COMMAND_ARGS_A = "☹ OOPS!!! The description of a ";
    public static final String NULL_COMMAND_ARGS_B = " cannot be empty.";
    public static final String MESSAGE_NULL_INDEX_DONE_A = "☹ OOPS!!! The index of the task to be marked as done";
    public static final String MESSAGE_NULL_INDEX_DONE_B = "cannot be empty.";
    public static final String MESSAGE_NULL_INDEX_DELETE_A = "☹ OOPS!!! The index of the task to be deleted cannot be";
    public static final String MESSAGE_NULL_INDEX_DELETE_B = "empty.";
    public static final String MESSAGE_INVALID_COMMAND = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String MESSAGE_DELETE_TASK = "Noted. I've removed this task:";
    public static final String FILE_PATH = "data/duke.txt";
    public static final String LETTER_TODO = "T";
    public static final String LETTER_DEADLINE = "D";
    public static final String LETTER_EVENT = "E";
    public static final String MESSAGE_SOMETHING_WENT_WRONG = "Something went wrong: ";
    private static ArrayList<Task> tasks;
    private static boolean isExit;

    public static void main(String[] args) {
        showWelcomeMessage();
        initProgram();
        while (!isExit) {
            String userInput = getUserInput();
            try {
                executeCommand(userInput);
            }
            catch (InvalidCommandException e) {
                showInvalidCommandExceptionMessage();
            }
        }
    }

    private static void initProgram() {
        tasks = new ArrayList<>();
        isExit = false;
        readTasks();
    }

    private static void readTasks() {
        File file = new File(FILE_PATH);
        try {
            if (file.exists()) {
                readFromFile();
            } else {
                file.createNewFile();
            }
        }
        catch (IOException e) {
            System.out.println(MESSAGE_SOMETHING_WENT_WRONG + e.getMessage());
            isExit = true;
        }
    }

    private static void readFromFile() throws FileNotFoundException {
        File file = new File(FILE_PATH);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String taskLine = scanner.nextLine();
            String[] taskTypeAndParams = taskLine.split(" \\| ");
            String taskType = taskTypeAndParams[0];
            String taskDoneStatus = taskTypeAndParams[1];
            String taskDescription = taskTypeAndParams[2];

            switch (taskType) {
            case LETTER_TODO:
                tasks.add(new Todo(taskDescription));
                break;
            case LETTER_DEADLINE:
                String deadlineDate = taskTypeAndParams[3];
                tasks.add(new Deadline(taskDescription, deadlineDate));
                break;
            case LETTER_EVENT:
                String eventDate = taskTypeAndParams[3];
                tasks.add(new Event(taskDescription, eventDate));
                break;
            default:
                throw new FileNotFoundException();
            }

            if (taskDoneStatus.equals("1")) {
                tasks.get(tasks.size() - 1).markAsDone();
            }
        }
    }

    private static void showWelcomeMessage() {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + MESSAGE_GREET_A);
        System.out.println(MESSAGE_PREFIX + MESSAGE_GREET_B);
        System.out.println(DIVIDER_BOTTOM);
    }

    private static String getUserInput() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    private static void executeCommand(String userInput) throws InvalidCommandException{
        String commandType = getCommandType(userInput);
        String commandArgs = getCommandArgs(userInput);
        try {
            switch (commandType) {
            case COMMAND_TODO:
                addTodo(commandArgs);
                break;
            case COMMAND_DEADLINE:
                addDeadline(commandArgs);
                break;
            case COMMAND_EVENT:
                addEvent(commandArgs);
                break;
            case COMMAND_DONE:
                markTaskAsDone(commandArgs);
                break;
            case COMMAND_LIST:
                showList();
                break;
            case COMMAND_DELETE:
                deleteTask(commandArgs);
                break;
            case COMMAND_BYE:
                saveTasks();
                exitProgram();
                break;
            default:
                throw new InvalidCommandException();
            }
        }
        catch (NullCommandArgsException e) {
            showNullCommandArgsExceptionMessage(commandType);
        }
    }

    private static String getCommandType(String userInput) {
        String commandType;
        if (userInput.contains(SPACE)) {
            String[] commandTypeAndParams = splitCommandWordAndArgs(userInput);
            commandType = commandTypeAndParams[0];
        }
        else {
            commandType = userInput;
        }
        return commandType;
    }

    private static String getCommandArgs(String userInput) {
        String commandArgs;
        if (userInput.contains(SPACE)) {
            String[] commandTypeAndParams = splitCommandWordAndArgs(userInput);
            commandArgs = commandTypeAndParams[1];
        }
        else {
            commandArgs = null;
        }
        return commandArgs;
    }

    private static String[] splitCommandWordAndArgs(String userInput) {
        String[] split = userInput.split(SPACE, 2);
        return split;
    }

    private static void addTodo(String commandArgs) throws NullCommandArgsException {
        if (commandArgs == null) {
            throw new NullCommandArgsException();
        }
        else {
            showAddTaskMessage();
            Todo todoTask = new Todo(commandArgs);
            tasks.add(todoTask);
            echoTask(todoTask);
            showTaskCountMessage();
        }
    }

    private static void addDeadline(String commandArgs) throws NullCommandArgsException {
        if (commandArgs == null) {
            throw new NullCommandArgsException();
        }
        else{
            String[] deadlineDescriptionAndDate = splitDeadlineDescriptionAndDate(commandArgs);
            String deadlineDescription = deadlineDescriptionAndDate[0];
            String deadlineDate = deadlineDescriptionAndDate[1];
            Deadline deadlineTask = new Deadline(deadlineDescription, deadlineDate);
            showAddTaskMessage();
            tasks.add(deadlineTask);
            echoTask(deadlineTask);
            showTaskCountMessage();
        }
    }

    private static void addEvent(String commandArgs) throws NullCommandArgsException {
        if (commandArgs == null) {
            throw new NullCommandArgsException();
        }
        else {
            String[] eventDescriptionAndDate = splitEventDescriptionAndDate(commandArgs);
            String eventDescription = eventDescriptionAndDate[0];
            String eventDate = eventDescriptionAndDate[1];
            Event eventTask = new Event(eventDescription, eventDate);
            showAddTaskMessage();
            tasks.add(eventTask);
            echoTask(eventTask);
            showTaskCountMessage();
        }
    }

    private static void showNullCommandArgsExceptionMessage(String commandType) {
        System.out.println(DIVIDER_TOP);
        switch (commandType) {
        case COMMAND_TODO:
        case COMMAND_DEADLINE:
        case COMMAND_EVENT:
            System.out.println(MESSAGE_PREFIX + MESSAGE_NULL_COMMAND_ARGS_A + commandType + NULL_COMMAND_ARGS_B);
            break;
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

    private static String[] splitDeadlineDescriptionAndDate(String commandArgs) {
        String[] split = commandArgs.split(DELIMITER_DEADLINE, 2);
        return split;
    }

    private static String[] splitEventDescriptionAndDate(String commandArgs) {
        String[] split = commandArgs.split(DELIMITER_EVENT, 2);
        return split;
    }

    private static void showAddTaskMessage() {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + MESSAGE_ADD_TASK);
    }

    private static void echoTask(Task task) {
        System.out.println(TASK_PREFIX + task.toString());
    }

    private static void showTaskCountMessage() {
        System.out.println(MESSAGE_PREFIX + MESSAGE_TASK_COUNT_A + tasks.size() + MESSAGE_TASK_COUNT_B);
        System.out.println(DIVIDER_BOTTOM);
    }

    private static void markTaskAsDone(String commandArgs) throws NullCommandArgsException {
        if (commandArgs == null) {
            throw new NullCommandArgsException();
        }
        else {
            int index = getDoneIndex(commandArgs);
            Task task = tasks.get(index);
            task.markAsDone();
            showDoneMessage(task);
        }
    }

    private static int getDoneIndex(String commandArgs) {
        return Integer.parseInt(commandArgs) - 1;
    }

    private static void showDoneMessage(Task task) {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + MESSAGE_DONE);
        System.out.println(TASK_PREFIX + task.toString());
        System.out.println(DIVIDER_BOTTOM);
    }

    private static void showList() {
        System.out.println(DIVIDER_TOP);
        if (tasks.size() == 0) {
            System.out.println(MESSAGE_PREFIX + MESSAGE_LIST_NO_TASKS);
        }
        else {
            System.out.println(MESSAGE_PREFIX + MESSAGE_LIST);
            for (Task task : tasks) {
                System.out.println(MESSAGE_PREFIX + (tasks.indexOf(task)+1) + PERIOD + task.toString());
            }
        }
        System.out.println(DIVIDER_BOTTOM);
    }

    private static void deleteTask(String commandArgs) throws NullCommandArgsException {
        if (commandArgs == null) {
            throw new NullCommandArgsException();
        }
        else {
            int index = getDoneIndex(commandArgs);
            showDeleteTaskMessage(index);
            tasks.remove(index);
            showTaskCountMessage();
        }
    }

    private static void showDeleteTaskMessage(int index) {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + MESSAGE_DELETE_TASK);
        System.out.println(TASK_PREFIX + tasks.get(index).toString());
    }

    private static void saveTasks() {
        String file = FILE_PATH;
        try {
            writeToFile(file);
        } catch (IOException e) {
            System.out.println(MESSAGE_SOMETHING_WENT_WRONG + e.getMessage());
        }
    }

    private static void writeToFile(String filePath) throws IOException {
        FileWriter filewriter = new FileWriter(filePath);
        for (Task task : tasks) {
            filewriter.write(task.toSave() + System.lineSeparator());
        }
        filewriter.close();
    }

    private static void exitProgram() {
        isExit = true;
        showExitMessage();
    }

    private static void showExitMessage() {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + MESSAGE_BYE);
        System.out.println(DIVIDER_BOTTOM);
    }

    private static void showInvalidCommandExceptionMessage() {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + MESSAGE_INVALID_COMMAND);
        System.out.println(DIVIDER_BOTTOM);
    }
}

