package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_DECK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_FRONTIER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_DECK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_FRONTIER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DECK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_FRONTIER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RATING_DECK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RATING_FRONTIER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CROWDED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_QUIET;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.StudyTracker;
import seedu.address.model.studyspot.StudySpot;

/**
 * A utility class containing a list of {@code StudySpot} objects to be used in tests.
 */
public class TypicalStudySpots {

    public static final StudySpot STARBUCKS = new StudySpotBuilder().withName("Starbucks")
            .withAddress("UTown").withEmail("-")
            .withRating("4")
            .withTags("coffee")
            .withAmenities("wifi")
            .build();
    public static final StudySpot CENTRAL_LIBRARY = new StudySpotBuilder().withName("Central library")
            .withAddress("NUS, Central Library")
            .withEmail("-").withRating("3")
            .withTags("cold", "quiet")
            .withAmenities("wifi").build();
    public static final StudySpot COM1 = new StudySpotBuilder().withName("COM1")
            .withRating("2")
            .withEmail("-")
            .withAddress("NUS Computing")
            .withAmenities("wifi").build();
    public static final StudySpot TOKYO_ROOM = new StudySpotBuilder().withName("Tokyo Room")
            .withRating("3")
            .withEmail("-")
            .withAddress("NUS, Yusof Ishak House Level 3")
            .withTags("cold")
            .withAmenities("wifi").build();
    public static final StudySpot PC_COMMONS = new StudySpotBuilder().withName("PC Commons")
            .withRating("4")
            .withEmail("-")
            .withAddress("Utown")
            .withAmenities("wifi").build();
    public static final StudySpot LT_17 = new StudySpotBuilder().withName("LT17")
            .withRating("5")
            .withEmail("-")
            .withAddress("NUS COM2").build();

    // Manually added
    public static final StudySpot BIZ_PODS = new StudySpotBuilder().withName("Biz Pods")
            .withRating("1")
            .withEmail("-")
            .withAddress("NUS BIZ 1").build();
    public static final StudySpot COMPUTING_LOUNGE = new StudySpotBuilder().withName("Computing Lounge")
            .withRating("1")
            .withEmail("-")
            .withAddress("NUS COM1, Basement 1").build();

    // Manually added - StudySpot's details found in {@code CommandTestUtil}
    public static final StudySpot FRONTIER = new StudySpotBuilder().withName(VALID_NAME_FRONTIER)
            .withRating(VALID_RATING_FRONTIER)
            .withEmail(VALID_EMAIL_FRONTIER)
            .withAddress(VALID_ADDRESS_FRONTIER)
            .withTags(VALID_TAG_CROWDED).build();
    public static final StudySpot DECK = new StudySpotBuilder().withName(VALID_NAME_DECK)
            .withRating(VALID_RATING_DECK)
            .withEmail(VALID_EMAIL_DECK)
            .withAddress(VALID_ADDRESS_DECK)
            .withTags(VALID_TAG_QUIET, VALID_TAG_CROWDED)
            .build();


    private TypicalStudySpots() {} // prevents instantiation

    /**
     * Returns an {@code StudyTracker} with all the typical study spots.
     */
    public static StudyTracker getTypicalStudyTracker() {
        StudyTracker st = new StudyTracker();
        for (StudySpot spot : getTypicalStudySpots()) {
            st.addStudySpot(spot);
        }
        return st;
    }

    public static List<StudySpot> getTypicalStudySpots() {
        return new ArrayList<>(Arrays.asList(STARBUCKS, CENTRAL_LIBRARY, COM1,
                FRONTIER, TOKYO_ROOM, PC_COMMONS, LT_17));
    }
}
