package toDoListProject.components.repositories.dispetcher;

import toDoListProject.components.entities.task.Task;
import toDoListProject.components.entities.task.UpdateTask;
import toDoListProject.components.entities.user.UpdateUser;
import toDoListProject.components.entities.user.User;

import java.util.List;
import java.util.Optional;

public interface IDispatcher {
    Task addTask(UpdateTask task);
    boolean deleteTask(String taskId);
    Task updateTask(String taskId, UpdateTask update);

    //--//--
    boolean addUser(User user);
    boolean deleteUser(String id);
    boolean updateUser(UpdateUser update);

    Optional<Task> getTask(String taskId);
    Optional<User> getUser(String userId);

    //Важно! Норм не норм?
    List<Task> getTaskList(String userId, boolean onlyOpened);

    default List<Task> getTaskList(String userId){
        return getTaskList(userId, false);
    }
    //List<IUser> getUserList(IUserFilter filter);
}
