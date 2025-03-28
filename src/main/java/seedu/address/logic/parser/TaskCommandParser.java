package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INCORRECT_DATE_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DUE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESC;
import static seedu.address.logic.parser.ParserUtil.parseDueDate;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.TaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskStatus;

/**
 * Parses input and creates a new {@code TaskCommand}.
 */
public class TaskCommandParser implements Parser<TaskCommand> {

    @Override
    public TaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TASK_DESC, PREFIX_DUE_DATE);

        Index index;
        LocalDateTime dueDate = null;

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TASK_DESC, PREFIX_DUE_DATE);

        // handles bad index
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskCommand.MESSAGE_USAGE), pe);
        }

        if (argMultimap.getValue(PREFIX_DUE_DATE).isPresent()) {
            // handles parsing date
            try {
                dueDate = parseDueDate(argMultimap);
            } catch (DateTimeParseException e) {
                throw new ParseException(MESSAGE_INCORRECT_DATE_FORMAT);
            }
        }
        String taskDescription = argMultimap.getValue(PREFIX_TASK_DESC).orElse("");

        return new TaskCommand(index, new Task(taskDescription, TaskStatus.YET_TO_START, dueDate));
    }
}

