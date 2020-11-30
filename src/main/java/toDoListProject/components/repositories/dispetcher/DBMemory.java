package toDoListProject.components.repositories.dispetcher;

import toDoListProject.components.entities.task.Task;
import toDoListProject.components.entities.task.UpdateTask;
import toDoListProject.components.entities.user.UpdateUser;
import toDoListProject.components.entities.user.User;
import toDoListProject.components.interactors.exceptions.UsernameExistsException;

import java.util.*;
import java.util.stream.Collectors;

public class DBMemory implements IDB {
    private Map<String, User> dbUsers = new HashMap<>();
    private Map<String, Task> dbTasks = new HashMap<>();
    private Map<String, Set<String>> dbLinkUserTasks = new HashMap<>();

    @Override
    public Optional<Task> addTask(UpdateTask updateTask) {
        if(!getUser(updateTask.userId()).isPresent())
            return Optional.empty();
        String uuid = UUID.randomUUID().toString();
        String userID = updateTask.userId();
        Task newTask = new Task(uuid, userID, updateTask.Name(), updateTask.Description(), updateTask.isOpen(), new Date());
        dbTasks.put(uuid, newTask);
        if (!dbLinkUserTasks.containsKey(userID)) {
            dbLinkUserTasks.put(userID, new HashSet<>());
        }
        dbLinkUserTasks.get(userID).add(uuid);
        return Optional.of(newTask);
    }

    @Override
    public Optional<Task> getTask(String taskId) {
        return Optional.ofNullable(dbTasks.get(taskId));
    }

    @Override
    public boolean deleteTask(String taskId, UpdateTask updateTask) {
        if (dbTasks.containsKey(taskId) && dbLinkUserTasks.get(updateTask.userId()).contains(taskId)){
            dbTasks.remove(taskId);
            dbLinkUserTasks.get(updateTask.userId()).remove(taskId);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Task> updateTask(String taskId, UpdateTask update) {
        if(dbTasks.containsKey(taskId)){
            Task oldTask = dbTasks.get(taskId);
            Task newTask = new Task(taskId, update.userId(), update.Name(), update.Description(), update.isOpen(), oldTask.getCreated());
            dbTasks.put(taskId, newTask);
            return Optional.of(newTask);
        }
        return Optional.empty();
    }

    @Override
    public List<Task> getTaskList(String userId, boolean onlyOpened) {

        if(dbLinkUserTasks.containsKey(userId)){
            return dbLinkUserTasks.get(userId).stream().map((id)-> dbTasks.get(id)).filter(task -> task.isOpen() == onlyOpened).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<User> addUser(UpdateUser update) {
        if (!dbUsers.containsKey(update.getId())){
            User newUser = new User(update.getId(), update.getSurname(), new Date());
            dbUsers.put(update.getId(), newUser);
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteUser(String id) {
        return false;
    }

    @Override
    public Optional<User> updateUser(UpdateUser update) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getUser(String userId) {
        User us = dbUsers.get(userId);
        return Optional.ofNullable(dbUsers.get(userId));
    }
}
