package toDoListProject.app;

import toDoListProject.components.interactors.IInteractor;
import toDoListProject.components.interactors.Interactor;
import toDoListProject.components.presenters.IPresenter;
import toDoListProject.components.presenters.console.Console;
import toDoListProject.components.repositories.dispetcher.IDB;
import toDoListProject.components.repositories.dispetcher.h2.DBH2;
import toDoListProject.components.repositories.dispetcher.memory.DBMemory;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class Application {
    public static void main(String[] args) throws IOException {
        //appProps.store(new FileWriter(propertiesPath), "");
        IDB dispatcher = new DBMemory();
        IInteractor interact = new Interactor(dispatcher);
        IPresenter iPresenter = new Console(interact);
        //iPresenter.start();

        String propertiesPath = Paths.get("", "db.properties").toAbsolutePath().toString();
        Properties dbProps = new Properties();
        dbProps.load(new FileInputStream(propertiesPath));
        IDB db = new DBH2(dbProps);
        IInteractor inter = new Interactor(db);
        IPresenter ipres = new Console(inter);
        ipres.start();
    }
}
