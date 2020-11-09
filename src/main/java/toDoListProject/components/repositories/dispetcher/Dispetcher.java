package toDoListProject.components.repositories.dispetcher;

import toDoListProject.components.entities.task.ITask;
import toDoListProject.components.entities.task.IUpdateTask;
import toDoListProject.components.entities.user.IUpdateUser;
import toDoListProject.components.entities.user.IUser;

public class Dispetcher implements IDispetcher{
    @Override
    public boolean addTask(ITask task) {
        return false;
    }

    @Override
    public boolean deleteTask(String id) {
        return false;
    }

    @Override
    public boolean updateTask(IUpdateTask update) {
        return false;
    }

    @Override
    public boolean addUser(IUser user) {
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
    public ITask getTask(String id) {
        return null;
    }

    @Override
    public IUser getUser(String id) {
        return null;
    }
}
