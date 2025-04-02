package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskStatus;

/**
 * Updates a task with a new status for a specific person.
 */
public class TaskStatusCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Update a task with status (completed, in-progress, yet-to-start) for a particular person.\n"
            + "Example: " + COMMAND_WORD + " 1 2 completed";
    public static final String MESSAGE_MARK_TASK_SUCCESS = "Marked task: %1$s with status: %2$s under %3$s";

    private final Index personIndex;
    private final Index taskIndex;
    private final TaskStatus newStatus;

    /**
     * Constructs a TaskStatusCommand.
     */
    public TaskStatusCommand(Index personIndex, Index taskIndex, TaskStatus newStatus) {
        requireNonNull(personIndex);
        requireNonNull(taskIndex);
        requireNonNull(newStatus);
        this.personIndex = personIndex;
        this.taskIndex = taskIndex;
        this.newStatus = newStatus;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (personIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(personIndex.getZeroBased());
        List<Task> updatedTasks = new java.util.ArrayList<>(personToEdit.getTasks());

        if (taskIndex.getZeroBased() >= updatedTasks.size()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX, taskIndex.getOneBased()));
        }

        Task taskToUpdate = updatedTasks.get(taskIndex.getZeroBased());
        Task updatedTask = taskToUpdate.withStatus(newStatus);
        updatedTasks.set(taskIndex.getZeroBased(), updatedTask);

        // Create a new Person with the updated tasks.
        // Assuming Person has a method addTask is immutable and returns a new Person,
        // here we build a new Person using the existing data but with updated tasks.
        Person updatedPerson = new Person(
                personToEdit.getName(),
                personToEdit.getPhone(),
                personToEdit.getEmail(),
                personToEdit.getTelegram(),
                personToEdit.getPosition(),
                personToEdit.getAddress(),
                personToEdit.getTags(),
                personToEdit.getSkills(),
                personToEdit.getOthers(),
                personToEdit.getTaskStatus(),
                updatedTasks
        );

        model.setPerson(personToEdit, updatedPerson);

        return new CommandResult(String.format(
                MESSAGE_MARK_TASK_SUCCESS,
                updatedTask.getDescription(),
                updatedTask.getStatus(),
                updatedPerson.getName()
        ));
    }
}
