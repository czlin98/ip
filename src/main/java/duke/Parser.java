package duke;

import duke.exceptions.InvalidDateFormatException;
import duke.exceptions.NullDescriptionException;
import duke.exceptions.NullIndexException;

/**
 * Parses user input.
 */
public class Parser {

    public static final String DELIMITER_COMMAND = " ";
    public static final String DELIMITER_DEADLINE = "/by";
    public static final String DELIMITER_EVENT = "/at";

    /**
     * Parses the command type of the user input.
     * @param userInput user input string.
     * @return the command type string.
     */
    public String getCommandType(String userInput) {
        String[] commandTypeAndParams;
        String commandType;
        if (userInput.contains(DELIMITER_COMMAND)) {
            commandTypeAndParams = userInput.split(DELIMITER_COMMAND, 2);
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
        if (userInput.contains(DELIMITER_COMMAND)) {
            commandTypeAndParams = userInput.split(DELIMITER_COMMAND, 2);
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
        if (commandArgs.contains(DELIMITER_DEADLINE)) {
            descriptionAndDate = commandArgs.split(DELIMITER_DEADLINE, 2);
            description = descriptionAndDate[0].trim();
        }
        else if (commandArgs.contains(DELIMITER_EVENT)) {
            descriptionAndDate = commandArgs.split(DELIMITER_EVENT, 2);
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
     * @return the date string of the task.
     * @throws InvalidDateFormatException if command arguments string is not in the valid format.
     */
    public String getDate(String commandArgs) throws InvalidDateFormatException {
        String[] descriptionAndDate;
        String date = null;
        if (!(commandArgs.contains(DELIMITER_DEADLINE) && commandArgs.contains(DELIMITER_EVENT))) {
            throw new InvalidDateFormatException();
        }
        if (commandArgs.contains(DELIMITER_DEADLINE)) {
            descriptionAndDate = commandArgs.split(DELIMITER_DEADLINE, 2);
            date = descriptionAndDate[1].trim();
        } else if (commandArgs.contains(DELIMITER_EVENT)) {
            descriptionAndDate = commandArgs.split(DELIMITER_EVENT, 2);
            date = descriptionAndDate[1].trim();
        }
        return date;
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
