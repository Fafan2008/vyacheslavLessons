package WebToDoList.DataBase;

import WebToDoList.Models.Task.ITask;
import WebToDoList.Models.Task.Priority;
import WebToDoList.Models.Task.Task;
import WebToDoList.Models.User.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Попробуй это.
//https://www.baeldung.com/java-maps-streams
public class Db implements IDb{
    Map<String,Task> taskMap = new HashMap<>();
    Map<String, User> userMap = new HashMap<>();

    @Override
    public ITask getTask(String id) {
        return taskMap.get(id);
    }

    @Override
    public List<Task> getTasks(Search filter) {
        return taskMap.entrySet().stream()
                .filter(item -> filter.priority == Priority.UNDEFINED || item.getValue().priority().equals(filter.priority))
                .map(Map.Entry::getValue).collect(Collectors.toList());
    }
}
