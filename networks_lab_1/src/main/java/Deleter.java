import java.util.Map;
import java.util.Set;

public class Deleter implements  Runnable{
    private final Long timeout = Long.valueOf(3000);
    @Override
    public void run() {
        while(!Thread.interrupted()) {
            Set<Map.Entry<String, Long>> set = TableOfAddresses.getSetOfTableElements();
            boolean isChanged = false;
            for(Map.Entry<String, Long> x : set) {
                if(x.getValue() + timeout < System.currentTimeMillis()) {
                    isChanged = true;
                    TableOfAddresses.updateMark(x.getKey(), Long.valueOf(-1));
                }
            }
            if(isChanged) {
                TableOfAddresses.printTable();
                TableOfAddresses.deleteMarks();
            }
        }
    }
}
