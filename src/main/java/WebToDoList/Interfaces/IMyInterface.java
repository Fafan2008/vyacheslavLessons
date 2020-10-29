package WebToDoList.Interfaces;

import WebToDoList.DataBase.IDispetcher;

public interface IMyInterface {
    void start();
    void initialize(IDispetcher dbDispeptcher);
    boolean isLife();
}
