import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CommandTest {
    public CommandTest() {

    }

    @Test
    public void testGetCommandWord() {
        Command command = new Command(CommandWord.GO, null);
        assertEquals(CommandWord.GO, command.getCommandWord());

    }

    @Test
    public void testGetSecondWord() {
        Command commandHelp = new Command(CommandWord.UNKNOWN, "help");

        assertEquals("help", commandHelp.getSecondWord());

        Command commandNull = new Command(CommandWord.UNKNOWN, null);

        assertEquals(null, commandNull.getSecondWord());
    }

    @Test
    public void testIsUnknown() {
        Command commandUnknown = new Command(CommandWord.UNKNOWN, null);

        assertEquals(true, commandUnknown.isUnknown());

        Command command = new Command(CommandWord.GO, null);

        assertEquals(false, command.isUnknown());
    }

    @Test
    public void testHasSecondWord() {
        Command commandHelp = new Command(CommandWord.UNKNOWN, "help");

        assertEquals(true, commandHelp.hasSecondWord());

        Command commandNull = new Command(CommandWord.UNKNOWN, null);

        assertEquals(false, commandNull.hasSecondWord());
    }
}
