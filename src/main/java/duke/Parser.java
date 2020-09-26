package duke;

import duke.exceptions.InvalidDateArgsException;
import duke.exceptions.NullDescriptionException;
import duke.exceptions.NullIndexException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Parses user input.
 */
public class Parser {

    public static final String COMMAND_WORD_DELIMITER = " ";
    public static final String DEADLINE_DELIMITER = "/by";
    public static final String EVENT_DELIMITER = "/at";

    /**
     * Parses the command type of the user input.
     * @param userInput user input string.
     * @return the command type string.
     */
    public String getCommandType(String userInput) {
        String[] commandTypeAndParams;
        String commandType;
        if (userInput.contains(COMMAND_WORD_DELIMITER)) {
            commandTypeAndParams = userInput.split(COMMAND_WORD_DELIMITER, 2);
            commandType = commandTypeAndParams[0];
        }
        else {
            commandType = userInput;
        }
        return commandType;
    }

    /**
     * Parses the command arguments of the user input.
     * @param userInput user input string.
     * @return the command arguments string.
     */
    public String getCommandArgs(String userInput) {
        String[] commandTypeAndParams;
        String commandArgs;
        if (userInput.contains(COMMAND_WORD_DELIMITER)) {
            commandTypeAndParams = userInput.split(COMMAND_WORD_DELIMITER, 2);
            commandArgs = commandTypeAndParams[1];
        }
        else {
            commandArgs = null;
        }
        return commandArgs;
    }

    /**
     * Parses the description of the task.
     * @param commandArgs command arguments string.
     * @return the description string of the task.
     * @throws NullDescriptionException if command arguments string is empty.
     */
    public String getDescription(String commandArgs) throws NullDescriptionException {
        String[] descriptionAndDate;
        String description;
        if (commandArgs == null || commandArgs.trim().length() == 0) {
            throw new NullDescriptionException();
        }
        if (commandArgs.contains(DEADLINE_DELIMITER)) {
            descriptionAndDate = commandArgs.split(DEADLINE_DELIMITER, 2);
            description = descriptionAndDate[0].trim();
        }
        else if (commandArgs.contains(EVENT_DELIMITER)) {
            descriptionAndDate = commandArgs.split(EVENT_DELIMITER, 2);
            description = descriptionAndDate[1].trim();
        }
        else {
            description = commandArgs;
        }
        return description;
    }

    /**
     * Parses the date of the task.
     * @param commandArgs command arguments string.
     * @return the formatted date object of the task.
     * @throws InvalidDateArgsException if date arguments string is not in the valid format.
     */
    public LocalDate getDate(String commandArgs) throws InvalidDateArgsException {
        String[] descriptionAndDate;
        String date = null;
        if (!(commandArgs.contains(DEADLINE_DELIMITER) || commandArgs.contains(EVENT_DELIMITER))) {
            throw new InvalidDateArgsException();
        }
        if (commandArgs.contains(DEADLINE_DELIMITER)) {
            descriptionAndDate = commandArgs.split(DEADLINE_DELIMITER, 2);
            date = descriptionAndDate[1].trim();
        }
        else if (commandArgs.contains(EVENT_DELIMITER)) {
            descriptionAndDate = commandArgs.split(EVENT_DELIMITER, 2);
            date = descriptionAndDate[1].trim();
        }
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("d/MM/yyyy"));
    }

    /**
     * Parses the index of the task to be marked as done or deleted.
     * @param commandArgs command arguments string.
     * @return the index integer.
     * @throws NullIndexException if command arguments string is empty.
     */
    public int getIndex(String commandArgs) throws NullIndexException {
        if (commandArgs == null || commandArgs.trim().length() == 0) {
            throw new NullIndexException();
        }
        int index = Integer.parseInt(commandArgs) - 1;
        return index;
    }
}
