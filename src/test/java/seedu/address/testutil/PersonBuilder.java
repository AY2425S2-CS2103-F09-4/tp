package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.other.Other;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Position;
import seedu.address.model.person.Telegram;
import seedu.address.model.skill.Skill;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.model.util.SampleDataUtil;


/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_TELEGRAM = "@amyBee";
    public static final String DEFAULT_POSITION = "Boss";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_TASK_STATUS = "yet to start";

    private Name name;
    private Phone phone;
    private Email email;
    private Telegram telegram;
    private Position position;
    private Address address;
    private Set<Tag> tags;
    private Set<Skill> skills;
    private Set<Other> others;
    private List<Task> tasks;
    private String taskStatus;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        telegram = new Telegram(DEFAULT_TELEGRAM);
        position = new Position(DEFAULT_POSITION);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        skills = new HashSet<>();
        others = new HashSet<>();
        tasks = new ArrayList<>();
        taskStatus = DEFAULT_TASK_STATUS;
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        telegram = personToCopy.getTelegram();
        position = personToCopy.getPosition();
        address = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
        skills = new HashSet<>(personToCopy.getSkills());
        others = new HashSet<>(personToCopy.getOthers());
        tasks = new ArrayList<>(personToCopy.getTasks());
        taskStatus = personToCopy.getTaskStatus();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code tasks} into a {@code List<Task>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTasks(String... tasks) {
        this.tasks = SampleDataUtil.getTask(tasks);
        return this;
    }

    /**
     * Sets the {@code tasks} of the {@code Person} that we are building.
     */
    public PersonBuilder withTasks(Task... tasks) {
        this.tasks = Arrays.asList(tasks);
        return this;
    }

    /**
     * Clears all tasks from the {@code Person} being built.
     */
    public PersonBuilder withoutTasks() {
        this.tasks = new ArrayList<>();
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Telegram} of the {@code Person} that we are building.
     */
    public PersonBuilder withTelegram(String telegram) {
        this.telegram = new Telegram(telegram);
        return this;
    }

    /**
     * Sets the {@code Position} of the {@code Person} that we are building.
     */
    public PersonBuilder withPosition(String position) {
        this.position = new Position(position);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Skill>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withSkills(String ... skills) {
        this.skills = SampleDataUtil.getSkillSet(skills);
        return this;
    }

    /**
     * Parses the {@code others} into a {@code Set<Other>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withOthers(String ... others) {
        this.others = SampleDataUtil.getOtherSet(others);
        return this;
    }

    /**
     * Sets the {@code taskStatus} of the {@code Person} that we are building.
     *
     * @param taskStatus The task status to be assigned to the Person.
     * @return The current instance of {@code PersonBuilder} with the updated task status.
     */
    public PersonBuilder withTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, telegram, position, address, tags, skills, others, taskStatus, tasks);
    }
}
