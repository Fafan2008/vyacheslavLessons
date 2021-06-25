package org.slava.company.toDoListProject.components.interactors;

import org.slava.company.toDoListProject.components.interactors.exceptions.*;
import org.slava.company.toDoListProject.components.entities.task.Task;
import org.slava.company.toDoListProject.components.entities.task.UpdateTask;
import org.slava.company.toDoListProject.components.entities.user.UpdateUser;
import org.slava.company.toDoListProject.components.entities.user.User;

import java.util.List;
import java.util.Optional;

public interface IInteractor {
    Optional<Task> addTask(UpdateTask task);
    void deleteTask(String taskId, UpdateTask updateTask) throws TaskNotFoundException, NotHavePermission;
    Task updateTask(String taskId, UpdateTask update) throws TaskNotFoundException, NotHavePermission, TaskUpdateOperationFail;

    User getUser(UpdateUser update) throws UserNotFoundException;
    boolean addUser(UpdateUser update) throws UsernameExistsException;
    boolean deleteUser(String userId) throws UserNotFoundException;
    boolean updateUser(String userId, UpdateUser update) throws UserNotFoundException, UsernameExistsException;

//    Task getTask(String taskId) ;
//    User getUser(String userId);
    List<Task> getTaskList(String userId, boolean onlyOpened) throws UserNotFoundException;

    default List<Task> getTaskList(String userId) throws UserNotFoundException{
        return getTaskList(userId, false);
    }

    boolean isUserPresent(String name);
}


