package org.slava.company.toDoListProject.dispetcher.memory;

import org.slava.company.toDoListProject.components.repositories.dispetcher.IDB;
import org.slava.company.toDoListProject.components.repositories.dispetcher.memory.DBMemory;
import org.slava.company.toDoListProject.dispetcher.IDBTest;

public class DBMemoryTest extends IDBTest {

    @Override
    public IDB createIDB() {
        return new DBMemory();
    }
}