import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class TableOfAddresses {
    public static final Map<String, Long> tableOfAddresses = new HashMap<String, Long>();

    public static void updateMark(String Address, Long newvalue) {
        synchronized (tableOfAddresses) {
            tableOfAddresses.put(Address, newvalue);
        }
    }

    public static void deleteMarks() {
        synchronized (tableOfAddresses) {
            for (String element : tableOfAddresses.keySet()) {
                if (tableOfAddresses.get(element) == -1) {
                    tableOfAddresses.remove(element);
                }
            }
        }
    }

    public static Set<Map.Entry<String, Long>> getSetOfTableElements() {
        return tableOfAddresses.entrySet();
    }

    public static void printTable() {
        for (String key : tableOfAddresses.keySet()) {
            Long value = tableOfAddresses.get(key);
            if(value > 0) {
                System.out.println(key + " alive");
            } else {
                System.out.println(key + " died");
            }
        }
    }
}
