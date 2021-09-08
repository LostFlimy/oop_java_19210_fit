import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class SomeType<T> {
    public <E> void test(Collection<E> collection) {
        System.out.println("first method");
        for(E e : collection) {
            System.out.println(e);
        }
    }

    public void test(List<Integer> integerList) {
        System.out.println("second method");
        for(Integer e : integerList) {
            System.out.println(e);
        }
    }
}
