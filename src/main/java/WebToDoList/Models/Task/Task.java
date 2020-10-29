package WebToDoList.Models.Task;

import java.util.Date;

public final class Task implements ITask {
    private String id;
    private String userID;
    private String name;
    private String description;
    private Priority priority = Priority.UNDEFINED;
    private Date timeToComplete;
    private Date created = new Date();

    public Task(String id, String userID, String name, String description, Priority priority, Date timeToComplete, Date created) {
        this.id = id;
        this.userID = userID;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.timeToComplete = timeToComplete;
        this.created = created;
    }

    public Priority priority(){
        return this.priority;
    }

    @Override
    public Task update(Update updates) {
        if (updates.name != null)
            this.name = updates.name;
        if (updates.description != null)
            this.description = updates.description;
        if (updates.priority != null)
            this.priority = updates.priority;
        if (updates.timeToComplete != null)
            this.timeToComplete = updates.timeToComplete;
        return new TaskBuilder().name(updates.name == null ? this.name : updates.name)
                .description(updates.description == null ? this.description : updates.description)
                .priority(updates.priority == null ? this.priority : updates.priority)
                .timeToComplete(updates.timeToComplete == null ? this.timeToComplete : updates.timeToComplete).buildTask();
        // I assumed that I should send a request to the remote database and receive a response based on which I can return a boolean result.
        //return new Task();
    }

    @Override
    public Data getData() {
        Data data = new Data();
        data.id = this.id;
        data.userID = this.userID;
        data.name = this.name;
        data.description = this.description;
        data.priority = this.priority;
        data.timeToComplete = this.timeToComplete;
        data.created = this.created;
        return data;
    }
}
