package toDoListProject.components.presenters;

import toDoListProject.components.entities.task.ITask;
import toDoListProject.components.interactors.IRequest;

public interface IPresenter {
    ITask createTask();

    String deleteTask();

    boolean haveRequest();

    IRequest getRequest();
}
