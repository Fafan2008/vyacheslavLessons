package WebToDoList.DataBase;

import WebToDoList.Models.Task.Task;
import WebToDoList.Models.User.User;

import java.util.List;

public interface IDispetcher {
    User getUser(String id);
    Task getTask(String id);
    void addTask(Task task);
    List<Task> getTasks(Search filter);

    boolean createUser(String login);
}
