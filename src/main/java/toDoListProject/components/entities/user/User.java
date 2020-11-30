package toDoListProject.components.entities.user;

import java.util.Date;

public class User {
    final private String id;
    final private String surname;
    final private Date created;

    public User(String id, String surname, Date created) {
        this.id = id;
        this.surname = surname;
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }
}
