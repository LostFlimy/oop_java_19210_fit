import com.Calculator.Context;
import com.Calculator.Fabric;
import com.Operations.Operation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OperationDefineTest {

    private final Fabric factory = new Fabric("/classesForFabric");
    private Operation define = factory.getNewOperation("DEFINE");
    private final Context context = new Context();

    @Test
    public void operationDefineTest() {
        String[] args = new String[2];
        args[0] = "abc";
        args[1] = "5.0";
        define.doOperation(context, args);
        Assertions.assertEquals(context.getValueFromMap(args[0]), Double.parseDouble(args[1]));
    }
}
