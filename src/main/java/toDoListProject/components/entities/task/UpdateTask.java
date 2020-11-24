package toDoListProject.components.entities.task;

public class UpdateTask {
    private final String userId;
    private final String name;
    private final String description;

    public UpdateTask(String userId, String name, String description) {
        this.userId = userId;
        this.name = name;
        this.description = description;
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
}
