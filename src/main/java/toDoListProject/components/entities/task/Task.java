package toDoListProject.components.entities.task;

public class Task {
    private String id;
    private String name;

    public Task(String id, String name){
        this.id = id;
        this.name = name;
    }

    String getId() {
        return this.id;
    }

    String getName() {
        return this.name;
    }
}
