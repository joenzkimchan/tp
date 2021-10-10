package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.commands.UnaliasCommand.MESSAGE_NOT_FOUND;
import static seedu.address.logic.commands.UnaliasCommand.MESSAGE_SUCCESS_UNALIAS;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.ModelStub;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.alias.Alias;

class UnaliasCommandTest {

    @Test
    public void constructor_nullAlias_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new UnaliasCommand(null));
    }

    @Test
    public void execute_validAliasInModel_removeSuccessful() throws Exception {
        Alias pwdAlias = new Alias(VALID_ALIAS_PWD, VALID_ALIAS_COMMAND_LIST);
        ModelStubWithAlias modelStub = new ModelStubWithAlias(pwdAlias);

        // initially contains alias pwd
        assertTrue(modelStub.hasAlias(pwdAlias));
        CommandResult UnaliasCommandResult = new UnaliasCommand(pwdAlias).execute(modelStub);

        // pwd alias has been removed
        assertEquals(String.format(MESSAGE_SUCCESS_UNALIAS, pwdAlias.userAlias),
                UnaliasCommandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(pwdAlias), modelStub.aliasesRemoved);
    }

    @Test
    public void execute_aliasNotInModel_showsNotFoundMessage() throws Exception {
        Alias lsAlias = new Alias(VALID_ALIAS_LS, VALID_ALIAS_COMMAND_LIST);
        Alias pwdAlias = new Alias(VALID_ALIAS_PWD, VALID_ALIAS_COMMAND_LIST);
        ModelStubWithAlias modelStub = new ModelStubWithAlias(lsAlias);

        // initially contains alias 'ls', try to unalias 'pwd'
        assertTrue(modelStub.hasAlias(lsAlias));
        CommandResult UnaliasCommandResult = new UnaliasCommand(pwdAlias).execute(modelStub);

        // 'pwd' is not removed, 'ls' still in model
        assertEquals(String.format(MESSAGE_NOT_FOUND, pwdAlias.userAlias),
                UnaliasCommandResult.getFeedbackToUser());
        assertTrue(modelStub.hasAlias(lsAlias));
    }

    @Test
    public void equals() {
        Alias lsList = new Alias(VALID_ALIAS_LS, VALID_ALIAS_COMMAND_LIST);
        Alias pwdList = new Alias(VALID_ALIAS_PWD, VALID_ALIAS_COMMAND_LIST);
        UnaliasCommand unaliasLsList = new UnaliasCommand(lsList);
        UnaliasCommand unaliasPwdList = new UnaliasCommand(pwdList);

        // same object -> returns true
        assertTrue(unaliasLsList.equals(unaliasLsList));

        // same values -> returns true
        UnaliasCommand unaliasLsExitCopy = new UnaliasCommand(lsList);
        assertTrue(unaliasLsList.equals(unaliasLsExitCopy));

        // different Object type -> returns false
        assertFalse(unaliasLsList.equals(1));

        // null -> returns false
        assertFalse(unaliasLsList.equals(null));

        // different alias -> returns false
        assertFalse(unaliasLsList.equals(unaliasPwdList));
    }

    /**
     * A Model stub that contains a single Alias.
     */
    private class ModelStubWithAlias extends ModelStub {
        private final Alias alias;
        final ArrayList<Alias> aliasesRemoved = new ArrayList<>();

        ModelStubWithAlias(Alias alias) {
            requireNonNull(alias);
            this.alias = alias;
        }

        @Override
        public boolean hasAlias(Alias alias) {
            requireNonNull(alias);
            return this.alias.isSameAlias(alias);
        }

        @Override
        public void removeAlias(Alias alias) {
            requireNonNull(alias);
            aliasesRemoved.add(alias);
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            return new UserPrefs();
        }
    }

}
