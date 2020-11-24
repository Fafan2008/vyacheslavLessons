package toDoListProject.app;

import toDoListProject.components.interactors.IInteractor;
import toDoListProject.components.interactors.Interactor;
import toDoListProject.components.presenters.IPresenter;
import toDoListProject.components.presenters.console.Console;
import toDoListProject.components.repositories.dispetcher.Dispatcher;
import toDoListProject.components.repositories.dispetcher.IDispatcher;

public class Application {
    public static void main(String[] args) {
        IDispatcher dispetcher = new Dispatcher();
        IInteractor interactor = new Interactor(dispetcher);
        IPresenter iPresenter = new Console(interactor);
        iPresenter.start();

    }
}
