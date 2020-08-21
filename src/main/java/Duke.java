import java.util.Scanner;
import java.util.Arrays;

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
        String[] items = new String[100];
        int itemCount = 0;

        System.out.println(greet);

        while(true) {
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();

            if (line.equals("bye")) {
                System.out.println(bye);
                break;
            }

            else if (line.equals("list")) {
                for (int i = 0; i < itemCount; i++) {
                    if (i == 0) {
                        System.out.println(
                                "    ____________________________________________________________\n" +
                                "     " + (i + 1) + ". " + items[i]
                        );
                    }
                    else if (i == itemCount - 1) {
                        System.out.println(
                                "     " + (i + 1) + ". " + items[i] + "\n" +
                                "    ____________________________________________________________\n"
                        );
                    }
                    else {
                        System.out.println("     " + (i + 1) + ". " + items[i]);
                    }
                }
            }

            else {
                System.out.println(
                        "    ____________________________________________________________\n" +
                        "     added: " + line + "\n" +
                        "    ____________________________________________________________\n"
                );
                for (String item : items) {
                    items[itemCount] = line;
                }
                itemCount++;
            }
        }
    }
}
