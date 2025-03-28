package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.util.SampleDataUtil;

class SetDueDateCommandTest {

    private Model model;
    private LocalDateTime newDueDate;

    @BeforeEach
    void setUp() {
        // A new model for test date
        model = new ModelManager(SampleDataUtil.getSampleAddressBook(), new UserPrefs());
        newDueDate = LocalDateTime.parse("2026-03-30T14:00");
    }

    @Test
    void execute_validIndexes_success() throws CommandException {
        SetDueDateCommand command = new SetDueDateCommand(newDueDate, INDEX_FIRST_TASK, INDEX_FIRST_PERSON);
        CommandResult result = command.execute(model);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        assertCommandSuccess(command, model, result, expectedModel);
    }

    @Test
    void execute_invalidPersonIndex_throwsCommandException() {
        Index outOfBoundsPersonIndex = Index.fromZeroBased(10);
        SetDueDateCommand command = new SetDueDateCommand(newDueDate, INDEX_FIRST_TASK, outOfBoundsPersonIndex);
        assertThrows(CommandException.class, () -> command.execute(model));
    }

    @Test
    void execute_invalidTaskIndex_throwsCommandException() {
        Index outOfBoundsTaskIndex = Index.fromZeroBased(10);
        SetDueDateCommand command = new SetDueDateCommand(newDueDate, outOfBoundsTaskIndex, INDEX_FIRST_PERSON);
        assertThrows(CommandException.class, () -> command.execute(model));
    }

    @Test
    void equals_test() {
        SetDueDateCommand command1 = new SetDueDateCommand(newDueDate, INDEX_FIRST_TASK, INDEX_FIRST_PERSON);
        SetDueDateCommand command2 = new SetDueDateCommand(newDueDate, INDEX_FIRST_TASK, INDEX_FIRST_PERSON);
        assertTrue(command1.equals(command2));
    }
}
