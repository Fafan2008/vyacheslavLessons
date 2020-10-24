package WebToDoList.Models.Task;

import java.util.Date;
import java.util.UUID;

public class TaskBuilder {
    private String id = UUID.randomUUID().toString();
    private String userID;
    private String name;
    private String description;
    private Priority priority = Priority.UNDEFINED;
    private Date timeToComplete;
    private Date created = new Date();

    public TaskBuilder() {}

    public Task buildTask(){
        return new Task(id, userID, name, description, priority, timeToComplete, created);
    }

    public TaskBuilder id(String id){
        this.id = id;
        return this;
    }
    public TaskBuilder userID(String userID){
        this.userID = userID;
        return this;
    }
    public TaskBuilder name(String name){
        this.name = name;
        return this;
    }
    public TaskBuilder description(String description){
        this.description = description;
        return this;
    }
    public TaskBuilder priority(Priority priority){
        this.priority = priority;
        return this;
    }
    public TaskBuilder timeToComplete(Date timeToComplete){
        this.timeToComplete = timeToComplete;
        return this;
    }
    public TaskBuilder created(Date created){
        this.created = created;
        return this;
    }
}
