package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INCORRECT_DATE_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.TaskCommand;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskStatus;

public class TaskCommandParserTest {

    private TaskCommandParser parser = new TaskCommandParser();

    @Test
    public void parse_noTaskDescription_success() {
        String userInput = "1 d/2025-12-31 23:59";
        LocalDateTime expectedDate = LocalDateTime.of(2025, 12, 31, 23, 59);
        Task expectedTask = new Task("", TaskStatus.YET_TO_START, expectedDate);
        TaskCommand expectedCommand = new TaskCommand(Index.fromOneBased(1), expectedTask);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_validArgsWithDate_success() {
        String userInput = "1 t/Finish assignment d/2025-12-31 23:59";
        LocalDateTime expectedDate = LocalDateTime.of(2025, 12, 31, 23, 59);
        Task expectedTask = new Task("Finish assignment", TaskStatus.YET_TO_START, expectedDate);
        TaskCommand expectedCommand = new TaskCommand(Index.fromOneBased(1), expectedTask);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_validArgsWithoutDate_success() {
        String userInput = "2 t/Buy groceries";
        Task expectedTask = new Task("Buy groceries", TaskStatus.YET_TO_START, null);
        TaskCommand expectedCommand = new TaskCommand(Index.fromOneBased(2), expectedTask);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingIndex_failure() {
        String userInput = "t/Do homework d/2025-12-31 23:59";
        assertParseFailure(parser, userInput,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidDateFormat_failure() {
        String userInput = "1 t/Invalid date d/31-12-2025 23:59";
        assertParseFailure(parser, userInput, MESSAGE_INCORRECT_DATE_FORMAT);
    }

    @Test
    public void parse_duplicatePrefixes_failure() {
        String userInput = "1 t/Do homework t/Extra d/2025-12-31 23:59";
        assertParseFailure(parser, userInput,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingTaskDescription_failure() {
        String userInput = "1 d/2025-12-31 23:59";
        assertParseFailure(parser, userInput,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndex_failure() {
        String userInput = "zero t/Do homework";
        assertParseFailure(parser, userInput,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskCommand.MESSAGE_USAGE));
    }
}
