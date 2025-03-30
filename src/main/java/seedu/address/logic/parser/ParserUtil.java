package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INCORRECT_DATE_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INCORRECT_TASK_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DUE_DATE;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.other.Other;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Position;
import seedu.address.model.person.Telegram;
import seedu.address.model.skill.Skill;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskStatus;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String telegram} into an {@code Telegram}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code telegram} is invalid.
     */
    public static Telegram parseTelegram(String telegram) throws ParseException {
        requireNonNull(telegram);
        String trimmedTelegram = telegram.trim();
        if (!Telegram.isValidTelegram(trimmedTelegram)) {
            throw new ParseException(Telegram.MESSAGE_CONSTRAINTS);
        }
        return new Telegram(trimmedTelegram);
    }

    /**
     * Parses a {@code String position} into an {@code Position}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Position parsePosition(String position) {
        requireNonNull(position);
        String trimmedPosition = position.trim();
        return new Position(trimmedPosition);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String Skill} into a {@code Skill}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code skill} is invalid.
     */
    public static Skill parseSkill(String skill) throws ParseException {
        requireNonNull(skill);
        String trimmedSkill = skill.trim();
        return new Skill(trimmedSkill);
    }

    /**
     * Parses {@code Collection<String> skills} into a {@code Set<Skill>}.
     */
    public static Set<Skill> parseSkills(Collection<String> skills) throws ParseException {
        requireNonNull(skills);
        final Set<Skill> skillSet = new HashSet<>();
        for (String skillName : skills) {
            skillSet.add(parseSkill(skillName));
        }
        return skillSet;
    }

    /**
     * Parses a {@code String other} into a {@code Other}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Other} is invalid.
     */
    public static Other parseOther(String other) throws ParseException {
        requireNonNull(other);
        String trimmedOther = other.trim();
        return new Other(trimmedOther);
    }

    /**
     * Parses {@code Collection<String> others} into a {@code Set<Other>}.
     */
    public static Set<Other> parseOthers(Collection<String> others) throws ParseException {
        requireNonNull(others);
        final Set<Other> otherSet = new HashSet<>();
        for (String other : others) {
            otherSet.add(parseOther(other));
        }
        return otherSet;
    }

    /**
     * Parses a {@code String task} into a {@code Task}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Task} is invalid.
     */
    public static Task parseTask(String task) throws ParseException {
        requireNonNull(task);

        String[] taskDetails = task.split(",");

        if (taskDetails.length != 3) {
            throw new ParseException("Task must be in format: description, yyyy-MM-dd HH:mm, status");
        }

        String taskDesc = taskDetails[0].trim();
        String dueDateStr = taskDetails[1].trim();
        String statusStr = taskDetails[2].trim();

        if (taskDesc.isEmpty() || dueDateStr.isEmpty() || statusStr.isEmpty()) {
            throw new ParseException("Task fields cannot be empty.");
        }

        try {
            LocalDateTime dueDate = LocalDateTime.parse(dueDateStr, INPUT_FORMATTER);
            TaskStatus taskStatus = TaskStatus.fromString(statusStr); // already trims and validates enum
            return new Task(taskDesc, taskStatus, dueDate);

        } catch (DateTimeParseException e) {
            throw new ParseException(MESSAGE_INCORRECT_DATE_FORMAT);
        } catch (IllegalArgumentException e) {
            throw new ParseException(MESSAGE_INCORRECT_TASK_STATUS);
        }
    }

    /**
     * Parses the due date provided by the user from the given {@code ArgumentMultimap},
     * expecting the value associated with {@code PREFIX_DUE_DATE} to be in String format.
     *
     * @param argMultimap The map containing argument prefixes and their corresponding values.
     * @return The parsed {@code LocalDateTime} due date.
     * @throws ParseException If the due date is not in the expected format.
     */
    public static LocalDateTime parseDueDate(ArgumentMultimap argMultimap) throws ParseException {
        LocalDateTime dueDate;

        // handles parsing date
        try {
            dueDate = LocalDateTime.parse(argMultimap.getValue(PREFIX_DUE_DATE).get(), INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new ParseException(MESSAGE_INCORRECT_DATE_FORMAT);
        }

        return dueDate;
    }
}
