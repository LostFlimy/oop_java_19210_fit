import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class TableOfAddresses {
    //Здесь храним соответствия IPv4address-time of last packet
    public static final Map<String, Long> tableOfAddresses = new HashMap<String, Long>();

    //Добавление/обновление записи в таблице
    public static void updateMark(String Address, Long newvalue) {
        synchronized (tableOfAddresses) {
            tableOfAddresses.put(Address, newvalue);
        }
    }
    //Удаление всех записей из таблицы помеченных -1,
    // Помечаются -1 адреса, которые не отправляли в течение таймаута пакеты, либо отправили пакет с сообщением "end"
    public static void deleteMarks() {
        synchronized (tableOfAddresses) {
            for (String key : tableOfAddresses.keySet()) {
                if (tableOfAddresses.get(key) == -1) {
                    tableOfAddresses.remove(key);
                }
            }
        }
    }

    //геттер таблицы
    public static Set<Map.Entry<String, Long>> getSetOfTableElements() {
        return tableOfAddresses.entrySet();
    }

    //распечатать таблицу в формате : <Address> <"alive"/"died">
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
