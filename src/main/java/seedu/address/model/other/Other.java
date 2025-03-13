package seedu.address.model.other;

import static java.util.Objects.requireNonNull;

/**
 * Represents an Other in TeamScape.
 */
public class Other {
    public final String other;

    /**
     * Constructs a {@code Other}.
     *
     * @param other A valid other.
     */
    public Other(String other) {
        requireNonNull(other);
        this.other = other;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Other)) {
            return false;
        }

        Other otherOther = (Other) other;
        return this.other.equals(otherOther.other);
    }

    @Override
    public int hashCode() {
        return other.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + other + ']';
    }
}
