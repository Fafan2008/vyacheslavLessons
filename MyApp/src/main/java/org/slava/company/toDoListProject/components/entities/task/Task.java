package org.slava.company.toDoListProject.components.entities.task;

import java.util.Date;
import java.util.Objects;

public class Task {
    private final String uuid;
    private final String ownerId;
    private final String name;
    private final String description;
    private final Date created;
    private final Boolean isOpen;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(uuid, task.uuid) &&
                Objects.equals(ownerId, task.ownerId) &&
                Objects.equals(name, task.name) &&
                Objects.equals(description, task.description) &&
                Objects.equals(created, task.created) &&
                Objects.equals(isOpen, task.isOpen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, ownerId, name, description, created, isOpen);
    }

    public Task(String id, String ownerId, String name, String description, Boolean isOpen, Date created){
        this.uuid = id;
        this.ownerId = ownerId;
        this.name = name;
        this.description = description;
        this.isOpen = isOpen;
        this.created = created;
    }
    public Task(TaskORM taskORM){
        this.uuid = taskORM.getUuid();
        this.ownerId = taskORM.getOwner().getId();
        this.name = taskORM.getName();
        this.description = taskORM.getDescription();
        this.isOpen = taskORM.getOpen();
        this.created = taskORM.getCreated();
    }

    public String getUuid() {
        return this.uuid;
    }
    public String getName() {
        return this.name;
    }
    public String getDescription() { return this.description; }
    public Date getCreated() { return this.created; }
    public Boolean isOpen() { return this.isOpen; }

    public String getOwner() {
        return this.ownerId;
    }
}
