import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AccountingContainer {
    private final Map<Long, Long> messagesAcceptedMap = new HashMap<>();
    private final Map<Long, String> messagesBodyMap = new HashMap<>();
    public static Map<Long, String> messagesSenders = new HashMap<>();
    private Long countOfNeighbours;

    public AccountingContainer(Long countOfNeighbours) {
        this.countOfNeighbours = countOfNeighbours;
    }

    public void createMessage(Long id, String msg, String senderAddress) {
        messagesBodyMap.put(id, msg);
        messagesAcceptedMap.put(id, 0L);
        messagesSenders.put(id, senderAddress);
    }

    synchronized public void acceptMsg(Long id) {
        if (messagesAcceptedMap.containsKey(id)) {
            messagesAcceptedMap.put(id, messagesAcceptedMap.get(id) + 1L);
        }
    }

    public boolean checkMessage(Long id) {
        return messagesAcceptedMap.get(id).equals(countOfNeighbours);
    }

    public Map<Long, String> getMessages() {
        return messagesBodyMap;
    }

    public void deleteMessages() {
        Set<Long> keySet = messagesAcceptedMap.keySet();
        for (Long key : keySet) {
            if(messagesAcceptedMap.get(key).equals(countOfNeighbours)) {
                messagesAcceptedMap.remove(key);
                messagesBodyMap.remove(key);
                messagesSenders.remove(key);
            }
        }
    }

    synchronized public void incCountOfNeiogbors() {
        countOfNeighbours++;
    }
}
