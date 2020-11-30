package toDoListProject.components.entities.task;

import java.util.Date;

public class Task {
    private final String id;
    private final String userId;
    private final String name;
    private final String description;
    private final Date created;
    private final Boolean isOpen;

    public Task(String id, String userId, String name, String description, Boolean isOpen, Date created){
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.isOpen = isOpen;
        this.created = created;
    }

    public String getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public String getDescription() { return this.description; }
    public Date getCreated() { return this.created; }
    public Boolean isOpen() { return this.isOpen; }

    public String getOwner() {
        return this.userId;
    }
}
