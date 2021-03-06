package duke.storage;

import duke.data.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage of the application, handles file reading and writing operations.
 */
public class Storage {

    public static final String LETTER_TODO = "T";
    public static final String LETTER_DEADLINE = "D";
    public static final String LETTER_EVENT = "E";
    public static final String MESSAGE_SOMETHING_WENT_WRONG = "Something went wrong: ";
    public static final String COLUMN = " \\| ";
    public static final String OUTPUT_DATE_FORMAT = "MMM d yyyy";
    public static final String OUTPUT_TIME_FORMAT = "hh:mm a";

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads the contents from an existing file, else creates a new file.
     * @return the ArrayList of tasks.
     * @throws FileNotFoundException if the file to be read does not exist.
     * @throws DateTimeParseException if the date to be read is not in the correct format.
     */
    public ArrayList<Task> readFromFile() throws FileNotFoundException, ArrayIndexOutOfBoundsException, DateTimeParseException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        DateTimeFormatter outputDateFormat = DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT);
        DateTimeFormatter outputTimeFormat = DateTimeFormatter.ofPattern(OUTPUT_TIME_FORMAT);

        if (file.exists()) {
            while (scanner.hasNext()) {
                String taskLine = scanner.nextLine();
                String[] taskTypeAndParams = taskLine.split(COLUMN);
                String taskType = taskTypeAndParams[0];
                String taskDoneStatus = taskTypeAndParams[1];
                String taskDescription = taskTypeAndParams[2];

                switch (taskType) {
                case LETTER_TODO:
                    tasks.add(new Todo(taskDescription));
                    break;
                case LETTER_DEADLINE:
                    String deadlineDate = taskTypeAndParams[3];
                    if (taskTypeAndParams.length == 4) {
                        tasks.add(new Deadline(taskDescription, LocalDate.parse(deadlineDate, outputDateFormat), LocalTime.MAX));
                    }
                    else if (taskTypeAndParams.length == 5){
                        String deadlineTime = taskTypeAndParams[4];
                        tasks.add(new Deadline(taskDescription, LocalDate.parse(deadlineDate, outputDateFormat),
                                LocalTime.parse(deadlineTime, outputTimeFormat)));
                    }
                    break;
                case LETTER_EVENT:
                    String eventDate = taskTypeAndParams[3];
                    if (taskTypeAndParams.length == 4) {
                        tasks.add(new Event(taskDescription, LocalDate.parse(eventDate, outputDateFormat), LocalTime.MAX));
                    }
                    else if (taskTypeAndParams.length == 5) {
                        String eventTime = taskTypeAndParams[4];
                        tasks.add(new Event(taskDescription, LocalDate.parse(eventDate, outputDateFormat),
                                LocalTime.parse(eventTime, outputTimeFormat)));
                    }
                    break;
                default:
                    throw new FileNotFoundException();
                }

                if (taskDoneStatus.equals("1")) {
                    tasks.get(tasks.size() - 1).markAsDone();
                }
            }
        }
        else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tasks;
    }

    /**
     * Writes the task list to the existing file.
     * @param tasks current task list.
     */
    public void writeToFile(TaskList tasks) {
        try {
            FileWriter filewriter = new FileWriter(filePath);
            for (Task task : tasks.getTaskList()) {
                filewriter.write(task.toSave() + System.lineSeparator());
            }
            filewriter.close();
        } catch (IOException e) {
            System.out.println(MESSAGE_SOMETHING_WENT_WRONG + e.getMessage());
        }
    }
}
