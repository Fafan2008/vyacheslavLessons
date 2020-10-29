package WebToDoList.Interfaces.AppWeb;

import WebToDoList.DataBase.Dispetcher;
import WebToDoList.DataBase.IDispetcher;
import WebToDoList.Interfaces.AMyInterface;
import WebToDoList.Interfaces.IMyInterface;

public class AppWeb extends AMyInterface {
    @Override
    public void initialize(IDispetcher dbDispeptcher) {

    }

    @Override
    public boolean isLife() {
        return false;
    }

    @Override
    public void run() {

    }
}
