package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.TagsInKeywordsPredicate;
import seedu.address.model.person.TasksInKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE,
                    PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_TASK);

        System.out.println(argMultimap.getSize());
        System.out.println(argMultimap.getAllValues(PREFIX_TASK));
        if (argMultimap.getSize() > 2) {
            throw new ParseException("Only 1 type of prefix allowed at once!");
        }

        if (argMultimap.getAllValues(PREFIX_NAME).size() > 1
            || argMultimap.getAllValues(PREFIX_TAG).size() > 1
            || argMultimap.getAllValues(PREFIX_TASK).size() > 1) {
            throw new ParseException("Only one instance of each prefix is allowed!");
        }

        if (argMultimap.getAllValues(PREFIX_NAME).size() == 0
            || argMultimap.getAllValues(PREFIX_TAG).size() == 0
            || argMultimap.getAllValues(PREFIX_TASK).size() == 0) {
            throw new ParseException("Keywords field should not be blank!");
        }

        if (arePrefixesPresent(argMultimap, PREFIX_NAME)) {
            argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);
            Name placeHolderName = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get().trim());
            String names = placeHolderName.fullName;
            String[] nameKeywords = names.trim().split("\\s+");
            List<String> arr = Arrays.asList(nameKeywords);
            return new FindCommand(new NameContainsKeywordsPredicate(arr));
        }

        if (arePrefixesPresent(argMultimap, PREFIX_TAG)) {
            argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);
            Optional<String> tagsToFindOptional = argMultimap.getValue(PREFIX_TAG);
            if (!tagsToFindOptional.isPresent()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            assert tagsToFindOptional.isPresent() : "Should be able to get tags";
            String tagsToFind = tagsToFindOptional.get().trim();
            String[] tagKeywords = tagsToFind.trim().split("\\s+");
            List<String> arr = Arrays.asList(tagKeywords);
            return new FindCommand(new TagsInKeywordsPredicate(arr));
        }

        if (arePrefixesPresent(argMultimap, PREFIX_TASK)) {
            argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);
            Optional<String> tasksToFindOptional = argMultimap.getValue(PREFIX_TASK);
            System.out.println("HERE");
            System.out.println(tasksToFindOptional);
            if (!tasksToFindOptional.isPresent()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            };
            assert tasksToFindOptional.isPresent() : "Should be able to get tasks";
            String tasksToFind = tasksToFindOptional.get().trim();
            System.out.println(tasksToFind);
            String[] taskKeywords = tasksToFind.trim().split("\\s+");
            List<String> arr = Arrays.asList(taskKeywords);
            return new FindCommand(new TasksInKeywordsPredicate(arr));
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
