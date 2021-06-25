package org.slava.company.toDoListProject.dispetcher.orm;

import org.slava.company.toDoListProject.components.repositories.dispetcher.IDB;
import org.slava.company.toDoListProject.components.repositories.dispetcher.orm.DBOrm;
import org.slava.company.toDoListProject.components.repositories.dispetcher.orm.TaskRepositories;
import org.slava.company.toDoListProject.components.repositories.dispetcher.orm.UserRepositories;
import org.slava.company.toDoListProject.dispetcher.IDBTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

@Import(OrmConfig.class)
public class DBOrmTest extends IDBTest {
    @Autowired
    private UserRepositories userRepositories;
    @Autowired
    private TaskRepositories taskRepositories;

    @Override
    public IDB createIDB() {
        return new DBOrm(userRepositories, taskRepositories);
    }
}
