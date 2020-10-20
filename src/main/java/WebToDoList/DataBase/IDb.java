package WebToDoList.DataBase;

import WebToDoList.Models.Task.ITask;

public interface IDb {
    public boolean getTask(final String _id ,ITask _task);

}
