import com.Calculator.Context;
import com.MyExceptions.StackIsEmptyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContextTest {
    private final Context context = new Context();

    @Test
    public void createcontextTest() {
        Assertions.assertEquals(context.getStackSize(), 0);
    }

    @Test
    public void stackOperationsTest() {
        Assertions.assertEquals(context.getStackSize(), 0);
        context.setValueToStack(5.0);
        Assertions.assertEquals(context.getStackSize(), 1);
        Assertions.assertEquals(context.getValueFromStack(), 5.0);
        Assertions.assertEquals(context.getStackSize(), 0);
        Assertions.assertThrows(StackIsEmptyException.class, () -> {
           Double x = context.getValueFromStack();
        });
    }

    @Test
    public void mapOperationsTest() {
        context.define("abc", 5.0);
        Assertions.assertEquals(context.getValueFromMap("abc"), 5.0);
        Assertions.assertFalse(context.haveValueFor("ab"));
        Assertions.assertTrue(context.haveValueFor("abc"));
    }
}
