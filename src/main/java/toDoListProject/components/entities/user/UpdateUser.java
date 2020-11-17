package toDoListProject.components.entities.user;

import toDoListProject.components.interactors.IInteractor;

public class UpdateUser implements IUpdateUser {
    public UpdateUser(String id){
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }
    private String id;
}
