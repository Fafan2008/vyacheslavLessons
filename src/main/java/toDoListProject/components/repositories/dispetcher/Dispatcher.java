package toDoListProject.components.repositories.dispetcher;

import toDoListProject.components.entities.task.Task;
import toDoListProject.components.entities.task.UpdateTask;
import toDoListProject.components.entities.user.IUpdateUser;
import toDoListProject.components.entities.user.User;

import java.util.*;

public class Dispatcher implements IDispatcher {
    private Map<String, User> dbUsers = new HashMap<>();
    private Map<String, Task> dbTasks = new HashMap<>();

    @Override
    public Optional<Task> addTask(UpdateTask updateTask) {
        if(!getUser(updateTask.userId()).isPresent())
            return Optional.empty();
        String uuid = UUID.randomUUID().toString();
        Task newTask = new Task(uuid, updateTask.userId(), updateTask.Name(), updateTask.Description(), new Date());
        dbTasks.put(uuid, newTask);
        return Optional.of(newTask);
    }

    @Override
    public boolean deleteTask(String taskId) {
        return false;
    }

    @Override
    public Task updateTask(String taskId, UpdateTask update) {
        return null;
    }

    @Override
    public boolean addUser(User user) {
        if (dbUsers.containsKey(user.getId()))
            return false;
        else {
            dbUsers.put(user.getId(), user);
            return true;
        }
    }

    @Override
    public boolean deleteUser(String id) {
        return false;
    }

    @Override
    public boolean updateUser(IUpdateUser update) {
        return false;
    }

    @Override
    public Optional<Task> getTask(String taskId) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getUser(String userId) {
        User us = dbUsers.get(userId);
        return Optional.ofNullable(dbUsers.get(userId));
    }

    @Override
    public List<Task> getTaskList(String userId, boolean onlyOpened) {
        return null;
    }
}
