package factory.store;

import factory.Factory;

import java.util.Stack;

public class Store<T> {
    private Stack<T> listOfDetails;
    private int maxCount;
    private int countOfCreatedDetails;

    public Store(int maximum) {
        listOfDetails = new Stack<T>();
        maxCount = maximum;
        countOfCreatedDetails = 0;
    }

    public synchronized void setDetail(T newDetail){
        while(listOfDetails.size() >= maxCount) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        listOfDetails.push(newDetail);
        notifyAll();
        countOfCreatedDetails++;
    }

    public synchronized T getDetail() {
        while(listOfDetails.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T result = listOfDetails.pop();
        notifyAll();
        return result;
    }

    public synchronized int getSize() {
        return listOfDetails.size();
    }

    public synchronized int getMaxSize() {
        return maxCount;
    }

    public synchronized int getCountOfCreatedDetails() {
        return countOfCreatedDetails;
    }
}
