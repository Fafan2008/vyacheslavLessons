package WebToDoList.Models.Task;

import java.util.Date;

public class Task implements ITask {
    public String id;
    public String userID;
    public String name;
    public String description;
    public Priority priority = Priority.UNDEFINED;
    public Date timeToComplete;
    public Date created = new Date();

    @Override
    public boolean update(Update updates) {
        if (updates.name != null)
            this.name = updates.name;
        if (updates.description != null)
            this.description = updates.description;
        if (updates.priority != null)
            this.priority = updates.priority;
        if (updates.timeToComplete != null)
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
