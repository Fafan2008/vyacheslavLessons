package toDoListProject.components.entities.user;

public class User {
    final private String id;
    final private String surname;

    static public User create(IUpdateUser update){
        User user = new User(update.getId(), update.getSurname());
        return user;
    }

    private User(String id, String surname) {
        this.id = id;
        this.surname = surname;
    }

    public String getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }
}
