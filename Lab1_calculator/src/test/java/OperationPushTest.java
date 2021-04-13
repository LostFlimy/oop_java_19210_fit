import com.Calculator.Context;
import com.Calculator.Fabric;
import com.Operations.Operation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OperationPushTest {
    private final Fabric factory = new Fabric("/classesForFabric");
    private Operation push = factory.getNewOperation("PUSH");
    private final Context context = new Context();

    @Test
    public void operationPushTest() {
        String[] args = new String[1];
        args[0] = "5.0";
        push.doOperation(context, args);
        Assertions.assertEquals(context.getStackSize(), 1);
        Assertions.assertEquals(context.getValueFromStack(), 5.0);
        context.setValueToStack(5.0);
        push.doOperation(context, args);
        Assertions.assertEquals(context.getStackSize(), 2);
    }
}
