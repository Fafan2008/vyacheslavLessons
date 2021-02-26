package toDoListProject.components.repositories.dispetcher.orm;

import org.springframework.beans.factory.annotation.Autowired;
import toDoListProject.app.TaskRepositories;
import toDoListProject.app.UserRepositories;
import toDoListProject.components.repositories.dispetcher.IDB;
import toDoListProject.components.repositories.dispetcher.IDBTest;

public class DBOrmTest extends IDBTest {
    @Autowired
    UserRepositories userRepositories;
    @Autowired
    TaskRepositories taskRepositories;
    @Override
    public IDB createIDB() {
        return new DBOrm(userRepositories, taskRepositories);
    }
}
