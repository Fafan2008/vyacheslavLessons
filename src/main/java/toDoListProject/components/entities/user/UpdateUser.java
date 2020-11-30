package toDoListProject.components.entities.user;

public class UpdateUser {
    public UpdateUser(String id, String surname){
        this.id = id;
        this.surname =surname;
    }

    public String getId() {
        return this.id;
    }

    public String getSurname() {
        return surname;
    }

    private String id;
    private String surname;
}
