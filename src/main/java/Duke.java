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

        System.out.println(greet);
        while(true) {
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();
            if (line.equals("bye")) {
                System.out.println(bye);
                break;
            }
            System.out.println(
                    "    ____________________________________________________________\n" +
                    "     " + line + "\n" +
                    "    ____________________________________________________________\n");
        }
    }
}
