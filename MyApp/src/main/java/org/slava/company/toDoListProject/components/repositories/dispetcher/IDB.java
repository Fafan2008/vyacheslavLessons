package org.slava.company.toDoListProject.components.repositories.dispetcher;

import org.slava.company.toDoListProject.components.entities.task.Task;
import org.slava.company.toDoListProject.components.entities.task.UpdateTask;
import org.slava.company.toDoListProject.components.entities.user.UpdateUser;
import org.slava.company.toDoListProject.components.entities.user.User;

import java.util.List;
import java.util.Optional;
public interface IDB {
    Optional<Task> getTask(String taskId);
    Optional<Task> addTask(UpdateTask task);
    Optional<Task> updateTask(String taskId, UpdateTask update);
    boolean deleteTask(String taskId, UpdateTask updateTask);
    List<Task> getTaskList(String userId, boolean onlyOpened);
    default List<Task> getTaskList(String userId){
        return getTaskList(userId, false);
    }
    Optional<User> addUser(UpdateUser update);
    Optional<User> updateUser(String userID, UpdateUser update);
    boolean deleteUser(String id);
    Optional<User> getUser(String userId);

    void clearAll();
    void deinitialization();
}
