package toDoListProject.components.repositories.dispetcher;

import toDoListProject.components.entities.task.Task;
import toDoListProject.components.entities.task.UpdateTask;
import toDoListProject.components.entities.user.IUpdateUser;
import toDoListProject.components.entities.user.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Dispatcher implements IDispatcher {
    private Map<String, User> dbUser;

    @Override
    public Task addTask(UpdateTask task) {
        return null;
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
        return false;
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
        return Optional.ofNullable(dbUser.get(userId));
    }

    @Override
    public List<Task> getTaskList(String userId, boolean onlyOpened) {
        return null;
    }
}