import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int taskCount = 0;

        showWelcomeMessage();
        while (true) {
            String userCommand = getUserInput();
            if (userCommand.equals("bye")) {
                showExitMessage();
                break;
            }
            else if (userCommand.equals("list")) {
                showList(tasks, taskCount);
            }
            else if (userCommand.startsWith("done")) {
                setDone(tasks, userCommand);
            }
            else {
                int lineSize = userCommand.length();
                showAddTaskMessage();
                if (userCommand.startsWith("todo")) {
                    taskCount = addTodo(tasks, taskCount, userCommand, lineSize);
                }
                else if (userCommand.startsWith("deadline")) {
                    taskCount = addDeadline(tasks, taskCount, userCommand, lineSize);
                }
                else if (userCommand.startsWith("event")) {
                    taskCount = addEvent(tasks, taskCount, userCommand, lineSize);
                }
                else {
                    taskCount = addTask(tasks, taskCount, userCommand);
                }
                showTaskCountMessage(taskCount);
            }
        }
    }

    private static String getUserInput() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    private static int addTask(Task[] tasks, int taskCount, String userCommand) {
        for (Task task : tasks) {
            tasks[taskCount] = new Task(userCommand);
        }
        echoTask(tasks, taskCount);
        taskCount++;
        return taskCount;
    }

    private static int addTodo(Task[] tasks, int taskCount, String userCommand, int lineSize) {
        String todoTask = getTask(userCommand);
        for (Task task : tasks) {

            tasks[taskCount] = new Todo(todoTask);
        }
        echoTask(tasks, taskCount);
        taskCount++;
        return taskCount;
    }

    private static int addDeadline(Task[] tasks, int taskCount, String userCommand, int lineSize) {
        String deadlineTask = getTask(userCommand);
        String deadlineBy = getDate(userCommand);
        for (Task task : tasks) {
            tasks[taskCount] = new Deadline(deadlineTask, deadlineBy);
        }
        echoTask(tasks, taskCount);
        taskCount++;
        return taskCount;
    }

    private static int addEvent(Task[] tasks, int taskCount, String userCommand, int lineSize) {
        String eventTask = getTask(userCommand);
        String eventAt = getDate(userCommand);
        for (Task task : tasks) {
            tasks[taskCount] = new Event(eventTask, eventAt);
        }
        echoTask(tasks, taskCount);
        taskCount++;
        return taskCount;
    }

    private static void setDone(Task[] tasks, String userCommand) {
        int index = getDoneIndex(userCommand);
        tasks[index].markAsDone();
        showDoneMessage(tasks, index);
    }

    private static String getTask(String userCommand) {
        if (userCommand.contains("/")) {
            return userCommand.substring(userCommand.indexOf(" ") + 1, userCommand.indexOf("/") - 1);
        }
        else {
            return userCommand.substring(userCommand.indexOf(" ") + 1);
        }
    }

    private static String getDate(String userCommand) {
        return userCommand.substring(userCommand.indexOf("/") + 4);
    }

    private static int getDoneIndex(String userCommand) {
        return Integer.parseInt(userCommand.substring(userCommand.indexOf(" ") + 1)) - 1;
    }

    private static void showWelcomeMessage() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________\n");
    }

    private static void showExitMessage() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________\n");
    }

    private static void showList(Task[] tasks, int taskCount) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println("     " + (i + 1) + "." + tasks[i].toString());
        }
        System.out.println("    ____________________________________________________________\n");
    }

    private static void showAddTaskMessage() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task:");
    }

    private static void showTaskCountMessage(int taskCount) {
        System.out.println("     Now you have " + taskCount + " tasks in the list.");
        System.out.println("    ____________________________________________________________\n");
    }

    private static void showDoneMessage(Task[] tasks, int index) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + tasks[index].toString());
        System.out.println("    ____________________________________________________________\n");
    }

    private static void echoTask(Task[] tasks, int taskCount) {
        System.out.println("       " + tasks[taskCount].toString());
    }
}
