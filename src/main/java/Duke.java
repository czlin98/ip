import java.util.Scanner;

public class Duke {

    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_BYE = "bye";
    public static final String DIVIDER_TOP = "    ____________________________________________________________";
    public static final String DIVIDER_BOTTOM = "    ____________________________________________________________\n";
    public static final String MESSAGE_PREFIX = "     ";
    public static final String TASK_PREFIX = "       ";
    public static final String MESSAGE_GREET_A = "Hello! I'm Duke";
    public static final String MESSAGE_GREET_B = "What can I do for you?";
    public static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";
    public static final String MESSAGE_LIST = "Here are the tasks in your list:";
    public static final String MESSAGE_ADD_TASK = "Got it. I've added this task:";
    public static final String MESSAGE_TASK_COUNT_A = "Now you have ";
    public static final String MESSAGE_TASK_COUNT_B = " tasks in the list.";
    public static final String MESSAGE_DONE = "Nice! I've marked this task as done:";
    public static final String PERIOD = ".";
    public static final String SPACE = " ";
    public static final String REGEX_DEADLINE = " /by ";
    public static final String REGEX_EVENT = " /at ";
    private static Task[] tasks;
    private static int taskCount;
    private static boolean isExit;

    public static void main(String[] args) {
        initProgram();
        showWelcomeMessage();
        while (!isExit) {
            String userInput = getUserInput();
            executeCommand(userInput);
        }
    }

    private static void initProgram() {
        tasks = new Task[100];
        taskCount = 0;
        isExit = false;
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

    private static void executeCommand(String userInput) {
        String commandType = getCommandType(userInput);
        String commandArgs = getCommandArgs(userInput);
        switch (commandType) {
        case COMMAND_TODO:
            taskCount = addTodo(tasks, taskCount, commandType, commandArgs);
            break;
        case COMMAND_DEADLINE:
            taskCount = addDeadline(tasks, taskCount, commandType, commandArgs);
            break;
        case COMMAND_EVENT:
            taskCount = addEvent(tasks, taskCount, commandType, commandArgs);
            break;
        case COMMAND_DONE:
            markAsDone(tasks, commandArgs);
            break;
        case COMMAND_LIST:
            showList(tasks, taskCount);
            break;
        case COMMAND_BYE:
            exitProgram();
            break;
        default:
            showCommandErrorMesssage();
            break;
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

    private static int addTodo(Task[] tasks, int taskCount, String commandType, String commandArgs) {
        if (commandArgs == null) {
            showNullCommandArgsErrorMessage(commandType);
        }
        else {
            showAddTaskMessage();
            String todoTask = commandArgs;
            for (Task task : tasks) {
                tasks[taskCount] = new Todo(todoTask);
            }
            echoTask(tasks, taskCount);
            taskCount++;
            showTaskCountMessage(taskCount);
        }
        return taskCount;
    }

    private static int addDeadline(Task[] tasks, int taskCount, String commandType, String commandArgs) {
        if (commandArgs == null) {
            showNullCommandArgsErrorMessage(commandType);
        }
        else{
            String[] deadlineDescriptionAndDate = splitDeadlineDescriptionAndDate(commandArgs);
            String deadlineDescription = deadlineDescriptionAndDate[0];
            String deadlineDate = deadlineDescriptionAndDate[1];
            showAddTaskMessage();
            for (Task task : tasks) {
                tasks[taskCount] = new Deadline(deadlineDescription, deadlineDate);
            }
            echoTask(tasks, taskCount);
            taskCount++;
            showTaskCountMessage(taskCount);
        }
        return taskCount;
    }

    private static int addEvent(Task[] tasks, int taskCount, String commandType, String commandArgs) {
        if (commandArgs == null) {
            showNullCommandArgsErrorMessage(commandType);
        }
        else {
            String[] eventDescriptionAndDate = splitEventDescriptionAndDate(commandArgs);
            String eventDescription = eventDescriptionAndDate[0];
            String eventDate = eventDescriptionAndDate[1];
            showAddTaskMessage();
            for (Task task : tasks) {
                tasks[taskCount] = new Event(eventDescription, eventDate);
            }
            echoTask(tasks, taskCount);
            taskCount++;
            showTaskCountMessage(taskCount);
        }
        return taskCount;
    }

    private static void showNullCommandArgsErrorMessage(String commandType) {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + "☹ OOPS!!! The description of a " + commandType + " cannot be empty.");
        System.out.println(DIVIDER_BOTTOM);
    }

    private static String[] splitDeadlineDescriptionAndDate(String commandArgs) {
        String[] split = commandArgs.split(REGEX_DEADLINE, 2);
        return split;
    }

    private static String[] splitEventDescriptionAndDate(String commandArgs) {
        String[] split = commandArgs.split(REGEX_EVENT, 2);
        return split;
    }

    private static void showAddTaskMessage() {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + MESSAGE_ADD_TASK);
    }

    private static void echoTask(Task[] tasks, int taskCount) {
        System.out.println(TASK_PREFIX + tasks[taskCount].toString());
    }

    private static void showTaskCountMessage(int taskCount) {
        System.out.println(MESSAGE_PREFIX + MESSAGE_TASK_COUNT_A + taskCount + MESSAGE_TASK_COUNT_B);
        System.out.println(DIVIDER_BOTTOM);
    }

    private static void markAsDone(Task[] tasks, String commandArgs) {
        if (commandArgs == null) {
            showNullDoneIndexErrorMessage();
        }
        else {
            int index = getDoneIndex(commandArgs);
            tasks[index].markAsDone();
            showDoneMessage(tasks, index);
        }
    }

    private static void showNullDoneIndexErrorMessage() {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + "☹ OOPS!!! The index of the task to be mark as done cannot be empty.");
        System.out.println(DIVIDER_BOTTOM);
    }

    private static int getDoneIndex(String commandArgs) {
        return Integer.parseInt(commandArgs) - 1;
    }

    private static void showDoneMessage(Task[] tasks, int index) {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + MESSAGE_DONE);
        System.out.println(TASK_PREFIX + tasks[index].toString());
        System.out.println(DIVIDER_BOTTOM);
    }

    private static void showList(Task[] tasks, int taskCount) {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + MESSAGE_LIST);
        for (int i = 0; i < taskCount; i++) {
            System.out.println(MESSAGE_PREFIX + (i + 1) + PERIOD + tasks[i].toString());
        }
        System.out.println(DIVIDER_BOTTOM);
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

    private static void showCommandErrorMesssage() {
        System.out.println(DIVIDER_TOP);
        System.out.println(MESSAGE_PREFIX + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println(DIVIDER_BOTTOM);
    }
}

