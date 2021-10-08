package seedu.address.model.studyspot;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.amenity.Amenity;
import seedu.address.model.tag.Tag;

/**
 * Represents a StudySpot in StudyTracker
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class StudySpot {

    // Identity fields
    private final Name name;
    private final Rating rating;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<Amenity> amenities = new HashSet<>();
    private final Set<Amenity> addedAmenities = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public StudySpot(Name name, Rating rating, Email email, Address address, Set<Tag> tags, Set<Amenity> amenities) {
        requireAllNonNull(name, rating, email, address, tags, amenities);
        this.name = name;
        this.rating = rating;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.amenities.addAll(amenities);
    }

    public Name getName() {
        return name;
    }

    public Rating getRating() {
        return rating;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable amenity set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Amenity> getAmenities() {
        return Collections.unmodifiableSet(amenities);
    }

    /**
     * Returns an immutable amenity set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Amenity> getAddedAmenities() {
        return Collections.unmodifiableSet(addedAmenities);
    }

    /**
     * Returns true if both study spots have the same name.
     * This defines a weaker notion of equality between two study spots.
     */
    public boolean isSameStudySpot(StudySpot otherSpot) {
        if (otherSpot == this) {
            return true;
        }

        return otherSpot != null
                && otherSpot.getName().equals(getName());
    }

    /**
     * Returns true if both study spots have the same identity and data fields.
     * This defines a stronger notion of equality between two study spots.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof StudySpot)) {
            return false;
        }

        StudySpot otherStudySpot = (StudySpot) other;
        return otherStudySpot.getName().equals(getName())
                && otherStudySpot.getRating().equals(getRating())
                && otherStudySpot.getEmail().equals(getEmail())
                && otherStudySpot.getAddress().equals(getAddress())
                && otherStudySpot.getTags().equals(getTags())
                && otherStudySpot.getAmenities().equals(getAmenities());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, rating, email, address, tags, amenities);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Rating: ")
                .append(getRating())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        Set<Amenity> amenities = getAmenities();
        if (!amenities.isEmpty()) {
            builder.append("; Amenities: ");
            amenities.forEach(builder::append);
        }

        return builder.toString();
    }

    public boolean isSameName(Name name) {
        return this.name.isSameNameCaseInsensitive(name);
    }

}
