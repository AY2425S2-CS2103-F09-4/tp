package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

public class ReportCommand extends Command {
    public static final String COMMAND_WORD = "report";
    public static final String MESSAGE_SUCCESS = "Task Status Report Generated!\n"
            + "Total Tasks: %d\n"
            + "Completed: %d\n"
            + "In Progress: %d\n"
            + "Yet to Start: %d";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // Temporary implementation using person count
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        int total = model.getFilteredPersonList().size();
        return new CommandResult(String.format(MESSAGE_SUCCESS, total, 0, 0, 0));
    }
}
