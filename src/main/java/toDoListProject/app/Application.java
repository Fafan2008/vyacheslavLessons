package toDoListProject.app;

import toDoListProject.components.interactors.IInteractor;
import toDoListProject.components.presenters.IPresenter;
import toDoListProject.components.repositories.dispetcher.Dispetcher;
import toDoListProject.components.repositories.dispetcher.IDispetcher;

public class Application {
    public static void main(String[] args) {
        IDispetcher dispetcher = new Dispetcher();
        IPresenter presenter = new Presenter();
        IInteractor interactor = new Interactor(dispetcher, presenter);
        interactor.startWork();
    }
}
