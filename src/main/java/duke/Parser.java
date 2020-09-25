package duke;

import duke.exceptions.InvalidDateArgsException;
import duke.exceptions.NullDescriptionException;
import duke.exceptions.NullIndexException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static final String COMMAND_WORD_DELIMITER = " ";
    public static final String DEADLINE_DELIMITER = "/by";
    public static final String EVENT_DELIMITER = "/at";

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

    public int getIndex(String commandArgs) throws NullIndexException {
        if (commandArgs == null || commandArgs.trim().length() == 0) {
            throw new NullIndexException();
        }
        int index = Integer.parseInt(commandArgs) - 1;
        return index;
    }
}
