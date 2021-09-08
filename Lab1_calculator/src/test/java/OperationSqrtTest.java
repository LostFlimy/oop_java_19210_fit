import com.calculator.Context;
import com.calculator.Fabric;
import com.operations.Operation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OperationSqrtTest {
    private final Fabric factory = new Fabric("/classesForFabric");
    private Operation sqrt = factory.getNewOperation("SQRT");
    private final Context context = new Context();

    @Test
    void operationSqrtTest() {
        context.setValueToStack(4.0);
        sqrt.doOperation(context, null);
        Assertions.assertEquals(context.getStackSize(), 1);
        Assertions.assertEquals(context.getValueFromStack(), 2.0);
    }

}
