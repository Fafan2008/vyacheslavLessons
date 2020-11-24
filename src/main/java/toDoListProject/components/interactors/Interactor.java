package toDoListProject.components.interactors;




import toDoListProject.components.entities.task.Task;
import toDoListProject.components.entities.task.UpdateTask;
import toDoListProject.components.entities.user.IUpdateUser;
import toDoListProject.components.entities.user.User;
import toDoListProject.components.interactors.exceptions.TaskNotFoundException;
import toDoListProject.components.interactors.exceptions.UserNotFoundException;
import toDoListProject.components.interactors.exceptions.UsernameExistsException;
import toDoListProject.components.repositories.dispetcher.IDispatcher;

import java.util.List;
import java.util.Optional;

public class Interactor implements IInteractor {
    private final IDispatcher iDispatcher;

    public Interactor(IDispatcher iDispatcher) {
        this.iDispatcher = iDispatcher;
    }

    @Override
    public boolean addUser(IUpdateUser user) throws UsernameExistsException {
        return iDispatcher.addUser(User.create(user));
    }

    @Override
    public boolean deleteUser(String userId) throws UserNotFoundException {
        return false;
    }

    @Override
    public boolean updateUser(String userId, IUpdateUser update) throws UserNotFoundException, UsernameExistsException {
        return false;
    }

    @Override
    public Optional<Task> addTask(String userID, UpdateTask task) {
        return iDispatcher.addTask(task);
    }

    @Override
    public Task updateTask(String taskId, UpdateTask update) throws TaskNotFoundException {
        return null;
    }

    @Override
    public void deleteTask(String taskId) throws TaskNotFoundException {
        if (iDispatcher.getTask(taskId).isPresent())
            iDispatcher.deleteTask(taskId);
        else
            throw new TaskNotFoundException();
    }

    @Override
    public List<Task> getTaskList(String userId, boolean onlyOpened) throws UserNotFoundException {
        return null;
    }

    @Override
    public boolean isUserPresent(String name) {
        return iDispatcher.getUser(name).isPresent();
    }
}
