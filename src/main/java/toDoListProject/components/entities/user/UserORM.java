package toDoListProject.components.entities.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class UserORM {
    @Id
    private String id;
    private String surname;
    private Date created;

    protected UserORM(){}

    public UserORM( String id, String surname, Date created) {
        this.id = id;
        this.surname = surname;
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "UserORM{" +
                "id='" + id + '\'' +
                ", surname='" + surname + '\'' +
                ", created=" + created +
                '}';
    }
}
