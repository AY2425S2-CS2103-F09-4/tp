package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.other.Other;

/**
 * Jackson-friendly version of {@link Other}.
 */
class JsonAdaptedOther {

    private final String other;

    /**
     * Constructs a {@code JsonAdaptedOther} with the given {@code other}.
     */
    @JsonCreator
    public JsonAdaptedOther(String other) {
        this.other = other;
    }

    /**
     * Converts a given {@code Other} into this class for Jackson use.
     */
    public JsonAdaptedOther(Other source) {
        other = source.other;
    }

    @JsonValue
    public String getOther() {
        return other;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Other} object.
     */
    public Other toModelType() throws IllegalValueException {
        return new Other(other);
    }

}
