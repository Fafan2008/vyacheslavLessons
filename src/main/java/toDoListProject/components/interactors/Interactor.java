package toDoListProject.components.interactors;

import toDoListProject.components.entities.task.Task;
import toDoListProject.components.entities.task.UpdateTask;
import toDoListProject.components.entities.user.UpdateUser;
import toDoListProject.components.entities.user.User;
import toDoListProject.components.interactors.exceptions.*;
import toDoListProject.components.presenters.console.Display;
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
        Optional<User> user = IDB.addUser(updateUser);
        return user.isPresent();
    }

    @Override
    public User getUser(UpdateUser update) throws UserNotFoundException {
        if(isUserPresent(update.getId()))
            return IDB.getUser(update.getId()).get();
        else
            throw new UserNotFoundException();
    }

    @Override
    public boolean deleteUser(String userId) throws UserNotFoundException {
        return false;
    }

    @Override
    public boolean updateUser(String userId, UpdateUser update) throws UserNotFoundException, UsernameExistsException {
        if(isUserPresent(userId)){
            if(isUserPresent(update.getId()) && !update.getId().equals(userId))
                throw new UsernameExistsException();
        }
        else{
            throw new UserNotFoundException();
        }
        boolean result = true;
        Optional<User> user =  IDB.updateUser(userId, update);
        if (user.isPresent() && !user.get().getId().equals(userId)){
            List<Task> tasks = IDB.getTaskList(userId);
            for (Task task : tasks){
                //Change owner of tasks.
                UpdateTask updateTask = new UpdateTask(user.get().getId(), task.getName(), task.getDescription(), task.isOpen());
                Optional<Task> newTask = IDB.updateTask(task.getId(), updateTask);
                if (!newTask.isPresent())
                    result = false;
            }
        }
        return result;
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
        if(!task.get().getOwner().equals(update.userId())){
            throw new NotHavePermission();
        }
        if(IDB.updateTask(taskId, update).isPresent())
            return IDB.getTask(taskId).get();
        else
            throw new TaskUpdateOperationFail();
    }

    @Override
    public void deleteTask(String taskId, UpdateTask updateTask) throws TaskNotFoundException, NotHavePermission {
        Optional<Task> task = IDB.getTask(taskId);
        if (!task.isPresent())
            throw new TaskNotFoundException();
        if(task.get().getOwner().equals(updateTask.userId())){
            if(IDB.deleteTask(taskId, updateTask))
                Display.successful();
            else
                Display.unsuccessful();
        }
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
