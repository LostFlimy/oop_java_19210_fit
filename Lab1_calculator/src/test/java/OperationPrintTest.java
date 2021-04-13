import com.Calculator.Context;
import com.Calculator.Fabric;
import com.Operations.Operation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OperationPrintTest {
    private final Fabric factory = new Fabric("/classesForFabric");
    private Operation print = factory.getNewOperation("PRINT");
    private final Context context = new Context();

    @Test
    void operationPrintTest() {
        context.setValueToStack(5.3);
        Assertions.assertDoesNotThrow(() -> {
            print.doOperation(context, null);
        });
        Assertions.assertEquals(context.getStackSize(), 1);
    }
}
