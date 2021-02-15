package toDoListProject.components.repositories.dispetcher;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import toDoListProject.components.entities.user.UpdateUser;
import toDoListProject.components.entities.user.User;

import java.util.Optional;

import static org.junit.Assert.*;

public abstract class IDBTest {
    IDB idb;

    public abstract IDB createIDB();

    @Before
    public void init(){
        idb = createIDB();
    }
    @After
    public void deinit(){
        idb.clearAll();
    }
    @AfterClass
    public  void end() {
        idb.deinitialization();
    }
    @Test
    public void addAndGetUserTest(){
        Optional<User> userOpt = idb.addUser(new UpdateUser("ABC", "DEF"));
        assertEquals("ABC", userOpt.get().getId());
        assertEquals("DEF", userOpt.get().getSurname());
        assertEquals(userOpt.get(), idb.getUser("ABC").get());

    }
}