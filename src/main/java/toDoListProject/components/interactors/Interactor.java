package toDoListProject.components.interactors;

import toDoListProject.components.entities.task.Task;
import toDoListProject.components.entities.task.UpdateTask;
import toDoListProject.components.entities.user.UpdateUser;
import toDoListProject.components.entities.user.User;
import toDoListProject.components.interactors.exceptions.*;
import toDoListProject.components.repositories.dispetcher.IDB;

import java.util.List;
import java.util.Optional;

public class Interactor implements IInteractor {
    private final IDB IDB;

    public Interactor(IDB IDB) {
        this.IDB = IDB;
    }

    @Override
    public boolean addUser(UpdateUser updateUser) throws UsernameExistsException {
        if(isUserPresent(updateUser.getId()))
            throw new UsernameExistsException();
        return IDB.addUser(updateUser).isPresent();
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
    public Optional<Task> addTask(UpdateTask updateTask) {
        if(isUserPresent(updateTask.userId()))
            return IDB.addTask(updateTask);
        else
            return Optional.empty();
    }

    @Override
    public Task updateTask(String taskId, UpdateTask update) throws TaskNotFoundException, NotHavePermission, TaskUpdateOperationFail {
        Optional<Task> task = IDB.getTask(taskId);
        if(!task.isPresent()){
            throw new TaskNotFoundException();
        }
        if(task.get().getOwner() != update.userId()){
            throw new NotHavePermission();
        }
        if(IDB.updateTask(taskId, update).isPresent())
            return IDB.updateTask(taskId, update).get();
        else
            throw new TaskUpdateOperationFail();
    }

    @Override
    public void deleteTask(String taskId, UpdateTask updateTask) throws TaskNotFoundException, NotHavePermission {
        Optional<Task> task = IDB.getTask(taskId);
        if (!task.isPresent())
            throw new TaskNotFoundException();
        if(task.get().getOwner() == updateTask.userId())
            IDB.deleteTask(taskId, updateTask);
        else
            throw new NotHavePermission();
    }

    @Override
    public List<Task> getTaskList(String userId, boolean onlyOpened) throws UserNotFoundException {
        if(!IDB.getUser(userId).isPresent())
            throw new UserNotFoundException();
        return IDB.getTaskList(userId);
    }

    @Override
    public boolean isUserPresent(String name) {
        return IDB.getUser(name).isPresent();
    }
}
