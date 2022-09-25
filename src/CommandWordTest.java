import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CommandWordTest {

  public CommandWordTest() {

  }

  @Test
  public void testToString() {
    CommandWord commandWordGo = CommandWord.GO;

    assertEquals("go", commandWordGo.toString());

    CommandWord commandWordHelp = CommandWord.HELP;
    assertEquals("help", commandWordHelp.toString());

  }
}
