package toDoListProject.components.presenters.console;

import toDoListProject.components.interactors.IInteractor;
import toDoListProject.components.interactors.UserNotFoundException;
import toDoListProject.components.presenters.IPresenter;

public class Console implements IPresenter {
    private final IInteractor iInteractor;

    public Console(IInteractor iInteractor) {
        this.iInteractor = iInteractor;
    }

    @Override
    public void start() {
        //example
        try {
            iInteractor.deleteUser("abc");
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {

        } finally {

        }
    }

    @Override
    public void stop() {

    }
}
