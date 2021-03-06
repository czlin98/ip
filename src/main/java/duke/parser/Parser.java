package duke.parser;

import duke.exceptions.InvalidTaskFormatException;
import duke.exceptions.NullDescriptionException;
import duke.exceptions.NullIndexException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parses user input.
 */
public class Parser {

    public static final String COMMAND_WORD_DELIMITER = " ";
    public static final String DEADLINE_DELIMITER = "/by";
    public static final String EVENT_DELIMITER = "/at";
    public static final String INPUT_DATE_FORMAT = "d/MM/yyyy";
    public static final String INPUT_TIME_FORMAT = "HHmm";

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
     * Parses the description of the deadline task.
     * @param commandArgs command arguments string.
     * @return the description string of the deadline task.
     * @throws NullDescriptionException if command arguments string or description string is omitted.
     * @throws InvalidTaskFormatException if the deadline delimiter "/by" or the date is omitted.
     */
    public String getDeadlineDescription(String commandArgs) throws NullDescriptionException, InvalidTaskFormatException {
        return getDescriptionAndDate(commandArgs, DEADLINE_DELIMITER)[0].trim();
    }

    /**
     * Parses the description of the event task.
     * @param commandArgs command arguments string.
     * @return the description string of the event task.
     * @throws NullDescriptionException if command arguments string or description string is omitted.
     * @throws InvalidTaskFormatException if the event delimiter "/at" or the date is omitted.
     */
    public String getEventDescription(String commandArgs) throws NullDescriptionException, InvalidTaskFormatException {
        return getDescriptionAndDate(commandArgs, EVENT_DELIMITER)[0].trim();
    }

    /**
     * Parses the date of the deadline task.
     * @param commandArgs command arguments string.
     * @return the formatted date object of the task.
     * @throws NullDescriptionException if command arguments string or description string is omitted.
     * @throws InvalidTaskFormatException if the deadline delimiter "/by" or the date is omitted.
     * @throws DateTimeParseException if the date string to be parsed is not in the correct format.
     */
    public LocalDate getDeadlineDate(String commandArgs) throws NullDescriptionException, InvalidTaskFormatException, DateTimeParseException {
        LocalDate formattedDate;
        String[] dateAndTime;
        String date = getDescriptionAndDate(commandArgs, DEADLINE_DELIMITER)[1].trim();
        if (date.contains(" ")) {
            dateAndTime = date.split(" ", 2);
            date = dateAndTime[0];
        }
        formattedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT));
        return formattedDate;
    }

    /**
     * Parses the date of the event task.
     * @param commandArgs command arguments string.
     * @return the formatted date object of the task.
     * @throws NullDescriptionException if command arguments string or description string is omitted.
     * @throws InvalidTaskFormatException if the event delimiter "/at" or the date is omitted.
     * @throws DateTimeParseException if the date string to be parsed is not in the correct format.
     */
    public LocalDate getEventDate(String commandArgs) throws NullDescriptionException, InvalidTaskFormatException, DateTimeParseException {
        LocalDate formattedDate;
        String[] dateAndTime;
        String date = getDescriptionAndDate(commandArgs, EVENT_DELIMITER)[1].trim();
        if (date.contains(" ")) {
            dateAndTime = date.split(" ", 2);
            date = dateAndTime[0];
        }
        formattedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT));
        return formattedDate;
    }

    /**
     * Parses the time of the deadline task.
     * @param commandArgs command arguments string.
     * @return the formatted time as a LocalTime object.
     * @throws NullDescriptionException if command arguments string or description string is omitted.
     * @throws InvalidTaskFormatException if the deadline delimiter "/by" or the date is omitted.
     * @throws DateTimeParseException if the date string or time string to be parsed is not in the correct format.
     */
    public LocalTime getDeadlineTime(String commandArgs) throws NullDescriptionException, InvalidTaskFormatException, DateTimeParseException{
        LocalTime formattedTime = LocalTime.MAX;
        String date = getDescriptionAndDate(commandArgs, DEADLINE_DELIMITER)[1].trim();
        String time = getTime(date);
        if (!isNullAndEmpty(time)) {
            formattedTime = LocalTime.parse(time.trim(), DateTimeFormatter.ofPattern(INPUT_TIME_FORMAT));
        }
        return formattedTime;
    }

    /**
     * Parses the time of the event task.
     * @param commandArgs command arguments string.
     * @return the formatted time as a LocalTime object.
     * @throws NullDescriptionException if command arguments string or description string is omitted.
     * @throws InvalidTaskFormatException if the event delimiter "/at" or the date is omitted.
     * @throws DateTimeParseException if the date string or time string to be parsed is not in the correct format.
     */
    public LocalTime getEventTime(String commandArgs) throws NullDescriptionException, InvalidTaskFormatException, DateTimeParseException{
        LocalTime formattedTime = LocalTime.MAX;
        String date = getDescriptionAndDate(commandArgs, EVENT_DELIMITER)[1].trim();
        String time = getTime(date);
        if (!isNullAndEmpty(time)) {
            formattedTime = LocalTime.parse(time.trim(), DateTimeFormatter.ofPattern(INPUT_TIME_FORMAT));
        }
        return formattedTime;
    }

    /**
     * Parses the description and date of the task.
     * @param commandArgs command arguments string.
     * @param taskDelimiter delimiter separating description and date.
     * @return the string array containing description and date strings.
     * @throws NullDescriptionException if command arguments string or description string is omitted.
     * @throws InvalidTaskFormatException if the task delimiter or the date is omitted.
     */
    private String[] getDescriptionAndDate(String commandArgs, String taskDelimiter) throws NullDescriptionException, InvalidTaskFormatException {
        if (isNullAndEmpty(commandArgs)) {
            throw new NullDescriptionException();
        }
        if (!commandArgs.contains(taskDelimiter)) {
            throw new InvalidTaskFormatException();
        }
        String[] descriptionAndDate = commandArgs.split(taskDelimiter, 2);
        if (isNullAndEmpty(descriptionAndDate[0])) {
            throw new NullDescriptionException();
        }
        if (isNullAndEmpty(descriptionAndDate[1])) {
            throw new InvalidTaskFormatException();
        }
        return descriptionAndDate;
    }

    public String getTime(String date) {
        String[] dateAndTime;
        String time = null;
        if (date.contains(" ")) {
            dateAndTime = date.split(" ", 2);
            time = dateAndTime[1];
        }
        return time;
    }

    /**
     * Parses the index of the task to be marked as done or deleted.
     * @param commandArgs command arguments string.
     * @return the index integer.
     * @throws NullIndexException if command arguments string is empty.
     */
    public int getIndex(String commandArgs) throws NullIndexException {
        if (isNullAndEmpty(commandArgs)) {
            throw new NullIndexException();
        }
        int index = Integer.parseInt(commandArgs) - 1;
        return index;
    }

    /**
     * Checks whether a string is null or empty
     * @param string string to be checked.
     * @return true if a string is null or empty, else false.
     */
    private Boolean isNullAndEmpty(String string) {
        return (string == null || string.trim().isEmpty());
    }
}
