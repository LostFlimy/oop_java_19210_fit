import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //List rawlist = new ArrayList();
        //List<String> list = new ArrayList<>();

        //rawlist = list;
        //rawlist.add(8);
        //String str = list.get(0);


        //Это не верный вариант, компилятор стирает информацию о generics, но List ближе по иерархии чем Collections по
        // по итогу он пытается привести String к Integer и вылезает ClassCastException

        //SomeType someType = new SomeType();
        //List<String> list = Arrays.asList("value");
        //someType.test(list);

        //это верный вариант
        SomeType<?> someType = new SomeType();
        List<String> list = Arrays.asList("value");
        someType.test(list);



    }
}

