import java.util.Map;
import java.util.Set;

public class Deleter implements  Runnable{
    private final Long timeout = Long.valueOf(3000);

    @Override
    public void run() {
        //работаем, пока нас не прервали
        while(!Thread.interrupted()) {
            //на каждой итерации получаем таблицу записей
            Set<Map.Entry<String, Long>> set = TableOfAddresses.getSetOfTableElements();
            //изначально считаем, что она не была изменена(оптимизация)
            boolean isChanged = false;
            //делаем проверку каждого элемента таблицы и если встретили запись слишком старую, то помечаем, как мертвую
            //таблица начинает считаться измененной и будет подвергаться изменению
            for(Map.Entry<String, Long> x : set) {
                if(x.getValue() + timeout < System.currentTimeMillis()) {
                    isChanged = true;
                    TableOfAddresses.updateMark(x.getKey(), Long.valueOf(-1));
                }
            }
            if(isChanged) {
                //распечатываем таблицу и удаляем мертвые записи
                TableOfAddresses.printTable();
                TableOfAddresses.deleteMarks();
            }
        }
    }
}

