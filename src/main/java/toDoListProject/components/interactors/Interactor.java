package toDoListProject.components.interactors;

import toDoListProject.components.entities.task.ITask;
import toDoListProject.components.presenters.IPresenter;
import toDoListProject.components.repositories.dispetcher.IDispetcher;

public class Interactor extends AInteractor {


    public Interactor(IDispetcher dispetcher, IPresenter presenter) {
        super(dispetcher, presenter);
    }

    @Override
    public void execute(IRequest request) {
        switch (request.getType()){
            case ADD_TASK:
                ITask task = presenter.createTask();
                dispetcher.addTask(task);
                break;
            case DELETE_TASK:
                String id = presenter.deleteTask();
                boolean result = dispetcher.deleteTask(id);
                request.setResult(result);
                break;
            case GET_TASK:
                //не могу прдумать как тут быть.
                break;
            case STOP_WORK:
                this.flagWork = false;
                break;
        }
    }

    @Override
    public void startWork() {
        while (this.flagWork){
            if (presenter.haveRequest())
                execute(presenter.getRequest());
        }
    }
    private boolean flagWork = true;
}
