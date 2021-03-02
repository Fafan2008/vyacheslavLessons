package toDoListProject.components.entities.task;

public class UpdateTask {
    private final String ownerId;
    private final String name;
    private final String description;
    private final Boolean isOpen;

    public UpdateTask(String ownerId, String name, String description, Boolean isOpen) {
        this.ownerId = ownerId;
        this.name = name;
        this.description = description;
        this.isOpen = isOpen;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Boolean isOpen() {
        return this.isOpen;
    }
}
