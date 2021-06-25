package org.slava.company.toDoListProject.components.entities.user;

import java.util.Date;
import java.util.Objects;

public class User {
    final private String id;
    final private String surname;
    final private Date created;

    public User(String id, String surname, Date created) {
        this.id = id;
        this.surname = surname;
        this.created = created;
    }
    public User(UserORM user) {
        this.id = user.getId();
        this.surname = user.getSurname();
        this.created = user.getCreated();
    }
    public String getId() {
        return id;
    }
    public String getSurname() {
        return surname;
    }
    public Date getCreated() {
        return created;
    }
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", surname='" + surname + '\'' +
                ", created=" + created +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(created, user.created);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, surname, created);
    }
}
