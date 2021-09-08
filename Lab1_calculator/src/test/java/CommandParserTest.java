import com.calculator.CommandsParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommandParserTest {
    private final CommandsParser parser = new CommandsParser();

    @Test
    public void parsingTest() {
        parser.split("ADD 5.0 3.5 abc");
        Assertions.assertEquals(parser.getClassName(), "ADD");
        Assertions.assertEquals(parser.getArgs()[0], "5.0");
        Assertions.assertEquals(parser.getArgs()[1], "3.5");
        Assertions.assertEquals(parser.getArgs()[2], "abc");
    }
}
