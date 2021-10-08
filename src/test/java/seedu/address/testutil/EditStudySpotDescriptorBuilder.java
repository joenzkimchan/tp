package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditStudySpotDescriptor;
import seedu.address.model.amenity.Amenity;
import seedu.address.model.studyspot.Address;
import seedu.address.model.studyspot.Email;
import seedu.address.model.studyspot.Name;
import seedu.address.model.studyspot.Rating;
import seedu.address.model.studyspot.StudySpot;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditStudySpotDescriptor objects.
 */
public class EditStudySpotDescriptorBuilder {

    private EditStudySpotDescriptor descriptor;

    public EditStudySpotDescriptorBuilder() {
        descriptor = new EditStudySpotDescriptor();
    }

    public EditStudySpotDescriptorBuilder(EditStudySpotDescriptor descriptor) {
        this.descriptor = new EditStudySpotDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditStudySpotDescriptor} with fields containing {@code studySpot}'s details
     */
    public EditStudySpotDescriptorBuilder(StudySpot studySpot) {
        descriptor = new EditStudySpotDescriptor();
        descriptor.setName(studySpot.getName());
        descriptor.setRating(studySpot.getRating());
        descriptor.setEmail(studySpot.getEmail());
        descriptor.setAddress(studySpot.getAddress());
        descriptor.setTags(studySpot.getTags());
        descriptor.setAmenities(studySpot.getAmenities());
        descriptor.setAddedAmenities(studySpot.getAddedAmenities());
    }

    /**
     * Sets the {@code Name} of the {@code EditStudySpotDescriptor} that we are building.
     */
    public EditStudySpotDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Rating} of the {@code EditStudySpotDescriptor} that we are building.
     */
    public EditStudySpotDescriptorBuilder withRating(String rating) {
        descriptor.setRating(new Rating(rating));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditStudySpotDescriptor} that we are building.
     */
    public EditStudySpotDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditStudySpotDescriptor} that we are building.
     */
    public EditStudySpotDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditStudySpotDescriptor}
     * that we are building.
     */
    public EditStudySpotDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Parses the {@code amenities} into a {@code Set<Amenity>} and set it to the {@code EditStudySpotDescriptor}
     * that we are building.
     */
    public EditStudySpotDescriptorBuilder withAmenities(String... amenities) {
        Set<Amenity> amenitySet = Stream.of(amenities).map(Amenity::new).collect(Collectors.toSet());
        descriptor.setAmenities(amenitySet);
        return this;
    }

    public EditStudySpotDescriptor build() {
        return descriptor;
    }
}
