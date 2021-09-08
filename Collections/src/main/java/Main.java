import javax.xml.crypto.Data;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //LinkedHashSet сохраняет порядок вставки
        //HashSet не сохраняет никакого порядка
        //TreeSet хранит элементы в порядке сортировки(В конструктор можно передавать Comparator<>)
        NavigableSet<Integer> set = new TreeSet<>();
        for(int i = 1; i <= 10; ++i) {
            set.add(i);
        }
        System.out.println("current set is : " + set);
        System.out.println("set.higher(5) : " + set.higher(5));
        System.out.println("set.lower(5) : " + set.lower(5));
        System.out.println("set.headSet(5) : " + set.headSet(5));
        System.out.println("set.tailSet(5) : " + set.tailSet(5));
        System.out.println("set.descendingSet() : " + set.descendingSet());

        System.out.println("");
        System.out.println("");
        System.out.println("");

        //Лучше в конструкторе задавать initialcapacity, расширение мапы очень затратная операция
        //float loadfactor - параметр конструктора, который задает степень загруженности map он от 0 до 1 (по умолчанию 0.75)
        Map<Integer, String> m = new HashMap<>();
        m.put(5, "a");
        m.put(4, "b");
        m.put(3, "c");
        m.put(2, "d");
        m.put(1, "e");
        System.out.println(m);
        //для LinkedHashMap есть еще один параметр конструктора boolean accessOrded, если он true, то порядок будет соответствовать
        //порядку доступа, то есть те к которым обращаемся чаще, тем ближе к концу он будет(на него влияют get и put)(В HashSet нету)
        m = new LinkedHashMap<>(5, 1, true);
        m.put(5, "a");
        m.put(4, "b");
        m.put(3, "c");
        m.put(2, "d");
        m.put(1, "e");
        System.out.println("map before operations get for 3, 5, 1 keys : " + m);
        m.get(3);
        m.get(5);
        m.get(1);
        System.out.println("after : " + m);

        System.out.println("");
        System.out.println("");
        System.out.println("");

        //Сделаем простенький кэш
        SimpleLRUCache<Integer, String> cache = new SimpleLRUCache<>(2);
        cache.put(1, "a");
        cache.put(2, "b");
        System.out.println("Initial cache : " + cache);
        cache.put(3, "c");
        System.out.println("Cache after put(3, 'c') : " + cache);
        cache.get(2);
        System.out.println("Cache after get(2) : " + cache);
        cache.put(9, "d");
        System.out.println("Cache after put(9, 'd') : " + cache);

        System.out.println("");
        System.out.println("");
        System.out.println("");

        //EnumMap распологает Enum ключами, причем заранее создавая все необходимые экземпляры ключей и новых созданий Объектов
        //при добавлении уже не происходит, что очень эффективно по памяти

        //WeakHashMap удаляет элементы, если ключи больше не используются

        Map<Object, String> map = new WeakHashMap<Object, String>();

        Object data = new Object();
        map.put(data, "info");
        data = null;
        System.gc();
        for(int i = 0; i < 10000; ++i) {
            if(map.isEmpty()) {
                System.out.println("Empty! on " + i + " iter");
                break;
            }
        }

        System.out.println();
        System.out.println();
        System.out.println();

        //Очереди

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 5; i > 0; --i) {
            queue.offer(i);
        }
        System.out.print("LinkedList : ");
        while(!queue.isEmpty()) {
            System.out.print(" " + queue.poll());
        }
        queue = new PriorityQueue<Integer>();
        for(int i = 5; i > 0; --i) {
            queue.offer(i);
        }
        System.out.println();
        System.out.print("PriorityQueue : ");
        while(!queue.isEmpty()) {
            System.out.print(" " + queue.poll());
        }

        System.out.println();

        queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1 % 2 < o2 % 2)
                    return -1;
                if(o2 % 2 > o2 % 2)
                    return 1;
                if(o1 < o2)
                    return -1;
                if(o1.equals(o2))
                    return 0;
                return 1;
            }
        });
        queue.offer(5);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        System.out.println("Example queue : " + queue);
    }
}
