package toDoListProject.app;

import toDoListProject.components.interactors.IInteractor;
import toDoListProject.components.interactors.Interactor;
import toDoListProject.components.presenters.IPresenter;
import toDoListProject.components.presenters.console.Console;
import toDoListProject.components.repositories.dispetcher.IDB;
import toDoListProject.components.repositories.dispetcher.h2.DBH2;
import toDoListProject.components.repositories.dispetcher.memory.DBMemory;

public class Application {
    public static void main(String[] args) {
        IDB dispetcher = new DBMemory();
        IInteractor interactor = new Interactor(dispetcher);
        IPresenter iPresenter = new Console(interactor);
        //iPresenter.start();

        IDB db = new DBH2();
        IInteractor inter = new Interactor(db);
        IPresenter ipres = new Console(inter);
        ipres.start();
    }
}
