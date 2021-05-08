package threadpool;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

public class ThreadPool implements Executor {
    private final Queue<Runnable> taskQueue = new ConcurrentLinkedQueue<>();
    private volatile boolean isRunning = true;

    public ThreadPool(int countOfThreads) {
        for(int i = 0; i < countOfThreads; ++i) {
            new Thread(new TaskWorker()).start();
        }
    }

    @Override
    public void execute(Runnable command) {
        if(isRunning) {
            taskQueue.offer(command);
        }
    }

    public void shutdown() {
        isRunning = false;
    }

    private final class TaskWorker implements Runnable {
        @Override
        public void run() {
            while(isRunning) {
                Runnable task = taskQueue.poll();
                if(task != null) {
                    task.run();
                }
            }
        }
    }
}
