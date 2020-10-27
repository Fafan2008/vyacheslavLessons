package WebToDoList.DataBase;

import WebToDoList.Models.Task.ITask;
import WebToDoList.Models.Task.Task;
import WebToDoList.Models.User.User;

import java.util.List;

public interface IDb {
    public User getUser(String id);
    public Task getTask(String id);
    public void addTask(String id, Task task);
    public List<Task> getTasks(Search filter);
}
