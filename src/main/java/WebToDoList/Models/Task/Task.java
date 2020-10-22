package WebToDoList.Models.Task;

import java.util.Date;

public class Task implements ITask {
    public String id;
    public String userID;
    public String name;
    public String description;
    public Priority priority;
    public Date timeToComplete;
    public Date created;

    @Override
    public boolean update(Update updates) {
        this.name = updates.name;
        this.description = updates.description;
        this.priority = updates.priority;
        this.timeToComplete = updates.timeToComplete;
        // I assumed that I should send a request to the remote database and receive a response based on which I can return a boolean result.
        return true;
    }

    @Override
    public void clear() {
        this.name = "";
        this.description = "";
        this. priority = Priority.UNDEFINED;
        this.timeToComplete = null;
    }
}
