package toDoListProject.components.entities.task;

public class UpdateTask {
    private final String userId;
    private final String name;
    private final String description;
    private Boolean isOpen;

    public UpdateTask(String userId, String name, String description, Boolean isOpen) {
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.isOpen = isOpen;
    }

    public String userId() {
        return userId;
    }

    public String Name() {
        return name;
    }

    public String Description() {
        return description;
    }

    public Boolean isOpen() {
        return this.isOpen;
    }
}
