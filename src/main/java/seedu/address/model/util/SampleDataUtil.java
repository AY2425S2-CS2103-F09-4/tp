package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
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
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"),
                    new Phone("87438807"),
                    new Email("alexyeoh@example.com"),
                    new Telegram("@alexY"), new Position("Team Leader"),
                    new Address("Blk 30 Geylang Street 29, #06-40"),
                    getTagSet("friends"),
                    getSkillSet("C Programming"),
                    getOtherSet("boss")),
            new Person(new Name("Bernice Yu"),
                    new Phone("99272758"),
                    new Email("berniceyu@example.com"),
                    new Telegram("@berniceY"),
                    new Position("Backend Developer"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getTagSet("colleagues", "friends"),
                    getSkillSet("java", "python"),
                    getOtherSet("")),
            new Person(new Name("Charlotte Oliveiro"),
                    new Phone("93210283"),
                    new Email("charlotte@example.com"),
                    new Telegram("@charlotte"),
                    new Position("UI Developer"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getTagSet("neighbours"),
                    getSkillSet("json", "css", "html"),
                    getOtherSet("meet soon")),
            new Person(new Name("David Li"),
                    new Phone("91031282"),
                    new Email("lidavid@example.com"),
                    new Telegram("@davidL"),
                    new Position("Sales Person"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    getTagSet("family"),
                    getSkillSet("marketing"),
                    getOtherSet("salesman")),
            new Person(new Name("Irfan Ibrahim"),
                    new Phone("92492021"),
                    new Email("irfan@example.com"),
                    new Telegram("@irfan"),
                    new Position("Backend Developer"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                    getTagSet("classmates"),
                    getSkillSet("java", "c"),
                    getOtherSet("")),
            new Person(new Name("Roy Balakrishnan"),
                    new Phone("92624417"),
                    new Email("royb@example.com"),
                    new Telegram("@roy"),
                    new Position("team member"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    getTagSet("colleagues"),
                    getSkillSet(""),
                    getOtherSet(""))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a skill set containing the list of strings given.
     */
    public static Set<Skill> getSkillSet(String... strings) {
        return Arrays.stream(strings)
                .map(Skill::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Other> getOtherSet(String... strings) {
        return Arrays.stream(strings)
                .map(Other::new)
                .collect(Collectors.toSet());
    }

}
