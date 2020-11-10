package toDoListProject.components.interactors;




import toDoListProject.components.entities.task.Task;
import toDoListProject.components.entities.task.UpdateTask;
import toDoListProject.components.entities.user.UpdateUser;
import toDoListProject.components.repositories.dispetcher.IDispatcher;

import java.util.List;

public class Interactor implements IInteractor {
    private final IDispatcher iDispetcher;

    public Interactor(IDispatcher iDispetcher) {
        this.iDispetcher = iDispetcher;
    }

    @Override
    public Task addTask(UpdateTask task) {
        return null;
    }

    @Override
    public void deleteTask(String taskId) throws TaskNotFoundException {
        if (iDispetcher.getTask(taskId).isPresent())
            iDispetcher.deleteTask(taskId);
        else
            throw new TaskNotFoundException();
    }

    @Override
    public Task updateTask(String taskId, UpdateTask update) throws TaskNotFoundException {
        return null;
    }

    @Override
    public boolean addUser(UpdateUser user) throws UsernameExistsException {
        return false;
    }

    @Override
    public boolean deleteUser(String userId) throws UserNotFoundException {
        return false;
    }

    @Override
    public boolean updateUser(String userId, UpdateUser update) throws UserNotFoundException, UsernameExistsException {
        return false;
    }

    @Override
    public List<Task> getTaskList(String userId, boolean onlyOpened) throws UserNotFoundException {
        return null;
    }
}
