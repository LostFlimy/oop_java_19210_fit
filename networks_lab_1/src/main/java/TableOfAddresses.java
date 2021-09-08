import java.util.HashMap;
import java.util.Map;

public final class TableOfAddresses {
    public static final Map<String, Integer> tableOfAddresses = new HashMap<String, Integer>();

    public void updateMark(String Address) {
            if (tableOfAddresses.containsKey(Address)) {
                tableOfAddresses.put(Address, 1);
            } else {
                tableOfAddresses.put(Address, 0);
            }
    }

    public void deleteMarks() {
        for(String element : tableOfAddresses.keySet()) {
            if(tableOfAddresses.get(element) == -1) {
                tableOfAddresses.remove(element);
            }
        }
    }
}
