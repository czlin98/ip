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
                    System.out.println("     " + (i + 1) + "." + "[" + tasks[i].getStatusIcon() + "]" + " " + items[i]);
                }
                System.out.println(divider);
            }

            else if (line.startsWith("done ")) {
                int lineSize = line.length();
                int index = Integer.parseInt(line.substring(5, lineSize)) - 1;
                tasks[index].markAsDone();
                System.out.println(divider + "     Nice! I've marked this task as done:\n" +
                        "       " + "[" + tasks[index].getStatusIcon() + "]" + " " + items[index] + "\n" + divider);
            }

            else {
                System.out.println(divider + "     added: " + line + "\n" + divider);
                for (String item : items) {
                    items[itemCount] = line;
                    tasks[itemCount] = new Task(line);
                }
                itemCount++;
            }
        }
    }
}
