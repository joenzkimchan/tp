package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SPOT;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.alias.Alias;
import seedu.address.model.studyspot.NameContainsKeywordsPredicate;
import seedu.address.model.studyspot.StudySpot;
import seedu.address.testutil.StudySpotBuilder;
import seedu.address.testutil.StudySpotUtil;

public class StudyTrackerParserTest {

    private static final List<Alias> ALIAS_LIST = Collections.singletonList(new Alias("ls", "list"));
    private final StudyTrackerParser parser = new StudyTrackerParser();

    @Test
    public void parseCommand_add() throws Exception {
        StudySpot studySpot = new StudySpotBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(StudySpotUtil.getAddCommand(studySpot), ALIAS_LIST);
        assertEquals(new AddCommand(studySpot), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD, ALIAS_LIST) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3", ALIAS_LIST) instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_SPOT.getOneBased(), ALIAS_LIST);
        assertEquals(new DeleteCommand(INDEX_FIRST_SPOT), command);
    }

    //    @Test
    //    public void parseCommand_edit() throws Exception {
    //        StudySpot studySpot = new StudySpotBuilder().withName("Test").build();
    //        EditStudySpotDescriptor descriptor = new EditStudySpotDescriptorBuilder(studySpot).build();
    //        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
    //                + "spot/Test" + " " + StudySpotUtil.getEditStudySpotDescriptorDetails(descriptor));
    //        assertEquals(new EditCommand(new Name("Test"), descriptor), command);
    //        //todo discrepancies in amenities and addedamenities
    //    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD, ALIAS_LIST) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3", ALIAS_LIST) instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")),
                ALIAS_LIST);
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD, ALIAS_LIST) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3", ALIAS_LIST) instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD, ALIAS_LIST) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3", ALIAS_LIST) instanceof ListCommand);
    }

    @Test
    public void parseCommand_aliasInList_parsesToCorrectCommand() throws Exception {
        assertTrue(parser.parseCommand("ls", ALIAS_LIST) instanceof ListCommand);
    }

    @Test
    public void parseCommand_unknownAlias_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand(
                "unknown alias", ALIAS_LIST));
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand("", ALIAS_LIST));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand(
                "unknownCommand", ALIAS_LIST));
    }
}
