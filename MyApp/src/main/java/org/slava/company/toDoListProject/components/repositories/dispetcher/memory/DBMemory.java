package org.slava.company.toDoListProject.components.repositories.dispetcher.memory;

import org.slava.company.toDoListProject.components.repositories.dispetcher.IDB;
import org.slava.company.toDoListProject.components.entities.task.Task;
import org.slava.company.toDoListProject.components.entities.task.UpdateTask;
import org.slava.company.toDoListProject.components.entities.user.UpdateUser;
import org.slava.company.toDoListProject.components.entities.user.User;

import java.util.*;
import java.util.stream.Collectors;

public class DBMemory implements IDB {
    private final Map<String, User> dbUsers = new HashMap<>();
    private final Map<String, Task> dbTasks = new HashMap<>();
    private final Map<String, Set<String>> dbLinkUserTasks = new HashMap<>();

    @Override
    public Optional<Task> addTask(UpdateTask updateTask) {
        if(getUser(updateTask.getOwnerId()).isEmpty())
            return Optional.empty();
        String uuid = UUID.randomUUID().toString();
        String userID = updateTask.getOwnerId();
        Task newTask = new Task(uuid, userID, updateTask.getName(), updateTask.getDescription(), updateTask.isOpen(), new Date());
        dbTasks.put(uuid, newTask);
        if (!dbLinkUserTasks.containsKey(userID)) {
            dbLinkUserTasks.put(userID, new HashSet<>());
        }
        dbLinkUserTasks.get(userID).add(uuid);
        return Optional.ofNullable(dbTasks.get(uuid));
    }
    @Override
    public Optional<Task> getTask(String taskId) {
        return Optional.ofNullable(dbTasks.get(taskId));
    }
    @Override
    public boolean deleteTask(String taskId, UpdateTask updateTask) {
        if (dbTasks.containsKey(taskId) && dbLinkUserTasks.get(updateTask.getOwnerId()).contains(taskId)){
            dbTasks.remove(taskId);
            dbLinkUserTasks.get(updateTask.getOwnerId()).remove(taskId);
            return true;
        }
        return false;
    }
    @Override
    public Optional<Task> updateTask(String taskId, UpdateTask update) {
        if(dbTasks.containsKey(taskId)){
            Task oldTask = dbTasks.get(taskId);
            Task newTask = new Task(taskId, update.getOwnerId(), update.getName(), update.getDescription(), update.isOpen(), oldTask.getCreated());
            if (!oldTask.getOwner().equals(newTask.getOwner())){
                dbLinkUserTasks.get(oldTask.getOwner()).remove(taskId);
                dbLinkUserTasks.get(newTask.getOwner()).add(taskId);
            }
            dbTasks.put(taskId, newTask);
            return Optional.ofNullable(dbTasks.get(taskId));
        }
        return Optional.empty();
    }
    @Override
    public List<Task> getTaskList(String userId, boolean onlyOpened) {
        if(dbLinkUserTasks.containsKey(userId)){
            if(onlyOpened)
                return dbLinkUserTasks.get(userId).stream().map(dbTasks::get).filter(Task::isOpen).collect(Collectors.toList());
            else
                return dbLinkUserTasks.get(userId).stream().map(dbTasks::get).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
    @Override
    public Optional<User> addUser(UpdateUser update) {
        if (!dbUsers.containsKey(update.getId())){
            User newUser = new User(update.getId(), update.getSurname(), new Date());
            dbUsers.put(update.getId(), newUser);
            dbLinkUserTasks.put(update.getId(), new HashSet<>());
            return Optional.ofNullable(dbUsers.get(update.getId()));
        }
        return Optional.empty();
    }
    @Override
    public boolean deleteUser(String id) {
        if(dbUsers.containsKey(id) && dbLinkUserTasks.containsKey(id)){
            dbUsers.remove(id);
            dbLinkUserTasks.get(id).forEach(dbTasks::remove);
            dbLinkUserTasks.remove(id);
            return true;
        }
        return false;
    }
    @Override
    public Optional<User> updateUser(String userID, UpdateUser updateUser) {
        if(dbUsers.containsKey(userID) && dbLinkUserTasks.containsKey(userID)){
            if(!userID.equals(updateUser.getId()) && !dbUsers.containsKey(updateUser.getId())){
                dbUsers.put(updateUser.getId(), new User(updateUser.getId(), updateUser.getSurname(), dbUsers.get(userID).getCreated()));
                for (String taskID: dbLinkUserTasks.get(userID)) {
                    Task oldTask = dbTasks.get(taskID);
                    dbTasks.put(taskID, new Task(taskID, updateUser.getId(), oldTask.getName(), oldTask.getDescription(), oldTask.isOpen(), oldTask.getCreated()));
                    if(!dbLinkUserTasks.containsKey(updateUser.getId()))
                        dbLinkUserTasks.put(updateUser.getId(), new HashSet<>());
                    dbLinkUserTasks.get(updateUser.getId()).add(taskID);
                }
                if (deleteUser(userID))
                    return Optional.ofNullable(dbUsers.get(updateUser.getId()));
            }
            if(userID.equals(updateUser.getId())){
                dbUsers.put(updateUser.getId(), new User(updateUser.getId(), updateUser.getSurname(), dbUsers.get(userID).getCreated()));
                return Optional.ofNullable(dbUsers.get(updateUser.getId()));
            }
        }
        return Optional.empty();
    }
    @Override
    public Optional<User> getUser(String userId) {
        return Optional.ofNullable(dbUsers.get(userId));
    }

    @Override
    public void clearAll() {
        dbLinkUserTasks.clear();
        dbTasks.clear();
        dbUsers.clear();
    }

    @Override
    public void deinitialization() {
        clearAll();
    }
}
