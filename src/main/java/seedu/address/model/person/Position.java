package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's position in the team.
 * Guarantees: immutable
 */
public class Position {

    public final String value;

    /**
     * Constructs an {@code Telegram}.
     *
     * @param position A valid telegram handle.
     */
    public Position(String position) {
        requireNonNull(position);
        value = position;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Position)) {
            return false;
        }

        Position otherPosition = (Position) other;
        return value.equals(otherPosition.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
