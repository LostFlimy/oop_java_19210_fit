import com.Calculator.Context;
import com.Calculator.Fabric;
import com.Operations.Operation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperationAddTest {
    private final Fabric factory = new Fabric("/classesForFabric");
    private Operation add = factory.getNewOperation("+");
    private final Context context = new Context();

    @Test
    void operationAddTest() {
        context.setValueToStack(5.3);
        context.setValueToStack(3.6);
        add.doOperation(context, null);
        assertEquals(context.getStackSize(), 1);
        assertEquals(context.getValueFromStack(), 8.9);
        assertEquals(context.getStackSize(), 0);
    }
}
