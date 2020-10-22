package WebToDoList.DataBase;

import WebToDoList.Models.Task.ITask;
import WebToDoList.Models.Task.Filter;

import java.util.List;

public interface IDb {
    public ITask getTask(final String id);
    public List<ITask> getTasks(final Filter filter);
}
