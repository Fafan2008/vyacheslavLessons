package WebToDoList.DataBase;

import WebToDoList.Models.Task.Data;
import WebToDoList.Models.Task.Priority;
import WebToDoList.Models.Task.Task;
import WebToDoList.Models.Task.TaskBuilder;
import WebToDoList.Models.User.User;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class Dispetcher implements IDispetcher{
    private Db db;

    public Dispetcher(Db db){
        this.db = db;
    }

    @Override
    public User getUser(String id)  {
        return db.userMap.get(id);
    }

    @Override
    public Task getTask(String id)   {
        return db.taskMap.get(id);
    }

    @Override
    public void addTask(Task task)  {
        String uuid = UUID.randomUUID().toString();
        Data data = task.getData();
        Task newTask = new TaskBuilder()
                .id(uuid)
                .name(data.name)
                .description(data.description)
                .priority(data.priority)
                .timeToComplete(data.timeToComplete)
                .created(data.created)
                .buildTask();
        db.taskMap.put(uuid, newTask);
    }

    @Override
    public List<Task> getTasks(Search filter) {
        return db.taskMap.entrySet().stream()
                .filter(item -> filter.priority == Priority.UNDEFINED || item.getValue().priority().equals(filter.priority))
                .map(Map.Entry::getValue).collect(Collectors.toList());
    }

    @Override
    public boolean createUser(String login) {
        db.userMap.put(login, new User());
        return true;
    }
}
