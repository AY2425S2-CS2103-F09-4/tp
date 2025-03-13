package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
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

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String telegram;
    private final String position;
    private final String address;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final List<JsonAdaptedSkill> skills = new ArrayList<>();
    private final List<JsonAdaptedOther> others = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name,
                             @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email,
                             @JsonProperty("telegram") String telegram,
                             @JsonProperty("position") String position,
                             @JsonProperty("address") String address,
                             @JsonProperty("tags") List<JsonAdaptedTag> tags,
                             @JsonProperty("skills") List<JsonAdaptedSkill> skills,
                             @JsonProperty("others") List<JsonAdaptedOther> others) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.telegram = telegram;
        this.position = position;
        this.address = address;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        if (skills != null) {
            this.skills.addAll(skills);
        }
        if (others != null) {
            this.others.addAll(others);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        telegram = source.getTelegram().value;
        position = source.getPosition().value;
        address = source.getAddress().value;
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        skills.addAll(source.getSkills().stream()
                .map(JsonAdaptedSkill::new)
                .collect(Collectors.toList()));
        others.addAll(source.getOthers().stream()
                .map(JsonAdaptedOther::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            personTags.add(tag.toModelType());
        }

        final List<Skill> personSkills = new ArrayList<>();
        for (JsonAdaptedSkill skill : skills) {
            personSkills.add(skill.toModelType());
        }

        final List<Other> personOthers = new ArrayList<>();
        for (JsonAdaptedOther other : others) {
            personOthers.add(other.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (telegram == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Telegram.class.getSimpleName()));
        }
        if (!Telegram.isValidTelegram(telegram)) {
            throw new IllegalValueException(Telegram.MESSAGE_CONSTRAINTS);
        }
        final Telegram modelTelegram = new Telegram(telegram);

        if (position == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Position.class.getSimpleName()));
        }
        final Position modelPosition = new Position(position);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        final Set<Skill> modelSkills = new HashSet<>(personSkills);
        final Set<Other> modelOthers = new HashSet<>(personOthers);
        return new Person(modelName, modelPhone, modelEmail, modelTelegram, modelPosition, modelAddress, modelTags,
                modelSkills, modelOthers);
    }

}
