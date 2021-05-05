package factory.store;

import java.util.Stack;

public class Store<T> {
    private Stack<T> listOfDetails;
    private int maxCount;

    public Store(int maximum) {
        listOfDetails = new Stack<T>();
        maxCount = maximum;
    }

    synchronized public void setDetail(T newDetail){
        if(listOfDetails.size() == maxCount) {
            try {
                wait(); // Ждем освобождения места на складе
            } catch(InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        listOfDetails.add(newDetail);
        notifyAll(); // Появилась деталь
    }

    synchronized public T getDetail() {
        if(listOfDetails.empty()) {
            try {
                wait(); // Ждем если нету деталей
            } catch(InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        T result = listOfDetails.pop();
        notifyAll(); // Место освободилось
        return result;
    }
}
