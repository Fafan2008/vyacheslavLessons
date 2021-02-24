package toDoListProject.app;

import toDoListProject.components.entities.task.Task;
import toDoListProject.components.entities.task.UpdateTask;
import toDoListProject.components.entities.user.UpdateUser;
import toDoListProject.components.entities.user.User;
import toDoListProject.components.repositories.dispetcher.IDB;

import java.util.List;
import java.util.Optional;

public class DBOrm implements IDB {
    UserRepositories userRepositories;
    TaskRepositories taskRepositories;
    public DBOrm(UserRepositories userRepo, TaskRepositories taskRepo){
        this.userRepositories = userRepo;
        this.taskRepositories = taskRepo;
    }

    @Override
    public Optional<Task> getTask(String taskId) {
        return Optional.empty();
    }

    @Override
    public Optional<Task> addTask(UpdateTask task) {
        return Optional.empty();
    }

    @Override
    public Optional<Task> updateTask(String taskId, UpdateTask update) {
        return Optional.empty();
    }

    @Override
    public boolean deleteTask(String taskId, UpdateTask updateTask) {
        return false;
    }

    @Override
    public List<Task> getTaskList(String userId, boolean onlyOpened) {
        return null;
    }

    @Override
    public Optional<User> addUser(UpdateUser update) {
        return Optional.empty();
    }

    @Override
    public Optional<User> updateUser(String userID, UpdateUser update) {
        return Optional.empty();
    }

    @Override
    public boolean deleteUser(String id) {
        return false;
    }

    @Override
    public Optional<User> getUser(String userId) {
        return Optional.empty();
    }

    @Override
    public void clearAll() {

    }

    @Override
    public void deinitialization() {

    }
}
