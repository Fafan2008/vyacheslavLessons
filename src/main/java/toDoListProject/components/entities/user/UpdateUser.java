package toDoListProject.components.entities.user;

import toDoListProject.components.interactors.IInteractor;

public class UpdateUser implements IUpdateUser {
    public UpdateUser(String id, String surname){
        this.id = id;
        this.surname =surname;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    private String id;
    private String surname;
}
