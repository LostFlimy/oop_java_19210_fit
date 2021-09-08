import com.calculator.Context;
import com.calculator.Fabric;
import com.operations.Operation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OperationPopTest {
    private final Fabric factory = new Fabric("/classesForFabric");
    private Operation pop = factory.getNewOperation("POP");
    private final Context context = new Context();

    @Test
    public void operationPopTest() {
        context.setValueToStack(3.0);
        context.setValueToStack(4.0);

        pop.doOperation(context, null);
        Assertions.assertEquals(context.getStackSize(), 1);
        Assertions.assertEquals(context.getValueFromStack(), 3.0);
    }
}
