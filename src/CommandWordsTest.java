import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CommandWordsTest {

  public CommandWordsTest() {

  }

  @Test
  public void testGetCommandWorld() {

    CommandWords commandWords = new CommandWords();
    assertEquals(CommandWord.GO, commandWords.getCommandWord("go"));

    assertEquals(CommandWord.UNKNOWN, commandWords.getCommandWord("other"));

  }

  @Test
  public void testIsCommand() {
    CommandWords commandWords = new CommandWords();
    assertEquals(true, commandWords.isCommand("help"));
    assertEquals(false, commandWords.isCommand("other"));

  }

  @Test
  public void testShowAll() {

  }
}
