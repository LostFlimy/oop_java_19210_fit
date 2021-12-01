package db;

import model.Task;

import java.util.List;
import java.util.Map;

//Будет работать с TaskDao и является посредником между TaskDao и контроллером
public interface TaskService {

    public Task getTaskByTaskId(long tid);

    public Task getTasksByWorkerId(List<Long> uids);

    public void deleteTask(long tid);

    public void addTask(Task task);

    public void updateTasks(Map<Task, Task> mapOldToNew);
}
