package toDoListProject.components.entities.task;

import java.util.Date;

public class Task {
    private final String id;
    private final String userId;
    private final String name;
    private final String description;
    private final Date created;

    public Task(String id, String userId, String name, String description, Date created){
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.created = created;
        this.description = description;
    }

    public String getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public String getDescription() { return description; }
    public Date getCreated() { return created; }
}
