package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INCORRECT_DATE_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DUE_DATE;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SetDueDateCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SetDueDateCommand object
 */
public class SetDueDateCommandParser implements Parser<SetDueDateCommand> {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    public SetDueDateCommand parse(String userInput) throws ParseException {
        // TODO: implements this
        requireNonNull(userInput);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_DUE_DATE);

        Index index;
        LocalDateTime dueDate;

        // handles non-numerical input for index
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetDueDateCommand.MESSAGE_USAGE), pe);
        }

        // handles where PREFIX_DUE_DATE is not present
        if (!argMultimap.getValue(PREFIX_DUE_DATE).isPresent()
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetDueDateCommand.MESSAGE_USAGE));
        }

        // handles parsing date
        try {
            dueDate = LocalDateTime.parse(argMultimap.getValue(PREFIX_DUE_DATE).get(), INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new ParseException(MESSAGE_INCORRECT_DATE_FORMAT);
        }

        return new SetDueDateCommand(dueDate, index);
    }
}
