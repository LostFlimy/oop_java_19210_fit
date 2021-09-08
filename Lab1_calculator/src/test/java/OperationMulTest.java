import com.calculator.Context;
import com.calculator.Fabric;
import com.operations.Operation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperationMulTest {
    private final Fabric factory = new Fabric("/classesForFabric");
    private Operation mul = factory.getNewOperation("*");
    private final Context context = new Context();

    @Test
    void operationAddTest() {
        context.setValueToStack(5.3);
        context.setValueToStack(3.6);
        mul.doOperation(context, null);
        assertEquals(context.getStackSize(), 1);
        assertEquals(context.getValueFromStack(), (5.3 * 3.6) );
        assertEquals(context.getStackSize(), 0);
    }
}
