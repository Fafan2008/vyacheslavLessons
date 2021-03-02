package toDoListProject.app;

import org.hibernate.annotations.GenericGenerator;
import toDoListProject.components.entities.task.UpdateTask;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
@Entity
public class TaskORM {
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    @ManyToOne
    @JoinColumn
    private UserORM owner;

    private String name;
    private String description;
    private Date created;
    private Boolean isOpen;

    public TaskORM() {
        this.created = new Date();
    }

    public TaskORM(String uuid, UserORM owner, String name, String description, Date created, Boolean isOpen) {
        this.uuid = uuid;
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.created = created;
        this.isOpen = isOpen;
    }

    public void setOwner(UserORM owner) {
        this.owner = owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }

    public String getUuid() {
        return uuid;
    }

    public UserORM getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreated() {
        return created;
    }

    public Boolean getOpen() {
        return isOpen;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskORM task = (TaskORM) o;
        return Objects.equals(uuid, task.uuid) &&
                Objects.equals(owner, task.owner) &&
                Objects.equals(name, task.name) &&
                Objects.equals(description, task.description) &&
                Objects.equals(created, task.created) &&
                Objects.equals(isOpen, task.isOpen);
    }
}
