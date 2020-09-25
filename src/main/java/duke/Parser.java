package duke;

import duke.exceptions.InvalidDateFormatException;
import duke.exceptions.NullDescriptionException;
import duke.exceptions.NullIndexException;

public class Parser {

    public static final String DELIMITER_COMMAND = " ";
    public static final String DELIMITER_DEADLINE = "/by";
    public static final String DELIMITER_EVENT = "/at";

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

    public int getIndex(String commandArgs) throws NullIndexException {
        if (commandArgs == null || commandArgs.trim().length() == 0) {
            throw new NullIndexException();
        }
        int index = Integer.parseInt(commandArgs) - 1;
        return index;
    }
}
