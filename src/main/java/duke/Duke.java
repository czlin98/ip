package duke;

import duke.data.TaskList;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidTaskFormatException;
import duke.exceptions.NullDescriptionException;
import duke.exceptions.NullIndexException;
import duke.exceptions.NullStringException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.ui.Ui;

import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {

    public static final String COMMAND_HELP = "help";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_FIND = "find";
    public static final String COMMAND_BYE = "bye";
    public static final String FILE_PATH = "duke.txt";
    private Ui ui;
    private TaskList tasks;
    private Parser parse;
    private Storage storage;

    /**
     * Sets up the required objects, and reads any existing file else creates a new file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        tasks = new TaskList();
        parse = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFromFile());
        } catch (FileNotFoundException e) {
            ui.showFileNotFoundExceptionMessage();
            tasks = new TaskList();
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showInvalidFileFormatExceptionMessage();
            System.exit(0);
        } catch (DateTimeParseException e) {
            ui.showInvalidFileFormatExceptionMessage();
            System.exit(0);
        }
    }

    /**
     * Reads the user command and executes it, until the user issues the exit command.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            String userInput = ui.getUserInput();
            String commandType = parse.getCommandType(userInput);
            String commandArgs = parse.getCommandArgs(userInput);
            try {
                switch (commandType) {
                case COMMAND_HELP:
                    ui.showHelpMessage();
                    break;
                case COMMAND_TODO:
                    Task todoTask = tasks.addTodo(commandArgs);
                    ui.showAddTaskMessage(tasks, todoTask);
                    storage.writeToFile(tasks);
                    break;
                case COMMAND_DEADLINE:
                    Task deadlineTask = tasks.addDeadline(parse.getDeadlineDescription(commandArgs), parse.getDeadlineDate(commandArgs), parse.getDeadlineTime(commandArgs));
                    ui.showAddTaskMessage(tasks, deadlineTask);
                    storage.writeToFile(tasks);
                    break;
                case COMMAND_EVENT:
                    Task eventTask = tasks.addEvent(parse.getEventDescription(commandArgs), parse.getEventDate(commandArgs), parse.getEventTime(commandArgs));
                    ui.showAddTaskMessage(tasks, eventTask);
                    storage.writeToFile(tasks);
                    break;
                case COMMAND_DONE:
                    Task taskToBeMarkedAsDone = tasks.markTaskAsDone(parse.getIndex(commandArgs));
                    taskToBeMarkedAsDone.markAsDone();
                    ui.showTaskDoneMessage(taskToBeMarkedAsDone);
                    storage.writeToFile(tasks);
                    break;
                case COMMAND_DELETE:
                    Task taskToBeDeleted = tasks.deleteTask(parse.getIndex(commandArgs));
                    ui.showTaskDeletedMessage(tasks, taskToBeDeleted);
                    storage.writeToFile(tasks);
                    break;
                case COMMAND_LIST:
                    ui.showTaskList(tasks);
                    break;
                case COMMAND_FIND:
                    ui.showFilteredTaskList(tasks, commandArgs);
                    break;
                case COMMAND_BYE:
                    isExit = true;
                    ui.showExitMessage();
                    storage.writeToFile(tasks);
                    break;
                default:
                    throw new InvalidCommandException();
                }
            } catch (InvalidCommandException e) {
                ui.showInvalidCommandExceptionMessage();
            } catch (InvalidTaskFormatException e) {
                ui.showInvalidTaskFormatExceptionMessage(commandType);
            } catch (NullDescriptionException e) {
                ui.showNullDescriptionExceptionMessage(commandType);
            } catch (NullIndexException e) {
                ui.showNullIndexExceptionMessage(commandType);
            } catch (IndexOutOfBoundsException e) {
                ui.showIndexOutOfBoundsExceptionMessage(commandType);
            } catch (NullStringException e)  {
                ui.showNullStringExceptionMessage();
            } catch (DateTimeParseException e) {
                ui.showInvalidDateFormatExceptionMessage();
            }
        }
    }

    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }
}

