package toDoListProject.components.presenters.console;

import toDoListProject.components.entities.task.ITask;
import toDoListProject.components.interactors.IRequest;
import toDoListProject.components.presenters.IPresenter;

public class Console implements IPresenter {
    @Override
    public ITask createTask() {
        return null;
    }

    @Override
    public String deleteTask() {
        return null;
    }

    @Override
    public boolean haveRequest() {
        return false;
    }

    @Override
    public IRequest getRequest() {
        return null;
    }
}
