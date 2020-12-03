package toDoListProject.components.repositories.dispetcher.h2;

import toDoListProject.components.repositories.dispetcher.IDB;
import toDoListProject.components.repositories.dispetcher.IDBTest;

import static org.junit.Assert.*;

public class DBH2Test extends IDBTest {

    @Override
    public IDB createIDB() {
        return new DBH2();
    }
}