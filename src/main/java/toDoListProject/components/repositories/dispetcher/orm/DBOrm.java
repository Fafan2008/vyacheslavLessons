package toDoListProject.components.repositories.dispetcher.orm;

import toDoListProject.components.entities.task.TaskORM;
import toDoListProject.components.entities.user.UserORM;
import toDoListProject.components.entities.task.Task;
import toDoListProject.components.entities.task.UpdateTask;
import toDoListProject.components.entities.user.UpdateUser;
import toDoListProject.components.entities.user.User;
import toDoListProject.components.repositories.dispetcher.IDB;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DBOrm implements IDB {
    UserRepositories userRepositories;
    TaskRepositories taskRepositories;
    public DBOrm(UserRepositories userRepo, TaskRepositories taskRepo){
        this.userRepositories = userRepo;
        this.taskRepositories = taskRepo;
    }

    @Override
    public Optional<Task> getTask(String taskId) {
        return taskRepositories.findById(taskId).map(Task::new);
    }

    @Override
    public Optional<Task> addTask(UpdateTask update) {
        Optional<UserORM> user = userRepositories.findById(update.getOwnerId());
        if(user.isEmpty())
            return Optional.empty();

        //Важно! При создании таски время создания генерируется в конструкторе по умолчанию. После мы сохраняем таску и получаем для неё id сгенерированный репохиторием.
        //Правильный ли это подход?
        TaskORM task = new TaskORM();
        taskRepositories.save(task);
        task.setOwner(user.get());
        task.setDescription(update.getDescription());
        task.setName(update.getName());
        task.setOpen(update.isOpen());
        taskRepositories.save(task);
        return taskRepositories.findById(task.getUuid()).map(Task::new);
    }

    @Override
    public Optional<Task> updateTask(String taskId, UpdateTask update) {
        Optional<TaskORM> task = taskRepositories.findById(taskId);
        if(task.isPresent()){
            if(!task.get().getOwner().getId().equals(update.getOwnerId())){
                Optional<UserORM> user = userRepositories.findById(update.getOwnerId());
                if(user.isPresent())
                    task.get().setOwner(user.get());
                else
                    return Optional.empty();
            }
            task.get().setName(update.getName());
            task.get().setDescription(update.getDescription());
            task.get().setOpen(update.isOpen());
            return Optional.of(new Task(taskRepositories.save(task.get())));
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteTask(String taskId, UpdateTask updateTask) {
        Optional<UserORM> user = userRepositories.findById(updateTask.getOwnerId());
        Optional<TaskORM> task = taskRepositories.findById(taskId);
        if(user.isPresent() && task.isPresent() && user.get().equals(task.get().getOwner()))
            taskRepositories.deleteById(taskId);

        return taskRepositories.findById(taskId).isEmpty();
    }

    @Override
    public List<Task> getTaskList(String userId, boolean onlyOpened) {
        Optional<UserORM> userORM = userRepositories.findById(userId);
        if(userORM.isPresent()){
            if(onlyOpened)
                return taskRepositories.findByOwnerAndIsOpen(userORM.get(), true).stream().map(Task::new).collect(Collectors.toList());
            else
                return taskRepositories.findByOwner(userORM.get()).stream().map(Task::new).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public Optional<User> addUser(UpdateUser update) {
        if(!userRepositories.existsById(update.getId()))
            userRepositories.save(new UserORM(update.getId(), update.getSurname(), new Date()));
        else
            return Optional.empty();
        Optional<UserORM> userORM = userRepositories.findById(update.getId());
        return userORM.map(User::new);
    }

    @Override
    public Optional<User> updateUser(String userID, UpdateUser update) {
        // Важно! Как реализовать каскадное изменение данных при смене ключевого поля у пользователя?
        if(!userID.equals(update.getId())){
            return Optional.empty();
        }
        Optional<UserORM> userORM = userRepositories.findById(userID);
        userORM.ifPresent(orm -> orm.setSurname(update.getSurname()));
        userORM.ifPresent(orm -> userRepositories.save(orm));
        return userRepositories.findById(update.getId()).map(User::new);
    }

    @Override
    public boolean deleteUser(String id) {
        // Важно!!! Как сделать каскадное удаление? Вместе с связанными объектами.
        Optional<UserORM> owner = userRepositories.findById(id);
        if(owner.isPresent()) {
            ArrayList<TaskORM> taskList = (ArrayList<TaskORM>) taskRepositories.findByOwner(owner.get());
            taskList.forEach(task-> taskRepositories.delete(task));
        }
        userRepositories.findById(id).ifPresent(user-> userRepositories.delete(user));
        return !userRepositories.existsById(id);
    }

    @Override
    public Optional<User> getUser(String userId) {
        return userRepositories.findById(userId).map(User::new);
    }

    @Override
    public void clearAll() {
        userRepositories.deleteAll();
        taskRepositories.deleteAll();
    }

    @Override
    public void deinitialization() {
        clearAll();
    }
}
