package WebToDoList.DataBase;

import WebToDoList.Models.Task.ITask;
import WebToDoList.Models.Task.Task;

import java.util.List;

public interface IDb {
    public ITask getTask(String id);
    public List<Task> getTasks(Search filter);
}
