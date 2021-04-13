import com.Calculator.ArgumentsController;
import com.Calculator.Context;
import com.Calculator.Fabric;
import com.MyExceptions.IncorrectArgumentException;
import com.MyExceptions.MoreThanNeedException;
import com.MyExceptions.NotFoundArgumentsException;
import com.MyExceptions.StackIsEmptyException;
import com.Operations.Operation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ControllerTest {
    private final ArgumentsController controller = new ArgumentsController("/typesCountsForOperations.properties");
    private final Fabric fabric = new Fabric("/classesForFabric");
    private final Context context = new Context();

    @Test
    public void normalArgsTest() {
        //Push 5.0
        //Push 3.0
        context.setValueToStack(5.0);
        context.setValueToStack(3.0);

        //+
        Operation commandAdd = fabric.getNewOperation("+");
        String[] args = null;
        String[] finalArgs = args;
        Assertions.assertDoesNotThrow(() -> {
            controller.doOperationWithControl(commandAdd, finalArgs, context);
        });

        //PUSH 5.0
        Operation commandPush = fabric.getNewOperation("push");
        args = new String[1];
        args[0] = "5.0";
        String[] finalArgs1 = args;
        Assertions.assertDoesNotThrow(() -> {
            controller.doOperationWithControl(commandPush, finalArgs1, context);
        });

        Operation commanddefine = fabric.getNewOperation("define");
        args = new String[2];
        args[0] = "abc";
        args[1] = "5.0";
        String[] finalArgs2 = args;
        Assertions.assertDoesNotThrow(() -> {
            controller.doOperationWithControl(commanddefine, finalArgs2, context);
        });

        args = new String[1];
        args[0] = "abc";
        String[] finalArgs3 = args;
        Assertions.assertDoesNotThrow(() -> {
            controller.doOperationWithControl(commandPush, finalArgs3, context);
        });

        while(context.getStackSize() != 0) {
            context.getValueFromStack();
        }
    }

    @Test
    public void fewArgsTest() {
        String[] args1 = new String[1];
        args1[0] = "abc";
        String[] args2 = null;

        Operation define = fabric.getNewOperation("define");
        Operation push = fabric.getNewOperation("push");

        Assertions.assertThrows(NotFoundArgumentsException.class, () -> {
           controller.doOperationWithControl(define, args1, context);
           controller.doOperationWithControl(push, args2, context);
        });
    }

    @Test
    public void moreThanNeedArgsTest() {
        String[] args1 = new String[3];
        args1[0] = "abc";
        args1[1] = "5.0";
        args1[2] = "a";
        String[] args2 = new String[2];
        args2[0] = "a";
        args2[1] = "5.0";

        Operation define = fabric.getNewOperation("define");
        Operation push = fabric.getNewOperation("push");

        Assertions.assertThrows(MoreThanNeedException.class, () -> {
            controller.doOperationWithControl(define, args1, context);
            controller.doOperationWithControl(push, args2, context);
        });
    }

    @Test
    public void notDefinedArgumentTest() {
        String[] args = new String[1];
        args[0] = "a";
        Operation push = fabric.getNewOperation("push");

        Assertions.assertThrows(IncorrectArgumentException.class, () -> {
            controller.doOperationWithControl(push, args, context);
        });
    }

    @Test
    public void stackIsEmptyTest() {
        String[] args = null;
        Operation add = fabric.getNewOperation("+");

        Assertions.assertThrows(StackIsEmptyException.class, () -> {
           controller.doOperationWithControl(add, args, context);
        });
    }
}
