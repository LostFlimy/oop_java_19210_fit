import com.calculator.Fabric;
import com.myexceptions.CommandNotFoundException;
import com.myexceptions.FabricException;
import com.myexceptions.HaveNotCommandException;
import com.operations.Operation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class FabricCreateOperationTest {
    private final Fabric factory = new Fabric("/classesForFabric");

    @Test
    public void fabricCreateOperationTest() {
        Assertions.assertDoesNotThrow(() -> {
            Operation command1 = factory.getNewOperation("+");
            Operation command2 = factory.getNewOperation("-");
            Operation command3 = factory.getNewOperation("define");
            Operation command4 = factory.getNewOperation("print");
        });
    }

    @Test
    public void wrongStreamTest() {
        Assertions.assertThrows(FabricException.class, () -> {
           Fabric fabric = new Fabric("aasdfdfc");
        });
    }

    @Test
    public void wrongCommandNameTest() {
        String str = "PUSH=com.Operations.Pus";
        InputStream stream = new ByteArrayInputStream(str.getBytes());
        Assertions.assertThrows(CommandNotFoundException.class, () -> {
            Fabric fabric = new Fabric(stream);
        });
    }

    @Test
    public void wrongCommandNameToGetOperationTest() {
        Assertions.assertThrows(HaveNotCommandException.class, () -> {
           Operation command = factory.getNewOperation("poush");
        });
    }
}
