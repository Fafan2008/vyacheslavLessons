package toDoListProject.components.interactors;

import toDoListProject.components.presenters.IPresenter;
import toDoListProject.components.repositories.dispetcher.IDispetcher;

public abstract class AInteractor implements IInteractor{
    AInteractor(IDispetcher dispetcher, IPresenter presenter) {
        this.dispetcher = dispetcher;
        this.presenter = presenter;
    }
    protected IDispetcher dispetcher;
    protected IPresenter presenter;
}
