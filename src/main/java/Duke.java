import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greet =
                "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n";
        String bye =
                "    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n";
        String divider =
                "    ____________________________________________________________\n";
        String[] items = new String[100];
        Task[] tasks = new Task[100];

        int itemCount = 0;

        System.out.println(greet);

        while (true) {
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();

            if (line.equals("bye")) {
                System.out.println(bye);
                break;
            }

            else if (line.equals("list")) {
                System.out.println(divider + "     Here are the tasks in your list:");
                for (int i = 0; i < itemCount; i++) {
                    System.out.println("     " + (i + 1) + "." + tasks[i].toString());
                }
                System.out.println(divider);
            }

            else if (line.startsWith("done ")) {
                int lineSize = line.length();
                int index = Integer.parseInt(line.substring(5, lineSize)) - 1;
                tasks[index].markAsDone();
                System.out.println(divider + "     Nice! I've marked this task as done:\n" +
                        "       " + tasks[index].toString() + "\n" + divider);
            }

            else {
                int lineSize = line.length();
                System.out.println(divider + "     Got it. I've added this task:");

                if (line.startsWith("todo ")) {
                    for (String item : items) {
                        items[itemCount] = line.substring(5, lineSize);
                        tasks[itemCount] = new Todo(line.substring(5, lineSize));
                    }
                    System.out.println("       " + tasks[itemCount].toString());
                    itemCount++;
                }

                else if (line.startsWith("deadline ")) {
                    int dividerPosition = line.indexOf("/by ");
                    String deadline = line.substring(9, dividerPosition - 1);
                    String by = line.substring(dividerPosition + 4, lineSize);
                    for (String item : items) {
                        items[itemCount] = deadline;
                        tasks[itemCount] = new Deadline(deadline, by);
                    }
                    System.out.println("       " + tasks[itemCount].toString());
                    itemCount++;
                }

                else if (line.startsWith("event ")) {
                    int dividerPosition = line.indexOf("/at ");
                    String event = line.substring(6, dividerPosition - 1);
                    String at = line.substring(dividerPosition + 4, lineSize);
                    for (String item : items) {
                        items[itemCount] = event;
                        tasks[itemCount] = new Event(event, at);
                    }
                    System.out.println("       " + tasks[itemCount].toString());
                    itemCount++;
                }

                else {
                    for (String item : items) {
                        items[itemCount] = line;
                        tasks[itemCount] = new Task(line);
                    }
                    System.out.println("       " + line);
                    itemCount++;
                }

                System.out.println("     Now you have " + itemCount + " tasks in the list.\n" + divider);
            }
        }
    }
}
