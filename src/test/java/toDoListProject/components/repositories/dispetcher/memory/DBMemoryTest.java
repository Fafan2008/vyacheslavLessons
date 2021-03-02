package toDoListProject.components.repositories.dispetcher.memory;

import toDoListProject.components.repositories.dispetcher.IDB;
import toDoListProject.components.repositories.dispetcher.IDBTest;

import static org.junit.Assert.*;

public class DBMemoryTest extends IDBTest {

    @Override
    public IDB createIDB() {
        return new DBMemory();
    }
}