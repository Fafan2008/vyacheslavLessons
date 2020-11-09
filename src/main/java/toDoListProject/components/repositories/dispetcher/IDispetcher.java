package toDoListProject.components.repositories.dispetcher;

import toDoListProject.components.entities.task.ITask;
import toDoListProject.components.entities.task.IUpdateTask;
import toDoListProject.components.entities.user.IUpdateUser;
import toDoListProject.components.entities.user.IUser;

public interface IDispetcher {
    boolean addTask(ITask task);
    boolean deleteTask(String id);
    boolean updateTask(IUpdateTask update);

    boolean addUser(IUser user);
    boolean deleteUser(String id);
    boolean updateUser(IUpdateUser update);

    ITask getTask(String id);
    IUser getUser(String id);

    //Важно! Норм не норм?
    //List<ITask> getTaskList(ITaskFilter filter);
    //List<IUser> getUserList(IUserFilter filter);
}
